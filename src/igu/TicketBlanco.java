package igu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class TicketBlanco {
    
    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    public static String[][] Datos;
    
    public String generarTicketBlanco(String Fecha, String Num) {
        StringBuilder ticket = new StringBuilder();
        int numeroFilas = 0; int Control=0; double TotalI=0; int TotalC=0;
        //SELECT Cantidad, Titulo, Edicion, Precio, Importe FROM compras_cliente WHERE FechaRecibido = '2025-01-02' AND id_Cliente = 100;
        /*----------------------------------------------------------------------------------------*/
        try{
            Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/base_periodicos", "root", "18320996");
            Statement smnt = Con.createStatement();
            ResultSet resultSet = smnt.executeQuery("SELECT COUNT(Titulo) AS TotalT FROM compras_cliente WHERE FechaRecibido = '2025-01-02' AND id_Cliente = "+Num+";");
            if (resultSet.next()) {
                numeroFilas = resultSet.getInt("TotalT");
            }
            Datos = new String[numeroFilas][6];
            Con.close(); smnt.close(); resultSet.close();
        }catch(SQLException ex){
            System.out.print("Error al cargar el numero de filas: " + ex);
            //JOptionPane.showMessageDialog(null,"Error al cargar el numero de filas: " + ex);
        }

        /*----------------------------------------------------------------------------------------*/
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT NombreCliente, Cantidad, Titulo, Edicion, Precio, Importe FROM compras_cliente "
            + "WHERE FechaRecibido = '"+Fecha+"' AND id_Cliente = "+Num+";");
            
            while(rs.next()){
                Datos[Control][0]=rs.getString("NombreCliente");
                Datos[Control][1]=rs.getString("Cantidad");
                Datos[Control][2]=rs.getString("Titulo");
                Datos[Control][3]=rs.getString("Edicion");
                Datos[Control][4]=rs.getString("Precio");
                Datos[Control][5]=rs.getString("Importe");
                Control++;
            }
            Con.close(); smnt.close(); rs.close();
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
        }
        /*----------------------------------------------------------------------------------------*/
        ticket.append("    DIST. DE PERIODICOS Y REVISTAS \n");
        ticket.append("FECHA:            \n");
        ticket.append("NOMBRE: "+Datos[0][0]+"  \n");
        ticket.append("=====================================\n");
        ticket.append("CANT    TITULO    EDC    PREC    SUBT\n");
        ticket.append("=====================================\n");
        for(int i =0; i<Datos.length; i++){
            ticket.append(""+Datos[i][1]+" "+Datos[i][2]+" "+Datos[i][3]+" "+Datos[i][4]+" "+Datos[i][5]+" \n");
            double I2 = Double.parseDouble(Datos[i][5]); int C2 = Integer.parseInt(Datos[i][1]);
            TotalI+=I2; TotalC+=C2;
        }
        ticket.append("=====================================\n");
        
        ticket.append(""+TotalC+"\n");
        ticket.append("Ejemplares devueltos:  \n");
        ticket.append("Subtotall:        "+TotalI+"\n");
        
        return ticket.toString();
    }
}
