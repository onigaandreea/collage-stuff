package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Peer {
    private final String name;
    private final ServerSocket serverSocket;
    private Socket peerSocket;
    private volatile BufferedReader reader;
    private volatile PrintWriter writer;
    private volatile boolean currentlyConnected = false;
    private volatile boolean running = true;
    private static final ExecutorService exec = Executors.newFixedThreadPool(4);
    private final ConnectionManager connectionManager;
    private final MessageHandler messageOperations;
    private final ServerOperations serverOperations;

    public Peer(String name, int port) throws IOException {
        this.name = name;
        this.serverSocket = new ServerSocket(port);
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.writer = new PrintWriter(System.out);
        this.connectionManager = new ConnectionManager(this);
        this.messageOperations = new MessageHandler(this);
        this.serverOperations = new ServerOperations(this);
    }

    public String getName() {
        return name;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Socket getPeerSocket() {
        return peerSocket;
    }

    public void setPeerSocket(Socket peerSocket) {
        this.peerSocket = peerSocket;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public boolean isCurrentlyConnected() {
        return currentlyConnected;
    }

    public void setCurrentlyConnected(boolean currentlyConnected) {
        this.currentlyConnected = currentlyConnected;
    }

    public boolean isRunning() {
        return running;
    }

    public void connectToServer(String serverAddress, int serverPort) throws IOException {
        serverOperations.connectToServer(serverAddress, serverPort);
    }

    public void disconnectFromServer(String serverAddress, int serverPort) throws IOException {
        serverOperations.disconnectFromServer(serverAddress, serverPort);
    }

    public boolean connectToPeerFromServer(String serverAddress, int serverPort, String peerName) throws IOException {
        return serverOperations.connectToPeerFromServer(serverAddress, serverPort, peerName);
    }

    public void connectToPeer(String ipAddress, int port) throws IOException {
        connectionManager.connectToPeer(ipAddress, port);
    }

    public void closeConnectionWithPeer() throws IOException {
        connectionManager.closeConnectionWithPeer();
    }

    public void initializeStreams(Socket socket) throws IOException {
        connectionManager.initializeStreams(socket);
    }

    public void sendMessage(Message message) {
        connectionManager.sendMessage(message);
    }

    public void startMessageListener() {
        connectionManager.listenForMessages();
    }

    public void close() throws IOException {
        synchronized (this) {
            if (peerSocket != null) {
                peerSocket.close();
            }
            serverSocket.close();
            currentlyConnected = false;
            running = false;
        }
    }

    public void handleConsoleInput() {
        messageOperations.handleConsoleInput(reader);
    }

    public void handleConnections() {
        connectionManager.handleConnections();
    }

    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        System.out.print("Enter your name: ");
        String name = reader.readLine();
        System.out.print("Enter a port: ");
        int port = Integer.parseInt(reader.readLine());
        Peer peer = new Peer(name, port);

        Runnable connectToServer = () -> {
            try {
                peer.connectToServer("localhost", 8000);
            } catch (IOException e) {
                System.out.println("Cannot connect to the server!");
            }
        };

        exec.execute(connectToServer);

        Runnable handleConsoleInputTask = peer::handleConsoleInput;
        exec.execute(handleConsoleInputTask);

        Runnable handleConnectionsTask = peer::handleConnections;
        exec.execute(handleConnectionsTask);
    }
}
