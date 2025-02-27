package igu;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.EventObject;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ArchivoTitulos extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    int Condicional = 0;

    public ArchivoTitulos() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        this.setExtendedState(this.MAXIMIZED_BOTH);
        PanelPrincipalDotaciones.setVisible(false);
        btnGUARDAR_R1.setEnabled(false);
        btnELIMINAR_R1.setEnabled(false);
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            txtCODIGO.requestFocus();
        }
        });
          
        // Declara la variable fuera de los listeners para que ambos puedan acceder a ella
        final boolean[] firstKeyPress = {true}; // Usamos un arreglo para permitir la modificación dentro de los listeners
        // Crea un JTextField para el editor de celdas
        JTextField textField = new JTextField();
        // Añade un KeyListener para detectar la primera tecla presionada
        textField.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            // Verifica si la tecla presionada es un número (o cualquier otro carácter permitido)
            char keyChar = e.getKeyChar();
            if (firstKeyPress[0] && Character.isDigit(keyChar)) { // Solo borra si la tecla es un dígito
                textField.setText(""); // Borra el contenido actual en la primera tecla
                firstKeyPress[0] = false; // Desactiva la limpieza para las teclas subsecuentes
            }
        }
    }); 
        // Añade un FocusListener para reiniciar la lógica de borrado cada vez que cambia de celda
        textField.addFocusListener(new FocusAdapter() {
        @Override
        public void focusGained(FocusEvent e) {
            firstKeyPress[0] = true; // Permite la limpieza al comenzar la edición de una nueva celda
        }
    });
        // Asigna el editor personalizado a la última columna de la JTable
        TablaTitulos.getColumnModel().getColumn(7).setCellEditor(new DefaultCellEditor(textField));
        // Añade un ListSelectionListener para iniciar el modo de edición al seleccionar una nueva celda
        TablaTitulos.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            int row = TablaTitulos.getSelectedRow();
            int column = TablaTitulos.getSelectedColumn();
            if (column == 7) { // Verifica que sea la columna deseada
                TablaTitulos.editCellAt(row, column);
                textField.requestFocusInWindow(); // Coloca el foco en el editor de la celda
            }
        }
    });
    }
    
    /*Aqui e el metodo que hace la busqueda desde la pantalla TitulosMostrar*/
    public void ConsultaDatos(){
        String Cod = ArchivoTitulosMostrar.CodigoT;
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM titulos WHERE Codigo = '"+Cod+"';");
            if(rs.next()){
                txtCODIGO.setText(rs.getString("Codigo"));
                txtNOMBRE.setText(rs.getString("Nombre"));
                txtPERIOCIDAD.setText(rs.getString("Periocidad"));
                txtPRECIO.setText(rs.getString("Precio"));
                txtCODIGO_P.setText(rs.getString("CodigoProveedor"));
                txtDESCUENTO_P.setText(rs.getString("DescuentoProveedor"));
                txtLISTA.setText(rs.getString("Lista"));
                txtPROVEEDOR.setText(rs.getString("Proveedor"));
                txtPROPUESTA.setText(rs.getString("Propuesta"));
                Condicional = 1;
            }else
            {
                txtNOMBRE.requestFocus();
                Limpiar();
            }
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
    }
    
    private void GuardarActualizar(){
        String Codigo =""; String Nombre=""; String Periocidad=""; Double Precio=0.0; Double DescuentoP = 0.0; String CodigoP=""; String Lista =""; String Proveedor="";
        
        /*Les asignamos los valores*/
        Codigo = String.valueOf(txtCODIGO.getText());
        Nombre = String.valueOf(txtNOMBRE.getText());
        Periocidad = String.valueOf(txtPERIOCIDAD.getText());
        if(!txtPRECIO.getText().isEmpty()){
        Precio = Double.parseDouble(txtPRECIO.getText());}
        if(!txtDESCUENTO_P.getText().isEmpty()){
        DescuentoP = Double.parseDouble(txtDESCUENTO_P.getText());}
        CodigoP = String.valueOf(txtCODIGO_P.getText());
        Lista = String.valueOf(txtLISTA.getText());
        Proveedor = String.valueOf(txtPROVEEDOR.getText());
        
        /*Si la condicion vale 0, quiere decir que los datos son nuevos, se hace un insert*/
        if(Condicional == 0){
            try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            //Registramos el titulo
            smnt.executeUpdate("INSERT INTO titulos (Codigo, Nombre, Periocidad, Precio, DescuentoProveedor, CodigoProveedor, Lista, Proveedor) VALUES "
            + "('"+Codigo+"', '"+Nombre+"', '"+Periocidad+"', "+Precio+", "+DescuentoP+", "
            + "'"+CodigoP+"', '"+Lista+"', '"+Proveedor+"');");
            //Agregamos el titulo a los clientes
            smnt.executeUpdate("ALTER TABLE clientes ADD COLUMN "+Codigo+" INT;");
            
            Limpiar();
            txtCODIGO.setText("");
            txtCODIGO.requestFocus();
            JOptionPane.showMessageDialog(null, "Datos registrados");
            btnGUARDAR_R1.setEnabled(false);
            btnELIMINAR_R1.setEnabled(false);
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error registrar datos: "+ex);
            }
        }
        /*Si la condicion vale 1, quiere decir que los datos son de un cliente que ya existe, se hace un update*/
        if(Condicional == 1){
            try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            smnt.executeUpdate("UPDATE titulos SET "
            + "Nombre = '"+Nombre+"', Periocidad = '"+Periocidad+"', Precio = "+Precio+", "
            + "DescuentoProveedor = "+DescuentoP+", CodigoProveedor = '"+CodigoP+"', "
            + "Lista = '"+Lista+"', Proveedor = '"+Proveedor+"' WHERE Codigo = '"+Codigo+"';");
            
            Limpiar();
            txtCODIGO.setText("");
            txtCODIGO.requestFocus();
            JOptionPane.showMessageDialog(null, "Datos actualizados");
            btnGUARDAR_R1.setEnabled(false);
            btnELIMINAR_R1.setEnabled(false);
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error actualizar datos: "+ex);
            }
        }
    }
    
    private void GuardarDotacion(){
        String Cod = String.valueOf(txtCODIGO.getText());
        DefaultTableModel model = (DefaultTableModel) TablaTitulos.getModel();

        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            
            for(int i = 0; i < model.getRowCount(); i++){
            int NrC = Integer.parseInt(model.getValueAt(i, 0).toString());
            int Dotacion = Integer.parseInt(model.getValueAt(i, 7).toString());
            smnt.executeUpdate("UPDATE clientes SET "+Cod+" = "+Dotacion+" WHERE Nro_Cliente = "+NrC+";");
            }
            Con.close(); smnt.close();
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error actualizar datos: "+ex);
            }   
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelPrincipalDatos = new javax.swing.JPanel();
        btnVolverArchivoTitulos = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        PanelDatosTitulos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCODIGO = new javax.swing.JTextField();
        txtNOMBRE = new javax.swing.JTextField();
        txtPERIOCIDAD = new javax.swing.JTextField();
        txtPRECIO = new javax.swing.JTextField();
        txtCODIGO_P = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtDESCUENTO_P = new javax.swing.JTextField();
        txtLISTA = new javax.swing.JTextField();
        txtPROVEEDOR = new javax.swing.JTextField();
        txtPROPUESTA = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        btnMuestraCodigos = new javax.swing.JButton();
        btnDatosTitulos = new javax.swing.JButton();
        btnDotacionesTitulos = new javax.swing.JButton();
        PanelBotones = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        btnEXCEL = new javax.swing.JButton();
        btnTABLA = new javax.swing.JButton();
        btnCORREO_ELECTRONICO = new javax.swing.JButton();
        PanelInferiorRegistro1 = new javax.swing.JPanel();
        btnSALIR_R1 = new javax.swing.JButton();
        btnGUARDAR_R1 = new javax.swing.JButton();
        btnIMPRIMIR_R1 = new javax.swing.JButton();
        btnELIMINAR_R1 = new javax.swing.JButton();
        PanelPrincipalDotaciones = new javax.swing.JPanel();
        btnVolverArchivoTitulos1 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnDatosTitulos1 = new javax.swing.JButton();
        btnDotacionesTitulos1 = new javax.swing.JButton();
        PanelDeResultados = new javax.swing.JPanel();
        txtMODIFICANDO1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        PanelBotones1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtPROPUESTA_DOTACION = new javax.swing.JTextField();
        btnCARGAR_DOTACION = new javax.swing.JButton();
        btnMODIFICAR_CLIENTE = new javax.swing.JButton();
        btnAJUSTAR_PROMEDIO = new javax.swing.JButton();
        btnAJUSTAR_SUGERIDO = new javax.swing.JButton();
        btnENVIAR_EXCEL = new javax.swing.JButton();
        PanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaTitulos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        PanelPrincipalDatos.setOpaque(false);

        btnVolverArchivoTitulos.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverArchivoTitulos.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverArchivoTitulos.setText("VOLVER");
        btnVolverArchivoTitulos.setBorderPainted(false);
        btnVolverArchivoTitulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverArchivoTitulosActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));

        PanelDatosTitulos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelDatosTitulos.setOpaque(false);

        jLabel1.setText("Datos del Articulo");

        jLabel2.setText("Codigo");

        txtCODIGO.setBackground(new java.awt.Color(204, 204, 204));
        txtCODIGO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCODIGO.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCODIGOMouseClicked(evt);
            }
        });
        txtCODIGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCODIGOActionPerformed(evt);
            }
        });

        txtNOMBRE.setBackground(new java.awt.Color(204, 204, 204));
        txtNOMBRE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNOMBRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNOMBREActionPerformed(evt);
            }
        });

        txtPERIOCIDAD.setBackground(new java.awt.Color(204, 204, 204));
        txtPERIOCIDAD.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtPERIOCIDAD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPERIOCIDADActionPerformed(evt);
            }
        });

        txtPRECIO.setBackground(new java.awt.Color(204, 204, 204));
        txtPRECIO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtPRECIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPRECIOActionPerformed(evt);
            }
        });

        txtCODIGO_P.setBackground(new java.awt.Color(204, 204, 204));
        txtCODIGO_P.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtCODIGO_P.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCODIGO_PActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre");

        jLabel4.setText("Periocidad");

        jLabel5.setText("Precio");

        jLabel6.setText("Codigo Proveedor");

        jLabel8.setText("Descuento Proveedor");

        txtDESCUENTO_P.setBackground(new java.awt.Color(204, 204, 204));
        txtDESCUENTO_P.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtDESCUENTO_P.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDESCUENTO_PActionPerformed(evt);
            }
        });

        txtLISTA.setBackground(new java.awt.Color(204, 204, 204));
        txtLISTA.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtLISTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLISTAActionPerformed(evt);
            }
        });

        txtPROVEEDOR.setBackground(new java.awt.Color(204, 204, 204));
        txtPROVEEDOR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtPROPUESTA.setBackground(new java.awt.Color(204, 204, 204));
        txtPROPUESTA.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel9.setText("Lista");

        jLabel10.setText("Proveedor");

        jLabel11.setText("Propuesta");

        btnMuestraCodigos.setBackground(new java.awt.Color(255, 152, 36));
        btnMuestraCodigos.setText("...");
        btnMuestraCodigos.setBorderPainted(false);
        btnMuestraCodigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuestraCodigosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDatosTitulosLayout = new javax.swing.GroupLayout(PanelDatosTitulos);
        PanelDatosTitulos.setLayout(PanelDatosTitulosLayout);
        PanelDatosTitulosLayout.setHorizontalGroup(
            PanelDatosTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosTitulosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelDatosTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosTitulosLayout.createSequentialGroup()
                        .addGroup(PanelDatosTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelDatosTitulosLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(PanelDatosTitulosLayout.createSequentialGroup()
                                .addComponent(txtCODIGO, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMuestraCodigos, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
                                .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPERIOCIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPRECIO, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtCODIGO_P, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(PanelDatosTitulosLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(239, 239, 239)
                        .addComponent(jLabel4)
                        .addGap(133, 133, 133)
                        .addComponent(jLabel5)
                        .addGap(114, 114, 114)
                        .addComponent(jLabel6)
                        .addGap(38, 38, 38))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDatosTitulosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(txtDESCUENTO_P, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(txtLISTA, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(txtPROVEEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(txtPROPUESTA, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelDatosTitulosLayout.setVerticalGroup(
            PanelDatosTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosTitulosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PanelDatosTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelDatosTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCODIGO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPERIOCIDAD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPRECIO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCODIGO_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMuestraCodigos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDESCUENTO_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLISTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPROVEEDOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPROPUESTA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        btnDatosTitulos.setBackground(new java.awt.Color(255, 152, 36));
        btnDatosTitulos.setText("Datos Generales de Titulos");
        btnDatosTitulos.setBorderPainted(false);

        btnDotacionesTitulos.setBackground(new java.awt.Color(255, 152, 36));
        btnDotacionesTitulos.setText("Dotaciones y Ajustes por Titulo");
        btnDotacionesTitulos.setBorderPainted(false);
        btnDotacionesTitulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDotacionesTitulosActionPerformed(evt);
            }
        });

        PanelBotones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelBotones.setOpaque(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel12.setText("Despliegue Totales Recibidos y Devueltos ");

        btnEXCEL.setBackground(new java.awt.Color(255, 152, 36));
        btnEXCEL.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEXCEL.setText("Enviar a Excel");
        btnEXCEL.setBorderPainted(false);

        btnTABLA.setBackground(new java.awt.Color(255, 152, 36));
        btnTABLA.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnTABLA.setText("Imprimir Tabla");
        btnTABLA.setBorderPainted(false);

        btnCORREO_ELECTRONICO.setBackground(new java.awt.Color(255, 152, 36));
        btnCORREO_ELECTRONICO.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnCORREO_ELECTRONICO.setText("Correo Electronico");
        btnCORREO_ELECTRONICO.setBorderPainted(false);

        javax.swing.GroupLayout PanelBotonesLayout = new javax.swing.GroupLayout(PanelBotones);
        PanelBotones.setLayout(PanelBotonesLayout);
        PanelBotonesLayout.setHorizontalGroup(
            PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(395, 395, 395))
            .addGroup(PanelBotonesLayout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(btnEXCEL, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTABLA, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCORREO_ELECTRONICO, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelBotonesLayout.setVerticalGroup(
            PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(PanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCORREO_ELECTRONICO, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTABLA, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEXCEL, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        PanelInferiorRegistro1.setOpaque(false);

        btnSALIR_R1.setBackground(new java.awt.Color(255, 152, 36));
        btnSALIR_R1.setText("Salir");
        btnSALIR_R1.setBorderPainted(false);
        btnSALIR_R1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSALIR_R1ActionPerformed(evt);
            }
        });

        btnGUARDAR_R1.setBackground(new java.awt.Color(255, 152, 36));
        btnGUARDAR_R1.setText("Guardar");
        btnGUARDAR_R1.setBorderPainted(false);
        btnGUARDAR_R1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGUARDAR_R1ActionPerformed(evt);
            }
        });

        btnIMPRIMIR_R1.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_R1.setText("Imprimir");
        btnIMPRIMIR_R1.setBorderPainted(false);

        btnELIMINAR_R1.setBackground(new java.awt.Color(255, 152, 36));
        btnELIMINAR_R1.setText("Eliminar");
        btnELIMINAR_R1.setBorderPainted(false);
        btnELIMINAR_R1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnELIMINAR_R1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelInferiorRegistro1Layout = new javax.swing.GroupLayout(PanelInferiorRegistro1);
        PanelInferiorRegistro1.setLayout(PanelInferiorRegistro1Layout);
        PanelInferiorRegistro1Layout.setHorizontalGroup(
            PanelInferiorRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorRegistro1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnELIMINAR_R1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnIMPRIMIR_R1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGUARDAR_R1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSALIR_R1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelInferiorRegistro1Layout.setVerticalGroup(
            PanelInferiorRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorRegistro1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelInferiorRegistro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSALIR_R1)
                    .addComponent(btnGUARDAR_R1)
                    .addComponent(btnIMPRIMIR_R1)
                    .addComponent(btnELIMINAR_R1))
                .addContainerGap())
        );

        javax.swing.GroupLayout PanelPrincipalDatosLayout = new javax.swing.GroupLayout(PanelPrincipalDatos);
        PanelPrincipalDatos.setLayout(PanelPrincipalDatosLayout);
        PanelPrincipalDatosLayout.setHorizontalGroup(
            PanelPrincipalDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPrincipalDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(PanelDatosTitulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelPrincipalDatosLayout.createSequentialGroup()
                        .addComponent(btnDatosTitulos, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnDotacionesTitulos, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelPrincipalDatosLayout.createSequentialGroup()
                        .addComponent(btnVolverArchivoTitulos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PanelBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalDatosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelInferiorRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelPrincipalDatosLayout.setVerticalGroup(
            PanelPrincipalDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalDatosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVolverArchivoTitulos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPrincipalDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatosTitulos)
                    .addComponent(btnDotacionesTitulos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelDatosTitulos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                .addComponent(PanelInferiorRegistro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        PanelPrincipalDotaciones.setOpaque(false);

        btnVolverArchivoTitulos1.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverArchivoTitulos1.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverArchivoTitulos1.setText("VOLVER");
        btnVolverArchivoTitulos1.setBorderPainted(false);
        btnVolverArchivoTitulos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverArchivoTitulos1ActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));

        btnDatosTitulos1.setBackground(new java.awt.Color(255, 152, 36));
        btnDatosTitulos1.setText("Datos Generales de Titulos");
        btnDatosTitulos1.setBorderPainted(false);
        btnDatosTitulos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosTitulos1ActionPerformed(evt);
            }
        });

        btnDotacionesTitulos1.setBackground(new java.awt.Color(255, 152, 36));
        btnDotacionesTitulos1.setText("Dotaciones y Ajustes por Titulo");
        btnDotacionesTitulos1.setBorderPainted(false);

        PanelDeResultados.setOpaque(false);

        txtMODIFICANDO1.setBackground(new java.awt.Color(204, 204, 204));
        txtMODIFICANDO1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel15.setText("Modificando");

        javax.swing.GroupLayout PanelDeResultadosLayout = new javax.swing.GroupLayout(PanelDeResultados);
        PanelDeResultados.setLayout(PanelDeResultadosLayout);
        PanelDeResultadosLayout.setHorizontalGroup(
            PanelDeResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelDeResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(34, 34, 34)
                .addComponent(txtMODIFICANDO1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelDeResultadosLayout.setVerticalGroup(
            PanelDeResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDeResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(txtMODIFICANDO1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addComponent(jLabel15))
        );

        PanelBotones1.setOpaque(false);

        jLabel13.setText("Propuesta");

        txtPROPUESTA_DOTACION.setBackground(new java.awt.Color(204, 204, 204));
        txtPROPUESTA_DOTACION.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnCARGAR_DOTACION.setBackground(new java.awt.Color(255, 152, 36));
        btnCARGAR_DOTACION.setText("Cargar Dotacion");
        btnCARGAR_DOTACION.setBorderPainted(false);
        btnCARGAR_DOTACION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCARGAR_DOTACIONActionPerformed(evt);
            }
        });

        btnMODIFICAR_CLIENTE.setBackground(new java.awt.Color(255, 152, 36));
        btnMODIFICAR_CLIENTE.setText("Modificar Cliente");
        btnMODIFICAR_CLIENTE.setBorderPainted(false);

        btnAJUSTAR_PROMEDIO.setBackground(new java.awt.Color(255, 152, 36));
        btnAJUSTAR_PROMEDIO.setText("Ajustar Promedio");
        btnAJUSTAR_PROMEDIO.setBorderPainted(false);

        btnAJUSTAR_SUGERIDO.setBackground(new java.awt.Color(255, 152, 36));
        btnAJUSTAR_SUGERIDO.setText("Ajustar Sugerido");
        btnAJUSTAR_SUGERIDO.setBorderPainted(false);

        btnENVIAR_EXCEL.setBackground(new java.awt.Color(255, 152, 36));
        btnENVIAR_EXCEL.setText("Enviar a Excel");
        btnENVIAR_EXCEL.setBorderPainted(false);

        javax.swing.GroupLayout PanelBotones1Layout = new javax.swing.GroupLayout(PanelBotones1);
        PanelBotones1.setLayout(PanelBotones1Layout);
        PanelBotones1Layout.setHorizontalGroup(
            PanelBotones1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBotones1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelBotones1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBotones1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBotones1Layout.createSequentialGroup()
                        .addComponent(txtPROPUESTA_DOTACION, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
            .addGroup(PanelBotones1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(PanelBotones1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnENVIAR_EXCEL, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAJUSTAR_SUGERIDO, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAJUSTAR_PROMEDIO, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMODIFICAR_CLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCARGAR_DOTACION, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 19, Short.MAX_VALUE))
        );
        PanelBotones1Layout.setVerticalGroup(
            PanelBotones1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBotones1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPROPUESTA_DOTACION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(btnCARGAR_DOTACION)
                .addGap(18, 18, 18)
                .addComponent(btnMODIFICAR_CLIENTE)
                .addGap(18, 18, 18)
                .addComponent(btnAJUSTAR_PROMEDIO)
                .addGap(18, 18, 18)
                .addComponent(btnAJUSTAR_SUGERIDO)
                .addGap(18, 18, 18)
                .addComponent(btnENVIAR_EXCEL)
                .addContainerGap(114, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(204, 204, 204));

        TablaTitulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero", "Nombre", "P/D3", "P/D2", "P/D1", "Promedio", "Sugerido", "Dotacion"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TablaTitulos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaTitulosKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TablaTitulos);
        if (TablaTitulos.getColumnModel().getColumnCount() > 0) {
            TablaTitulos.getColumnModel().getColumn(0).setPreferredWidth(5);
            TablaTitulos.getColumnModel().getColumn(1).setPreferredWidth(250);
            TablaTitulos.getColumnModel().getColumn(2).setPreferredWidth(50);
            TablaTitulos.getColumnModel().getColumn(3).setPreferredWidth(50);
            TablaTitulos.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        javax.swing.GroupLayout PanelTablaLayout = new javax.swing.GroupLayout(PanelTabla);
        PanelTabla.setLayout(PanelTablaLayout);
        PanelTablaLayout.setHorizontalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        PanelTablaLayout.setVerticalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 526, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PanelPrincipalDotacionesLayout = new javax.swing.GroupLayout(PanelPrincipalDotaciones);
        PanelPrincipalDotaciones.setLayout(PanelPrincipalDotacionesLayout);
        PanelPrincipalDotacionesLayout.setHorizontalGroup(
            PanelPrincipalDotacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalDotacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelPrincipalDotacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(PanelPrincipalDotacionesLayout.createSequentialGroup()
                        .addComponent(btnVolverArchivoTitulos1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PanelPrincipalDotacionesLayout.createSequentialGroup()
                        .addComponent(btnDatosTitulos1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                        .addComponent(btnDotacionesTitulos1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelPrincipalDotacionesLayout.createSequentialGroup()
                        .addGroup(PanelPrincipalDotacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PanelDeResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(PanelBotones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        PanelPrincipalDotacionesLayout.setVerticalGroup(
            PanelPrincipalDotacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelPrincipalDotacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVolverArchivoTitulos1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelPrincipalDotacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDatosTitulos1)
                    .addComponent(btnDotacionesTitulos1))
                .addGroup(PanelPrincipalDotacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelPrincipalDotacionesLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(PanelBotones1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(155, Short.MAX_VALUE))
                    .addGroup(PanelPrincipalDotacionesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(PanelDeResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipalDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalDotaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelPrincipalDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PanelPrincipalDotaciones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /*----------------------------Metodos propios con diferentes usos------------------------------*/
    private void salir(){
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }
/*Metodo que limpia los cuadros de texto*/    
    private void Limpiar(){
        txtNOMBRE.setText("");
        txtPERIOCIDAD.setText("");
        txtPRECIO.setText("");
        txtCODIGO_P.setText("");
        txtDESCUENTO_P.setText("");
        txtLISTA.setText("");
        txtPROVEEDOR.setText("");
        txtPROPUESTA.setText("");
        Condicional = 0;
    }
    /*
    public int SumaDotacion(){
        int contar = TablaTitulos.getRowCount();
        int suma = 0;
        for (int i = 0; i < contar; i++){
            suma = suma +Integer.parseInt(TablaTitulos.getValueAt(i, 7).toString());
        }
        return suma; -  
    }*/
/*---------------------Metodos generados por NetBeans-----------------------------------------*/
    private void btnDotacionesTitulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDotacionesTitulosActionPerformed
        PanelPrincipalDotaciones.setVisible(true);
        PanelPrincipalDatos.setVisible(false);
        DefaultTableModel model = (DefaultTableModel) TablaTitulos.getModel();
        model.setRowCount(0); TablaTitulos.setModel(model);  
        String Codigo = String.valueOf(txtCODIGO.getText());
        if(!txtNOMBRE.getText().isEmpty())
        {//cada quien saba qhacer ocn este edo en la torre ahora que debo de hacer
            try{
            //Cargmos los valores de la columna 1 y 2 (Nro cliente y nombre)
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Nro_Cliente, Nombre, "+Codigo+" FROM Clientes ORDER BY Nro_Cliente ASC;");
            //Cargamos los valores de las columnas 3, 4 y 5 (Ultima venta / ultima devolucion)
            
            //Cargamos los valores de la columna 6 (promedio de dotacion venta-devolucion)
            
            //Cargamos los valores de la columna 7 (sugerencia de dotacion segun datos)
            
            //Cargamos los datos de la columna 8 (datos de dotacion de cada producto
            
            while(rs.next()){
                String Nro_Cliente = rs.getString("Nro_Cliente");
                String Nombre = rs.getString("Nombre");
                String Dot = rs.getString(""+Codigo+"");
                model.addRow(new Object[]{Nro_Cliente, Nombre, null, null, null, null, null, Dot});
            }
            TablaTitulos.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en cargar dotacion: " + ex);
        }
        }else{
            JOptionPane.showMessageDialog(null, "Ingrese un titulo ");
        }
        
        int contar = TablaTitulos.getRowCount();
        int suma = 0;
        for (int i = 0; i < contar; i++){
            Object ValorCelda = TablaTitulos.getValueAt(i, 7);
            if(ValorCelda!=null){
                String ValorTexto = ValorCelda.toString();
            if(!ValorTexto.isEmpty()){
            try{
                suma += Integer.parseInt(ValorTexto);
            } catch(NumberFormatException e){
                System.out.print("Error: "+ e);
                    }
                }
            }
        }
        txtPROPUESTA_DOTACION.setText(Integer.toString(suma));
        String Titu = txtNOMBRE.getText();
        txtMODIFICANDO1.setText(Titu);
        /*------Este codigo hace que se lleve a cabo el focus en la celda-----------*/
        SwingUtilities.invokeLater(() -> {
        TablaTitulos.changeSelection(0, 7, false, false);
        TablaTitulos.editCellAt(0, 7);
        });
        model.addRow(new Object[]{null, "Totales:", null, null, null, null, null, suma});
    }//GEN-LAST:event_btnDotacionesTitulosActionPerformed

    private void btnVolverArchivoTitulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverArchivoTitulosActionPerformed
        salir();
    }//GEN-LAST:event_btnVolverArchivoTitulosActionPerformed

    private void btnVolverArchivoTitulos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverArchivoTitulos1ActionPerformed
        // TODO add your handling code here:
        salir();
    }//GEN-LAST:event_btnVolverArchivoTitulos1ActionPerformed

    private void btnDatosTitulos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosTitulos1ActionPerformed
        // TODO add your handling code here:
        PanelPrincipalDotaciones.setVisible(false);
        PanelPrincipalDatos.setVisible(true);
        GuardarDotacion();
    }//GEN-LAST:event_btnDatosTitulos1ActionPerformed

    private void txtCODIGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCODIGOActionPerformed
            if(txtCODIGO.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un codigo");
            btnGUARDAR_R1.setEnabled(false);
            btnELIMINAR_R1.setEnabled(false);
            txtCODIGO.requestFocus();
            Limpiar();
        }else{
        btnGUARDAR_R1.setEnabled(true);
        btnELIMINAR_R1.setEnabled(true);    
        String Cod = String.valueOf(txtCODIGO.getText());
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT * FROM titulos WHERE Codigo = '"+Cod+"';");
            if(rs.next()){
                txtCODIGO.setText(rs.getString("Codigo"));
                txtNOMBRE.setText(rs.getString("Nombre"));
                txtPERIOCIDAD.setText(rs.getString("Periocidad"));
                txtPRECIO.setText(rs.getString("Precio"));
                txtCODIGO_P.setText(rs.getString("CodigoProveedor"));
                txtDESCUENTO_P.setText(rs.getString("DescuentoProveedor"));
                txtLISTA.setText(rs.getString("Lista"));
                txtPROVEEDOR.setText(rs.getString("Proveedor"));
                txtPROPUESTA.setText(rs.getString("Propuesta"));
                Condicional = 1;
            }else
            {
                txtNOMBRE.requestFocus();
                Limpiar();
            }
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
        }
    }//GEN-LAST:event_txtCODIGOActionPerformed

    private void txtNOMBREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNOMBREActionPerformed
        txtPERIOCIDAD.requestFocus();
    }//GEN-LAST:event_txtNOMBREActionPerformed

    private void txtPERIOCIDADActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPERIOCIDADActionPerformed
        txtPRECIO.requestFocus();
    }//GEN-LAST:event_txtPERIOCIDADActionPerformed

    private void txtPRECIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPRECIOActionPerformed
        txtCODIGO_P.requestFocus();
    }//GEN-LAST:event_txtPRECIOActionPerformed

    private void txtCODIGO_PActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCODIGO_PActionPerformed
        int Num = Integer.parseInt(txtCODIGO_P.getText());
        try{
              Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
              Statement smnt = Con.createStatement();
              ResultSet rs = smnt.executeQuery("SELECT * FROM Proveedores WHERE Nro_Proveedor="+Num+";");
              if(rs.next()){
                  txtCODIGO_P.setText(rs.getString("Nro_Proveedor"));
                  txtDESCUENTO_P.setText(rs.getString("Descuento_P"));
                  txtPROVEEDOR.setText(rs.getString("Nombre"));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error obtener datos proveedor: "+ex);
            }
        txtLISTA.requestFocus();
    }//GEN-LAST:event_txtCODIGO_PActionPerformed

    private void txtLISTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLISTAActionPerformed
        GuardarActualizar();
    }//GEN-LAST:event_txtLISTAActionPerformed

    private void btnMuestraCodigosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuestraCodigosActionPerformed
        ArchivoTitulosMostrar TM = new ArchivoTitulosMostrar(this);
        TM.setVisible(true);
        TM.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnMuestraCodigosActionPerformed

    private void btnSALIR_R1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSALIR_R1ActionPerformed
        salir();
    }//GEN-LAST:event_btnSALIR_R1ActionPerformed

    private void btnGUARDAR_R1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGUARDAR_R1ActionPerformed
        GuardarActualizar();
    }//GEN-LAST:event_btnGUARDAR_R1ActionPerformed

    private void txtDESCUENTO_PActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDESCUENTO_PActionPerformed
        txtLISTA.requestFocus();
    }//GEN-LAST:event_txtDESCUENTO_PActionPerformed

    private void btnCARGAR_DOTACIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCARGAR_DOTACIONActionPerformed
        GuardarDotacion();      
    }//GEN-LAST:event_btnCARGAR_DOTACIONActionPerformed

    
    private void TablaTitulosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaTitulosKeyReleased
       int dotacion = 0; int Nro_C = 0; int id = 0;
/*--Aqui obtenemos los valores de las celdas Numero cliente y dotacion--------------------------*/
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        // Obtén la fila y la columna seleccionadas
        int row = TablaTitulos.getSelectedRow(); int column = TablaTitulos.getSelectedColumn();
        
        // Verifica que la fila y columna sean válidas
        if (row >= 0 && column >= 0) {
            DefaultTableModel model = (DefaultTableModel) TablaTitulos.getModel();

            try {
                Object currentCellValue = model.getValueAt(row, column);
                if (currentCellValue != null) {
                    dotacion = Integer.parseInt(currentCellValue.toString());}
                
                Object firstColumnValue = model.getValueAt(row, 0);
                if (firstColumnValue != null) {
                    Nro_C = Integer.parseInt(firstColumnValue.toString());}
                
            } catch (NumberFormatException e) {
                // Maneja el caso en que el valor no sea un número entero
                System.err.println("Error al convertir el valor a int: " + e.getMessage());
            }
        }
    }
/*------Seleccionamos el id del cliente--------------------------*/
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
             ResultSet rs = smnt.executeQuery("SELECT id FROM clientes WHERE Nro_Cliente = "+Nro_C+";");
            if(rs.next()){
                id = rs.getInt("id");
            }
            Con.close(); smnt.close(); rs.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Falla al obtener el id: "+ex);
        }
/*------Seccion de codigo que guarda los datos en la tabla--------------------------------------*/
        String Titulo = txtCODIGO.getText();
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            smnt.executeUpdate("UPDATE clientes SET "+Titulo+" = "+dotacion+" "
                    + "WHERE id = "+id+";");
            Con.close(); smnt.close();
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Falla al guardar dotacion: "+ex);
        }
/*------Seccion de codigo que hace la sumatoria de las dotaciones-------------------------------*/
        int contar = TablaTitulos.getRowCount();
        int suma = 0;
        for (int i = 0; i < contar-1; i++){
            Object ValorCelda = TablaTitulos.getValueAt(i, 7);
            if(ValorCelda!=null){
                String ValorTexto = ValorCelda.toString();
            if(!ValorTexto.isEmpty()){
            try{
                suma += Integer.parseInt(ValorTexto);
            } catch(NumberFormatException e){
                System.out.print("Error: "+ e);
                    }
                }
            }
        }
        txtPROPUESTA_DOTACION.setText(Integer.toString(suma));
/*-------------------------------------------------------------------------------------------*/
        int row = TablaTitulos.getSelectedRow(); int column = TablaTitulos.getSelectedColumn();
        int filaActual = TablaTitulos.getSelectedRow();
        int columnaActual = TablaTitulos.getSelectedColumn();

        // Verifica si está en la última celda
        if (filaActual == TablaTitulos.getRowCount() - 2) {
            // Mueve el foco a la primera celda de la última columna
            TablaTitulos.changeSelection(filaActual-1, columnaActual, false, false);
        }
        else{
            TablaTitulos.changeSelection(row + 1, column, false, false);
        }
        javax.swing.table.TableModel model1 = TablaTitulos.getModel();
        model1.setValueAt(suma, TablaTitulos.getRowCount()-1, 7);
    }//GEN-LAST:event_TablaTitulosKeyReleased
    
    
    private void btnELIMINAR_R1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnELIMINAR_R1ActionPerformed
        // TODO add your handling code here:
        if(txtCODIGO.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese un titulo");
        }
        else{
            String Codigo = String.valueOf(txtCODIGO.getText());
            try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            smnt.executeUpdate("DELETE FROM titulos WHERE Codigo='"+Codigo+"';");
            smnt.executeUpdate("ALTER TABLE clientes DROP COLUMN "+Codigo+";");
            Limpiar();
            txtCODIGO.setText("");
            txtCODIGO.requestFocus();
            JOptionPane.showMessageDialog(null, "Datos eliminados");
            btnGUARDAR_R1.setEnabled(false);
            btnELIMINAR_R1.setEnabled(false);
            }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al eliminar titulo: "+ex);
            }
        }
    }//GEN-LAST:event_btnELIMINAR_R1ActionPerformed

    
    private void txtCODIGOMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCODIGOMouseClicked
        // TODO add your handling code here:
        Limpiar();
        txtCODIGO.setText("");
        txtCODIGO.requestFocus();
    }//GEN-LAST:event_txtCODIGOMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBotones;
    private javax.swing.JPanel PanelBotones1;
    private javax.swing.JPanel PanelDatosTitulos;
    private javax.swing.JPanel PanelDeResultados;
    private javax.swing.JPanel PanelInferiorRegistro1;
    private javax.swing.JPanel PanelPrincipalDatos;
    private javax.swing.JPanel PanelPrincipalDotaciones;
    private javax.swing.JPanel PanelTabla;
    private javax.swing.JTable TablaTitulos;
    private javax.swing.JButton btnAJUSTAR_PROMEDIO;
    private javax.swing.JButton btnAJUSTAR_SUGERIDO;
    private javax.swing.JButton btnCARGAR_DOTACION;
    private javax.swing.JButton btnCORREO_ELECTRONICO;
    private javax.swing.JButton btnDatosTitulos;
    private javax.swing.JButton btnDatosTitulos1;
    private javax.swing.JButton btnDotacionesTitulos;
    private javax.swing.JButton btnDotacionesTitulos1;
    private javax.swing.JButton btnELIMINAR_R1;
    private javax.swing.JButton btnENVIAR_EXCEL;
    private javax.swing.JButton btnEXCEL;
    private javax.swing.JButton btnGUARDAR_R1;
    private javax.swing.JButton btnIMPRIMIR_R1;
    private javax.swing.JButton btnMODIFICAR_CLIENTE;
    private javax.swing.JButton btnMuestraCodigos;
    private javax.swing.JButton btnSALIR_R1;
    private javax.swing.JButton btnTABLA;
    private javax.swing.JButton btnVolverArchivoTitulos;
    private javax.swing.JButton btnVolverArchivoTitulos1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtCODIGO;
    private javax.swing.JTextField txtCODIGO_P;
    private javax.swing.JTextField txtDESCUENTO_P;
    private javax.swing.JTextField txtLISTA;
    private javax.swing.JTextField txtMODIFICANDO1;
    private javax.swing.JTextField txtNOMBRE;
    private javax.swing.JTextField txtPERIOCIDAD;
    private javax.swing.JTextField txtPRECIO;
    private javax.swing.JTextField txtPROPUESTA;
    private javax.swing.JTextField txtPROPUESTA_DOTACION;
    private javax.swing.JTextField txtPROVEEDOR;
    // End of variables declaration//GEN-END:variables
}
