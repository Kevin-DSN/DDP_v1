package igu;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import com.toedter.calendar.JDateChooser;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import javax.swing.SwingUtilities;

public class ArchivoProveedores extends javax.swing.JFrame {

    public static String CodigoTitu = "";
    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    int Condicional = 0;
    String N;
    double DescProv;
    int Nro_Prov;
    String Nom_Prov;
    ObtenerID OBID = new ObtenerID();
    
    public ArchivoProveedores() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        this.setExtendedState(this.MAXIMIZED_BOTH);
        PanelPrincipalRR.setVisible(false);
        btnGUARDAR.setEnabled(false);
        btnELIMINAR.setEnabled(false);
        txtIMPORTE.setVisible(false);
        /*Este codigo hace que se enfoque el JTextField al abrir la vetnana*/
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            txtNUMERO.requestFocus();
        }
        });
    }
    
    private void BotonAgregar(){
        DefaultTableModel model = (DefaultTableModel) TablaProveedores1.getModel();
        Date Fecha = jDateChooser1.getDate(); double SUBT; Date FechaDev = jDateChooser3.getDate();
        int Propuesta= Integer.parseInt(txtPROPUESTA.getText()); int CantidadE=Integer.parseInt(txtCANTIDAD.getText()); 
        if(txtTITULO.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un titulo");
        }else{
            if (Propuesta != CantidadE){
                /*ArchivoTitulos_Correccion MenuA = new ArchivoTitulos_Correccion();
                MenuA.setVisible(true);
                MenuA.setLocationRelativeTo(null);
                CodigoTitu = txtTITULO.getText();*/
                
            }else{
            String ClaveR = txtTITULO.getText();
            String Edicion = txtEDICION.getText();
            String Cantidad = txtCANTIDAD.getText();
            String PU = txtPRECIO.getText();
            String Importe = txtIMPORTE.getText();
            String Calve = txtCLAVE_CUADRO.getText();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String fechaFormateada = formato.format(Fecha);
            String fechaFormateadaDev = formato.format(FechaDev);
 
            model.addRow(new Object[]{ClaveR, N, Edicion, Cantidad, PU, Importe, Calve, fechaFormateada, fechaFormateadaDev});
            if(txtSUBTOTAL.getText().isEmpty()){
                SUBT = Double.parseDouble(txtIMPORTE.getText());
                txtSUBTOTAL.setText(String.valueOf(SUBT));
                double valorD = (SUBT * DescProv) / 100;
                txtDESCUENTO1.setText(String.valueOf(valorD));
                double Total = SUBT - valorD;
                txtTOTAL.setText(String.valueOf(Total));
            }
            else{
                double SU = Double.parseDouble(txtIMPORTE.getText());
                double S = Double.parseDouble(txtSUBTOTAL.getText());
                SUBT = SU + S;
                txtSUBTOTAL.setText(String.valueOf(SUBT));
                double valorD = (SUBT * DescProv) / 100;
                txtDESCUENTO1.setText(String.valueOf(valorD));
                double Total = SUBT - valorD;
                txtTOTAL.setText(String.valueOf(Total));
            }
            LimpiarV();   
            }
        }
    }
    
    private void Guardar_Actualizar(){
        /*Declaramoos las variables*/
        String Nombre=""; String Calle=""; String Colonia=""; String Poblacion=""; String Telefono=""; String RFC=""; String Corto=""; String EMAIL=""; Double Desc=0.0; Double Saldo=0.0;
        /*Les asignamos los valores*/
        int Nro_P = Integer.parseInt(txtNUMERO.getText());
        Nombre = String.valueOf(txtNOMBRE.getText());
        Calle = String.valueOf(txtCALLE.getText());
        Colonia = String.valueOf(txtCOLONIA.getText());
        Poblacion = String.valueOf(txtPOBLACION.getText());
        Telefono = String.valueOf(txtTELEFONO.getText());
        RFC = String.valueOf(txtRFC.getText());
        Corto = String.valueOf(txtCORTO.getText());
        EMAIL = String.valueOf(txtEMAIL.getText());
        if(!txtDESCUENTO.getText().isEmpty()){
        Desc = Double.parseDouble(txtDESCUENTO.getText());}
        if(!txtSALDO.getText().isEmpty()){
        Saldo = Double.parseDouble(txtSALDO.getText());}
        
        /*Si la condicion vale 0, quiere decir que los datos son nuevos, se hace un insert*/
        if(Condicional == 0){
            try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            smnt.executeUpdate("INSERT INTO proveedores (Nro_Proveedor, Nombre, Calle, Colonia, Poblacion, Telefono, RFC, Corto, EMAIL, Descuento_P, Saldo) VALUES "
            + "("+Nro_P+", '"+Nombre+"', '"+Calle+"', '"+Colonia+"', '"+Poblacion+"', "
            + "'"+Telefono+"', '"+RFC+"', '"+Corto+"', '"+EMAIL+"', "+Desc+", "+Saldo+");");
            
            Limpiar();
            txtNUMERO.setText("");
            txtNUMERO.requestFocus();
            JOptionPane.showMessageDialog(null, "Datos registrados");
            btnGUARDAR.setEnabled(false);
            btnELIMINAR.setEnabled(false);
            Con.close(); smnt.close();
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error registrar datos: "+ex);
            }
        }
        
        /*Si la condicion vale 1, quiere decir que los datos son de un cliente que ya existe, se hace un update*/
        if(Condicional == 1){
            try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            smnt.executeUpdate("UPDATE proveedores SET Nombre = '"+Nombre+"',"
            + " Calle = '"+Calle+"', Colonia = '"+Colonia+"', Poblacion = '"+Poblacion+"' , "
            + "Telefono = '"+Telefono+"', RFC = '"+RFC+"', Corto = '"+Corto+"', EMAIL = '"+EMAIL+"', "
            + "Descuento_P = "+Desc+", Saldo = "+Saldo+" WHERE Nro_Proveedor = "+Nro_P+";");
            
            Limpiar();
            txtNUMERO.setText("");
            txtNUMERO.requestFocus();
            JOptionPane.showMessageDialog(null, "Datos actualizados");
            btnGUARDAR.setEnabled(false);
            btnELIMINAR.setEnabled(false);
            Con.close(); smnt.close();
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error actualizar datos: "+ex);
            }
        }
    }
    
    public static String generarCodigo(Date fecha) {
        // Convertir Date a LocalDate
        LocalDate localDate = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // Formatear el año para obtener los dos últimos dígitos
        String año = String.format("%02d", localDate.getYear() % 100);

        // Formatear el mes y el día con dos dígitos
        String mes = String.format("%02d", localDate.getMonthValue());
        String dia = String.format("%02d", localDate.getDayOfMonth());

        // Concatenar para formar el código
        String codigo = año + mes + dia;
        
        return codigo;
    }
    
    public void GenerarFolio(){
    int Folio =0;
    try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT MAX(FolioCompra) FROM compras_proveedor;");
            while(rs.next()){
                Folio = rs.getInt(1);
            }
            Con.close(); smnt.close(); rs.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al generar numero de folio: "+ex);
        }
        Folio += 1;
        txtFOLIO_MOVIMIENTO.setText(String.valueOf(Folio));
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipalDG = new javax.swing.JPanel();
        PanelSuperiorDG = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnMostrarDatosGeneral = new javax.swing.JButton();
        btnMostrarRecepcionRevistas = new javax.swing.JButton();
        btnVolverArchivoProveedores = new javax.swing.JButton();
        PanelAgregarDatosDG = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNUMERO = new javax.swing.JTextField();
        btnBUSQUEDA = new javax.swing.JButton();
        txtCORTO = new javax.swing.JTextField();
        txtNOMBRE = new javax.swing.JTextField();
        txtCALLE = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCOLONIA = new javax.swing.JTextField();
        txtPOBLACION = new javax.swing.JTextField();
        txtRFC = new javax.swing.JTextField();
        txtTELEFONO = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEMAIL = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDESCUENTO = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtSALDO = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtULTIMO_MOVIMIENTO = new javax.swing.JTextField();
        btnELIMINAR = new javax.swing.JButton();
        btnGUARDAR = new javax.swing.JButton();
        PanelBotonesDG = new javax.swing.JPanel();
        btnMOVIMIENTOS = new javax.swing.JButton();
        btnIMPRIMIR_EDO_CTA = new javax.swing.JButton();
        btnCORREO = new javax.swing.JButton();
        PanelTablaDG = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProveedores = new javax.swing.JTable();
        PanelPrincipalRR = new javax.swing.JPanel();
        PanelSuperior = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnMostraraDatosGeneral = new javax.swing.JButton();
        btnMostrarRecepcionRevistas1 = new javax.swing.JButton();
        btnVolverArchivoProveedores1 = new javax.swing.JButton();
        PanelDatosProveedor = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtTITULO = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtFOLIO_MOVIMIENTO = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtRECEPCION_REVISTAS = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtREFERENCIA = new javax.swing.JTextField();
        btnBUSCAR = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        txtEDICION = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtCANTIDAD = new javax.swing.JTextField();
        txtPRECIO = new javax.swing.JTextField();
        txtCLAVE_CUADRO = new javax.swing.JTextField();
        txtIMPORTE = new javax.swing.JTextField();
        btnAGREGAR = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel32 = new javax.swing.JLabel();
        txtPROPUESTA = new javax.swing.JTextField();
        PanelResultados = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        txtTOTAL = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        txtDESCUENTO1 = new javax.swing.JTextField();
        txtSUBTOTAL = new javax.swing.JTextField();
        btnAPLICAR_COMPRA = new javax.swing.JButton();
        PanelTabla = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaProveedores1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        PanelPrincipalDG.setOpaque(false);

        PanelSuperiorDG.setOpaque(false);

        jLabel1.setText("PROVEEDOR");

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));

        btnMostrarDatosGeneral.setBackground(new java.awt.Color(255, 152, 36));
        btnMostrarDatosGeneral.setForeground(new java.awt.Color(0, 0, 0));
        btnMostrarDatosGeneral.setText("Datos General y Estado de Cuenta");
        btnMostrarDatosGeneral.setBorderPainted(false);

        btnMostrarRecepcionRevistas.setBackground(new java.awt.Color(255, 152, 36));
        btnMostrarRecepcionRevistas.setForeground(new java.awt.Color(0, 0, 0));
        btnMostrarRecepcionRevistas.setText("Recepcion de Revistas");
        btnMostrarRecepcionRevistas.setBorderPainted(false);
        btnMostrarRecepcionRevistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarRecepcionRevistasActionPerformed(evt);
            }
        });

        btnVolverArchivoProveedores.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverArchivoProveedores.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverArchivoProveedores.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverArchivoProveedores.setText("VOLVER");
        btnVolverArchivoProveedores.setBorderPainted(false);
        btnVolverArchivoProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverArchivoProveedoresActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSuperiorDGLayout = new javax.swing.GroupLayout(PanelSuperiorDG);
        PanelSuperiorDG.setLayout(PanelSuperiorDGLayout);
        PanelSuperiorDGLayout.setHorizontalGroup(
            PanelSuperiorDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorDGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSuperiorDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(PanelSuperiorDGLayout.createSequentialGroup()
                        .addComponent(btnVolverArchivoProveedores)
                        .addGap(511, 511, 511)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelSuperiorDGLayout.createSequentialGroup()
                        .addComponent(btnMostrarDatosGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(btnMostrarRecepcionRevistas, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelSuperiorDGLayout.setVerticalGroup(
            PanelSuperiorDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSuperiorDGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSuperiorDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverArchivoProveedores)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSuperiorDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMostrarDatosGeneral)
                    .addComponent(btnMostrarRecepcionRevistas))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelAgregarDatosDG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelAgregarDatosDG.setOpaque(false);

        jLabel2.setText("Datos del Proveedor:");

        jLabel3.setText("Numero:");

        txtNUMERO.setBackground(new java.awt.Color(204, 204, 204));
        txtNUMERO.setForeground(new java.awt.Color(0, 0, 0));
        txtNUMERO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNUMERO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNUMEROMouseClicked(evt);
            }
        });
        txtNUMERO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNUMEROActionPerformed(evt);
            }
        });

        btnBUSQUEDA.setBackground(new java.awt.Color(255, 152, 36));
        btnBUSQUEDA.setForeground(new java.awt.Color(0, 0, 0));
        btnBUSQUEDA.setText("Busqueda");
        btnBUSQUEDA.setBorderPainted(false);
        btnBUSQUEDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBUSQUEDAActionPerformed(evt);
            }
        });

        txtCORTO.setBackground(new java.awt.Color(204, 204, 204));
        txtCORTO.setForeground(new java.awt.Color(0, 0, 0));
        txtCORTO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCORTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCORTOActionPerformed(evt);
            }
        });

        txtNOMBRE.setBackground(new java.awt.Color(204, 204, 204));
        txtNOMBRE.setForeground(new java.awt.Color(0, 0, 0));
        txtNOMBRE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNOMBRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNOMBREActionPerformed(evt);
            }
        });

        txtCALLE.setBackground(new java.awt.Color(204, 204, 204));
        txtCALLE.setForeground(new java.awt.Color(0, 0, 0));
        txtCALLE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCALLE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCALLEActionPerformed(evt);
            }
        });

        jLabel4.setText("Corto");

        jLabel5.setText("Nombre");

        jLabel6.setText("Calle");

        jLabel7.setText("Colonia");

        txtCOLONIA.setBackground(new java.awt.Color(204, 204, 204));
        txtCOLONIA.setForeground(new java.awt.Color(0, 0, 0));
        txtCOLONIA.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCOLONIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCOLONIAActionPerformed(evt);
            }
        });

        txtPOBLACION.setBackground(new java.awt.Color(204, 204, 204));
        txtPOBLACION.setForeground(new java.awt.Color(0, 0, 0));
        txtPOBLACION.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtPOBLACION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPOBLACIONActionPerformed(evt);
            }
        });

        txtRFC.setBackground(new java.awt.Color(204, 204, 204));
        txtRFC.setForeground(new java.awt.Color(0, 0, 0));
        txtRFC.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtRFC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRFCActionPerformed(evt);
            }
        });

        txtTELEFONO.setBackground(new java.awt.Color(204, 204, 204));
        txtTELEFONO.setForeground(new java.awt.Color(0, 0, 0));
        txtTELEFONO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTELEFONO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTELEFONOActionPerformed(evt);
            }
        });

        jLabel8.setText("Poblacion");

        jLabel9.setText("RFC");

        jLabel10.setText("Telefono");

        jLabel11.setText("E-Mail");

        txtEMAIL.setBackground(new java.awt.Color(204, 204, 204));
        txtEMAIL.setForeground(new java.awt.Color(0, 0, 0));
        txtEMAIL.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtEMAIL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEMAILActionPerformed(evt);
            }
        });

        jLabel12.setText("Descuento");

        txtDESCUENTO.setBackground(new java.awt.Color(204, 204, 204));
        txtDESCUENTO.setForeground(new java.awt.Color(0, 0, 0));
        txtDESCUENTO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtDESCUENTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDESCUENTOActionPerformed(evt);
            }
        });

        jLabel13.setText("Saldo:");

        txtSALDO.setBackground(new java.awt.Color(204, 204, 204));
        txtSALDO.setForeground(new java.awt.Color(0, 0, 0));
        txtSALDO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel14.setText("Ultimo Movimiento:");

        txtULTIMO_MOVIMIENTO.setBackground(new java.awt.Color(204, 204, 204));
        txtULTIMO_MOVIMIENTO.setForeground(new java.awt.Color(0, 0, 0));
        txtULTIMO_MOVIMIENTO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnELIMINAR.setBackground(new java.awt.Color(255, 152, 36));
        btnELIMINAR.setForeground(new java.awt.Color(0, 0, 0));
        btnELIMINAR.setText("Eliminar");
        btnELIMINAR.setBorderPainted(false);
        btnELIMINAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnELIMINARActionPerformed(evt);
            }
        });

        btnGUARDAR.setBackground(new java.awt.Color(255, 152, 36));
        btnGUARDAR.setForeground(new java.awt.Color(0, 0, 0));
        btnGUARDAR.setText("Guardar");
        btnGUARDAR.setBorderPainted(false);
        btnGUARDAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGUARDARActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAgregarDatosDGLayout = new javax.swing.GroupLayout(PanelAgregarDatosDG);
        PanelAgregarDatosDG.setLayout(PanelAgregarDatosDGLayout);
        PanelAgregarDatosDGLayout.setHorizontalGroup(
            PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                .addGroup(PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                                .addGroup(PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                                        .addComponent(txtNUMERO, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(btnBUSQUEDA, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jLabel3)))
                                .addGap(21, 21, 21)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txtCORTO, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(txtCALLE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAgregarDatosDGLayout.createSequentialGroup()
                                .addGroup(PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCOLONIA, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPOBLACION))
                                    .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                                        .addGroup(PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                                                .addComponent(jLabel11)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtEMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel12)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtDESCUENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                                                .addComponent(jLabel13)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtSALDO, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel14)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtULTIMO_MOVIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addGroup(PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                                        .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTELEFONO, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                                        .addComponent(btnELIMINAR, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnGUARDAR, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)))))))
                .addContainerGap())
        );
        PanelAgregarDatosDGLayout.setVerticalGroup(
            PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgregarDatosDGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNUMERO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBUSQUEDA)
                    .addComponent(txtCORTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCALLE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCOLONIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPOBLACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRFC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTELEFONO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtEMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtDESCUENTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelAgregarDatosDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtSALDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txtULTIMO_MOVIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnELIMINAR)
                    .addComponent(btnGUARDAR))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelBotonesDG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelBotonesDG.setOpaque(false);

        btnMOVIMIENTOS.setBackground(new java.awt.Color(255, 152, 36));
        btnMOVIMIENTOS.setForeground(new java.awt.Color(0, 0, 0));
        btnMOVIMIENTOS.setText("Movimientos");
        btnMOVIMIENTOS.setBorderPainted(false);
        btnMOVIMIENTOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMOVIMIENTOSActionPerformed(evt);
            }
        });

        btnIMPRIMIR_EDO_CTA.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_EDO_CTA.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR_EDO_CTA.setText("Imprimir Edo. Cta");
        btnIMPRIMIR_EDO_CTA.setBorderPainted(false);

        btnCORREO.setBackground(new java.awt.Color(255, 152, 36));
        btnCORREO.setForeground(new java.awt.Color(0, 0, 0));
        btnCORREO.setText("Enviar por Correo");
        btnCORREO.setBorderPainted(false);

        javax.swing.GroupLayout PanelBotonesDGLayout = new javax.swing.GroupLayout(PanelBotonesDG);
        PanelBotonesDG.setLayout(PanelBotonesDGLayout);
        PanelBotonesDGLayout.setHorizontalGroup(
            PanelBotonesDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBotonesDGLayout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(PanelBotonesDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCORREO, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIMPRIMIR_EDO_CTA, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMOVIMIENTOS, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );
        PanelBotonesDGLayout.setVerticalGroup(
            PanelBotonesDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBotonesDGLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(btnMOVIMIENTOS)
                .addGap(75, 75, 75)
                .addComponent(btnIMPRIMIR_EDO_CTA)
                .addGap(79, 79, 79)
                .addComponent(btnCORREO)
                .addContainerGap(87, Short.MAX_VALUE))
        );

        PanelTablaDG.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelTablaDG.setOpaque(false);

        TablaProveedores.setBackground(new java.awt.Color(204, 204, 204));
        TablaProveedores.setForeground(new java.awt.Color(0, 0, 0));
        TablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Concepto", "Cargos", "Creditos", "Saldo"
            }
        ));
        jScrollPane1.setViewportView(TablaProveedores);

        javax.swing.GroupLayout PanelTablaDGLayout = new javax.swing.GroupLayout(PanelTablaDG);
        PanelTablaDG.setLayout(PanelTablaDGLayout);
        PanelTablaDGLayout.setHorizontalGroup(
            PanelTablaDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        PanelTablaDGLayout.setVerticalGroup(
            PanelTablaDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelPrincipalDGLayout = new javax.swing.GroupLayout(PanelPrincipalDG);
        PanelPrincipalDG.setLayout(PanelPrincipalDGLayout);
        PanelPrincipalDGLayout.setHorizontalGroup(
            PanelPrincipalDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSuperiorDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalDGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPrincipalDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelAgregarDatosDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelPrincipalDGLayout.createSequentialGroup()
                        .addComponent(PanelTablaDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(PanelBotonesDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelPrincipalDGLayout.setVerticalGroup(
            PanelPrincipalDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalDGLayout.createSequentialGroup()
                .addComponent(PanelSuperiorDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelAgregarDatosDG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPrincipalDGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelBotonesDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTablaDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        PanelPrincipalRR.setOpaque(false);

        PanelSuperior.setOpaque(false);

        jLabel15.setText("PROVEEDOR");

        jSeparator2.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));

        btnMostraraDatosGeneral.setBackground(new java.awt.Color(255, 152, 36));
        btnMostraraDatosGeneral.setForeground(new java.awt.Color(0, 0, 0));
        btnMostraraDatosGeneral.setText("Datos General y Estado de Cuenta");
        btnMostraraDatosGeneral.setBorderPainted(false);
        btnMostraraDatosGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostraraDatosGeneralActionPerformed(evt);
            }
        });

        btnMostrarRecepcionRevistas1.setBackground(new java.awt.Color(255, 152, 36));
        btnMostrarRecepcionRevistas1.setForeground(new java.awt.Color(0, 0, 0));
        btnMostrarRecepcionRevistas1.setText("Recepcion de Revistas");
        btnMostrarRecepcionRevistas1.setBorderPainted(false);
        btnMostrarRecepcionRevistas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarRecepcionRevistas1ActionPerformed(evt);
            }
        });

        btnVolverArchivoProveedores1.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverArchivoProveedores1.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverArchivoProveedores1.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverArchivoProveedores1.setText("VOLVER");
        btnVolverArchivoProveedores1.setBorderPainted(false);
        btnVolverArchivoProveedores1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverArchivoProveedores1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSuperiorLayout = new javax.swing.GroupLayout(PanelSuperior);
        PanelSuperior.setLayout(PanelSuperiorLayout);
        PanelSuperiorLayout.setHorizontalGroup(
            PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(PanelSuperiorLayout.createSequentialGroup()
                        .addComponent(btnVolverArchivoProveedores1)
                        .addGap(511, 511, 511)
                        .addComponent(jLabel15)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelSuperiorLayout.createSequentialGroup()
                        .addComponent(btnMostraraDatosGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(btnMostrarRecepcionRevistas1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelSuperiorLayout.setVerticalGroup(
            PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelSuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverArchivoProveedores1)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMostraraDatosGeneral)
                    .addComponent(btnMostrarRecepcionRevistas1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelDatosProveedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelDatosProveedor.setOpaque(false);

        jLabel16.setText("Datos del pedido");

        jLabel17.setText("Referencia:");

        txtTITULO.setBackground(new java.awt.Color(204, 204, 204));
        txtTITULO.setForeground(new java.awt.Color(0, 0, 0));
        txtTITULO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTITULO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTITULOActionPerformed(evt);
            }
        });

        jLabel18.setText("Fecha:");

        jLabel19.setText("Folio del Movimiento");

        txtFOLIO_MOVIMIENTO.setBackground(new java.awt.Color(204, 204, 204));
        txtFOLIO_MOVIMIENTO.setForeground(new java.awt.Color(0, 0, 0));
        txtFOLIO_MOVIMIENTO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtFOLIO_MOVIMIENTO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFOLIO_MOVIMIENTOActionPerformed(evt);
            }
        });

        jLabel20.setText("Recepcion de Revistas del Proveedor");

        txtRECEPCION_REVISTAS.setBackground(new java.awt.Color(204, 204, 204));
        txtRECEPCION_REVISTAS.setForeground(new java.awt.Color(0, 0, 0));
        txtRECEPCION_REVISTAS.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel21.setText("Titulo");

        txtREFERENCIA.setBackground(new java.awt.Color(204, 204, 204));
        txtREFERENCIA.setForeground(new java.awt.Color(0, 0, 0));
        txtREFERENCIA.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtREFERENCIA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtREFERENCIAActionPerformed(evt);
            }
        });

        btnBUSCAR.setBackground(new java.awt.Color(255, 152, 36));
        btnBUSCAR.setForeground(new java.awt.Color(0, 0, 0));
        btnBUSCAR.setText("Buscar");
        btnBUSCAR.setBorderPainted(false);
        btnBUSCAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBUSCARActionPerformed(evt);
            }
        });

        jLabel22.setText("Edicion");

        txtEDICION.setBackground(new java.awt.Color(204, 204, 204));
        txtEDICION.setForeground(new java.awt.Color(0, 0, 0));
        txtEDICION.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtEDICION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEDICIONActionPerformed(evt);
            }
        });

        jLabel23.setText("Cantidad");

        jLabel24.setText("Precio");

        jLabel25.setText("Clave para Cuadro");

        jLabel26.setText("Recepcion");

        jLabel27.setText("Devolucion");

        txtCANTIDAD.setBackground(new java.awt.Color(204, 204, 204));
        txtCANTIDAD.setForeground(new java.awt.Color(0, 0, 0));
        txtCANTIDAD.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCANTIDAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCANTIDADActionPerformed(evt);
            }
        });

        txtPRECIO.setBackground(new java.awt.Color(204, 204, 204));
        txtPRECIO.setForeground(new java.awt.Color(0, 0, 0));
        txtPRECIO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtCLAVE_CUADRO.setBackground(new java.awt.Color(204, 204, 204));
        txtCLAVE_CUADRO.setForeground(new java.awt.Color(0, 0, 0));
        txtCLAVE_CUADRO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtIMPORTE.setBackground(new java.awt.Color(204, 204, 204));
        txtIMPORTE.setForeground(new java.awt.Color(0, 0, 0));
        txtIMPORTE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnAGREGAR.setBackground(new java.awt.Color(255, 152, 36));
        btnAGREGAR.setForeground(new java.awt.Color(0, 0, 0));
        btnAGREGAR.setText("Agregar");
        btnAGREGAR.setBorderPainted(false);
        btnAGREGAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAGREGARActionPerformed(evt);
            }
        });

        jDateChooser1.setBackground(new java.awt.Color(204, 204, 204));
        jDateChooser1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel32.setText("Propuesta:");

        txtPROPUESTA.setBackground(new java.awt.Color(204, 204, 204));
        txtPROPUESTA.setBorder(null);

        javax.swing.GroupLayout PanelDatosProveedorLayout = new javax.swing.GroupLayout(PanelDatosProveedor);
        PanelDatosProveedor.setLayout(PanelDatosProveedorLayout);
        PanelDatosProveedorLayout.setHorizontalGroup(
            PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(510, 510, 510)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20)
                        .addGap(97, 97, 97))
                    .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                        .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(30, 30, 30)
                                .addComponent(txtREFERENCIA, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel21))
                            .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPROPUESTA, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(txtCANTIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDatosProveedorLayout.createSequentialGroup()
                                        .addComponent(txtPRECIO, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCLAVE_CUADRO, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDatosProveedorLayout.createSequentialGroup()
                                        .addComponent(txtIMPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAGREGAR, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PanelDatosProveedorLayout.createSequentialGroup()
                                .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel18)
                                        .addGap(26, 26, 26)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel22)
                                        .addGap(67, 67, 67)
                                        .addComponent(jLabel23)))
                                .addGap(30, 30, 30)
                                .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel25)
                                        .addGap(137, 137, 137)
                                        .addComponent(jLabel26)
                                        .addGap(142, 142, 142)
                                        .addComponent(jLabel27)
                                        .addGap(87, 87, 87))
                                    .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                                        .addComponent(txtFOLIO_MOVIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(49, 49, 49)
                                        .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                                .addContainerGap())
                                            .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                                                .addComponent(txtRECEPCION_REVISTAS)
                                                .addGap(55, 55, 55))))))))
                    .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                        .addComponent(txtTITULO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBUSCAR, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtEDICION, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(922, Short.MAX_VALUE))))
        );
        PanelDatosProveedorLayout.setVerticalGroup(
            PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosProveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(jLabel18)
                        .addComponent(txtFOLIO_MOVIMIENTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtRECEPCION_REVISTAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtREFERENCIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(jLabel22)
                        .addComponent(jLabel24)
                        .addComponent(jLabel25)
                        .addComponent(jLabel26)
                        .addComponent(jLabel27)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTITULO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBUSCAR)
                        .addComponent(txtEDICION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCANTIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPRECIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCLAVE_CUADRO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAGREGAR)
                    .addGroup(PanelDatosProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(txtPROPUESTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtIMPORTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelResultados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelResultados.setOpaque(false);

        jLabel29.setText("Total:");

        txtTOTAL.setBackground(new java.awt.Color(204, 204, 204));
        txtTOTAL.setForeground(new java.awt.Color(0, 0, 0));
        txtTOTAL.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel30.setText("Descuento:");

        jLabel31.setText("Sub-Total:");

        txtDESCUENTO1.setBackground(new java.awt.Color(204, 204, 204));
        txtDESCUENTO1.setForeground(new java.awt.Color(0, 0, 0));
        txtDESCUENTO1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtSUBTOTAL.setBackground(new java.awt.Color(204, 204, 204));
        txtSUBTOTAL.setForeground(new java.awt.Color(0, 0, 0));
        txtSUBTOTAL.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnAPLICAR_COMPRA.setBackground(new java.awt.Color(255, 152, 36));
        btnAPLICAR_COMPRA.setForeground(new java.awt.Color(0, 0, 0));
        btnAPLICAR_COMPRA.setText("Aplicar Compra");
        btnAPLICAR_COMPRA.setBorderPainted(false);
        btnAPLICAR_COMPRA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAPLICAR_COMPRAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelResultadosLayout = new javax.swing.GroupLayout(PanelResultados);
        PanelResultados.setLayout(PanelResultadosLayout);
        PanelResultadosLayout.setHorizontalGroup(
            PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAPLICAR_COMPRA, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel30)
                    .addComponent(jLabel29)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSUBTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDESCUENTO1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        PanelResultadosLayout.setVerticalGroup(
            PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtSUBTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtDESCUENTO1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAPLICAR_COMPRA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtTOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PanelTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setForeground(new java.awt.Color(0, 0, 0));

        TablaProveedores1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Descripcion", "Edicion", "Cantidad", "P.U", "Importe", "Clave para Cuadro", "Recepcion", "Devolucion"
            }
        ));
        jScrollPane2.setViewportView(TablaProveedores1);
        if (TablaProveedores1.getColumnModel().getColumnCount() > 0) {
            TablaProveedores1.getColumnModel().getColumn(0).setPreferredWidth(6);
            TablaProveedores1.getColumnModel().getColumn(1).setPreferredWidth(150);
            TablaProveedores1.getColumnModel().getColumn(4).setPreferredWidth(5);
            TablaProveedores1.getColumnModel().getColumn(5).setPreferredWidth(5);
            TablaProveedores1.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        javax.swing.GroupLayout PanelTablaLayout = new javax.swing.GroupLayout(PanelTabla);
        PanelTabla.setLayout(PanelTablaLayout);
        PanelTablaLayout.setHorizontalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        PanelTablaLayout.setVerticalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelPrincipalRRLayout = new javax.swing.GroupLayout(PanelPrincipalRR);
        PanelPrincipalRR.setLayout(PanelPrincipalRRLayout);
        PanelPrincipalRRLayout.setHorizontalGroup(
            PanelPrincipalRRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPrincipalRRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPrincipalRRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelDatosProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalRRLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        PanelPrincipalRRLayout.setVerticalGroup(
            PanelPrincipalRRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalRRLayout.createSequentialGroup()
                .addComponent(PanelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelDatosProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipalDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalRR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipalDG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalRR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*-------------------------Metodos generados para diferentes propositos----------------------*/
    private void Limpiar(){
        txtCORTO.setText("");
        txtNOMBRE.setText("");
        txtCALLE.setText("");
        txtCOLONIA.setText("");
        txtPOBLACION.setText("");
        txtTELEFONO.setText("");
        txtRFC.setText("");
        txtEMAIL.setText("");
        txtDESCUENTO.setText("");
        txtSALDO.setText("");
    }
    private void BUSCAR(){
        if(txtNUMERO.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un numero de Proveedor");
            txtNUMERO.requestFocus();
            Limpiar();
            Condicional = 0; 
            btnGUARDAR.setEnabled(false);
            btnELIMINAR.setEnabled(false);
        }
        else{
            btnGUARDAR.setEnabled(true);
            btnELIMINAR.setEnabled(true);
            int N = Integer.parseInt(txtNUMERO.getText());
            String Num = OBID.IDProveedor(N);
            try{
              Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
              Statement smnt = Con.createStatement();
              ResultSet rs = smnt.executeQuery("SELECT * FROM Proveedores WHERE Nro_Proveedor="+Num+";");
              if(rs.next()){
                  
                    txtNUMERO.setText(rs.getString("Nro_Proveedor"));
                    txtCORTO.setText(rs.getString("Corto"));
                    txtNOMBRE.setText(rs.getString("Nombre"));
                    txtCALLE.setText(rs.getString("Calle"));
                    txtCOLONIA.setText(rs.getString("Colonia"));
                    txtPOBLACION.setText(rs.getString("Poblacion"));
                    txtTELEFONO.setText(rs.getString("Telefono"));
                    txtRFC.setText(rs.getString("RFC"));
                    txtEMAIL.setText(rs.getString("EMAIL"));
                    txtDESCUENTO.setText(rs.getString("Descuento_P"));
                    txtSALDO.setText(rs.getString("Saldo"));
                    Condicional = 1; 
                }else
                {
                    txtCORTO.requestFocus();
                    Limpiar();
                    Condicional = 0; 
                }
            } catch (SQLException ex) {
                System.out.print("Error en consulta: " + ex);
            }
        }
    }
    
    private void LimpiarV(){
        txtEDICION.setText("");
        txtTITULO.setText("");
        txtCANTIDAD.setText("");
        txtPRECIO.setText("");
        txtCLAVE_CUADRO.setText("");
        txtIMPORTE.setText("");
        txtPROPUESTA.setText("");
    }
    
    public void EstadoCuenta(){
        DefaultTableModel model = (DefaultTableModel) TablaProveedores.getModel();
        int N = Integer.parseInt(txtNUMERO.getText());
        String NroP = OBID.IDProveedor(N);
        model.setRowCount(0);
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM estado_cuentaproveedores WHERE id_proveedor = "+NroP+";");
            while(rs.next()){
                String Fecha = rs.getString("Fecha");
                String Concepto = rs.getString("Concepto");
                String Cargo = rs.getString("Cargo");
                model.addRow(new Object[]{Fecha, Concepto, Cargo});
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar Estado de cuenta: "+ex);
            }
    }
    /*Esta funcion jala los datos de la pantalla mostrar proveedor*/
    public void Buscar_EstadoCuenta(){
        int Num = ArchivoProveedoresMostrar.CodigoP;
            try{
              Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
              Statement smnt = Con.createStatement();
              ResultSet rs = smnt.executeQuery("SELECT * FROM Proveedores WHERE Nro_Proveedor="+Num+";");
              if(rs.next()){
                    txtNUMERO.setText(rs.getString("Nro_Proveedor"));
                    txtCORTO.setText(rs.getString("Corto"));
                    txtNOMBRE.setText(rs.getString("Nombre"));
                    txtCALLE.setText(rs.getString("Calle"));
                    txtCOLONIA.setText(rs.getString("Colonia"));
                    txtPOBLACION.setText(rs.getString("Poblacion"));
                    txtTELEFONO.setText(rs.getString("Telefono"));
                    txtRFC.setText(rs.getString("RFC"));
                    txtEMAIL.setText(rs.getString("EMAIL"));
                    txtDESCUENTO.setText(rs.getString("Descuento_P"));
                    txtSALDO.setText(rs.getString("Saldo"));
                    Condicional = 1; 
                }else
                {
                    txtCORTO.requestFocus();
                    Limpiar();
                    Condicional = 0; 
                }
            } catch (SQLException ex) {
                System.out.print("Error en consulta: " + ex);
            }
     /*--------------------Aqui llama el estado de cuenta----------------------------------*/
        DefaultTableModel model = (DefaultTableModel) TablaProveedores.getModel();
        model.setRowCount(0);
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM estado_cuentaproveedores WHERE id_proveedor = "+Num+";");
            while(rs.next()){
                String Fecha = rs.getString("Fecha");
                String Concepto = rs.getString("Concepto");
                String Cargo = rs.getString("Cargo");
                model.addRow(new Object[]{Fecha, Concepto, Cargo});
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar Estado de cuenta: "+ex);
            }
    }
/*----------------------------Metodos generados por NetBeans---------------------------------*/
    private void btnVolverArchivoProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverArchivoProveedoresActionPerformed
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverArchivoProveedoresActionPerformed

    private void btnMostrarRecepcionRevistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarRecepcionRevistasActionPerformed
        PanelPrincipalRR.setVisible(true);
        PanelPrincipalDG.setVisible(false);
        GenerarFolio();
        txtREFERENCIA.requestFocus();
        
        String Fecha;
        if(Principal.FechaAjustada.isEmpty()){
            Date fechaActual = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Fecha = formatter.format(fechaActual);
            jDateChooser1.setDate(fechaActual);
            jDateChooser2.setDate(fechaActual);
            
            Date fechaSeleccionada = jDateChooser1.getDate();
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaSeleccionada);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            
            jDateChooser3.setDate(calendar.getTime());
        }else{
            Fecha = Principal.FechaAjustada;
            jDateChooser1.setDate(Principal.Fech);
            jDateChooser2.setDate(Principal.Fech);
            
            Date fechaSeleccionada = jDateChooser1.getDate();
            
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaSeleccionada);
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            
            jDateChooser3.setDate(calendar.getTime());
        } 
    }//GEN-LAST:event_btnMostrarRecepcionRevistasActionPerformed

    private void txtNUMEROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNUMEROActionPerformed
        BUSCAR();
        EstadoCuenta();
    }//GEN-LAST:event_txtNUMEROActionPerformed

    private void txtCORTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCORTOActionPerformed
        txtNOMBRE.requestFocus();
    }//GEN-LAST:event_txtCORTOActionPerformed

    private void txtNOMBREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNOMBREActionPerformed
        txtCALLE.requestFocus();
    }//GEN-LAST:event_txtNOMBREActionPerformed

    private void txtCALLEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCALLEActionPerformed
        txtCOLONIA.requestFocus();
    }//GEN-LAST:event_txtCALLEActionPerformed

    private void txtCOLONIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCOLONIAActionPerformed
        txtPOBLACION.requestFocus();
    }//GEN-LAST:event_txtCOLONIAActionPerformed

    private void txtPOBLACIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPOBLACIONActionPerformed
        txtRFC.requestFocus();
    }//GEN-LAST:event_txtPOBLACIONActionPerformed

    private void txtRFCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRFCActionPerformed
        txtTELEFONO.requestFocus();
    }//GEN-LAST:event_txtRFCActionPerformed

    private void txtTELEFONOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTELEFONOActionPerformed
        txtEMAIL.requestFocus();
    }//GEN-LAST:event_txtTELEFONOActionPerformed

    private void txtEMAILActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEMAILActionPerformed
        txtDESCUENTO.requestFocus();
    }//GEN-LAST:event_txtEMAILActionPerformed

    private void btnMostraraDatosGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostraraDatosGeneralActionPerformed
        PanelPrincipalRR.setVisible(false);
        PanelPrincipalDG.setVisible(true);
        LimpiarV();
        txtREFERENCIA.setText("");
        DefaultTableModel model = (DefaultTableModel) TablaProveedores1.getModel();
        model.setRowCount(0);
        txtSUBTOTAL.setText("");
        txtDESCUENTO1.setText("");
        txtTOTAL.setText("");
        txtFOLIO_MOVIMIENTO.setText("");
    }//GEN-LAST:event_btnMostraraDatosGeneralActionPerformed

    private void btnVolverArchivoProveedores1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverArchivoProveedores1ActionPerformed
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverArchivoProveedores1ActionPerformed

    private void btnBUSQUEDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBUSQUEDAActionPerformed
        ArchivoProveedoresMostrar PM = new ArchivoProveedoresMostrar(this);
        PM.setVisible(true);
        PM.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnBUSQUEDAActionPerformed

    private void txtREFERENCIAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtREFERENCIAActionPerformed
        txtTITULO.requestFocus();
    }//GEN-LAST:event_txtREFERENCIAActionPerformed

    private void txtFOLIO_MOVIMIENTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFOLIO_MOVIMIENTOActionPerformed
        txtRECEPCION_REVISTAS.requestFocus();
    }//GEN-LAST:event_txtFOLIO_MOVIMIENTOActionPerformed

    private void btnGUARDARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGUARDARActionPerformed
        Guardar_Actualizar();
    }//GEN-LAST:event_btnGUARDARActionPerformed

    private void btnELIMINARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnELIMINARActionPerformed
        /*Verificamos si el cuadro de */
        if(txtNUMERO.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un cliente");
        }
        else{
            try{
            int DNumero = Integer.parseInt(txtNUMERO.getText());
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            smnt.executeUpdate("DELETE FROM proveedores WHERE Nro_Proveedor = "+DNumero+";;");
            
            Limpiar();
            txtNUMERO.setText("");
            txtNUMERO.requestFocus();
            JOptionPane.showMessageDialog(null, "Datos eliminados");
            btnGUARDAR.setEnabled(false);
            btnELIMINAR.setEnabled(false);
            Con.close(); smnt.close();
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            }
        }
    }//GEN-LAST:event_btnELIMINARActionPerformed

    private void btnBUSCARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBUSCARActionPerformed
        // TODO add your handling code here:
        /*ArchivoTitulosMostrar TM = new ArchivoTitulosMostrar();
        TM.setVisible(true);
        TM.setLocationRelativeTo(null);*/
    }//GEN-LAST:event_btnBUSCARActionPerformed

    private void txtTITULOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTITULOActionPerformed
        // TODO add your handling code here:
        int Edicion; Double Importe; Double precio; int cantidad; Date Fecha = jDateChooser1.getDate();
        if(txtTITULO.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un codigo");
            txtTITULO.requestFocus();
        }else{    
        String Cod = String.valueOf(txtTITULO.getText());
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM titulos WHERE Codigo = '"+Cod+"';");
            Statement smnt1 = Con.createStatement();
            ResultSet rs1 = smnt1.executeQuery("SELECT SUM("+Cod+") AS total FROM clientes;");
            if(rs.next()){
                Nro_Prov =rs.getInt("CodigoProveedor");
                Nom_Prov = rs.getString("Proveedor");
                DescProv = rs.getDouble("DescuentoProveedor");
                txtTITULO.setText(rs.getString("Codigo"));
                txtPRECIO.setText(rs.getString("Precio"));
                txtCLAVE_CUADRO.setText(rs.getString("Lista"));
                N = rs.getString("Nombre");
                
                while(rs1.next()){
                    txtPROPUESTA.setText(rs1.getString("total"));   
                }
                Condicional = 1;
            }else
            {
                txtNOMBRE.requestFocus();
                Limpiar();
            }
            Con.close(); smnt.close(); rs.close(); smnt1.close(); rs1.close();
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
        }
        String CodigoE = generarCodigo(Fecha);
        txtEDICION.setText(CodigoE);
        jDateChooser2.setDate(Fecha);
        txtCANTIDAD.requestFocus();
        Date fechaSeleccionada = jDateChooser1.getDate();
        jDateChooser2.setDate(fechaSeleccionada); 
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaSeleccionada);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
            
        jDateChooser3.setDate(calendar.getTime());
    }//GEN-LAST:event_txtTITULOActionPerformed

    private void txtEDICIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEDICIONActionPerformed
        // TODO add your handling code here:
        txtCANTIDAD.requestFocus();
    }//GEN-LAST:event_txtEDICIONActionPerformed

    private void btnAGREGARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAGREGARActionPerformed
        BotonAgregar();
    }//GEN-LAST:event_btnAGREGARActionPerformed

    private void btnAPLICAR_COMPRAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAPLICAR_COMPRAActionPerformed
        //Declaramos variables y modelo de tabla
        String FechaR = ""; int Folio=0; String FechaD="";
        DefaultTableModel model = (DefaultTableModel) TablaProveedores1.getModel();
        String Concepto = "Venta"; Double Cargo = Double.parseDouble(txtTOTAL.getText());
        int Num_P =0; String Nom_P ="";
        //Condicion que detecta si hay datos en la tabla
        if(model.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Agregue un titulo a la compra");
        }else
        {
            //Se genera la coneccion con la base de datos
            try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            Connection Con1 = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt1 = Con1.createStatement();
            Connection Con2 = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt2 = Con2.createStatement();    
            
            //Creamos un bucle for para obtener los datos de la tabla
            for(int i = 0; i < model.getRowCount(); i++){
                int FolioC = Integer.parseInt(txtFOLIO_MOVIMIENTO.getText());
                String Titulo = model.getValueAt(i, 1).toString();
                String Codigo = model.getValueAt(i, 0).toString();
                String Edicion = model.getValueAt(i, 2).toString();
                int Cantidad = Integer.parseInt(model.getValueAt(i, 3).toString());
                double Precio = Double.parseDouble(model.getValueAt(i, 4).toString());
                double Importe = Double.parseDouble(model.getValueAt(i, 5).toString());
                int Clave = Integer.parseInt(model.getValueAt(i, 6).toString());
                
                String fechaOriginal = model.getValueAt(i, 7).toString();
                SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatoSalida = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date fecha = formatoEntrada.parse(fechaOriginal);
                    FechaR= formatoSalida.format(fecha);
                } catch (Exception e) {
                    e.printStackTrace(); // Manejo de excepción en caso de error
                }
                
                String FechaDevolucion = model.getValueAt(i, 8).toString();
                SimpleDateFormat formatoEntrada1 = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat formatoSalida1 = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date fecha1 = formatoEntrada1.parse(FechaDevolucion);
                    FechaD= formatoSalida1.format(fecha1);
                } catch (Exception e) {
                    e.printStackTrace(); // Manejo de excepción en caso de error
                }
                double Total = Double.parseDouble(txtTOTAL.getText());
                double DescCompra = Double.parseDouble(txtDESCUENTO1.getText());
                
                //Realizamos el select de los datos del proveedor
                ResultSet rs = smnt.executeQuery("SELECT CodigoProveedor, Proveedor FROM Titulos WHERE Codigo = '"+Codigo+"';");
                if(rs.next()){
                    Nom_P = rs.getString("Proveedor");
                    Num_P = rs.getInt("CodigoProveedor");
                }
                //Realizamos el insert en la tabla compras proveedor
                smnt.executeUpdate("INSERT INTO compras_proveedor (FolioCompra, Nro_Proveedor, NombreProveedor, Titulo, CodigoTitulo, Edicion, Cantidad, Precio, Importe, Clave, FechaRecibido, TotalCompra, DescCompra, FechaDevolucion) "
                        + "VALUES ("+FolioC+", "+Num_P+", '"+Nom_P+"', '"+Titulo+"', "
                        + "'"+Codigo+"', '"+Edicion+"', "+Cantidad+", "+Precio+", "+Importe+", "
                        + ""+Clave+", '"+FechaR+"', "+Total+", "+DescCompra+", '"+FechaD+"');");
                
                //Realizamos el insert en la tabla estado de cuenta
                smnt1.executeUpdate("INSERT INTO Estado_CuentaProveedores (id_proveedor,NombreProv, Fecha, Concepto, Cargo) "
                + "VALUES ("+Num_P+", '"+Nom_P+"', '"+FechaR+"', '"+Concepto+"', "+Importe+");");
            }
            Con.close(); smnt.close(); Con1.close(); smnt1.close(); Con2.close(); smnt2.close(); 
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al realizar la compra: "+ex);
        }
            model.setRowCount(0);
            LimpiarV();
            txtREFERENCIA.setText("");
            txtSUBTOTAL.setText("");
            txtDESCUENTO1.setText("");
            txtTOTAL.setText("");
        }
        GenerarFolio();
        txtREFERENCIA.requestFocus();
    }//GEN-LAST:event_btnAPLICAR_COMPRAActionPerformed

    private void btnMostrarRecepcionRevistas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarRecepcionRevistas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMostrarRecepcionRevistas1ActionPerformed

    private void txtDESCUENTOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDESCUENTOActionPerformed
        // TODO add your handling code here:
        Guardar_Actualizar();
    }//GEN-LAST:event_txtDESCUENTOActionPerformed

    private void txtCANTIDADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCANTIDADActionPerformed
        double Precio = Double.parseDouble(txtPRECIO.getText());
        double Cantidad = Double.parseDouble(txtCANTIDAD.getText());
        double Importe = Precio * Cantidad;
        txtIMPORTE.setText(String.valueOf(Importe));
        BotonAgregar();
    }//GEN-LAST:event_txtCANTIDADActionPerformed

    private void txtNUMEROMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNUMEROMouseClicked
        // TODO add your handling code here:
        Limpiar();
        txtNUMERO.setText("");
        txtNUMERO.requestFocus();
        DefaultTableModel model = (DefaultTableModel) TablaProveedores.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_txtNUMEROMouseClicked

    private void btnMOVIMIENTOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMOVIMIENTOSActionPerformed
        // TODO add your handling code here:
        ArchivoProveedoresMovimientos MenuA =  new ArchivoProveedoresMovimientos();
        MenuA.setVisible(true);
        MenuA.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnMOVIMIENTOSActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelAgregarDatosDG;
    private javax.swing.JPanel PanelBotonesDG;
    private javax.swing.JPanel PanelDatosProveedor;
    private javax.swing.JPanel PanelPrincipalDG;
    private javax.swing.JPanel PanelPrincipalRR;
    private javax.swing.JPanel PanelResultados;
    private javax.swing.JPanel PanelSuperior;
    private javax.swing.JPanel PanelSuperiorDG;
    private javax.swing.JPanel PanelTabla;
    private javax.swing.JPanel PanelTablaDG;
    private javax.swing.JTable TablaProveedores;
    private javax.swing.JTable TablaProveedores1;
    private javax.swing.JButton btnAGREGAR;
    private javax.swing.JButton btnAPLICAR_COMPRA;
    private javax.swing.JButton btnBUSCAR;
    private javax.swing.JButton btnBUSQUEDA;
    private javax.swing.JButton btnCORREO;
    private javax.swing.JButton btnELIMINAR;
    private javax.swing.JButton btnGUARDAR;
    private javax.swing.JButton btnIMPRIMIR_EDO_CTA;
    private javax.swing.JButton btnMOVIMIENTOS;
    private javax.swing.JButton btnMostrarDatosGeneral;
    private javax.swing.JButton btnMostrarRecepcionRevistas;
    private javax.swing.JButton btnMostrarRecepcionRevistas1;
    private javax.swing.JButton btnMostraraDatosGeneral;
    private javax.swing.JButton btnVolverArchivoProveedores;
    private javax.swing.JButton btnVolverArchivoProveedores1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
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
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtCALLE;
    private javax.swing.JTextField txtCANTIDAD;
    private javax.swing.JTextField txtCLAVE_CUADRO;
    private javax.swing.JTextField txtCOLONIA;
    private javax.swing.JTextField txtCORTO;
    private javax.swing.JTextField txtDESCUENTO;
    private javax.swing.JTextField txtDESCUENTO1;
    private javax.swing.JTextField txtEDICION;
    private javax.swing.JTextField txtEMAIL;
    private javax.swing.JTextField txtFOLIO_MOVIMIENTO;
    private javax.swing.JTextField txtIMPORTE;
    private javax.swing.JTextField txtNOMBRE;
    private javax.swing.JTextField txtNUMERO;
    private javax.swing.JTextField txtPOBLACION;
    private javax.swing.JTextField txtPRECIO;
    private javax.swing.JTextField txtPROPUESTA;
    private javax.swing.JTextField txtRECEPCION_REVISTAS;
    private javax.swing.JTextField txtREFERENCIA;
    private javax.swing.JTextField txtRFC;
    private javax.swing.JTextField txtSALDO;
    private javax.swing.JTextField txtSUBTOTAL;
    private javax.swing.JTextField txtTELEFONO;
    private javax.swing.JTextField txtTITULO;
    private javax.swing.JTextField txtTOTAL;
    private javax.swing.JTextField txtULTIMO_MOVIMIENTO;
    // End of variables declaration//GEN-END:variables
}
