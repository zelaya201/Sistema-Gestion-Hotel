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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.dao.ClienteDao;
import modelos.dao.HabitacionDao;
import modelos.dao.HotelDao;
import modelos.dao.TipoHabitacionDao;
import modelos.entidades.Cliente;
import modelos.entidades.Habitacion;
import modelos.entidades.Hotel;
import modelos.entidades.TipoHabitacion;
import utilidades.CambiaPanel;
import utilidades.ImgTabla;
import utilidades.ListaDoble;
import utilidades.ListaSimple;
import vistas.main.Menu;
import vistas.modulos.ConfirmDialogTipo;
import vistas.modulos.ModalConfig;
import vistas.modulos.ModalEditConfig;
import vistas.modulos.VistaHabitacion;
import vistas.modulos.VistaRecepcion;
import vistas.modulos.VistaRegistro;
import vistas.modulos.VistaTipo;
import vistas.modulos.modalHabitacion;

/**
 *
 * @author Adonay
 */
public class Controlador implements ActionListener, MouseListener{
    private Menu menu;
    private String principalOn = "";
    private String modalOn = "";
    DefaultTableModel md;
    
    
    /* HABITACIÓN */
    private HabitacionDao daoHabitacion = new HabitacionDao();
    private TipoHabitacionDao tipoHabDao = new TipoHabitacionDao();
    private TipoHabitacion selectedTipo = null;
    private Habitacion habitacionSelected = null;
    
    ConfirmDialogTipo modalEliminar;
    modalHabitacion modalHab;
    
    
    /* REGISTRO HABITACIÓN */
    private VistaTipo tipoVista;
    private VistaHabitacion habitacionVista;
    private VistaRegistro registroVista;
    
    /* RECEPCIÓN */
    private VistaRecepcion recepVista; 
    private Habitacion recepcionSelected = null;
    private ClienteDao daoCliente;
    
    /* CONFIGURACIÓN */
    private ModalConfig configModal;
    private HotelDao daoHotel = new HotelDao();
    private ModalEditConfig configModalEdit;

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
            llenarComboRegistro();
            principalOn = "mRegistro";
            new CambiaPanel(menu.body, registroVista);
        }else if(mod.equals("mTipo")){
            tipoVista = new VistaTipo();
            tipoVista.setControlador(this);
//            Metodo
            mostrarTablaTipos(tipoVista.tablaTiposHab);
            principalOn = "mTipo";
            new CambiaPanel(menu.body, tipoVista);
        }else if(mod.equals("mHabitacion")){
            habitacionVista = new VistaHabitacion();
            habitacionVista.setControlador(this);
            mostrarTablaHabitaciones(habitacionVista.tablaHabitaciones);
            principalOn = "mHabitacion";
            new CambiaPanel(menu.body, habitacionVista);
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
        }else if(modals.equals("eliminarTipos")){
            modalEliminar = new ConfirmDialogTipo(new JFrame(), true);
            modalEliminar.setControlador(this);
            modalOn = "mEliminarTipo";
            
            modalEliminar.header.setText("Eliminar ");
            modalEliminar.textDialog.setText("<html>¿Eliminar el tipo de habitación <b>" + selectedTipo.getNombre() + "</b>? </html>");
            modalEliminar.btnEliminar.setText("Eliminar");
            modalEliminar.iniciar();
//            tipoHabDao.eliminar(selectedTipo);
        }else if(modals.equals("modalNewHabitacion")){
            modalHab = new modalHabitacion(new JFrame(), true);
            modalHab.setControlador(this);
            modalOn = "modalNewHabitacion";
            llenarComboBox();
            modalHab.iniciar();
        } else if(modals.equals("eliminarHabitacion")){
            modalEliminar = new ConfirmDialogTipo(new JFrame(), true);
            modalEliminar.setControlador(this);
            modalOn = "modalEliminarHab";
            
            modalEliminar.header.setText("Eliminar ");
            modalEliminar.textDialog.setText("<html>¿Eliminar la habitación número <b>" + habitacionSelected.getNumHabitacion() + "</b>? </html>");
            modalEliminar.btnEliminar.setText("Eliminar");
            modalEliminar.iniciar();
        } else if(modals.equals("modalModHabitacion")){
            modalHab = new modalHabitacion(new JFrame(), true);
            modalHab.setControlador(this);
            modalOn = "modalModHab";
            
            modalHab.btnHabitacionReg.setText("Modificar");
            modalHab.btnHabitacionReg.setActionCommand("ModificarHabitacion");
            modalHab.txtNumHabitacion.setEditable(false);
            modalHab.txtNumHabitacion.setText(String.valueOf(habitacionSelected.getNumHabitacion()));
            modalHab.txtDescripcionHab.setText(habitacionSelected.getDescripcion());
            modalHab.txtPrecioHab.setText(String.valueOf(habitacionSelected.getPrecio()));
            llenarComboBox();
            modalHab.iniciar();
        }
    }
    
    public void accionesDeBotones(ActionEvent btn) throws SQLException{
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
        
        if (principalOn.equals("mTipo")) {
            if (btn.getActionCommand().equals("GuardarTipo")) {
                if (!tipoVista.tfNombreTipo.getText().isEmpty() && !tipoVista.tfCantidadTipo.getText().isEmpty()) {
                    if (tipoHabDao.validarTipos(new TipoHabitacion(tipoVista.tfNombreTipo.getText(), Integer.parseInt(tipoVista.tfCantidadTipo.getText()))) == true) {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("No se puede agregar", "El tipo de habitación de ya existe.", DesktopNotify.WARNING, 8000);

                        mostrarModulos("mTipo");
                    } else if (tipoHabDao.insert(new TipoHabitacion(tipoVista.tfNombreTipo.getText(), Integer.parseInt(tipoVista.tfCantidadTipo.getText())))) {                    
                        DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                        DesktopNotify.showDesktopMessage("Tipo de Habitación modificado", "El registro se ha hecho exitosamente.", DesktopNotify.SUCCESS, 8000);

                        mostrarModulos("mTipo");
                    }
                }
            }else if (btn.getActionCommand().equals("ModificarTipo")) {
                if (!tipoVista.tfNombreTipo.getText().isEmpty() && !tipoVista.tfCantidadTipo.getText().isEmpty()) {
                    selectedTipo.setNombre(tipoVista.tfNombreTipo.getText());
                    selectedTipo.setCantidad(Integer.parseInt(tipoVista.tfCantidadTipo.getText()));
                    if (tipoHabDao.update(selectedTipo)) {                    

                        DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                        DesktopNotify.showDesktopMessage("Tipo de Habitación modificado", "Modificación se ha hecho exitosamente.", DesktopNotify.SUCCESS, 8000);

                        mostrarModulos("mTipo");
                    }
                }
            }
        }
        if (principalOn.equals("mHabitacion")) {
            if (btn.getActionCommand().equals("RegistrarHabitacion") && modalOn.equals("modalNewHabitacion")) {
//              
                if (!modalHab.txtNumHabitacion.getText().isEmpty() && !modalHab.txtDescripcionHab.getText().isEmpty() &&
                        !modalHab.txtPrecioHab.getText().isEmpty() && modalHab.cbTiposHabitacion.getSelectedIndex() > 0) {
                    if (this.daoHabitacion.validarNumeroHabitacion(modalHab.txtNumHabitacion.getText()) == true) {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Número de Habitacion Duplicado", "El número de habitación ya existe", DesktopNotify.SUCCESS, 8000);
                        modalHab.dispose();
//                        mostrarModulos("mHabitacion");
                    } else {
                        TipoHabitacion tipo = new TipoHabitacion();
                        tipo.setIdTipo(Integer.parseInt(modalHab.cbTiposHabitacion.getSelectedItem().toString().substring(0, 1)));
                        if (daoHabitacion.insert(new Habitacion(Integer.parseInt(modalHab.txtNumHabitacion.getText()), modalHab.txtDescripcionHab.getText(), Double.parseDouble(modalHab.txtPrecioHab.getText()), 1, "DISPONIBLE", tipo, new Hotel(1)))) {
                            DesktopNotify.setDefaultTheme(NotifyTheme.LightBlue);
                            DesktopNotify.showDesktopMessage("Registro de Habitación", "El registro se ha hecho exitosamente.", DesktopNotify.INFORMATION, 8000);
                            modalHab.dispose();
                            mostrarModulos("mHabitacion");
                        }
                    }
                }
                
            } 
            if (btn.getActionCommand().equals("ModificarHabitacion") && modalOn.equals("modalModHab")) {
                
                if (!modalHab.txtNumHabitacion.getText().isEmpty() && !modalHab.txtDescripcionHab.getText().isEmpty() &&
                        !modalHab.txtPrecioHab.getText().isEmpty() && modalHab.cbTiposHabitacion.getSelectedIndex() > 0) {
                    
                    habitacionSelected.setNumHabitacion(Integer.parseInt(modalHab.txtNumHabitacion.getText()));
                    habitacionSelected.setDescripcion(modalHab.txtDescripcionHab.getText());
                    habitacionSelected.setPrecio(Double.parseDouble(modalHab.txtPrecioHab.getText()));
                    
                    TipoHabitacion tipo = new TipoHabitacion();
                    tipo.setIdTipo(Integer.parseInt(modalHab.cbTiposHabitacion.getSelectedItem().toString().substring(0, 1)));
                    habitacionSelected.setTipoHabitacion(tipo);
                    habitacionSelected.setHotel(new Hotel(1));
                    if (daoHabitacion.update(habitacionSelected)) {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                        DesktopNotify.showDesktopMessage("Registro de Habitación", "El registro se ha modificado con e.", DesktopNotify.SUCCESS, 8000);
                        modalHab.dispose();
                        mostrarModulos("mHabitacion");
                    }
                }
            }
            
        }
    }
    
    public void llenarComboBox() throws SQLException{
        if (modalOn.equals("modalNewHabitacion") || modalOn.equals("modalModHab")) {
            modalHab.cbTiposHabitacion.removeAllItems();
            modalHab.cbTiposHabitacion.addItem("Seleccione");
            String dato = "";
            
            ListaSimple<TipoHabitacion> tipo = tipoHabDao.selectAll();
            for (TipoHabitacion x : tipo.toArray()) {
                dato = x.getIdTipo() + " - " + x.getNombre();
                this.modalHab.cbTiposHabitacion.addItem(dato);
            }
        } 
    }
    public void llenarComboRegistro() throws SQLException{
            registroVista.cbHuesped.removeAllItems();
            registroVista.cbHuesped.addItem("Seleccione");
            String dato = "";
            
            ListaSimple<Cliente> huesped = daoCliente.selectAll();
            for (Cliente x : huesped.toArray()) {
                dato = x.getDui() + " | " + x.getNombre();
                this.registroVista.cbHuesped.addItem(dato);
            }
        
    }
    
    public void mostrarTablaTipos(JTable tabla) throws SQLException{
        md = (DefaultTableModel) tabla.getModel();
        DefaultTableCellRenderer d = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0);
        md.setRowCount(0);
        
        tabla.getColumnModel().getColumn(0).setCellRenderer(d);
        tabla.getColumnModel().getColumn(1).setCellRenderer(d);
        tabla.getColumnModel().getColumn(2).setCellRenderer(d);
        
        tabla.setDefaultRenderer(Object.class, new ImgTabla());
        
            for (TipoHabitacion tipos : tipoHabDao.selectAll().toArray()) {
                try {
                    ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editarTipo.png"));
                    JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));
                    
                    ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                    JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));
                    
                    md.addRow(new Object[]{
                    tipos.getIdTipo(),
                    tipos.getNombre(),
                    tipos.getCantidad(),
                    lbImg_edit,
                    lbImg_delete});
                    tabla.setRowHeight(40);
                } catch (Exception ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (md.getRowCount() < 1) {
                md.addRow(new Object[]{"Ningun resultado encontrado"});
            }
            tabla.setModel(md);
    }
    
    public void mostrarTablaHabitaciones(JTable tabla) throws SQLException{
        md = (DefaultTableModel) tabla.getModel();
        DefaultTableCellRenderer d = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0);
        md.setRowCount(0);
        
        tabla.getColumnModel().getColumn(0).setCellRenderer(d); 
        
        tabla.getColumnModel().getColumn(1).setCellRenderer(d);
        tabla.getColumnModel().getColumn(2).setCellRenderer(d);
        tabla.getColumnModel().getColumn(3).setCellRenderer(d);
        tabla.getColumnModel().getColumn(4).setCellRenderer(d);
        
        tabla.setDefaultRenderer(Object.class, new ImgTabla());
        
        for (Habitacion hab : daoHabitacion.selectAll().toArray()) {
                try {
                    ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editarTipo.png"));
                    JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));
                    
                    ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                    JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));
                    
                    md.addRow(new Object[]{
                    hab.getNumHabitacion(),
                    hab.getDescripcion(),
                    "$" + hab.getPrecio(),
                    hab.getTipoHabitacion().getNombre(),
                    hab.getDisposicion(),
                    lbImg_edit,
                    lbImg_delete});
                    tabla.setRowHeight(40);
                } catch (Exception ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (md.getRowCount() < 1) {
                md.addRow(new Object[]{"Ningun resultado encontrado"});
            }
            tabla.setModel(md);
        
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
            }
        }
        
        if (btn.getActionCommand().equals("Tipo")) {
            try {
                mostrarModulos("mTipo");
                
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (btn.getActionCommand().equals("GuardarTipo")) {
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (btn.getActionCommand().equals("CancelarTipo")) {
            try {
                mostrarModulos("mTipo");
            } catch (SQLException e) {
            }
        }
        
        if (btn.getActionCommand().equals("ModificarTipo")) {
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (btn.getActionCommand().equals("NuevaHabitacion")) {
            try {
                mostrarModals("modalNewHabitacion");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (btn.getActionCommand().equals("RegistrarHabitacion")) {
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (btn.getActionCommand().equals("ModificarHabitacion")) {
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (btn.getActionCommand().equals("CancelarHabitacion")) {
            modalHab.dispose();
        }
        
        if (btn.getActionCommand().equals("Habitacion")) {
            try {
                mostrarModulos("mHabitacion");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (principalOn.equals("mTipo") && me.getSource() == tipoVista.tablaTiposHab) {
            
            int col = tipoVista.tablaTiposHab.getSelectedColumn();
            try {
                if (col == 4) {
                    int fila = tipoVista.tablaTiposHab.getSelectedRow();
                    int id = (int) tipoVista.tablaTiposHab.getValueAt(fila, 0);
                    
                    ArrayList<TipoHabitacion> th = tipoHabDao.selectAllTo("id_tipo", String.valueOf(id));
                    selectedTipo = th.get(0);
                    mostrarModals("eliminarTipos");
                } else if(col == 3){
                    int fila = tipoVista.tablaTiposHab.getSelectedRow();
                    int id = (int) tipoVista.tablaTiposHab.getValueAt(fila, 0);
                    
                    ArrayList<TipoHabitacion> th = tipoHabDao.selectAllTo("id_tipo", String.valueOf(id));
                    selectedTipo = th.get(0);
                    
                    if (!th.isEmpty()) {
                        tipoVista.tfNombreTipo.setText(selectedTipo.getNombre());
                        tipoVista.tfCantidadTipo.setText(String.valueOf(selectedTipo.getCantidad()));
                        tipoVista.btnGuardarTipo.setText("Modificar");
                        tipoVista.btnGuardarTipo.setActionCommand("ModificarTipo");
                    }
                    
                }
            } catch (SQLException e) {
                System.out.println("ERROR en el mouse clicked del tipo > " + e);
            }
        }
        
        if (principalOn.equals("mHabitacion") && me.getSource() == habitacionVista.tablaHabitaciones) {
            int col = habitacionVista.tablaHabitaciones.getSelectedColumn();
            try {
                if (col == 6) {
                    int fila = habitacionVista.tablaHabitaciones.getSelectedRow();
                    int numHab = (int) habitacionVista.tablaHabitaciones.getValueAt(fila, 0);
                    
                    ListaSimple<Habitacion> habL  = daoHabitacion.selectAllTo("num_habitacion", String.valueOf(numHab));
                    
                    habitacionSelected = habL.toArray().get(0);
                    mostrarModals("eliminarHabitacion");
                } else if(col == 5){
                    int fila = habitacionVista.tablaHabitaciones.getSelectedRow();
                    int numHab = (int) habitacionVista.tablaHabitaciones.getValueAt(fila, 0);
                    
                    ListaSimple<Habitacion> habL  = daoHabitacion.selectAllTo("num_habitacion", String.valueOf(numHab));
                    habitacionSelected = habL.toArray().get(0);
                    
                    if (!habL.isEmpty()) {
                        mostrarModals("modalModHabitacion");
                        
                    }
                    
                }
            } catch (SQLException e) {
                System.out.println("ERROR en el mouse clicked del tipo > " + e);
            }
        }
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
        if(modalOn.equals("mEliminarTipo") && me.getSource().equals(modalEliminar.btnEliminar)){
           try { 
                eventoLabel("btnEliminarTipo");
           } catch (Exception e) {
                
           }
        }
        if(modalOn.equals("modalEliminarHab") && me.getSource().equals(modalEliminar.btnEliminar)){
            try {
                eventoLabel("btnEliminarHabitacion");
            } catch (Exception e) {
            }
        }
    }
    
    public void eventoLabel(String btn){
        if (principalOn.equals("mTipo")) {
            if (modalOn.equals("mEliminarTipo")) {
                if (btn.equals("btnEliminarTipo")) {
                    try {
                        if (selectedTipo != null) {
                            if (tipoHabDao.eliminar(selectedTipo)) {
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Tipo eliminado", "El tipo habitación se ha eliminado exitosamente.", DesktopNotify.INFORMATION, 8000);
                                mostrarModulos("mTipo");
                                modalEliminar.dispose();
                                }
                        }else{
                                modalEliminar.dispose();
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Problema con eliminar el tipo", "No es posible realizar esta acción.", DesktopNotify.INFORMATION, 8000);
                                mostrarModulos("mTipo");
                        }
                        } catch (SQLException e) {
                    
                    }
                }
            }
        }
        if (principalOn.equals("mHabitacion")) {
            if (modalOn.equals("modalEliminarHab")) {
                if (btn.equals("btnEliminarHabitacion")) {
                    try {
                        if (habitacionSelected != null) {
                            if (daoHabitacion.delete(habitacionSelected)) {
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Habitación vacia eliminada", "La habitación se ha eliminado exitosamente.", DesktopNotify.INFORMATION, 8000);
                                mostrarModulos("mHabitacion");
                                modalEliminar.dispose();
                            }
                        }
                    } catch (SQLException e) {
                    }
                }
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
