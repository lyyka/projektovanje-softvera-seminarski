package domain;

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
        return "product_features";
    }
    
    @Override
    public String getColumnNamesForInsert() {
        return "title,val";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(title).append("',");
        sb.append("'").append(val).append("',");
        return sb.toString();
    }

    @Override
    public String getUpdateValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("title = ").append("'").append(title).append("'").append(",")
                .append("val = ").append("'").append(val).append("'").append(",");
        return sb.toString();
    }
    
    @Override
    public String getColumnNamesForSelect() {
        return "id,title,val";
    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {
        ProductFeature pf = new ProductFeature();
        try {
            pf.setId(rs.getLong("id"));
            pf.setTitle(rs.getString("title"));
            pf.setVal(rs.getString("val"));
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pf;
    }

    @Override
    public String getWhereClauseForSelect() {
        List<String> wheres = new ArrayList<>();
        
        if(title != null) {
            wheres.add("title like '%" + title + "'%");
        }
        
        String res = String.join(" or ", wheres);
        
        if(product != null) {
            if(res.length() > 0) {
                return "(" + res + ") and product_id = " + product.getId();
            } else {
                return "product_id = " + product.getId();
            }
        }
        
        return res;
    }
}
