package app.controllers;

import app.repositories.GenericRepository;
import domain.Broker;
import domain.Client;
import domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operations.brokers.CreateBrokerOperation;
import operations.brokers.DeleteBrokerOperation;
import operations.brokers.LoadAllBrokersOperation;
import operations.brokers.LoadBrokerOperation;
import operations.brokers.SearchBrokersOperation;
import operations.brokers.UpdateBrokerOperation;
import operations.clients.CreateClientOperation;
import operations.clients.DeleteClientOperation;
import operations.clients.LoadAllClientsOperation;
import operations.clients.LoadClientOperation;
import operations.clients.SearchClientsOperation;
import operations.clients.UpdateClientOperation;
import operations.products.LoadAllProductsOperation;

public class Controller {
    private static Controller instance;
    
    private Controller() {}
    
    public static Controller getInstance()
    {
        if(instance == null) {
            instance = new Controller();
        }
        
        return instance;
    }
    
    public Broker login(Broker params)
    {
        Broker b = null;
        
        try {
            for(Broker br : (new GenericRepository<Broker>()).getAll(new Broker())) {
                if(br.getEmail().equals(params.getEmail()) && br.getPassword().equals(params.getPassword())) {
                    b = br;
                    break;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return b;
    }
    
    public Client loadClient(Client c)
    {
        try {
            LoadClientOperation op = new LoadClientOperation();
            op.execute(c);
            return op.getResult();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return c;
    }
    
    public void createClient(Client c)
    {
        try {
            (new CreateClientOperation()).execute(c);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateClient(Client c)
    {
        try {
            (new UpdateClientOperation()).execute(c);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteClient(Client c)
    {
        try {
            (new DeleteClientOperation()).execute(c);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Client> loadAllClients()
    {
        try {
            LoadAllClientsOperation o = new LoadAllClientsOperation();
            o.execute(new Client());
            return o.getResult();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>();
    }
    
    public List<Client> searchClients(Client c)
    {
        try {
            SearchClientsOperation o = new SearchClientsOperation();
            o.execute(c);
            return o.getResult();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>();
    }
    
    public Broker loadBroker(Broker b)
    {
        try {
            LoadBrokerOperation op = new LoadBrokerOperation();
            op.execute(b);
            return op.getResult();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return b;
    }
    
    public void createBroker(Broker b)
    {
        try {
            (new CreateBrokerOperation()).execute(b);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateBroker(Broker b)
    {
        try {
            (new UpdateBrokerOperation()).execute(b);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteBroker(Broker b)
    {
        try {
            (new DeleteBrokerOperation()).execute(b);
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Broker> loadAllBrokers()
    {
        try {
            LoadAllBrokersOperation o = new LoadAllBrokersOperation();
            o.execute(new Broker());
            return o.getResult();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>();
    }
    
    public List<Broker> searchBrokers(Broker b)
    {
        try {
            SearchBrokersOperation o = new SearchBrokersOperation();
            o.execute(b);
            return o.getResult();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>();
    }
    
    public List<Product> loadAllProducts()
    {
        try {
            LoadAllProductsOperation o = new LoadAllProductsOperation();
            o.execute(new Product());
            return o.getResult();
        } catch (Exception ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>();
    }
}
