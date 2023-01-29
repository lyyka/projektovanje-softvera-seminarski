package app.server;

import app.database.DatabaseBroker;
import app.thread.ProcessClientsRequests;
import domain.Broker;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.SrvFrm;

public class Server extends Thread {

    private ServerSocket serverSocket;
    private List<ProcessClientsRequests> pcrs;
    private SrvFrm srvFrm;

    public Server(SrvFrm srvFrm) {
        this.srvFrm = srvFrm;
        this.pcrs = new ArrayList<>();
    }
    
    public void run() {
        try {
            serverSocket = new ServerSocket(9000);
            while (serverSocket != null && !serverSocket.isClosed()) {
                System.out.println("Waiting for connection...");
                Socket socket = serverSocket.accept();
                System.out.println("Connected!");
                handleClient(socket);
            }
        } catch (Exception ex) {
            Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void stopServer() throws SQLException, IOException
    {
        for(ProcessClientsRequests pcr : this.pcrs) {
            pcr.stopHandler();
        }
        
        if(serverSocket != null && !serverSocket.isClosed()) {
            serverSocket.close();
            serverSocket = null;
        }
            
        DatabaseBroker.getInstance().disconnect();
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
