package operations.brokers;

import domain.Broker;
import operations.AbstractOperation;


public class CreateBrokerOperation extends AbstractOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Broker)) {
            throw new Exception("Invalid broker data!");
        }
        
        Broker b = (Broker) param;
        
        boolean isValid = (b.getFirstName() != null && b.getFirstName().length() >= 2) &&
                (b.getLastName() != null && b.getLastName().length() >= 2) &&
                (b.getEmail() != null && b.getEmail().contains("@") && b.getEmail().length() >= 2) &&
                (b.getPhone() != null && b.getPhone().length() >= 2) &&
                (b.getPassword() != null && b.getPassword().length() >= 8);
        
        if(!isValid) {
            throw new Exception("Invalid broker data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.add((Broker) param);
    }
}
