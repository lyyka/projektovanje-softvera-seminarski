package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Product implements GenericEntity {
    private Long id;
    private String title;
    private List<ProductFeature> productFeatures;
    
    public Product() {
        
    }

    public Product(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProductFeature> getProductFeatures() {
        return productFeatures;
    }

    public void setProductFeatures(List<ProductFeature> productFeatures) {
        this.productFeatures = productFeatures;
    }

    @Override
    public String toString() {
        return title; 
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
        hash = 23 * hash + Objects.hashCode(this.title);
        hash = 23 * hash + Objects.hashCode(this.productFeatures);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.productFeatures, other.productFeatures);
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
        if(this.getId() != null) {
            return "id = ?";
        }
        
        List<String> wheres = new ArrayList<>();
        
        if(title != null) {
            wheres.add("title like ?");
        }
        
        return String.join(" or ", wheres);
    }

    @Override
    public void bindWhereClauseValuesForSelect(PreparedStatement ps) {
        try {
            if(this.getId() != null) {
                ps.setLong(1, this.getId());
            } else {
                if(title != null) {
                    ps.setString(1, "%" + title + "%");
                }
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
