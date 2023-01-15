package forms;

import domain.ProductFeature;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProductFeatureTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Naziv", "Vrednost"};
    private final List<ProductFeature> features;
    
    public ProductFeatureTableModel()
    {
        this.features = new ArrayList<>();
    }
    
    public ProductFeatureTableModel(List<ProductFeature> features) {
        this.features = features;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductFeature pf = features.get(rowIndex);
        switch(columnIndex){
            case 0: return pf.getTitle() != null ? pf.getTitle() : "/";
            case 1: return pf.getVal() != null ? pf.getVal() : "/";
            default:
                return "n/a";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        ProductFeature pf = features.get(rowIndex);
        switch(columnIndex){
            case 0:
                pf.setTitle((String) aValue);
                break;
            case 1: 
                pf.setVal((String) aValue);
                break;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        if (column>columnNames.length) return "n/a";
        return columnNames[column]; 
    }
    
    @Override
    public int getRowCount() {
        return this.features.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    public List<ProductFeature> getFeatures() {
        return features;
    }
    
    public void addFeature(ProductFeature pf)
    {
        this.features.add(pf);
        this.fireTableDataChanged();
    }
    
    public void removeFeatureAt(int i) {
        this.features.remove(i);
    }
}
