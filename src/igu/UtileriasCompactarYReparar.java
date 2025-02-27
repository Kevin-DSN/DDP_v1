package igu;
import java.awt.Color;

public class UtileriasCompactarYReparar extends javax.swing.JFrame {

    public UtileriasCompactarYReparar() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        //this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolverUtileriasCompactarYReparar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        btnVolverUtileriasCompactarYReparar.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverUtileriasCompactarYReparar.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverUtileriasCompactarYReparar.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverUtileriasCompactarYReparar.setText("VOLVER");
        btnVolverUtileriasCompactarYReparar.setBorderPainted(false);
        btnVolverUtileriasCompactarYReparar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverUtileriasCompactarYRepararActionPerformed(evt);
            }
        });

        jLabel1.setText("Compactar y Reparar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVolverUtileriasCompactarYReparar)
                .addGap(179, 179, 179)
                .addComponent(jLabel1)
                .addContainerGap(268, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverUtileriasCompactarYReparar)
                    .addComponent(jLabel1))
                .addContainerGap(572, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverUtileriasCompactarYRepararActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverUtileriasCompactarYRepararActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverUtileriasCompactarYRepararActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolverUtileriasCompactarYReparar;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
