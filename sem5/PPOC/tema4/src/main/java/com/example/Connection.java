package com.example;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    private final List<Socket> connections= new ArrayList<>();
    public List<Socket> getConnections() {
        return connections;
    }
    public synchronized void addConnection(Socket conn){
        connections.add(conn);
    }

    public synchronized void removeConnection(Socket to) {
        connections.remove(to);
    }

    public boolean containsConnection(Socket socket){
        return connections.contains(socket);
    }

}
