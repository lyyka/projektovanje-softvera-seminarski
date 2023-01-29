package controllers;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Broker;
import domain.Client;
import domain.Deal;
import domain.Product;
import java.net.Socket;
import java.util.List;

public class Communication {
    Socket socket;
    Sender sender;
    Receiver receiver;
    private static Communication instance;
    
    private Communication() throws Exception{
        socket=new Socket("127.0.0.1", 9000);
        sender=new Sender(socket);
        receiver=new Receiver(socket);
    }
    
    public static Communication getInstance() throws Exception{
        if(instance==null){
            instance=new Communication();
        }
        return instance;
    }
    
    public Broker login(String email, String password) throws Exception {
        Broker user= new Broker();
        user.setEmail(email);
        user.setPassword(password);
        Request request = new Request(Operation.LOGIN, user);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (Broker) response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public void saveClient(Client client) throws Exception
    {
        Request request = new Request(Operation.SAVE_CLIENT, client);
        sender.send(request);
        Response response=(Response)receiver.receive();
        
        if(response.getException()!=null){
            throw response.getException();
        }
    }
    
    public void saveBroker(Broker broker) throws Exception
    {
        Request request = new Request(Operation.SAVE_BROKER, broker);
        sender.send(request);
        Response response=(Response)receiver.receive();
        
        if(response.getException()!=null){
            throw response.getException();
        }
    }
    
    public void deleteBroker(Broker broker) throws Exception
    {
        Request request = new Request(Operation.DELETE_BROKER, broker);
        sender.send(request);
        Response response=(Response)receiver.receive();
        
        if(response.getException()!=null){
            throw response.getException();
        }
    }
    
    public void deleteClient(Client client) throws Exception
    {
        Request request = new Request(Operation.DELETE_CLIENT, client);
        sender.send(request);
        Response response=(Response)receiver.receive();
        
        if(response.getException()!=null){
            throw response.getException();
        }
    }
    
    public List<Deal> getAllDeals() throws Exception
    {
        Request request = new Request(Operation.GET_ALL_DEALS, null);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Deal>) response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public List<Client> getAllClients() throws Exception
    {
        Request request = new Request(Operation.GET_ALL_CLIENTS, null);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Client>) response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public List<Client> getAllClients(Client param) throws Exception
    {
        Request request = new Request(Operation.SEARCH_CLIENTS, param);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Client>) response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public List<Broker> getAllBrokers() throws Exception
    {
        Request request = new Request(Operation.GET_ALL_BROKERS, null);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Broker>) response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public List<Broker> getAllBrokers(Broker param) throws Exception
    {
        Request request = new Request(Operation.SEARCH_BROKERS, param);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Broker>) response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public List<Product> getAllProducts() throws Exception
    {
        Request request = new Request(Operation.GET_ALL_PRODUCTS, null);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Product>) response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public void saveDeal(Deal deal) throws Exception
    {
        Request request = new Request(Operation.SAVE_DEAL, deal);
        sender.send(request);
        Response response=(Response)receiver.receive();
        
        if(response.getException()!=null){
            throw response.getException();
        }
    }
}
