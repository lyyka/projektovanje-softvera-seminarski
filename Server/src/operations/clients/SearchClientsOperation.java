package operations.clients;

import domain.Client;
import java.util.List;
import operations.AbstractOperation;


public class SearchClientsOperation extends AbstractOperation {
    private List<Client> result;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Client)) {
            throw new Exception("Invalid client data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.result = this.repository.getAll(param);
    }

    public List<Client> getResult() {
        return result;
    }
}
