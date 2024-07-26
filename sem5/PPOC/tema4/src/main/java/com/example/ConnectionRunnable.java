package com.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ConnectionRunnable implements Runnable {
    private final Connection connections;
    private final int port;

    public ConnectionRunnable(Connection connections, int port) {
        this.connections = connections;
        this.port = port;
    }

    private void startListeningThread(Socket socket) {
        new Thread(new ListeningRunnable(socket, connections)).start();
    }

    private boolean isConnected(Socket socket) {
        return connections.containsConnection(socket);
    }


    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(this.port)) {
            while (true) {
                try {
                    Socket socket = server.accept();
                    if (!isConnected(socket)) {
                        startListeningThread(socket);
                        connections.addConnection(socket);
                        System.out.println("NEW USER JUST CONNECTED:  " + socket.getRemoteSocketAddress().toString());
                    }
                } catch (IOException ioe) {
                    System.out.println(ioe);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
