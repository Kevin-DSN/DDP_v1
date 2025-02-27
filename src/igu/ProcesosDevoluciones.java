package igu;
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
public class ProcesosDevoluciones extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    
    public ProcesosDevoluciones() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        this.setExtendedState(this.MAXIMIZED_BOTH);
    }
    
    public int GenerarFolio(){
    int Folio =0;
    try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT MAX(FolioDevo) FROM compras_cliente;");
            while(rs.next()){
                Folio = rs.getInt(1);
            }
            Con.close(); smnt.close(); rs.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al generar numero de folio: "+ex);
        }
        return Folio += 1;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolverProcesosDevoluciones = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        PanelFechas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        dtFechaDevolucion = new com.toedter.calendar.JDateChooser();
        dtFechaDesde = new com.toedter.calendar.JDateChooser();
        dtFechaHasta = new com.toedter.calendar.JDateChooser();
        PanelDatos = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNUMERO = new javax.swing.JTextField();
        txtNOMBRE = new javax.swing.JTextField();
        btnBUSQUEDA = new javax.swing.JButton();
        btnCARGAR_DEVOLUCION = new javax.swing.JButton();
        PanelInferior = new javax.swing.JPanel();
        btnSALIR = new javax.swing.JButton();
        btnAGREGAR_TITULO = new javax.swing.JButton();
        btnIMPRIMIR_DEVOLUCION = new javax.swing.JButton();
        PanelResultados = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtIMPORTE_TOTAL = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDESCUENTO_PERIODICO = new javax.swing.JTextField();
        txtIMPORTE_DESCONTAR = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtSALDO_ANTERIOR = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtDEVOLUCION_PERIODICO = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTOTAL_PAGO = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        btnAPLICAR_DEVOLUCION = new javax.swing.JButton();
        PanelTabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProcesos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        btnVolverProcesosDevoluciones.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverProcesosDevoluciones.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverProcesosDevoluciones.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverProcesosDevoluciones.setText("VOLVER");
        btnVolverProcesosDevoluciones.setBorderPainted(false);
        btnVolverProcesosDevoluciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverProcesosDevolucionesActionPerformed(evt);
            }
        });

        jLabel1.setText("Devoluciones");

        jSeparator1.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));

        PanelFechas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelFechas.setOpaque(false);

        jLabel2.setText("Fecha de devolucion");

        jLabel3.setText("Desde");

        jLabel4.setText("Hasta");

        dtFechaDevolucion.setBackground(new java.awt.Color(204, 204, 204));
        dtFechaDevolucion.setForeground(new java.awt.Color(0, 0, 0));

        dtFechaDesde.setBackground(new java.awt.Color(204, 204, 204));
        dtFechaDesde.setForeground(new java.awt.Color(0, 0, 0));

        dtFechaHasta.setBackground(new java.awt.Color(204, 204, 204));
        dtFechaHasta.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout PanelFechasLayout = new javax.swing.GroupLayout(PanelFechas);
        PanelFechas.setLayout(PanelFechasLayout);
        PanelFechasLayout.setHorizontalGroup(
            PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFechasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(dtFechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(PanelFechasLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PanelFechasLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFechasLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(55, 55, 55))
                    .addGroup(PanelFechasLayout.createSequentialGroup()
                        .addComponent(dtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        PanelFechasLayout.setVerticalGroup(
            PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFechasLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dtFechaDevolucion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelFechasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dtFechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtFechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        PanelDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelDatos.setOpaque(false);

        jLabel5.setText("Numero:");

        txtNUMERO.setBackground(new java.awt.Color(204, 204, 204));
        txtNUMERO.setForeground(new java.awt.Color(0, 0, 0));
        txtNUMERO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNUMERO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNUMEROActionPerformed(evt);
            }
        });

        txtNOMBRE.setBackground(new java.awt.Color(204, 204, 204));
        txtNOMBRE.setForeground(new java.awt.Color(0, 0, 0));
        txtNOMBRE.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        btnBUSQUEDA.setBackground(new java.awt.Color(255, 152, 36));
        btnBUSQUEDA.setForeground(new java.awt.Color(0, 0, 0));
        btnBUSQUEDA.setText("Busqueda");
        btnBUSQUEDA.setBorderPainted(false);
        btnBUSQUEDA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBUSQUEDAActionPerformed(evt);
            }
        });

        btnCARGAR_DEVOLUCION.setBackground(new java.awt.Color(255, 152, 36));
        btnCARGAR_DEVOLUCION.setForeground(new java.awt.Color(0, 0, 0));
        btnCARGAR_DEVOLUCION.setText("Cargar Devolucion");
        btnCARGAR_DEVOLUCION.setBorderPainted(false);
        btnCARGAR_DEVOLUCION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCARGAR_DEVOLUCIONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelDatosLayout = new javax.swing.GroupLayout(PanelDatos);
        PanelDatos.setLayout(PanelDatosLayout);
        PanelDatosLayout.setHorizontalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(txtNUMERO, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBUSQUEDA, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelDatosLayout.createSequentialGroup()
                        .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCARGAR_DEVOLUCION, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(333, Short.MAX_VALUE))
        );
        PanelDatosLayout.setVerticalGroup(
            PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelDatosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtNUMERO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBUSQUEDA))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCARGAR_DEVOLUCION))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelInferior.setOpaque(false);

        btnSALIR.setBackground(new java.awt.Color(255, 152, 36));
        btnSALIR.setForeground(new java.awt.Color(0, 0, 0));
        btnSALIR.setText("Salir");
        btnSALIR.setBorderPainted(false);

        btnAGREGAR_TITULO.setBackground(new java.awt.Color(255, 152, 36));
        btnAGREGAR_TITULO.setForeground(new java.awt.Color(0, 0, 0));
        btnAGREGAR_TITULO.setText("Agregar Titulo");
        btnAGREGAR_TITULO.setBorderPainted(false);

        btnIMPRIMIR_DEVOLUCION.setBackground(new java.awt.Color(255, 152, 36));
        btnIMPRIMIR_DEVOLUCION.setForeground(new java.awt.Color(0, 0, 0));
        btnIMPRIMIR_DEVOLUCION.setText("Imprimir Devolucion");
        btnIMPRIMIR_DEVOLUCION.setBorderPainted(false);

        javax.swing.GroupLayout PanelInferiorLayout = new javax.swing.GroupLayout(PanelInferior);
        PanelInferior.setLayout(PanelInferiorLayout);
        PanelInferiorLayout.setHorizontalGroup(
            PanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnIMPRIMIR_DEVOLUCION, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAGREGAR_TITULO, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSALIR, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PanelInferiorLayout.setVerticalGroup(
            PanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelInferiorLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSALIR)
                    .addComponent(btnAGREGAR_TITULO)
                    .addComponent(btnIMPRIMIR_DEVOLUCION))
                .addContainerGap())
        );

        PanelResultados.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelResultados.setOpaque(false);

        jLabel6.setText("Estado de Cuenta de Devoluciones");

        jLabel7.setText("Importe Total de Devoluciones");

        txtIMPORTE_TOTAL.setBackground(new java.awt.Color(204, 204, 204));
        txtIMPORTE_TOTAL.setForeground(new java.awt.Color(0, 0, 0));
        txtIMPORTE_TOTAL.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel8.setText("Descuento de Periodicos");

        txtDESCUENTO_PERIODICO.setBackground(new java.awt.Color(204, 204, 204));
        txtDESCUENTO_PERIODICO.setForeground(new java.awt.Color(0, 0, 0));
        txtDESCUENTO_PERIODICO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        txtIMPORTE_DESCONTAR.setBackground(new java.awt.Color(204, 204, 204));
        txtIMPORTE_DESCONTAR.setForeground(new java.awt.Color(0, 0, 0));
        txtIMPORTE_DESCONTAR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel9.setText("Importe a Descontar");

        jLabel10.setText("Saldo Anterior");

        txtSALDO_ANTERIOR.setBackground(new java.awt.Color(204, 204, 204));
        txtSALDO_ANTERIOR.setForeground(new java.awt.Color(0, 0, 0));
        txtSALDO_ANTERIOR.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel11.setText("Devolucion de Periodicos");

        txtDEVOLUCION_PERIODICO.setBackground(new java.awt.Color(204, 204, 204));
        txtDEVOLUCION_PERIODICO.setForeground(new java.awt.Color(0, 0, 0));
        txtDEVOLUCION_PERIODICO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jLabel12.setText("Total a Pagar");

        txtTOTAL_PAGO.setBackground(new java.awt.Color(204, 204, 204));
        txtTOTAL_PAGO.setForeground(new java.awt.Color(0, 0, 0));
        txtTOTAL_PAGO.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jSeparator2.setBackground(new java.awt.Color(102, 102, 102));
        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));

        btnAPLICAR_DEVOLUCION.setBackground(new java.awt.Color(255, 152, 36));
        btnAPLICAR_DEVOLUCION.setForeground(new java.awt.Color(0, 0, 0));
        btnAPLICAR_DEVOLUCION.setText("Aplicar Devolucion");
        btnAPLICAR_DEVOLUCION.setBorderPainted(false);
        btnAPLICAR_DEVOLUCION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAPLICAR_DEVOLUCIONActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelResultadosLayout = new javax.swing.GroupLayout(PanelResultados);
        PanelResultados.setLayout(PanelResultadosLayout);
        PanelResultadosLayout.setHorizontalGroup(
            PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                        .addGap(0, 37, Short.MAX_VALUE)
                        .addGroup(PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(64, 64, 64))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                                .addGroup(PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDESCUENTO_PERIODICO, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIMPORTE_TOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIMPORTE_DESCONTAR, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtSALDO_ANTERIOR, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDEVOLUCION_PERIODICO, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTOTAL_PAGO, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(78, 78, 78))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(88, 88, 88))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(107, 107, 107))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(78, 78, 78))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelResultadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(112, 112, 112))
            .addGroup(PanelResultadosLayout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(btnAPLICAR_DEVOLUCION, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelResultadosLayout.setVerticalGroup(
            PanelResultadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelResultadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIMPORTE_TOTAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDESCUENTO_PERIODICO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIMPORTE_DESCONTAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSALDO_ANTERIOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDEVOLUCION_PERIODICO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTOTAL_PAGO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnAPLICAR_DEVOLUCION, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        TablaProcesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Entregada", "Devuelta", "Nombre", "Edicion", "Precio", "Total"
            }
        ));
        jScrollPane1.setViewportView(TablaProcesos);

        javax.swing.GroupLayout PanelTablaLayout = new javax.swing.GroupLayout(PanelTabla);
        PanelTabla.setLayout(PanelTablaLayout);
        PanelTablaLayout.setHorizontalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 924, Short.MAX_VALUE))
        );
        PanelTablaLayout.setVerticalGroup(
            PanelTablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(PanelResultados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(btnVolverProcesosDevoluciones)
                        .addGap(527, 527, 527)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PanelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(PanelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverProcesosDevoluciones)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PanelDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelResultados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelInferior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverProcesosDevolucionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverProcesosDevolucionesActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverProcesosDevolucionesActionPerformed

    private void txtNUMEROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNUMEROActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) TablaProcesos.getModel();
        model.setRowCount(0);
        int NrC = Integer.parseInt(txtNUMERO.getText());
        String PrimeraF;
        String SegundaF;
        
        Date Fech1 = dtFechaDesde.getDate();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        PrimeraF = sdf1.format(Fech1);
        
        Date Fech2 = dtFechaHasta.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        SegundaF = sdf2.format(Fech2);
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Cantidad, CantidadDevo, Titulo, Edicion, Precio, ImporteDevo FROM compras_cliente WHERE "
            + "id_Cliente = "+NrC+" AND FechaRecibido BETWEEN '"+PrimeraF+"' AND '"+SegundaF+"';");
            while(rs.next()){
                String Cantidad = rs.getString("Cantidad");
                String CantidadD = rs.getString("CantidadDevo");
                String Titulo = rs.getString("Titulo");
                String Edicion = rs.getString("Edicion");
                String Precio = rs.getString("Precio");
                String ImporteD = rs.getString("ImporteDevo");
                
                model.addRow(new Object[]{Cantidad, CantidadD,  Titulo, Edicion, Precio, ImporteD});
            }
            Con.close(); smnt.close(); rs.close();
            }catch(SQLException ex){
                
            }
    }//GEN-LAST:event_txtNUMEROActionPerformed

    private void btnBUSQUEDAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBUSQUEDAActionPerformed
        // TODO add your handling code here:
        /*ArchivoClientessMostrar CM = new ArchivoClientessMostrar();
        CM.setVisible(true);
        CM.setLocationRelativeTo(null);*/
    }//GEN-LAST:event_btnBUSQUEDAActionPerformed

    private void btnCARGAR_DEVOLUCIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCARGAR_DEVOLUCIONActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) TablaProcesos.getModel(); double Saldo = 0;
        double ImporteT = 0;
        for(int i = 0; i < model.getRowCount(); i++){
            int Devo = Integer.parseInt(model.getValueAt(i, 1).toString());
            double Precio = Double.parseDouble(model.getValueAt(i, 4).toString());
            double Total = Devo * Precio;
            ImporteT = ImporteT + Total;
            model.setValueAt(Total, i, 5);
        }
        
        try{
            String NroC = String.valueOf(txtNUMERO.getText());
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT SaldoActual FROM clientes "
            + "WHERE Nro_Cliente = "+NroC+";");
            while(rs.next()){
                Saldo=rs.getDouble("SaldoActual");
            }
            txtSALDO_ANTERIOR.setText(String.valueOf(Saldo));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error mostrar estado de cuenta: "+ex);
        }
        double Pago = Saldo + ImporteT;
        txtTOTAL_PAGO.setText(String.valueOf(Pago));
        txtIMPORTE_TOTAL.setText(String.valueOf(ImporteT));
        txtDESCUENTO_PERIODICO.setText("0.0");
        txtIMPORTE_DESCONTAR.setText(String.valueOf(ImporteT));
        txtDEVOLUCION_PERIODICO.setText(String.valueOf(ImporteT));
        
    }//GEN-LAST:event_btnCARGAR_DEVOLUCIONActionPerformed

    private void btnAPLICAR_DEVOLUCIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAPLICAR_DEVOLUCIONActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) TablaProcesos.getModel();
        String PrimeraF; String SegundaF; String TerceraF; String NomC=""; int FolioD = GenerarFolio();
        int NrC = Integer.parseInt(txtNUMERO.getText());
        
        Date Fech1 = dtFechaDesde.getDate();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        PrimeraF = sdf1.format(Fech1);
        
        Date Fech2 = dtFechaHasta.getDate();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        SegundaF = sdf2.format(Fech2);
        
        Date Fech3 = dtFechaDevolucion.getDate();
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");
        TerceraF = sdf3.format(Fech3);
        
        /*--------------Obtenemos los datos del cliente--------------------------------------------*/
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Nombre FROM clientes WHERE Nro_Cliente = "+NrC+";");
            while(rs.next()){
                NomC = rs.getString("Nombre");
            }
            Con.close(); smnt.close(); rs.close();
            }catch(SQLException ex){
                
            }
        /*---------------Actualizamos los datos en la tabla de Compras cliente-----------------------*/
        try{
            Connection Con1 = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt1 = Con1.createStatement();
            for(int i = 0; i < model.getRowCount(); i++){
                int CantDevuelta = Integer.parseInt(model.getValueAt(i, 1).toString());
                double Importe = Double.parseDouble(model.getValueAt(i, 5).toString());
                smnt1.executeUpdate("UPDATE compras_cliente SET "
                + "CantidadDevo = "+CantDevuelta+", FechaDevolucion = '"+TerceraF+"', "
                + "ImporteDevo = '"+Importe+"', FolioDevo = "+FolioD+" WHERE "
                + "id_Cliente = "+NrC+" AND FechaRecibido BETWEEN '"+PrimeraF+"' AND '"+SegundaF+"';");
            }
            Con1.close(); smnt1.close();
            }catch(SQLException ex){
                
            }
        /*---------------Insertar estado de cuenta---------------------------------------------------*/
        String Concepto = "Devolucion";
        double Cargo = Double.parseDouble(txtDEVOLUCION_PERIODICO.getText());
        double Saldo = Double.parseDouble(txtTOTAL_PAGO.getText());
        try{
                Connection Con2 = (Connection) DriverManager.getConnection(url, usuario, contraseña);
                Statement smnt2 = Con2.createStatement();
                smnt2.executeUpdate("INSERT INTO estado_cuentacliente (idCliente,Nombre, Fecha, Concepto, Cargo, Saldos) "
                + "VALUES ("+NrC+", '"+NomC+"', '"+TerceraF+"', '"+Concepto+"', "+Cargo+", "+Saldo+");");
            Con2.close(); smnt2.close();
            }catch(SQLException ex){
                
            }
        /*--------------------Actualizamos el saldo--------------------------------------------------*/
        try{
                double SaldoAct = Double.parseDouble(txtTOTAL_PAGO.getText());
                Connection Con3 = (Connection) DriverManager.getConnection(url, usuario, contraseña);
                Statement smnt3 = Con3.createStatement();
                smnt3.executeUpdate("UPDATE clientes SET SaldoActual= "+SaldoAct+""
                        + " WHERE Nombre = '"+NomC+"';");
            Con3.close(); smnt3.close();
            }catch(SQLException ex){
                
            }
        
        
        dtFechaDevolucion.setCalendar(null);
        dtFechaDesde.setCalendar(null);
        dtFechaHasta.setCalendar(null);
        model.setRowCount(0);
        txtNUMERO.setText("");
        txtIMPORTE_TOTAL.setText("");
        txtDESCUENTO_PERIODICO.setText("");
        txtIMPORTE_DESCONTAR.setText("");
        txtSALDO_ANTERIOR.setText("");
        txtDEVOLUCION_PERIODICO.setText("");
        txtTOTAL_PAGO.setText(""); 
    }//GEN-LAST:event_btnAPLICAR_DEVOLUCIONActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelDatos;
    private javax.swing.JPanel PanelFechas;
    private javax.swing.JPanel PanelInferior;
    private javax.swing.JPanel PanelResultados;
    private javax.swing.JPanel PanelTabla;
    private javax.swing.JTable TablaProcesos;
    private javax.swing.JButton btnAGREGAR_TITULO;
    private javax.swing.JButton btnAPLICAR_DEVOLUCION;
    private javax.swing.JButton btnBUSQUEDA;
    private javax.swing.JButton btnCARGAR_DEVOLUCION;
    private javax.swing.JButton btnIMPRIMIR_DEVOLUCION;
    private javax.swing.JButton btnSALIR;
    private javax.swing.JButton btnVolverProcesosDevoluciones;
    private com.toedter.calendar.JDateChooser dtFechaDesde;
    private com.toedter.calendar.JDateChooser dtFechaDevolucion;
    private com.toedter.calendar.JDateChooser dtFechaHasta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtDESCUENTO_PERIODICO;
    private javax.swing.JTextField txtDEVOLUCION_PERIODICO;
    private javax.swing.JTextField txtIMPORTE_DESCONTAR;
    private javax.swing.JTextField txtIMPORTE_TOTAL;
    private javax.swing.JTextField txtNOMBRE;
    private javax.swing.JTextField txtNUMERO;
    private javax.swing.JTextField txtSALDO_ANTERIOR;
    private javax.swing.JTextField txtTOTAL_PAGO;
    // End of variables declaration//GEN-END:variables
}
