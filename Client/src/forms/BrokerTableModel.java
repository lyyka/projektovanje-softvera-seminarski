/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import domain.Broker;
import domain.Client;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BrokerTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Ime", "Prezime", "Email", "Telefon"};
    private final List<Broker> brokers;
    
    public BrokerTableModel(List<Broker> clients) {
        this.brokers = clients;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Broker c = brokers.get(rowIndex);
        switch(columnIndex){
            case 0: return c.getFirstName();
            case 1: return c.getLastName();
            case 2: return c.getEmail();
            case 3: return c.getPhone();
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
        return this.brokers.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }
    
    public Broker getClientAt(int i) {
        return this.brokers.get(i);
    }
    
    public void removeClientAt(int i) {
        this.brokers.remove(i);
    }
    
    public int count() {
        return this.brokers.size();
    }
}
