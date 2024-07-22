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

public class Main {
    private static final int PORT = 8000;
    private static int numProducers;
    private static int numConsumers;
    private static int deltaT;
    private static Lock lockFinal = new ReentrantLock();
    private static Condition notDone = lockFinal.newCondition();
    public static boolean done = false;
    public static void main(String[] args) {
        try {
            numProducers = Integer.parseInt(args[0]);
            numConsumers = Integer.parseInt(args[1]);
            deltaT = Integer.parseInt(args[2]);

            Map<Integer, Integer> rankingCountries = null;
            var lastTime = System.currentTimeMillis();
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server listens on port " + PORT);

            MyQueue queue = new MyQueue();
            MyList participantsList = new MyList();
            LockedIds lockedIds = new LockedIds();

            ExecutorService poolProducers = Executors.newFixedThreadPool(numProducers);
            ExecutorService executorForFuture = Executors.newSingleThreadExecutor();
            ExecutorService executorForFinalResults = Executors.newSingleThreadExecutor();
            List<Future<Integer>> futureFinalResults = new ArrayList<>();

            Thread[] consumerThreads = new Thread[numConsumers];
            for (int i = 0; i < numConsumers; i++) {
                consumerThreads[i] = new Consumer(queue, participantsList, lockedIds);
                consumerThreads[i].start();
            }

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected to server");

            ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            Object receivedObject;

            try {
                while ((receivedObject = inputStream.readObject()) != null) {

                    if (receivedObject instanceof List) {
                        List<ParticipantNode> concurentList =  (List<ParticipantNode>) receivedObject;
                        poolProducers.execute(new Producer(queue, concurentList, lockedIds));
                    }
                    if (receivedObject instanceof String request) {
                        if (receivedObject.equals("ranking_by_country")) {
                            var nowTime = System.currentTimeMillis();
                            if ((nowTime - lastTime < deltaT) && rankingCountries != null) {
                                outputStream.writeObject(rankingCountries);
                            }
                            else {
                                Future<Map<Integer, Integer>> rankingFuture = executorForFuture.submit(() -> getRankingCountries(participantsList));
                                rankingCountries = rankingFuture.get();
                                outputStream.writeObject(rankingCountries);
                                lastTime = System.currentTimeMillis();
                            }
                        }
                        else if (request.equals("final_ranking")) {
                            Socket newClientSocket = serverSocket.accept();

                            futureFinalResults.add(executorForFinalResults.submit(() -> {
                                lockFinal.lock();
                                try {
                                    ObjectOutputStream outputStreamFinale = new ObjectOutputStream(newClientSocket.getOutputStream());
                                    if (!Main.done) {
                                        notDone.await();
                                    }

                                    StringBuilder line = new StringBuilder();
                                    for (var p : participantsList.getList()) {
                                        line.append(p.id).append(",").append(p.score).append("\n");
                                    }
                                    outputStreamFinale.writeObject(line.toString());

                                    line.setLength(0);
                                    var finalRanking = getRankingCountries(participantsList);
                                    for (var entry : finalRanking.entrySet()) {
                                        line.append(entry.getKey()).append(",").append(entry.getValue()).append("\n");
                                    }
                                    outputStreamFinale.writeObject(line.toString());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                finally {
                                    lockFinal.unlock();
                                    return 0;
                                }
                            }));
                        }
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
            poolProducers.shutdown();
            boolean finish = false;
            while (!finish) {
                finish = poolProducers.awaitTermination(10, TimeUnit.SECONDS) &&
                        executorForFuture.awaitTermination(10, TimeUnit.SECONDS);
            }
            Main.done = true;
            queue.myNotify();
            lockFinal.lock();
            notDone.signalAll();
            lockFinal.unlock();
            finish = false;
            while (!finish) {
                finish = executorForFinalResults.awaitTermination(10, TimeUnit.SECONDS);
            }
            for (var f : futureFinalResults) {
                f.get();
            }

            for (int i = 0; i < numConsumers; i++) {
                consumerThreads[i].join();
            }

            writeFile("output2.txt", participantsList);
            if (compareFiles()) {
                System.out.println("Files are the same");
            }
            else {
                System.out.println("Files are NOT the same");
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<Integer, Integer> getRankingCountries(MyList results) {
        List<ParticipantNode> partialResults = results.getList();
        Map<Integer, Integer> ranking = new HashMap<>();
        for (int i = 1; i <=5; i++) {
            ranking.put(i, 0);
        }
        for (var res : partialResults) {
            ranking.put(res.country, ranking.get(res.country) + res.score);
        }
        return ranking;
    }

    public static boolean compareFiles() throws IOException {
        BufferedReader br1 = new BufferedReader(new FileReader("output1.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("output2.txt"));
        String line1;
        String line2;
        while ((line1 = br1.readLine()) != null && (line2 = br2.readLine()) != null ) {
            if (!line1.equals(line2)) {
                return false;
            }
        }
        return true;
    }

    public static void writeFile(String fileName, MyList rezultate) throws IOException {
        FileWriter writer = new FileWriter(fileName);
        StringBuilder line = new StringBuilder();

        for (var node : rezultate.getList()) {
            line.append(node.id).append(",").append(node.score).append("\n");
            writer.write(line.toString());
            line.setLength(0);
        }
        writer.close();
    }

}
