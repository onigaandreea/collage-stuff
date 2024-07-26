package com.example;

import com.example.message.Body;
import com.example.message.Header;
import com.example.message.Message;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Main2 {
    public static final String GETALL = "!getall";
    public static final String CONNECT = "!connect";
    public static final String MESSAGE = "!message";
    public static final String FINISH = "!finish";
    private static int port = 5600;
    private final String ipAdd = "127.0.0.1";
    private static Connection connections = new Connection();

    private static void startListeningThread(Socket socket) {
        new Thread(new ListeningRunnable(socket,connections)).start();
    }

    private static void startAcceptConnectionThread() {
        new Thread(new ConnectionRunnable(connections, port)).start();
    }

    private static Socket getTo(String ip) {
        for (Socket socket : connections.getConnections()) {
            if (socket.getRemoteSocketAddress().toString().split("/")[1].split(":")[0].equals(ip)) {
                return socket;
            }
        }
        return null;
    }

    private static void sendMessage(String messageString, Socket socket) throws IOException {
        Message message = new Message(new Header(System.currentTimeMillis()), new Body(messageString));
        ObjectMapper messageMapper = new ObjectMapper();
        messageMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        StringWriter stringMsg = new StringWriter();
        messageMapper.writeValue(stringMsg, message);
        ObjectOutputStream outputStream = new ObjectOutputStream(
                socket.getOutputStream());
        outputStream.writeObject(stringMsg.toString());
    }

    private static void enterCommand() {
        DataInputStream inputStream = new DataInputStream(System.in);
        while (true) {
            try {
                String line = inputStream.readLine();
                String[] splittedLineArray = line.split(" ");

                //TO DO
                if (CONNECT.equals(splittedLineArray[0])) {
                    String ip = splittedLineArray[1].split(":")[0];
                    int port = Integer.parseInt(splittedLineArray[1].split(":")[1]);
                    InetAddress host = InetAddress.getByName(ip);
                    Socket socket = new Socket(host.getHostName(), port);
                    if (!connections.containsConnection(socket)) {
                        connections.addConnection(socket);
                        startListeningThread(socket);
                    } else {
                        System.out.println("ALREADY CONNECTED!");
                    }

                } else if (MESSAGE.equals(splittedLineArray[0])) {
                    Socket toSocket = getTo(splittedLineArray[1]);
                    if (toSocket != null) {
                        sendMessage(Arrays.stream(splittedLineArray).skip(2).collect(Collectors.joining()), toSocket);
                    } else {
                        System.out.println("COULD NOT FIND CONNECTION");
                    }

                } else if (GETALL.equals(splittedLineArray[0])) {
                    connections.getConnections().forEach(x-> System.out.println(x.getRemoteSocketAddress()));

                } else if (FINISH.equals(splittedLineArray[0])) {
                    Socket to = getTo(splittedLineArray[1]);
                    if (to != null) {
                        ObjectOutputStream outputStream = new ObjectOutputStream(
                                to.getOutputStream());
                        outputStream.writeObject(FINISH);
                        System.out.println("List of connections size: " + connections.getConnections().size());
                        connections.removeConnection(to);
                        System.out.println("List of connections size: " + connections.getConnections().size());

                    }
                }
                else{
                    System.out.println("COMMAND UNKNOWN");
                }

            } catch (IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        startAcceptConnectionThread();
        enterCommand();
    }


}

//!connect 172.31.96.1:6500
//!message 172.31.96.1 salut!!!
//!finish 172.31.96.1