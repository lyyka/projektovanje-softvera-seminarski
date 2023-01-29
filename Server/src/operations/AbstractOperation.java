package operations;

import app.repositories.DbRepository;
import app.repositories.GenericRepository;
import app.repositories.IRepository;


public abstract class AbstractOperation {
    protected final IRepository repository;

    public AbstractOperation() {
        this.repository = new GenericRepository();
    }

    protected abstract void preconditions(Object param) throws Exception;

    protected abstract void executeOperation(Object param) throws Exception;

    public final void execute(Object param) throws Exception {
        try {
            preconditions(param);
            startTransaction();
            executeOperation(param);
            commitTransaction();
        } catch (Exception ex) {
            ex.printStackTrace();
            rollbackTransaction();
            throw ex;
        } finally {
            disconnect();
        }
    }

    private void startTransaction() throws Exception {
        ((DbRepository) repository).connect();
    }

    private void commitTransaction() throws Exception {
        ((DbRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception {
        ((DbRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((DbRepository) repository).disconnect();
    }
}
