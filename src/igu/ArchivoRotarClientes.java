package igu;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ArchivoRotarClientes extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    public ArchivoRotarClientes() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        //this.setExtendedState(this.MAXIMIZED_BOTH);
        txtID1.setVisible(false);
        txtID2.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolverUtileriasUsuario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNumeroC2 = new javax.swing.JTextField();
        txtNumeroC1 = new javax.swing.JTextField();
        txtNombreC1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNombreC2 = new javax.swing.JTextField();
        btnRotar = new javax.swing.JButton();
        txtID1 = new javax.swing.JTextField();
        txtID2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        btnVolverUtileriasUsuario.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverUtileriasUsuario.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverUtileriasUsuario.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverUtileriasUsuario.setText("VOLVER");
        btnVolverUtileriasUsuario.setBorderPainted(false);
        btnVolverUtileriasUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverUtileriasUsuarioActionPerformed(evt);
            }
        });

        jLabel1.setText("Rotar Clientes");

        jLabel2.setText("Cliente 1");

        jLabel4.setText("Cliente 2");

        txtNumeroC2.setBorder(null);
        txtNumeroC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroC2ActionPerformed(evt);
            }
        });

        txtNumeroC1.setBorder(null);
        txtNumeroC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumeroC1ActionPerformed(evt);
            }
        });

        txtNombreC1.setBorder(null);

        jLabel3.setText("Rotar");

        txtNombreC2.setBorder(null);

        btnRotar.setBackground(new java.awt.Color(255, 152, 36));
        btnRotar.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnRotar.setForeground(new java.awt.Color(0, 0, 0));
        btnRotar.setText("ROTAR");
        btnRotar.setBorderPainted(false);
        btnRotar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRotarActionPerformed(evt);
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
                        .addComponent(btnVolverUtileriasUsuario)
                        .addGap(128, 128, 128)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(217, 217, 217)
                                .addComponent(jLabel3)
                                .addGap(188, 188, 188))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNumeroC1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNumeroC2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombreC1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNombreC2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnRotar))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverUtileriasUsuario)
                    .addComponent(jLabel1))
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumeroC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNumeroC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombreC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtID2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(btnRotar)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverUtileriasUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverUtileriasUsuarioActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverUtileriasUsuarioActionPerformed

    private void txtNumeroC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroC2ActionPerformed
        // TODO add your handling code here:
        int NroC = Integer.parseInt(txtNumeroC2.getText());
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Nombre, Nro_Cliente, id FROM clientes WHERE Nro_Cliente = '"+NroC+"';");
            if(rs.next()){
                txtNombreC2.setText(rs.getString("Nombre"));
                txtNumeroC2.setText(rs.getString("Nro_Cliente"));
                txtID2.setText(rs.getString("id"));
            }
            Con.close(); smnt.close(); rs.close();
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
    }//GEN-LAST:event_txtNumeroC2ActionPerformed

    private void txtNumeroC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroC1ActionPerformed
        // TODO add your handling code here:
        int NroC = Integer.parseInt(txtNumeroC1.getText());
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Nombre, Nro_Cliente, id FROM clientes WHERE Nro_Cliente = '"+NroC+"';");
            if(rs.next()){
                txtNombreC1.setText(rs.getString("Nombre"));
                txtNumeroC1.setText(rs.getString("Nro_Cliente"));
                txtID1.setText(rs.getString("id"));
            }
            Con.close(); smnt.close(); rs.close();
        }catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
        txtNumeroC2.requestFocus();
    }//GEN-LAST:event_txtNumeroC1ActionPerformed

    private void btnRotarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRotarActionPerformed
        //Datos del primer cliente
        int NroC1 = Integer.parseInt(txtNumeroC1.getText());
        int idC1 = Integer.parseInt(txtID1.getText());

        //Datos del cliente 2
        int NroC2 = Integer.parseInt(txtNumeroC2.getText());;
        int idC2 = Integer.parseInt(txtID2.getText());
        
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            smnt.executeUpdate("UPDATE Clientes SET "
            + "Nro_Cliente = "+NroC2+" WHERE id = "+idC1+";");
            
            Connection Con1 = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt1 = Con1.createStatement();
            smnt1.executeUpdate("UPDATE Clientes SET "
            + "Nro_Cliente = "+NroC1+" WHERE id = "+idC2+";");
            
            Con.close(); smnt.close(); Con1.close(); smnt1.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al rotar: " + ex);
        }
        /*-------------------------------------------------------------------------------------*/
        JOptionPane.showMessageDialog(null,"Rotacion exitosa");
        txtNumeroC1.setText("");
        txtNombreC1.setText("");
        txtNombreC2.setText("");
        txtNumeroC2.setText("");
        txtNumeroC1.requestFocus();
    }//GEN-LAST:event_btnRotarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRotar;
    private javax.swing.JButton btnVolverUtileriasUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtID1;
    private javax.swing.JTextField txtID2;
    private javax.swing.JTextField txtNombreC1;
    private javax.swing.JTextField txtNombreC2;
    private javax.swing.JTextField txtNumeroC1;
    private javax.swing.JTextField txtNumeroC2;
    // End of variables declaration//GEN-END:variables
}
