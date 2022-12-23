package app.database;

import app.helpers.PropertiesFile;
import java.sql.*;

public class DatabaseBroker {
    private static Connection connection;
    
    public static Connection getConnection() throws SQLException
    {
        if(DatabaseBroker.connection == null || DatabaseBroker.connection.isClosed()) {
            String url = PropertiesFile.get("database_url");
            String user = PropertiesFile.get("database_user");
            String password = PropertiesFile.get("database_password");
            DatabaseBroker.connection = DriverManager.getConnection(url, user, password);
            DatabaseBroker.connection.setAutoCommit(false);
        }
        
        return DatabaseBroker.connection;
    }
}
