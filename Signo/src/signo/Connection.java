/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signo;

import javax.swing.JOptionPane;
import java.sql.*;


/**
 * Clase principal para realizar la conexión con la base de datos utilizando
 * mysql como gestor de base de datos.
 * <pre>Connection con = new Connection();</pre>
 * @author Grupo de base de datos
 * @version 1.0 10-11-2017 
 */
public class Connection {
    Statement st;
    
    /**
     * Método principal de conexión a la base de datos,
     * donde se carga el driver JDBC de java para MYSQL 
     * (com.mysql.jdbc.Driver)
     * <pre> Connection.Connection(); </pre>
     * @autor grupo de base de datos
     * @version 1.0 10-11-2017
     * 
     */
    public Connection(){
        try {
		//Cargamos el Driver MySQL
		Class.forName("com.mysql.jdbc.Driver");
		java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/signo_db","root","");
		System.out.println("conexion establecida");
		//JOptionPane.showMessageDialog(null,"Estas Conectado");
                st = con.createStatement();
				
            } catch (ClassNotFoundException | SQLException e) {
		System.out.println("error de conexion");
		JOptionPane.showMessageDialog(null,"Error de conexion"+e);
            }
    }
    
    /**
    * Método implementado para realizar una consulta a la base de datos y recibir un conjunto de datos en forma de ResultSet.
    * <pre>Connection.consultDB("Consulta SQL");</pre>
    * @author Grupo base de datos
    * @version 1.0 10-11-2017
    * @param query parámetro el cual recibe una cadena de texto que ejecuta una sentencia SQL en Mysql
    * @return rs (ResultSet) Éste metodo retorna un ResultSet el cual almacena todos los datos ejecutados en la consulta SQL
    * 
    * 
    */
    public ResultSet consultDB(String query){
        ResultSet rs = null;
        try {
                rs=st.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error Inesperado");
                System.out.println("Error"+e.getMessage());
            }
        return rs;
    }
     /**
    * Método implementado para modificar o realizar un cambio a la base de datos por medio de una sentencia SQL.
    * <pre>Connection.modifyDB("Consulta SQL");</pre>
    * @author Grupo base de datos
    * @version 1.0 10-11-2017
    * @param query parámetro el cual recibe una cadena de texto que ejecuta una sentencia SQL en Mysql 
    */
    public void modifyDB(String query){
        try {
                st.executeUpdate(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error Inesperado");
                System.out.println("Error"+e.getMessage());
            }
    }
   
    
}
