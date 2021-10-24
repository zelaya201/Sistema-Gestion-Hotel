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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import modelos.dao.HotelDao;
import modelos.dao.TipoHabitacionDao;
import modelos.entidades.Habitacion;
import modelos.entidades.Hotel;
import modelos.entidades.Tipo_Habitacion;
import utilidades.CodigoRecursivo;
import utilidades.ImgTabla;
import utilidades.ListaCircularDoble;
import utilidades.ListaSimple;
import vistas.main.Insert_antes;
import vistas.main.Regis_hab;
import vistas.main.Vista_hab;

/**
 *
 * @author Adonay
 */
public class Controlador implements ActionListener, MouseListener, KeyListener, ItemListener {

    DefaultTableModel modelo;
    private ListaCircularDoble<Habitacion> habitaciones;
    
    String principalOn = "";
    String vistaOn = "";
    String subModal = "";

    private Vista_hab vista;
    private Regis_hab registrarHab;
    private Insert_antes insertAntes;

    /* HOTEL */
    HotelDao daoHotel = new HotelDao();

    /* HABITACION */
    Habitacion habitacion = new Habitacion();
    Habitacion habitacionSelected = null;
    HabitacionDao habitacionDao = new HabitacionDao();

    /* TIPO */
    TipoHabitacionDao tipoHabitacionDao = new TipoHabitacionDao();
    Tipo_Habitacion tipoHabitacion = new Tipo_Habitacion();
    

    public Controlador(Vista_hab vista) throws SQLException {
        this.vista = vista;
        habitaciones = new ListaCircularDoble();
        llenarListaCircular();
        mostrarVista("habitacion");
    }

    public void mostrarVista(String str) throws SQLException {
        if (str.equals("habitacion")) {
            vista.setControlador(this);
            principalOn = "vistaHabitacion";
            vista.iniciar();
            mostrarDatos(vista.tbregishab);
        } else if (str.equals("VistaRegistrarhab")) {
            registrarHab = new Regis_hab(new JFrame(), true);
            registrarHab.setControlador(this);
            llenarComboBox();
            vistaOn = "nuevaHabitacion";
            registrarHab.iniciar();
        } else if(str.equals("selectHabitacion")){
            insertAntes = new Insert_antes(new JFrame(), true);
            insertAntes.setControlador(this);
            subModal = "selectHabitacion";
            llenarComboBox();
            insertAntes.iniciar();
        }
    }

    public void eventosBotones(ActionEvent e) throws FileNotFoundException, IOException, SQLException {
        if (e.getActionCommand().equals("Reporte")) {

            String path = "";

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int request = file.showSaveDialog(registrarHab);

            if (request == JFileChooser.APPROVE_OPTION) {
                path = file.getSelectedFile().getPath();
                ListaCircularDoble<Habitacion> habitaciones = habitacionDao.selectAll();
                ListaCircularDoble<Hotel> hotel = daoHotel.selectAll();

                new ExportPDF(path, habitaciones, hotel.toArrayAsc().get(0));
                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                DesktopNotify.showDesktopMessage("Reporte generado", "Ruta: " + path, DesktopNotify.INFORMATION, 8000);
            }

        } else if (e.getActionCommand().equals("Buscar")) {
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
                    } else {
                        mostrarDatos(vista.tbregishab);
                    }
                } else {
                    mostrarDatos(vista.tbregishab);
                }

            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if (e.getActionCommand().equals("Insertar")) {
            if (vistaOn.equals(("nuevaHabitacion"))) {
                if (!registrarHab.jTNumerohab.getText().isEmpty() && !registrarHab.jTpreciohab.getText().isEmpty()
                        && registrarHab.cbTipo.getSelectedIndex() > 0 && !registrarHab.taDescripcion.getText().isEmpty()) {
                    ListaCircularDoble<Habitacion> existe = habitacionDao.selectAllTo("num_habitacion", registrarHab.jTNumerohab.getText());
                    
                    if (existe.isEmpty()) {                        
                        if (registrarHab.cbTipo.getSelectedIndex() > 0) {
                            String v[] = registrarHab.cbTipo.getSelectedItem().toString().split(". ");
                            
                            System.out.println(v[0]);
                            
                            ListaCircularDoble<Tipo_Habitacion> tipos = tipoHabitacionDao.selectId(Integer.parseInt(v[0]));
                            tipoHabitacion = tipos.toArrayAsc().get(0);
                        }
                        
                        Habitacion obj = new Habitacion("NORM" + habitaciones.toArrayAsc().size(), Integer.parseInt(registrarHab.jTNumerohab.getText()), 
                                                        registrarHab.taDescripcion.getText(), Double.parseDouble(registrarHab.jTpreciohab.getText()), 
                                                        1, "DISPONIBLE", new Hotel(1), new Tipo_Habitacion(tipoHabitacion.getId_tipo()));
                        
                        if (habitacionDao.insert(obj)) {
                            habitaciones.insertar(obj);
                            llenarFichero();
                            DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                            DesktopNotify.showDesktopMessage("Habitación registrada", "La habitación ha sido registrada correctamente.", DesktopNotify.INFORMATION, 8000);
                        }else {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("ERROR", "Ha ocurrido un error al registrar la habitación.", DesktopNotify.INFORMATION, 10000);
                        }
                        registrarHab.dispose();
                        vistaOn = "";
                    }else {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Habitación ya existe.", "La habitación ha registrar ya existe.", DesktopNotify.INFORMATION, 10000);
                    }
                    mostrarDatos(vista.tbregishab);
                }else {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.INFORMATION, 10000);
                }
            }
        }else if (e.getActionCommand().equals("InsertarInicio")) {
            if (vistaOn.equals(("nuevaHabitacion"))) {

                if (!registrarHab.jTNumerohab.getText().isEmpty() && !registrarHab.jTpreciohab.getText().isEmpty()
                        && registrarHab.cbTipo.getSelectedIndex() > 0 && !registrarHab.taDescripcion.getText().isEmpty()) {
                    ListaCircularDoble<Habitacion> existe = habitacionDao.selectAllTo("num_habitacion", registrarHab.jTNumerohab.getText());
                    
                    if (existe.isEmpty()) {                        
                        if (registrarHab.cbTipo.getSelectedIndex() > 0) {
                            String v[] = registrarHab.cbTipo.getSelectedItem().toString().split(". ");
                            
                            //System.out.println(v[0]);
                            
                            ListaCircularDoble<Tipo_Habitacion> tipos = tipoHabitacionDao.selectId(Integer.parseInt(v[0]));
                            tipoHabitacion = tipos.toArrayAsc().get(0);
                        }
                        //registrarHab.lbIdHab.getText()
                    
                        Habitacion obj = new Habitacion("MAT0" + this.habitaciones.toArrayAsc().size(), Integer.parseInt(registrarHab.jTNumerohab.getText()), 
                                                        registrarHab.taDescripcion.getText(), Double.parseDouble(registrarHab.jTpreciohab.getText()), 
                                                        1, "DISPONIBLE", new Hotel(1), new Tipo_Habitacion(tipoHabitacion.getId_tipo()));
                        
                        
                        
                        if (habitacionDao.insert(obj)) {
                            this.habitaciones.insertarInicio(obj);
                            llenarFichero();
                            DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                            DesktopNotify.showDesktopMessage("Habitación registrada", "La habitación ha sido registrada correctamente.", DesktopNotify.INFORMATION, 8000);
                        }else {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("ERROR", "Ha ocurrido un error al registrar la habitación.", DesktopNotify.INFORMATION, 10000);
                        }
                        registrarHab.dispose();
                        vistaOn = "";
                    }else {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Habitación ya existe.", "La habitación ha registrar ya existe.", DesktopNotify.INFORMATION, 10000);
                    }
                    mostrarDatos(vista.tbregishab);
                }else {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.INFORMATION, 10000);
                }
            }
        }else if (e.getActionCommand().equals("InsertarFinal")) {
            if (vistaOn.equals(("nuevaHabitacion"))) {

                if (!registrarHab.jTNumerohab.getText().isEmpty() && !registrarHab.jTpreciohab.getText().isEmpty()
                        && registrarHab.cbTipo.getSelectedIndex() > 0 && !registrarHab.taDescripcion.getText().isEmpty()) {
                    ListaCircularDoble<Habitacion> existe = habitacionDao.selectAllTo("num_habitacion", registrarHab.jTNumerohab.getText());
                    
                    if (existe.isEmpty()) {                        
                        if (registrarHab.cbTipo.getSelectedIndex() > 0) {
                            String v[] = registrarHab.cbTipo.getSelectedItem().toString().split(". ");
                            
                            //System.out.println(v[0]);
                            
                            ListaCircularDoble<Tipo_Habitacion> tipos = tipoHabitacionDao.selectId(Integer.parseInt(v[0]));
                            tipoHabitacion = tipos.toArrayAsc().get(0);
                        }
                        //registrarHab.lbIdHab.getText()
                    
                        Habitacion obj = new Habitacion("FIN0" + this.habitaciones.toArrayAsc().size(), Integer.parseInt(registrarHab.jTNumerohab.getText()), 
                                                        registrarHab.taDescripcion.getText(), Double.parseDouble(registrarHab.jTpreciohab.getText()), 
                                                        1, "DISPONIBLE", new Hotel(1), new Tipo_Habitacion(tipoHabitacion.getId_tipo()));
                        
                        
                        
                        if (habitacionDao.insert(obj)) {
                            this.habitaciones.insertarFinal(obj);
                            llenarFichero();
                            DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                            DesktopNotify.showDesktopMessage("Habitación registrada", "La habitación ha sido registrada correctamente.", DesktopNotify.INFORMATION, 8000);
                        }else {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("ERROR", "Ha ocurrido un error al registrar la habitación.", DesktopNotify.INFORMATION, 10000);
                        }
                        registrarHab.dispose();
                        vistaOn = "";
                    }else {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Habitación ya existe.", "La habitación ha registrar ya existe.", DesktopNotify.INFORMATION, 10000);
                    }
                    mostrarDatos(vista.tbregishab);
                }else {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.INFORMATION, 10000);
                }
            }
        }

    }

    public void mostrarDatos(JTable tabla) {
     
        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);
        tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
        tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(4).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);
        if (principalOn.equals("vistaHabitacion")) {
            
            for (Habitacion x : habitaciones.toArrayAsc()) {
                if (x.getEstado_habitacion() == 1) {
                    ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                    JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));
                    
                    modelo.addRow(new Object[]{x.getId_habitacion(), x.getNum_habitacion(), x.getDescr_habitacion(), "$ " + formateador.format(x.getPrecio_habitacion()), x.getTipoH().getNombre_tipo(), x.getDispo_habitacion(), lbImg_delete});
                }
            }
            
            tabla.setModel(modelo);
        }
    }

    public void mostrarBusqueda(ArrayList lista, JTable tabla) {

        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img

        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);

        tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
        tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(4).setCellRenderer(diseño);
        tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);

        for (Object obj : lista) {
            Habitacion x = (Habitacion) obj;
            if (x.getEstado_habitacion() == 1) {
                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                modelo.addRow(new Object[]{x.getId_habitacion(), x.getNum_habitacion(), x.getDescr_habitacion(), "$ " + formateador.format(x.getPrecio_habitacion()), x.getTipoH().getNombre_tipo(), x.getDispo_habitacion(), lbImg_delete});
            }
        }

        tabla.setModel(modelo);
    }

    public void llenarComboBox() throws SQLException {
        
        if(subModal.equals("selectHabitacion")){
            insertAntes.cbHab.setEnabled(true);
            insertAntes.cbHab.removeAllItems();
            insertAntes.cbHab.addItem("Seleccionar habitación");
            
            for(Habitacion x : habitaciones.toArrayAsc()){
                insertAntes.cbHab.addItem(x.getId_habitacion() + " - " + x.getTipoH().getNombre_tipo());
            }
        }else{
            registrarHab.cbTipo.setEnabled(true);
            registrarHab.cbTipo.removeAllItems();
            registrarHab.cbTipo.addItem("Seleccionar");

            ListaCircularDoble<Tipo_Habitacion> listTipo = tipoHabitacionDao.selectAll();

            for (Tipo_Habitacion x : listTipo.toArrayAsc()) {
                registrarHab.cbTipo.addItem(x.getId_tipo() + ". " + x.getNombre_tipo() + " | " + x.getCantidad_tipo() + " personas");
            }
        }

    }
    
    public void llenarListaCircular() throws SQLException{
        ListaCircularDoble<Habitacion> aux = habitacionDao.selectAll();
        int iterador = 0;
        String linea[] = new String[aux.toArrayAsc().size() + 1];
        
        try{
            BufferedReader ficheroEntrada = new BufferedReader(
            new FileReader(new File("ficheroHabitaciones.txt")));

            while (iterador < aux.toArrayAsc().size()) {
                linea[iterador] = ficheroEntrada.readLine();
                iterador++;
            }
 
            ficheroEntrada.close();
        }catch (IOException errorDeFichero){
            System.out.println(errorDeFichero);
        }
        
        iterador = 0;
        
        for(int i = 0; i < aux.toArrayAsc().size(); i++){
            
            for(Habitacion x : aux.toArrayAsc()){
                if(habitaciones.buscar(x) == null){

                    String[] partes = linea[iterador].split(",");

                    if(x.getId_habitacion().equals(partes[0])){
                                                
                        habitaciones.insertarFinal(x);
                        iterador++;
                    }  
                }
            }
        }
      
    }
    
    public void llenarFichero(){
        int iterador = 0;
        
        try{
            BufferedWriter ficheroSalida = new BufferedWriter(
            new FileWriter(new File("ficheroHabitaciones.txt")));
            
            for(Habitacion x : habitaciones.toArrayAsc()){
                ficheroSalida.write(x.getId_habitacion() + "," + iterador);
                ficheroSalida.newLine();
                iterador++;
            }

            ficheroSalida.close();
        }catch (IOException errorDeFichero){
            
        }
    }

    public void generandoCodigo() {
        registrarHab.cbTipo.addItemListener(
                new ItemListener() {
            int i = 0;

            @Override
            public void itemStateChanged(ItemEvent e) { // Numeros correlativos... IMPORTANTE

                if (e.getStateChange() == 1 && !e.getItem().equals("Seleccionar")) {
                    String ant = e.getItem().toString();
                    if (e.getStateChange() == 1 && e.getItem().equals(ant.substring(3, 6))) {
                        registrarHab.lbIdHab.setText(CodigoRecursivo.generarCodigo(e.getItem().toString().substring(3, 6), i++));
                    }
                    registrarHab.lbIdHab.setText(CodigoRecursivo.generarCodigo(e.getItem().toString().substring(3, 6), i++));

                } else {
                    registrarHab.lbIdHab.setText(null);
                }
            }
        }
        );
    }
   

    @Override
    public void actionPerformed(ActionEvent btn) {

        if (btn.getActionCommand().equals("Agregar")) {
            try {
                mostrarVista("VistaRegistrarhab");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (btn.getActionCommand().equals("Reporte")) {
            try {
                eventosBotones(btn);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (btn.getActionCommand().equals("Buscar")) {
            try {
                try {
                    eventosBotones(btn);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        if (btn.getActionCommand().equals("Insertar")) {
            try {
                eventosBotones(btn);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (btn.getActionCommand().equals("InsertarInicio")) {
            try {
                eventosBotones(btn);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (btn.getActionCommand().equals("InsertarFinal")) {
            try {
                eventosBotones(btn);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (btn.getActionCommand().equals("InsertarAntes")) {
            try {
                mostrarVista("selectHabitacion");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (principalOn.equals("vistaHabitacion")) {
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
                            habitaciones.eliminar(habitacionSelected);
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Habitación eliminada", "La habitación ha sido eliminada exitosamente.", DesktopNotify.INFORMATION, 10000);
                            llenarFichero();
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

        if (principalOn.equals("vistaHabitacion")) {
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
                    } else {
                        mostrarDatos(vista.tbregishab);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                mostrarDatos(vista.tbregishab);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    @Override
    public void itemStateChanged(ItemEvent ie) {

    }
}
