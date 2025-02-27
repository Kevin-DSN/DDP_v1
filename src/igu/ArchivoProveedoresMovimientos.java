package igu;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ArchivoProveedoresMovimientos extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    
    public ArchivoProveedoresMovimientos() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMuestraCodigos = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        btnVolverProcesosPeriodicosRecibidos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PanelMuestraCodigos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PanelMuestraCodigos.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        jLabel7.setText("MOVIMIENTOS DE PROVEEDORES");

        jRadioButton1.setBackground(new java.awt.Color(255, 172, 78));
        jRadioButton1.setText("1. CARGOS");

        jRadioButton2.setBackground(new java.awt.Color(255, 172, 78));
        jRadioButton2.setText("2. CREDITOS (BONIFICACIONES)");

        jRadioButton3.setBackground(new java.awt.Color(255, 172, 78));
        jRadioButton3.setText("3. PAGOS");

        jLabel1.setText("Fecha");

        jLabel2.setText("Importe");

        jLabel8.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        jLabel8.setText("CONCEPTO");

        btnVolverProcesosPeriodicosRecibidos.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverProcesosPeriodicosRecibidos.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverProcesosPeriodicosRecibidos.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverProcesosPeriodicosRecibidos.setText("APLICAR");
        btnVolverProcesosPeriodicosRecibidos.setBorderPainted(false);
        btnVolverProcesosPeriodicosRecibidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverProcesosPeriodicosRecibidosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelMuestraCodigosLayout = new javax.swing.GroupLayout(PanelMuestraCodigos);
        PanelMuestraCodigos.setLayout(PanelMuestraCodigosLayout);
        PanelMuestraCodigosLayout.setHorizontalGroup(
            PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                        .addComponent(jRadioButton2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMuestraCodigosLayout.createSequentialGroup()
                        .addGroup(PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMuestraCodigosLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMuestraCodigosLayout.createSequentialGroup()
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMuestraCodigosLayout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMuestraCodigosLayout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addGroup(PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMuestraCodigosLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMuestraCodigosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(63, 63, 63))))
            .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                .addGroup(PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jTextField2)))
                    .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                        .addGroup(PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                                .addGap(198, 198, 198)
                                .addComponent(btnVolverProcesosPeriodicosRecibidos))
                            .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                                .addGap(188, 188, 188)
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PanelMuestraCodigosLayout.setVerticalGroup(
            PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGroup(PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jRadioButton1)
                        .addGap(39, 39, 39)
                        .addComponent(jRadioButton2)
                        .addGap(43, 43, 43)
                        .addComponent(jRadioButton3)
                        .addGap(33, 33, 33))
                    .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnVolverProcesosPeriodicosRecibidos)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 512, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelMuestraCodigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 463, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelMuestraCodigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverProcesosPeriodicosRecibidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverProcesosPeriodicosRecibidosActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverProcesosPeriodicosRecibidosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelMuestraCodigos;
    private javax.swing.JButton btnVolverProcesosPeriodicosRecibidos;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
