/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Adonay
 */
public class ExportPDF {

    public ExportPDF(String path) throws FileNotFoundException, IOException {
        String ruta = path + "\\" + "listadoHabitaciones" + ".pdf"; //Ruta donde se guardar el archivo
        crearPDF(ruta);
    }
    
    public void crearPDF(String ruta) throws FileNotFoundException, IOException{
        //Creación del Archivo
        PdfWriter writer = new PdfWriter(ruta);
        PdfDocument pdf = new PdfDocument(writer);
        
        //Fuentes
        PdfFont font1 = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);
        PdfFont font2 = PdfFontFactory.createRegisteredFont(StandardFonts.HELVETICA);
        
        //Propiedades del archivo
        Document documento = new Document(pdf, PageSize.LETTER);
        documento.setMargins(40, 20, 40, 20);
        
        //Cabecera del Archivo
        Table encabezado = new Table(1).useAllAvailableWidth();
        encabezado.setHorizontalAlignment(HorizontalAlignment.CENTER);
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).setFontColor(new DeviceRgb(90, 90, 90)).add(new Paragraph(getFecha()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.RIGHT).setFontSize(10f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Hotel Sheraton Presidente").setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(16f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Dirección: Residencial los Amositos, barrio Calderon #2").setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Teléfono: 2393-6761").setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Listado de Habitaciones").setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(14f)));
        
        Paragraph saltoDeLinea = new Paragraph("");
            
        //Cuerpor del Archivo
        Table habitaciones = new Table(6).useAllAvailableWidth();
        habitaciones.setHorizontalAlignment(HorizontalAlignment.CENTER);
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Id de Habitación").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("No. de Habitación").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Descripción").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Precio").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Tipo").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Disposición").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        
        //Agregamos todos los objetos al documento
        documento.add(encabezado);
        documento.add(saltoDeLinea);
        documento.add(habitaciones);
        documento.close();
        
        try {
            File objetofile = new File(ruta);
            Desktop.getDesktop().open(objetofile);
        }catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public String getFecha(){
        SimpleDateFormat formatFecha = new SimpleDateFormat("d 'de' MMMM 'del' yyyy");
        String fechaFormato = formatFecha.format(new Date());
        
        return fechaFormato;
    }
}
