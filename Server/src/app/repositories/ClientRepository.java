package app.repositories;

import app.database.DatabaseBroker;
import domain.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository extends Repository {
    public List<Client> getAll()
    {
        try {
            String sql = "select * from clients";
            List<Client> clients = new ArrayList<>();
            Connection connection = DatabaseBroker.getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("id"));
                client.setFirstName(rs.getString("first_name"));
                client.setLastName(rs.getString("last_name"));
                client.setEmail(rs.getString("email"));
                client.setPhone(rs.getString("phone"));
                client.setAddress(rs.getString("address"));
                client.setCreatedAt(rs.getDate("created_at"));
                client.setUpdatedAt(rs.getDate("updated_at"));
                clients.add(client);
            }
            rs.close();
            statement.close();
           
            return clients;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public Client save(Client client) throws Exception
    {
        try {
            Connection connection = DatabaseBroker.getConnection();
            boolean isNew = client.getId() == null;
            String sql;
            PreparedStatement statement;
            
            if(isNew) {
                sql = "INSERT into clients (first_name, last_name, email, phone, address) VALUES (?,?,?,?,?)";
                statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            } else {
                sql = "update clients set first_name = ?, last_name = ?, email = ?, phone = ?, address = ? where id = ?";
                statement = connection.prepareStatement(sql);
            }
            
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getPhone());
            statement.setString(5, client.getAddress());
            
            if(!isNew) {
                statement.setLong(6, client.getId());
            }
            
            statement.executeUpdate();
            
            if(isNew) {
                ResultSet rsKey = statement.getGeneratedKeys();
                if (rsKey.next()) {
                    Long id = rsKey.getLong(1);
                    client.setId(id);
                } else {
                    throw new Exception("Client id was not generated!");
                }
                rsKey.close();
            }
            
            statement.close();
        
            return client;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Client can not be saved!");
        }
    }
    
    public void delete(Client client) throws Exception
    {
        try {
            String sql = "delete from clients id=" + client.getId();
            System.out.println(sql);
            Connection connection = DatabaseBroker.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}