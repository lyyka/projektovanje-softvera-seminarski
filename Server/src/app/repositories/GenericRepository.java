package app.repositories;

import app.database.DatabaseBroker;
import domain.GenericEntity;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GenericRepository<T extends GenericEntity> implements DbRepository<T> {

    @Override
    public void add(T param) throws Exception {
        try {
            Connection connection = DatabaseBroker.getInstance().getConnection();
            
            StringBuilder sb = new StringBuilder();
            
            sb.append("INSERT INTO ")
                    .append(param.getTableName())
                    .append(" (").append(param.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(param.getInsertValues())
                    .append(");");
            
            String query = sb.toString();
            
            Statement statement = connection.createStatement();
            
            statement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            
            ResultSet rsKey = statement.getGeneratedKeys();
            
            if (rsKey.next()) {
                Long id = rsKey.getLong(1);
                param.setId(id);
            }
            
            statement.close();
            rsKey.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public List<T> getAll(T param) throws Exception {
        try {
            StringBuilder sb = new StringBuilder();
            
            sb.append("select ")
                    .append(param.getColumnNamesForSelect())
                    .append(" from ")
                    .append(param.getTableName());
            
            String wheres = param.getWhereClauseForSelect();
            
            if(wheres.length() > 0) {
                sb.append(" where ")
                        .append(wheres);
            } 
            
            sb.append(";");
            
            String sql = sb.toString();
            
            List<T> ges = new ArrayList<>();
            
            Connection connection = DatabaseBroker.getInstance().getConnection();

            Statement statement = connection.createStatement();
            
            ResultSet rs = statement.executeQuery(sql);
            
            while (rs.next()) {
                ges.add((T) param.newFromResultSet(rs));
            }
            
            rs.close();
            
            statement.close();
            
            return ges;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public void edit(T param) throws Exception {
        try {
            Connection connection = DatabaseBroker.getInstance().getConnection();
            
            StringBuilder sb = new StringBuilder();
            
            sb.append("update ")
                    .append(param.getTableName())
                    .append(" set ")
                    .append(param.getUpdateValues())
                    .append(" where id = ")
                    .append(param.getId()).append(";");
            
            String query = sb.toString();
            
            Statement statement = connection.createStatement();
            
            statement.executeUpdate(query);
            
            statement.close();
        } catch (SQLException ex) {
            throw ex;
        }
    }

    @Override
    public void delete(T param) throws Exception {
        try {
            String sql = "delete from " + param.getTableName() + " where id = " + param.getId() + ";";
            Connection connection = DatabaseBroker.getInstance().getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Override
    public List<T> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
