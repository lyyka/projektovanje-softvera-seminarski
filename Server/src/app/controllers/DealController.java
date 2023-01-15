package app.controllers;

import app.repositories.DealRepository;
import domain.Deal;

public class DealController {
    private DealRepository repo;
    
    public DealController()
    {
        this.repo = new DealRepository();
    }
    
    
    public void save(Deal deal) throws Exception {
        try {
            this.repo.add(deal);
            this.repo.commit();
        } catch (Exception e) {
            this.repo.rollback();
            throw e;
        } finally {
            this.repo.disconnect();
        }
    }
}
