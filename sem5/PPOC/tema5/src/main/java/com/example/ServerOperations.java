package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerOperations {
    private final Peer peer;

    public ServerOperations(Peer peer) {
        this.peer = peer;
    }

    public void connectToServer(String serverAddress, int serverPort) throws IOException {
        Socket serverSocket = new Socket(serverAddress, serverPort);
        PrintWriter serverWriter = new PrintWriter(serverSocket.getOutputStream(), true);
        serverWriter.println("ADD " + peer.getName() + " " + peer.getServerSocket().getLocalPort());
        serverSocket.close();
    }

    public void disconnectFromServer(String serverAddress, int serverPort) throws IOException {
        Socket serverSocket = new Socket(serverAddress, serverPort);
        PrintWriter serverWriter = new PrintWriter(serverSocket.getOutputStream(), true);
        serverWriter.println("DELETE " + peer.getName());
        serverSocket.close();
    }

    public boolean connectToPeerFromServer(String serverAddress, int serverPort, String peerName) throws IOException {
        boolean connectionStatus = false;
        if (!peer.isCurrentlyConnected()) {
            boolean foundPeer = false;
            Socket serverSocket = new Socket(serverAddress, serverPort);
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter serverWriter = new PrintWriter(serverSocket.getOutputStream(), true);
            serverWriter.println("PEERS");
            String peerList = serverReader.readLine();
            String[] peers = peerList.split(",");
            for (String peerInfo : peers) {
                String[] parts = peerInfo.split(":");
                String pName = parts[0];
                int pPort = Integer.parseInt(parts[1]);
                if (pName.compareTo(peerName) == 0 && !peer.isCurrentlyConnected()) {
                    foundPeer = true;
                    peer.setCurrentlyConnected(true);
                    peer.connectToPeer("localhost", pPort);
                    connectionStatus = true;
                }
            }
            if (!foundPeer) {
                System.out.println("Peer with name " + peerName + " wasn't found\n");
            }
            serverSocket.close();
        } else {
            System.out.println("You are connected to a peer!\n");
        }
        return connectionStatus;
    }
}
