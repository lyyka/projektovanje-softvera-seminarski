package app.repositories;

import app.database.DatabaseBroker;
import domain.Broker;
import domain.Client;
import domain.Deal;
import domain.Product;
import domain.ProductFeature;
import enums.DealStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealRepository implements DbRepository<Deal> {
    @Override
    public List<Deal> getAll()
    {
        try {
            String sql = "select * from deals";
            List<Deal> deals = new ArrayList<>();
            Connection connection = DatabaseBroker.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Deal deal = new Deal();
                deal.setId(rs.getLong("id"));
                deal.setDealValue(rs.getDouble("deal_value"));
                deal.setDealStatus(DealStatus.valueOf(rs.getString("deal_status")));
                deal.setDescription(rs.getString("description"));
                deal.setCreatedAt(rs.getDate("created_at"));
                deal.setUpdatedAt(rs.getDate("updated_at"));
                
                // get client
                String sqli = "select * from clients where id = ?";
                PreparedStatement si = connection.prepareStatement(sqli);
                si.setLong(1, rs.getLong("client_id"));
                ResultSet rsi = si.executeQuery();
                if(rsi.next()) {
                    Client client = new Client();
                    client.setId(rsi.getLong("id"));
                    client.setFirstName(rsi.getString("first_name"));
                    client.setLastName(rsi.getString("last_name"));
                    client.setEmail(rsi.getString("email"));
                    client.setPhone(rsi.getString("phone"));
                    client.setAddress(rsi.getString("address"));
                    client.setCreatedAt(rsi.getDate("created_at"));
                    client.setUpdatedAt(rsi.getDate("updated_at"));
                    deal.setClient(client);
                }
                rsi.close();
                si.close();
                
                // get broker
                sqli = "select * from brokers where id = ?";
                si = connection.prepareStatement(sqli);
                si.setLong(1, rs.getLong("broker_id"));
                rsi = si.executeQuery();
                if(rsi.next()) {
                    Broker broker = new Broker();
                    broker.setId(rsi.getLong("id"));
                    broker.setFirstName(rsi.getString("first_name"));
                    broker.setLastName(rsi.getString("last_name"));
                    broker.setEmail(rsi.getString("email"));
                    broker.setPhone(rsi.getString("phone"));
                    broker.setPassword(rsi.getString("password"));
                    broker.setCreatedAt(rsi.getDate("created_at"));
                    broker.setUpdatedAt(rsi.getDate("updated_at"));
                    deal.setBroker(broker);
                }
                rsi.close();
                si.close();
                
                // get product
                sqli = "select * from products where id = ?";
                si = connection.prepareStatement(sqli);
                si.setLong(1, rs.getLong("product_id"));
                rsi = si.executeQuery();
                if(rsi.next()) {
                    Product product = new Product();
                    product.setId(rsi.getLong("id"));
                    product.setTitle(rsi.getString("title"));
                    deal.setProduct(product);
                }
                rsi.close();
                si.close();
                
                deals.add(deal);
            }
            rs.close();
            statement.close();
           
            return deals;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
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
            
            // Create new product
            if(deal.getProduct().getId() == null) {
                String sqlP = "INSERT INTO products (title) values (?)";
                PreparedStatement statementP = connection.prepareStatement(sqlP, PreparedStatement.RETURN_GENERATED_KEYS);
                statementP.setString(1, deal.getProduct().getTitle());
                statementP.executeUpdate();
                
                ResultSet rsKeyP = statementP.getGeneratedKeys();
                if (rsKeyP.next()) {
                    Long id = rsKeyP.getLong(1);
                    deal.getProduct().setId(id);
                } else {
                    throw new Exception("Product id was not generated!");
                }
                rsKeyP.close();
            
                statementP.close();
                
                // If the product was created successfully,
                // create all of its features too
                if(deal.getProduct().getId() != null) {
                    for(ProductFeature pf : deal.getProduct().getProductFeatures()) {
                        sqlP = "INSERT INTO product_features (product_id, title, val) values (?,?,?)";
                        statementP = connection.prepareStatement(sqlP, PreparedStatement.RETURN_GENERATED_KEYS);
                        statementP.setLong(1, deal.getProduct().getId());
                        statementP.setString(2, pf.getTitle());
                        statementP.setString(3, pf.getVal());
                        statementP.executeUpdate();
                
                        rsKeyP = statementP.getGeneratedKeys();
                        if (rsKeyP.next()) {
                            Long id = rsKeyP.getLong(1);
                            pf.setId(id);
                        } else {
                            throw new Exception("Product feature id was not generated!");
                        }
                        rsKeyP.close();

                        statementP.close();
                    }
                }
            }
            
            // Store the deal
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
