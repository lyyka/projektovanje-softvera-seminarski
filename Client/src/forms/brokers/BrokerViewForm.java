/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forms.brokers;

import forms.tableModels.BrokerTableModel;
import controllers.Communication;
import domain.Broker;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class BrokerViewForm extends javax.swing.JDialog {

    private BrokerTableModel btm;
    private java.awt.Frame parent;
    
    /**
     * Creates new form ClientViewForm
     */
    public BrokerViewForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.parent = parent;
        initComponents();
        setLocationRelativeTo(null);
        
        initTable();
    }
    
    private void updateInterface()
    {
        boolean isEmpty = this.btm.count() == 0;
        this.deleteBtn.setEnabled(!isEmpty);
        this.editBtn.setEnabled(!isEmpty);
    }
    
    private void initTable() {
        try {
            this.btm = new BrokerTableModel(
                    Communication.getInstance().getAllBrokers()
            );
        
            this.jTable1.setModel(this.btm);
            
            this.updateInterface();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita sve prodavce");
            Logger.getLogger(BrokerViewForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        editBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        editBtn.setText("Izmeni");
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });

        deleteBtn.setText("Obrisi");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        jButton3.setText("Osvezi pregled");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setText("SVI PRODAVCI");

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("Pretraga:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(searchField)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(editBtn)
                        .addGap(18, 18, 18)
                        .addComponent(deleteBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        int i = this.jTable1.getSelectedRow();
        
        if(i >= 0) {
            Broker b = this.btm.getBrokerAt(i);
            
            try {
                b = Communication.getInstance().loadBroker(b);
                
                JOptionPane.showMessageDialog(this, "Sistem je uspesno ucitao prodavca");
            
                BrokerCreateForm bcf = new BrokerCreateForm(this.parent, false);
                bcf.setBroker(b);
                bcf.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita prodavca");
                Logger.getLogger(BrokerViewForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_editBtnActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.initTable();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        int i = this.jTable1.getSelectedRow();
        
        if(i >= 0) {
            Broker broker = this.btm.getBrokerAt(i);
            
            try {
                broker = Communication.getInstance().loadBroker(broker);
                
                JOptionPane.showMessageDialog(this, "Sistem je uspesno ucitao prodavca");
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita prodavca");
                Logger.getLogger(BrokerViewForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                Communication.getInstance().deleteBroker(broker);
                
                JOptionPane.showMessageDialog(this, "Sistem je uspesno izbrisao prodavca");
                
                this.btm.removeClientAt(i);
                this.jTable1.setModel(this.btm);
                this.updateInterface();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Sistem ne moze da izbrise prodavca");
                Logger.getLogger(BrokerViewForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        Broker param = new Broker();
        param.setFirstName(searchField.getText());
        param.setLastName(searchField.getText());
        param.setEmail(searchField.getText());
        param.setPhone(searchField.getText());
        
        try {
            this.btm = new BrokerTableModel(
                    Communication.getInstance().getAllBrokers(param)
            );
        
            this.jTable1.setModel(this.btm);
            
            this.updateInterface();
            
            if(this.btm.count() == 0) {
                JOptionPane.showMessageDialog(this, "Sistem nije pronasao ni jednog prodavca po zadatoj vrednosti");
            } else {
                JOptionPane.showMessageDialog(this, "Sistem je pronasao prodavce po zadatoj vrednosti");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem nije pronasao ni jednog prodavca po zadatoj vrednosti");
            Logger.getLogger(BrokerViewForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField searchField;
    // End of variables declaration//GEN-END:variables
}
