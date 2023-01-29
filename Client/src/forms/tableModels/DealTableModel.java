package forms.tableModels;

import domain.Deal;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DealTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Klijent", "Prodavac", "Proizvod"};
    private final List<Deal> deals;
    
    public DealTableModel(List<Deal> deals) {
        this.deals = deals;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Deal p = deals.get(rowIndex);
        switch(columnIndex){
            case 0: return p.getId();
            case 1: return p.getClient();
            case 2: return p.getBroker();
            case 3: return p.getProduct();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column>columnNames.length) return "n/a";
        return columnNames[column]; 
    }
    
    @Override
    public int getRowCount() {
        return this.deals.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }
    
    public Deal getDealAt(int i) {
        return this.deals.get(i);
    }
    
    public int count() {
        return this.deals.size();
    }
}
