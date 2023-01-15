package app.controllers;

import app.repositories.ProductRepository;
import domain.Product;
import java.util.List;

public class ProductController {
    private ProductRepository repo;
    
    public ProductController() {
        this.repo = new ProductRepository();
    }
    
    public List<Product> all() {
        return this.repo.getAll();
    }
    
    public void delete(Product product) throws Exception {
        try {
            this.repo.delete(product);
            this.repo.commit();
        } catch (Exception e) {
            this.repo.rollback();
            throw e;
        } finally {
            this.repo.disconnect();
        }
    }
}
