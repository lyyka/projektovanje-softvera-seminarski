package app.server;

import app.thread.ProcessClientsRequests;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private List<ProcessClientsRequests> pcrs;

    public Server() {
        this.pcrs = new ArrayList<>();
    }
    
    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(9000);
            while (true) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Connected!");
                handleClient(socket);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void handleClient(Socket socket) throws Exception {
        ProcessClientsRequests pcr = new ProcessClientsRequests(socket);
        pcr.start();
        this.pcrs.add(pcr);
    }
}
