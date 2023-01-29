package domain;

import java.io.Serializable;
import java.sql.ResultSet;

public interface GenericEntity extends Serializable {
    String getTableName();
    
    String getColumnNamesForInsert();
    
    String getInsertValues();
    
    String getUpdateValues();
    
    String getColumnNamesForSelect();
    
    String getWhereClauseForSelect();
    
    GenericEntity newFromResultSet(ResultSet rs);
    
    void setId(Long id);
    
    Long getId();
}
