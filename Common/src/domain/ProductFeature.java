package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductFeature implements GenericEntity {
    private Long id;
    private Product product;
    private String title;
    private String val;
    
    public ProductFeature() {}

    public ProductFeature(Long id, Product product, String title, String val) {
        this.id = id;
        this.product = product;
        this.title = title;
        this.val = val;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return title; 
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.product);
        hash = 17 * hash + Objects.hashCode(this.title);
        hash = 17 * hash + Objects.hashCode(this.val);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductFeature other = (ProductFeature) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.val, other.val)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.product, other.product);
    }

    @Override
    public String getTableName() {
        return "products";
    }
    
    @Override
    public String getColumnNamesForInsert() {
        return "title";
    }

    @Override
    public void bindInsertValues(PreparedStatement ps) {
        try {
            ps.setString(1, title);
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getUpdateValues() {
        return "title";
    }

    @Override
    public void bindUpdateValues(PreparedStatement ps) {
        try {
            ps.setString(1, title);
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getColumnNamesForSelect() {
        return "id,title";
    }

    @Override
    public String getWhereClauseForSelect() {
        List<String> wheres = new ArrayList<>();
        
        if(title != null) {
            wheres.add("title like ?");
        }
        
        String res = String.join(" or ", wheres);
        
        if(product != null) {
            if(res.length() > 0) {
                return "(" + res + ") and product_id = ?";
            } else {
                return "product_id = ?";
            }
        }
        
        return res;
    }

    @Override
    public void bindWhereClauseValuesForSelect(PreparedStatement ps) {
        try {
            int i = 1;
            
            if(title != null) {
                ps.setString(i, "%" + title + "%");
                i++;
            }

            if(product != null) {
                ps.setLong(i, product.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {
        Product product = new Product();
        try {
            product.setId(rs.getLong("id"));
            product.setTitle(rs.getString("title"));
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }
}
