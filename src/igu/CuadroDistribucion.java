package igu;
import java.awt.*;
import java.awt.print.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CuadroDistribucion implements Printable {
    private List<String> titulos;
    private List<String> clientes;
    String url = "jdbc:mysql://localhost:3306/base_periodicos";
    String usuario = "root";
    String contraseña = "18320996";
    
    public CuadroDistribucion() {
        titulos = obtenerTitulos();
        clientes = obtenerClientes();
    }

    private List<String> obtenerTitulos() {
        List<String> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT Nombre FROM titulos WHERE Lista = 2")) {
            
            while (rs.next()) {
                lista.add(rs.getString("Nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private List<String> obtenerClientes() {
        List<String> lista = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, usuario, contraseña);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nombre FROM clientes WHERE Ruta = 2 AND Reparto = 2")) {
            
            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        if (pageIndex > 0) return NO_SUCH_PAGE;

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        
        int x = 50, y = 50, width = 100, height = 30;
        
        g2d.setFont(new Font("Courier New", Font.BOLD, 15));
        g2d.drawString("DIST. DE PERIODICOS Y REVISTAS", x, y);
        g2d.setFont(new Font("Courier New", Font.PLAIN, 10));
        g2d.drawString("Cuadro de Distribución", x, y + 20);
        
        // Fecha y Hora
        g2d.drawString("Fecha: " + java.time.LocalDate.now(), x + 350, y);
        g2d.drawString("Hora: " + java.time.LocalTime.now().withNano(0), x + 350, y + 15);
        g2d.drawString("Página: 00000001", x + 350, y + 30);
        
        y += 50;
        
        // Dibujar encabezados de títulos
        g2d.setFont(new Font("Courier New", Font.PLAIN, 10));
        g2d.drawRect(x, y, width, height);
        g2d.drawString("Clientes / Titulos", x + 5, y + 20);
        
        for (int i = 0; i < titulos.size(); i++) {
            g2d.drawRect(x + (i + 1) * width, y, width, height);
            g2d.drawString(titulos.get(i), x + (i + 1) * width + 5, y + 20);
        }
        
        y += height;
        
        // Dibujar clientes y celdas vacías
        g2d.setFont(new Font("Courier New", Font.PLAIN, 10));
        for (int j = 0; j < clientes.size(); j++) {
            g2d.drawRect(x, y + j * height, width, height);
            g2d.drawString(clientes.get(j), x + 5, y + j * height + 20);
            for (int i = 0; i < titulos.size(); i++) {
                g2d.drawRect(x + (i + 1) * width, y + j * height, width, height);
            }
        }
        
        return PAGE_EXISTS;
    }

    public void imprimir() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(this);
        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }
}
