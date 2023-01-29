package operations.brokers;

import domain.Broker;
import operations.AbstractOperation;


public class DeleteBrokerOperation extends AbstractOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Broker)) {
            throw new Exception("Invalid broker data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.delete((Broker) param);
    }
}
