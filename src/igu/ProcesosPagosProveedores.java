package igu;
import java.awt.Color;

public class ProcesosPagosProveedores extends javax.swing.JFrame {

    public ProcesosPagosProveedores() {
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        //this.setExtendedState(this.MAXIMIZED_BOTH);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolverProcesosPagosProveedor = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);

        btnVolverProcesosPagosProveedor.setBackground(new java.awt.Color(255, 152, 36));
        btnVolverProcesosPagosProveedor.setFont(new java.awt.Font("Gadugi", 1, 12)); // NOI18N
        btnVolverProcesosPagosProveedor.setForeground(new java.awt.Color(0, 0, 0));
        btnVolverProcesosPagosProveedor.setText("VOLVER");
        btnVolverProcesosPagosProveedor.setBorderPainted(false);
        btnVolverProcesosPagosProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverProcesosPagosProveedorActionPerformed(evt);
            }
        });

        jLabel1.setText("Pagos Proveedor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVolverProcesosPagosProveedor)
                .addGap(184, 184, 184)
                .addComponent(jLabel1)
                .addContainerGap(284, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverProcesosPagosProveedor)
                    .addComponent(jLabel1))
                .addContainerGap(571, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverProcesosPagosProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverProcesosPagosProveedorActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Principal Prin = new Principal();
        Prin.setVisible(true);
    }//GEN-LAST:event_btnVolverProcesosPagosProveedorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolverProcesosPagosProveedor;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
