package domain;

import enums.DealStatus;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Deal implements GenericEntity {
    private Long id;
    private Client client;
    private Broker broker;
    private Product product;
    private Double dealValue;
    private DealStatus dealStatus;
    private String description;
    private Date statusUpdatedAt;
    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getDealValue() {
        return dealValue;
    }

    public void setDealValue(Double dealValue) {
        this.dealValue = dealValue;
    }

    public DealStatus getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(DealStatus dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStatusUpdatedAt() {
        return statusUpdatedAt;
    }

    public void setStatusUpdatedAt(Date statusUpdatedAt) {
        this.statusUpdatedAt = statusUpdatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.client);
        hash = 67 * hash + Objects.hashCode(this.broker);
        hash = 67 * hash + Objects.hashCode(this.product);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.dealValue) ^ (Double.doubleToLongBits(this.dealValue) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.dealStatus);
        hash = 67 * hash + Objects.hashCode(this.description);
        hash = 67 * hash + Objects.hashCode(this.statusUpdatedAt);
        hash = 67 * hash + Objects.hashCode(this.createdAt);
        hash = 67 * hash + Objects.hashCode(this.updatedAt);
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
        final Deal other = (Deal) obj;
        if (Double.doubleToLongBits(this.dealValue) != Double.doubleToLongBits(other.dealValue)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.client, other.client)) {
            return false;
        }
        if (!Objects.equals(this.broker, other.broker)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        if (this.dealStatus != other.dealStatus) {
            return false;
        }
        if (!Objects.equals(this.statusUpdatedAt, other.statusUpdatedAt)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        return Objects.equals(this.updatedAt, other.updatedAt);
    }

    @Override
    public String getTableName() {
        return "deals";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "client_id,broker_id,product_id,deal_value,deal_status,description";
    }

    @Override
    public void bindInsertValues(PreparedStatement ps) {
        try {
            ps.setLong(1, client.getId());
            ps.setLong(2, broker.getId());
            ps.setLong(3, product.getId());
            ps.setDouble(4, dealValue);
            ps.setString(5, dealStatus.toString());
            ps.setString(6, description);
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getUpdateValues() {
        return "client_id,broker_id,product_id,deal_value,deal_status,description";
    }

    @Override
    public void bindUpdateValues(PreparedStatement ps) {
        try {
            ps.setLong(1, client.getId());
            ps.setLong(2, broker.getId());
            ps.setLong(3, product.getId());
            ps.setDouble(4, dealValue);
            ps.setString(5, dealStatus.toString());
            ps.setString(6, description);
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getColumnNamesForSelect() {
        return "id,client_id,broker_id,product_id,deal_value,deal_status,description,created_at,updated_at";
    }

    @Override
    public String getWhereClauseForSelect() {
        if(this.getId() != null) {
            return "id = ?";
        }
        
        return "";
    }

    @Override
    public void bindWhereClauseValuesForSelect(PreparedStatement ps) {
        try {
            if(this.getId() != null) {
                ps.setLong(1, this.getId());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Deal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {
        Deal deal = new Deal();
        try {
            Client c = new Client();
            c.setId(rs.getLong("client_id"));
            Broker b = new Broker();
            b.setId(rs.getLong("broker_id"));
            Product p = new Product();
            p.setId(rs.getLong("product_id"));
            
            deal.setId(rs.getLong("id"));
            deal.setClient(c);
            deal.setBroker(b);
            deal.setProduct(p);
            deal.setDealValue(rs.getDouble("deal_value"));
            deal.setDealStatus(DealStatus.valueOf(rs.getString("deal_status")));
            deal.setDescription(rs.getString("description"));
            deal.setCreatedAt(rs.getDate("created_at"));
            deal.setUpdatedAt(rs.getDate("updated_at"));
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return deal;
    }
}
