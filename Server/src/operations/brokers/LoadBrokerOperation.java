package operations.brokers;

import domain.Broker;
import java.util.List;
import operations.AbstractOperation;


public class LoadBrokerOperation extends AbstractOperation {
    private Broker result;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Broker)) {
            throw new Exception("Invalid broker data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.result = null;
        
        List<Broker> bs = this.repository.getAll((Broker) param);
        
        if(!bs.isEmpty()) {
            this.result = bs.get(0);
        }
    }

    public Broker getResult() {
        return result;
    }
}
