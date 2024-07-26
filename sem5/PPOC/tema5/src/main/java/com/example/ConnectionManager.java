package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionManager {
    private final Peer peer;

    public ConnectionManager(Peer peer) {
        this.peer = peer;
    }

    public void connectToPeer(String ipAddress, int port) throws IOException {
        try {
            peer.setPeerSocket(new Socket(ipAddress, port));
            peer.initializeStreams(peer.getPeerSocket());
        } catch (IOException ex) {
            System.err.println("Error connecting to peer: " + ex.getMessage());
        }
    }

    public void closeConnectionWithPeer() throws IOException {
        try {
            if (peer.getPeerSocket() != null) {
                peer.getPeerSocket().close();
                peer.setCurrentlyConnected(false);
            }
        } catch (IOException ex) {
            System.err.println("Error closing connection with peer: " + ex.getMessage());
        }
    }

    public void acceptConnection() throws IOException {
        System.out.println("Waiting for new connections...\n");
        try {
            if (!peer.isCurrentlyConnected()) {
                peer.setPeerSocket(peer.getServerSocket().accept());
                peer.setCurrentlyConnected(true);
                peer.initializeStreams(peer.getPeerSocket());
            }
        } catch (IOException ex) {
            System.err.println("Error accepting connection: " + ex.getMessage());
        }
    }

    public void initializeStreams(Socket socket) throws IOException {
        peer.setReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
        peer.setWriter(new PrintWriter(socket.getOutputStream(), true));
        peer.getWriter().println(new Message(peer.getName(), "!ack").toJson());
        System.out.println("Sent !ack message to peer.");
    }

    public void sendMessage(Message message) {
        peer.getWriter().println(message.toJson());
    }

    public Message receiveMessage() throws IOException {
        String json = peer.getReader().readLine();
        return Message.fromJson(json);
    }

    public void handleConnections() {
        try {
            while (peer.isRunning()) {
                synchronized (Peer.class) {
                    if (!peer.isCurrentlyConnected()) {
                        acceptConnection();
                    } else {
                        listenForMessages();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listenForMessages() {
        try {
            while (peer.isCurrentlyConnected()) {
                if (peer.getReader().ready()) {
                    Message message = receiveMessage();
                    if (message.getContent().contains("!hello")) {
                        peer.connectToPeerFromServer("localhost", 8000, message.getSender());
                        System.out.println("Peer connected.");
                        System.out.print("<" + peer.getName() + "> : ");
                    } else if (message.getContent().contains("!bye")) {
                        closeConnectionWithPeer();
                        System.out.println("Peer disconnected.");
                        System.out.println("Status: " + peer.isCurrentlyConnected());
                    } else {
                        System.out.println("\n" + "<" + message.getSender() + "> : " + message.getContent());
                        System.out.print("<" + peer.getName() + "> : ");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
