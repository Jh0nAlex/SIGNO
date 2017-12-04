/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Resources.Connection;
/**
 *
 * @author usuario
 */
public class Subjects extends javax.swing.JInternalFrame {

    Connection cone;
    DefaultTableModel modelo;
    String idT;

    public Subjects() {
        initComponents();
        cone = new Connection();
        btnUpdate.setEnabled(false);
        selectSubjects();
        id();
        tbSubjects("");

    }

    public void insert() {
        selectSubjects();
        String idS = txtId.getText();
        String name = txtName.getText();
        String idSu =(String) cboSubjects.getSelectedItem();

        cone.modifyDB("insert into materia values(" + idS + ",'" + name + "','1'," + idSu + ")");
        JOptionPane.showMessageDialog(null, "registro exitoso");

    }

    public void id() {
        try {

            String fila;

            //cuadro.setText("CODIGO\tNOMBRE\tGENERO\tEDAD\tCURSO\n");
            ResultSet rs = cone.consultDB("SELECT MAX(idMateria) FROM materia");
            if (rs.next()) {
                int num = rs.getInt("MAX(idMateria)") + 1;
                if (num == 0) {
                    txtId.setText(1 + "");
                } else {
                    txtId.setText(num + "");
                }

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    private void selectSubjects() {
        Connection cone2 = new Connection();

        try {
            ResultSet rs = cone2.consultDB("select * from asignatura");
            while (rs.next()) {
                
               int state;
                state = Integer.parseInt(rs.getString("Activo"));
                if (state == 1) {
                
                cboSubjects.addItem(rs.getString("idAsignatura"));
                }
            }

        } catch (Exception e) {
        }
    }

    public void tbSubjects(String value) {
        
        try {
            String titulos[] = {"codigo", "Materia", "Asignatura"};
            String fila[] = new String[3];

            modelo = new DefaultTableModel(null, titulos);
            Connection cone2 = new Connection();
            
            ResultSet rs = cone2.consultDB("select * from materia as mat "
                    + "INNER JOIN asignatura as asi on mat.Asignatura_id = asi.idAsignatura "
             + "WHERE CONCAT (mat.idMateria, mat.Nombre, asi.Nombre) LIKE '%" + value + "%'");

            while (rs.next()) {
                int state;
                state = Integer.parseInt(rs.getString("mat.Activo"));
                if (state == 1) {

                    fila[0] = rs.getString("mat.idMateria");
                    fila[1] = rs.getString("mat.Nombre");
                    fila[2] = rs.getString("asi.Nombre");

                    modelo.addRow(fila);
                    
                }
            }

            tbSubjects.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Subjects.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
//    select mat.idMateria, mat.Nombre, mat.Asignatura_id "
//                        + "from materia as mat "
//                        + "inner join asignatura as asi "
//                        + "on mat.Asignatura_id = asi.idAsignatura"
//                        + "where mat.idMateria = " + idT 

    public void selectUpdate() {
        int selectRow;

        selectRow = tbSubjects.getSelectedRow();
        if (selectRow == -1) {
            JOptionPane.showMessageDialog(null, "no ha seleccionado ninguna fila");
        } else {
            try {

                idT = (String) modelo.getValueAt(tbSubjects.getSelectedRow(), 0);
                ResultSet rs = cone.consultDB("select * from materia where idMateria = "+idT);

                if (rs.next()) {

                    txtId.setText(rs.getString("idMateria"));
                    txtName.setText(rs.getString("Nombre"));
                    cboSubjects.setSelectedItem(rs.getString("Asignatura_id"));

                } else {
                    JOptionPane.showMessageDialog(rootPane, "El codigo no existe");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

    public void updateTable() {
        String idS = txtId.getText();
        String name = txtName.getText();
        String idA = (String) cboSubjects.getSelectedItem();

        Integer.parseInt(idS);

        cone.modifyDB("UPDATE materia SET Nombre='"+name+"', Asignatura_id=" + idA
                + " WHERE idMateria=" + idS);
        JOptionPane.showMessageDialog(null, "Actualización Exitosa");

    }

    public void inactivate() {
        int selectRow;

        selectRow = tbSubjects.getSelectedRow();
        if (selectRow == -1) {
            JOptionPane.showMessageDialog(null, "no ha seleccionado ninguna fila");
        } else {

            DefaultTableModel modelo = (DefaultTableModel) tbSubjects.getModel();
            String idT = (String) modelo.getValueAt(selectRow, 0);
            modelo.removeRow(selectRow);
            Integer.parseInt(idT);

            cone.modifyDB("UPDATE materia SET Activo = '0' "
                    + "WHERE idMateria = '" + idT + "'");
            JOptionPane.showMessageDialog(null, "Inactivación Éxitosa");
        }
    }
    private void clear(){
        txtName.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popSubjects = new javax.swing.JPopupMenu();
        popUpdate = new javax.swing.JMenuItem();
        popInactivate = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboSubjects = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdA = new javax.swing.JLabel();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSubjects = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        popUpdate.setText("Modificar");
        popUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popUpdateActionPerformed(evt);
            }
        });
        popSubjects.add(popUpdate);

        popInactivate.setText("Inactivar");
        popInactivate.setActionCommand("");
        popInactivate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popInactivateActionPerformed(evt);
            }
        });
        popSubjects.add(popInactivate);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("MATERIAS");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(0, 0, 0), null, null));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Nombre:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Asignatura:");

        cboSubjects.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSubjectsItemStateChanged(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Id Materia:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Id Asignatura:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(28, 28, 28)
                        .addComponent(txtIdA, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cboSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        btnInsert.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnInsert.setText("REGISTRAR");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnUpdate.setText("ACTUALIZAR");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        tbSubjects.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbSubjects.setComponentPopupMenu(popSubjects);
        jScrollPane1.setViewportView(tbSubjects);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Buscar:");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInsert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        // TODO add your handling code here:
        insert();
        tbSubjects("");
        id();
        clear();
    }//GEN-LAST:event_btnInsertActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        // TODO add your handling code here:
        tbSubjects(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyReleased

    private void popUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popUpdateActionPerformed
        // TODO add your handling code here:
        selectUpdate();
        btnUpdate.setEnabled(true);
    }//GEN-LAST:event_popUpdateActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        updateTable();
        btnUpdate.setEnabled(false);
        tbSubjects("");
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void popInactivateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popInactivateActionPerformed
        // TODO add your handling code here:
        inactivate();
    }//GEN-LAST:event_popInactivateActionPerformed

    private void cboSubjectsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSubjectsItemStateChanged
        // TODO add your handling code here:
         Connection cone2 = new Connection();
 
       
        try {
            ResultSet rs = cone2.consultDB("select * from asignatura where idAsignatura =" + cboSubjects.getSelectedItem());
            if (rs.next()) {
                txtIdA.setText(rs.getString("Nombre"));
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboSubjectsItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboSubjects;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem popInactivate;
    private javax.swing.JPopupMenu popSubjects;
    private javax.swing.JMenuItem popUpdate;
    private javax.swing.JTable tbSubjects;
    private javax.swing.JLabel txtId;
    private javax.swing.JLabel txtIdA;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
