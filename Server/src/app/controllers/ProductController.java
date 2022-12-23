package app.controllers;

import app.repositories.ProductRepository;
import domain.Product;
import java.util.List;

public class ProductController {
    public List<Product> all() {
        return (new ProductRepository()).getAll();
    }
}
