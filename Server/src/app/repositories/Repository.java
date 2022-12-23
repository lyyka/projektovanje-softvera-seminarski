package app.repositories;

import app.database.DatabaseBroker;
import java.sql.*;

public class Repository {
    public Connection connect() throws SQLException
    {
        return DatabaseBroker.getConnection();
    }
    
    public void commit() throws SQLException
    {
        DatabaseBroker.getConnection().commit();
    }
    
    public void rollback() throws SQLException
    {
        DatabaseBroker.getConnection().rollback();
    }
    
    public void disconnect() throws SQLException
    {
        DatabaseBroker.getConnection().close();
    }
}
