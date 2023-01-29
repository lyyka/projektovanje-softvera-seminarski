package operations.deals;

import domain.Deal;
import domain.ProductFeature;
import operations.AbstractOperation;


public class SaveDealOperation extends AbstractOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Deal)) {
            throw new Exception("Invalid deal data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Deal deal = (Deal) param;
        
        // create product if its new
        if(deal.getProduct().getId() == null) {
            this.repository.add(deal.getProduct());
            
            // If the product was created successfully,
            // create all of its features too
            if(deal.getProduct().getId() == null) {
                for(ProductFeature pf : deal.getProduct().getProductFeatures()) {
                    this.repository.add(pf);
                }
            }
        }
        
        if(deal.getId() != null) {
            this.repository.edit(deal);
        } else {
            this.repository.add(deal);
        }
    }
}
