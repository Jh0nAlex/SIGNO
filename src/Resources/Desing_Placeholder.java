package Resources;



import javax.swing.JPasswordField;
import javax.swing.JTextField;

/* @author JH0N4T4N  */
public class Desing_Placeholder {

    String plomo = "#818181";
    String blanco = "#FDFEFE"; 

    public void Mensaje(JTextField letra, String Mensaje, int tamano) {
        if (tamano == 0) {
            letra.setText(Mensaje);
            letra.setForeground(java.awt.Color.decode(plomo));
        }
    }
    public void Clic(JTextField letra, String Mensaje){
        if (letra.getText().equals(Mensaje)) {
            letra.setText("");
            letra.setForeground(java.awt.Color.decode(blanco));
        }        
    }
    
    public void Mensaje1(JPasswordField letra, String Mensaje1, int tamano){
        if (tamano == 0) {
            letra.setText(Mensaje1);
            letra.setForeground(java.awt.Color.decode(plomo));
        } 
    }
    
    public void Clic1(JPasswordField letra, String Mensaje1){
        if (letra.getText().equals(Mensaje1)) {
            letra.setText("");
            letra.setForeground(java.awt.Color.decode(blanco));
        }
    }
}
