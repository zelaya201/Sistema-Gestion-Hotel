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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelos.entidades.Habitacion;
import modelos.entidades.Hotel;
import modelos.entidades.Producto;
import modelos.entidades.Registro;
import modelos.entidades.RegistroProducto;

/**
 *
 * @author Adonay
 */
public class ExportPDF {
    
   private ListaSimple<Habitacion> ListHabitaciones;
   private ListaSimple<Registro> ListaRegistro;
   private ListaSimple<Producto> ListaProducto;
   private ListaSimple<RegistroProducto> ListaRegistroProducto;
   private String path;
   private Hotel hotel;

    public ExportPDF() {
        
    }

    public void setListaProducto(ListaSimple<Producto> ListaProducto) {
        this.ListaProducto = ListaProducto;
    }
    
    public void setListHabitaciones(ListaSimple<Habitacion> ListHabitaciones) {
        this.ListHabitaciones = ListHabitaciones;
    }
    
    public void setListaRegistro(ListaSimple<Registro> ListaRegistro) {
        this.ListaRegistro = ListaRegistro;
    }
    
    public void setListaRegistroProducto(ListaSimple<RegistroProducto> ListaRegistroProducto) {
        this.ListaRegistroProducto = ListaRegistroProducto;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
    
     public void crearFacturaProducto() throws FileNotFoundException, IOException{
        //Creaci??n del Archivo
        String ruta = path + "\\" + "Factura de Venta 00" +ListaRegistro.toArray().get(0).getIdRegistro()+ " " + getFecha(2) + ".pdf"; //Ruta donde se guardar el archivo
        PdfWriter writer = new PdfWriter(ruta);
        PdfDocument pdf = new PdfDocument(writer);
        
        //Fuentes
        PdfFont font1 = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);
        PdfFont font2 = PdfFontFactory.createRegisteredFont(StandardFonts.HELVETICA);
        
        //Propiedades del archivo
        Document documento = new Document(pdf, PageSize.LETTER.rotate());
        documento.setMargins(40, 20, 40, 20);
        
        //Cabecera del Archivo
     
        Table encabezado = new Table(1).useAllAvailableWidth();
        encabezado.setHorizontalAlignment(HorizontalAlignment.CENTER);
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).setFontColor(new DeviceRgb(90, 90, 90)).add(new Paragraph(getFecha(1)).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.RIGHT).setFontSize(10f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Hotel " + hotel.getNombre()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(16f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Direcci??n: " + hotel.getDireccion()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Tel??fono: " + hotel.getTelefono()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("N??mero de Factura:" + " " + facturaId(ListaRegistro.toArray().get(0).getIdRegistro())).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(14f)));
        
        Paragraph saltoDeLinea = new Paragraph(""); 
        //Cuerpo de Primera Tabla  
        
        Table huespedDetalles = new Table(3).useAllAvailableWidth();
        huespedDetalles.setHorizontalAlignment(HorizontalAlignment.CENTER);
        huespedDetalles.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph(String.valueOf("DUI: " + ListaRegistro.toArray().get(0).getCliente().getDui())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        huespedDetalles.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph(String.valueOf("Hu??sped: " + ListaRegistro.toArray().get(0).getCliente().getNombre() + " " + ListaRegistro.toArray().get(0).getCliente().getApellido())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        huespedDetalles.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph(String.valueOf("Tel??fono: " + ListaRegistro.toArray().get(0).getCliente().getTelefono())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
       
        //Cuerpo de Segunda Tabla
        Table detalleRegistro = new Table(6).useAllAvailableWidth();
        detalleRegistro.setHorizontalAlignment(HorizontalAlignment.CENTER);
        detalleRegistro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("N?? Habitaci??n").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleRegistro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Tipo").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleRegistro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Fecha de Entrada").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleRegistro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Fecha de Salida").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleRegistro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Precio").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleRegistro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Mora").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        
             detalleRegistro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(ListaRegistro.toArray().get(0).getHabitacion().getNumHabitacion())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleRegistro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(ListaRegistro.toArray().get(0).getTipo())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleRegistro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(ListaRegistro.toArray().get(0).getFechaEntrada())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleRegistro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(ListaRegistro.toArray().get(0).getFechaSalida())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleRegistro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf("$" + formatoDecimal(ListaRegistro.toArray().get(0).getHabitacion().getPrecio()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleRegistro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf("$" + formatoDecimal(ListaRegistro.toArray().get(0).getMora()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        
        //Cuerpo de Tercera Tabla 
        Table producCons = new Table(1).useAllAvailableWidth();
        producCons.setHorizontalAlignment(HorizontalAlignment.CENTER);
        producCons.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph(String.valueOf("Productos Consumidos")).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        
        Table detalleproducto = new Table(5).useAllAvailableWidth();
        detalleproducto.setHorizontalAlignment(HorizontalAlignment.CENTER);
        detalleproducto.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("C??digo Producto").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleproducto.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Producto").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleproducto.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Cantidad").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleproducto.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Precio Unitario").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleproducto.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Subtotal").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
       
        double stotal = 0;
        
        for (RegistroProducto x : ListaRegistro.toArray().get(0).getRegistrosProductos().toArray()) {
             detalleproducto.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getProducto().getCodigo())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleproducto.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getProducto().getDescripcion())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleproducto.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getCantidad())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleproducto.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf("$" + formatoDecimal(x.getProducto().getPrecio()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleproducto.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf("$" + formatoDecimal(x.getSubtotal()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             
             stotal = stotal + x.getSubtotal();
        }
        
        double costoTotal = stotal + ListaRegistro.toArray().get(0).getTotal() + ListaRegistro.toArray().get(0).getMora();
        
        Paragraph deposit = new Paragraph("D??posito: $ " + formatoDecimal(ListaRegistro.toArray().get(0).getDeposito())).setTextAlignment(TextAlignment.RIGHT);
        Paragraph costtotal = new Paragraph("Costo Total: $ " + formatoDecimal(costoTotal)).setTextAlignment(TextAlignment.RIGHT);
        Paragraph totalpag = new Paragraph("Total a Pagar: $ " + formatoDecimal(costoTotal - ListaRegistro.toArray().get(0).getDeposito())).setTextAlignment(TextAlignment.RIGHT);
        
        //Agregamos todos los objetos al documento
        
        documento.add(encabezado);
        documento.add(saltoDeLinea);
        documento.add(huespedDetalles);
        documento.add(saltoDeLinea);
        documento.add(detalleRegistro);
        documento.add(saltoDeLinea);
        //
        if (!ListaRegistro.toArray().get(0).getRegistrosProductos().isEmpty()) {
            documento.add(producCons);
            documento.add(saltoDeLinea);
            documento.add(detalleproducto);
            documento.add(saltoDeLinea);
        }
        //
        documento.add(deposit);
        documento.add(costtotal);
        documento.add(totalpag);
        documento.close();
        
        //Creamos el archivo en la ruta especificada
        try {
            File objetofile = new File(ruta);
            Desktop.getDesktop().open(objetofile);
        }catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void crearDetalleProducto() throws FileNotFoundException, IOException{
        //Creaci??n del Archivo
        String ruta = path + "\\" + "Detalle de Venta " + getFecha(2) + ".pdf"; //Ruta donde se guardar el archivo
        PdfWriter writer = new PdfWriter(ruta);
        PdfDocument pdf = new PdfDocument(writer);
        
        //Fuentes
        PdfFont font1 = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);
        PdfFont font2 = PdfFontFactory.createRegisteredFont(StandardFonts.HELVETICA);
        
        //Propiedades del archivo
        Document documento = new Document(pdf, PageSize.LETTER.rotate());
        documento.setMargins(40, 20, 40, 20);
        
        //Cabecera del Archivo
     
        Table encabezado = new Table(1).useAllAvailableWidth();
        encabezado.setHorizontalAlignment(HorizontalAlignment.CENTER);
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).setFontColor(new DeviceRgb(90, 90, 90)).add(new Paragraph(getFecha(1)).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.RIGHT).setFontSize(10f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Hotel " + hotel.getNombre()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(16f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Direcci??n: " + hotel.getDireccion()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Tel??fono: " + hotel.getTelefono()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Detalle de Venta").setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(14f)));
        
        Paragraph saltoDeLinea = new Paragraph(""); //Arreglar segunda columna y agregar el numero de habitacion al encabezado
        //Cuerpo de Primera Tabla  
        
        Table habitacionDetalles = new Table(3).useAllAvailableWidth();
        habitacionDetalles.setHorizontalAlignment(HorizontalAlignment.CENTER);
        habitacionDetalles.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph(String.valueOf("N?? De Habitaci??n: " + ListaRegistroProducto.toArray().get(0).getRegistro().getHabitacion().getNumHabitacion())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitacionDetalles.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph(String.valueOf("Tipo de Habitaci??n: " + ListaRegistroProducto.toArray().get(0).getRegistro().getHabitacion().getTipoHabitacion().getNombre())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitacionDetalles.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph(String.valueOf("Precio: $" + formatoDecimal(ListaRegistroProducto.toArray().get(0).getRegistro().getHabitacion().getPrecio()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitacionDetalles.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph(String.valueOf("Cliente: " + ListaRegistroProducto.toArray().get(0).getRegistro().getCliente().getNombre()+ " " + ListaRegistroProducto.toArray().get(0).getRegistro().getCliente().getApellido())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitacionDetalles.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph(String.valueOf("Fecha de Entrada: " + ListaRegistroProducto.toArray().get(0).getRegistro().getFechaEntrada())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitacionDetalles.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph(String.valueOf("Fecha de Salida: " + ListaRegistroProducto.toArray().get(0).getRegistro().getFechaSalida())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        
        //Cuerpo de Segunda Tabla 
        Table detalleproducto = new Table(5).useAllAvailableWidth();
        detalleproducto.setHorizontalAlignment(HorizontalAlignment.CENTER);
        detalleproducto.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("C??digo Producto").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleproducto.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Producto").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleproducto.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Cantidad").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleproducto.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Precio Unitario").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        detalleproducto.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Subtotal").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
       
        double stotal = 0;
        
        for (RegistroProducto x : ListaRegistroProducto.toArray()) {
             detalleproducto.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getProducto().getCodigo())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleproducto.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getProducto().getDescripcion())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleproducto.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getCantidad())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleproducto.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf("$" + formatoDecimal(x.getProducto().getPrecio()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             detalleproducto.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf("$" + formatoDecimal(x.getSubtotal()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             
             stotal = stotal + x.getSubtotal();
        }
        
        Paragraph totalSub = new Paragraph("Total Global: $ " + formatoDecimal(stotal)).setTextAlignment(TextAlignment.RIGHT);
        
        //Agregamos todos los objetos al documento
        documento.add(encabezado);
        documento.add(saltoDeLinea);
        documento.add(habitacionDetalles);
        documento.add(saltoDeLinea);
        documento.add(detalleproducto);
        documento.add(totalSub);
        documento.close();
        
        //Creamos el archivo en la ruta especificada
        try {
            File objetofile = new File(ruta);
            Desktop.getDesktop().open(objetofile);
        }catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void crearDetalleHabitacion() throws FileNotFoundException, IOException{
        //Creaci??n del Archivo
        String ruta = path + "\\" + "Detalle de Habitacion 00" +ListaRegistro.toArray().get(0).getHabitacion().getNumHabitacion() + " " + getFecha(2) + ".pdf"; //Ruta donde se guardar el archivo
        PdfWriter writer = new PdfWriter(ruta);
        PdfDocument pdf = new PdfDocument(writer);
        
        //Fuentes
        PdfFont font1 = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);
        PdfFont font2 = PdfFontFactory.createRegisteredFont(StandardFonts.HELVETICA);
        
        //Propiedades del archivo
        Document documento = new Document(pdf, PageSize.LETTER.rotate());
        documento.setMargins(40, 20, 40, 20);
        
        //Cabecera del Archivo
        Table encabezado = new Table(1).useAllAvailableWidth();
        encabezado.setHorizontalAlignment(HorizontalAlignment.CENTER);
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).setFontColor(new DeviceRgb(90, 90, 90)).add(new Paragraph(getFecha(1)).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.RIGHT).setFontSize(10f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Hotel " + hotel.getNombre()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(16f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Direcci??n: " + hotel.getDireccion()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Tel??fono: " + hotel.getTelefono()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Detalle de Habitacion 00" + ListaRegistro.toArray().get(0).getHabitacion().getNumHabitacion()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(14f)));
        
        Paragraph saltoDeLinea = new Paragraph("");
            
        //Cuerpo del Archivo
        Table registro = new Table(7).useAllAvailableWidth();
        registro.setHorizontalAlignment(HorizontalAlignment.CENTER);
        registro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Descripci??n").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        registro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Cliente").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        registro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Usuario").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        registro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Fecha de Entrada").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        registro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Fecha de Salida").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        registro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Mora").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        registro.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Total").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
      
        double total = 0;
        
        for (Registro x : ListaRegistro.toArray()) {
             registro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getHabitacion().getDescripcion())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             registro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getCliente().getNombre() + " " + x.getCliente().getApellido())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             registro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getUsuario().getNombre() + " " + x.getUsuario().getApellido())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             registro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getFechaEntrada())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             registro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getFechaSalida())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             registro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf("$" + formatoDecimal(x.getMora()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             registro.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf("$" + formatoDecimal(x.getTotal()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));

             total = total + x.getTotal();             
        }
        
        Paragraph totalSalt = new Paragraph("Total Global: $ " + formatoDecimal(total)).setTextAlignment(TextAlignment.RIGHT);
        
        //Agregamos todos los objetos al documento  
        documento.add(encabezado);
        documento.add(saltoDeLinea);
        documento.add(registro);
        documento.add(totalSalt);
        documento.close();
        
        //Creamos el archivo en la ruta especificada
        try {
            File objetofile = new File(ruta);
            Desktop.getDesktop().open(objetofile);
        }catch (IOException ex) {
            System.out.println(ex);
        }
    }  
    
    public void crearListaHabitaciones() throws FileNotFoundException, IOException{
        //Creaci??n del Archivo
        String ruta = path + "\\" + "Listado de Habitaciones " + getFecha(2) + ".pdf"; //Ruta donde se guardar el archivo
        PdfWriter writer = new PdfWriter(ruta);
        PdfDocument pdf = new PdfDocument(writer);
        
        //Fuentes
        PdfFont font1 = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);
        PdfFont font2 = PdfFontFactory.createRegisteredFont(StandardFonts.HELVETICA);
        
        //Propiedades del archivo
        Document documento = new Document(pdf, PageSize.LETTER.rotate());
        documento.setMargins(40, 20, 40, 20);
        
        //Cabecera del Archivo
        Table encabezado = new Table(1).useAllAvailableWidth();
        encabezado.setHorizontalAlignment(HorizontalAlignment.CENTER);
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).setFontColor(new DeviceRgb(90, 90, 90)).add(new Paragraph(getFecha(1)).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.RIGHT).setFontSize(10f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Hotel " + hotel.getNombre()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(16f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Direcci??n: " + hotel.getDireccion()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Tel??fono: " + hotel.getTelefono()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Listado de Habitaciones").setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(14f)));
        
        Paragraph saltoDeLinea = new Paragraph("");
            
        //Cuerpo del Archivo
        Table habitaciones = new Table(5).useAllAvailableWidth();
        habitaciones.setHorizontalAlignment(HorizontalAlignment.CENTER);
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("No. de Habitaci??n").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Descripci??n").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Precio").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Tipo").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        habitaciones.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Disposici??n").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        
        for (Habitacion x : ListHabitaciones.toArray()) {
             habitaciones.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getNumHabitacion())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             habitaciones.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getDescripcion())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             habitaciones.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf("$" + formatoDecimal(x.getPrecio()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             habitaciones.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getTipoHabitacion().getNombre())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             habitaciones.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getDisposicion())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        }
        
        //Agregamos todos los objetos al documento
        documento.add(encabezado);
        documento.add(saltoDeLinea);
        documento.add(habitaciones);
        documento.close();
        
        //Creamos el archivo en la ruta especificada
        try {
            File objetofile = new File(ruta);
            Desktop.getDesktop().open(objetofile);
        }catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public void crearListaProducto() throws FileNotFoundException, IOException{
        //Creaci??n del Archivo
        String ruta = path + "\\" + "Listado de Productos " + getFecha(2) + ".pdf"; //Ruta donde se guardar el archivo
        PdfWriter writer = new PdfWriter(ruta);
        PdfDocument pdf = new PdfDocument(writer);
        
        //Fuentes
        PdfFont font1 = PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE);
        PdfFont font2 = PdfFontFactory.createRegisteredFont(StandardFonts.HELVETICA);
        
        //Propiedades del archivo
        Document documento = new Document(pdf, PageSize.LETTER.rotate());
        documento.setMargins(40, 20, 40, 20);
        
        //Cabecera del Archivo
        Table encabezado = new Table(1).useAllAvailableWidth();
        encabezado.setHorizontalAlignment(HorizontalAlignment.CENTER);
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).setFontColor(new DeviceRgb(90, 90, 90)).add(new Paragraph(getFecha(1)).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.RIGHT).setFontSize(10f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Hotel " + hotel.getNombre()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(16f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Direcci??n: " + hotel.getDireccion()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font1).add(new Paragraph("Tel??fono: " + hotel.getTelefono()).setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(11f)));
        encabezado.addCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).add(new Paragraph("Listado de Productos").setVerticalAlignment(VerticalAlignment.MIDDLE).setTextAlignment(TextAlignment.CENTER).setFontSize(14f)));
        
        Paragraph saltoDeLinea = new Paragraph("");
            
        //Cuerpo del Archivo
        Table productos = new Table(3).useAllAvailableWidth();
        productos.setHorizontalAlignment(HorizontalAlignment.CENTER);
        productos.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("C??digo Producto").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        productos.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Descripci??n").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        productos.addHeaderCell(new Cell().setBorder(Border.NO_BORDER).setFont(font2).setBackgroundColor(new DeviceRgb(221,221,221)).add(new Paragraph("Precio").setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
       
        for (Producto x : ListaProducto.toArray()) {
             productos.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getCodigo())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             productos.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf(x.getDescripcion())).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
             productos.addCell(new Cell().setFont(font2).add(new Paragraph(String.valueOf("$" + formatoDecimal(x.getPrecio()))).setFontSize(11f).setTextAlignment(TextAlignment.CENTER)).setVerticalAlignment(VerticalAlignment.MIDDLE));
        }
        
        //Agregamos todos los objetos al documento
        documento.add(encabezado);
        documento.add(saltoDeLinea);
        documento.add(productos);
        documento.close();
        
        //Creamos el archivo en la ruta especificada
        try {
            File objetofile = new File(ruta);
            Desktop.getDesktop().open(objetofile);
        }catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public String getFecha(int formato){
        
        String fechaFormato = null;
        SimpleDateFormat formatFecha;
        
        switch(formato){
            case 1 :
                formatFecha = new SimpleDateFormat("d 'de' MMMM 'del' yyyy");
                fechaFormato = formatFecha.format(new Date());
                break;
            case 2 : 
                formatFecha = new SimpleDateFormat("yyyy-MM-dd 'at' h.m.s a");
                fechaFormato = formatFecha.format(new Date());
                break;
        }
        
        
        return fechaFormato;
    }
    
    public String formatoDecimal(Double precio){
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00",simbolos);
        
        return formateador.format(precio);
    }
    
    public String facturaId(int idRegistro){
        DecimalFormat id = new DecimalFormat("000000");
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        return id.format(idRegistro);
    }
    
}