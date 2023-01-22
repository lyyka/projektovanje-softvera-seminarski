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
                broker.setPassword(rs.getString("password"));
                broker.setCreatedAt(rs.getDate("created_at"));
                broker.setUpdatedAt(rs.getDate("updated_at"));
                brokers.add(broker);
            }
            rs.close();
            statement.close();
            return brokers;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List getAll(Broker param) throws Exception {
        try {
            
            String sql = "select * from brokers where first_name like ? or last_name like ? or email like ? or phone like ?";
            List<Broker> brokers = new ArrayList<>();
            Connection connection = DatabaseBroker.getInstance().getConnection();

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + param.getFirstName() + "%");
            statement.setString(2, "%" + param.getLastName() + "%");
            statement.setString(3, "%" + param.getEmail() + "%");
            statement.setString(4, "%" + param.getPhone() + "%");
            
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Broker broker = new Broker();
                broker.setId(rs.getLong("id"));
                broker.setFirstName(rs.getString("first_name"));
                broker.setLastName(rs.getString("last_name"));
                broker.setEmail(rs.getString("email"));
                broker.setPhone(rs.getString("phone"));
                broker.setPassword(rs.getString("password"));
                broker.setCreatedAt(rs.getDate("created_at"));
                broker.setUpdatedAt(rs.getDate("updated_at"));
                brokers.add(broker);
            }
            rs.close();
            statement.close();
            System.out.println("Search3");
            return brokers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void add(Broker broker) throws Exception {
        try {
            Connection connection = DatabaseBroker.getInstance().getConnection();
            boolean isNew = broker.getId() == null;
            String sql;
            PreparedStatement statement;
            
            if(isNew) {
                sql = "INSERT into brokers (first_name, last_name, email, phone, password) VALUES (?,?,?,?,?)";
                statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            } else {
                sql = "update brokers set first_name = ?, last_name = ?, email = ?, phone = ?, password = ? where id = ?";
                statement = connection.prepareStatement(sql);
            }
            
            statement.setString(1, broker.getFirstName());
            statement.setString(2, broker.getLastName());
            statement.setString(3, broker.getEmail());
            statement.setString(4, broker.getPhone());
            statement.setString(5, broker.getPassword());
            
            if(!isNew) {
                statement.setLong(6, broker.getId());
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
            String sql = "delete from brokers where id=" + broker.getId();
            Connection connection = DatabaseBroker.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
