package app.controllers;

import app.repositories.ClientRepository;
import domain.Client;
import java.util.List;

public class ClientController {
    
    private ClientRepository repo;
    
    public ClientController() {
        this.repo = new ClientRepository();
    }
    
    public List<Client> all() {
        return this.repo.getAll();
    }
    
    public void save(Client client) throws Exception {
        try {
            this.repo.add(client);
            this.repo.commit();
        } catch (Exception e) {
            this.repo.rollback();
            throw e;
        } finally {
            this.repo.disconnect();
        }
    }
    
    public void delete(Client client) throws Exception {
        try {
            this.repo.delete(client);
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
