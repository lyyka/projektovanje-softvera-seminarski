package app.repositories;

import app.database.DatabaseBroker;
import domain.Broker;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class BrokerRepository implements DbRepository<Broker> {
    @Override
    public List<Broker> getAll()
    {
        try {
            String sql = "select * from brokers";
            List<Broker> brokers = new ArrayList<>();
            Connection connection = DatabaseBroker.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Broker broker = new Broker();
                broker.setId(rs.getLong("id"));
                broker.setFirstName(rs.getString("first_name"));
                broker.setLastName(rs.getString("last_name"));
                broker.setEmail(rs.getString("email"));
                broker.setPhone(rs.getString("phone"));
                broker.setCreatedAt(rs.getDate("created_at"));
                broker.setUpdatedAt(rs.getDate("updated_at"));
                brokers.add(broker);
            }
            rs.close();
            statement.close();
            return brokers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List getAll(Broker broker) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(Broker broker) throws Exception {
        try {
            Connection connection = DatabaseBroker.getInstance().getConnection();
            boolean isNew = broker.getId() == null;
            String sql;
            PreparedStatement statement;
            
            if(isNew) {
                sql = "INSERT into clients (first_name, last_name, email, phone) VALUES (?,?,?,?)";
                statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            } else {
                sql = "update clients set first_name = ?, last_name = ?, email = ?, phone = ? where id = ?";
                statement = connection.prepareStatement(sql);
            }
            
            statement.setString(1, broker.getFirstName());
            statement.setString(2, broker.getLastName());
            statement.setString(3, broker.getEmail());
            statement.setString(4, broker.getPhone());
            
            if(!isNew) {
                statement.setLong(5, broker.getId());
            }
            
            statement.executeUpdate();
            
            if(isNew) {
                ResultSet rsKey = statement.getGeneratedKeys();
                if (rsKey.next()) {
                    Long id = rsKey.getLong(1);
                    broker.setId(id);
                } else {
                    throw new Exception("Broker id was not generated!");
                }
                rsKey.close();
            }
            
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Broker can not be saved!");
        }
    }

    @Override
    public void edit(Broker param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Broker broker) throws Exception {
        try {
            String sql = "delete from brokers id=" + broker.getId();
            System.out.println(sql);
            Connection connection = DatabaseBroker.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
