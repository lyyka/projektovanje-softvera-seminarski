package app.controllers;

import app.repositories.GenericRepository;
import domain.Broker;
import domain.Client;
import domain.Deal;
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
import operations.deals.LoadAllDealsOperation;
import operations.deals.LoadDealOperation;
import operations.deals.SaveDealOperation;
import operations.deals.SearchDealsOperation;
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
    
    public Broker login(Broker params) throws Exception
    {
        Broker b = null;
        
        for(Broker br : (new GenericRepository<Broker>()).getAll(new Broker())) {
            if(br.getEmail().equals(params.getEmail()) && br.getPassword().equals(params.getPassword())) {
                b = br;
                break;
            }
        }
        
        return b;
    }
    
    public Client loadClient(Client c) throws Exception
    {
        LoadClientOperation op = new LoadClientOperation();
        op.execute(c);
        return op.getResult();
    }
    
    public void createClient(Client c) throws Exception
    {
        (new CreateClientOperation()).execute(c);
    }
    
    public void updateClient(Client c) throws Exception
    {
        (new UpdateClientOperation()).execute(c);
    }
    
    public void deleteClient(Client c) throws Exception
    {
        (new DeleteClientOperation()).execute(c);
    }
    
    public List<Client> loadAllClients() throws Exception
    {
        LoadAllClientsOperation o = new LoadAllClientsOperation();
        o.execute(new Client());
        return o.getResult();
    }
    
    public List<Client> searchClients(Client c) throws Exception
    {
        SearchClientsOperation o = new SearchClientsOperation();
        o.execute(c);
        return o.getResult();
    }
    
    public Broker loadBroker(Broker b) throws Exception
    {
        LoadBrokerOperation op = new LoadBrokerOperation();
        op.execute(b);
        return op.getResult();
    }
    
    public void createBroker(Broker b) throws Exception
    {
        (new CreateBrokerOperation()).execute(b);
    }
    
    public void updateBroker(Broker b) throws Exception
    {
        (new UpdateBrokerOperation()).execute(b);
    }
    
    public void deleteBroker(Broker b) throws Exception
    {
        (new DeleteBrokerOperation()).execute(b);
    }
    
    public List<Broker> loadAllBrokers() throws Exception
    {
        LoadAllBrokersOperation o = new LoadAllBrokersOperation();
        o.execute(new Broker());
        return o.getResult();
    }
    
    public List<Broker> searchBrokers(Broker b) throws Exception
    {
        SearchBrokersOperation o = new SearchBrokersOperation();
        o.execute(b);
        return o.getResult();
    }
    
    public List<Product> loadAllProducts() throws Exception
    {
        LoadAllProductsOperation o = new LoadAllProductsOperation();
        o.execute(new Product());
        return o.getResult();
    }
    
    public Deal loadDeal(Deal deal) throws Exception
    {
        LoadDealOperation op = new LoadDealOperation();
        op.execute(deal);
        return op.getResult();
    }
    
    public void saveDeal(Deal d) throws Exception
    {
        (new SaveDealOperation()).execute(d);
    }
    
    public List<Deal> searchDeals(Deal d) throws Exception
    {
        SearchDealsOperation o = new SearchDealsOperation();
        o.execute(d);
        return o.getResult();
    }
    
    public List<Deal> loadAllDeals() throws Exception
    {
        LoadAllDealsOperation o = new LoadAllDealsOperation();
        o.execute(new Deal());
        return o.getResult();
    }
}
