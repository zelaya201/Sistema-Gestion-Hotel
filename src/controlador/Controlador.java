/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import utilidades.ExportPDF;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.dao.HabitacionDao;
import modelos.dao.TipoHabitacionDao;
import modelos.entidades.Habitacion;
import utilidades.ImgTabla;
import utilidades.ListaCircularDoble;
import utilidades.ListaSimple;
import vistas.main.Regis_hab;
import vistas.main.Vista_hab;

/**
 *
 * @author Adonay
 */
public class Controlador implements ActionListener, MouseListener, KeyListener {

    DefaultTableModel modelo;
    
    String vistaOn = "";
    
    private Vista_hab vista;
    private Regis_hab registrarHab;

    /* HABITACION */
    Habitacion habitacion = new Habitacion();
    Habitacion habitacionSelected = null;
    HabitacionDao habitacionDao = new HabitacionDao();
    
    /* TIPO */
    TipoHabitacionDao tipoHabitacionDao = new TipoHabitacionDao();

    public Controlador(Vista_hab vista) {
        this.vista = vista;
        mostrarVista("habitacion");
    }

    public void mostrarVista(String str) {
        if (str.equals("habitacion")) {
            vista.setControlador(this);
            vista.iniciar();
            vistaOn = "vistaHabitacion";
            mostrarDatos(vista.tbregishab);
        } else if (str.equals("VistaRegistrarhab")) {
            registrarHab = new Regis_hab(new JFrame(), true);
            registrarHab.setControlador(this);
            registrarHab.iniciar();
            vistaOn = "nuevaHabitacion";
        }
    }
    
    public void eventosBotones(ActionEvent e) throws FileNotFoundException, IOException{
        if (e.getActionCommand().equals("Reporte")) {

            String path = "";

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int request = file.showSaveDialog(registrarHab);

            if (request == JFileChooser.APPROVE_OPTION) {
                path = file.getSelectedFile().getPath();
                
                new ExportPDF(path);
            }

        }else if (e.getActionCommand().equals("Buscar")) {
            try {
                if (!vista.jTbuscar.getText().isEmpty()) {
                    int num = Integer.parseInt(vista.jTbuscar.getText());

                    Habitacion b = new Habitacion();
                    b.setNum_habitacion(num);

                    ListaCircularDoble list = new ListaCircularDoble();

                    list = habitacionDao.selectAll();

                    ListaSimple listaDeBusqueda = new ListaSimple();

                    if (list.buscar(b) != null) {
                        Habitacion obj = (Habitacion) list.buscar(b).getDato();
                        listaDeBusqueda.insertar(obj);
                        mostrarBusqueda(listaDeBusqueda.toArray(), vista.tbregishab);
                    }else {
                        mostrarDatos(vista.tbregishab);
                    }
                }else {
                    mostrarDatos(vista.tbregishab);
                }
                
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
    }

    public void mostrarDatos(JTable tabla) {
        try {
            DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
            modelo = (DefaultTableModel) tabla.getModel();
            modelo.setRowCount(0);
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("0.00",simbolos);
            
            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(4).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);
            
            if (vistaOn.equals("vistaHabitacion")) {
                 ListaCircularDoble<Habitacion> habitaciones = habitacionDao.selectAll();
                
                for (Habitacion x : habitaciones.toArrayAsc()) {
                    if (x.getEstado_habitacion() == 1) {
                        ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                        JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                        modelo.addRow(new Object[]{x.getId_habitacion(), x.getNum_habitacion(), x.getDescr_habitacion(), "$ " + formateador.format(x.getPrecio_habitacion()), x.getTipoH().getNombre_tipo(), x.getDispo_habitacion(), lbImg_delete});
                    }
                }

                tabla.setModel(modelo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mostrarBusqueda(ArrayList lista, JTable tabla) {

        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00",simbolos);

        tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
        tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(4).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);


            for (Object obj: lista) {
                Habitacion x = (Habitacion) obj;
                if (x.getEstado_habitacion() == 1) {
                    ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                    JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                    modelo.addRow(new Object[]{x.getId_habitacion(), x.getNum_habitacion(), x.getDescr_habitacion(), "$ " + formateador.format(x.getPrecio_habitacion()), x.getTipoH().getNombre_tipo(), x.getDispo_habitacion(), lbImg_delete});
                }
            }
            
            tabla.setModel(modelo);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent btn) {

        if(btn.getActionCommand().equals("Agregar")){
            mostrarVista("VistaRegistrarhab");
        }else if(btn.getActionCommand().equals("Reporte")){
            try {
                eventosBotones(btn);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        if(btn.getActionCommand().equals("Buscar")) {
            try {
                eventosBotones(btn);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (vistaOn.equals("vistaHabitacion")) {
            int columna = vista.tbregishab.getSelectedColumn();
            
            try {
                try {
                    if (columna == 6) {
                        int fila = vista.tbregishab.getSelectedRow();
                        String id = vista.tbregishab.getValueAt(fila, 0).toString();
                        ListaCircularDoble<Habitacion> lista = habitacionDao.selectAllTo("id_habitacion", id);
                        habitacionSelected = lista.toArrayAsc().get(0);
                    }

                    if (habitacionSelected != null) {

                        if (habitacionDao.delete(habitacionSelected)) {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Producto eliminado", "El producto ha sido eliminado exitosamente.", DesktopNotify.INFORMATION, 8000);
                            mostrarDatos(vista.tbregishab);
                        }

                        habitacionSelected = null;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (Exception e) {
                    
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
       
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (vistaOn.equals("vistaHabitacion")) {
            if (ke.getKeyCode() == ke.VK_ENTER && !vista.jTbuscar.getText().isEmpty()) {
                try {
                    int num = Integer.parseInt(vista.jTbuscar.getText());
                    
                    Habitacion b = new Habitacion();
                    b.setNum_habitacion(num);
                    
                    ListaCircularDoble list = new ListaCircularDoble();
                    
                    list = habitacionDao.selectAll();
                    
                    ListaSimple listaDeBusqueda = new ListaSimple();
                    
                    if (list.buscar(b) != null) {
                        Habitacion obj = (Habitacion) list.buscar(b).getDato();
                        listaDeBusqueda.insertar(obj);
                        mostrarBusqueda(listaDeBusqueda.toArray(), vista.tbregishab);
                    }else {
                        mostrarDatos(vista.tbregishab);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else {
                mostrarDatos(vista.tbregishab);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {
       
    }
}
