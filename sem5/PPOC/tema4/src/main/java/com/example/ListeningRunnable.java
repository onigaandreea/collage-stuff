package com.example;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ListeningRunnable implements Runnable {
    private final Socket socket;
    private final Connection connection;

    public ListeningRunnable(Socket socket, Connection connection) {
        this.socket = socket;
        this.connection = connection;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            while (true) {
                try {
                    String message = (String) inputStream.readObject();
                    System.out.println("FROM: " + socket.getRemoteSocketAddress() + ":" + message);
                    if (message.equals("!finish")) {
                        connection.removeConnection(socket);
                        break;
                    }
                } catch (IOException ioe) {
                    System.out.println("ERROR: " + ioe.getMessage());
                    break;
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }

            inputStream.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("SERVER ERROR: " + e.getMessage());
        }
    }

}
