package app.repositories;

import app.database.DatabaseBroker;
import domain.Product;
import domain.ProductFeature;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements DbRepository<Product> {
    @Override
    public List<Product> getAll(Product param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void add(Product param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void edit(Product param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Product param) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Product> getAll() {
        try {
            String sql = "select * from products";
            List<Product> products = new ArrayList<>();
            Connection connection = DatabaseBroker.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setTitle(rs.getString("title"));
                
                String sqlFeatures = "select * from product_features where product_id = ?";
                List<ProductFeature> productFeatures = new ArrayList<>();
                PreparedStatement statementFeatures = connection.prepareStatement(sqlFeatures);
                statementFeatures.setLong(1, product.getId());
                ResultSet rsFeatures = statementFeatures.executeQuery();
                
                while(rsFeatures.next()) {
                    ProductFeature pf = new ProductFeature();
                    pf.setId(rsFeatures.getLong(1));
                    pf.setProduct(product);
                    pf.setTitle(rsFeatures.getString(3));
                    pf.setVal(rsFeatures.getString(4));
                    productFeatures.add(pf);
                }
                
                product.setProductFeatures(productFeatures);
                products.add(product);
            }
            rs.close();
            statement.close();
            return products;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
