/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Resources.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Valentina
 */
public class Admin extends javax.swing.JInternalFrame {

    Connection cone;
    String idRol[];
    String idCiudad[];
    String IdExpedicion[];
    String IdNacimiento[];
    String IdGroup[];
    ResultSet rSearch;
    
    public Admin() {
        initComponents();
        cone = new Connection();
        cargarComboPais();
        cargarComboPais2();
        cargarComboExpedicion();
        cargarComboRol();
        cargarComboGroup ();
//        cargarComboDepartamento();
//        cargarComboDepartamento2();
//        cargarComboCiudad();
//        cargarComboCiudad2();
    }

    public void cargarComboPais() {
        jcCountry.removeAllItems();
        
        try {
            ResultSet rs=cone.consultDB("SELECT Nombre FROM pais");
            while (rs.next()) {
            jcCountry.addItem(rs.getString("Nombre"));
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
    
    }
    
    public void cargarComboPais2() {
        Connection cone2 = new Connection();
        jcCountry2.removeAllItems();
        try {
            
            ResultSet rs=cone2.consultDB("SELECT Nombre FROM pais");
            while (rs.next()) {
            jcCountry2.addItem(rs.getString("Nombre"));
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
    
    }
    
     public void cargarComboDepartamento() {
         
        Connection cone3 = new Connection();
        jcDepartment.removeAllItems();
         try {
              
            ResultSet rs=cone3.consultDB("SELECT departamento.Nombre "
                    + "FROM departamento, pais WHERE departamento.Pais_Id=pais.idPais "
                    + "AND pais.Nombre='" + jcCountry.getSelectedItem()+"'");
             System.out.println(rs);
            while (rs.next()) {
            jcDepartment.addItem(rs.getString("departamento.Nombre"));
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
    
    }
    
    public void cargarComboDepartamento2() {
        Connection cone4 = new Connection();
        jcDepartment2.removeAllItems();
        try {
            ResultSet rs=cone4.consultDB("SELECT departamento.Nombre "
                    + "FROM departamento, pais WHERE departamento.Pais_Id=pais.idPais "
                    + "AND pais.Nombre='" + jcCountry2.getSelectedItem()+"'");
            while (rs.next()) {
            jcDepartment2.addItem(rs.getString("departamento.Nombre"));
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
    
    }
    
    public void cargarComboCiudad() {
        Connection cone5 = new Connection();
        jcCity.removeAllItems();
        
      try {
            
            ResultSet resul = cone5.consultDB("SELECT COUNT(*) cuenta FROM Ciudad C "
                    + "INNER JOIN Departamento D ON C.Departamento_id = D.idDepartamento"
                    + " WHERE D.Nombre = '" +jcDepartment.getSelectedItem()+ "'");
            if(resul.next()){
                idCiudad = new String[resul.getInt("cuenta")];
            }
         } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
        
        try {
             int i = 0;
            ResultSet rs=cone5.consultDB("SELECT idCiudad,ciudad.Nombre "
                    + "FROM ciudad, departamento WHERE ciudad.Departamento_id=departamento.idDepartamento"
                    + " AND departamento.Nombre='" + jcDepartment.getSelectedItem()+"'");
            while (rs.next()) {
            idCiudad[i] = rs.getString("idCiudad");
            System.out.println(idCiudad[i]);    
            jcCity.addItem(rs.getString("ciudad.Nombre"));
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
    
    }
    
    public void cargarComboCiudad2() {
        Connection cone6 = new Connection();
        jcBirthPlace.removeAllItems();
        try {
            ResultSet resul = cone6.consultDB("SELECT COUNT(*) cuenta FROM Ciudad C "
                    + "INNER JOIN Departamento D ON C.Departamento_id = D.idDepartamento"
                    + " WHERE D.Nombre = '" +jcDepartment2.getSelectedItem()+ "'");
            if(resul.next()){
                IdNacimiento = new String[resul.getInt("cuenta")];
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex);
        }
        
        int i = 0;
        try {
            ResultSet rs=cone6.consultDB("SELECT ciudad.Nombre, ciudad.idCiudad "
                    + "FROM ciudad, departamento WHERE ciudad.Departamento_id=departamento.idDepartamento"
                    + " AND departamento.Nombre='" + jcDepartment2.getSelectedItem()+"'");
            while (rs.next()) {
            jcBirthPlace.addItem(rs.getString("ciudad.Nombre"));
            IdNacimiento[i] =rs.getString("ciudad.idCiudad");
                    i++;
            }
        } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }
    
    }
    
    public void cargarComboExpedicion() {
        Connection cone7 = new Connection();
        jcExpeditionPlace.removeAllItems();
        try {
            ResultSet resul = cone7.consultDB("SELECT COUNT(*) cuenta FROM Ciudad C "
                    + "INNER JOIN Departamento D ON C.Departamento_id = D.idDepartamento"
                    + " WHERE D.Nombre = '" +jcDepartment2.getSelectedItem()+ "'");
            if(resul.next()){
                IdExpedicion = new String[resul.getInt("cuenta")];
            }
        } catch (Exception e) {
        }
        int i = 0;
        try {
            ResultSet rs=cone7.consultDB("Select C.Nombre, C.idCiudad FROM ciudad C "
                    + "INNER JOIN Departamento D ON C.Departamento_id = "
                    + "D.idDepartamento WHERE D.Nombre ='" + jcDepartment2.getSelectedItem()+"'");
            while (rs.next()){
                jcExpeditionPlace.addItem(rs.getString("C.Nombre"));
                IdExpedicion[i] = rs.getString("C.idCiudad");
            i++;
            }
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }
    
    public void cargarComboRol() {
        Connection cone8 = new Connection();
        try {
            ResultSet resul = cone8.consultDB("SELECT COUNT(*) cuenta FROM ROL");
            if(resul.next()){
                idRol = new String[resul.getInt("cuenta")];
            }
         } catch (SQLException ex) {
            System.out.println("Error" + ex);
        }    
            try {
                int i = 0;
            ResultSet rs=cone8.consultDB("SELECT idRol, Nombre FROM rol");            
            while (rs.next()) {
            idRol[i] = rs.getString("idRol");
            System.out.println(idRol[i]);
            jcRol.addItem(rs.getString("Nombre"));
            i++;
            }
            
            } catch (Exception e) {
                System.out.println("Error"+e);
            }
            
    }
    
    public void cargarComboGroup () {
        Connection cone9 = new Connection();
        try {
            ResultSet rs = cone9.consultDB("SELECT COUNT(*) cuenta FROM Grupo");
            while (rs.next()) {
            IdGroup = new String[rs.getInt("cuenta")];
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex);
        }
        try {
            int i = 0;
            ResultSet resul=cone9.consultDB("SELECT idGrupo, Nombre FROM Grupo");
            while (resul.next()) {
                IdGroup[i] = resul.getString("idGrupo");
                System.out.println(IdGroup[i]);
                jcGroup.addItem(resul.getString("Nombre"));
                i++;
            }
        } catch (Exception ex) {
            System.out.println("Error" + ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtSecondName = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        txtSecondL = new javax.swing.JTextField();
        jsAge = new javax.swing.JSpinner();
        jrMas = new javax.swing.JRadioButton();
        jrFem = new javax.swing.JRadioButton();
        txtPhone = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtNuip = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jcCountry2 = new javax.swing.JComboBox<>();
        jcDepartment2 = new javax.swing.JComboBox<>();
        jcBirthPlace = new javax.swing.JComboBox<>();
        txtHeight = new javax.swing.JTextField();
        txtRH = new javax.swing.JTextField();
        txtExpeditionDate = new javax.swing.JTextField();
        jcExpeditionPlace = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jbRegister = new javax.swing.JButton();
        jcCountry = new javax.swing.JComboBox<>();
        jbConsult = new javax.swing.JButton();
        jcDepartment = new javax.swing.JComboBox<>();
        jbUpdate = new javax.swing.JButton();
        jcCity = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jcRol = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jpPassword = new javax.swing.JPasswordField();
        jLabel25 = new javax.swing.JLabel();
        jcGroup = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        txtGuardian = new javax.swing.JTextField();
        lblImg = new javax.swing.JLabel();
        lblAcu = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel12.setText("Ciudad");

        jrMas.setText("Masculino");

        jrFem.setText("Femenino");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel21.setText("Lugar de expedicion");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Primer Nombre");

        jcCountry2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcCountry2ItemStateChanged(evt);
            }
        });

        jcDepartment2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcDepartment2ItemStateChanged(evt);
            }
        });

        jcBirthPlace.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcBirthPlaceItemStateChanged(evt);
            }
        });

        jcExpeditionPlace.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcExpeditionPlaceItemStateChanged(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("Segundo Nombre");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Primer Apellido");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Segundo Apellido");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel5.setText("Edad");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("Genero");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Telefono");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel8.setText("Direccion ");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("E-mail");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setText("Nuip");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Pais");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel14.setText("Fecha de nacimiento");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("Departamento");

        jbRegister.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jbRegister.setText("Registrar");
        jbRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbRegisterActionPerformed(evt);
            }
        });

        jcCountry.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcCountryItemStateChanged(evt);
            }
        });

        jbConsult.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jbConsult.setText("Consultar");

        jcDepartment.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcDepartmentItemStateChanged(evt);
            }
        });

        jbUpdate.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jbUpdate.setText("Actualizar");
        jbUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbUpdateActionPerformed(evt);
            }
        });

        jcCity.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcCityItemStateChanged(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel15.setText("Pais");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel16.setText("Departamento");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel17.setText("Lugar de nacimiento");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel18.setText("Estatura");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel19.setText("RH");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel20.setText("Fecha de expedicion");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel22.setText("Rol");

        jcRol.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcRolItemStateChanged(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel23.setText("Usuario");

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel24.setText("Contraseña");

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel25.setText("Grupo");

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel26.setText("Acudiente");

        lblImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestionusuarios/50744-200.png"))); // NOI18N
        lblImg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblImgMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jrFem)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jsAge, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel6))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jrMas))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(29, 29, 29)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtFirstName)
                                        .addComponent(txtSecondName)
                                        .addComponent(txtSurname)
                                        .addComponent(txtSecondL, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addGap(92, 92, 92))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(105, 105, 105)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(jbRegister))
                                .addGap(43, 43, 43)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPhone)
                            .addComponent(txtAddress)
                            .addComponent(txtEmail)
                            .addComponent(jcCountry, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcCity, javax.swing.GroupLayout.Alignment.TRAILING, 0, 195, Short.MAX_VALUE)
                            .addComponent(jcDepartment, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jbConsult)
                                .addGap(18, 18, 18)
                                .addComponent(jbUpdate)))))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(112, 112, 112))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel14)
                                    .addGap(18, 18, 18)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16))
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel19))
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNuip)
                            .addComponent(txtDate)
                            .addComponent(jcCountry2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcDepartment2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jcBirthPlace, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHeight)
                            .addComponent(txtRH)
                            .addComponent(txtExpeditionDate)
                            .addComponent(jcExpeditionPlace, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel21)
                                .addComponent(jLabel22)
                                .addComponent(jLabel23)
                                .addComponent(jLabel24)
                                .addComponent(jLabel25)
                                .addComponent(jLabel26)))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jpPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtUser)
                            .addComponent(jcGroup, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcRol, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtGuardian, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblImg)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblAcu, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtSecondName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtSecondL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jsAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jrMas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrFem)
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jcRol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(jcCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel22))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtNuip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addComponent(jLabel15))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(jcCountry2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jcDepartment2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jcBirthPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtRH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtExpeditionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(jcExpeditionPlace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jcCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel25)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jpPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jcGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jcDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 28, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jbConsult)
                            .addComponent(jbRegister)
                            .addComponent(jbUpdate)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtGuardian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblImg))
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAcu, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbRegisterActionPerformed
        int pos = (int) jcRol.getSelectedIndex();
        String rol = (String) jcRol.getSelectedItem();
        System.out.println(pos);
        int posc = (int) jcCity.getSelectedIndex();
        System.out.println(posc);
        
//        if(rol.equals("Administrador")){
//            
//            cone.modifyDB("INSERT INTO Administrador VALUES ("+txtNuip.getText()+","+idRol[pos]+",1)");
//            
//        }else if(rol.equals("Acudiente")){
//            
//            cone.modifyDB("INSERT INTO Acudiente VALUES ("+txtNuip.getText()+","+idRol[pos]+",1)");
//            
//        }else if(rol.equals("Docente")){
//            
//            cone.modifyDB("INSERT INTO Docente VALUES ("+txtNuip.getText()+","+idRol[pos]+"1)");
//            
//        }else if(rol.equals("Estudiante")){
//            
//            cone.modifyDB("INSERT INTO Estudiante VALUES ("+txtNuip.getText()+","+idRol[pos]+"1)");
//       }
        
         try {
            int Edad = (int)jsAge.getValue();
            String Genero = "";
            if (jrMas.isSelected()) {
                Genero = "M";
            }
            if (jrFem.isSelected()) {
                Genero = "F";
            }
            int PosNaci = jcBirthPlace.getSelectedIndex();
            int PosExp = jcExpeditionPlace.getSelectedIndex();
        cone.modifyDB("INSERT INTO usuario VALUES ( "+txtNuip.getText()+" ,' "+txtSurname.getText()+" ',' "+txtSecondL.getText()+
                " ',' "+txtFirstName.getText()+" ',' "+txtSecondName.getText()+"', '"+txtPhone.getText()+"' ,' "+txtAddress.getText()+"',"
                +Edad+",' "+txtEmail.getText()+" ','"+Genero+"',' "+txtUser.getText()+" ',' "+jpPassword.getText()+" ',"+idCiudad[posc]+","+idRol[pos]+"); ");
        
         cone.modifyDB("INSERT INTO Nuip VALUES ("+txtNuip.getText()+",'"+txtDate.getText()+"',"+IdNacimiento[PosNaci]
             +",'"+txtHeight.getText()+"','"+txtRH.getText()+"','"+txtExpeditionDate.getText()+"',"+IdExpedicion[PosExp]+"); ");
        
        } catch (Exception ex) {
            System.out.println("Error" + ex);
        }
    }//GEN-LAST:event_jbRegisterActionPerformed

    private void jbUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbUpdateActionPerformed
        
        try {
            int Edad = jsAge.getComponentCount();
            String Genero = "";
            if (jrMas.isSelected()) {
                Genero = "Masculino";
            }
            if (jrFem.isSelected()) {
                Genero = "Femenino";
            }
            
            cone.modifyDB("UPDATE ");
            
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }//GEN-LAST:event_jbUpdateActionPerformed

    private void jcCountryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcCountryItemStateChanged
        
      cargarComboDepartamento();
    }//GEN-LAST:event_jcCountryItemStateChanged

    private void jcDepartmentItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcDepartmentItemStateChanged
        
              cargarComboCiudad();
    }//GEN-LAST:event_jcDepartmentItemStateChanged

    private void jcCountry2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcCountry2ItemStateChanged
        
     cargarComboDepartamento2();
    }//GEN-LAST:event_jcCountry2ItemStateChanged

    private void jcDepartment2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcDepartment2ItemStateChanged
        
        cargarComboCiudad2();
    }//GEN-LAST:event_jcDepartment2ItemStateChanged

    private void jcCityItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcCityItemStateChanged
        // TODO add your handling code here:
   
    }//GEN-LAST:event_jcCityItemStateChanged

    private void jcBirthPlaceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcBirthPlaceItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jcBirthPlaceItemStateChanged

    private void jcExpeditionPlaceItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcExpeditionPlaceItemStateChanged
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jcExpeditionPlaceItemStateChanged

    private void jcRolItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcRolItemStateChanged
       String rolEs = (String) jcRol.getSelectedItem();
       int posEst = (int) jcRol.getSelectedIndex();
       if(rolEs.equals("Estudiante")){
           jcGroup.setVisible(true);
           txtGuardian.setVisible(true);
           jLabel25.setVisible(true);
           jLabel26.setVisible(true);
           lblImg.setVisible(true);
           lblAcu.setVisible(true);
           
       }else if(rolEs.equals("Administrador")){
           jcGroup.setVisible(false);
           txtGuardian.setVisible(false);
           jLabel25.setVisible(false);
           jLabel26.setVisible(false);
           lblImg.setVisible(false);
           lblAcu.setVisible(false);
           
       }else if(rolEs.equals("Acudiente")){
           jcGroup.setVisible(false);
           txtGuardian.setVisible(false);
           jLabel25.setVisible(false);
           jLabel26.setVisible(false);
           lblImg.setVisible(false);
           lblAcu.setVisible(false);
       
       }
       else if(rolEs.equals("Docente")){
           jcGroup.setVisible(false);
           txtGuardian.setVisible(false); 
           jLabel25.setVisible(false);
           jLabel26.setVisible(false);
           lblImg.setVisible(false);
           lblAcu.setVisible(false);
       }
    }//GEN-LAST:event_jcRolItemStateChanged

    private void lblImgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImgMousePressed
        // TODO add your handling code here:
         String Acu = txtGuardian.getText();
            rSearch = cone.consultDB("SELECT CONCAT (PrimerNombre, '' ,PrimerApellido) Nombre FROM usuario INNER JOIN Acudiente A ON usuario.NUIP = A.Codigo WHERE A.Codigo = "+Acu);
            try {
                while (rSearch.next()) {
                    Acu = rSearch.getString("Nombre");
                }
        } catch (Exception e) {
                System.out.println("Error" + e);
        }
    }//GEN-LAST:event_lblImgMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton jbConsult;
    private javax.swing.JButton jbRegister;
    private javax.swing.JButton jbUpdate;
    private javax.swing.JComboBox<String> jcBirthPlace;
    private javax.swing.JComboBox<String> jcCity;
    private javax.swing.JComboBox<String> jcCountry;
    private javax.swing.JComboBox<String> jcCountry2;
    private javax.swing.JComboBox<String> jcDepartment;
    private javax.swing.JComboBox<String> jcDepartment2;
    private javax.swing.JComboBox<String> jcExpeditionPlace;
    private javax.swing.JComboBox<String> jcGroup;
    private javax.swing.JComboBox<String> jcRol;
    private javax.swing.JPasswordField jpPassword;
    private javax.swing.JRadioButton jrFem;
    private javax.swing.JRadioButton jrMas;
    private javax.swing.JSpinner jsAge;
    private javax.swing.JLabel lblAcu;
    private javax.swing.JLabel lblImg;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtExpeditionDate;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGuardian;
    private javax.swing.JTextField txtHeight;
    private javax.swing.JTextField txtNuip;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtRH;
    private javax.swing.JTextField txtSecondL;
    private javax.swing.JTextField txtSecondName;
    private javax.swing.JTextField txtSurname;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
