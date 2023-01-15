/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.thread;

import app.controllers.BrokerController;
import app.controllers.ClientController;
import app.controllers.DealController;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Client;
import domain.Broker;
import app.controllers.ProductController;
import domain.Deal;
import domain.Product;

public class ProcessClientsRequests extends Thread {
    Broker loggedInBroker;
    Socket socket;
    Sender sender;
    Receiver receiver;

    public ProcessClientsRequests(Socket socket) {
        this.socket = socket;        
        sender = new Sender(socket);
        receiver = new Receiver(socket);
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
                            Broker b = (new BrokerController()).login(params);
                            response.setResult(b);
                            this.loggedInBroker = b;
                            break;
                        case GET_ALL_PRODUCTS:
                            response.setResult((new ProductController()).all());
                            break;
                        case DELETE_PRODUCT:
                            Product ptod = (Product) request.getArgument();
                            (new ProductController()).delete(ptod);
                            response.setResult(ptod);
                            break;
                        case GET_ALL_CLIENTS:
                            response.setResult((new ClientController()).all());
                            break;
                        case SAVE_CLIENT:
                            Client clientToSave = (Client) request.getArgument();
                            (new ClientController()).save(clientToSave);
                            response.setResult(clientToSave);
                            break;
                        case DELETE_CLIENT:
                            Client clientToDelete = (Client) request.getArgument();
                            (new ClientController()).delete(clientToDelete);
                            response.setResult(clientToDelete);
                            break;
                        case GET_ALL_BROKERS:
                            response.setResult((new BrokerController()).all());
                            break;
                        case SAVE_BROKER:
                            Broker brokerToSave = (Broker) request.getArgument();
                            (new BrokerController()).save(brokerToSave);
                            response.setResult(brokerToSave);
                            break;
                        case DELETE_BROKER:
                            Broker brokerToDelete = (Broker) request.getArgument();
                            (new BrokerController()).delete(brokerToDelete);
                            response.setResult(brokerToDelete);
                            break;
                        case SAVE_DEAL:
                            Deal dealToSave = (Deal) request.getArgument();
                            (new DealController()).save(dealToSave);
                            response.setResult(dealToSave);
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
