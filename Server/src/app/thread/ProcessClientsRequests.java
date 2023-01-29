/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.thread;

import app.controllers.Controller;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Client;
import domain.Broker;
import domain.Deal;
import main.SrvFrm;

public class ProcessClientsRequests extends Thread {
    Broker loggedInBroker;
    Socket socket;
    Sender sender;
    Receiver receiver;
    SrvFrm srvFrm;

    public ProcessClientsRequests(Socket socket) {
        this.socket = socket;        
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public void setSrvFrm(SrvFrm srvFrm) {
        this.srvFrm = srvFrm;
    }
    
    public Broker getLoggedInBroker() {
        return loggedInBroker;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Request request = (Request) receiver.receive();
                Response response = new Response();
                try {
                    switch (request.getOperation()) {
                        case LOGIN:
                            Broker params = (Broker) request.getArgument();
                            Broker b = Controller.getInstance().login(params);
                            response.setResult(b);
                            this.loggedInBroker = b;
                            this.srvFrm.refreshList();
                            break;
                        case GET_ALL_PRODUCTS:
                            response.setResult(Controller.getInstance().loadAllProducts());
                            break;
                        case GET_ALL_CLIENTS:
                            response.setResult(Controller.getInstance().loadAllClients());
                            break;
                        case LOAD_CLIENT:
                            Client clientToLoad = (Client) request.getArgument();
                            response.setResult(Controller.getInstance().loadClient(clientToLoad));
                            break;
                        case SEARCH_CLIENTS:
                            Client searchParamClient = (Client) request.getArgument();
                            response.setResult(Controller.getInstance().searchClients(searchParamClient));
                            break;
                        case SAVE_CLIENT:
                            Client clientToSave = (Client) request.getArgument();
                            if(clientToSave.getId() != null) {
                                Controller.getInstance().updateClient(clientToSave);
                            } else {
                                Controller.getInstance().createClient(clientToSave);
                            }
                            response.setResult(clientToSave);
                            break;
                        case DELETE_CLIENT:
                            Client clientToDelete = (Client) request.getArgument();
                            Controller.getInstance().deleteClient(clientToDelete);
                            response.setResult(clientToDelete);
                            break;
                        case GET_ALL_BROKERS:
                            response.setResult(Controller.getInstance().loadAllBrokers());
                            break;
                        case LOAD_BROKER:
                            Broker brokerToLoad = (Broker) request.getArgument();
                            response.setResult(Controller.getInstance().loadBroker(brokerToLoad));
                            break;
                        case SEARCH_BROKERS:
                            Broker searchParamBroker = (Broker) request.getArgument();
                            response.setResult(Controller.getInstance().searchBrokers(searchParamBroker));
                            break;
                        case SAVE_BROKER:
                            Broker brokerToSave = (Broker) request.getArgument();
                            if(brokerToSave.getId() != null) {
                                Controller.getInstance().updateBroker(brokerToSave);
                            } else {
                                Controller.getInstance().createBroker(brokerToSave);
                            }
                            response.setResult(brokerToSave);
                            break;
                        case DELETE_BROKER:
                            Broker brokerToDelete = (Broker) request.getArgument();
                            Controller.getInstance().deleteBroker(brokerToDelete);
                            response.setResult(brokerToDelete);
                            break;
                        case LOAD_DEAL:
                            Deal dealToLoad = (Deal) request.getArgument();
                            response.setResult(Controller.getInstance().loadDeal(dealToLoad));
                            break;
                        case SEARCH_DEALS:
                            Deal searchParamDeal = (Deal) request.getArgument();
                            response.setResult(Controller.getInstance().searchDeals(searchParamDeal));
                            break;
                        case SAVE_DEAL:
                            Deal dealToSave = (Deal) request.getArgument();
                            Controller.getInstance().saveDeal(dealToSave);
                            response.setResult(dealToSave);
                            break;
                        case GET_ALL_DEALS:
                            response.setResult(Controller.getInstance().loadAllDeals());
                            break;
                    }
                } catch (Exception e) {
                    response.setException(e);
                }
                sender.send(response);
            } catch (Exception ex) {
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
