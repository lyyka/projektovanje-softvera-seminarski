package app.database;

import app.helpers.PropertiesFile;
import java.sql.*;

public class DatabaseBroker {
    private Connection connection;
    private static DatabaseBroker instance;

    private DatabaseBroker() {
    }

    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }
    
    public Connection getConnection() throws SQLException
    {
        if(this.connection == null || this.connection.isClosed()) {
            String url = PropertiesFile.getInstance().get("database_url");
            String user = PropertiesFile.getInstance().get("database_user");
            String password = PropertiesFile.getInstance().get("database_password");
            this.connection = DriverManager.getConnection(url, user, password);
            this.connection.setAutoCommit(false);
        }
        
        return this.connection;
    }
    
    public void disconnect() throws SQLException
    {
        if(this.connection != null) {
            if(!this.connection.isClosed()) {
                this.connection.close();
            }
            
            this.connection = null;
        }
    }
}
