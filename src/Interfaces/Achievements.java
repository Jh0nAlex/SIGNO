/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Resources.Connection;
/**
 *
 * @author unicuces
 */
public class Achievements extends javax.swing.JInternalFrame {

    DefaultTableModel model;
    Connection cone;
    DefaultListModel buscar;
    String idT;

    /**
     * Creates new form Archievements
     */
    public Achievements() {
        initComponents();
        cone = new Connection();
        id();
        tbAchievements("");
        mostrarPeriodo();
        mostrarMateria();
        sumPercentage();
        btnUpdate.setEnabled(false);
        clear();
        buscar = new DefaultListModel();
    }

    void tbAchievements(String value) {
        String[] titulos = {"idlogro", "Nombre", "Descripcion", "Materia", "Periodo", "Porcentaje"};
        String[] registros = new String[6];
        String sql = "select idLogro,descripcion,log.Porcentaje as porcentaje,log.Nombre as Nombre,mat.Nombre as materias"
                + ",per.Nombre as periodo,log.Activo as Activo from logro as log"
                + " INNER JOIN materia as mat on log.Materia_id=mat.idMateria"
                + " INNER JOIN periodo as per on log.Periodo_id=per.idperiodo";
//                + "WHERE log.Materia_id LIKE "+cdoSubjects.getSelectedItem()
//                + "log.periodo_id LIKE"+cboPeriod.getSelectedItem();

        model = new DefaultTableModel(null, titulos);
        try {

            ResultSet rs = cone.consultDB(sql);

            while (rs.next()) {
                int state;
                state = Integer.parseInt(rs.getString("Activo"));
                if (state == 1) {
                    registros[0] = rs.getString("idLogro");
                    registros[1] = rs.getString("Nombre");
                    registros[2] = rs.getString("descripcion");
                    registros[3] = rs.getString("materias");
                    registros[4] = rs.getString("periodo");
                    registros[5] = rs.getString("porcentaje") + "%";
                    model.addRow(registros);
                }
            }

            tbAchievements.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    public void mostrarPeriodo() {
        try {
            String sql = "SELECT idperiodo FROM periodo";
            Connection cone2 = new Connection();
            ResultSet rs = cone2.consultDB(sql);
            cboPeriod.addItem("seleccione");
            while (rs.next()) {

                cboPeriod.addItem(rs.getString("idperiodo"));

            }
        } catch (SQLException ex) {
            Logger.getLogger(Achievements.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarMateria() {
        try {
            String mysql = "SELECT * FROM materia";
            Connection cone2 = new Connection();
            ResultSet rs = cone2.consultDB(mysql);
            cdoSubjects.addItem("seleccione");
            while (rs.next()) {
                int state;
                state = Integer.parseInt(rs.getString("Activo"));
                if (state == 1) {
                    cdoSubjects.addItem(rs.getString("idMateria"));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Achievements.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarListas() {
        try {
            String sql = "select * from logro";
            Connection cone2 = new Connection();
            ResultSet rs = cone2.consultDB(sql);
            while (rs.next()) {
                int state;
                state = Integer.parseInt(rs.getString("Activo"));
                if (state == 1) {
                    buscar.addElement(rs.getString("Nombre"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Achievements.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectUpdate() {
        Connection cone2 = new Connection();
        int selectRow;

        selectRow = tbAchievements.getSelectedRow();
        if (selectRow == -1) {
            JOptionPane.showMessageDialog(null, "no ha seleccionado ninguna fila");
        } else {
            try {
                String tipo = "";
                idT = (String) model.getValueAt(tbAchievements.getSelectedRow(), 0);
                ResultSet rs = cone2.consultDB("select * from logro where idLogro = " + idT);

                if (rs.next()) {
                    txtId.setText(idT);
                    txtName.setText(rs.getString("Nombre"));
                    txtDescription.setText(rs.getString("descripcion"));
                    cdoSubjects.setSelectedItem(rs.getString("Materia_id"));
                    if (rs.getString("Tipo").equals("ACA")) {

                        rbAcademic.setSelected(true);

                    } else if (rs.getString("Tipo").equals("INS")) {

                        rbInstitutional.setSelected(true);
                    }
                    cboPeriod.setSelectedItem(rs.getString("Periodo_id"));
                    spiPercentage.setValue(rs.getInt("Porcentaje"));

                } else {
                    JOptionPane.showMessageDialog(rootPane, "El codigo no existe");
                }
            } catch (SQLException ex) {
                System.out.println("error" + ex);
            }
        }
    }

    public void clear() {
        txtName.setText("");
        txtDescription.setText("");
        spiPercentage.setValue(0);
        

    }

    public void updateTable() {
        String idA = txtId.getText();
        String name = txtName.getText();
        String descr = txtDescription.getText();
        String idS = (String) cdoSubjects.getSelectedItem();
        String idP = (String) cboPeriod.getSelectedItem();
        int Percentaje = (int) spiPercentage.getValue();
        String type = "";
        if (rbInstitutional.isSelected()) {
            type = "INS";

        } else if (rbAcademic.isSelected()) {
            type = "ACA";
        }
        
        cone.modifyDB("UPDATE logro SET Nombre='" + name + "', descripcion='" + descr
                + "', Materia_id='" + idS + "',periodo_id='" + idP + "', porcentaje='" + Percentaje + "', tipo='" + type + "' WHERE idLogro='" + idA+"'");
        JOptionPane.showMessageDialog(null, "Actualización Exitosa");

    }

    public void insert() {
        int Porcentaje;
        String materias = (String) cdoSubjects.getSelectedItem();
        String periodo = (String) cboPeriod.getSelectedItem();
        String name, area, tipo = "", id;
        id = txtId.getText();
        name = txtName.getText();
        area = txtDescription.getText();
        Porcentaje = (int) spiPercentage.getValue();
        if (rbInstitutional.isSelected()) {
            tipo = "INS";

        } else if (rbAcademic.isSelected()) {
            tipo = "ACA";
        }

        cone.modifyDB("INSERT INTO logro VALUES (" + id + ",'" + name + "','" + area + "',1," + materias + "," + periodo + "," + Porcentaje + ",'" + tipo + "')");
        JOptionPane.showMessageDialog(null, "registro exitoso");

    }

    public void id() {
        try {

            String fila;

            //cuadro.setText("CODIGO\tNOMBRE\tGENERO\tEDAD\tCURSO\n");
            ResultSet rs = cone.consultDB("SELECT MAX(idLogro) FROM logro");
            if (rs.next()) {
                int num = rs.getInt("MAX(idLogro)") + 1;
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

    public void inactivate() {
        int selectRow;

        selectRow = tbAchievements.getSelectedRow();
        if (selectRow == -1) {
            JOptionPane.showMessageDialog(null, "no ha seleccionado ninguna fila");
        } else {

            DefaultTableModel modelo = (DefaultTableModel) tbAchievements.getModel();
            String idT = (String) modelo.getValueAt(selectRow, 0);
            modelo.removeRow(selectRow);
            Integer.parseInt(idT);

            cone.modifyDB("UPDATE logro SET Activo = '0' "
                    + "WHERE idLogro = '" + idT + "'");
            JOptionPane.showMessageDialog(null, "Inactivación Éxitosa");
        }
    }

    public void sumPercentage() {

        ResultSet rs = cone.consultDB("select * from logro where Materia_id = '"
                + cdoSubjects.getSelectedItem() + "'AND periodo_id ='" + cboPeriod.getSelectedItem() + "'"
                + "AND Activo='1'");

        try {
            int total = 0;

            while (rs.next()) {
                int porsrntage = Integer.parseInt(rs.getString("porcentaje"));
                total = total + porsrntage;
            }

            txtTotal.setText(String.valueOf(total));
            if (total == 90) {
                JOptionPane.showMessageDialog(null, "El porcentaje esta por llegar al 100%");
                spiPercentage.setValue(10);

            } else if (total == 100) {
                JOptionPane.showMessageDialog(null, "El porcentaje ya llegó a 100%");
                btnInsert.setEnabled(false);
            } else {
                btnInsert.setEnabled(true);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Achievements.class.getName()).log(Level.SEVERE, null, ex);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        Actualizar = new javax.swing.JMenuItem();
        Inactivar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cdoSubjects = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescription = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cboPeriod = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        rbAcademic = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        rbInstitutional = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        spiPercentage = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtId = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbAchievements = new javax.swing.JTable();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();

        Actualizar.setText("Actualizar");
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Actualizar);

        Inactivar.setText("Inactivar");
        Inactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InactivarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(Inactivar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("LOGROS");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setText("Nombre");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setText("ID_Materia");

        cdoSubjects.setToolTipText("");
        cdoSubjects.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cdoSubjectsActionPerformed(evt);
            }
        });

        txtDescription.setColumns(20);
        txtDescription.setRows(5);
        jScrollPane1.setViewportView(txtDescription);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setText("Descripcion");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel4.setText("Nombre de materia:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel6.setText("ID_Periodo");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel7.setText("Nombre del periodo:");

        cboPeriod.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboPeriodItemStateChanged(evt);
            }
        });
        cboPeriod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPeriodActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonGroup1.add(rbAcademic);
        rbAcademic.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbAcademic.setText("Academico");
        rbAcademic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAcademicActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel9.setText("Tipo de logro");

        buttonGroup1.add(rbInstitutional);
        rbInstitutional.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        rbInstitutional.setText("Institucional");
        rbInstitutional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbInstitutionalActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel10.setText("Porcentaje");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel11.setText("%");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel13.setText("Id Materia:");

        jLabel14.setText("Total:");

        txtTotal.setText("0");

        jLabel15.setText("%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel13)))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(33, 33, 33)
                                .addComponent(spiPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel15))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cdoSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cboPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(rbAcademic)
                                        .addGap(2, 2, 2)
                                        .addComponent(rbInstitutional)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel2)
                                .addGap(22, 22, 22)
                                .addComponent(jLabel6)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cdoSubjects, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboPeriod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbAcademic)
                                    .addComponent(rbInstitutional))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(spiPercentage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)
                                .addComponent(txtTotal)
                                .addComponent(jLabel15))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tbAchievements.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbAchievements.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tbAchievements);

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

        jLabel12.setText("Buscar:");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(btnInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInsert)
                    .addComponent(btnUpdate))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        int total = 0;
        try {
            ResultSet rs = cone.consultDB("SELECT * FROM logro WHERE Materia_id='" + cdoSubjects.getSelectedItem() + "'AND "
                    + "periodo_id='" + cboPeriod.getSelectedItem() + "' AND Activo='1'");

            while (rs.next()) {

                total = total + rs.getInt("Porcentaje");
                System.out.println(total);

            }
            int porcen = (int) spiPercentage.getValue();
            if (total > porcen || porcen > 100) {
                JOptionPane.showMessageDialog(null, "El porcentaje ingresado no Puede sobrepasar el 100%");

            } else {
                insert();
                clear();
                tbAchievements("");
                sumPercentage();
                id();

            }
        } catch (SQLException ex) {
            Logger.getLogger(Achievements.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnInsertActionPerformed

    private void cboPeriodItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboPeriodItemStateChanged
        int num = cboPeriod.getSelectedIndex();
        if (num != 0) {
            String period = (String) cboPeriod.getSelectedItem();
            String sql = "SELECT Nombre,idperiodo FROM periodo WHERE idperiodo = " + period;
            ResultSet rs = cone.consultDB(sql);

            try {
                while (rs.next()) {
                    jLabel8.setText(rs.getString("Nombre"));
                }

            } catch (Exception e) {
                System.out.println("Error" + e);
            }
            sumPercentage();
        } else {
            jLabel8.setText("");
        }


    }//GEN-LAST:event_cboPeriodItemStateChanged

    private void cboPeriodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPeriodActionPerformed

    }//GEN-LAST:event_cboPeriodActionPerformed

    private void cdoSubjectsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cdoSubjectsActionPerformed
        int num = cdoSubjects.getSelectedIndex();
        if (num != 0) {
            String mate = (String) cdoSubjects.getSelectedItem();
            String sql = "SELECT Nombre FROM materia WHERE idMateria = " + mate;
            ResultSet rs = cone.consultDB(sql);
            try {

                while (rs.next()) {

                    jLabel5.setText(rs.getString("Nombre"));

                }

            } catch (Exception e) {
                System.out.println("Error" + e);
            }
            sumPercentage();
        } else {
            jLabel5.setText("");
        }


    }//GEN-LAST:event_cdoSubjectsActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        tbAchievements(txtSearch.getText());
    }//GEN-LAST:event_txtSearchKeyReleased

    private void rbInstitutionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbInstitutionalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbInstitutionalActionPerformed

    private void rbAcademicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAcademicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbAcademicActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        updateTable();
        sumPercentage();
        id();
        clear();
        btnUpdate.setEnabled(false);
        btnInsert.setEnabled(true);
        tbAchievements("");
        

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        // TODO add your handling code here:
        selectUpdate();
        btnUpdate.setEnabled(true);
        btnInsert.setEnabled(false);
    }//GEN-LAST:event_ActualizarActionPerformed

    private void InactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InactivarActionPerformed
        // TODO add your handling code here:
        inactivate();
    }//GEN-LAST:event_InactivarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Actualizar;
    private javax.swing.JMenuItem Inactivar;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboPeriod;
    private javax.swing.JComboBox cdoSubjects;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbAcademic;
    private javax.swing.JRadioButton rbInstitutional;
    private javax.swing.JSpinner spiPercentage;
    private javax.swing.JTable tbAchievements;
    private javax.swing.JTextArea txtDescription;
    private javax.swing.JLabel txtId;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JLabel txtTotal;
    // End of variables declaration//GEN-END:variables
}
