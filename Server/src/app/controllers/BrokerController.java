package app.controllers;

import app.repositories.BrokerRepository;
import domain.Broker;
import java.util.List;

public class BrokerController {
    private BrokerRepository repo;

    public BrokerController() {
        this.repo = new BrokerRepository();
    }
    
    public Broker login(Broker params) {
        Broker b = null;
        
        for(Broker br : this.repo.getAll()) {
            if(br.getEmail().equals(params.getEmail()) && br.getPassword().equals(params.getPassword())) {
                b = br;
                break;
            }
        }
        
        return b;
    }
    
    public List<Broker> all() {
        return this.repo.getAll();
    }
    
    public void save(Broker broker) throws Exception {
        try {
            this.repo.add(broker);
            this.repo.commit();
        } catch (Exception e) {
            this.repo.rollback();
            throw e;
        } finally {
            this.repo.disconnect();
        }
    }
    
    public void delete(Broker broker) throws Exception {
        try {
            this.repo.delete(broker);
            this.repo.commit();
        } catch (Exception e) {
            this.repo.rollback();
            throw e;
        } finally {
            this.repo.disconnect();
        }
    }
    
    public void search() {
        
    }
}
