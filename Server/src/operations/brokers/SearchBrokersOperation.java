package operations.brokers;

import domain.Broker;
import java.util.List;
import operations.AbstractOperation;


public class SearchBrokersOperation extends AbstractOperation {
    private List<Broker> result;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Broker)) {
            throw new Exception("Invalid broker data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.result = this.repository.getAll((Broker) param);
    }

    public List<Broker> getResult() {
        return result;
    }
}
