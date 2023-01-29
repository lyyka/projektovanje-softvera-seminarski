package operations.clients;

import domain.Client;
import operations.AbstractOperation;


public class UpdateClientOperation extends AbstractOperation {
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Client)) {
            throw new Exception("Invalid client data!");
        }
        
        Client c = (Client) param;
        
        boolean isValid = (c.getFirstName() != null && c.getFirstName().length() >= 2) &&
                (c.getLastName() != null && c.getLastName().length() >= 2) &&
                (c.getEmail() != null && c.getEmail().contains("@") && c.getEmail().length() >= 2) &&
                (c.getPhone() != null && c.getPhone().length() >= 2);
        
        if(!isValid) {
            throw new Exception("Invalid broker data!");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        this.repository.edit((Client) param);
    }

}
