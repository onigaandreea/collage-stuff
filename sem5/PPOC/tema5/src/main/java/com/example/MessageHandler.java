package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {
    private final Peer peer;
    private static final ExecutorService exec = Executors.newFixedThreadPool(4);

    public MessageHandler(Peer peer) {
        this.peer = peer;
    }

    public void handleConsoleInput(BufferedReader reader) {
        while (peer.isRunning()) {
            System.out.print("<" + peer.getName() + "> : ");
            try {
                String inputMessage = reader.readLine();
                processInputMessage(inputMessage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void processInputMessage(String message) {
        try {
            if (message.startsWith("!hello")) {
                processHelloMessage(message);
            } else if (message.equals("!bye")) {
                processByeMessage();
            } else if (message.equals("!byebye")) {
                processByeByeMessage();
            } else {
                peer.sendMessage(new Message(peer.getName(), message));
            }
        } catch (IOException e) {
            System.out.println("Cannot process the message" + e.getMessage());
        }
    }

    private void processHelloMessage(String message) throws IOException {
        try {
            String name = message.split(" ")[1];

            boolean connectionStatus = peer.connectToPeerFromServer("localhost", 8000, name);

            if (connectionStatus) {
                peer.getWriter().println(new Message(peer.getName(), "!hello").toJson());
                String ackMessage = Message.fromJson(peer.getReader().readLine()).getContent();

                if (ackMessage.compareTo("!ack") == 0) {
                    System.out.println("Connection with " + name);
                    peer.setCurrentlyConnected(true);
                    exec.execute(peer::startMessageListener);
                } else {
                    peer.closeConnectionWithPeer();
                    peer.getWriter().println(new Message(peer.getName(), "!bye").toJson());
                    System.out.println("Connection failed. Did not receive ack message.");
                }
            }
        } catch (IOException ex) {
            System.err.println("Error processing !hello message: " + ex.getMessage());
        }
    }

    private void processByeMessage() throws IOException {
        peer.getWriter().println(new Message(peer.getName(), "!bye").toJson());
        peer.closeConnectionWithPeer();
        System.out.println("Closed connection with peer.");
        System.out.println("Connection status: " + peer.isCurrentlyConnected());
    }

    private void processByeByeMessage() throws IOException {
        peer.getWriter().println(new Message(peer.getName(), "!byebye").toJson());
        peer.disconnectFromServer("localhost", 8000);
        exec.shutdown();
        peer.closeConnectionWithPeer();
        peer.close();
        System.out.println("Peer disconnected.");
        System.exit(0);
    }
}
