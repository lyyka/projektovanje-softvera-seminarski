package app.server;

import app.thread.ProcessClientsRequests;
import domain.Broker;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import main.SrvFrm;

public class Server extends Thread {

    private List<ProcessClientsRequests> pcrs;
    private SrvFrm srvFrm;

    public Server(SrvFrm srvFrm) {
        this.srvFrm = srvFrm;
        this.pcrs = new ArrayList<>();
    }
    
    public void run() {
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
    
    public void refreshLoggedInList()
    {
        this.srvFrm.refreshList();
    }
    
    public void removePcr(ProcessClientsRequests pcr)
    {
        this.pcrs.remove(pcr);
    }

    private void handleClient(Socket socket) throws Exception {
        ProcessClientsRequests pcr = new ProcessClientsRequests(socket);
        pcr.setServer(this);
        pcr.start();
        this.pcrs.add(pcr);
    }
    
    public List<Broker> getLoggedInBrokers()
    {
        List<Broker> res = new ArrayList<>();
        for(ProcessClientsRequests pcr : this.pcrs) {
            if(pcr.getLoggedInBroker() != null) {
                res.add(pcr.getLoggedInBroker());
            }
        }
        return res;
    }
}
