package igu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class ProcesosPeriodicosRecibidos extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    double  Total = 0.0;
    
    public ProcesosPeriodicosRecibidos() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        //this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolverProcesosPeriodicosRecibidos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Fecha2 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        Fecha1 = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        btnProcesar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        btnVolverProcesosPeriodicosRecibidos.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverProcesosPeriodicosRecibidos.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverProcesosPeriodicosRecibidos.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverProcesosPeriodicosRecibidos.setText("VOLVER");
        btnVolverProcesosPeriodicosRecibidos.setBorderPainted(false);
        btnVolverProcesosPeriodicosRecibidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverProcesosPeriodicosRecibidosActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel1.setText("Periodicos Recibidos");

        jLabel2.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel2.setText("Periodico recibido entre el:");

        jLabel3.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel3.setText("y");

        jLabel4.setFont(new java.awt.Font("Gadugi", 0, 18)); // NOI18N
        jLabel4.setText("Proveedores");

        jRadioButton1.setBackground(new java.awt.Color(255, 172, 78));
        jRadioButton1.setText("TODOS");

        jRadioButton2.setBackground(new java.awt.Color(255, 172, 78));
        jRadioButton2.setText("RANGO");

        jLabel5.setText("De:");

        jLabel6.setText("al");

        btnProcesar.setBackground(new java.awt.Color(255, 152, 36));
        btnProcesar.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnProcesar.setForeground(new java.awt.Color(0, 0, 0));
        btnProcesar.setText("Procesar");
        btnProcesar.setBorderPainted(false);
        btnProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProcesarActionPerformed(evt);
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
                        .addComponent(jSeparator1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnVolverProcesosPeriodicosRecibidos)
                                .addGap(93, 93, 93)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 88, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnProcesar)
                .addGap(247, 247, 247))
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jRadioButton2)
                        .addGap(83, 83, 83)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel6)
                        .addGap(38, 38, 38)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVolverProcesosPeriodicosRecibidos)
                    .addComponent(jLabel1))
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Fecha2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(btnProcesar)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverProcesosPeriodicosRecibidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverProcesosPeriodicosRecibidosActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverProcesosPeriodicosRecibidosActionPerformed

    private void btnProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProcesarActionPerformed
    //Creamos los datos para la imprension
    
    //Las dos fechas
    Date fechaSeleccionada = Fecha1.getDate();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    String F1 = formato.format(fechaSeleccionada);
    Date fechaSeleccionada2 = Fecha2.getDate();
    String F2 = formato.format(fechaSeleccionada2);
    
    SimpleDateFormat formato1 = new SimpleDateFormat("yyyy/MM/dd");
    String Fecha1 = formato1.format(fechaSeleccionada);
    String Fecha2 = formato1.format(fechaSeleccionada2);
    //Fecha y Hora
    Date fechaActual = new Date();
    String FechaA = formato.format(fechaActual);
    
    Date horaActual = new Date();
    SimpleDateFormat formato4 = new SimpleDateFormat("HH:mm:ss");
    String Hora = formato4.format(horaActual);

    //Datos del total de la cuenta
    
    // Crear el trabajo de impresión
    PrinterJob job = PrinterJob.getPrinterJob(); 
    job.setPrintable(new Printable() { 
        @Override
        public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException  {
            if (pageIndex > 0) {
                return Printable.NO_SUCH_PAGE; // Solo una página
            }   

            // Configurar fuente y posición inicial
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            g2d.setFont(new Font("Courier New", Font.PLAIN, 10));

            int y = 20; // Coordenada vertical inicial

            // Encabezado
            g2d.drawString("DIST. DE PERIODICOS Y REVISTAS", 50, y); y += 15;
            g2d.drawString("Revistas Recibidas del : "+F1+" al : "+F2+"", 50, y); y += 20;

            // Datos de encabezado derecho
            g2d.drawString("Fecha : "+FechaA+"", 450, 20);
            g2d.drawString("Hora : "+Hora+"", 450, 35);
            g2d.drawString("Página : ", 450, 50);

            // Línea separadora
            g2d.drawLine(50, y, 550, y); y += 15;

            // Encabezado de columnas
            g2d.drawString("CANT  TITULO                      EDICION  PRECIO   IMPORTE", 50, y); y += 15;
            g2d.drawLine(50, y, 550, y); y += 15;

            //Detalles de los titulos
            try{
            //Cargmos los valores de la columna 1 y 2 (Nro cliente y nombre)
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Cantidad, Titulo, Edicion, Precio, Importe, TotalCompra "
            +"FROM Compras_Proveedor WHERE FechaRecibido BETWEEN "
            + "'"+Fecha1+"' AND '"+Fecha2+"';");
            
            while(rs.next()){
                 int cantidad = rs.getInt("Cantidad");
                String titulo = rs.getString("Titulo");
                int edicion = rs.getInt("Edicion");
                double precio = rs.getDouble("Precio");
                double importe = rs.getDouble("Importe");
                Total += importe;    
                // Imprimir cada línea
                String linea = String.format("%-4d  %-28s %-6d  %-7.2f  %-7.2f", 
                cantidad, titulo, edicion, precio, importe);
                g2d.drawString(linea, 50, y); 
                y += 15;
                /*String Cantidad = rs.getString("Cantidad");
                String Titulo = rs.getString("Titulo");
                String Edicion = rs.getString("Edicion");
                String Precio = rs.getString("Precio");
                String Importe = rs.getString("Importe");
                int Import = rs.getInt("Importe");
                
                g2d.drawString(""+Cantidad+"   "+Titulo+"        "+Edicion+"     "+Precio+"     "+Importe+"", 50, y); y += 15;*/
            }
            Con.close(); smnt.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al obtener los datos: " + ex);
        }
            
            // Línea separadora
            g2d.drawLine(50, y, 550, y); y += 15;

            // Subtotales
            double NTotal = Total/2;
            g2d.drawString("SUB TOTAL :                "+NTotal+"", 350, y); y += 15;
            g2d.drawString("DESCUENTO :                0.00", 350, y); y += 15;
            g2d.drawString("I.V.A.    :                0.00", 350, y); y += 15;
            g2d.drawString("TOTAL     :                "+NTotal+"", 350, y); y += 15;

            // Línea final
            g2d.drawLine(50, y, 550, y);

            return Printable.PAGE_EXISTS; // Página válida
        }
    });

    // Mostrar el diálogo de impresión
    boolean aceptar = job.printDialog();
    if (aceptar) {
        try {
            job.print(); // Imprimir
        } catch (PrinterException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al imprimir: " + ex.getMessage());
        }
    }
    }//GEN-LAST:event_btnProcesarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser Fecha1;
    private com.toedter.calendar.JDateChooser Fecha2;
    private javax.swing.JButton btnProcesar;
    private javax.swing.JButton btnVolverProcesosPeriodicosRecibidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
