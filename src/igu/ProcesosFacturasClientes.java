package igu;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ProcesosFacturasClientes extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    public static int numeroFilas = 0;
    public static String[] Datos; 
    public static double b1 = 0;
    public static double b2 = 0;
    public static double T = 0;
    ObtenerID OBID = new ObtenerID();
    
    public ProcesosFacturasClientes() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        //this.setExtendedState(this.MAXIMIZED_BOTH);
    }
/*---------------------Aqui van los metodos generados por el usuario-----------------------------*/
    
    /*Metodo que se encarga de obtener los datos de los titulos*/
    public static String[][] ArregloDatos(String Fecha){
        int TamañoI= 0; int TamañoJ=0; 
        Vector<Vector<String>> resultados = new Vector<>();
        try{
            Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/base_periodicos", "root", "18320996");
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Titulo, CodigoTitulo, Edicion, Precio, Clave,"
            + "FechaRecibido, FechaDevolucion FROM Compras_Proveedor WHERE FechaRecibido = '"+Fecha+"';");
            while(rs.next()){
                Vector<String> fila = new Vector<>(); // Crear un vector para cada fila
                fila.add(rs.getString("Titulo"));
                fila.add(rs.getString("CodigoTitulo"));
                fila.add(rs.getString("Edicion"));
                fila.add(rs.getString("Precio"));
                fila.add(rs.getString("Clave"));
                fila.add(rs.getString("FechaRecibido"));
                fila.add(rs.getString("FechaDevolucion"));
 
                resultados.add(fila); // Añadir la fila completa al vector de resultados
            }
            Con.close(); smnt.close(); rs.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error en metodo ArregloDatos: " + ex);
        }
        //Aqui mandamos a obtener los datos del tamaño de la fila del vector para darselo al arreglo
        for (int i = 0; i < resultados.size(); i++) {
            TamañoI = resultados.size();
            Vector<String> fila = resultados.get(i); // Obtener cada fila
            for (int j = 0; j < fila.size(); j++) {
                //System.out.print(fila.get(j) + " | "); // Mostrar cada columna
                TamañoJ=fila.size();
            }
            //System.out.println(); // Nueva línea para cada fila
        }

        //Creamos el arreglo que contendra los datos de la consulta
        String Datos [][] = new String[TamañoI][TamañoJ];
        //Pasamos los datos del vector al arreglo
        for (int i = 0; i < resultados.size(); i++) {
            Vector<String> fila = resultados.get(i); // Obtener cada fila
            for (int j = 0; j < fila.size(); j++) {
                Datos[i][j]=fila.get(j);
            }
        }
        return Datos;
    }
    /*Metodo que se encarga de obtener los datos de los clientes*/
    public static String[][] ArregloDatos1(String Datos[][]){
        Vector<Vector<String>> resultados1 = new Vector<>();
        int TamañoI1= 0; int TamañoJ1=0;
        try{
            Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/base_periodicos", "root", "18320996");
            Statement smnt = Con.createStatement();
            for(int i = 0; i < Datos.length; i++){
            ResultSet rs = smnt.executeQuery("SELECT Nro_Cliente, Nombre, "+Datos[i][1]+" FROM Clientes;");
            while(rs.next()){
                Vector<String> fila1 = new Vector<>(); // Crear un vector para cada fila
                fila1.add(rs.getString("Nro_Cliente"));
                fila1.add(rs.getString("Nombre"));
                fila1.add(rs.getString(""+Datos[i][1]+""));
 
                resultados1.add(fila1); // Añadir la fila completa al vector de resultados
            }
            }
            Con.close(); smnt.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error en metodo ArregloDatos1: " + ex);
        }

        for (int i = 0; i < resultados1.size(); i++) {
            TamañoI1 = resultados1.size();
            Vector<String> fila1 = resultados1.get(i); // Obtener cada fila
            for (int j = 0; j < fila1.size(); j++) {
                //System.out.print(fila1.get(j) + " | "); // Mostrar cada columna
                TamañoJ1=fila1.size();
            }
            //System.out.println(); // Nueva línea para cada fila
        }
        String Datos1 [][] = new String[TamañoI1][TamañoJ1];
        for (int i = 0; i < resultados1.size(); i++) {
            Vector<String> fila1 = resultados1.get(i); // Obtener cada fila
            for (int j = 0; j < fila1.size(); j++) {
                Datos1[i][j]=fila1.get(j);
            }
        }
        return Datos1;
    }
    /*Metodo que combina los arreglos de clientes y titulos*/
    public static String[][] combinarArreglos(String[][] datos, String[][] datos1) {
    // Filas y columnas de los arreglos
    int filasDatos = datos.length;
    int columnasDatos = datos[0].length;
    int filasDatos1 = datos1.length;
    int columnasDatos1 = datos1[0].length;

    // Verificar que la cantidad de filas en Datos1 sea un múltiplo de filasDatos
    if (filasDatos1 % filasDatos != 0) {
        throw new IllegalArgumentException("Las filas de Datos1 deben ser un múltiplo exacto de las filas de Datos.");
    }

    // Crear el arreglo combinado con la misma cantidad de filas que Datos1
    String[][] combinado = new String[filasDatos1][columnasDatos + columnasDatos1];

    // Tamaño del bloque de clientes que corresponde a cada producto
    int bloqueClientes = filasDatos1 / filasDatos;

    // Llenar el arreglo combinado
    for (int i = 0; i < filasDatos; i++) { // Recorrer cada fila de Datos
        for (int j = 0; j < bloqueClientes; j++) { // Recorrer el bloque correspondiente en Datos1
            // Índice global en Datos1
            int indiceDatos1 = (i * bloqueClientes) + j;

            // Copiar datos de Datos1 al arreglo combinado
            for (int k = 0; k < columnasDatos1; k++) {
                combinado[indiceDatos1][k] = datos1[indiceDatos1][k];
            }

            // Copiar datos de Datos al arreglo combinado
            for (int k = 0; k < columnasDatos; k++) {
                combinado[indiceDatos1][columnasDatos1 + k] = datos[i][k];
            }
        }
    }

    return combinado;
    }
    //Este metodo obtiene una lista con el numero de todos los clientes
    public static void ObtenerN(){
        try{
            Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/base_periodicos", "root", "18320996");
            Statement smnt = Con.createStatement();
            ResultSet resultSet = smnt.executeQuery("SELECT COUNT(Nro_Cliente) AS Total_Registros FROM Clientes;");
            if (resultSet.next()) {
                numeroFilas = resultSet.getInt("Total_Registros");
            }
            Datos = new String[numeroFilas];
            Con.close(); smnt.close(); resultSet.close();
        }catch(SQLException ex){
            System.out.print("Error al cargar el numero de filas: " + ex);
            JOptionPane.showMessageDialog(null,"Error al cargar el numero de filas: " + ex);
        }        
    }
    //
    public void generarLista(){
        try{
            Connection Con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/base_periodicos", "root", "18320996");
            Statement smnt = Con.createStatement();
            ResultSet resultSet = smnt.executeQuery("SELECT Nro_Cliente FROM Clientes;");
            int index = 0;
            // Llena el arreglo con los resultados
            while (resultSet.next()) {
                Datos[index] = resultSet.getString("Nro_Cliente");
                index++;
            }
            Con.close(); smnt.close(); resultSet.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error en metodo ObtenerN: " + ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolverProcesosFacturaCliente = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnIMPRIMIR = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        PanelInterno = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        Fecha = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        btnVolverProcesosFacturaCliente.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverProcesosFacturaCliente.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverProcesosFacturaCliente.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverProcesosFacturaCliente.setText("VOLVER");
        btnVolverProcesosFacturaCliente.setBorderPainted(false);
        btnVolverProcesosFacturaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverProcesosFacturaClienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Factura Clientes");

        btnIMPRIMIR.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        btnIMPRIMIR.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR.setText("FACTURAR");
        btnIMPRIMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIMPRIMIRActionPerformed(evt);
            }
        });

        jLabel3.setText("Mensajes para las notas");

        PanelInterno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelInterno.setOpaque(false);

        jLabel2.setText("Orden de impresion");

        jRadioButton3.setBackground(new java.awt.Color(255, 172, 78));
        jRadioButton3.setText("Numero");

        jRadioButton4.setBackground(new java.awt.Color(255, 172, 78));
        jRadioButton4.setText("Orden Alfabetico");

        jRadioButton5.setBackground(new java.awt.Color(255, 172, 78));
        jRadioButton5.setText("Ruta");

        javax.swing.GroupLayout PanelInternoLayout = new javax.swing.GroupLayout(PanelInterno);
        PanelInterno.setLayout(PanelInternoLayout);
        PanelInternoLayout.setHorizontalGroup(
            PanelInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInternoLayout.createSequentialGroup()
                .addGroup(PanelInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelInternoLayout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelInternoLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addComponent(jRadioButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jRadioButton5)
                .addGap(30, 30, 30))
        );
        PanelInternoLayout.setVerticalGroup(
            PanelInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelInternoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(PanelInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4)
                    .addComponent(jRadioButton5))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel6.setText("al");

        jLabel5.setText("Del");

        jRadioButton2.setBackground(new java.awt.Color(255, 172, 78));
        jRadioButton2.setText("Rango");

        jRadioButton1.setBackground(new java.awt.Color(255, 172, 78));
        jRadioButton1.setText("Todos");

        jLabel4.setText("Clientes");

        jLabel7.setText("Periodicos comprados el dia");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVolverProcesosFacturaCliente)
                        .addGap(188, 188, 188)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 63, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(177, 177, 177)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jRadioButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(38, 38, 38)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(137, 137, 137)
                                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(PanelInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(80, 80, 80))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230))
            .addGroup(layout.createSequentialGroup()
                .addGap(247, 247, 247)
                .addComponent(btnIMPRIMIR, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverProcesosFacturaCliente)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addComponent(PanelInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnIMPRIMIR)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*-----------------Aqui van los metodos que generamos con ayuda de NetBeans-----------------------*/
    
    /*Boton que se encarga de volver a la pantalla Menu Procesos*/
    private void btnVolverProcesosFacturaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverProcesosFacturaClienteActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverProcesosFacturaClienteActionPerformed
    
    /*Boton que realiza las facturas a los clientes y las imprime*/
    private void btnIMPRIMIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIMPRIMIRActionPerformed
        //Obtenemos la fecha seleccionada
        Date Fechas = Fecha.getDate();
        SimpleDateFormat formato1 = new SimpleDateFormat("yyyy/MM/dd");
        String Fechaa = formato1.format(Fechas);
        //Creamos los arreglos y los cargamos con datos
        String DatosT [][] = ArregloDatos(Fechaa);
        String DatosC [][] = ArregloDatos1(DatosT);
        //Combinamos los arreglos
        String[][] combinado = combinarArreglos(DatosT, DatosC);
        //Esto es para la impresion
        ObtenerN();
        generarLista();
        
        //Realizamos el insert (facturamos)
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            for (int i = 0; i < combinado.length; i++) {
            b1 = Double.parseDouble(combinado[i][6]);
            b2 = Double.parseDouble(combinado[i][2]);
            T = b1*b2;
            String id = OBID.IDProveedor(Integer.parseInt(combinado[i][0]));
            smnt.executeUpdate("INSERT INTO compras_cliente (id_Cliente, NombreCliente, Cantidad, Titulo, CodigoTitulo, Edicion, Precio, Clave, FechaRecibido, FechaDevolucion, Importe) VALUES"
            + "("+id+", '"+combinado[i][1]+"', "+combinado[i][2]+""
               +", '"+combinado[i][3]+"', '"+combinado[i][4]+"', "+combinado[i][5]+", "+combinado[i][6]+""
               + ", "+combinado[i][7]+", '"+combinado[i][8]+"', '"+combinado[i][9]+"', "+T+");");
            }
            Con.close(); smnt.close(); 
            JOptionPane.showMessageDialog(null, "Se realizaron: "+combinado.length+" registros :D");
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            }
        for(int i =0; i < Datos.length; i++){
        TicketBlanco ticketBlanco = new TicketBlanco();
        String textoTicket = ticketBlanco.generarTicketBlanco(Fechaa, Datos[i]);
        // Enviar el ticket a la impresora
        ImprimirTicketBlanco.imprimir(textoTicket);
        }
        /*for (int i = 0; i < combinado.length; i++) {
               System.out.println(""+combinado[i][0]+", '"+combinado[i][1]+"', "+combinado[i][2]+""
               +", '"+combinado[i][3]+"', '"+combinado[i][4]+"', "+combinado[i][5]+", "+combinado[i][6]+""
               + ", "+combinado[i][7]+", '"+combinado[i][8]+"', '"+combinado[i][9]+"'; ");
        }*/
        
        /*Aqui mandamos a imprimir los tickets
        TicketBlanco ticketBlanco = new TicketBlanco();
        String textoTicket = ticketBlanco.generarTicketBlanco();
        // Enviar el ticket a la impresora
        ImprimirTicketBlanco.imprimir(textoTicket);*/
    }//GEN-LAST:event_btnIMPRIMIRActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Fecha;
    private javax.swing.JPanel PanelInterno;
    private javax.swing.JButton btnIMPRIMIR;
    private javax.swing.JButton btnVolverProcesosFacturaCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
