package operations.products;

import domain.Product;
import domain.ProductFeature;
import java.util.List;
import operations.AbstractOperation;

public class LoadAllProductsOperation extends AbstractOperation {
    private List<Product> result;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Product)) {
            throw new Exception("Invalid product data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.result = this.repository.getAll(param);
        
        for(Product p : this.result) {
            ProductFeature s = new ProductFeature();
            s.setProduct(p);
            
            p.setProductFeatures(this.repository.getAll(s));
        }
    }

    public List<Product> getResult() {
        return result;
    }
}
