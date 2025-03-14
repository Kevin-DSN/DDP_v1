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

public class ArchivoTitulosMostrar extends javax.swing.JFrame {

    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    public static String CodigoT = "";
    private ArchivoTitulos archivoTitulos;
    
    public ArchivoTitulosMostrar(ArchivoTitulos archivoTitulos) {
        this.archivoTitulos = archivoTitulos;
        initComponents();
        getContentPane().setBackground(new Color(255, 172, 78));
        /*--------------------------------------------------------------------*/
        DefaultTableModel model = (DefaultTableModel) TablaMC.getModel();
        try{
            Connection Con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            Statement smnt = Con.createStatement();
            ResultSet rs = smnt.executeQuery("SELECT Codigo, Nombre FROM titulos;;");
            while(rs.next()){
                String codigo = rs.getString("Codigo");
                String nombre = rs.getString("Nombre");
                model.addRow(new Object[]{codigo, nombre});
            }
            TablaMC.setModel(model);
        } catch (SQLException ex) {
            System.out.print("Error en consulta: " + ex);
        }
        /*--------------------------------------------------------------------*/
        TablaMC.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent Mouse_evt){
                JTable table = (JTable) Mouse_evt.getSource();
                Point point = Mouse_evt.getPoint();
                int row = table.rowAtPoint(point);
                if(Mouse_evt.getClickCount() == 1){
                    DatoSeleccionado.setText(TablaMC.getValueAt(TablaMC.getSelectedRow(), 0).toString());
                    CodigoT = TablaMC.getValueAt(TablaMC.getSelectedRow(), 0).toString();
                    archivoTitulos.ConsultaDatos();
                    Salir();
                }
            }
        });
        
    }
    
    private void Salir(){
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelMuestraCodigos = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaMC = new javax.swing.JTable();
        DatoSeleccionado = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        PanelMuestraCodigos.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        PanelMuestraCodigos.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Gadugi", 1, 18)); // NOI18N
        jLabel7.setText("TITULOS");

        jScrollPane2.setForeground(new java.awt.Color(204, 204, 204));

        TablaMC.setBackground(new java.awt.Color(204, 204, 204));
        TablaMC.setForeground(new java.awt.Color(0, 0, 0));
        TablaMC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "NOMBRE"
            }
        ));
        jScrollPane2.setViewportView(TablaMC);

        javax.swing.GroupLayout PanelMuestraCodigosLayout = new javax.swing.GroupLayout(PanelMuestraCodigos);
        PanelMuestraCodigos.setLayout(PanelMuestraCodigosLayout);
        PanelMuestraCodigosLayout.setHorizontalGroup(
            PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                .addGroup(PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                    .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(PanelMuestraCodigosLayout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(DatoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelMuestraCodigosLayout.setVerticalGroup(
            PanelMuestraCodigosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelMuestraCodigosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(52, 52, 52)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(DatoSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
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
            .addGap(0, 512, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(PanelMuestraCodigos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField DatoSeleccionado;
    private javax.swing.JPanel PanelMuestraCodigos;
    private javax.swing.JTable TablaMC;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
