package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Broker implements GenericEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private Date createdAt;
    private Date updatedAt;

    public Broker() {
    }

    public Broker(Long id, String first_name, String last_name, String email, String phone, String password, Date created_at, Date updated_at) {
        this.id = id;
        this.firstName = first_name;
        this.lastName = last_name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.createdAt = created_at;
        this.updatedAt = updated_at;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.firstName);
        hash = 37 * hash + Objects.hashCode(this.lastName);
        hash = 37 * hash + Objects.hashCode(this.email);
        hash = 37 * hash + Objects.hashCode(this.phone);
        hash = 37 * hash + Objects.hashCode(this.createdAt);
        hash = 37 * hash + Objects.hashCode(this.updatedAt);
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
        final Broker other = (Broker) obj;
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
        return "brokers";
    }
    
    @Override
    public String getColumnNamesForInsert() {
        return "first_name,last_name,email,phone,password";
    }

    @Override
    public void bindInsertValues(PreparedStatement ps) {
        try {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, password);
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getUpdateValues() {
        return "first_name,last_name,email,phone,password";
    }

    @Override
    public void bindUpdateValues(PreparedStatement ps) {
        try {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, password);
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getColumnNamesForSelect() {
        return "id,first_name,last_name,email,phone,password,created_at,updated_at";
    }

    @Override
    public String getWhereClauseForSelect() {
        if(this.getId() != null) {
            return "id = ?";
        }
        
        List<String> wheres = new ArrayList<>();
        
        if(firstName != null) {
            wheres.add("first_name like ?");
        }

        if(lastName != null) {
            wheres.add("last_name like ?");
        }

        if(email != null) {
            wheres.add("email like ?");
        }

        if(phone != null) {
            wheres.add("phone like ?");
        }
        
        return String.join(" or ", wheres);
    }

    @Override
    public void bindWhereClauseValuesForSelect(PreparedStatement ps) {
        try {
            if(this.getId() != null) {
                ps.setLong(1, this.getId());
            } else {
                int i = 1;
                
                if(firstName != null) {
                    ps.setString(i, "%" + firstName + "%");
                    i++;
                }

                if(lastName != null) {
                    ps.setString(i, "%" + lastName + "%");
                    i++;
                }

                if(email != null) {
                    ps.setString(i, "%" + email + "%");
                    i++;
                }

                if(phone != null) {
                    ps.setString(i, "%" + phone + "%");
                    i++;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public GenericEntity newFromResultSet(ResultSet rs) {
        Broker broker = new Broker();
        try {
            broker.setId(rs.getLong("id"));
            broker.setFirstName(rs.getString("first_name"));
            broker.setLastName(rs.getString("last_name"));
            broker.setEmail(rs.getString("email"));
            broker.setPhone(rs.getString("phone"));
            broker.setPassword(rs.getString("password"));
            broker.setCreatedAt(rs.getDate("created_at"));
            broker.setUpdatedAt(rs.getDate("updated_at"));
        } catch (SQLException ex) {
            Logger.getLogger(Broker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return broker;
    }
}
