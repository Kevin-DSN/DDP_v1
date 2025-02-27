package igu;
import java.awt.*;
import java.awt.print.*;

public class ImprimirTicketBlanco {
    public static void imprimir(String ticketTexto) {
        PrinterJob job = PrinterJob.getPrinterJob();
        
        PageFormat pf = job.defaultPage();
        Paper paper = new Paper();
        double width = 180; // Ancho en puntos (aprox. 58mm)
        double height = Double.MAX_VALUE; // Altura indefinida (larga para tickets)
        paper.setSize(width, height);
        paper.setImageableArea(0, 0, width, height);
        
        pf.setPaper(paper);
        
        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }
                
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                g2d.setFont(new Font("Monospaced", Font.PLAIN, 8)); // Fuente más pequeña
                
                int y = 10; // Posición vertical inicial
                for (String line : ticketTexto.split("\n")) {
                    graphics.drawString(line, 5, y); // Ajuste del margen izquierdo
                    y += 12; // Espaciado entre líneas reducido
                }
                
                return PAGE_EXISTS;
            }
        }, pf);

        try {
            job.print();
        } catch (PrinterException e) {
            e.printStackTrace();
        }
    }
}
