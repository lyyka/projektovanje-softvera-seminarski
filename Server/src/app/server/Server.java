package app.server;

import app.thread.ProcessClientsRequests;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

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
        ProcessClientsRequests processClientsRequests = new ProcessClientsRequests(socket);
        processClientsRequests.start();
    }
}
