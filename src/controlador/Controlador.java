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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.dao.ClaveAccesoDao;
import modelos.dao.HabitacionDao;
import modelos.dao.HotelDao;
import modelos.dao.UsuarioDao;
import modelos.entidades.ClaveAcceso;
import modelos.entidades.Habitacion;
import modelos.entidades.Hotel;
import modelos.entidades.Usuario;
import utilidades.ArbolBB;
import utilidades.CambiaPanel;
import utilidades.Encriptacion;
import utilidades.ImgTabla;
import utilidades.ListaSimple;
import utilidades.TextPrompt;
import vistas.main.Login;
import vistas.main.Menu;
import vistas.modulos.ConfirmDialog;
import vistas.modulos.ModalConfig;
import vistas.modulos.ModalEditConfig;
import vistas.modulos.ModalUsuario;
import vistas.modulos.VistaRecepcion;
import vistas.modulos.VistaRegistro;
import vistas.modulos.VistaUsuario;

/**
 *
 * @author Adonay
 */
public class Controlador implements ActionListener, MouseListener, KeyListener{
    private DefaultTableModel modelo;
    private Menu menu;
    private Login login;
    private ConfirmDialog confirmDialog;
    private String principalOn = "";
    private String modalOn = "";
    
    /* CONTROL DE USUARIOS */
    private Usuario usuario = new Usuario();
    private Usuario usuarioSelected = null;
    private UsuarioDao daoUsuario = new UsuarioDao();
    private ClaveAcceso claveAcceso = new ClaveAcceso();
    private ClaveAccesoDao daoClave =  new ClaveAccesoDao();
    private VistaUsuario usuarioVista;
    private ModalUsuario usuarioModal;
    private ArbolBB arbolBusqueda = new ArbolBB();
    
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
    
    public Controlador(Login login) {
        try {
            this.login = login;
            mostrarModulos("Login");
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mostrarModulos(String mod) throws SQLException{
        if (mod.equals("Login")) {
            login = new Login();
            login.setControlador(this);
            login.iniciar();
            principalOn = "Login";
        }else if(mod.equals("mConfig")){
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
        }else if(mod.equals("mUsuarios")) {
            usuarioVista = new VistaUsuario();
            usuarioVista.setControlador(this);
            principalOn = "mUsuarios";
            new CambiaPanel(menu.body, usuarioVista);
            mostrarDatos(usuarioVista.tbUsuarios);
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
        }
        
        /* CONTROL DE USUARIOS */
        if (modals.equals("claveAcceso")) {
            usuarioModal = new ModalUsuario(new JFrame(), true);
            usuarioModal.setControlador(this);
            modalOn = "usuarioModal";

            usuarioModal.jPanel1.remove(usuarioModal.btnBaja);
            usuarioModal.form.remove(usuarioModal.iconName);
            usuarioModal.form.remove(usuarioModal.jtNom);
            usuarioModal.form.remove(usuarioModal.jtApe);
            usuarioModal.form.remove(usuarioModal.iconBirthday);
            usuarioModal.form.remove(usuarioModal.jDate);
            usuarioModal.form.remove(usuarioModal.iconTel);
            usuarioModal.form.remove(usuarioModal.jtTel);
            usuarioModal.form.remove(usuarioModal.cbGenero);
            usuarioModal.form.remove(usuarioModal.iconRol);
            usuarioModal.form.remove(usuarioModal.cbRol);
            usuarioModal.form.remove(usuarioModal.iconUser);
            usuarioModal.form.remove(usuarioModal.jtUser);
            usuarioModal.form.remove(usuarioModal.modiPassCheck);

            usuarioModal.header.setText("Confirmar Acceso");
            usuarioModal.btnGuardar.setText("Confirmar");
            
            usuarioModal.setSize(434, 234);
            usuarioModal.iniciar();
        }else if (modals.equals("nuevoUsuario")) {
            usuarioModal = new ModalUsuario(new JFrame(), true);
            usuarioSelected = null;
           
            usuarioModal.setControlador(this);
            
            if (!claveAcceso.getClave().isEmpty()) {
                usuarioModal.form.remove(usuarioModal.iconRol);
                usuarioModal.form.remove(usuarioModal.cbRol);
                usuarioModal.cbRol.setSelectedItem("Administrador");
                usuarioModal.setSize(555, 445);
            }else {
                usuarioModal.setSize(555, 495);
            }
            
            usuarioModal.jPanel1.remove(usuarioModal.btnBaja);
            usuarioModal.form.remove(usuarioModal.modiPassCheck);
            modalOn = "usuarioModal";
            usuarioModal.iniciar();
        }else if(modals.equals("editarUsuario")){
            try {
                usuarioModal = new ModalUsuario(new JFrame(), true);
                usuarioModal.setControlador(this);
                modalOn = "usuarioModal";
                
                if (usuario.getRol().equals("Administrador")) {
                    usuarioModal.jPanel1.remove(usuarioModal.btnBaja);
                }
                
                usuarioModal.header.setText("Editar Usuario");
                
                usuarioModal.iconPass.setVisible(false);
                usuarioModal.jtPass.setVisible(false);
                usuarioModal.jtPassRepet.setVisible(false);
                
                usuarioModal.jtNom.setText(usuarioSelected.getNombre());
                usuarioModal.jtApe.setText(usuarioSelected.getApellido());
                
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(usuarioSelected.getfNacimiento());
                String formatDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
                Date parseDate = new SimpleDateFormat("dd/MM/yyyy").parse(formatDate);
                
                usuarioModal.jDate.setDate(parseDate);
                usuarioModal.jtTel.setText(usuarioSelected.getTelefono());
                usuarioModal.cbGenero.setSelectedItem(usuarioSelected.getGenero());
                
                usuarioModal.jtUser.setText(usuarioSelected.getNick());
                
                usuarioModal.cbRol.setSelectedItem(usuarioSelected.getRol());
                
                if (usuario.getRol().equals("Administrador") && !usuarioSelected.getNick().equals(usuario.getNick())) {
                    usuarioModal.cbRol.setEnabled(true);
                }else if (usuario.getRol().equals("Administrador") || usuarioSelected.getNick().equals(usuario.getNick())){
                    usuarioModal.cbRol.setEnabled(false);
                }
                
                new TextPrompt("Nueva contraseña", usuarioModal.jtPass);
                new TextPrompt("Repita la nueva contraseña", usuarioModal.jtPass);
                
                usuarioModal.setSize(555, 470); //Width - Height
                usuarioModal.iniciar();
            } catch (ParseException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(modals.equals("eliminarUsuario")){
            confirmDialog = new ConfirmDialog(new JFrame(), true);
            confirmDialog.setControlador(this);
            modalOn = "modalDialog";
            confirmDialog.header.setText("Eliminar usuario");
           
            
            confirmDialog.textDialog.setText("<html>¿Estás seguro que quieres eliminar el usuario <b>" + usuarioSelected.getNick() + "</b>?<br><b>Al eliminar un usuario se eliminara toda su información y registros.</b></html>");
            confirmDialog.setSize(610, 260);
            confirmDialog.btnEliminar.setText("Eliminar");
             
            confirmDialog.iniciar();
        }else if (modals.equals("confirmarAcceso")) {
            usuarioModal = new ModalUsuario(new JFrame(), true);
            usuarioModal.setControlador(this);
            modalOn = "usuarioModal";

            usuarioModal.jPanel1.remove(usuarioModal.btnBaja);
            usuarioModal.form.remove(usuarioModal.iconName);
            usuarioModal.form.remove(usuarioModal.jtNom);
            usuarioModal.form.remove(usuarioModal.jtApe);
            usuarioModal.form.remove(usuarioModal.iconBirthday);
            usuarioModal.form.remove(usuarioModal.jDate);
            usuarioModal.form.remove(usuarioModal.iconTel);
            usuarioModal.form.remove(usuarioModal.jtTel);
            usuarioModal.form.remove(usuarioModal.cbGenero);
            usuarioModal.form.remove(usuarioModal.iconRol);
            usuarioModal.form.remove(usuarioModal.cbRol);
            usuarioModal.form.remove(usuarioModal.modiPassCheck);

            usuarioModal.header.setText("Confirmar Acceso");
            usuarioModal.btnGuardar.setText("Confirmar");
            
            usuarioModal.setSize(485, 284);
            usuarioModal.iniciar();
        }
    }
    
    public void mostrarDatos(JTable tabla){
        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel)tabla.getModel();
        modelo.setRowCount(0);

        /* CONTROL DE USUARIOS */
        if(principalOn.equals("mUsuarios")){
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img
            
            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(4).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(6).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(7).setCellRenderer(diseño);

            ListaSimple<Usuario> usuarios = daoUsuario.selectAll();
            int i = 1;
            
            for(Usuario x : usuarios.toArray()){

                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));
                
                if(x.getEstado() > 0){
                   
                    modelo.addRow(new Object[]{i, x.getNombre(), x.getEdad() + " años", x.getGenero(), x.getTelefono(), x.getNick(), x.getClave(), x.getRol(), lbImg_edit, lbImg_delete});
                    i++;
                }
               
            }
            
            if(modelo.getRowCount() < 1){
                modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
            }
            
            tabla.setModel(modelo);
        }
    }
    
    public void verificarCredenciales(ActionEvent btn) {
        if (principalOn.equals("Login")) {
            if (!login.tfUser.getText().isEmpty() && !login.tfPass.getText().isEmpty()) {
                /* APLICAR ARBOLES DE BUSQUEDA */
                ListaSimple<Usuario> usuarios = daoUsuario.buscar(login.tfUser.getText());
                
                if (!usuarios.isEmpty() && usuarios.toArray().get(0).getEstado() == 1) {
                    //String nick = login.tfUser.getText();
                    String clave = Encriptacion.getStringMessageDigest(login.tfPass.getText(), Encriptacion.SHA256);
                    
                    if (clave.equals(usuarios.toArray().get(0).getClave())) {
                        /* APLICAR ARBOLES DE BUSQUEDA */
                        this.usuario = usuarios.toArray().get(0);
                        this.menu = new Menu();
                        this.menu.setControlador(this);
                        
                        if(usuario.getRol().equals("Administrador")){
                            String n[] = usuario.getNombre().split(" ");
                            String a[] = usuario.getApellido().split(" ");
                            
                            menu.lbUserName.setText(n[0] + " " + a[0]);
                            menu.header.remove(menu.btnModiUser);
                            //principalOn = "Menu";
                        }else{
                            String n[] = usuario.getNombre().split(" ");
                            String a[] = usuario.getApellido().split(" ");
                            
                            menu.lbUserName.setText(n[0] + " " + a[0]);
                                         
                            //Eliminar Modulos
                            menu.modulos.remove(menu.btnUsuario);
                            menu.modulos.remove(menu.btnProducto);

//                            principalOn = "Menu";
                        }
                        
                        menu.iniciar();
                        
                        DesktopNotify.setDefaultTheme(NotifyTheme.LightBlue);
                        DesktopNotify.showDesktopMessage("¡Bienvenido/a " + usuario.getNick() + "!", "Espero disfrutes del sistema, ten un buen día.", DesktopNotify.INFORMATION, 10000);
                        
                        login.dispose();
                        principalOn = "";
                    }else{
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Contraseña incorrecta", "Asegúrese que la contraseña sea correcta.", DesktopNotify.WARNING, 8000);
                    }
                }else{
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Usuario incorrecto", "Asegúrese de que el usuario digitado sea correcto.", DesktopNotify.WARNING, 8000);
                }
            }else{
                //Campos vacios
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
            }
        }
    }
    
    public void accionesDeBotones(ActionEvent btn) throws SQLException{
        if(btn.getActionCommand().equals("GuardarInfo") && principalOn == "mConfig"){
            if(!configModalEdit.tfNom.getText().isEmpty() && !configModalEdit.tfDir.getText().isEmpty() && !configModalEdit.tfTel.getText().isEmpty()){
                if(daoHotel.update(new Hotel(1, configModalEdit.tfNom.getText(), configModalEdit.tfDir.getText(), configModalEdit.tfTel.getText()))){
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Información de Hotel modificada", "La información del Hotel se modificó correctamente.", DesktopNotify.SUCCESS, 8000);
                    configModalEdit.dispose();
                    modalOn = "";
                    mostrarModulos("mConfig");
                }
            }
        }
        
        if(btn.getActionCommand().equals("checkbox") && modalOn.equals("usuarioModal")) {
            if (usuarioModal.modiPassCheck.isSelected()) {
                usuarioModal.setSize(555, 530);
                usuarioModal.iconPass.setVisible(true);
                usuarioModal.jtPass.setVisible(true);
                usuarioModal.jtPassRepet.setVisible(true);
            }else {
                usuarioModal.iconPass.setVisible(false);
                usuarioModal.jtPass.setVisible(false);
                usuarioModal.jtPassRepet.setVisible(false);
                usuarioModal.setSize(555, 470);
            }
            
        }
    }
    
    public void eventosBotones(String btn){
        if(modalOn.equals("usuarioModal") || modalOn.equals("modalDialog")){
            if(btn.equals("Agregar")){
                if(!usuarioModal.jtNom.getText().isEmpty() && !usuarioModal.jtApe.getText().isEmpty()
                        && usuarioModal.jDate.getDate() != null && !usuarioModal.jtTel.getText().isEmpty()
                        && usuarioModal.cbGenero.getSelectedIndex() > 0 && !usuarioModal.jtUser.getText().isEmpty()
                        && usuarioModal.cbRol.getSelectedIndex() > 0){
                    
                    if(usuarioSelected == null){
                        if (!usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()) {
                            if(usuarioModal.jtPass.getText().equals(usuarioModal.jtPassRepet.getText())){
                                String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave

                                ListaSimple<Usuario> existeUser = daoUsuario.buscar(usuarioModal.jtUser.getText());

                                if(existeUser.isEmpty()){
                                    String nom = usuarioModal.jtNom.getText();
                                    String ape = usuarioModal.jtApe.getText();
                                    String tel = usuarioModal.jtTel.getText();

                                    Date fecha = usuarioModal.jDate.getDate();
                                    DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                                    String fechaNac = f.format(fecha);

                                    String genero = usuarioModal.cbGenero.getSelectedItem().toString();
                                    String nick = usuarioModal.jtUser.getText();
                                    String rol = usuarioModal.cbRol.getSelectedItem().toString();

                                    Usuario usuario = new Usuario(nom, ape, tel, fechaNac, genero, nick, rol, clave, 1);

                                    if(daoUsuario.insert(usuario)){
                                        //Mensaje de guardado
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                        DesktopNotify.showDesktopMessage("Usuario guardado", "El usuario ha sido alamcenado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                    }
                                    
                                    modalOn = "";
                                    usuarioModal.dispose();

                                }else{
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Usuario " + usuarioModal.jtUser.getText() +  " ya existe", "El nuevo nombre de usuario debe ser diferente a los demás.", DesktopNotify.WARNING, 10000);                             
                                }
                            }else{
                                //Contraseñas diferentes
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                            }
                        }
                    }else {
                        if(usuarioSelected != null){
                            //Modificar
                            ListaSimple<Usuario> existeUser = daoUsuario.selectAllTo("nick_usuario", usuarioModal.jtUser.getText());
                            String claveAux = usuarioModal.jtPass.getText();
                            Date fecha = usuarioModal.jDate.getDate();

                            DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                            String fechaNac = f.format(fecha);
                            
                            if (!usuarioModal.jtNom.getText().equals(usuarioSelected.getNombre()) || !usuarioModal.jtApe.getText().equals(usuarioSelected.getApellido())
                                    || !fechaNac.equals(usuarioSelected.getfNacimiento()) || !usuarioModal.jtTel.getText().equals(usuarioSelected.getTelefono())
                                    || !usuarioModal.cbGenero.getSelectedItem().toString().equals(usuarioSelected.getGenero()) || !usuarioModal.jtUser.getText().equals(usuarioSelected.getNick())
                                    || !usuarioModal.cbRol.getSelectedItem().toString().equals(usuarioSelected.getRol())) {
                                if(existeUser.isEmpty()){
                                    usuarioSelected.setNombre(usuarioModal.jtNom.getText());
                                    usuarioSelected.setApellido(usuarioModal.jtApe.getText());

                                    usuarioSelected.setfNacimiento(fechaNac);
                                    usuarioSelected.setTelefono(usuarioModal.jtTel.getText());
                                    usuarioSelected.setGenero(usuarioModal.cbGenero.getSelectedItem().toString());
                                    usuarioSelected.setNick(usuarioModal.jtUser.getText());
                                    usuarioSelected.setRol(usuarioModal.cbRol.getSelectedItem().toString());
                                    
                                    if(daoUsuario.update(usuarioSelected)){ //Guardado
                                        //Mensaje de modificado
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                        DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                        usuarioSelected = null;
                                        usuarioModal.dispose();
                                    }else{ //Ocurrio un error
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                        DesktopNotify.showDesktopMessage("Error", "Usuario no actualizado", DesktopNotify.FAIL, 8000);
                                    }
                                }else {
                                    usuarioSelected.setNombre(usuarioModal.jtNom.getText());
                                    usuarioSelected.setApellido(usuarioModal.jtApe.getText());

                                    usuarioSelected.setfNacimiento(fechaNac);
                                    usuarioSelected.setTelefono(usuarioModal.jtTel.getText());
                                    usuarioSelected.setGenero(usuarioModal.cbGenero.getSelectedItem().toString());
                                    usuarioSelected.setRol(usuarioModal.cbRol.getSelectedItem().toString());

                                    if(existeUser.toArray().get(0).getNick().equals(usuarioSelected.getNick())){ 
                                        if(daoUsuario.update(usuarioSelected)){
                                            DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                            DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                            usuarioSelected = null;
                                            usuarioModal.dispose();
                                        }else{ //Ocurrio un error
                                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                            DesktopNotify.showDesktopMessage("Error", "Usuario no actualizado", DesktopNotify.FAIL, 8000);
                                        }

                                    }else{ //Usuario ya existe
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                        DesktopNotify.showDesktopMessage("Usuario " + usuarioModal.jtUser.getText() +  " ya existe", "El nuevo nombre de usuario debe ser diferente a los demás.", DesktopNotify.WARNING, 10000);
                                    }   
                                }
                            }else if (usuarioModal.modiPassCheck.isSelected()) {
                                if (!usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()){
                                    usuarioSelected.setNombre(usuarioModal.jtNom.getText());
                                    usuarioSelected.setApellido(usuarioModal.jtApe.getText());

                                    usuarioSelected.setfNacimiento(fechaNac);
                                    usuarioSelected.setTelefono(usuarioModal.jtTel.getText());
                                    usuarioSelected.setGenero(usuarioModal.cbGenero.getSelectedItem().toString());
                                    usuarioSelected.setNick(usuarioModal.jtUser.getText());
                                    usuarioSelected.setRol(usuarioModal.cbRol.getSelectedItem().toString());
                                    
                                        if(usuarioModal.jtPass.getText().equals(usuarioModal.jtPassRepet.getText())){
                                            String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave

                                            usuarioSelected.setClave(clave);

                                            if(daoUsuario.update(usuarioSelected)){ //Guardado
                                                //Mensaje de modificado
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                                DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                                usuarioSelected = null;
                                                usuarioModal.dispose();
                                            }else{ //Ocurrio un error
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                                DesktopNotify.showDesktopMessage("Error", "Usuario no actualizado", DesktopNotify.FAIL, 8000);
                                            }
                                        }else{
                                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                            DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                                        }
                                }else {
                                    //Campos incompletos
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                                }
                            }else {
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Usuario no modificado", "No se ha modificado ningún campo.", DesktopNotify.FAIL, 8000);
                            }
                        }else{
                            //Campos incompletos
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                        }
                    }
                }else if (!usuarioModal.jtUser.getText().isEmpty() && !usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()) {
                    
                    /* CONFIRMAR ACCESO - ELIMINAR */
                    
                    if (usuarioModal.jtUser.getText().equals(usuario.getNick())) {
                        String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText(), Encriptacion.SHA256);

                        if (clave.equals(usuario.getClave())) {
                                usuarioSelected.setEstado(0);
                                
//                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
//                                DesktopNotify.showDesktopMessage("Hola hola", "Hola hola.", DesktopNotify.INFORMATION, 8000);
                                
                                if(daoUsuario.update(usuarioSelected)){
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Usuario eliminado", "El usuario ha sido eliminado exitosamente.", DesktopNotify.INFORMATION, 8000);
                                    mostrarDatos(usuarioVista.tbUsuarios);
                                    usuarioModal.dispose();
                                }

                                if (usuarioSelected.getNick().equals(usuario.getNick())) {
                                    usuarioSelected = null;
                                    usuario = null; 
                                    menu.dispose(); 
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                    DesktopNotify.showDesktopMessage("Sesión cerrada", "Se ha cerrado sesión con exito.", DesktopNotify.SUCCESS, 8000); //8 seg
                                    try {
                                        mostrarModulos("Login");
                                    } catch (SQLException ex) {
                                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                
                                modalOn = "";
                                usuarioModal.dispose();
                        }else{
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Contraseña incorrecta", "Asegúrese que la contraseña sea correcta.", DesktopNotify.WARNING, 8000);
                        }
                    }else{
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Usuario incorrecto", "Asegúrese de que el usuario digitado sea correcto.", DesktopNotify.WARNING, 8000);
                    }
                }else if (!usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()) {
                    try {
                        /* CONFIRMAR ACCESO - NUEVO USUARIO */
                        
                        claveAcceso = daoClave.selectAll().toArray().get(0);
                        
                        if(usuarioModal.jtPass.getText().equals(usuarioModal.jtPassRepet.getText())){
                            String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText(), Encriptacion.SHA256);
                            
                            if (clave.equals(claveAcceso.getClave())) {
                                usuarioModal.dispose();
                                
                                mostrarModals("nuevoUsuario");
                            }else{
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Contraseña incorrecta", "Asegúrese que la contraseña sea correcta.", DesktopNotify.WARNING, 8000);
                            }
                        }else {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    //Campos vacios
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                }
            
                try {
                    mostrarDatos(usuarioVista.tbUsuarios);
                } catch(Exception e) {
                    
                }
            }
        
            if (btn.equals("Eliminar")) {
                try {
                    confirmDialog.dispose();
                    mostrarModals("confirmarAcceso");
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }    
    }
    
    public void mostrarInfoHotel() throws SQLException{
//        Hotel hotelInfo = daoHotel.selectAll().toArrayDesc().get(0);
//        
//        if(principalOn.equals("mConfig")){
//            if(modalOn.equals("modalConfig")){
//                configModalEdit.tfNom.setText(hotelInfo.getNom_hotel());
//                configModalEdit.tfDir.setText(hotelInfo.getDir_hotel());
//                configModalEdit.tfTel.setText(hotelInfo.getTel_hotel());
//            }else{
//                configModal.lbNomHotel.setText(hotelInfo.getNom_hotel());
//                configModal.lbDirHotel.setText(hotelInfo.getDir_hotel());
//                configModal.lbTelHotel.setText(hotelInfo.getTel_hotel());
//            }
//        }        
    }
    
    public void mostrarInfoHab() throws SQLException{
//        Habitacion habi = daoHabitacion.selectId(recepcionSelected.getNum_habitacion()).toArrayAsc().get(0);
//        registroVista.lbDescrip.setText(habi.getDescr_habitacion());
//        registroVista.lbNumHab.setText(String.valueOf(habi.getNum_habitacion()));
//        
//        registroVista.lbEstado.setForeground(Color.white);
//        
//        if(habi.getDispo_habitacion().equals("DISPONIBLE")){
//            registroVista.lbEstado.setBackground(new Color(0, 166, 90));
//        }else if(habi.getDispo_habitacion().equals("OCUPADA")){
//            registroVista.lbEstado.setBackground(new Color(223, 56, 56));
//        }else{
//            registroVista.lbEstado.setBackground(new Color(61,137,248));
//        }
//        
//        registroVista.lbEstado.setText(habi.getDispo_habitacion());
//        registroVista.lbTipoHab.setText(habi.getTipoH().getNombre_tipo());
//        registroVista.lbPrecio.setText("$" + String.valueOf(habi.getPrecio_habitacion()));
        
    }
    
    public void generarHabitaciones() throws SQLException{
//
//        ListaCircularDoble<Habitacion> listaHab = this.daoHabitacion.selectAll();
//        
//        for(Habitacion x : listaHab.toArrayAsc()){
//            
//            GridBagConstraints gridBagConstraints;
//            JPanel panel = new javax.swing.JPanel();
//            JLabel lbNoHab = new JLabel();
//            JLabel lbDispo = new JLabel();
//            JLabel lbTipo = new JLabel();
//            JLabel lbIcono = new JLabel();
//            JScrollPane scroll = new JScrollPane();
//
//            if(x.getDispo_habitacion().equals("DISPONIBLE")){
//                panel.setBackground(new java.awt.Color(0, 166, 90));
//                lbDispo.setBackground(new java.awt.Color(0, 147, 93));
//            }else if(x.getDispo_habitacion().equals("OCUPADA")){
//                panel.setBackground(new java.awt.Color(223, 56, 56));
//                lbDispo.setBackground(new java.awt.Color(187, 56, 56));
//            }else{
//                panel.setBackground(new java.awt.Color(61,137,248));
//                lbDispo.setBackground(new java.awt.Color(61, 115, 213));
//            }
//     
//            panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
//            panel.setName(String.valueOf(x.getNum_habitacion()));
//            panel.setLayout(new java.awt.GridBagLayout());
//
//            lbNoHab.setFont(new java.awt.Font("Calibri", 1, 16));
//            lbNoHab.setForeground(new java.awt.Color(255, 255, 255));
//            lbNoHab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
//            lbNoHab.setText("N° DE HABITACIÓN: " + x.getNum_habitacion());
//            gridBagConstraints = new java.awt.GridBagConstraints();
//            gridBagConstraints.gridx = 1;
//            gridBagConstraints.gridy = 0;
//            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
//            gridBagConstraints.ipadx = 20;
//            gridBagConstraints.ipady = 25;
//            panel.add(lbNoHab, gridBagConstraints);
//
//            lbDispo.setFont(new java.awt.Font("Calibri", 1, 16));
//            lbDispo.setForeground(new java.awt.Color(255, 255, 255));
//            lbDispo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//            lbDispo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/arrow.png")));
//            lbDispo.setText(x.getDispo_habitacion());
//            lbDispo.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
//            lbDispo.setOpaque(true);
//            gridBagConstraints = new java.awt.GridBagConstraints();
//            gridBagConstraints.gridx = 0;
//            gridBagConstraints.gridy = 2;
//            gridBagConstraints.gridwidth = 2;
//            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
//            gridBagConstraints.ipady = 5;
//            gridBagConstraints.weightx = 1.0;
//            gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
//            panel.add(lbDispo, gridBagConstraints);
//
//            lbTipo.setFont(new java.awt.Font("Calibri", 1, 14));
//            lbTipo.setForeground(new java.awt.Color(255, 255, 255));
//            lbTipo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
//            lbTipo.setText("TIPO: " + x.getTipoH().getNombre_tipo());
//            gridBagConstraints = new java.awt.GridBagConstraints();
//            gridBagConstraints.gridx = 1;
//            gridBagConstraints.gridy = 1;
//            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
//            panel.add(lbTipo, gridBagConstraints);
//
//            lbIcono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//            lbIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bed.png")));
//            gridBagConstraints = new java.awt.GridBagConstraints();
//            gridBagConstraints.gridx = 0;
//            gridBagConstraints.gridy = 0;
//            gridBagConstraints.gridheight = 3;
//            gridBagConstraints.ipadx = 15;
//            panel.add(lbIcono, gridBagConstraints);
//            panel.addMouseListener(this);
//            
//            scroll.setViewportView(panel);
//            scroll.setBorder((javax.swing.border.Border) Border.NO_BORDER);
//            recepVista.habPanel.add(scroll);
//        }

    }

    @Override
    public void actionPerformed(ActionEvent btn) {
        if (!principalOn.equals("Login")) {
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
            
            if(btn.getActionCommand().equals("Usuarios")) {
                try {
                    mostrarModulos("mUsuarios");
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
            
            if(btn.getActionCommand().equals("modiUsuario")) {
                try {
                    usuarioSelected = usuario;
                    mostrarModals("editarUsuario");
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(btn.getActionCommand().equals("checkbox")){
                try {
                    accionesDeBotones(btn);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(btn.getActionCommand().equals("Salir")){
                try {
                    usuarioSelected = null;
                    usuario = null; 
                    menu.dispose(); 
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Sesión cerrada", "Se ha cerrado sesión con exito.", DesktopNotify.SUCCESS, 8000); //8 seg
                    mostrarModulos("Login");
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else {
            if(btn.getActionCommand().equals("Ingresar")) {
                verificarCredenciales(btn);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if(principalOn.equals("mUsuarios") && me.getSource() == usuarioVista.tbUsuarios){

            int columna = usuarioVista.tbUsuarios.getSelectedColumn();
            
            try{
                if(columna == 8){
                    int fila = usuarioVista.tbUsuarios.getSelectedRow();
                    String nick = usuarioVista.tbUsuarios.getValueAt(fila, 5).toString();
                    ListaSimple<Usuario> lista = daoUsuario.buscar(nick);
                    usuarioSelected = lista.toArray().get(0);
                    claveAcceso.setClave("");
                    mostrarModals("editarUsuario");
                }else if(columna == 9){
                    int fila = usuarioVista.tbUsuarios.getSelectedRow();
                    String nick = usuarioVista.tbUsuarios.getValueAt(fila, 5).toString();
                    ListaSimple<Usuario> lista = daoUsuario.buscar(nick);
                    usuarioSelected = lista.toArray().get(0);
                    mostrarModals("eliminarUsuario");
                }
            }catch(Exception ex){
                
            }
       
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        
        if(principalOn.equals("mRecepcion")){
            recepcionSelected = new Habitacion();
            //recepcionSelected.setNum_habitacion(Integer.parseInt(me.getComponent().getName()));
            try {
                mostrarModulos("mRegistro");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        try {
            if (me.getSource().equals(login.lbNuevaCuenta)) {
                mostrarModals("claveAcceso");
            }
        }catch (Exception e) {
            
        }
        
        try {
            if(me.getSource().equals(usuarioVista.btnNuevo)) {
                try {
                    claveAcceso.setClave("");
                    mostrarModals("nuevoUsuario");
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch(Exception e) {
            
        }
        
        try {
            if (me.getSource().equals(usuarioModal.btnGuardar)) {
                eventosBotones("Agregar");
            }
        }catch (Exception e) {
            
        }
        
        try {
            if (me.getSource().equals(usuarioModal.btnBaja)) {
                try {
                    usuarioModal.dispose();
                    mostrarModals("eliminarUsuario");
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }catch (Exception e) {
            
        }
        
        try {
            if(me.getSource().equals(confirmDialog.btnEliminar)){
                eventosBotones("Eliminar");
            }
        }catch (Exception e) {
            
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
