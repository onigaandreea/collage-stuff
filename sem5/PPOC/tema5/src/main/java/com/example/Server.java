package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private final Map<String, Integer> peerMap = new HashMap<>();

    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Waiting for connections...");
            while(true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {
                         System.out.println("New client: " + clientSocket);
                         String command = reader.readLine();
                         if (command.startsWith("ADD")) {
                             addPeer(command);
                         } else if (command.startsWith("DELETE")) {
                             deletePeer(command);
                         } else if (command.equals("PEERS")) {
                             allPeers(writer);
                         }
                     } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void addPeer(String command) {
        String[] cmd = command.split(" ");
        String peerName = cmd[1];
        int peerPort = Integer.parseInt(cmd[2]);

        peerMap.put(peerName, peerPort);
    }
    private void deletePeer(String command) {
        String[] cmd = command.split(" ");
        String peerName = cmd[1];
        peerMap.remove(peerName);
    }
    private void allPeers(PrintWriter writer) {
        writer.println(String.join(",", peerMap.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue())
                .toArray(String[]::new)));
    }
    public static void main(String[] args) throws IOException {
        Server tracker = new Server();
        tracker.start(8000);
    }
}
