package igu;
import java.awt.Color;

public class UtileriasRespaldos extends javax.swing.JFrame {

    public UtileriasRespaldos() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        //this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolverUtileriasRespaldo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        btnVolverUtileriasRespaldo.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverUtileriasRespaldo.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverUtileriasRespaldo.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverUtileriasRespaldo.setText("VOLVER");
        btnVolverUtileriasRespaldo.setBorderPainted(false);
        btnVolverUtileriasRespaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverUtileriasRespaldoActionPerformed(evt);
            }
        });

        jLabel1.setText("Respaldos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVolverUtileriasRespaldo)
                .addGap(214, 214, 214)
                .addComponent(jLabel1)
                .addContainerGap(289, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverUtileriasRespaldo)
                    .addComponent(jLabel1))
                .addContainerGap(571, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverUtileriasRespaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverUtileriasRespaldoActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverUtileriasRespaldoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolverUtileriasRespaldo;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
