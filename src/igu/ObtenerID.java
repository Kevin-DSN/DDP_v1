package igu;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
//Aqui creamos la clase que obtendra nuestros metodos
public class ObtenerID {
    //Variables globales para acceder a la base de datos MySQL
    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    //Declaramos el metodo que obtendra el id de los clientes basado en su Nro_Cliente
    public String IDCliente(int id){
        String R="";
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT id FROM clientes WHERE Nro_Cliente = "+id+";");
            while(rs.next()){
                R=rs.getString("id");
            }
            Con.close(); smnt.close(); rs.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al generar numero de folio: "+ex);
        }
        return R;
    }
    
    public String IDProveedor(int id){
        String R="";
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT id FROM proveedores WHERE Nro_Proveedor = "+id+";");
            while(rs.next()){
                R=rs.getString("id");
            }
            Con.close(); smnt.close(); rs.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al generar numero de folio: "+ex);
        }
        return R;
    }
}
