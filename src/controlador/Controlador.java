/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
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
import modelos.entidades.Tipo_Habitacion;
import utilidades.ImgTabla;
import utilidades.ListaCircularDoble;
import vistas.main.Regis_hab;
import vistas.main.Vista_hab;

/**
 *
 * @author Adonay
 */
public class Controlador implements ActionListener {

    DefaultTableModel modelo;

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
            mostrarDatos(vista.tbregishab);
        } else if (str.equals("VistaRegistrarhab")) {
            registrarHab = new Regis_hab(new JFrame(), true);
            registrarHab.setControlador(this);
            registrarHab.iniciar();
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
            
            ListaCircularDoble habitaciones = habitacionDao.selectAll();
                
            for (Object obj : habitaciones.toArrayAsc()) {
                Habitacion x = (Habitacion) obj;

                if (x.getEstado_habitacion() == 1) {
                    ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                    JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));
                    
                    modelo.addRow(new Object[]{x.getId_habitacion(), x.getNum_habitacion(), x.getDescr_habitacion(), formateador.format(x.getPrecio_habitacion()), x.getTipoH().getNombre_tipo(), x.getDispo_habitacion(), lbImg_delete});
                }
            }
            
            tabla.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void actionPerformed(ActionEvent btn) {

        if(btn.getActionCommand().equals("Agregar")){
            mostrarVista("VistaRegistrarhab");
        }
    }
}
