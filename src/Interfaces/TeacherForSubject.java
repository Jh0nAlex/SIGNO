package Interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Resources.Connection;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andre
 */
public class TeacherForSubject extends javax.swing.JInternalFrame {

     /**
 * Clase principal para realizar la conexiÃ³n con las tablas grupos,materia y docente
 * <pre>gestion de docentes por materia,por grupo ;</pre>
 * @author Grupo de base de datos
 * @version 1.0 22-11-2017
 */
    
     Connection con;
     DefaultTableModel modelo;
     
    public TeacherForSubject() {
        initComponents();
        
         con=new Connection();
   Consult();
   chargerSubject();
   chargerGroup();
   chargerTeacher();
   btnUpdate.setVisible(false);
    }

        
        public void chargerSubject (){
  /**
 * Clase utilizada para realizar la conexiÃ³n con la tabla materia de la base de datos utilizando
 * mysql como gestor de base de datos.
 * <pre>SELECT Nombre,id FROM materia;</pre>
 * @author Grupo de base de datos
 * @version 1.0 22-11-2017 
 */
        try {
            //seleccionar el nombre del grupo 
            ResultSet rs= con.consultDB("SELECT idMateria FROM materia");
            while(rs.next()){
                cboSubject.addItem(rs.getString("idMateria"));
                                            
            }   } catch (SQLException ex) {
            Logger.getLogger(TeacherForSubject.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
        
        
         public void chargerGroup (){
  /**
 * Clase utilizada para realizar la conexiÃ³n con la tabla grupo de la base de datos utilizando
 * mysql como gestor de base de datos.
 * <pre>SELECT id FROM grupo;</pre>
 * @author Grupo de base de datos
 * @version 1.0 22-11-2017 
 */
        try {
            //seleccionar el nombre del grupo 
            ResultSet rs= con.consultDB("SELECT idGrupo FROM Grupo");
            while(rs.next()){
                cboGroupId.addItem(rs.getString("idGrupo"));
                                            
            }   } catch (SQLException ex) {
            Logger.getLogger(TeacherForSubject.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
         
         
           public void chargerTeacher (){
  /**
 * Clase utilizada para realizar la conexiÃ³n con la tabla Docente de la base de datos utilizando
 * mysql como gestor de base de datos.
 * <pre>SELECT id FROM grupo;</pre>
 * @author Grupo de base de datos
 * @version 1.0 22-11-2017 
 */
        try {
            //seleccionar el nombre del grupo 
            ResultSet rs= con.consultDB("SELECT Codigo FROM docente");
            while(rs.next()){
                cboTeacherId.addItem(rs.getString("Codigo"));
                                            
            }   } catch (SQLException ex) {
            Logger.getLogger(TeacherForSubject.class.getName()).log(Level.SEVERE, null, ex);
            
            }
        }
           
           
           
            public void Consult(){
      
        try{
            String titulos[]={ "Docente_id","Grupo_id","Materia_id"};
            String fila []= new String [3];
            
            modelo=new DefaultTableModel(null,titulos);
            
            
         ResultSet rs =  con.consultDB("SELECT *  FROM docente_materia_grupo " );
          

           while (rs.next()){
               fila [0]= rs.getString("Docente_id");
               fila [1]= rs.getString("Grupo_id");
               fila [2]= rs.getString("Materia_id");
               
                
                      
               
                    
               
              modelo.addRow(fila);
       
                         
           }
           tbTeachers.setModel(modelo);
          // cboCurso.setModel(modelo);
          // btnActualizar.setVisible(true);
        }
        
        catch(Exception e){
            System.out.println("Error"+e);
            
           
        }      
           
        
    }
            
             void Search(String param){
               
        try{
              String titulos[]={ "Docente_id","Grupo_id","Materia_id"};
            String fila []= new String [3];
            
            modelo=new DefaultTableModel(null,titulos);
            
            
            //ResultSet rs =st.executeQuery("SELECT * FROM cursos where CONCAT (nomCurso,'',codEst) LIKE '%"+param+"%'" );
           //                                                                                ResultSet rs =conex.consultaBD("SELECT nombreCurso,codCurso FROM cursos where CONCAT (nombreCurso,'',codCurso,'') LIKE '%"+param+"%'" );
            //ResultSet rs =  conex.consultaBD("SELECT cur.codCurso,cur.nombreCurso,doce.codDocente,doce.nomDocente FROM cursos as cur LEFT JOIN docentes as doce ON doce.codCurso=cur.codCurso where (nombreCurso) LIKE '%"+param+"%'" );
          // ResultSet rs =  con.consultDB("SELECT e.codEst,e.nomEst,c.codCurso,c.nombreCurso  FROM estudiantes e INNER JOIN matriculas m ON e.codEst=m.codEst INNER JOIN cursos as c ON m.codCurso=c.codCurso where (e.codEst) LIKE '%"+param+"%'" );
             ResultSet rs =  con.consultDB("SELECT * FROM docente_materia_grupo WHERE (Docente_id) LIKE '%"+param+"%'" );
           while (rs.next()){
                 fila [0]= rs.getString("Docente_id");
               fila [1]= rs.getString("Grupo_id");
               fila [2]= rs.getString("Materia_id");
               
               
               
               
                             
               
              modelo.addRow(fila);
       
                         
           }
           tbTeachers.setModel(modelo);
        }
        
        catch(Exception e){
            System.out.println("Error"+e);
            
           
        } 
       }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jpmTeacher = new javax.swing.JPopupMenu();
        Eliminar = new javax.swing.JMenuItem();
        Modificar = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        cboSubject = new javax.swing.JComboBox<>();
        lblSubjectName = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboGroupId = new javax.swing.JComboBox<>();
        lblGroupName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cboTeacherId = new javax.swing.JComboBox<>();
        lblTeacherName = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbTeachers = new javax.swing.JTable();
        lblAssign = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();

        Eliminar.setText("Eliminar");
        Eliminar.setToolTipText("");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        jpmTeacher.add(Eliminar);

        Modificar.setText("Modificar");
        Modificar.setToolTipText("");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });
        jpmTeacher.add(Modificar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Teacher For Subject and Group");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Materia ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 70, 20));

        cboSubject.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSubjectItemStateChanged(evt);
            }
        });
        cboSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSubjectActionPerformed(evt);
            }
        });
        getContentPane().add(cboSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 80, -1));

        lblSubjectName.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        getContentPane().add(lblSubjectName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 150, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Grupo ID");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 80, -1));

        cboGroupId.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        cboGroupId.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboGroupIdItemStateChanged(evt);
            }
        });
        getContentPane().add(cboGroupId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 90, -1));

        lblGroupName.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        getContentPane().add(lblGroupName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 120, 20));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Docente ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 70, 20));

        cboTeacherId.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        cboTeacherId.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTeacherIdItemStateChanged(evt);
            }
        });
        getContentPane().add(cboTeacherId, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 90, 20));

        lblTeacherName.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        getContentPane().add(lblTeacherName, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 110, 20));

        tbTeachers.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        tbTeachers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbTeachers.setComponentPopupMenu(jpmTeacher);
        jScrollPane1.setViewportView(tbTeachers);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, 210));

        lblAssign.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        lblAssign.setText("ASIGNAR");
        lblAssign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lblAssignActionPerformed(evt);
            }
        });
        getContentPane().add(lblAssign, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 420, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Buscar");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 420, 50, 20));

        txtSearch.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 420, 120, -1));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        btnUpdate.setText("ACTUALIZAR");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSubjectActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSubjectActionPerformed

    private void cboSubjectItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSubjectItemStateChanged
          Connection conex2= new Connection();
        try{
          
            ResultSet rs2= conex2.consultDB("SELECT Nombre FROM Materia where idMateria="+cboSubject.getSelectedItem());
        if(rs2.next()){
            lblSubjectName.setText(rs2.getString("Nombre"));
        }
        }catch(Exception e){
            System.out.println("Error"+e);
        
            }
    }//GEN-LAST:event_cboSubjectItemStateChanged

    private void cboGroupIdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboGroupIdItemStateChanged
             Connection conex3= new Connection();
        try{
          
            ResultSet rs2= conex3.consultDB("SELECT Nombre FROM Grupo where idGrupo="+cboGroupId.getSelectedItem());
        if(rs2.next()){
            lblGroupName.setText(rs2.getString("Nombre"));
        }
        }catch(Exception e){
            System.out.println("Error"+e);
        
            }
    }//GEN-LAST:event_cboGroupIdItemStateChanged

    private void cboTeacherIdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTeacherIdItemStateChanged
         Connection conex4= new Connection();
        try{
          
            ResultSet rs2= conex4.consultDB("SELECT PrimerNombre from usuario u INNER JOIN rol r ON r.idRol=u.Rol_id INNER JOIN docente d ON r.idrol=d.Rol_Id where d.Codigo="+cboTeacherId.getSelectedItem());
        if(rs2.next()){
            lblTeacherName.setText(rs2.getString("PrimerNombre"));
        }
        }catch(Exception e){
            System.out.println("Error"+e);
        
            }
    }//GEN-LAST:event_cboTeacherIdItemStateChanged

    private void lblAssignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lblAssignActionPerformed
      String teacher=(String)cboTeacherId.getSelectedItem();
      String group=(String)cboGroupId.getSelectedItem(); 
      String subject=(String)cboSubject.getSelectedItem(); 
            
            
            con.modifyDB("INSERT INTO docente_materia_grupo VALUES('"+subject+"','"+group+"','"+teacher+"')");
            JOptionPane.showMessageDialog(null, "El registro ha sido ingresado");
           // txtNombre.setText("");
            
            Consult(); 
    }//GEN-LAST:event_lblAssignActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
         Search(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyReleased

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        int filaselec;
        String cod;
          
               
        // try{
             filaselec=tbTeachers.getSelectedRow();
             if (filaselec==-1){
                 JOptionPane.showMessageDialog(null, "No se ha seleccionado una fila");
             }else{
                 
                 DefaultTableModel modelo =(DefaultTableModel)tbTeachers.getModel();
                 cod=(String)modelo.getValueAt(filaselec, 0);
                 modelo.removeRow(filaselec);
                 
                  con.modifyDB("DELETE FROM docente_materia_grupo WHERE Docente_id='"+cod+"'");
             JOptionPane.showMessageDialog(null, "El registro ha sido eliminado");
                 
             }
              Consult();
    }//GEN-LAST:event_EliminarActionPerformed

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
           int filaselec;
          
            filaselec=tbTeachers.getSelectedRow();
            String group=(String)modelo.getValueAt(filaselec, 1);
            String teacher=(String)modelo.getValueAt(filaselec, 0);
            String subject=(String)modelo.getValueAt(filaselec, 2);
            
            btnUpdate.setVisible(true);
          
              cboTeacherId.setSelectedItem(teacher);
              cboSubject.setSelectedItem(subject);
              cboGroupId.setSelectedItem(group);
              

    }//GEN-LAST:event_ModificarActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JMenuItem Modificar;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboGroupId;
    private javax.swing.JComboBox<String> cboSubject;
    private javax.swing.JComboBox<String> cboTeacherId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu jpmTeacher;
    private javax.swing.JButton lblAssign;
    private javax.swing.JLabel lblGroupName;
    private javax.swing.JLabel lblSubjectName;
    private javax.swing.JLabel lblTeacherName;
    private javax.swing.JTable tbTeachers;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
