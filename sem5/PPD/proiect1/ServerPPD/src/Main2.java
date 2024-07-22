import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main2 {
    private static final int PORT = 8000;
    private static int numProducers;
    private static int numConsumers;
    private static int deltaT;
    private static final Lock resultsLock = new ReentrantLock();
    private static final Condition notDone = resultsLock.newCondition();
    public static boolean processingDone = false;
    static ExecutorService producerPool;
    static ExecutorService executorForFuture = Executors.newSingleThreadExecutor();
    static ExecutorService executorForFinalResults = Executors.newSingleThreadExecutor();
    static List<Future<Integer>> futureFinalResults = new ArrayList<>();
    static ServerSocket serverSocket;
    static Map<Integer, Integer> countryRanking = null;
    static long lastTime = System.currentTimeMillis();
    public static void main(String[] args) throws IOException {
        try {
            numProducers = Integer.parseInt(args[0]);
            numConsumers = Integer.parseInt(args[1]);
            deltaT = Integer.parseInt(args[2]);

            lastTime = System.currentTimeMillis();
            serverSocket = new ServerSocket(PORT);
            System.out.println("The server listens on port " + PORT);

            MyQueue queue = new MyQueue();
            MyList resultsList = new MyList();
            LockedIds lockedIds = new LockedIds();
            producerPool = Executors.newFixedThreadPool(numProducers);

            Thread[] consumersThreads = new Thread[numConsumers];
            for (int i = 0; i < numConsumers; i++) {
                consumersThreads[i] = new Consumer(queue, resultsList, lockedIds);
                consumersThreads[i].start();
            }

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            Object receivedObject;
            try {
                while ((receivedObject = inputStream.readObject()) != null) {

                    if (receivedObject instanceof List) {
                        List<ParticipantNode> newList = (List<ParticipantNode>) receivedObject;
                        producerPool.execute(new Producer(queue, newList, lockedIds));
                    }
                    if (receivedObject instanceof String request) {
                        processRequest(request, outputStream, resultsList);
                    }
                }
            } catch (EOFException e) {
                inputStream.close();
                clientSocket.close();
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
            executorForFinalResults.shutdown();
            executorForFuture.shutdown();
            producerPool.shutdown();
            boolean done = false;
            while (!done) {
                done = producerPool.awaitTermination(10, TimeUnit.SECONDS) && executorForFuture.awaitTermination(10, TimeUnit.SECONDS);
            }
            for (var f : futureFinalResults) {
                f.get();
            }
            for (int i = 0; i < numConsumers; i++) {
                consumersThreads[i].join();
            }

            writeFile("output2.txt", resultsList);
            if (compareFiles()) {
                System.out.println("Files are the same");
            } else {
                System.out.println("Files are NOT the same");
            }

        } catch (IOException | InterruptedException | ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private static void processRequest(String request, ObjectOutputStream outputStream, MyList resultList) throws IOException, ExecutionException, InterruptedException {
        if (request.equals("ranking_by_country")) {
            sendCountryRanking(outputStream, resultList);
        } else if (request.equals("final_ranking")) {
            processFinalResults(outputStream, resultList);
        }
    }

    private static void sendCountryRanking(ObjectOutputStream outputStream, MyList resultList) throws IOException, ExecutionException, InterruptedException {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastTime < deltaT) {
            outputStream.writeObject(getCountryRanking(resultList));
        } else {
            Future<Map<Integer, Integer>> countryRankingFuture = executorForFuture.submit(() -> getCountryRanking(resultList));
            countryRanking = countryRankingFuture.get();
            outputStream.writeObject(countryRanking);
            lastTime = System.currentTimeMillis();
        }
    }

    private static void processFinalResults(ObjectOutputStream outputStream, MyList resultList) throws IOException, InterruptedException {
        Socket newClientSocket = serverSocket.accept();
        futureFinalResults.add(executorForFinalResults.submit(() -> {
            resultsLock.lock();
            try {
                ObjectOutputStream outputStreamFinal = new ObjectOutputStream(newClientSocket.getOutputStream());
                if(!Main2.processingDone) {
                    notDone.await();
                }
                StringBuilder line = new StringBuilder();
                for(var p: resultList.getList()) {
                    line.append(p.id).append(",").append(p.score).append("\n");
                }
                outputStreamFinal.writeObject(line.toString());

                line.setLength(0);
                var finalRanking = getCountryRanking(resultList);
                for(var entry: finalRanking.entrySet()) {
                    line.append(entry.getKey()).append(",").append(entry.getValue()).append("\n");
                }
                outputStreamFinal.writeObject(line.toString());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            finally {
                resultsLock.unlock();
                return 0;
            }
        }));
    }

    public static Map<Integer, Integer> getCountryRanking(MyList resultList) {
        List<ParticipantNode> partialResult = resultList.getList();
        Map<Integer, Integer> ranking = new HashMap<>();
        for (int i = 1; i <=5; i++) {
            ranking.put(i, 0);
        }
        for (var res : partialResult) {
            ranking.put(res.country, ranking.get(res.country) + res.score);
        }
        return ranking;
    }

    public static boolean compareFiles() throws IOException {
        BufferedReader buffer1 = new BufferedReader(new FileReader("output1.txt"));
        BufferedReader buffer2 = new BufferedReader(new FileReader("output2.txt"));
        String line1, line2;
        while ((line1 = buffer1.readLine()) != null && (line2 = buffer2.readLine()) != null) {
            if (!line1.equals(line2)) {
                return false;
            }
        }
        return true;
    }

    public static void writeFile(String fileName, MyList resultList) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        StringBuilder line = new StringBuilder();

        for (var p : resultList.getList()) {
            line.append(p.id).append(",").append(p.score).append("\n");
            writer.write(line.toString());
            line.setLength(0);
        }
        writer.close();
    }
}