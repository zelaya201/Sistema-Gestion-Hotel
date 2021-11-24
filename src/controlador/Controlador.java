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
import modelos.dao.HabitacionDao;
import modelos.dao.HotelDao;
import modelos.dao.TipoHabitacionDao;
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
    ConfirmDialogTipo modalEliminar;
    ListaDoble listitaD;
    
    /* REGISTRO HABITACIÓN */
    private VistaTipo tipoVista;
    private VistaHabitacion habitacionVista;
    private VistaRegistro registroVista;
    
    /* RECEPCIÓN */
    private VistaRecepcion recepVista; 
    private Habitacion recepcionSelected = null;
    
    /* CONFIGURACIÓN */
    private ModalConfig configModal;
    private HotelDao daoHotel = new HotelDao();
    private ModalEditConfig configModalEdit;

    public Controlador(Menu menu) {
        this.menu = menu;
        this.menu.setControlador(this);
        this.menu.iniciar();
        listitaD = new ListaDoble();
    }
    
    public void mostrarModulos(String mod) throws SQLException{
        if(mod.equals("mConfig")){
            configModal = new ModalConfig(new JFrame(), true);
            configModal.setControlador(this);
            principalOn = "mConfig";
            mostrarInfoHotel();
            configModal.iniciar();
        }else if(mod.equals("mRecepcion")){
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
//            metodo
            principalOn = "mHabitacion";
            new CambiaPanel(menu.body, habitacionVista);
        }
    }
    
    public void mostrarModals(String modals) throws SQLException{
        if(modals.equals("modalConfig")){
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
    }
    
    public void mostrarTablaTipos(JTable tabla) throws SQLException{
        md = (DefaultTableModel) tabla.getModel();
        md.setRowCount(0);
        
        DefaultTableCellRenderer align = new DefaultTableCellRenderer();
        align.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(align);
        tabla.getColumnModel().getColumn(1).setCellRenderer(align);
        tabla.getColumnModel().getColumn(2).setCellRenderer(align);
        
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
                mostrarModulos("mConfig");
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
            } catch (Exception e) {
            }
        }
        
        if (btn.getActionCommand().equals("ModificarTipo")) {
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
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
                                DesktopNotify.showDesktopMessage("No puede eliminar este usuario", "No es posible eliminar el usuario logueado actualmente.", DesktopNotify.INFORMATION, 8000);
                                mostrarModulos("mTipo");
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
