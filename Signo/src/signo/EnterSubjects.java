/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Grupo diseño
 * @version 1.0
  */
public class EnterSubjects extends javax.swing.JInternalFrame {

    /**
     * @author Grupo diseño
     * @version 1.0
     * Crea una nueva ventana para el ingreso de asignaturas.
     */
    Connection con;
    DefaultTableModel tableModel;
    /**
     * @author Grupo diseño
     * @version 1.0
     * Método constructor de la clase EnterSubjects, instanciando la clase
     * de la conexión a la base de datos, escribiendo el método de cargar la tabla
     * y deshabilitando el botón de actualizar
     */
    public EnterSubjects() {
        initComponents();
        con = new Connection();
        loadTable("");
        btnUpdate.setEnabled(false);
    }
    /**
     * @author Grupo diseño
     * @version 1.0
     * @param value recibe un parámetro de tipo cadena donde este 
     * hará el filtro de busqueda en la tabla
     * Este método cargará la tabla con los datos de la asignatura (id y nombre)
     * 
    */
    public void loadTable(String value) {

        //load : Cargar
        //Table : tabla
        String query = "SELECT idAsignatura, Nombre FROM asignatura "
                + "WHERE Activo = 1 AND CONCAT(idAsignatura, ' ', Nombre) LIKE '%" + value + "%'";
        String[] title = {"Id", "Nombre"};
        String[] registry = new String[2];
        ResultSet rs = con.consultDB(query);
        tableModel = new DefaultTableModel(null, title);

        try {
            while (rs.next()) {
                registry[0] = rs.getString("idAsignatura");
                registry[1] = rs.getString("Nombre");

                tableModel.addRow(registry);
            }

            tbAsignatura.setModel(tableModel);
        } catch (SQLException ex) {
            Logger.getLogger(EnterSubjects.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @author Grupo diseño
     * @version 1.0
     * Este método hará la inserción de los datos a la base de datos
     * en la tabla asignatura.
     */
    public void insertSubjects() {
        /**
         * Deshabilitar btnUpdate
         */
        btnUpdate.setEnabled(false);

        /**
         * Selecciona el id de asignatura
         */
        String query = "SELECT MAX(idAsignatura + 1) as id FROM asignatura";
        ResultSet rs = con.consultDB(query);
        String id = "";
        try {
            while (rs.next()) {
                id = rs.getString("id");
                if (id == null) {
                    id = "1";
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnterSubjects.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**
         * Inserta asignatura
         */
        int idAsig = Integer.parseInt(id);
        String sql = "INSERT INTO asignatura VALUES("
                + "" + idAsig + ",'" + txtName.getText() + "','" + txtDescription.getText() + "'," + 1
                + ")";

        con.modifyDB(sql);
        txtName.setText("");
        txtDescription.setText("");
        loadTable("");
    }
    /**
     * @author Grupo diseño
     * @version 1.0
     * Este método pone los datos en el frame para que el usuario modifique los valores.
     */
    public void modifySubjects() {

        /**
         *
         */
        btnRegister.setEnabled(false);
        btnUpdate.setEnabled(true);

        int row = tbAsignatura.getSelectedRow();
        int id = Integer.parseInt((String) tbAsignatura.getValueAt(row, 0));

        String query = "SELECT Nombre, descripcion FROM asignatura WHERE idAsignatura = " + id;
        ResultSet rs = con.consultDB(query);

        try {
            while (rs.next()) {
                txtName.setText(rs.getString("Nombre"));
                txtDescription.setText(rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnterSubjects.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * @author Grupo diseño
     * @version 1.0
     * Este método Actualiza los datos en la base de datos que el usuario modificó .
     */
    public void updateSubjects(){
        int row = tbAsignatura.getSelectedRow();
        int id = Integer.parseInt((String) tbAsignatura.getValueAt(row, 0));

        String sql = "UPDATE asignatura SET Nombre = '" + txtName.getText() + "', "
                + "descripcion = '" + txtDescription.getText() + "' "
                + "WHERE idAsignatura = " + id;

        con.modifyDB(sql);

        txtName.setText("");
        txtDescription.setText("");
        btnRegister.setEnabled(true);
        btnUpdate.setEnabled(false);
        loadTable("");
    }
    
    /**
     * @author Grupo diseño
     * @version 1.0
     * Este método inactiva la asignatura pero NO la borra de la base de datos.
     */
    public void deleteSubject() {
        int row = tbAsignatura.getSelectedRow();
        String id = (String) tbAsignatura.getValueAt(row, 0);
        String sql = "UPDATE asignatura SET Activo = 0 WHERE idAsignatura = " + id;

        con.modifyDB(sql);
        loadTable("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popMenu = new javax.swing.JPopupMenu();
        itmModify = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itmDelete = new javax.swing.JMenuItem();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        btnRegister = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbAsignatura = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        itmModify.setText("Modificar");
        itmModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmModifyActionPerformed(evt);
            }
        });
        popMenu.add(itmModify);
        popMenu.add(jSeparator1);

        itmDelete.setText("Eliminar");
        itmDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmDeleteActionPerformed(evt);
            }
        });
        popMenu.add(itmDelete);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("INGRESO DE ASIGNATURAS");

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Descripción");

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane2.setViewportView(txtDescription);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        btnRegister.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnRegister.setText("REGISTRAR");
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnUpdate.setText("ACTUALIZAR");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        tbAsignatura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Nombre"
            }
        ));
        tbAsignatura.setComponentPopupMenu(popMenu);
        tbAsignatura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbAsignaturaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbAsignatura);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Buscar");

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnRegister)
                        .addGap(61, 61, 61)
                        .addComponent(btnUpdate))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(74, 74, 74))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRegister)
                    .addComponent(btnUpdate))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        updateSubjects();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        insertSubjects();
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void tbAsignaturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbAsignaturaMouseClicked
        if (evt.getButton() == 3) {
            popMenu.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tbAsignaturaMouseClicked

    private void itmModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmModifyActionPerformed
        modifySubjects();
    }//GEN-LAST:event_itmModifyActionPerformed

    private void itmDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmDeleteActionPerformed
        deleteSubject();
    }//GEN-LAST:event_itmDeleteActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        loadTable(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JMenuItem itmDelete;
    private javax.swing.JMenuItem itmModify;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu popMenu;
    private javax.swing.JTable tbAsignatura;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
