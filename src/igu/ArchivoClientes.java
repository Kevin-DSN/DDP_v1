package igu;
import javax.swing.*;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.time.LocalDate;

public class ArchivoClientes extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    int Condicional = 0;
    ObtenerID Oid = new ObtenerID();
    
    public ArchivoClientes() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        this.setExtendedState(this.MAXIMIZED_BOTH);
        PanelPrincipalVenta.setVisible(false);
        PanelPrincipalRechazo.setVisible(false);
        btnGUARDAR_R.setEnabled(false);
        btnELIMINAR_R.setEnabled(false);
                SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            txtNUMERO_R.requestFocus();
        }
        });
    }
    
    /*-------------ESTA SECCION CONTIENE METODOS CREADOS POR EL USUARIO-------------------------*/
    
    /*Metodo que limpia los cuadros de texto*/    
    private void Limpiar(){
        txtNOMBRE_R.setText("");
        txtRAZON_SOCIAL_R.setText("");
        txtDIRECCION_R.setText("");
        txtPOBLACION_R.setText("");
        txtRFC_R.setText("");
        txtTELEFONO_R.setText("");
        txtEMAIL_R.setText("");
        txtDESCUENTO_R.setText("");
        txtRUTA_R.setText("");
        txtREPARTO_R.setText("");
        txtLIMITE_CREDITO_R.setText("");
        txtATRASO_R.setText("");
        txtNOTAS_R.setText("");
        txtCLIENTE_V.setText("");
        txtCLIENTE_RE.setText("");
        txtSALDO_R.setText("");
        Condicional = 0;
    }
    /*Metodo que limpia las casillas de ventas*/    
    private void LimpiaV(){
        txtTITULO_V.setText("");
        txtNOMBRE_V.setText("");
        txtEDICION_V.setText("");
        txtPRECIO_V.setText("");
        txtCANTIDAD_V.setText("");
        txtIMPORTE_V.setText("");
    }
    /*Metodo que limpia tabla de ventas*/
    private void LimpiaVT(){
        DefaultTableModel model = (DefaultTableModel) TablaDatosClientes_V.getModel();
        model.setRowCount(0);
    }
    /*Metodo que guarda y actualiza datos de los clientes*/
    private void Guardar_Actualizar(){
        /*Declaramos las variables*/
        String Nombre=""; String RazonS=""; String Direccion=""; String Poblacion=""; String RFC=""; String Telef=""; String EMAIL=""; Double Descuento=0.0; String Ruta=""; String Reparto=""; Double SaldoLmite=0.0; String Atraso=""; String Notas="";
        
        /*Asignamos los valores*/
        int Nro_C = Integer.parseInt(txtNUMERO_R.getText());
        Nombre = String.valueOf(txtNOMBRE_R.getText());
        RazonS = String.valueOf(txtRAZON_SOCIAL_R.getText());
        Direccion = String.valueOf(txtDIRECCION_R.getText());
        Poblacion = String.valueOf(txtPOBLACION_R.getText());
        RFC = String.valueOf(txtRFC_R.getText());
        Telef = String.valueOf(txtTELEFONO_R.getText());
        EMAIL = String.valueOf(txtEMAIL_R.getText());
        if(!txtDESCUENTO_R.getText().isEmpty()){
        Descuento = Double.parseDouble(txtDESCUENTO_R.getText());}
        Ruta = String.valueOf(txtRUTA_R.getText());
        Reparto = String.valueOf(txtREPARTO_R.getText());
        if(!txtLIMITE_CREDITO_R.getText().isEmpty()){
        SaldoLmite = Double.parseDouble(txtLIMITE_CREDITO_R.getText());}
        Atraso = String.valueOf(txtATRASO_R.getText());
        Notas = String.valueOf(txtNOTAS_R.getText());
        
        /*Si la condicion vale 0, quiere decir que los datos son nuevos, se hace un insert*/
        if(Condicional == 0){
            try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            smnt.executeUpdate("INSERT INTO clientes (Nro_Cliente, Nombre, RazonSocial, Direccion, Poblacion, RFC, Telefono, EMAIL, Descuento, Ruta, Reparto, LimiteCredito, Atraso, Notas, SaldoActual, SaldoLimite, Dotacion) "
            + "VALUES ("+Nro_C+",'"+Nombre+"','"+RazonS+"','"+Direccion+"','"+Poblacion+"',"
            + " '"+RFC+"', '"+Telef+"', '"+EMAIL+"', "+Descuento+", '"+Ruta+"',"
            + " '"+Reparto+"', "+SaldoLmite+", '"+Atraso+"', '"+Notas+"', "+0.0+", "+0.0+", "+0+");");
            
            Limpiar();
            txtNUMERO_R.setText("");
            txtNUMERO_R.requestFocus();
            JOptionPane.showMessageDialog(null, "Datos registrados");
            btnGUARDAR_R.setEnabled(false);
            btnELIMINAR_R.setEnabled(false);
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            }
        }
        
        /*Si la condicion vale 1, quiere decir que los datos son de un cliente que ya existe, se hace un update*/
        if(Condicional == 1){
            try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            smnt.executeUpdate("UPDATE clientes SET "
            + "Nombre = '"+Nombre+"', RazonSocial = '"+RazonS+"', Direccion = '"+Direccion+"', "
            + "Poblacion = '"+Poblacion+"', RFC = '"+RFC+"', Telefono = '"+Telef+"', EMAIL = '"+EMAIL+"', "
            + "Descuento = "+Descuento+", Ruta = '"+Ruta+"', Reparto = '"+Reparto+"', "
            + "LimiteCredito = "+SaldoLmite+", Atraso = '"+Atraso+"', Notas = '"+Notas+"' WHERE Nro_Cliente = "+Nro_C+";");
            Limpiar();
            txtNUMERO_R.setText("");
            txtNUMERO_R.requestFocus();
            JOptionPane.showMessageDialog(null, "Datos actualizados");
            btnGUARDAR_R.setEnabled(false);
            btnELIMINAR_R.setEnabled(false);
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: "+ex);
            }
        }
    }
    /*Metodo que genera el folio de venta para clientes*/
    public void GenerarFolio(){
    int Folio =0;
    try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT MAX(FolioCompra) FROM compras_cliente;");
            while(rs.next()){
                Folio = rs.getInt(1);
            }
            Con.close(); smnt.close(); rs.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al generar numero de folio: "+ex);
        }
        Folio += 1;
        txtFOLIO_VENTAS_V.setText(String.valueOf(Folio));
}
    /*Generamos una funcion que regresa al menu Archivo cuando es invocada*/
    private void Salir(){
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }
    /*Metodo que busca a los cliEntes*/    
    private void BUSCAR(){
        if(txtNUMERO_R.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un numero de cliente");
            Limpiar();
            txtNUMERO_R.setText("");
            txtNUMERO_R.requestFocus();
            btnGUARDAR_R.setEnabled(false);
            btnELIMINAR_R.setEnabled(false);
        }
        else{
        btnGUARDAR_R.setEnabled(true);
        btnELIMINAR_R.setEnabled(true);
        int N = Integer.parseInt(txtNUMERO_R.getText());      
        String Num = Oid.IDCliente(N);
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM clientes WHERE Nro_Cliente ="+Num+";");
            
            if(rs.next()){
                txtNUMERO_R.setText(rs.getString("Nro_Cliente"));
                txtNOMBRE_R.setText(rs.getString("Nombre"));
                txtRAZON_SOCIAL_R.setText(rs.getString("RazonSocial"));
                txtDIRECCION_R.setText(rs.getString("Direccion"));
                txtPOBLACION_R.setText(rs.getString("Poblacion"));
                txtRFC_R.setText(rs.getString("RFC"));
                txtTELEFONO_R.setText(rs.getString("Telefono"));
                txtEMAIL_R.setText(rs.getString("EMAIL"));
                txtDESCUENTO_R.setText(rs.getString("Descuento"));
                txtRUTA_R.setText(rs.getString("Ruta"));
                txtREPARTO_R.setText(rs.getString("Reparto"));
                txtLIMITE_CREDITO_R.setText(rs.getString("LimiteCredito"));
                txtATRASO_R.setText(rs.getString("Atraso"));
                txtNOTAS_R.setText(rs.getString("Notas"));
                txtSALDO_R.setText(rs.getString("SaldoActual"));
                Condicional = 1;
            }else
            {
                Limpiar();
                txtNOMBRE_R.requestFocus();
            }
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
        }
    }
    /*Metodo que se encarga de buscar saldo*/
    public void BuscarSaldo(){
        double Saldo=0;
        try{
            String Client = String.valueOf(txtNOMBRE_R.getText());
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT SaldoActual FROM clientes "
            + "WHERE Nombre ='"+Client+"';");
            while(rs.next()){
                Saldo=rs.getDouble("SaldoActual");
            }
            txtSALDO_V.setText(String.valueOf(Saldo));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error mostrar estado de cuenta: "+ex);
        }
    }
    /*Metodo que carga el estado de cuenta del cliente en una tabla*/
    public void EstadoCuenta(){
        DefaultTableModel model = (DefaultTableModel) tablaClientes_R.getModel();  
        int N = Integer.parseInt(txtNUMERO_R.getText()); 
        String NroP = Oid.IDCliente(N);
        model.setRowCount(0);
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM estado_cuentacliente WHERE idCliente = "+NroP+";");
            while(rs.next()){
                String Fecha = rs.getString("Fecha");
                String Concepto = rs.getString("Concepto");
                String Cargo = rs.getString("Cargo");
                String Saldos = rs.getString("Saldos");
                model.addRow(new Object[]{Fecha, Concepto, Cargo, null, Saldos});
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar Estado de cuenta: "+ex);
            }
    }
    /*Metodo que busca el estado de cuenta del cliente en una tabla*/
    public void Buscar_EstadoDeCuenta(){    
        int N = ArchivoClientessMostrar.CodigoC;  
        String Num = Oid.IDCliente(N);
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM clientes WHERE Nro_Cliente ="+Num+";");
            
            if(rs.next()){
                txtNUMERO_R.setText(rs.getString("Nro_Cliente"));
                txtNOMBRE_R.setText(rs.getString("Nombre"));
                txtRAZON_SOCIAL_R.setText(rs.getString("RazonSocial"));
                txtDIRECCION_R.setText(rs.getString("Direccion"));
                txtPOBLACION_R.setText(rs.getString("Poblacion"));
                txtRFC_R.setText(rs.getString("RFC"));
                txtTELEFONO_R.setText(rs.getString("Telefono"));
                txtEMAIL_R.setText(rs.getString("EMAIL"));
                txtDESCUENTO_R.setText(rs.getString("Descuento"));
                txtRUTA_R.setText(rs.getString("Ruta"));
                txtREPARTO_R.setText(rs.getString("Reparto"));
                txtLIMITE_CREDITO_R.setText(rs.getString("LimiteCredito"));
                txtATRASO_R.setText(rs.getString("Atraso"));
                txtNOTAS_R.setText(rs.getString("Notas"));
                txtSALDO_R.setText(rs.getString("SaldoActual"));
                Condicional = 1;
            }else
            {
                Limpiar();
                txtNOMBRE_R.requestFocus();
            }
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
        /*Cargamos el estado de cuenta*/
        DefaultTableModel model = (DefaultTableModel) tablaClientes_R.getModel();
        model.setRowCount(0);
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM estado_cuentacliente WHERE idCliente = "+Num+";");
            while(rs.next()){
                String Fecha = rs.getString("Fecha");
                String Concepto = rs.getString("Concepto");
                String Cargo = rs.getString("Cargo");
                String Saldos = rs.getString("Saldos");
                model.addRow(new Object[]{Fecha, Concepto, Cargo, null, Saldos});
            }
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar Estado de cuenta: "+ex);
            }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipalRegistro = new javax.swing.JPanel();
        PanelSuperiorRegistro = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        MostrarVentasCliente_R = new javax.swing.JButton();
        MostrarRechazosCliente_R = new javax.swing.JButton();
        MostrarRegistroClientes_R = new javax.swing.JButton();
        btnVolverArchivoCRegistro = new javax.swing.JButton();
        PanelCapturaDatosRegistro = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNUMERO_R = new javax.swing.JTextField();
        btnBUSCAR_R = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtTELEFONO_R = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtRAZON_SOCIAL_R = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDIRECCION_R = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtNOMBRE_R = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRFC_R = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPOBLACION_R = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtEMAIL_R = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDESCUENTO_R = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtRUTA_R = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtREPARTO_R = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtLIMITE_CREDITO_R = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtATRASO_R = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtNOTAS_R = new javax.swing.JTextField();
        PanelMuestraTablaRegistro = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtSALDO_R = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnMOVIMIENTOS_R = new javax.swing.JButton();
        txtULTIMO_PAGO_R = new javax.swing.JTextField();
        jScrollPane_R = new javax.swing.JScrollPane();
        tablaClientes_R = new javax.swing.JTable();
        PanelInferiorRegistro = new javax.swing.JPanel();
        btnSALIR_R = new javax.swing.JButton();
        btnGUARDAR_R = new javax.swing.JButton();
        btnIMPRIMIR_R = new javax.swing.JButton();
        btnELIMINAR_R = new javax.swing.JButton();
        PanelPrincipalVenta = new javax.swing.JPanel();
        PanelSuperiorVenta = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        MostrarVentasCliente_V = new javax.swing.JButton();
        MostrarRechazosCliente_V = new javax.swing.JButton();
        MostraraRegistroClientes_V = new javax.swing.JButton();
        btnVolverArchivoCVenta = new javax.swing.JButton();
        PanelInferiorVenta = new javax.swing.JPanel();
        btnSALIR_V = new javax.swing.JButton();
        btnGUARDAR_V = new javax.swing.JButton();
        btnIMPRIMIR_V = new javax.swing.JButton();
        btnELIMINAR_V = new javax.swing.JButton();
        PanelAgregarDatosVenta = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        txtCLIENTE_V = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtTITULO_V = new javax.swing.JTextField();
        txtNOMBRE_V = new javax.swing.JTextField();
        txtEDICION_V = new javax.swing.JTextField();
        txtPRECIO_V = new javax.swing.JTextField();
        txtCANTIDAD_V = new javax.swing.JTextField();
        txtIMPORTE_V = new javax.swing.JTextField();
        btnAGREGAR_V = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        txtEXISTENCIA_V = new javax.swing.JTextField();
        PanelResultadosVenta = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        txtSUBTOTAL_V = new javax.swing.JTextField();
        txtDESCUENTO_V = new javax.swing.JTextField();
        txtTOTAL_V = new javax.swing.JTextField();
        txtABONO_V = new javax.swing.JTextField();
        PanelOtrosResultadosVenta = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtSALDO_V = new javax.swing.JTextField();
        txtSALDO_ABONO_V = new javax.swing.JTextField();
        txtSALDO_NUEVO_V = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtFOLIO_VENTAS_V = new javax.swing.JTextField();
        btnAPLICAR_VENTAS_V = new javax.swing.JButton();
        btnIMPRIMIR_REMISION_V = new javax.swing.JButton();
        btnIMPRIMIR_ROLLO_V = new javax.swing.JButton();
        btnIMPRIMIR_FACTURA_V = new javax.swing.JButton();
        PanelTabla = new javax.swing.JPanel();
        jScrollPane_V = new javax.swing.JScrollPane();
        TablaDatosClientes_V = new javax.swing.JTable();
        PanelPrincipalRechazo = new javax.swing.JPanel();
        PanelSuperiorRechazos = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        MostrarVentasCliente_RE = new javax.swing.JButton();
        MostrarRechazosCliente_RE = new javax.swing.JButton();
        MostraraRegistroClientes_RE = new javax.swing.JButton();
        btnVolverArchivoCRechazo = new javax.swing.JButton();
        PanelInferiorRechazos = new javax.swing.JPanel();
        btnSALIR_RE = new javax.swing.JButton();
        btnGUARDAR_RE = new javax.swing.JButton();
        btnIMPRIMIR_RE = new javax.swing.JButton();
        btnELIMINAR_RE = new javax.swing.JButton();
        PanelCapturaDatosRechazos = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        txtCLIENTE_RE = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtTITULO_RE = new javax.swing.JTextField();
        txtNOMBRE_RE = new javax.swing.JTextField();
        txtEDICION_RE = new javax.swing.JTextField();
        txtPRECIO_RE = new javax.swing.JTextField();
        txtCANTIDAD_RE = new javax.swing.JTextField();
        txtIMPORTE_RE = new javax.swing.JTextField();
        btnAGREGAR_RE = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        txtCANTIDAD_ENTREGADA_RE = new javax.swing.JTextField();
        PanelResultadosRechazos = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        txtSUBTOTAL_RE = new javax.swing.JTextField();
        txtDESCUENTO_RE = new javax.swing.JTextField();
        txtTOTAL_RE = new javax.swing.JTextField();
        PanelOtrosResultadosRechazos = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        txtSALDO_ACTIVO_RE = new javax.swing.JTextField();
        txtTOTAL_RECHAZO = new javax.swing.JTextField();
        txtNUEVO_SALDO = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtFOLIO_RECHAZO_RE = new javax.swing.JTextField();
        btnAPLICAR_RECHAZO_RE = new javax.swing.JButton();
        btnIMPRIMIR_ROLLO_RE = new javax.swing.JButton();
        btnIMPRIMIR_RECHAZO_RE = new javax.swing.JButton();
        PanelTablaRechazos = new javax.swing.JPanel();
        jScrollPane_RE = new javax.swing.JScrollPane();
        tablaCLIENTE_RE = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        PanelPrincipalRegistro.setOpaque(false);

        PanelSuperiorRegistro.setOpaque(false);

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));

        jLabel1.setText("CLIENTE");

        MostrarVentasCliente_R.setBackground(new java.awt.Color(255, 152, 36));
        MostrarVentasCliente_R.setForeground(new java.awt.Color(0, 0, 0));
        MostrarVentasCliente_R.setText("Ventas a Cliente");
        MostrarVentasCliente_R.setBorderPainted(false);
        MostrarVentasCliente_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarVentasCliente_RActionPerformed(evt);
            }
        });

        MostrarRechazosCliente_R.setBackground(new java.awt.Color(255, 152, 36));
        MostrarRechazosCliente_R.setForeground(new java.awt.Color(0, 0, 0));
        MostrarRechazosCliente_R.setText("Rechazos a Cliente");
        MostrarRechazosCliente_R.setBorderPainted(false);
        MostrarRechazosCliente_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarRechazosCliente_RActionPerformed(evt);
            }
        });

        MostrarRegistroClientes_R.setBackground(new java.awt.Color(255, 152, 36));
        MostrarRegistroClientes_R.setForeground(new java.awt.Color(0, 0, 0));
        MostrarRegistroClientes_R.setText("Registro de Cliente");
        MostrarRegistroClientes_R.setBorderPainted(false);

        btnVolverArchivoCRegistro.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverArchivoCRegistro.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverArchivoCRegistro.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverArchivoCRegistro.setText("VOLVER");
        btnVolverArchivoCRegistro.setBorderPainted(false);
        btnVolverArchivoCRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverArchivoCRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSuperiorRegistroLayout = new javax.swing.GroupLayout(PanelSuperiorRegistro);
        PanelSuperiorRegistro.setLayout(PanelSuperiorRegistroLayout);
        PanelSuperiorRegistroLayout.setHorizontalGroup(
            PanelSuperiorRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorRegistroLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(PanelSuperiorRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(PanelSuperiorRegistroLayout.createSequentialGroup()
                        .addComponent(MostrarRegistroClientes_R, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MostrarVentasCliente_R, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(MostrarRechazosCliente_R, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSuperiorRegistroLayout.createSequentialGroup()
                        .addComponent(btnVolverArchivoCRegistro)
                        .addGap(531, 531, 531)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        PanelSuperiorRegistroLayout.setVerticalGroup(
            PanelSuperiorRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSuperiorRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverArchivoCRegistro)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSuperiorRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MostrarRegistroClientes_R)
                    .addComponent(MostrarVentasCliente_R)
                    .addComponent(MostrarRechazosCliente_R))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        PanelCapturaDatosRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelCapturaDatosRegistro.setOpaque(false);

        jLabel2.setText("Datos del cliente");

        jLabel3.setText("Numero");

        txtNUMERO_R.setBackground(new java.awt.Color(204, 204, 204));
        txtNUMERO_R.setForeground(new java.awt.Color(0, 0, 0));
        txtNUMERO_R.setBorder(null);
        txtNUMERO_R.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNUMERO_RMouseClicked(evt);
            }
        });
        txtNUMERO_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNUMERO_RActionPerformed(evt);
            }
        });

        btnBUSCAR_R.setBackground(new java.awt.Color(255, 152, 36));
        btnBUSCAR_R.setForeground(new java.awt.Color(0, 0, 0));
        btnBUSCAR_R.setText("Buscar");
        btnBUSCAR_R.setBorderPainted(false);
        btnBUSCAR_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBUSCAR_RActionPerformed(evt);
            }
        });

        jLabel4.setText("Nombre:");

        txtTELEFONO_R.setBackground(new java.awt.Color(204, 204, 204));
        txtTELEFONO_R.setForeground(new java.awt.Color(0, 0, 0));
        txtTELEFONO_R.setBorder(null);
        txtTELEFONO_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTELEFONO_RActionPerformed(evt);
            }
        });

        jLabel5.setText("Razon Social:");

        txtRAZON_SOCIAL_R.setBackground(new java.awt.Color(204, 204, 204));
        txtRAZON_SOCIAL_R.setForeground(new java.awt.Color(0, 0, 0));
        txtRAZON_SOCIAL_R.setBorder(null);
        txtRAZON_SOCIAL_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRAZON_SOCIAL_RActionPerformed(evt);
            }
        });

        jLabel6.setText("Direccion:");

        txtDIRECCION_R.setBackground(new java.awt.Color(204, 204, 204));
        txtDIRECCION_R.setForeground(new java.awt.Color(0, 0, 0));
        txtDIRECCION_R.setBorder(null);
        txtDIRECCION_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDIRECCION_RActionPerformed(evt);
            }
        });

        jLabel7.setText("Poblacion:");

        txtNOMBRE_R.setBackground(new java.awt.Color(204, 204, 204));
        txtNOMBRE_R.setForeground(new java.awt.Color(0, 0, 0));
        txtNOMBRE_R.setBorder(null);
        txtNOMBRE_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNOMBRE_RActionPerformed(evt);
            }
        });

        jLabel8.setText("RFC:");

        txtRFC_R.setBackground(new java.awt.Color(204, 204, 204));
        txtRFC_R.setForeground(new java.awt.Color(0, 0, 0));
        txtRFC_R.setBorder(null);
        txtRFC_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRFC_RActionPerformed(evt);
            }
        });

        jLabel9.setText("Telefono:");

        txtPOBLACION_R.setBackground(new java.awt.Color(204, 204, 204));
        txtPOBLACION_R.setForeground(new java.awt.Color(0, 0, 0));
        txtPOBLACION_R.setBorder(null);
        txtPOBLACION_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPOBLACION_RActionPerformed(evt);
            }
        });

        jLabel10.setText("E-Mail:");

        txtEMAIL_R.setBackground(new java.awt.Color(204, 204, 204));
        txtEMAIL_R.setForeground(new java.awt.Color(0, 0, 0));
        txtEMAIL_R.setBorder(null);
        txtEMAIL_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEMAIL_RActionPerformed(evt);
            }
        });

        jLabel11.setText("Descuento:");

        txtDESCUENTO_R.setBackground(new java.awt.Color(204, 204, 204));
        txtDESCUENTO_R.setForeground(new java.awt.Color(0, 0, 0));
        txtDESCUENTO_R.setBorder(null);
        txtDESCUENTO_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDESCUENTO_RActionPerformed(evt);
            }
        });

        jLabel12.setText("Ruta:");

        txtRUTA_R.setBackground(new java.awt.Color(204, 204, 204));
        txtRUTA_R.setForeground(new java.awt.Color(0, 0, 0));
        txtRUTA_R.setBorder(null);
        txtRUTA_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRUTA_RActionPerformed(evt);
            }
        });

        jLabel13.setText("Reparto:");

        txtREPARTO_R.setBackground(new java.awt.Color(204, 204, 204));
        txtREPARTO_R.setForeground(new java.awt.Color(0, 0, 0));
        txtREPARTO_R.setBorder(null);
        txtREPARTO_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtREPARTO_RActionPerformed(evt);
            }
        });

        jLabel14.setText("Limite de credito:");

        txtLIMITE_CREDITO_R.setBackground(new java.awt.Color(204, 204, 204));
        txtLIMITE_CREDITO_R.setForeground(new java.awt.Color(0, 0, 0));
        txtLIMITE_CREDITO_R.setBorder(null);

        jLabel15.setText("Atraso:");

        txtATRASO_R.setBackground(new java.awt.Color(204, 204, 204));
        txtATRASO_R.setForeground(new java.awt.Color(0, 0, 0));
        txtATRASO_R.setBorder(null);

        jLabel16.setText("Notas:");

        txtNOTAS_R.setBackground(new java.awt.Color(204, 204, 204));
        txtNOTAS_R.setForeground(new java.awt.Color(0, 0, 0));
        txtNOTAS_R.setBorder(null);

        javax.swing.GroupLayout PanelCapturaDatosRegistroLayout = new javax.swing.GroupLayout(PanelCapturaDatosRegistro);
        PanelCapturaDatosRegistro.setLayout(PanelCapturaDatosRegistroLayout);
        PanelCapturaDatosRegistroLayout.setHorizontalGroup(
            PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtNUMERO_R, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBUSCAR_R))
                    .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(txtNOMBRE_R))
                    .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtRAZON_SOCIAL_R))
                    .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtDIRECCION_R))
                    .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(txtPOBLACION_R, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtRFC_R, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(txtTELEFONO_R))
                    .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(txtEMAIL_R))
                    .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(txtDESCUENTO_R, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(txtRUTA_R, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(txtREPARTO_R, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(txtLIMITE_CREDITO_R, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(txtATRASO_R, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(txtNOTAS_R)))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        PanelCapturaDatosRegistroLayout.setVerticalGroup(
            PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCapturaDatosRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNUMERO_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btnBUSCAR_R))
                .addGap(18, 18, 18)
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNOMBRE_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtRAZON_SOCIAL_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtDIRECCION_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtRFC_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPOBLACION_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTELEFONO_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtEMAIL_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtDESCUENTO_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtRUTA_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(txtREPARTO_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtLIMITE_CREDITO_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(txtATRASO_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelCapturaDatosRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtNOTAS_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        PanelMuestraTablaRegistro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelMuestraTablaRegistro.setOpaque(false);

        jLabel17.setText("Estado de cuenta");

        jLabel18.setText("Saldo:");

        txtSALDO_R.setBackground(new java.awt.Color(204, 204, 204));
        txtSALDO_R.setForeground(new java.awt.Color(0, 0, 0));
        txtSALDO_R.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel19.setText("Ultimo pago:");

        btnMOVIMIENTOS_R.setBackground(new java.awt.Color(255, 152, 36));
        btnMOVIMIENTOS_R.setForeground(new java.awt.Color(0, 0, 0));
        btnMOVIMIENTOS_R.setText("Movimientos");
        btnMOVIMIENTOS_R.setBorderPainted(false);

        txtULTIMO_PAGO_R.setBackground(new java.awt.Color(204, 204, 204));
        txtULTIMO_PAGO_R.setForeground(new java.awt.Color(0, 0, 0));
        txtULTIMO_PAGO_R.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tablaClientes_R.setBackground(new java.awt.Color(204, 204, 204));
        tablaClientes_R.setForeground(new java.awt.Color(0, 0, 0));
        tablaClientes_R.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Concepto", "Cargo", "Creditos", "Saldos"
            }
        ));
        jScrollPane_R.setViewportView(tablaClientes_R);

        javax.swing.GroupLayout PanelMuestraTablaRegistroLayout = new javax.swing.GroupLayout(PanelMuestraTablaRegistro);
        PanelMuestraTablaRegistro.setLayout(PanelMuestraTablaRegistroLayout);
        PanelMuestraTablaRegistroLayout.setHorizontalGroup(
            PanelMuestraTablaRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMuestraTablaRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelMuestraTablaRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelMuestraTablaRegistroLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSALDO_R, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtULTIMO_PAGO_R, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnMOVIMIENTOS_R))
                    .addComponent(jScrollPane_R))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        PanelMuestraTablaRegistroLayout.setVerticalGroup(
            PanelMuestraTablaRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMuestraTablaRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelMuestraTablaRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtSALDO_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtULTIMO_PAGO_R, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMOVIMIENTOS_R))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane_R, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        PanelInferiorRegistro.setOpaque(false);

        btnSALIR_R.setBackground(new java.awt.Color(255, 152, 36));
        btnSALIR_R.setForeground(new java.awt.Color(0, 0, 0));
        btnSALIR_R.setText("Salir");
        btnSALIR_R.setBorderPainted(false);
        btnSALIR_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSALIR_RActionPerformed(evt);
            }
        });

        btnGUARDAR_R.setBackground(new java.awt.Color(255, 152, 36));
        btnGUARDAR_R.setForeground(new java.awt.Color(0, 0, 0));
        btnGUARDAR_R.setText("Guardar");
        btnGUARDAR_R.setBorderPainted(false);
        btnGUARDAR_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGUARDAR_RActionPerformed(evt);
            }
        });

        btnIMPRIMIR_R.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_R.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR_R.setText("Imprimir");
        btnIMPRIMIR_R.setBorderPainted(false);

        btnELIMINAR_R.setBackground(new java.awt.Color(255, 152, 36));
        btnELIMINAR_R.setForeground(new java.awt.Color(0, 0, 0));
        btnELIMINAR_R.setText("Eliminar");
        btnELIMINAR_R.setBorderPainted(false);
        btnELIMINAR_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnELIMINAR_RActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInferiorRegistroLayout = new javax.swing.GroupLayout(PanelInferiorRegistro);
        PanelInferiorRegistro.setLayout(PanelInferiorRegistroLayout);
        PanelInferiorRegistroLayout.setHorizontalGroup(
            PanelInferiorRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorRegistroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnELIMINAR_R, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIMPRIMIR_R, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGUARDAR_R, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSALIR_R, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelInferiorRegistroLayout.setVerticalGroup(
            PanelInferiorRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorRegistroLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelInferiorRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSALIR_R)
                    .addComponent(btnGUARDAR_R)
                    .addComponent(btnIMPRIMIR_R)
                    .addComponent(btnELIMINAR_R))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelPrincipalRegistroLayout = new javax.swing.GroupLayout(PanelPrincipalRegistro);
        PanelPrincipalRegistro.setLayout(PanelPrincipalRegistroLayout);
        PanelPrincipalRegistroLayout.setHorizontalGroup(
            PanelPrincipalRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSuperiorRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalRegistroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPrincipalRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelPrincipalRegistroLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelInferiorRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelPrincipalRegistroLayout.createSequentialGroup()
                        .addComponent(PanelCapturaDatosRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(PanelMuestraTablaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelPrincipalRegistroLayout.setVerticalGroup(
            PanelPrincipalRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalRegistroLayout.createSequentialGroup()
                .addComponent(PanelSuperiorRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPrincipalRegistroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelMuestraTablaRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelCapturaDatosRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(PanelInferiorRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelPrincipalVenta.setOpaque(false);

        PanelSuperiorVenta.setOpaque(false);

        jSeparator2.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));

        jLabel20.setText("CLIENTE");

        MostrarVentasCliente_V.setBackground(new java.awt.Color(255, 152, 36));
        MostrarVentasCliente_V.setForeground(new java.awt.Color(0, 0, 0));
        MostrarVentasCliente_V.setText("Ventas a Cliente");
        MostrarVentasCliente_V.setBorderPainted(false);

        MostrarRechazosCliente_V.setBackground(new java.awt.Color(255, 152, 36));
        MostrarRechazosCliente_V.setForeground(new java.awt.Color(0, 0, 0));
        MostrarRechazosCliente_V.setText("Rechazos a Cliente");
        MostrarRechazosCliente_V.setBorderPainted(false);
        MostrarRechazosCliente_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarRechazosCliente_VActionPerformed(evt);
            }
        });

        MostraraRegistroClientes_V.setBackground(new java.awt.Color(255, 152, 36));
        MostraraRegistroClientes_V.setForeground(new java.awt.Color(0, 0, 0));
        MostraraRegistroClientes_V.setText("Registro de Cliente");
        MostraraRegistroClientes_V.setBorderPainted(false);
        MostraraRegistroClientes_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostraraRegistroClientes_VActionPerformed(evt);
            }
        });

        btnVolverArchivoCVenta.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverArchivoCVenta.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverArchivoCVenta.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverArchivoCVenta.setText("VOLVER");
        btnVolverArchivoCVenta.setBorderPainted(false);
        btnVolverArchivoCVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverArchivoCVentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSuperiorVentaLayout = new javax.swing.GroupLayout(PanelSuperiorVenta);
        PanelSuperiorVenta.setLayout(PanelSuperiorVentaLayout);
        PanelSuperiorVentaLayout.setHorizontalGroup(
            PanelSuperiorVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorVentaLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(PanelSuperiorVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(PanelSuperiorVentaLayout.createSequentialGroup()
                        .addComponent(MostraraRegistroClientes_V, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(MostrarVentasCliente_V, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(MostrarRechazosCliente_V, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelSuperiorVentaLayout.createSequentialGroup()
                        .addComponent(btnVolverArchivoCVenta)
                        .addGap(531, 531, 531)
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(5, 5, 5))
        );
        PanelSuperiorVentaLayout.setVerticalGroup(
            PanelSuperiorVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelSuperiorVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverArchivoCVenta)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelSuperiorVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MostraraRegistroClientes_V)
                    .addComponent(MostrarVentasCliente_V)
                    .addComponent(MostrarRechazosCliente_V))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        PanelInferiorVenta.setOpaque(false);

        btnSALIR_V.setBackground(new java.awt.Color(255, 152, 36));
        btnSALIR_V.setForeground(new java.awt.Color(0, 0, 0));
        btnSALIR_V.setText("Salir");
        btnSALIR_V.setBorderPainted(false);
        btnSALIR_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSALIR_VActionPerformed(evt);
            }
        });

        btnGUARDAR_V.setBackground(new java.awt.Color(255, 152, 36));
        btnGUARDAR_V.setForeground(new java.awt.Color(0, 0, 0));
        btnGUARDAR_V.setText("Guardar");
        btnGUARDAR_V.setBorderPainted(false);

        btnIMPRIMIR_V.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_V.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR_V.setText("Imprimir");
        btnIMPRIMIR_V.setBorderPainted(false);

        btnELIMINAR_V.setBackground(new java.awt.Color(255, 152, 36));
        btnELIMINAR_V.setForeground(new java.awt.Color(0, 0, 0));
        btnELIMINAR_V.setText("Eliminar");
        btnELIMINAR_V.setBorderPainted(false);

        javax.swing.GroupLayout PanelInferiorVentaLayout = new javax.swing.GroupLayout(PanelInferiorVenta);
        PanelInferiorVenta.setLayout(PanelInferiorVentaLayout);
        PanelInferiorVentaLayout.setHorizontalGroup(
            PanelInferiorVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorVentaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnELIMINAR_V, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIMPRIMIR_V, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGUARDAR_V, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSALIR_V, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelInferiorVentaLayout.setVerticalGroup(
            PanelInferiorVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorVentaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelInferiorVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSALIR_V)
                    .addComponent(btnGUARDAR_V)
                    .addComponent(btnIMPRIMIR_V)
                    .addComponent(btnELIMINAR_V))
                .addContainerGap())
        );

        PanelAgregarDatosVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelAgregarDatosVenta.setOpaque(false);

        jLabel21.setText("CLIENTE");

        txtCLIENTE_V.setBackground(new java.awt.Color(204, 204, 204));
        txtCLIENTE_V.setForeground(new java.awt.Color(0, 0, 0));
        txtCLIENTE_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCLIENTE_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCLIENTE_VActionPerformed(evt);
            }
        });

        jLabel22.setText("Nombre");

        jLabel23.setText("Titulo");

        jLabel24.setText("Edicion");

        jLabel25.setText("Precio");

        jLabel26.setText("Cantidad");

        jLabel27.setText("Importe");

        txtTITULO_V.setBackground(new java.awt.Color(204, 204, 204));
        txtTITULO_V.setForeground(new java.awt.Color(0, 0, 0));
        txtTITULO_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTITULO_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTITULO_VActionPerformed(evt);
            }
        });

        txtNOMBRE_V.setBackground(new java.awt.Color(204, 204, 204));
        txtNOMBRE_V.setForeground(new java.awt.Color(0, 0, 0));
        txtNOMBRE_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNOMBRE_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNOMBRE_VActionPerformed(evt);
            }
        });

        txtEDICION_V.setBackground(new java.awt.Color(204, 204, 204));
        txtEDICION_V.setForeground(new java.awt.Color(0, 0, 0));
        txtEDICION_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtEDICION_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEDICION_VActionPerformed(evt);
            }
        });

        txtPRECIO_V.setBackground(new java.awt.Color(204, 204, 204));
        txtPRECIO_V.setForeground(new java.awt.Color(0, 0, 0));
        txtPRECIO_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtPRECIO_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPRECIO_VActionPerformed(evt);
            }
        });

        txtCANTIDAD_V.setBackground(new java.awt.Color(204, 204, 204));
        txtCANTIDAD_V.setForeground(new java.awt.Color(0, 0, 0));
        txtCANTIDAD_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCANTIDAD_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCANTIDAD_VActionPerformed(evt);
            }
        });

        txtIMPORTE_V.setBackground(new java.awt.Color(204, 204, 204));
        txtIMPORTE_V.setForeground(new java.awt.Color(0, 0, 0));
        txtIMPORTE_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnAGREGAR_V.setBackground(new java.awt.Color(255, 152, 36));
        btnAGREGAR_V.setForeground(new java.awt.Color(0, 0, 0));
        btnAGREGAR_V.setText("Agregar");
        btnAGREGAR_V.setBorderPainted(false);
        btnAGREGAR_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAGREGAR_VActionPerformed(evt);
            }
        });

        jLabel28.setText("Existencia:");

        txtEXISTENCIA_V.setBackground(new java.awt.Color(204, 204, 204));
        txtEXISTENCIA_V.setForeground(new java.awt.Color(0, 0, 0));
        txtEXISTENCIA_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtEXISTENCIA_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEXISTENCIA_VActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelAgregarDatosVentaLayout = new javax.swing.GroupLayout(PanelAgregarDatosVenta);
        PanelAgregarDatosVenta.setLayout(PanelAgregarDatosVentaLayout);
        PanelAgregarDatosVentaLayout.setHorizontalGroup(
            PanelAgregarDatosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgregarDatosVentaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(593, 593, 593))
            .addGroup(PanelAgregarDatosVentaLayout.createSequentialGroup()
                .addGroup(PanelAgregarDatosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelAgregarDatosVentaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCLIENTE_V, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addComponent(txtEXISTENCIA_V, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelAgregarDatosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelAgregarDatosVentaLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(txtTITULO_V, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNOMBRE_V, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtEDICION_V, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPRECIO_V, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCANTIDAD_V, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIMPORTE_V, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnAGREGAR_V))
                        .addGroup(PanelAgregarDatosVentaLayout.createSequentialGroup()
                            .addGap(81, 81, 81)
                            .addComponent(jLabel23)
                            .addGap(224, 224, 224)
                            .addComponent(jLabel22)
                            .addGap(191, 191, 191)
                            .addComponent(jLabel24)
                            .addGap(115, 115, 115)
                            .addComponent(jLabel25)
                            .addGap(129, 129, 129)
                            .addComponent(jLabel26)
                            .addGap(102, 102, 102)
                            .addComponent(jLabel27))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        PanelAgregarDatosVentaLayout.setVerticalGroup(
            PanelAgregarDatosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelAgregarDatosVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAgregarDatosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCLIENTE_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28)
                    .addComponent(txtEXISTENCIA_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAgregarDatosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAgregarDatosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTITULO_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNOMBRE_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEDICION_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPRECIO_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCANTIDAD_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIMPORTE_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAGREGAR_V))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        PanelResultadosVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelResultadosVenta.setOpaque(false);

        jLabel29.setText("Subtotal:");

        jLabel30.setText("Descuento:");

        jLabel31.setText("Total:");

        jLabel32.setText("Abono:");

        txtSUBTOTAL_V.setBackground(new java.awt.Color(204, 204, 204));
        txtSUBTOTAL_V.setForeground(new java.awt.Color(0, 0, 0));
        txtSUBTOTAL_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtDESCUENTO_V.setBackground(new java.awt.Color(204, 204, 204));
        txtDESCUENTO_V.setForeground(new java.awt.Color(0, 0, 0));
        txtDESCUENTO_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtTOTAL_V.setBackground(new java.awt.Color(204, 204, 204));
        txtTOTAL_V.setForeground(new java.awt.Color(0, 0, 0));
        txtTOTAL_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtABONO_V.setBackground(new java.awt.Color(204, 204, 204));
        txtABONO_V.setForeground(new java.awt.Color(0, 0, 0));
        txtABONO_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtABONO_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtABONO_VActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelResultadosVentaLayout = new javax.swing.GroupLayout(PanelResultadosVenta);
        PanelResultadosVenta.setLayout(PanelResultadosVentaLayout);
        PanelResultadosVentaLayout.setHorizontalGroup(
            PanelResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelResultadosVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32))
                .addGap(29, 29, 29)
                .addGroup(PanelResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtABONO_V, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTOTAL_V, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDESCUENTO_V, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSUBTOTAL_V, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelResultadosVentaLayout.setVerticalGroup(
            PanelResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelResultadosVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(txtSUBTOTAL_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtDESCUENTO_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(txtTOTAL_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtABONO_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelOtrosResultadosVenta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelOtrosResultadosVenta.setOpaque(false);

        jLabel33.setText("Saldo Activo");

        txtSALDO_V.setBackground(new java.awt.Color(204, 204, 204));
        txtSALDO_V.setForeground(new java.awt.Color(0, 0, 0));
        txtSALDO_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtSALDO_ABONO_V.setBackground(new java.awt.Color(204, 204, 204));
        txtSALDO_ABONO_V.setForeground(new java.awt.Color(0, 0, 0));
        txtSALDO_ABONO_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtSALDO_NUEVO_V.setBackground(new java.awt.Color(204, 204, 204));
        txtSALDO_NUEVO_V.setForeground(new java.awt.Color(0, 0, 0));
        txtSALDO_NUEVO_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel34.setText("Folio de Ventas");

        txtFOLIO_VENTAS_V.setBackground(new java.awt.Color(204, 204, 204));
        txtFOLIO_VENTAS_V.setForeground(new java.awt.Color(0, 0, 0));
        txtFOLIO_VENTAS_V.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnAPLICAR_VENTAS_V.setBackground(new java.awt.Color(255, 152, 36));
        btnAPLICAR_VENTAS_V.setForeground(new java.awt.Color(0, 0, 0));
        btnAPLICAR_VENTAS_V.setText("Aplicar Venta");
        btnAPLICAR_VENTAS_V.setBorderPainted(false);
        btnAPLICAR_VENTAS_V.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAPLICAR_VENTAS_VActionPerformed(evt);
            }
        });

        btnIMPRIMIR_REMISION_V.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_REMISION_V.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR_REMISION_V.setText("Imprimir Remision");
        btnIMPRIMIR_REMISION_V.setBorderPainted(false);

        btnIMPRIMIR_ROLLO_V.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_ROLLO_V.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR_ROLLO_V.setText("Imprimir Rollo");
        btnIMPRIMIR_ROLLO_V.setBorderPainted(false);

        btnIMPRIMIR_FACTURA_V.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_FACTURA_V.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR_FACTURA_V.setText("Imprimir Factura");
        btnIMPRIMIR_FACTURA_V.setBorderPainted(false);

        javax.swing.GroupLayout PanelOtrosResultadosVentaLayout = new javax.swing.GroupLayout(PanelOtrosResultadosVenta);
        PanelOtrosResultadosVenta.setLayout(PanelOtrosResultadosVentaLayout);
        PanelOtrosResultadosVentaLayout.setHorizontalGroup(
            PanelOtrosResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOtrosResultadosVentaLayout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(PanelOtrosResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIMPRIMIR_FACTURA_V, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIMPRIMIR_ROLLO_V, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIMPRIMIR_REMISION_V, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAPLICAR_VENTAS_V, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelOtrosResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOtrosResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSALDO_NUEVO_V, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSALDO_ABONO_V, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelOtrosResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOtrosResultadosVentaLayout.createSequentialGroup()
                                    .addComponent(jLabel33)
                                    .addGap(128, 128, 128))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOtrosResultadosVentaLayout.createSequentialGroup()
                                    .addComponent(txtSALDO_V, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(73, 73, 73)))
                            .addComponent(txtFOLIO_VENTAS_V, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOtrosResultadosVentaLayout.createSequentialGroup()
                            .addComponent(jLabel34)
                            .addGap(116, 116, 116)))))
        );
        PanelOtrosResultadosVentaLayout.setVerticalGroup(
            PanelOtrosResultadosVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOtrosResultadosVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSALDO_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSALDO_ABONO_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSALDO_NUEVO_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFOLIO_VENTAS_V, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnAPLICAR_VENTAS_V)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIMPRIMIR_REMISION_V)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIMPRIMIR_ROLLO_V)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIMPRIMIR_FACTURA_V)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelTabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelTabla.setOpaque(false);

        TablaDatosClientes_V.setBackground(new java.awt.Color(204, 204, 204));
        TablaDatosClientes_V.setForeground(new java.awt.Color(0, 0, 0));
        TablaDatosClientes_V.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Descripcion", "Edicion", "Cantidad", "P.U", "Importe"
            }
        ));
        jScrollPane_V.setViewportView(TablaDatosClientes_V);

        javax.swing.GroupLayout PanelTablaLayout = new javax.swing.GroupLayout(PanelTabla);
        PanelTabla.setLayout(PanelTablaLayout);
        PanelTablaLayout.setHorizontalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane_V)
        );
        PanelTablaLayout.setVerticalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane_V, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelPrincipalVentaLayout = new javax.swing.GroupLayout(PanelPrincipalVenta);
        PanelPrincipalVenta.setLayout(PanelPrincipalVentaLayout);
        PanelPrincipalVentaLayout.setHorizontalGroup(
            PanelPrincipalVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSuperiorVenta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPrincipalVentaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPrincipalVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelAgregarDatosVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalVentaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelInferiorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalVentaLayout.createSequentialGroup()
                        .addGroup(PanelPrincipalVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelPrincipalVentaLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(PanelResultadosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(PanelOtrosResultadosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelPrincipalVentaLayout.setVerticalGroup(
            PanelPrincipalVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalVentaLayout.createSequentialGroup()
                .addComponent(PanelSuperiorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelAgregarDatosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelPrincipalVentaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalVentaLayout.createSequentialGroup()
                        .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelResultadosVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelOtrosResultadosVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInferiorVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelPrincipalRechazo.setOpaque(false);

        PanelSuperiorRechazos.setOpaque(false);

        jSeparator3.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator3.setForeground(new java.awt.Color(102, 102, 102));

        jLabel35.setText("CLIENTE");

        MostrarVentasCliente_RE.setBackground(new java.awt.Color(255, 152, 36));
        MostrarVentasCliente_RE.setForeground(new java.awt.Color(0, 0, 0));
        MostrarVentasCliente_RE.setText("Ventas a Cliente");
        MostrarVentasCliente_RE.setBorderPainted(false);
        MostrarVentasCliente_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarVentasCliente_REActionPerformed(evt);
            }
        });

        MostrarRechazosCliente_RE.setBackground(new java.awt.Color(255, 152, 36));
        MostrarRechazosCliente_RE.setForeground(new java.awt.Color(0, 0, 0));
        MostrarRechazosCliente_RE.setText("Rechazos a Cliente");
        MostrarRechazosCliente_RE.setBorderPainted(false);

        MostraraRegistroClientes_RE.setBackground(new java.awt.Color(255, 152, 36));
        MostraraRegistroClientes_RE.setForeground(new java.awt.Color(0, 0, 0));
        MostraraRegistroClientes_RE.setText("Registro de Cliente");
        MostraraRegistroClientes_RE.setBorderPainted(false);
        MostraraRegistroClientes_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostraraRegistroClientes_REActionPerformed(evt);
            }
        });

        btnVolverArchivoCRechazo.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverArchivoCRechazo.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverArchivoCRechazo.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverArchivoCRechazo.setText("VOLVER");
        btnVolverArchivoCRechazo.setBorderPainted(false);
        btnVolverArchivoCRechazo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverArchivoCRechazoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelSuperiorRechazosLayout = new javax.swing.GroupLayout(PanelSuperiorRechazos);
        PanelSuperiorRechazos.setLayout(PanelSuperiorRechazosLayout);
        PanelSuperiorRechazosLayout.setHorizontalGroup(
            PanelSuperiorRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1280, Short.MAX_VALUE)
            .addGroup(PanelSuperiorRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelSuperiorRechazosLayout.createSequentialGroup()
                    .addGap(5, 5, 5)
                    .addGroup(PanelSuperiorRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator3)
                        .addGroup(PanelSuperiorRechazosLayout.createSequentialGroup()
                            .addComponent(MostraraRegistroClientes_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(MostrarVentasCliente_RE, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(MostrarRechazosCliente_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PanelSuperiorRechazosLayout.createSequentialGroup()
                            .addComponent(btnVolverArchivoCRechazo)
                            .addGap(531, 531, 531)
                            .addComponent(jLabel35)
                            .addGap(0, 0, Short.MAX_VALUE)))
                    .addGap(5, 5, 5)))
        );
        PanelSuperiorRechazosLayout.setVerticalGroup(
            PanelSuperiorRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 80, Short.MAX_VALUE)
            .addGroup(PanelSuperiorRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelSuperiorRechazosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(PanelSuperiorRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVolverArchivoCRechazo)
                        .addComponent(jLabel35))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(PanelSuperiorRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(MostraraRegistroClientes_RE)
                        .addComponent(MostrarVentasCliente_RE)
                        .addComponent(MostrarRechazosCliente_RE))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        PanelInferiorRechazos.setOpaque(false);

        btnSALIR_RE.setBackground(new java.awt.Color(255, 152, 36));
        btnSALIR_RE.setForeground(new java.awt.Color(0, 0, 0));
        btnSALIR_RE.setText("Salir");
        btnSALIR_RE.setBorderPainted(false);
        btnSALIR_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSALIR_REActionPerformed(evt);
            }
        });

        btnGUARDAR_RE.setBackground(new java.awt.Color(255, 152, 36));
        btnGUARDAR_RE.setForeground(new java.awt.Color(0, 0, 0));
        btnGUARDAR_RE.setText("Guardar");
        btnGUARDAR_RE.setBorderPainted(false);

        btnIMPRIMIR_RE.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_RE.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR_RE.setText("Imprimir");
        btnIMPRIMIR_RE.setBorderPainted(false);

        btnELIMINAR_RE.setBackground(new java.awt.Color(255, 152, 36));
        btnELIMINAR_RE.setForeground(new java.awt.Color(0, 0, 0));
        btnELIMINAR_RE.setText("Eliminar");
        btnELIMINAR_RE.setBorderPainted(false);

        javax.swing.GroupLayout PanelInferiorRechazosLayout = new javax.swing.GroupLayout(PanelInferiorRechazos);
        PanelInferiorRechazos.setLayout(PanelInferiorRechazosLayout);
        PanelInferiorRechazosLayout.setHorizontalGroup(
            PanelInferiorRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorRechazosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnELIMINAR_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIMPRIMIR_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGUARDAR_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSALIR_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelInferiorRechazosLayout.setVerticalGroup(
            PanelInferiorRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorRechazosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelInferiorRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSALIR_RE)
                    .addComponent(btnGUARDAR_RE)
                    .addComponent(btnIMPRIMIR_RE)
                    .addComponent(btnELIMINAR_RE))
                .addContainerGap())
        );

        PanelCapturaDatosRechazos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelCapturaDatosRechazos.setOpaque(false);

        jLabel36.setText("CLIENTE");

        txtCLIENTE_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtCLIENTE_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtCLIENTE_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCLIENTE_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCLIENTE_REActionPerformed(evt);
            }
        });

        jLabel37.setText("Nombre");

        jLabel38.setText("Titulo");

        jLabel39.setText("Edicion");

        jLabel40.setText("Precio");

        jLabel41.setText("Cantidad");

        jLabel42.setText("Importe");

        txtTITULO_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtTITULO_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtTITULO_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtTITULO_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTITULO_REActionPerformed(evt);
            }
        });

        txtNOMBRE_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtNOMBRE_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtNOMBRE_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNOMBRE_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNOMBRE_REActionPerformed(evt);
            }
        });

        txtEDICION_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtEDICION_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtEDICION_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtEDICION_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEDICION_REActionPerformed(evt);
            }
        });

        txtPRECIO_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtPRECIO_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtPRECIO_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtPRECIO_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPRECIO_REActionPerformed(evt);
            }
        });

        txtCANTIDAD_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtCANTIDAD_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtCANTIDAD_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCANTIDAD_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCANTIDAD_REActionPerformed(evt);
            }
        });

        txtIMPORTE_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtIMPORTE_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtIMPORTE_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnAGREGAR_RE.setBackground(new java.awt.Color(255, 152, 36));
        btnAGREGAR_RE.setForeground(new java.awt.Color(0, 0, 0));
        btnAGREGAR_RE.setText("Agregar");
        btnAGREGAR_RE.setBorderPainted(false);
        btnAGREGAR_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAGREGAR_REActionPerformed(evt);
            }
        });

        jLabel43.setText("Cantidad Entregada:");

        txtCANTIDAD_ENTREGADA_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtCANTIDAD_ENTREGADA_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtCANTIDAD_ENTREGADA_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCANTIDAD_ENTREGADA_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCANTIDAD_ENTREGADA_REActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelCapturaDatosRechazosLayout = new javax.swing.GroupLayout(PanelCapturaDatosRechazos);
        PanelCapturaDatosRechazos.setLayout(PanelCapturaDatosRechazosLayout);
        PanelCapturaDatosRechazosLayout.setHorizontalGroup(
            PanelCapturaDatosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCapturaDatosRechazosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(593, 593, 593))
            .addGroup(PanelCapturaDatosRechazosLayout.createSequentialGroup()
                .addGroup(PanelCapturaDatosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PanelCapturaDatosRechazosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCLIENTE_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(81, 81, 81)
                        .addComponent(jLabel43)
                        .addGap(18, 18, 18)
                        .addComponent(txtCANTIDAD_ENTREGADA_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelCapturaDatosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PanelCapturaDatosRechazosLayout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(txtTITULO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtNOMBRE_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtEDICION_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPRECIO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCANTIDAD_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtIMPORTE_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btnAGREGAR_RE))
                        .addGroup(PanelCapturaDatosRechazosLayout.createSequentialGroup()
                            .addGap(81, 81, 81)
                            .addComponent(jLabel38)
                            .addGap(224, 224, 224)
                            .addComponent(jLabel37)
                            .addGap(191, 191, 191)
                            .addComponent(jLabel39)
                            .addGap(115, 115, 115)
                            .addComponent(jLabel40)
                            .addGap(129, 129, 129)
                            .addComponent(jLabel41)
                            .addGap(102, 102, 102)
                            .addComponent(jLabel42))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        PanelCapturaDatosRechazosLayout.setVerticalGroup(
            PanelCapturaDatosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelCapturaDatosRechazosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelCapturaDatosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCLIENTE_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(txtCANTIDAD_ENTREGADA_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelCapturaDatosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38)
                    .addComponent(jLabel39)
                    .addComponent(jLabel40)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelCapturaDatosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTITULO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNOMBRE_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEDICION_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPRECIO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCANTIDAD_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIMPORTE_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAGREGAR_RE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        PanelResultadosRechazos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelResultadosRechazos.setOpaque(false);

        jLabel44.setText("Subtotal:");

        jLabel45.setText("Descuento:");

        jLabel46.setText("Total:");

        txtSUBTOTAL_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtSUBTOTAL_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtSUBTOTAL_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtDESCUENTO_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtDESCUENTO_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtDESCUENTO_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtTOTAL_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtTOTAL_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtTOTAL_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        javax.swing.GroupLayout PanelResultadosRechazosLayout = new javax.swing.GroupLayout(PanelResultadosRechazos);
        PanelResultadosRechazos.setLayout(PanelResultadosRechazosLayout);
        PanelResultadosRechazosLayout.setHorizontalGroup(
            PanelResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelResultadosRechazosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45)
                    .addComponent(jLabel46))
                .addGap(29, 29, 29)
                .addGroup(PanelResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTOTAL_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDESCUENTO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSUBTOTAL_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelResultadosRechazosLayout.setVerticalGroup(
            PanelResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelResultadosRechazosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtSUBTOTAL_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(txtDESCUENTO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(txtTOTAL_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelOtrosResultadosRechazos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelOtrosResultadosRechazos.setOpaque(false);

        jLabel47.setText("Saldo Activo");

        txtSALDO_ACTIVO_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtSALDO_ACTIVO_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtSALDO_ACTIVO_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtTOTAL_RECHAZO.setBackground(new java.awt.Color(204, 204, 204));
        txtTOTAL_RECHAZO.setForeground(new java.awt.Color(0, 0, 0));
        txtTOTAL_RECHAZO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtNUEVO_SALDO.setBackground(new java.awt.Color(204, 204, 204));
        txtNUEVO_SALDO.setForeground(new java.awt.Color(0, 0, 0));
        txtNUEVO_SALDO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel48.setText("Folio de Rechazo");

        txtFOLIO_RECHAZO_RE.setBackground(new java.awt.Color(204, 204, 204));
        txtFOLIO_RECHAZO_RE.setForeground(new java.awt.Color(0, 0, 0));
        txtFOLIO_RECHAZO_RE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnAPLICAR_RECHAZO_RE.setBackground(new java.awt.Color(255, 152, 36));
        btnAPLICAR_RECHAZO_RE.setForeground(new java.awt.Color(0, 0, 0));
        btnAPLICAR_RECHAZO_RE.setText("Aplicar Rechazo");
        btnAPLICAR_RECHAZO_RE.setBorderPainted(false);
        btnAPLICAR_RECHAZO_RE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAPLICAR_RECHAZO_REActionPerformed(evt);
            }
        });

        btnIMPRIMIR_ROLLO_RE.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_ROLLO_RE.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR_ROLLO_RE.setText("Imprimir en Rollo");
        btnIMPRIMIR_ROLLO_RE.setBorderPainted(false);

        btnIMPRIMIR_RECHAZO_RE.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_RECHAZO_RE.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR_RECHAZO_RE.setText("Imprimir Rechazo");
        btnIMPRIMIR_RECHAZO_RE.setBorderPainted(false);

        javax.swing.GroupLayout PanelOtrosResultadosRechazosLayout = new javax.swing.GroupLayout(PanelOtrosResultadosRechazos);
        PanelOtrosResultadosRechazos.setLayout(PanelOtrosResultadosRechazosLayout);
        PanelOtrosResultadosRechazosLayout.setHorizontalGroup(
            PanelOtrosResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOtrosResultadosRechazosLayout.createSequentialGroup()
                .addContainerGap(79, Short.MAX_VALUE)
                .addGroup(PanelOtrosResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOtrosResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnIMPRIMIR_RECHAZO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIMPRIMIR_ROLLO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAPLICAR_RECHAZO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNUEVO_SALDO, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTOTAL_RECHAZO, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelOtrosResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOtrosResultadosRechazosLayout.createSequentialGroup()
                                .addComponent(jLabel47)
                                .addGap(128, 128, 128))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOtrosResultadosRechazosLayout.createSequentialGroup()
                                .addComponent(txtSALDO_ACTIVO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73)))
                        .addComponent(txtFOLIO_RECHAZO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelOtrosResultadosRechazosLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addGap(112, 112, 112))))
        );
        PanelOtrosResultadosRechazosLayout.setVerticalGroup(
            PanelOtrosResultadosRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelOtrosResultadosRechazosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSALDO_ACTIVO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTOTAL_RECHAZO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNUEVO_SALDO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jLabel48)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFOLIO_RECHAZO_RE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(btnAPLICAR_RECHAZO_RE)
                .addGap(18, 18, 18)
                .addComponent(btnIMPRIMIR_ROLLO_RE)
                .addGap(18, 18, 18)
                .addComponent(btnIMPRIMIR_RECHAZO_RE)
                .addContainerGap(111, Short.MAX_VALUE))
        );

        PanelTablaRechazos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelTablaRechazos.setOpaque(false);

        tablaCLIENTE_RE.setBackground(new java.awt.Color(204, 204, 204));
        tablaCLIENTE_RE.setForeground(new java.awt.Color(0, 0, 0));
        tablaCLIENTE_RE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Clave", "Descripcion", "Edicion", "Cantidad", "P.U", "Importe"
            }
        ));
        jScrollPane_RE.setViewportView(tablaCLIENTE_RE);

        javax.swing.GroupLayout PanelTablaRechazosLayout = new javax.swing.GroupLayout(PanelTablaRechazos);
        PanelTablaRechazos.setLayout(PanelTablaRechazosLayout);
        PanelTablaRechazosLayout.setHorizontalGroup(
            PanelTablaRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane_RE)
        );
        PanelTablaRechazosLayout.setVerticalGroup(
            PanelTablaRechazosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane_RE, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelPrincipalRechazoLayout = new javax.swing.GroupLayout(PanelPrincipalRechazo);
        PanelPrincipalRechazo.setLayout(PanelPrincipalRechazoLayout);
        PanelPrincipalRechazoLayout.setHorizontalGroup(
            PanelPrincipalRechazoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelSuperiorRechazos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PanelPrincipalRechazoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPrincipalRechazoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelCapturaDatosRechazos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalRechazoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelInferiorRechazos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalRechazoLayout.createSequentialGroup()
                        .addGroup(PanelPrincipalRechazoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PanelPrincipalRechazoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(PanelResultadosRechazos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PanelTablaRechazos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(PanelOtrosResultadosRechazos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelPrincipalRechazoLayout.setVerticalGroup(
            PanelPrincipalRechazoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalRechazoLayout.createSequentialGroup()
                .addComponent(PanelSuperiorRechazos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelCapturaDatosRechazos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelPrincipalRechazoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalRechazoLayout.createSequentialGroup()
                        .addComponent(PanelTablaRechazos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelResultadosRechazos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(PanelOtrosResultadosRechazos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInferiorRechazos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipalRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalRechazo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipalRegistro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalRechazo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
/*--------------- Esta seccion es la de el panel 'PanelPrincipalRegistro' ------------------------------ */
    private void MostrarVentasCliente_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarVentasCliente_RActionPerformed
        PanelPrincipalVenta.setVisible(true);
        PanelPrincipalRegistro.setVisible(false);
        PanelPrincipalRechazo.setVisible(false);
        String C_V = txtNOMBRE_R.getText();
        txtCLIENTE_V.setText(C_V);
        GenerarFolio();
        BuscarSaldo();
        txtTITULO_V.requestFocus();
    }//GEN-LAST:event_MostrarVentasCliente_RActionPerformed

    private void btnVolverArchivoCRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverArchivoCRegistroActionPerformed
        Salir();
    }//GEN-LAST:event_btnVolverArchivoCRegistroActionPerformed

    private void MostrarRechazosCliente_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarRechazosCliente_RActionPerformed
        PanelPrincipalVenta.setVisible(false);
        PanelPrincipalRegistro.setVisible(false);
        PanelPrincipalRechazo.setVisible(true);
        String C_R = txtNOMBRE_R.getText();
        txtCLIENTE_RE.setText(C_R);
        txtTITULO_RE.requestFocus();
    }//GEN-LAST:event_MostrarRechazosCliente_RActionPerformed

    private void txtNUMERO_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNUMERO_RActionPerformed
        BUSCAR();
        EstadoCuenta();
    }//GEN-LAST:event_txtNUMERO_RActionPerformed

    private void txtNOMBRE_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNOMBRE_RActionPerformed
        txtRAZON_SOCIAL_R.requestFocus();
    }//GEN-LAST:event_txtNOMBRE_RActionPerformed

    private void txtRAZON_SOCIAL_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRAZON_SOCIAL_RActionPerformed
        txtDIRECCION_R.requestFocus();
    }//GEN-LAST:event_txtRAZON_SOCIAL_RActionPerformed

    private void txtDIRECCION_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDIRECCION_RActionPerformed
        txtPOBLACION_R.requestFocus();
    }//GEN-LAST:event_txtDIRECCION_RActionPerformed

    private void txtPOBLACION_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPOBLACION_RActionPerformed
        txtRFC_R.requestFocus();
    }//GEN-LAST:event_txtPOBLACION_RActionPerformed

    private void txtRFC_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRFC_RActionPerformed
        txtTELEFONO_R.requestFocus();
    }//GEN-LAST:event_txtRFC_RActionPerformed

    private void txtTELEFONO_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTELEFONO_RActionPerformed
        txtEMAIL_R.requestFocus();
    }//GEN-LAST:event_txtTELEFONO_RActionPerformed

    private void txtEMAIL_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEMAIL_RActionPerformed
        txtDESCUENTO_R.requestFocus();
    }//GEN-LAST:event_txtEMAIL_RActionPerformed

    private void txtDESCUENTO_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDESCUENTO_RActionPerformed
        txtRUTA_R.requestFocus();
    }//GEN-LAST:event_txtDESCUENTO_RActionPerformed

    private void txtRUTA_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRUTA_RActionPerformed
        txtREPARTO_R.requestFocus();
    }//GEN-LAST:event_txtRUTA_RActionPerformed

    private void txtREPARTO_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtREPARTO_RActionPerformed
        Guardar_Actualizar();
    }//GEN-LAST:event_txtREPARTO_RActionPerformed
/*---------------------Esta seccion es la de el panel 'PanelPrincipalVentas'------------------------------*/
    private void MostrarRechazosCliente_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarRechazosCliente_VActionPerformed
        PanelPrincipalVenta.setVisible(false);
        PanelPrincipalRegistro.setVisible(false);
        PanelPrincipalRechazo.setVisible(true);
        String C_R = txtNOMBRE_R.getText();
        txtCLIENTE_RE.setText(C_R);
        LimpiaV();
        LimpiaVT();
        txtSUBTOTAL_V.setText("");
        txtDESCUENTO_V.setText("");
        txtTOTAL_V.setText("");
        txtABONO_V.setText("");
        txtSALDO_ABONO_V.setText("");
        txtSALDO_NUEVO_V.setText("");
        txtTITULO_RE.requestFocus();
    }//GEN-LAST:event_MostrarRechazosCliente_VActionPerformed

    private void MostraraRegistroClientes_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostraraRegistroClientes_VActionPerformed
        PanelPrincipalVenta.setVisible(false);
        PanelPrincipalRegistro.setVisible(true);
        PanelPrincipalRechazo.setVisible(false);
        EstadoCuenta();
        LimpiaV();
        LimpiaVT();
        txtSUBTOTAL_V.setText("");
        txtDESCUENTO_V.setText("");
        txtTOTAL_V.setText("");
        txtABONO_V.setText("");
        txtSALDO_ABONO_V.setText("");
        txtSALDO_NUEVO_V.setText("");
    }//GEN-LAST:event_MostraraRegistroClientes_VActionPerformed

    private void btnVolverArchivoCVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverArchivoCVentaActionPerformed
        Salir();
    }//GEN-LAST:event_btnVolverArchivoCVentaActionPerformed
/*--------------------Esta seccion es la de el panel 'PanelPrincipalRechazo'---------------------*/
    private void MostrarVentasCliente_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarVentasCliente_REActionPerformed
        PanelPrincipalVenta.setVisible(true);
        PanelPrincipalRegistro.setVisible(false);
        PanelPrincipalRechazo.setVisible(false);
        String C_R = txtNOMBRE_R.getText();
        txtCLIENTE_V.setText(C_R);
        GenerarFolio();
        BuscarSaldo();
        txtTITULO_V.requestFocus();
    }//GEN-LAST:event_MostrarVentasCliente_REActionPerformed

    private void MostraraRegistroClientes_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostraraRegistroClientes_REActionPerformed
        PanelPrincipalVenta.setVisible(false);
        PanelPrincipalRegistro.setVisible(true);
        PanelPrincipalRechazo.setVisible(false);
        EstadoCuenta();
    }//GEN-LAST:event_MostraraRegistroClientes_REActionPerformed

    private void btnVolverArchivoCRechazoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverArchivoCRechazoActionPerformed
        Salir();
    }//GEN-LAST:event_btnVolverArchivoCRechazoActionPerformed
/*------------------Boton que hace la busqueda de los datos en Panel Registro-----------------------*/
    private void btnBUSCAR_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBUSCAR_RActionPerformed
        ArchivoClientessMostrar CM = new ArchivoClientessMostrar(this);
        CM.setVisible(true);
        CM.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnBUSCAR_RActionPerformed
/*Declaracion de los botones salir*/
    private void btnSALIR_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSALIR_RActionPerformed
        Salir();
    }//GEN-LAST:event_btnSALIR_RActionPerformed

    private void btnSALIR_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSALIR_VActionPerformed
        Salir();
    }//GEN-LAST:event_btnSALIR_VActionPerformed

    private void btnSALIR_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSALIR_REActionPerformed
        Salir();
    }//GEN-LAST:event_btnSALIR_REActionPerformed
/*-Focus del panel Venta-*/
    private void txtCLIENTE_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCLIENTE_VActionPerformed
        txtEXISTENCIA_V.requestFocus();
    }//GEN-LAST:event_txtCLIENTE_VActionPerformed

    private void txtEXISTENCIA_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEXISTENCIA_VActionPerformed
        txtTITULO_V.requestFocus();
    }//GEN-LAST:event_txtEXISTENCIA_VActionPerformed

    private void txtTITULO_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTITULO_VActionPerformed
        String Cod = String.valueOf(txtTITULO_V.getText());
        String N = String.valueOf(txtCLIENTE_V.getText());
        
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt1 = Con.createStatement();
            ResultSet rs1 = smnt1.executeQuery("SELECT "+Cod+" FROM clientes WHERE Nombre = '"+N+"';");
            
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM titulos WHERE Codigo = '"+Cod+"';");
            
            if(rs1.next()){
                txtCANTIDAD_V.setText(String.valueOf(rs1.getInt(""+Cod+"")));
                while(rs.next()){
                txtTITULO_V.setText(rs.getString("Codigo"));
                txtNOMBRE_V.setText(rs.getString("Nombre"));
                txtPRECIO_V.setText(String.valueOf(rs.getInt("Precio")));
                }
            }else
            {
                txtTITULO_V.requestFocus();
                txtTITULO_V.setText("");
                txtNOMBRE_V.setText("");
                txtEDICION_V.setText("");
                txtCANTIDAD_V.setText("");
                txtPRECIO_V.setText("");
                txtIMPORTE_V.setText("");
            }
         
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
        int C = Integer.parseInt(txtCANTIDAD_V.getText());
        int P = Integer.parseInt(txtPRECIO_V.getText());
        int T = C * P;
        txtIMPORTE_V.setText(String.valueOf(T));
    }//GEN-LAST:event_txtTITULO_VActionPerformed

    private void txtNOMBRE_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNOMBRE_VActionPerformed
        txtEDICION_V.requestFocus();
    }//GEN-LAST:event_txtNOMBRE_VActionPerformed

    private void txtEDICION_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEDICION_VActionPerformed
        txtPRECIO_V.requestFocus();
    }//GEN-LAST:event_txtEDICION_VActionPerformed

    private void txtPRECIO_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPRECIO_VActionPerformed
        txtCANTIDAD_V.requestFocus();
    }//GEN-LAST:event_txtPRECIO_VActionPerformed

    private void txtCANTIDAD_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCANTIDAD_VActionPerformed
        int Precio = Integer.parseInt(txtPRECIO_V.getText());
        int Cantidad = Integer.parseInt(txtCANTIDAD_V.getText());
        
        int Importe = Precio * Cantidad;
        txtIMPORTE_V.setText(String.valueOf(Importe));
    }//GEN-LAST:event_txtCANTIDAD_VActionPerformed
/*-------------------------- Focus del panel Rechazos--------------------------------------------------  */
    private void txtCLIENTE_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCLIENTE_REActionPerformed
        txtCANTIDAD_ENTREGADA_RE.requestFocus();
    }//GEN-LAST:event_txtCLIENTE_REActionPerformed

    private void txtCANTIDAD_ENTREGADA_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCANTIDAD_ENTREGADA_REActionPerformed
        txtTITULO_RE.requestFocus();
    }//GEN-LAST:event_txtCANTIDAD_ENTREGADA_REActionPerformed

    private void btnAGREGAR_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAGREGAR_VActionPerformed
        DefaultTableModel model = (DefaultTableModel) TablaDatosClientes_V.getModel();
        double SUBT; double DescProv = 0;
        
        if(txtTITULO_V.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un titulo");
        }else{
            String titulo = txtTITULO_V.getText();
            String Nombre = txtNOMBRE_V.getText();
            String Edicion = txtEDICION_V.getText();
            String Precio = txtPRECIO_V.getText();
            String Cantidad = txtCANTIDAD_V.getText();
            String Importe = txtIMPORTE_V.getText();
        model.addRow(new Object[]{titulo, Nombre, Edicion, Cantidad, Precio, Importe});
        
        if(txtSUBTOTAL_V.getText().isEmpty()){
            SUBT = Double.parseDouble(txtIMPORTE_V.getText());
            txtSUBTOTAL_V.setText(String.valueOf(SUBT));
            DescProv = Double.parseDouble(txtDESCUENTO_R.getText());
            double Desc = (SUBT * DescProv) / 100;
            txtDESCUENTO_V.setText(String.valueOf(Desc));
            double Total = SUBT - Desc;
            txtTOTAL_V.setText(String.valueOf(Total));
        }else{
            double SU = Double.parseDouble(txtIMPORTE_V.getText());
            double S = Double.parseDouble(txtSUBTOTAL_V.getText());
            SUBT = SU + S;
            txtSUBTOTAL_V.setText(String.valueOf(SUBT));
            DescProv = Double.parseDouble(txtDESCUENTO_R.getText());
            double Desc = (SUBT * DescProv) / 100;
            txtDESCUENTO_V.setText(String.valueOf(Desc));
            double Total = SUBT - Desc;
            txtTOTAL_V.setText(String.valueOf(Total));
        }
        LimpiaV();
        }
    }//GEN-LAST:event_btnAGREGAR_VActionPerformed

    private void btnAPLICAR_VENTAS_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAPLICAR_VENTAS_VActionPerformed
        int Folio=0; String Fecha; 
        DefaultTableModel model = (DefaultTableModel) TablaDatosClientes_V.getModel();
        
        if(model.getRowCount() == 0){
            JOptionPane.showMessageDialog(null, "Agregue un titulo a la venta");
        }else
        {
            try{
                if(Principal.FechaAjustada.isEmpty()){
                    Date fechaActual = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Fecha = formatter.format(fechaActual);
                }else{
                    Fecha = Principal.FechaAjustada;
                } 
                Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
                Statement smnt = Con.createStatement();
                
                for(int i = 0; i < model.getRowCount(); i++)
                {
                    int FolioV = Integer.parseInt(txtFOLIO_VENTAS_V.getText());
                    int idCliente = Integer.parseInt(txtNUMERO_R.getText());
                    String NombreC = String.valueOf(txtNOMBRE_R.getText());
                    String Codigo = model.getValueAt(i, 0).toString();
                    String Titulo = model.getValueAt(i, 1).toString();
                    String Edicion = model.getValueAt(i, 2).toString();
                    int Cantidad = Integer.parseInt(model.getValueAt(i, 3).toString());
                    double Precio = Double.parseDouble(model.getValueAt(i, 4).toString());
                    double Importe = Double.parseDouble(model.getValueAt(i, 5).toString());
                    int Clave = Integer.parseInt(txtFOLIO_VENTAS_V.getText());
                    double TotalC = Double.parseDouble(txtTOTAL_V.getText());
                    double DescC = Double.parseDouble(txtDESCUENTO_V.getText());
                    
                    smnt.executeUpdate("INSERT INTO compras_cliente (FolioCompra, id_Cliente, NombreCliente, CodigoTitulo, Titulo, Edicion, Cantidad, Precio, Importe, Clave, FechaRecibido, TotalCompra, DescCompra) "
                    + "VALUES ("+FolioV+", "+idCliente+", '"+NombreC+"', '"+Codigo+"', '"+Titulo+"', "
                    + "'"+Edicion+"', "+Cantidad+", "+Precio+", "+Importe+", "+Clave+", '"+Fecha+"', "
                    + ""+TotalC+", "+DescC+");");
                }
                Con.close(); smnt.close();
            /*------------------------------------------------------------------------------------*/
            int idC2 = Integer.parseInt(txtNUMERO_R.getText());
            String Nombre2 = String.valueOf(txtNOMBRE_R.getText());
            String Concepto2 = "Venta";
            double Cargo2 = Double.parseDouble(txtTOTAL_V.getText());
            double Saldo2 = Double.parseDouble(txtSALDO_NUEVO_V.getText());
            try{
                Connection Con2 = (Connection) DriverManager.getConnection(url, usuario, contraseña);
                Statement smnt2 = Con2.createStatement();
                smnt2.executeUpdate("INSERT INTO estado_cuentacliente (idCliente,Nombre, Fecha, Concepto, Cargo, Saldos) "
                + "VALUES ("+idC2+", '"+Nombre2+"', '"+Fecha+"', '"+Concepto2+"', "+Cargo2+", "+Saldo2+");");
            Con2.close(); smnt2.close();
            }catch(SQLException ex){
                
            }
            /*-------------------------------------------------------------------------------------*/
            try{
                double SaldoA3 = Double.parseDouble(txtSALDO_NUEVO_V.getText());
                String NombreC3 = String.valueOf(txtNOMBRE_R.getText());
                Connection Con3 = (Connection) DriverManager.getConnection(url, usuario, contraseña);
                Statement smnt3 = Con3.createStatement();
                smnt3.executeUpdate("UPDATE clientes SET SaldoActual= "+SaldoA3+""
                        + " WHERE Nombre = '"+NombreC3+"';");
            Con3.close(); smnt3.close();
            }catch(SQLException ex){
                
            }
            /*-------------------------------------------------------------------------------------*/
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Error al realizar venta: " + ex);
            }
  
        LimpiaV();
        LimpiaVT();
        txtSUBTOTAL_V.setText("");
        txtDESCUENTO_V.setText("");
        txtTOTAL_V.setText("");
        txtABONO_V.setText("");
        txtSALDO_ABONO_V.setText("");
        txtSALDO_NUEVO_V.setText("");
        }
        GenerarFolio();
        BuscarSaldo();
    }//GEN-LAST:event_btnAPLICAR_VENTAS_VActionPerformed

    private void txtNOMBRE_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNOMBRE_REActionPerformed
        txtEDICION_RE.requestFocus();
    }//GEN-LAST:event_txtNOMBRE_REActionPerformed

    private void txtTITULO_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTITULO_REActionPerformed
        String Cod = String.valueOf(txtTITULO_RE.getText());
        
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM titulos WHERE Codigo = '"+Cod+"';");
            if(rs.next()){
                txtNOMBRE_RE.setText(rs.getString("Nombre"));
                txtPRECIO_RE.setText(String.valueOf(rs.getInt("Precio")));
            }else
            {
                txtNOMBRE_RE.requestFocus();
                txtNOMBRE_RE.setText("");
                txtPRECIO_RE.setText("");
            }
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
    }//GEN-LAST:event_txtTITULO_REActionPerformed

    private void txtEDICION_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEDICION_REActionPerformed
        txtPRECIO_RE.requestFocus();
    }//GEN-LAST:event_txtEDICION_REActionPerformed

    private void txtPRECIO_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPRECIO_REActionPerformed
        txtCANTIDAD_RE.requestFocus();
    }//GEN-LAST:event_txtPRECIO_REActionPerformed

    private void txtCANTIDAD_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCANTIDAD_REActionPerformed
        int Precio = Integer.parseInt(txtPRECIO_RE.getText());
        int Cantidad = Integer.parseInt(txtCANTIDAD_RE.getText());
        
        int Importe = Precio * Cantidad;
        txtIMPORTE_RE.setText(String.valueOf(Importe));
    }//GEN-LAST:event_txtCANTIDAD_REActionPerformed

    private void btnAGREGAR_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAGREGAR_REActionPerformed
        
    }//GEN-LAST:event_btnAGREGAR_REActionPerformed

    private void btnAPLICAR_RECHAZO_REActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAPLICAR_RECHAZO_REActionPerformed
        
    }//GEN-LAST:event_btnAPLICAR_RECHAZO_REActionPerformed

    private void btnGUARDAR_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGUARDAR_RActionPerformed
        Guardar_Actualizar();
    }//GEN-LAST:event_btnGUARDAR_RActionPerformed

    private void btnELIMINAR_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnELIMINAR_RActionPerformed
        if(txtNUMERO_R.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un cliente");
        }
        else{
            int DNumero = Integer.parseInt(txtNUMERO_R.getText());
            try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            smnt.executeUpdate("DELETE FROM clientes WHERE Nro_Cliente="+DNumero+";");
            
            Limpiar();
            JOptionPane.showMessageDialog(null, "Cliente eliminado");
            txtNUMERO_R.setText("");
            txtNUMERO_R.requestFocus();
            btnGUARDAR_R.setEnabled(false);
            btnELIMINAR_R.setEnabled(false);
            } catch (SQLException ex) {
            System.out.print("Error en aplicar rechazo: " + ex);
            }
        }
    }//GEN-LAST:event_btnELIMINAR_RActionPerformed

    private void txtABONO_VActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtABONO_VActionPerformed
        // TODO add your handling code here:
        double Total = Double.parseDouble(txtTOTAL_V.getText());
        double Abono = Double.parseDouble(txtABONO_V.getText());
        Double SaldoA = Double.parseDouble(txtSALDO_V.getText());
        
        double SaldoAbono = Abono - Total;
        txtSALDO_ABONO_V.setText(String.valueOf(SaldoAbono));
        
        double SaldoF = SaldoA+SaldoAbono;
        txtSALDO_NUEVO_V.setText(String.valueOf(SaldoF));
    }//GEN-LAST:event_txtABONO_VActionPerformed

    private void txtNUMERO_RMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNUMERO_RMouseClicked
        // TODO add your handling code here:
        Limpiar();
        txtNUMERO_R.setText("");
        txtNUMERO_R.requestFocus();
        DefaultTableModel model = (DefaultTableModel) tablaClientes_R.getModel();
        model.setRowCount(0);
    }//GEN-LAST:event_txtNUMERO_RMouseClicked
/*--------------------------Esta seccion es donde se declaran los componentes ------------------------------- */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MostrarRechazosCliente_R;
    private javax.swing.JButton MostrarRechazosCliente_RE;
    private javax.swing.JButton MostrarRechazosCliente_V;
    private javax.swing.JButton MostrarRegistroClientes_R;
    private javax.swing.JButton MostrarVentasCliente_R;
    private javax.swing.JButton MostrarVentasCliente_RE;
    private javax.swing.JButton MostrarVentasCliente_V;
    private javax.swing.JButton MostraraRegistroClientes_RE;
    private javax.swing.JButton MostraraRegistroClientes_V;
    private javax.swing.JPanel PanelAgregarDatosVenta;
    private javax.swing.JPanel PanelCapturaDatosRechazos;
    private javax.swing.JPanel PanelCapturaDatosRegistro;
    private javax.swing.JPanel PanelInferiorRechazos;
    private javax.swing.JPanel PanelInferiorRegistro;
    private javax.swing.JPanel PanelInferiorVenta;
    private javax.swing.JPanel PanelMuestraTablaRegistro;
    private javax.swing.JPanel PanelOtrosResultadosRechazos;
    private javax.swing.JPanel PanelOtrosResultadosVenta;
    private javax.swing.JPanel PanelPrincipalRechazo;
    private javax.swing.JPanel PanelPrincipalRegistro;
    private javax.swing.JPanel PanelPrincipalVenta;
    private javax.swing.JPanel PanelResultadosRechazos;
    private javax.swing.JPanel PanelResultadosVenta;
    private javax.swing.JPanel PanelSuperiorRechazos;
    private javax.swing.JPanel PanelSuperiorRegistro;
    private javax.swing.JPanel PanelSuperiorVenta;
    private javax.swing.JPanel PanelTabla;
    private javax.swing.JPanel PanelTablaRechazos;
    private javax.swing.JTable TablaDatosClientes_V;
    private javax.swing.JButton btnAGREGAR_RE;
    private javax.swing.JButton btnAGREGAR_V;
    private javax.swing.JButton btnAPLICAR_RECHAZO_RE;
    private javax.swing.JButton btnAPLICAR_VENTAS_V;
    private javax.swing.JButton btnBUSCAR_R;
    private javax.swing.JButton btnELIMINAR_R;
    private javax.swing.JButton btnELIMINAR_RE;
    private javax.swing.JButton btnELIMINAR_V;
    private javax.swing.JButton btnGUARDAR_R;
    private javax.swing.JButton btnGUARDAR_RE;
    private javax.swing.JButton btnGUARDAR_V;
    private javax.swing.JButton btnIMPRIMIR_FACTURA_V;
    private javax.swing.JButton btnIMPRIMIR_R;
    private javax.swing.JButton btnIMPRIMIR_RE;
    private javax.swing.JButton btnIMPRIMIR_RECHAZO_RE;
    private javax.swing.JButton btnIMPRIMIR_REMISION_V;
    private javax.swing.JButton btnIMPRIMIR_ROLLO_RE;
    private javax.swing.JButton btnIMPRIMIR_ROLLO_V;
    private javax.swing.JButton btnIMPRIMIR_V;
    private javax.swing.JButton btnMOVIMIENTOS_R;
    private javax.swing.JButton btnSALIR_R;
    private javax.swing.JButton btnSALIR_RE;
    private javax.swing.JButton btnSALIR_V;
    private javax.swing.JButton btnVolverArchivoCRechazo;
    private javax.swing.JButton btnVolverArchivoCRegistro;
    private javax.swing.JButton btnVolverArchivoCVenta;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane_R;
    private javax.swing.JScrollPane jScrollPane_RE;
    private javax.swing.JScrollPane jScrollPane_V;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tablaCLIENTE_RE;
    private javax.swing.JTable tablaClientes_R;
    private javax.swing.JTextField txtABONO_V;
    private javax.swing.JTextField txtATRASO_R;
    private javax.swing.JTextField txtCANTIDAD_ENTREGADA_RE;
    private javax.swing.JTextField txtCANTIDAD_RE;
    private javax.swing.JTextField txtCANTIDAD_V;
    private javax.swing.JTextField txtCLIENTE_RE;
    private javax.swing.JTextField txtCLIENTE_V;
    private javax.swing.JTextField txtDESCUENTO_R;
    private javax.swing.JTextField txtDESCUENTO_RE;
    private javax.swing.JTextField txtDESCUENTO_V;
    private javax.swing.JTextField txtDIRECCION_R;
    private javax.swing.JTextField txtEDICION_RE;
    private javax.swing.JTextField txtEDICION_V;
    private javax.swing.JTextField txtEMAIL_R;
    private javax.swing.JTextField txtEXISTENCIA_V;
    private javax.swing.JTextField txtFOLIO_RECHAZO_RE;
    private javax.swing.JTextField txtFOLIO_VENTAS_V;
    private javax.swing.JTextField txtIMPORTE_RE;
    private javax.swing.JTextField txtIMPORTE_V;
    private javax.swing.JTextField txtLIMITE_CREDITO_R;
    private javax.swing.JTextField txtNOMBRE_R;
    private javax.swing.JTextField txtNOMBRE_RE;
    private javax.swing.JTextField txtNOMBRE_V;
    private javax.swing.JTextField txtNOTAS_R;
    private javax.swing.JTextField txtNUEVO_SALDO;
    private javax.swing.JTextField txtNUMERO_R;
    private javax.swing.JTextField txtPOBLACION_R;
    private javax.swing.JTextField txtPRECIO_RE;
    private javax.swing.JTextField txtPRECIO_V;
    private javax.swing.JTextField txtRAZON_SOCIAL_R;
    private javax.swing.JTextField txtREPARTO_R;
    private javax.swing.JTextField txtRFC_R;
    private javax.swing.JTextField txtRUTA_R;
    private javax.swing.JTextField txtSALDO_ABONO_V;
    private javax.swing.JTextField txtSALDO_ACTIVO_RE;
    private javax.swing.JTextField txtSALDO_NUEVO_V;
    private javax.swing.JTextField txtSALDO_R;
    private javax.swing.JTextField txtSALDO_V;
    private javax.swing.JTextField txtSUBTOTAL_RE;
    private javax.swing.JTextField txtSUBTOTAL_V;
    private javax.swing.JTextField txtTELEFONO_R;
    private javax.swing.JTextField txtTITULO_RE;
    private javax.swing.JTextField txtTITULO_V;
    private javax.swing.JTextField txtTOTAL_RE;
    private javax.swing.JTextField txtTOTAL_RECHAZO;
    private javax.swing.JTextField txtTOTAL_V;
    private javax.swing.JTextField txtULTIMO_PAGO_R;
    // End of variables declaration//GEN-END:variables
}
