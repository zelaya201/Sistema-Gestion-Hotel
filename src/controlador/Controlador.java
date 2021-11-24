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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import modelos.dao.HabitacionDao;
import modelos.dao.HotelDao;
import modelos.entidades.Habitacion;
import modelos.entidades.Hotel;
import utilidades.CambiaPanel;
import utilidades.ListaCircularDoble;
import vistas.main.Menu;
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
            principalOn = "mRegistro";
            new CambiaPanel(menu.body, registroVista);
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
    }
    
    public void mostrarInfoHotel() throws SQLException{
        Hotel hotelInfo = daoHotel.selectAll().toArrayDesc().get(0);
        
        if(modalOn.equals("modalConfig")){
            configModalEdit.tfNom.setText(hotelInfo.getNom_hotel());
            configModalEdit.tfDir.setText(hotelInfo.getDir_hotel());
            configModalEdit.tfTel.setText(hotelInfo.getTel_hotel());
        }else{
            configModal.lbNomHotel.setText(hotelInfo.getNom_hotel());
            configModal.lbDirHotel.setText(hotelInfo.getDir_hotel());
            configModal.lbTelHotel.setText(hotelInfo.getTel_hotel());
        }       
        
    }
    
    public void mostrarInfoHab() throws SQLException{
        Habitacion habi = daoHabitacion.selectId(recepcionSelected.getNum_habitacion()).toArrayAsc().get(0);
        registroVista.lbDescrip.setText(habi.getDescr_habitacion());
        registroVista.lbNumHab.setText(String.valueOf(habi.getNum_habitacion()));
        
        registroVista.lbEstado.setForeground(Color.white);
        
        if(habi.getDispo_habitacion().equals("DISPONIBLE")){
            registroVista.lbEstado.setBackground(new Color(0, 166, 90));
        }else if(habi.getDispo_habitacion().equals("OCUPADA")){
            registroVista.lbEstado.setBackground(new Color(223, 56, 56));
        }else{
            registroVista.lbEstado.setBackground(new Color(61,137,248));
        }
        
        registroVista.lbEstado.setText(habi.getDispo_habitacion());
        registroVista.lbTipoHab.setText(habi.getTipoH().getNombre_tipo());
        registroVista.lbPrecio.setText("$" + String.valueOf(habi.getPrecio_habitacion()));
        
    }
    
    public void generarHabitaciones() throws SQLException{

        ListaCircularDoble<Habitacion> listaHab = this.daoHabitacion.selectAll();
        
        for(Habitacion x : listaHab.toArrayAsc()){
            
            GridBagConstraints gridBagConstraints;
            JPanel panel = new javax.swing.JPanel();
            JLabel lbNoHab = new JLabel();
            JLabel lbDispo = new JLabel();
            JLabel lbTipo = new JLabel();
            JLabel lbIcono = new JLabel();
            JScrollPane scroll = new JScrollPane();

            if(x.getDispo_habitacion().equals("DISPONIBLE")){
                panel.setBackground(new java.awt.Color(0, 166, 90));
                lbDispo.setBackground(new java.awt.Color(0, 147, 93));
            }else if(x.getDispo_habitacion().equals("OCUPADA")){
                panel.setBackground(new java.awt.Color(223, 56, 56));
                lbDispo.setBackground(new java.awt.Color(187, 56, 56));
            }else{
                panel.setBackground(new java.awt.Color(61,137,248));
                lbDispo.setBackground(new java.awt.Color(61, 115, 213));
            }
     
            panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            panel.setName(String.valueOf(x.getNum_habitacion()));
            panel.setLayout(new java.awt.GridBagLayout());

            lbNoHab.setFont(new java.awt.Font("Calibri", 1, 16));
            lbNoHab.setForeground(new java.awt.Color(255, 255, 255));
            lbNoHab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            lbNoHab.setText("N° DE HABITACIÓN: " + x.getNum_habitacion());
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
            lbDispo.setText(x.getDispo_habitacion());
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
            lbTipo.setText("TIPO: " + x.getTipoH().getNombre_tipo());
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
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
        if(principalOn.equals("mRecepcion")){
            recepcionSelected = new Habitacion();
            recepcionSelected.setNum_habitacion(Integer.parseInt(me.getComponent().getName()));
            
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
