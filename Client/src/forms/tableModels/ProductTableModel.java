/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.tableModels;

import domain.Product;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProductTableModel extends AbstractTableModel {
    private final String[] columnNames = {"ID", "Naziv"};
    private final List<Product> products;
    
    public ProductTableModel(List<Product> products) {
        this.products = products;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product p = products.get(rowIndex);
        switch(columnIndex){
            case 0: return p.getId();
            case 1: return p.getTitle();
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
        return this.products.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }
    
    public Product getProductAt(int i) {
        return this.products.get(i);
    }
    
    public void removeProductAt(int i) {
        this.products.remove(i);
    }
    
    public int count() {
        return this.products.size();
    }
    
    public int indexOf(Product searchFor)
    {
        for(int i = 0; i < this.count(); i++) {
            Product p = this.getProductAt(i);
            if(p.getId().equals(searchFor.getId())) {
                return i;
            }
        }
        
        return -1;
    }
}
