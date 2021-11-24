/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.itextpdf.layout.borders.Border;
import ds.desktop.notify.DesktopNotify;
import ds.desktop.notify.NotifyTheme;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import modelos.dao.HabitacionDao;
import modelos.dao.HotelDao;
import modelos.dao.ProductoDao;
import modelos.dao.UsuarioDao;
import modelos.entidades.Habitacion;
import modelos.entidades.Hotel;
import modelos.entidades.Producto;
import modelos.entidades.Usuario;
import utilidades.CambiaPanel;
import utilidades.ExportPDF;
import utilidades.ListaSimple;
import vistas.main.Menu;
import vistas.modulos.Dashboard;
import vistas.modulos.ModalConfig;
import vistas.modulos.ModalEditConfig;
import vistas.modulos.VistaRecepcion;
import vistas.modulos.VistaRegistro;

/**
 *
 * @author Adonay
 */
public class Controlador implements ActionListener, MouseListener{
    private Menu menu;
    private String principalOn = "";
    private String modalOn = "";
    
    /* HABITACIÓN */
    private HabitacionDao daoHabitacion = new HabitacionDao();
    
    /* REGISTRO HABITACIÓN */
    private VistaRegistro registroVista;
    
    /* RECEPCIÓN */
    private VistaRecepcion recepVista; 
    private Habitacion recepcionSelected = null;
    
    /* PRODUCTO */
    private ProductoDao daoProducto = new ProductoDao();
    
    /* PRODUCTO */
    private UsuarioDao daoUsuario = new UsuarioDao();
    
    /* CONFIGURACIÓN */
    private ModalConfig configModal;
    private HotelDao daoHotel = new HotelDao();
    private ModalEditConfig configModalEdit;
    
     /* DASHBOARD */
    private Dashboard dashVista;

    public Controlador(Menu menu) {
        this.menu = menu;
        this.menu.setControlador(this);
        this.menu.iniciar();
    }
    
    public void mostrarModulos(String mod) throws SQLException{
        if(mod.equals("mRecepcion")){
            recepVista = new VistaRecepcion();
            recepVista.setControlador(this);
            generarHabitaciones();
            principalOn = "mRecepcion";
            new CambiaPanel(menu.body, recepVista);
        }else if(mod.equals("mRegistro")){
            registroVista = new VistaRegistro();
            registroVista.setControlador(this);
            mostrarInfoHab();
            principalOn = "mRegistro";
            new CambiaPanel(menu.body, registroVista);
        }else if(mod.equals("mDashboard")){
            dashVista = new Dashboard();
            cargarDashboard();
            principalOn = "mDashboard";
            mostrarDatos();
            new CambiaPanel(menu.body, dashVista);
        }
    }
    
    public void mostrarModals(String modals) throws SQLException{
        if(modals.equals("mConfig")){
            configModal = new ModalConfig(new JFrame(), true);
            configModal.setControlador(this);
            modalOn = "mConfig";
            mostrarInfoHotel();
            configModal.iniciar();
        }else if(modals.equals("modalConfig")){
            configModal.dispose();
            configModalEdit = new ModalEditConfig(new JFrame(), true);
            configModalEdit.setControlador(this);
            modalOn = "modalConfig";
            mostrarInfoHotel();
            configModalEdit.iniciar();
            modalOn = "";
        }
    }
    

    public void accionesDeBotones(ActionEvent btn) throws SQLException, IOException{
        if(btn.getActionCommand().equals("GuardarInfo") && modalOn == "modalConfig"){
            if(!configModalEdit.tfNom.getText().isEmpty() && !configModalEdit.tfDir.getText().isEmpty() && !configModalEdit.tfTel.getText().isEmpty()){
                if(daoHotel.update(new Hotel(1, configModalEdit.tfNom.getText(), configModalEdit.tfDir.getText(), configModalEdit.tfTel.getText()))){
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Información de Hotel modificada", "La información del Hotel se modificó correctamente.", DesktopNotify.SUCCESS, 8000);
                    configModalEdit.dispose();
                    modalOn = "";
                    mostrarModals("mConfig");
                }
            }else{
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos", DesktopNotify.WARNING, 8000);
            }
        }
        
        if (btn.getActionCommand().equals("Reporte")) {

            String path = "";

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int request = file.showSaveDialog(menu);

            if (request == JFileChooser.APPROVE_OPTION) {
                path = file.getSelectedFile().getPath();
                ListaSimple<Habitacion> habitaciones = daoHabitacion.selectAll();
                ListaSimple<Hotel> hotel = daoHotel.selectAll();

                new ExportPDF(path, habitaciones, hotel.toArray().get(0));
                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                DesktopNotify.showDesktopMessage("Reporte generado", "Ruta: " + path, DesktopNotify.INFORMATION, 10000);
            }

        }
    }
    
    public void mostrarInfoHotel() throws SQLException{
        Hotel hotelInfo = daoHotel.selectAll().toArray().get(0);     
        if(modalOn.equals("modalConfig")){
            configModalEdit.tfNom.setText(hotelInfo.getNombre());
            configModalEdit.tfDir.setText(hotelInfo.getDireccion());
            configModalEdit.tfTel.setText(hotelInfo.getTelefono());
        }else{
            configModal.lbNomHotel.setText(hotelInfo.getNombre());
            configModal.lbDirHotel.setText(hotelInfo.getDireccion());
            configModal.lbTelHotel.setText(hotelInfo.getTelefono());
        }       

    }
    
    public void mostrarInfoHab() throws SQLException{

        Habitacion habi = daoHabitacion.selectId(recepcionSelected.getNumHabitacion()).toArray().get(0);
        registroVista.lbDescrip.setText(habi.getDescripcion());
        registroVista.lbNumHab.setText(String.valueOf(habi.getNumHabitacion()));     
        registroVista.lbEstado.setForeground(Color.white);
        
        if(habi.getDisposicion().equals("DISPONIBLE")){
            registroVista.lbEstado.setBackground(new Color(0, 166, 90));
        }else if(habi.getDisposicion().equals("OCUPADA")){
            registroVista.lbEstado.setBackground(new Color(223, 56, 56));
        }else{
            registroVista.lbEstado.setBackground(new Color(61,137,248));
        }
        
        registroVista.lbEstado.setText(habi.getDisposicion());
        registroVista.lbTipoHab.setText(habi.getTipoHabitacion().getNombre());
        registroVista.lbPrecio.setText("$" + String.valueOf(habi.getPrecio()));
        
    }
    
    public void generarHabitaciones() throws SQLException{

        ListaSimple<Habitacion> listaHab = this.daoHabitacion.selectAll();
        
        for(Habitacion x : listaHab.toArray()){
            
            GridBagConstraints gridBagConstraints;
            JPanel panel = new javax.swing.JPanel();
            JLabel lbNoHab = new JLabel();
            JLabel lbDispo = new JLabel();
            JLabel lbTipo = new JLabel();
            JLabel lbIcono = new JLabel();
            JScrollPane scroll = new JScrollPane();

            if(x.getDisposicion().equals("DISPONIBLE")){
                panel.setBackground(new java.awt.Color(0, 166, 90));
                lbDispo.setBackground(new java.awt.Color(0, 147, 93));
            }else if(x.getDisposicion().equals("OCUPADA")){
                panel.setBackground(new java.awt.Color(223, 56, 56));
                lbDispo.setBackground(new java.awt.Color(187, 56, 56));
            }else{
                panel.setBackground(new java.awt.Color(61,137,248));
                lbDispo.setBackground(new java.awt.Color(61, 115, 213));
            }
     
            panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            panel.setName(String.valueOf(x.getNumHabitacion()));
            panel.setLayout(new java.awt.GridBagLayout());

            lbNoHab.setFont(new java.awt.Font("Calibri", 1, 16));
            lbNoHab.setForeground(new java.awt.Color(255, 255, 255));
            lbNoHab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lbNoHab.setText("N° DE HABITACIÓN: " + x.getNumHabitacion());
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipadx = 20;
            gridBagConstraints.ipady = 25;
            panel.add(lbNoHab, gridBagConstraints);

            lbDispo.setFont(new java.awt.Font("Calibri", 1, 16));
            lbDispo.setForeground(new java.awt.Color(255, 255, 255));
            lbDispo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lbDispo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow.png")));
            lbDispo.setText(x.getDisposicion());
            lbDispo.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
            lbDispo.setOpaque(true);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 2;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.ipady = 5;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
            panel.add(lbDispo, gridBagConstraints);

            lbTipo.setFont(new java.awt.Font("Calibri", 1, 14));
            lbTipo.setForeground(new java.awt.Color(255, 255, 255));
            lbTipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lbTipo.setText("TIPO: " + x.getTipoHabitacion().getNombre());
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            panel.add(lbTipo, gridBagConstraints);

            lbIcono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lbIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bed.png")));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridheight = 3;
            gridBagConstraints.ipadx = 15;
            panel.add(lbIcono, gridBagConstraints);
            panel.addMouseListener(this);
            
            scroll.setViewportView(panel);
            scroll.setBorder((javax.swing.border.Border) Border.NO_BORDER);
            recepVista.habPanel.add(scroll);
        }

    }
    public void cargarDashboard() throws SQLException{
        
        ListaSimple<Habitacion> habitacion = daoHabitacion.selectAll();
        ListaSimple<Producto> producto = daoProducto.selectAll();
        ListaSimple<Usuario> usuario = daoUsuario.selectAll();
        
        int cantDispo = 0;
        int cantReserv = 0;
        int cantOcup = 0;
                
        /*ALERTAS TOTALES*/
        dashVista.lbTotHab.setText(String.valueOf(habitacion.toArray().size()));
        dashVista.lbTotProd.setText(String.valueOf(producto.toArray().size()));
        dashVista.lbTotUsu.setText(String.valueOf(usuario.toArray().size()));
        
        /*ALERTAS DE DISPONIBILIDAD*/
        for(Habitacion x: habitacion.toArray()){
            if(x.getDisposicion().equals("DISPONIBLE")){
                cantDispo++;
            }else if(x.getDisposicion().equals("OCUPADA")){
                cantOcup++;
            }else if(x.getDisposicion().equals("RESERVADA")){
                cantReserv++;
            }
        }
        
        dashVista.lbHabDis.setText(String.valueOf(cantDispo));
        dashVista.lbHabOcu.setText(String.valueOf(cantOcup));
        dashVista.lbHabRes.setText(String.valueOf(cantReserv));
        
    }
    
    public void mostrarDatos() throws SQLException{
        if(principalOn.equals("mDashboard")){
            DefaultTableModel modelo = (DefaultTableModel) dashVista.tablaHabAgre.getModel();
            modelo.setRowCount(0);
            ListaSimple<Habitacion> habitacion = daoHabitacion.selectAll();
            
            for(Habitacion x: habitacion.toArray()){
                if(x.getDisposicion().equals("DISPONIBLE")){
                   modelo.addRow(new Object[]{x.getNumHabitacion(), x.getDescripcion(), x.getTipoHabitacion().getNombre(),"$ "+ x.getPrecio()});
                }
            }
            dashVista.tablaHabAgre.setModel(modelo);
        }
    }

    @Override
    public void actionPerformed(ActionEvent btn) {
        if(btn.getActionCommand().equals("Configuracion")){
            try {
                mostrarModals("mConfig");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        
        if(btn.getActionCommand().equals("Recepcion")){
            try {
                mostrarModulos("mRecepcion");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(btn.getActionCommand().equals("ModificarInfo")){
            try {
                mostrarModals("modalConfig");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(btn.getActionCommand().equals("GuardarInfo")){
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(btn.getActionCommand().equals("Dashboard")){
            try {
                mostrarModulos("mDashboard");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(btn.getActionCommand().equals("Reporte")){
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
        
        if(principalOn.equals("mRecepcion")){
            recepcionSelected = new Habitacion();
            recepcionSelected.setNumHabitacion(Integer.parseInt(me.getComponent().getName()));
            
            try {
                mostrarModulos("mRegistro");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
}
