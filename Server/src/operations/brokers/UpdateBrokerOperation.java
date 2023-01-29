package operations.brokers;

import domain.Broker;
import operations.AbstractOperation;


public class UpdateBrokerOperation extends AbstractOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Broker)) {
            throw new Exception("Invalid broker data!");
        }
        
        // todo: validate other params
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.add((Broker) param);
    }
}
