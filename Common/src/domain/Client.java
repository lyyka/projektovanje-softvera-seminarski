package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements GenericEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private Date createdAt;
    private Date updatedAt;

    public Client() {
    }

    public Client(Long id, String firstName, String lastName, String email, String phone, String address, Date createdAt, Date updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date created_at) {
        this.createdAt = created_at;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updatedAt = updated_at;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "(" + email + ")"; 
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.firstName);
        hash = 97 * hash + Objects.hashCode(this.lastName);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.phone);
        hash = 97 * hash + Objects.hashCode(this.address);
        hash = 97 * hash + Objects.hashCode(this.createdAt);
        hash = 97 * hash + Objects.hashCode(this.updatedAt);
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        return Objects.equals(this.updatedAt, other.updatedAt);
    }


    @Override
    public String getTableName() {
        return "clients";
    }
    
    @Override
    public String getColumnNamesForInsert() {
        return "first_name,last_name,email,phone";
    }

    @Override
    public String getInsertValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("'").append(firstName).append("',")
            .append("'").append(lastName).append("',")
            .append("'").append(email).append("',")
            .append("'").append(phone).append("',");
        return sb.toString();
    }

    @Override
    public String getUpdateValues() {
        StringBuilder sb = new StringBuilder();
        sb.append("first_name = ").append("'").append(firstName).append("'").append(",")
            .append("last_name = ").append("'").append(lastName).append("'").append(",")
            .append("email = ").append("'").append(email).append("'").append(",")
            .append("phone = ").append("'").append(phone).append("'").append(",");
        return sb.toString();
    }
    
    @Override
    public String getColumnNamesForSelect() {
        return "id,first_name,last_name,email,phone,created_at,updated_at";
    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {
        Client client = new Client();
        try {
            client.setId(rs.getLong("id"));
            client.setFirstName(rs.getString("first_name"));
            client.setLastName(rs.getString("last_name"));
            client.setEmail(rs.getString("email"));
            client.setPhone(rs.getString("phone"));
            client.setCreatedAt(rs.getDate("created_at"));
            client.setUpdatedAt(rs.getDate("updated_at"));
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }

    @Override
    public String getWhereClauseForSelect() {
        List<String> wheres = new ArrayList<>();
        
        if(firstName != null) {
            wheres.add("first_name like '%" + firstName + "'%");
        }
        
        if(lastName != null) {
            wheres.add("last_name like '%" + lastName + "'%");
        }
        
        if(email != null) {
            wheres.add("email like '%" + email + "'%");
        }
        
        if(phone != null) {
            wheres.add("phone like '%" + phone + "'%");
        }
        
        if(address != null) {
            wheres.add("address like '%" + address + "'%");
        }
        
        return String.join(" or ", wheres);
    }
}
