package igu;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class ProcesosCopiarDotacion extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    
    public ProcesosCopiarDotacion() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            jTextField1.requestFocus();
            TABLA.setVisible(false);
        }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolverProcesosPeriodicosRecibidos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnCOPIAR = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        Titulo1 = new javax.swing.JTextField();
        Titulo2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TABLA = new javax.swing.JTable();

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
        jLabel1.setText("Copiar Dotaciones");

        jLabel2.setText("Titulo 1");

        jLabel3.setText("Copiar a ");

        jLabel4.setText("Titulo 2");

        btnCOPIAR.setBackground(new java.awt.Color(255, 152, 36));
        btnCOPIAR.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnCOPIAR.setForeground(new java.awt.Color(0, 0, 0));
        btnCOPIAR.setText("Copiar");
        btnCOPIAR.setBorderPainted(false);
        btnCOPIAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCOPIARActionPerformed(evt);
            }
        });

        jTextField1.setBorder(null);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.setBorder(null);
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        Titulo1.setBorder(null);

        Titulo2.setBorder(null);

        TABLA.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "N", "D"
            }
        ));
        jScrollPane1.setViewportView(TABLA);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVolverProcesosPeriodicosRecibidos)
                        .addGap(82, 82, 82)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Titulo2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(btnCOPIAR)))))
                .addContainerGap(36, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(224, 224, 224))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVolverProcesosPeriodicosRecibidos)
                    .addComponent(jLabel1))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Titulo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCOPIAR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverProcesosPeriodicosRecibidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverProcesosPeriodicosRecibidosActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverProcesosPeriodicosRecibidosActionPerformed

    private void btnCOPIARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCOPIARActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) TABLA.getModel();
        model.setRowCount(0); TABLA.setModel(model);  
        String Codigo = String.valueOf(jTextField1.getText());
        try{
            //Cargmos los valores de la columna 1 y 2 (Nro cliente y nombre)
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Nro_Cliente, "+Codigo+" FROM Clientes;");

            while(rs.next()){
                String Nro_Cliente = rs.getString("Nro_Cliente");
                String Dot = rs.getString(""+Codigo+"");
                model.addRow(new Object[]{Nro_Cliente, Dot});
            }
            TABLA.setModel(model);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error en cargar dotacion: " + ex);
        }
        
        /*-------------------------------------------------------------------------------------*/
        String Cod = String.valueOf(jTextField2.getText());

        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            
            for(int i = 0; i < model.getRowCount(); i++){
            int NrC = Integer.parseInt(model.getValueAt(i, 0).toString());
            int Dotacion = Integer.parseInt(model.getValueAt(i, 1).toString());
            smnt.executeUpdate("UPDATE clientes SET "+Cod+" = "+Dotacion+" WHERE Nro_Cliente = "+NrC+";");
            }
            Con.close(); smnt.close();
            } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error actualizar datos: "+ex);
            }  
        
        JOptionPane.showMessageDialog(null,"Se realizo la carga de datos");
        jTextField1.setText("");
        Titulo1.setText("");
        Titulo2.setText("");
        jTextField2.setText("");
        jTextField1.requestFocus();
    }//GEN-LAST:event_btnCOPIARActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
        String Cod = jTextField1.getText();
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Nombre, Codigo FROM titulos WHERE Codigo = '"+Cod+"';");
            if(rs.next()){
                Titulo1.setText(rs.getString("Nombre"));
                jTextField1.setText(rs.getString("Codigo"));
            }else
            {
                
            }
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
        jTextField2.requestFocus();
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
        String Cod = jTextField2.getText();
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Nombre, Codigo FROM titulos WHERE Codigo = '"+Cod+"';");
            if(rs.next()){
                Titulo2.setText(rs.getString("Nombre"));
                jTextField2.setText(rs.getString("Codigo"));
            }else
            {
                
            }
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
    }//GEN-LAST:event_jTextField2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TABLA;
    private javax.swing.JTextField Titulo1;
    private javax.swing.JTextField Titulo2;
    private javax.swing.JButton btnCOPIAR;
    private javax.swing.JButton btnVolverProcesosPeriodicosRecibidos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
