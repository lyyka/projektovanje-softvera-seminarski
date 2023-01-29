package operations.deals;

import domain.Broker;
import domain.Client;
import domain.Deal;
import domain.Product;
import java.util.List;
import operations.AbstractOperation;


public class LoadDealOperation extends AbstractOperation {
    private Deal result;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Deal)) {
            throw new Exception("Invalid deal data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        List<Deal> res = this.repository.getAll(param);
        
        if(!res.isEmpty()) {
            Deal deal = res.get(0);
            Client c = deal.getClient();
            Broker b = deal.getBroker();
            Product p = deal.getProduct();

            if(c != null && c.getId() != null) {
                deal.setClient(
                        (Client) this.repository.getAll(c).get(0)
                );
            }

            if(b != null && b.getId() != null) {
                deal.setBroker(
                        (Broker) this.repository.getAll(b).get(0)
                );
            }

            if(p != null && p.getId() != null) {
                deal.setProduct(
                        (Product) this.repository.getAll(p).get(0)
                );
            }
            
            this.result = deal;
        }
    }

    public Deal getResult() {
        return result;
    }
}
