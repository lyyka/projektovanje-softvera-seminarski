package operations.clients;

import domain.Client;
import operations.AbstractOperation;


public class UpdateClientOperation extends AbstractOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Client)) {
            throw new Exception("Invalid client data!");
        }
        
        // todo: validate other params
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.add((Client) param);
    }

}
