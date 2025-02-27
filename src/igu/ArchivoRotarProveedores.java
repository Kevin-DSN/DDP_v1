package igu;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ArchivoRotarProveedores extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    
    public ArchivoRotarProveedores() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        //this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolverUtileriasUsuario = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

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

        jLabel1.setText("Rotar Proveedores");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVolverUtileriasUsuario)
                .addGap(139, 139, 139)
                .addComponent(jLabel1)
                .addContainerGap(231, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverUtileriasUsuario)
                    .addComponent(jLabel1))
                .addContainerGap(321, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverUtileriasUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverUtileriasUsuarioActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverUtileriasUsuarioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolverUtileriasUsuario;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
