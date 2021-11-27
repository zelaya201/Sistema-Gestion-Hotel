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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import modelos.dao.ClaveAccesoDao;
import modelos.dao.ProductoDao;
import modelos.dao.RegistroDao;
import modelos.dao.RegistroProductoDao;
import modelos.dao.UsuarioDao;
import modelos.entidades.ClaveAcceso;
import modelos.entidades.Producto;
import modelos.entidades.Registro;
import modelos.entidades.RegistroProducto;
import modelos.entidades.Usuario;
import utilidades.ArbolBB;
import utilidades.Encriptacion;
import utilidades.JFreeCharts;
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
import utilidades.ExportPDF;
import utilidades.ImgTabla;
import utilidades.ListaSimple;
import utilidades.TextPrompt;
import vistas.main.Login;
import vistas.main.Menu;
import vistas.modulos.ConfirmDialog;
import vistas.modulos.ConfirmDialogTipo;
import vistas.modulos.Dashboard;
import vistas.modulos.ModalConfig;
import vistas.modulos.ModalEditConfig;
import vistas.modulos.ModalUsuario;
import vistas.modulos.VistaHabitacion;
import vistas.modulos.VistaRecepcion;
import vistas.modulos.VistaRegistro;
import vistas.modulos.VistaTipo;
import vistas.modulos.VistaUsuario;
import vistas.modulos.modalHabitacion;


/**
 *
 * @author Adonay
 */
public class Controlador implements ActionListener, MouseListener, KeyListener {

    private DefaultTableModel modelo;
    private Menu menu;
    private Login login;
    private ConfirmDialog confirmDialog;
    private String principalOn = "";
    private String modalOn = "";
    private String modalConfig = "";

    /* CONTROL DE USUARIOS */
    private Usuario usuario = new Usuario();
    private Usuario usuarioSelected = null;
    private UsuarioDao daoUsuario = new UsuarioDao();
    private ClaveAcceso claveAcceso = new ClaveAcceso();
    private ClaveAccesoDao daoClave = new ClaveAccesoDao();
    private VistaUsuario usuarioVista;
    private ModalUsuario usuarioModal;
    private ArbolBB arbolBusqueda = new ArbolBB();

    /* REGISTRO HABITACIÓN */
    private RegistroDao daoRegistro = new RegistroDao();

    /* REGISTRO PRODUCTO */
    private RegistroProductoDao daoRegistroProducto = new RegistroProductoDao();

    DefaultTableModel md;
    
    
    /* HABITACIÓN */
    private HabitacionDao daoHabitacion = new HabitacionDao();
    private TipoHabitacionDao tipoHabDao = new TipoHabitacionDao();
    private TipoHabitacion selectedTipo = null;
    private Habitacion habitacionSelected = null;
    
    private ConfirmDialogTipo modalEliminar;
    private modalHabitacion modalHab;
    
    
    /* REGISTRO HABITACIÓN */
    private VistaTipo tipoVista;
    private VistaHabitacion habitacionVista;
    private VistaRegistro registroVista;

    /* RECEPCIÓN */
    private VistaRecepcion recepVista;
    private Habitacion recepcionSelected = null;

    /* PRODUCTO */
    private ProductoDao daoProducto = new ProductoDao();

    /* CLIENTE */
    private ClienteDao daoCliente;
    

    /* CONFIGURACIÓN */
    private ModalConfig configModal;
    private HotelDao daoHotel = new HotelDao();
    private ModalEditConfig configModalEdit;

    /* DASHBOARD */
    private Dashboard dashVista;
    private JFreeCharts barChart = new JFreeCharts();

    public Controlador(Menu menu) throws SQLException {
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

    public void mostrarModulos(String mod) throws SQLException {

        if (mod.equals("Login")) {
            login = new Login();
            login.setControlador(this);
            login.iniciar();
            principalOn = "Login";
        } else if (mod.equals("mConfig")) {
            configModal = new ModalConfig(new JFrame(), true);
            configModal.setControlador(this);
            modalConfig = "mConfig";
            mostrarInfoHotel();
            configModal.iniciar();
        } else if (mod.equals("mRecepcion")) {
            recepVista = new VistaRecepcion();
            recepVista.setControlador(this);
            generarHabitaciones();
            principalOn = "mRecepcion";
            new CambiaPanel(menu.body, recepVista);
        } else if (mod.equals("mRegistro")) {
            registroVista = new VistaRegistro();
            registroVista.setControlador(this);
            mostrarInfoHab();
            llenarComboRegistro();
            principalOn = "mRegistro";
            new CambiaPanel(menu.body, registroVista);
        } else if (mod.equals("mDashboard")) {
            dashVista = new Dashboard();
            cargarDashboard();
            principalOn = "mDashboard";
            mostrarDatos();
            new CambiaPanel(menu.body, dashVista);
        } else if (mod.equals("mUsuarios")) {
            usuarioVista = new VistaUsuario();
            usuarioVista.setControlador(this);
            principalOn = "mUsuarios";
            new CambiaPanel(menu.body, usuarioVista);
            mostrarDatos(usuarioVista.tbUsuarios);
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

    public void mostrarModals(String modals) throws SQLException {
        if(modals.equals("mConfig")){
            configModal = new ModalConfig(new JFrame(), true);
            configModal.setControlador(this);
            modalConfig = "mConfig";
            mostrarInfoHotel();
            configModal.iniciar();
            modalConfig = "";
        }else if(modals.equals("modalConfig")){
            configModalEdit = new ModalEditConfig(new JFrame(), true);
            configModalEdit.setControlador(this);
            modalConfig = "modalConfig";
            mostrarInfoHotel();
            configModalEdit.iniciar();
            modalConfig = "";
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
        } else if (modals.equals("nuevoUsuario")) {
            usuarioModal = new ModalUsuario(new JFrame(), true);
            usuarioSelected = null;

            usuarioModal.setControlador(this);

            if (!claveAcceso.getClave().isEmpty()) {
                usuarioModal.form.remove(usuarioModal.iconRol);
                usuarioModal.form.remove(usuarioModal.cbRol);
                usuarioModal.cbRol.setSelectedItem("Administrador");
                usuarioModal.setSize(555, 445);
            } else {
                usuarioModal.setSize(555, 495);
            }

            usuarioModal.jPanel1.remove(usuarioModal.btnBaja);
            usuarioModal.form.remove(usuarioModal.modiPassCheck);
            modalOn = "usuarioModal";
            usuarioModal.iniciar();
        } else if (modals.equals("editarUsuario")) {
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
                } else if (usuario.getRol().equals("Administrador") || usuarioSelected.getNick().equals(usuario.getNick())) {
                    usuarioModal.cbRol.setEnabled(false);
                }

                new TextPrompt("Nueva contraseña", usuarioModal.jtPass);
                new TextPrompt("Repita la nueva contraseña", usuarioModal.jtPass);

                usuarioModal.setSize(555, 470); //Width - Height
                usuarioModal.iniciar();
            } catch (ParseException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (modals.equals("eliminarUsuario")) {
            confirmDialog = new ConfirmDialog(new JFrame(), true);
            confirmDialog.setControlador(this);
            modalOn = "modalDialog";
            confirmDialog.header.setText("Eliminar usuario");

            confirmDialog.textDialog.setText("<html>¿Estás seguro que quieres eliminar el usuario <b>" + usuarioSelected.getNick() + "</b>?<br><b>Al eliminar un usuario se eliminara toda su información y registros.</b></html>");
            confirmDialog.setSize(610, 260);
            confirmDialog.btnEliminar.setText("Eliminar");

            confirmDialog.iniciar();
        } else if (modals.equals("confirmarAcceso")) {
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

    public void mostrarDatos(JTable tabla) {
        DefaultTableCellRenderer diseño = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0); //Obtener diseño de la tabla
        modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        /* CONTROL DE USUARIOS */
        if (principalOn.equals("mUsuarios")) {
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

            for (Usuario x : usuarios.toArray()) {

                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/editar.png"));
                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));

                ImageIcon img_delete = new ImageIcon(getClass().getResource("/img/delete.png"));
                JLabel lbImg_delete = new JLabel(new ImageIcon(img_delete.getImage()));

                if (x.getEstado() > 0) {

                    modelo.addRow(new Object[]{i, x.getNombre(), x.getEdad() + " años", x.getGenero(), x.getTelefono(), x.getNick(), x.getClave(), x.getRol(), lbImg_edit, lbImg_delete});
                    i++;
                }

            }

            if (modelo.getRowCount() < 1) {
                modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
            }

            tabla.setModel(modelo);
        }
    }

    public void verificarCredenciales(ActionEvent btn) throws SQLException {
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

                        if (usuario.getRol().equals("Administrador")) {
                            String n[] = usuario.getNombre().split(" ");
                            String a[] = usuario.getApellido().split(" ");

                            menu.lbUserName.setText(n[0] + " " + a[0]);
                            menu.header.remove(menu.btnModiUser);
                            //principalOn = "Menu";
                        } else {
                            String n[] = usuario.getNombre().split(" ");
                            String a[] = usuario.getApellido().split(" ");

                            menu.lbUserName.setText(n[0] + " " + a[0]);

                            //Eliminar Modulos
                            menu.modulos.remove(menu.btnUsuario);
                            menu.modulos.remove(menu.btnProducto);

//                            principalOn = "Menu";
                        }
                        
                        mostrarModulos("mDashboard");
                        menu.iniciar();

                        DesktopNotify.setDefaultTheme(NotifyTheme.LightBlue);
                        DesktopNotify.showDesktopMessage("¡Bienvenido/a " + usuario.getNick() + "!", "Espero disfrutes del sistema, ten un buen día.", DesktopNotify.INFORMATION, 10000);

                        login.dispose();
                        principalOn = "";
                    } else {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Contraseña incorrecta", "Asegúrese que la contraseña sea correcta.", DesktopNotify.WARNING, 8000);
                    }
                } else {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Usuario incorrecto", "Asegúrese de que el usuario digitado sea correcto.", DesktopNotify.WARNING, 8000);
                }
            } else {
                //Campos vacios
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
            }
        }
    }

    public void accionesDeBotones(ActionEvent btn) throws SQLException, IOException {
        if (btn.getActionCommand().equals("GuardarInfo") && modalConfig == "modalConfig") {
            if (!configModalEdit.tfNom.getText().isEmpty() && !configModalEdit.tfDir.getText().isEmpty() && !configModalEdit.tfTel.getText().isEmpty()) {
                if (daoHotel.update(new Hotel(1, configModalEdit.tfNom.getText(), configModalEdit.tfDir.getText(), configModalEdit.tfTel.getText()))) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Información de Hotel modificada", "La información del Hotel se modificó correctamente.", DesktopNotify.SUCCESS, 8000);
                    configModalEdit.dispose();
                    modalConfig = "";
                    mostrarModals("mConfig");
                }
            } else {
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos", DesktopNotify.WARNING, 8000);
            }
        }

        if (btn.getActionCommand().equals("ReporteHab")) {

            String path = "";

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int request = file.showSaveDialog(menu);

            if (request == JFileChooser.APPROVE_OPTION) {
                path = file.getSelectedFile().getPath();
                ListaSimple<Habitacion> habitaciones = daoHabitacion.selectAll();
                ListaSimple<Hotel> hotel = daoHotel.selectAll();

                ExportPDF exporPdf = new ExportPDF();
                exporPdf.setHotel(hotel.toArray().get(0));
                exporPdf.setListHabitaciones(habitaciones);
                exporPdf.setPath(path);
                exporPdf.crearListaHabitaciones();
                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                DesktopNotify.showDesktopMessage("Reporte generado", "Ruta: " + path, DesktopNotify.INFORMATION, 10000);
            }

        }

        if (btn.getActionCommand().equals("ReporteReg")) {

            String path = "";

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int request = file.showSaveDialog(menu);

            if (request == JFileChooser.APPROVE_OPTION) {
                path = file.getSelectedFile().getPath();
                ListaSimple<Registro> registro = daoRegistro.selectAllTo("fk_num_habitacion", "4");
                ListaSimple<Hotel> hotel = daoHotel.selectAll();

                ExportPDF exporPdf = new ExportPDF();
                exporPdf.setHotel(hotel.toArray().get(0));
                exporPdf.setListaRegistro(registro);
                exporPdf.setPath(path);
                exporPdf.crearDetalleHabitacion();
                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                DesktopNotify.showDesktopMessage("Reporte generado", "Ruta: " + path, DesktopNotify.INFORMATION, 10000);
            }

        }
        if (btn.getActionCommand().equals("ReportePro")) {

            String path = "";

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int request = file.showSaveDialog(menu);

            if (request == JFileChooser.APPROVE_OPTION) {
                path = file.getSelectedFile().getPath();
                ListaSimple<Producto> producto = daoProducto.selectAll();
                ListaSimple<Hotel> hotel = daoHotel.selectAll();

                ExportPDF exporPdf = new ExportPDF();
                exporPdf.setHotel(hotel.toArray().get(0));
                exporPdf.setListaProducto(producto);
                exporPdf.setPath(path);
                exporPdf.crearListaProducto();
                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                DesktopNotify.showDesktopMessage("Reporte generado", "Ruta: " + path, DesktopNotify.INFORMATION, 10000);
            }

        }
        if (btn.getActionCommand().equals("ReporteHabPro")) {

            String path = "";

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int request = file.showSaveDialog(menu);

            if (request == JFileChooser.APPROVE_OPTION) {
                path = file.getSelectedFile().getPath();
                ListaSimple<RegistroProducto> registroProducto = daoRegistroProducto.selectAllTo("fk_id_registro", "1");
                ListaSimple<Hotel> hotel = daoHotel.selectAll();

                ExportPDF exporPdf = new ExportPDF();
                exporPdf.setHotel(hotel.toArray().get(0));
                exporPdf.setListaRegistroProducto(registroProducto);
                exporPdf.setPath(path);
                exporPdf.crearDetalleProducto();
                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                DesktopNotify.showDesktopMessage("Reporte generado", "Ruta: " + path, DesktopNotify.INFORMATION, 10000);
            }

        }

        if (btn.getActionCommand().equals("checkbox") && modalOn.equals("usuarioModal")) {
            if (usuarioModal.modiPassCheck.isSelected()) {
                usuarioModal.setSize(555, 530);
                usuarioModal.iconPass.setVisible(true);
                usuarioModal.jtPass.setVisible(true);
                usuarioModal.jtPassRepet.setVisible(true);
            } else {
                usuarioModal.iconPass.setVisible(false);
                usuarioModal.jtPass.setVisible(false);
                usuarioModal.jtPassRepet.setVisible(false);
                usuarioModal.setSize(555, 470);
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

    public void eventosBotones(String btn) {
        if (modalOn.equals("usuarioModal") || modalOn.equals("modalDialog")) {
            if (btn.equals("Agregar")) {
                if (!usuarioModal.jtNom.getText().isEmpty() && !usuarioModal.jtApe.getText().isEmpty()
                        && usuarioModal.jDate.getDate() != null && !usuarioModal.jtTel.getText().isEmpty()
                        && usuarioModal.cbGenero.getSelectedIndex() > 0 && !usuarioModal.jtUser.getText().isEmpty()
                        && usuarioModal.cbRol.getSelectedIndex() > 0) {

                    if (usuarioSelected == null) {
                        if (!usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()) {
                            if (usuarioModal.jtPass.getText().equals(usuarioModal.jtPassRepet.getText())) {
                                String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave

                                ListaSimple<Usuario> existeUser = daoUsuario.buscar(usuarioModal.jtUser.getText());

                                if (existeUser.isEmpty()) {
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

                                    if (daoUsuario.insert(usuario)) {
                                        //Mensaje de guardado
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                        DesktopNotify.showDesktopMessage("Usuario guardado", "El usuario ha sido alamcenado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                    }

                                    modalOn = "";
                                    usuarioModal.dispose();

                                } else {
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Usuario " + usuarioModal.jtUser.getText() + " ya existe", "El nuevo nombre de usuario debe ser diferente a los demás.", DesktopNotify.WARNING, 10000);
                                }
                            } else {
                                //Contraseñas diferentes
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                            }
                        }
                    } else {
                        if (usuarioSelected != null) {
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
                                if (existeUser.isEmpty()) {
                                    usuarioSelected.setNombre(usuarioModal.jtNom.getText());
                                    usuarioSelected.setApellido(usuarioModal.jtApe.getText());

                                    usuarioSelected.setfNacimiento(fechaNac);
                                    usuarioSelected.setTelefono(usuarioModal.jtTel.getText());
                                    usuarioSelected.setGenero(usuarioModal.cbGenero.getSelectedItem().toString());
                                    usuarioSelected.setNick(usuarioModal.jtUser.getText());
                                    usuarioSelected.setRol(usuarioModal.cbRol.getSelectedItem().toString());

                                    if (daoUsuario.update(usuarioSelected)) { //Guardado
                                        //Mensaje de modificado
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                        DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                        usuarioSelected = null;
                                        usuarioModal.dispose();
                                    } else { //Ocurrio un error
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                        DesktopNotify.showDesktopMessage("Error", "Usuario no actualizado", DesktopNotify.FAIL, 8000);
                                    }
                                } else {
                                    usuarioSelected.setNombre(usuarioModal.jtNom.getText());
                                    usuarioSelected.setApellido(usuarioModal.jtApe.getText());

                                    usuarioSelected.setfNacimiento(fechaNac);
                                    usuarioSelected.setTelefono(usuarioModal.jtTel.getText());
                                    usuarioSelected.setGenero(usuarioModal.cbGenero.getSelectedItem().toString());
                                    usuarioSelected.setRol(usuarioModal.cbRol.getSelectedItem().toString());

                                    if (existeUser.toArray().get(0).getNick().equals(usuarioSelected.getNick())) {
                                        if (daoUsuario.update(usuarioSelected)) {
                                            DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                            DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                            usuarioSelected = null;
                                            usuarioModal.dispose();
                                        } else { //Ocurrio un error
                                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                            DesktopNotify.showDesktopMessage("Error", "Usuario no actualizado", DesktopNotify.FAIL, 8000);
                                        }

                                    } else { //Usuario ya existe
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                        DesktopNotify.showDesktopMessage("Usuario " + usuarioModal.jtUser.getText() + " ya existe", "El nuevo nombre de usuario debe ser diferente a los demás.", DesktopNotify.WARNING, 10000);
                                    }
                                }
                            } else if (usuarioModal.modiPassCheck.isSelected()) {
                                if (!usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()) {
                                    usuarioSelected.setNombre(usuarioModal.jtNom.getText());
                                    usuarioSelected.setApellido(usuarioModal.jtApe.getText());

                                    usuarioSelected.setfNacimiento(fechaNac);
                                    usuarioSelected.setTelefono(usuarioModal.jtTel.getText());
                                    usuarioSelected.setGenero(usuarioModal.cbGenero.getSelectedItem().toString());
                                    usuarioSelected.setNick(usuarioModal.jtUser.getText());
                                    usuarioSelected.setRol(usuarioModal.cbRol.getSelectedItem().toString());

                                    if (usuarioModal.jtPass.getText().equals(usuarioModal.jtPassRepet.getText())) {
                                        String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText(), Encriptacion.SHA256); //Encriptamos la clave

                                        usuarioSelected.setClave(clave);

                                        if (daoUsuario.update(usuarioSelected)) { //Guardado
                                            //Mensaje de modificado
                                            DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                            DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                            usuarioSelected = null;
                                            usuarioModal.dispose();
                                        } else { //Ocurrio un error
                                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                            DesktopNotify.showDesktopMessage("Error", "Usuario no actualizado", DesktopNotify.FAIL, 8000);
                                        }
                                    } else {
                                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                        DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                                    }
                                } else {
                                    //Campos incompletos
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                                }
                            } else {
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Usuario no modificado", "No se ha modificado ningún campo.", DesktopNotify.FAIL, 8000);
                            }
                        } else {
                            //Campos incompletos
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                        }
                    }
                } else if (!usuarioModal.jtUser.getText().isEmpty() && !usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()) {

                    /* CONFIRMAR ACCESO - ELIMINAR */
                    if (usuarioModal.jtUser.getText().equals(usuario.getNick())) {
                        String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText(), Encriptacion.SHA256);

                        if (clave.equals(usuario.getClave())) {
                            usuarioSelected.setEstado(0);

//                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
//                                DesktopNotify.showDesktopMessage("Hola hola", "Hola hola.", DesktopNotify.INFORMATION, 8000);
                            if (daoUsuario.update(usuarioSelected)) {
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
                        } else {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Contraseña incorrecta", "Asegúrese que la contraseña sea correcta.", DesktopNotify.WARNING, 8000);
                        }
                    } else {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Usuario incorrecto", "Asegúrese de que el usuario digitado sea correcto.", DesktopNotify.WARNING, 8000);
                    }
                } else if (!usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()) {
                    try {
                        /* CONFIRMAR ACCESO - NUEVO USUARIO */

                        claveAcceso = daoClave.selectAll().toArray().get(0);

                        if (usuarioModal.jtPass.getText().equals(usuarioModal.jtPassRepet.getText())) {
                            String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText(), Encriptacion.SHA256);

                            if (clave.equals(claveAcceso.getClave())) {
                                usuarioModal.dispose();

                                mostrarModals("nuevoUsuario");
                            } else {
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Contraseña incorrecta", "Asegúrese que la contraseña sea correcta.", DesktopNotify.WARNING, 8000);
                            }
                        } else {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Contraseñas diferentes", "Las contraseñas tienen que ser iguales.", DesktopNotify.WARNING, 8000);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    //Campos vacios
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                }

                try {
                    mostrarDatos(usuarioVista.tbUsuarios);
                } catch (Exception e) {

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
        Hotel hotelInfo = daoHotel.selectAll().toArray().get(0);     
        if(modalConfig.equals("modalConfig")){
            configModalEdit.tfNom.setText(hotelInfo.getNombre());
            configModalEdit.tfDir.setText(hotelInfo.getDireccion());
            configModalEdit.tfTel.setText(hotelInfo.getTelefono());
        }else if(modalConfig.equals("mConfig")){
            configModal.lbNomHotel.setText(hotelInfo.getNombre());
            configModal.lbDirHotel.setText(hotelInfo.getDireccion());
            configModal.lbTelHotel.setText(hotelInfo.getTelefono());
        }       

    }

    public void mostrarInfoHab() throws SQLException {
        Habitacion habi = daoHabitacion.selectId(recepcionSelected.getNumHabitacion()).toArray().get(0);
        registroVista.lbDescrip.setText(habi.getDescripcion());
        registroVista.lbNumHab.setText(String.valueOf(habi.getNumHabitacion()));
        registroVista.lbEstado.setForeground(Color.white);

        if (habi.getDisposicion().equals("DISPONIBLE")) {
            registroVista.lbEstado.setBackground(new Color(0, 166, 90));
        } else if (habi.getDisposicion().equals("OCUPADA")) {
            registroVista.lbEstado.setBackground(new Color(223, 56, 56));
        } else {
            registroVista.lbEstado.setBackground(new Color(61, 137, 248));
        }

        registroVista.lbEstado.setText(habi.getDisposicion());
        registroVista.lbTipoHab.setText(habi.getTipoHabitacion().getNombre());
        registroVista.lbPrecio.setText("$" + String.valueOf(habi.getPrecio()));
    }

    public void generarHabitaciones() throws SQLException {

        ListaSimple<Habitacion> listaHab = this.daoHabitacion.selectAll();

        for (Habitacion x : listaHab.toArray()) {

            GridBagConstraints gridBagConstraints;
            JPanel panel = new javax.swing.JPanel();
            JLabel lbNoHab = new JLabel();
            JLabel lbDispo = new JLabel();
            JLabel lbTipo = new JLabel();
            JLabel lbIcono = new JLabel();
            JScrollPane scroll = new JScrollPane();

            if (x.getDisposicion().equals("DISPONIBLE")) {
                panel.setBackground(new java.awt.Color(0, 166, 90));
                lbDispo.setBackground(new java.awt.Color(0, 147, 93));
            } else if (x.getDisposicion().equals("OCUPADA")) {
                panel.setBackground(new java.awt.Color(223, 56, 56));
                lbDispo.setBackground(new java.awt.Color(187, 56, 56));
            } else {
                panel.setBackground(new java.awt.Color(61, 137, 248));
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

    public void cargarDashboard() throws SQLException {

        ListaSimple<Habitacion> habitacion = daoHabitacion.selectAll();
        ListaSimple<Producto> producto = daoProducto.selectAll();
        ListaSimple<Usuario> usuario = daoUsuario.selectAll();
        ListaSimple<Registro> registro = daoRegistro.selectAll();

        int cantRegis = 0;
        int cantDispo = 0;
        int cantReserv = 0;
        int cantOcup = 0;

        for (Registro x : registro.toArray()) {
            if (x.getEstado() == 1) {
                cantRegis++;
            }
        }

        /*ALERTAS TOTALES*/
        dashVista.lbTotHab.setText(String.valueOf(habitacion.toArray().size()));
        dashVista.lbTotProd.setText(String.valueOf(producto.toArray().size()));
        dashVista.lbTotUsu.setText(String.valueOf(usuario.toArray().size()));
        dashVista.lbTotFac.setText(String.valueOf(cantRegis));

        /*ALERTAS DE DISPONIBILIDAD*/
        for (Habitacion x : habitacion.toArray()) {
            if (x.getDisposicion().equals("DISPONIBLE")) {
                cantDispo++;
            } else if (x.getDisposicion().equals("OCUPADA")) {
                cantOcup++;
            } else if (x.getDisposicion().equals("RESERVADA")) {
                cantReserv++;
            }
        }

        dashVista.lbHabDis.setText(String.valueOf(cantDispo));
        dashVista.lbHabOcu.setText(String.valueOf(cantOcup));
        dashVista.lbHabRes.setText(String.valueOf(cantReserv));

        crearGrafica();

    }

    public void crearGrafica() throws SQLException {
        int totales[] = new int[12];
        int total = 0;

        ListaSimple<Registro> registro = daoRegistro.selectAll();

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < registro.toArray().size(); j++) {
                if (registro.toArray().get(j).getEstado() == 0) {
                    String fecha[] = registro.toArray().get(j).getFechaSalida().split("/");
                    if ((Integer.parseInt(fecha[1]) - 1) == i) {
                        total++;
                    }
                }
            }
            totales[i] = total;
            total = 0;
        }
        barChart.getBarChart(dashVista.pChart, totales);
    }

    public void mostrarDatos() throws SQLException {
        if (principalOn.equals("mDashboard")) {
            DefaultTableModel modelo = (DefaultTableModel) dashVista.tablaHabAgre.getModel();
            modelo.setRowCount(0);
            ListaSimple<Habitacion> habitacion = daoHabitacion.selectAll();

            for (Habitacion x : habitacion.toArray()) {
                if (x.getDisposicion().equals("DISPONIBLE")) {
                    modelo.addRow(new Object[]{x.getNumHabitacion(), x.getDescripcion(), x.getTipoHabitacion().getNombre(), "$ " + formatoDecimal(x.getPrecio())});
                }
            }
            dashVista.tablaHabAgre.setModel(modelo);

            DefaultTableModel modelo2 = (DefaultTableModel) dashVista.tablaUltFact.getModel();
            modelo2.setRowCount(0);
            ListaSimple<Registro> registro = daoRegistro.selectAllOrder();

            for (Registro x : registro.toArray()) {
                if (x.getEstado() == 0) {
                    modelo2.addRow(new Object[]{x.getIdRegistro(), x.getCliente().getNombre() + " " + x.getCliente().getApellido(), x.getFechaSalida(), "$ " + formatoDecimal(x.getTotal())});
                }
            }
            dashVista.tablaUltFact.setModel(modelo2);
        }
    }

    public String formatoDecimal(Double precio) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);

        return formateador.format(precio);
    }
    
    @Override
    public void actionPerformed(ActionEvent btn) {
        
        if (!principalOn.equals("Login")) {
            
            if(btn.getActionCommand().equals("ModificarInfo")){
                try {
                    configModal.dispose();
                    mostrarModals("modalConfig");
                    System.out.println("Hola");
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
            
            if(btn.getActionCommand().equals("ReporteHab")){
                try {
                    accionesDeBotones(btn);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(btn.getActionCommand().equals("ReporteReg")){
                try {
                    accionesDeBotones(btn);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(btn.getActionCommand().equals("ReportePro")){
                try {
                    accionesDeBotones(btn);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(btn.getActionCommand().equals("ReporteHabPro")){
                try {
                    accionesDeBotones(btn);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
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
                } catch (IOException ex) {
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
                try {
                    verificarCredenciales(btn);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            } catch (IOException ex) {
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
            } catch (IOException ex) {
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
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (btn.getActionCommand().equals("ModificarHabitacion")) {
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
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

        if (principalOn.equals("mUsuarios") && me.getSource() == usuarioVista.tbUsuarios) {

            int columna = usuarioVista.tbUsuarios.getSelectedColumn();

            try {
                if (columna == 8) {
                    int fila = usuarioVista.tbUsuarios.getSelectedRow();
                    String nick = usuarioVista.tbUsuarios.getValueAt(fila, 5).toString();
                    ListaSimple<Usuario> lista = daoUsuario.buscar(nick);
                    usuarioSelected = lista.toArray().get(0);
                    claveAcceso.setClave("");
                    mostrarModals("editarUsuario");
                } else if (columna == 9) {
                    int fila = usuarioVista.tbUsuarios.getSelectedRow();
                    String nick = usuarioVista.tbUsuarios.getValueAt(fila, 5).toString();
                    ListaSimple<Usuario> lista = daoUsuario.buscar(nick);
                    usuarioSelected = lista.toArray().get(0);
                    mostrarModals("eliminarUsuario");
                }
            } catch (Exception ex) {

            }

        }
        
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
        if (principalOn.equals("mRecepcion")) {
            recepcionSelected = new Habitacion();
            recepcionSelected.setNumHabitacion(Integer.parseInt(me.getComponent().getName()));
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
        } catch (Exception e) {

        }

        try {
            if (me.getSource().equals(usuarioVista.btnNuevo)) {
                try {
                    claveAcceso.setClave("");
                    mostrarModals("nuevoUsuario");
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (Exception e) {

        }

        try {
            if (me.getSource().equals(usuarioModal.btnGuardar)) {
                eventosBotones("Agregar");
            }
        } catch (Exception e) {

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
        } catch (Exception e) {

        }

        try {
            if (me.getSource().equals(confirmDialog.btnEliminar)) {
                eventosBotones("Eliminar");
            }
        } catch (Exception e) {


            if(modalOn.equals("mEliminarTipo") && me.getSource().equals(modalEliminar.btnEliminar)){
               eventoLabel("btnEliminarTipo");
            }

            if(modalOn.equals("modalEliminarHab") && me.getSource().equals(modalEliminar.btnEliminar)){
              
                eventoLabel("btnEliminarHabitacion");
            
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
