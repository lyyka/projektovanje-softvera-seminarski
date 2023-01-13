package controllers;

import communication.Operation;
import communication.Receiver;
import communication.Request;
import communication.Response;
import communication.Sender;
import domain.Broker;
import domain.Client;
import java.net.Socket;

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
    
    public void addClient(Client client) throws Exception
    {
        Request request = new Request(Operation.SAVE_CLIENT, client);
        sender.send(request);
        Response response=(Response)receiver.receive();
        
        if(response.getException()!=null){
            throw response.getException();
        }
    }
}
