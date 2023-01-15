package app.repositories;

import app.database.DatabaseBroker;
import domain.Client;
import domain.Deal;
import java.sql.*;
import java.util.List;

public class DealRepository implements DbRepository<Deal> {
    @Override
    public List<Deal> getAll()
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
   
    @Override
    public void delete(Deal deal) throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Deal> getAll(Deal param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(Deal deal) throws Exception {
        try {
            Connection connection = DatabaseBroker.getInstance().getConnection();
            boolean isNew = deal.getId() == null;
            String sql;
            PreparedStatement statement;
            
            if(isNew) {
                sql = "INSERT into deals (client_id, broker_id, product_id, deal_value, deal_status, description) VALUES (?,?,?,?,?,?)";
                statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            } else {
                sql = "update deals set client_id = ?, broker_id = ?, product_id = ?, deal_value = ?, deal_status = ?, description = ? where id = ?";
                statement = connection.prepareStatement(sql);
            }
            
            statement.setLong(1, deal.getClient().getId());
            statement.setLong(2, deal.getBroker().getId());
            statement.setLong(3, deal.getProduct().getId());
            statement.setDouble(4, deal.getDealValue());
            statement.setString(5, deal.getDealStatus().toString());
            statement.setString(6, deal.getDescription());
            
            if(!isNew) {
                statement.setLong(7, deal.getId());
            }
            
            statement.executeUpdate();
            
            if(isNew) {
                ResultSet rsKey = statement.getGeneratedKeys();
                if (rsKey.next()) {
                    Long id = rsKey.getLong(1);
                    deal.setId(id);
                } else {
                    throw new Exception("Deal id was not generated!");
                }
                rsKey.close();
            }
            
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("Deal can not be saved!");
        }
    }

    @Override
    public void edit(Deal param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
