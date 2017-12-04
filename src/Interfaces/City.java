/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;


import Resources.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Valentina
 */
public class City extends javax.swing.JInternalFrame {

    DefaultTableModel modelo;
    Connection cone;

    String idDep[ ] ;
    public City() {
        initComponents();
        cone = new Connection();
        actualizar();
        cargarCombodepart();
        
        btnUpdate.setVisible(false);
    }

    public void cargarId(){
 
        try{
            
            
            ResultSet rs = cone.consultDB("SELECT max(idCiudad) FROM ciudad");
            if(rs.next()){
             lblId.setText((rs.getInt("max(idCiudad)")+1)+"");
             
            }
            
        }catch (Exception e){
            System.out.println("Error" + e);
        }
        
    }
    
    public void actualizar(){
        
        try {
      String[] registros = new String[4];
      String[] titulos = {"IdCiudad","Nombre","Indicativo", "Departamento_id"};
      
      modelo = new DefaultTableModel(null, titulos);
      
       ResultSet rs = cone.consultDB("SELECT * FROM ciudad" );
       
            while(rs.next()){
                registros[0] = rs.getString("idCiudad");
                registros[1] = rs.getString("Nombre");
                registros[2] = rs.getString("Indicativo");
                registros[3] = rs.getString("Departamento_id");
                
                modelo.addRow(registros);
            } 
            jTable1.setModel(modelo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        cargarId();
    }
    
    public void cargar(String valor){
    
        try{
      String[] registros = new String[2];
      String[] titulos = {"IdCiudad","Nombre"};
      
      modelo = new DefaultTableModel(null, titulos);
      
       ResultSet rs = cone.consultDB("SELECT * FROM ciudad WHERE CONCAT (idCiudad,'',Nombre) LIKE ' %"+valor+"%'" );
       
            while(rs.next( )){
                registros[0] = rs.getString("idCiudad");
                registros[1] = rs.getString("Nombre");
     
                modelo.addRow(registros);
     
            }
            
            jTable1.setModel(modelo);
            
        }catch (Exception e){
            System.out.println("Error"+e);
        }
            cargarId();
        }

    
    public void cargarCombodepart() {
        try {
            ResultSet resul =cone.consultDB("SELECT COUNT(*) cuenta FROM departamento");
            if(resul.next()){
                idDep = new String[resul.getInt("cuenta")];
            }
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
        try {
            int i = 0;
            ResultSet rs=cone.consultDB("SELECT idDepartamento,Nombre FROM Departamento");
            while  (rs.next()) {
            idDep[i] = rs.getString("idDepartamento");
            jcDepart.addItem(rs.getString("Nombre"));
            i++;
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        eliminar = new javax.swing.JMenuItem();
        modificar = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtIndi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jcDepart = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminar);

        modificar.setText("Editar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(modificar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel1.setText("Id");

        jLabel2.setText("Nombre");

        jLabel3.setText("Indicativo");

        jLabel5.setText("Departamento");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jTable1);

        btnAdd.setText("Agregar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Actualizar");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(btnAdd)
                        .addGap(26, 26, 26)
                        .addComponent(btnUpdate))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jcDepart, 0, 187, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtIndi)
                                    .addComponent(txtName)
                                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lblId, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIndi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcDepart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        int pos = (int) jcDepart.getSelectedIndex();
        String pais = (String) jcDepart.getSelectedItem();
        System.out.println(pos);
        
        cone.modifyDB("INSERT INTO Ciudad VALUES (NULL, '" +txtName.getText()+ "', '" +txtIndi.getText()+ "', '" +idDep[pos]+ "')");
    }//GEN-LAST:event_btnAddActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        // TODO add your handling code here:
        int row = jTable1.getSelectedRow();  
        
        try {
            cone.modifyDB("DELETE FROM ciudad WHERE idCiudad="+jTable1.getValueAt(row, 0));
            actualizar();
            JOptionPane.showMessageDialog(rootPane, "el estudiantes a sido eliminado");
            
            
        } catch (Exception e) {
            System.out.println("Error"+ e);
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
        // TODO add your handling code here:
         
        btnUpdate.setVisible(true);
        int row = jTable1.getSelectedRow();
        
        try {
            ResultSet rs = cone.consultDB("SELECT * FROM ciudad WHERE idCiudad="+jTable1.getValueAt(row, 0));
            
            rs.next();
            lblId.setText(rs.getString("idCiudad"));
            txtName.setText(rs.getString("Nombre"));
            txtIndi.setText(rs.getString("Indicativo"));
            jcDepart.addItem(rs.getString("Pais_id"));
            jcDepart.setSelectedItem(rs.getString("Pais_id"));
            
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
       
    }//GEN-LAST:event_modificarActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        String nombre = txtName.getText();
        
        
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcDepart;
    private javax.swing.JLabel lblId;
    private javax.swing.JMenuItem modificar;
    private javax.swing.JTextField txtIndi;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
