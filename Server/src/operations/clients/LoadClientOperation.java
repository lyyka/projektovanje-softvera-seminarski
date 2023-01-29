package operations.clients;

import domain.Client;
import java.util.List;
import operations.AbstractOperation;


public class LoadClientOperation extends AbstractOperation {
    private Client result;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Client)) {
            throw new Exception("Invalid client data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.result = null;
        
        List<Client> bs = this.repository.getAll((Client) param);
        
        if(!bs.isEmpty()) {
            this.result = bs.get(0);
        }
    }

    public Client getResult() {
        return result;
    }

}
