/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms;

import domain.Client;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kredium
 */
public class ClientTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Ime", "Prezime", "Email", "Telefon", "Adresa"};
    private final List<Client> clients;
    
    public ClientTableModel(List<Client> clients) {
        this.clients = clients;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client c = clients.get(rowIndex);
        switch(columnIndex){
            case 0: return c.getFirstName();
            case 1: return c.getLastName();
            case 2: return c.getEmail();
            case 3: return c.getPhone();
            case 4: return c.getAddress();
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
        return this.clients.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }
    
    public Client getClientAt(int i) {
        return this.clients.get(i);
    }
}
