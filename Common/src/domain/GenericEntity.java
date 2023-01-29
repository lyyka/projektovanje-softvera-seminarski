package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface GenericEntity extends Serializable {
    String getTableName();
    
    String getColumnNamesForInsert();
    void bindInsertValues(PreparedStatement ps);
    
    String getUpdateValues();
    void bindUpdateValues(PreparedStatement ps);
    
    String getColumnNamesForSelect();
    String getWhereClauseForSelect();
    void bindWhereClauseValuesForSelect(PreparedStatement ps);
    
    GenericEntity newFromResultSet(ResultSet rs);
    
    void setId(Long id);
    Long getId();
}
