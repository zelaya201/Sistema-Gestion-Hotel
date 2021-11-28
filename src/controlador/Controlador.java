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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import java.time.LocalDate;
import java.time.Period;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JFileChooser;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import utilidades.ColaPrioridadN;
import utilidades.ExportPDF;
import utilidades.ImgTabla;
import utilidades.ListaSimple;
import utilidades.TextPrompt;
import vistas.main.Login;
import vistas.main.Menu;
import vistas.modulos.ConfirmDialog;
import vistas.modulos.ConfirmDialogTipo;
import vistas.modulos.Dashboard;
import vistas.modulos.ModalAddProducto;
import vistas.modulos.ModalCliente;
import vistas.modulos.ModalConfig;
import vistas.modulos.ModalEditConfig;
import vistas.modulos.ModalModProducto;
import vistas.modulos.ModalUsuario;
import vistas.modulos.VistaAddVenta;
import vistas.modulos.VistaHabitacion;
import vistas.modulos.VistaListadoRegistro;
import vistas.modulos.VistaRecepcion;
import vistas.modulos.VistaRegistro;
import vistas.modulos.VistaTipo;
import vistas.modulos.VistaUsuario;
import vistas.modulos.VistaVentas;
import vistas.modulos.modalHabitacion;


/**
 *
 * @author Adonay
 */
public class Controlador implements ActionListener, MouseListener, KeyListener, ItemListener {

    private DefaultTableModel modelo;
    private Menu menu;
    private Login login;
    private ConfirmDialog confirmDialog;
    private ColaPrioridadN ListaRegisCola;
    private String principalOn = "";
    private String modalOn = "";
    private String modalConfig = "";
    SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String fechaActual = dtf.format(LocalDateTime.now());

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
    private Registro registroSelected = null;

    /* VENTAS - PRODUCTOS */
    private Producto productoSelected = null;
    private RegistroProducto registroPSelected = null;

    VistaVentas ventasVista;
    private ModalAddProducto modalProducto;
    private VistaAddVenta addVentaVista;
    private ModalModProducto modalMProducto;
    ListaSimple<RegistroProducto> registrosProductos = new ListaSimple();

//    private Producto 
    private ProductoDao daoProducto = new ProductoDao();//
    ListaSimple<Producto> productSelected = new ListaSimple();
    
    /* REGISTRO PRODUCTO */
    private RegistroProductoDao daoRegistroP = new RegistroProductoDao();
    private DefaultTableModel md;
       
    private boolean isLoged = false;
    

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
    private VistaListadoRegistro listadoRegisVista;

    /* RECEPCIÓN */
    private VistaRecepcion recepVista;
    private Habitacion recepcionSelected = null;

    /* CLIENTE */
    private ModalCliente huespedModal;
    private ClienteDao daoCliente = new ClienteDao();

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
        } else if (mod.equals("mVentas")) {
            ventasVista = new VistaVentas();
            ventasVista.setControlador(this);
            generarHabitacionesOcupadas();
            registrosProductos = new ListaSimple();
            principalOn = "mVentas";
            new CambiaPanel(menu.body, ventasVista);
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
            registroVista.txtDescuento.setText("0");
            registroVista.txtAdelanto.setText("0");
            new CambiaPanel(menu.body, registroVista);
        } else if (mod.equals("mAddVenta")) {
            addVentaVista = new VistaAddVenta();
            addVentaVista.setControlador(this);
            mostrarInfoHab2();
            principalOn = "mAddVenta";
            new CambiaPanel(menu.body, addVentaVista);
            llenarComboProducto();
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
        }else if(mod.equals("mListRegistro")){
            this.listadoRegisVista = new VistaListadoRegistro();
            listadoRegisVista.setControlador(this);
            principalOn = "mListRegistro";
            mostrarDatos(listadoRegisVista.tablaListaRegistros);
            new CambiaPanel(menu.body, listadoRegisVista);
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

        // VENTAS-PRODUCTOS
        if (modals.equals("nuevoProducto") && principalOn.equals("mVentas")) {

            modalProducto = new ModalAddProducto(new JFrame(), true);
            modalProducto.setControlador(this);
            modalOn = "nuevoProducto";
            modalProducto.iniciar();
            
        }
        
        if (modals.equals("modiProducto") && principalOn.equals("mVentas")) {
            modalMProducto = new ModalModProducto(new JFrame(), true);
            modalMProducto.setControlador(this);
            modalOn = "modiProducto";
            mostrarTablaM(modalMProducto.tbModP);
            modalMProducto.iniciar();

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

            usuarioModal.setLocationRelativeTo(null);

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
            }else {
                usuarioModal.setSize(555, 495);
                usuarioModal.setLocationRelativeTo(null);
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

                
                Date date = new SimpleDateFormat("yyyy/MM/dd").parse(usuarioSelected.getfNacimiento());

                String formatDate = new SimpleDateFormat("dd/MM/yyyy").format(date);
                Date parseDate = new SimpleDateFormat("dd/MM/yyyy").parse(formatDate);

                usuarioModal.jDate.setDate(parseDate);
                usuarioModal.jtTel.setText(usuarioSelected.getTelefono());
                usuarioModal.cbGenero.setSelectedItem(usuarioSelected.getGenero());

                usuarioModal.jtUser.setText(usuarioSelected.getNick());

                usuarioModal.cbRol.setSelectedItem(usuarioSelected.getRol());

                if (usuario.getRol().equals("Administrador") && !usuarioSelected.getNick().equals(usuario.getNick())) {
                    usuarioModal.cbRol.setEnabled(true);

                }else if (usuario.getRol().equals("Recepcionista") || usuarioSelected.getNick().equals(usuario.getNick())){

                    usuarioModal.cbRol.setEnabled(false);
                }

                new TextPrompt("Nueva contraseña", usuarioModal.jtPass);
                new TextPrompt("Repita la nueva contraseña", usuarioModal.jtPass);

                usuarioModal.setSize(555, 470); //Width - Height
                usuarioModal.setLocationRelativeTo(null);
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
            confirmDialog.btnEliminar.setText("Eliminar");

            confirmDialog.setSize(610, 260);
            confirmDialog.setLocationRelativeTo(null);
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
            usuarioModal.setLocationRelativeTo(null);
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
        }else if(modals.equals("agregarHuesped")) {
            huespedModal = new ModalCliente(new JFrame(), true);
            huespedModal.setControlador(this);
            modalOn = "huespedModal";
            huespedModal.iniciar();
        }
    }

    public void mostrarDatos(JTable tabla) throws SQLException {
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
        
        if(principalOn.equals("mListRegistro")){
            
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img

            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(4).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(7).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(8).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(9).setCellRenderer(diseño);

            ListaSimple<Registro> registro = daoRegistro.selectAll();
            ListaRegisCola = new ColaPrioridadN(13);
            
            for(Registro x : registro.toArray()){
                String prioridad[] = x.getFechaSalida().split("/");
                ListaRegisCola.offer(x, (x.getEstado() == 1) ? Integer.parseInt(prioridad[1]) - 1 : 12);
            }
            
            int i = 1;

            for (Object obj : ListaRegisCola.toArray()) {
                
                Registro x = (Registro) obj;

                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/file.png"));
                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));
                
                ImageIcon img_estado1 = new ImageIcon(getClass().getResource("/img/estado1.png"));
                JLabel lbImg_estado1 = new JLabel(new ImageIcon(img_estado1.getImage()));
                
                ImageIcon img_estado0 = new ImageIcon(getClass().getResource("/img/estado0.png"));
                JLabel lbImg_estado0 = new JLabel(new ImageIcon(img_estado0.getImage()));

                modelo.addRow(new Object[]{
                    x.getIdRegistro(), 
                    x.getHabitacion().getNumHabitacion(), 
                    x.getHabitacion().getDescripcion(), 
                    x.getCliente().getNombre() + " " + x.getCliente().getApellido(), 
                    x.getUsuario().getNombre() + " " + x.getUsuario().getApellido(), 
                    x.getTipo(),
                    (x.getEstado() != 0) ? lbImg_estado1 : lbImg_estado0,
                    x.getFechaEntrada(),
                    x.getFechaSalida(),
                    "$ " + formatoDecimal(x.getTotal()),
                    lbImg_edit
                });
                i++;
                
            }

            if (modelo.getRowCount() < 1) {
                modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
            }

            tabla.setModel(modelo);
        }
    }
    
    public void mostrarBusqueda(ListaSimple lista, JTable tabla){
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
            
            for(Object obj : lista.toArray()){
                Usuario x = (Usuario)obj;
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
        
        /* Listado Registro */
        if(principalOn.equals("mListRegistro")){
            
            tabla.setDefaultRenderer(Object.class, new ImgTabla()); //Renderizar para poner las img

            tabla.getColumnModel().getColumn(0).setCellRenderer(diseño); //Mantener diseño de la tabla por columns
            tabla.getColumnModel().getColumn(1).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(2).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(3).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(4).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(5).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(7).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(8).setCellRenderer(diseño);
            tabla.getColumnModel().getColumn(9).setCellRenderer(diseño);
            
            ListaRegisCola = new ColaPrioridadN(13);
            
            for(Object obj : lista.toArray()){
                Registro x = (Registro) obj;
                String prioridad[] = x.getFechaSalida().split("/");
                ListaRegisCola.offer(x, (x.getEstado() == 1) ? Integer.parseInt(prioridad[1]) - 1 : 12);
            }
            
            int i = 1;

            for (Object obj : ListaRegisCola.toArray()) {
                
                Registro x = (Registro) obj;

                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/file.png"));
                JLabel lbImg_edit = new JLabel(new ImageIcon(img_edit.getImage()));
                
                ImageIcon img_estado1 = new ImageIcon(getClass().getResource("/img/estado1.png"));
                JLabel lbImg_estado1 = new JLabel(new ImageIcon(img_estado1.getImage()));
                
                ImageIcon img_estado0 = new ImageIcon(getClass().getResource("/img/estado0.png"));
                JLabel lbImg_estado0 = new JLabel(new ImageIcon(img_estado0.getImage()));

                modelo.addRow(new Object[]{
                    x.getIdRegistro(), 
                    x.getHabitacion().getNumHabitacion(), 
                    x.getHabitacion().getDescripcion(), 
                    x.getCliente().getNombre() + " " + x.getCliente().getApellido(), 
                    x.getUsuario().getNombre() + " " + x.getUsuario().getApellido(), 
                    x.getTipo(),
                    (x.getEstado() != 0) ? lbImg_estado1 : lbImg_estado0,
                    x.getFechaEntrada(),
                    x.getFechaSalida(),
                    "$ " + formatoDecimal(x.getTotal())
                });
                i++;
                
            }

            if (modelo.getRowCount() < 1) {
                modelo.addRow(new Object[]{"", "", "Ningún resultado encontrado"});
            }

            tabla.setModel(modelo);
        }
    }
    
    public void actualizarHeader(Usuario user) {
        String n[] = user.getNombre().split(" ");
        String a[] = user.getApellido().split(" ");

        menu.lbUserName.setText(n[0] + " " + a[0]);
    }
    
    public void verificarCredenciales(ActionEvent btn) throws SQLException {
        if (principalOn.equals("Login")) {
            if (!login.tfUser.getText().isEmpty() && !login.tfPass.getText().isEmpty()) {
                /* APLICAR ARBOLES DE BUSQUEDA */
                ListaSimple<Usuario> usuarios = daoUsuario.selectAllTo("nick_usuario", login.tfUser.getText());
                
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

                        }else{

                            String n[] = usuario.getNombre().split(" ");
                            String a[] = usuario.getApellido().split(" ");

                            menu.lbUserName.setText(n[0] + " " + a[0]);

                            //Eliminar Modulos
                            menu.modulos.remove(menu.btnUsuario);
                            menu.modulos.remove(menu.btnVenta);
                            menu.modulos.remove(menu.btnConfig);
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

        //DETALLES / MODIFICAR PRODUCTOS
        if (btn.getActionCommand().equals("btnModProducto") && modalOn == "modiProducto") {
            if (!modalMProducto.tfNomP.getText().isEmpty() && !modalMProducto.tfPrecio.getText().isEmpty()) {
                productoSelected.setDescripcion(modalMProducto.tfNomP.getText());
                productoSelected.setPrecio(Double.parseDouble(modalMProducto.tfPrecio.getText()));
                if(daoProducto.update(productoSelected)){
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Información de producto modificada", "La información del Hotel se modificó correctamente.", DesktopNotify.SUCCESS, 8000);
                    mostrarTablaM(modalMProducto.tbModP);
                    modalMProducto.tfNomP.setText("");
                    modalMProducto.tfPrecio.setText("");
                }else{
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Producto no actualizar", "La información no se actualizó correctamente.", DesktopNotify.FAIL, 8000);
                }
            }
        }
        
        //GUARDAR NUEVO PRODUCTO
        if (btn.getActionCommand().equals("GuardarProducto") && principalOn == "mVentas") {
            
            if (!modalProducto.tfNomP.getText().isEmpty() && !modalProducto.tfPrecio.getText().isEmpty()) {
                //wtf xd?
                if (daoProducto.insertar(new Producto("PR00" + daoProducto.selectAll().toArray().size() + 1, modalProducto.tfNomP.getText(), Double.parseDouble(modalProducto.tfPrecio.getText())))) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Nuevo producto ingresado", "La información del producto se guardó correctamente.", DesktopNotify.SUCCESS, 8000);
                    modalProducto.dispose();
                    modalOn = "";
                    mostrarModulos("mVentas");
                } else {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Error al ingresar producto", "La información no se guardó correctamente.", DesktopNotify.FAIL, 8000);
                    modalProducto.dispose();
                    modalOn = "";
                    mostrarModulos("mVentas");
                }

            } else {
               
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("Error al guardar producto", "Ingresa los datos requeridos.", DesktopNotify.WARNING, 8000);
//                    modalProducto.dispose();
                modalOn = "";
                mostrarModulos("mVentas");
            }
        }
        
        /* Boton de agregar venta a registro*/
        if (btn.getActionCommand().equals("addRegistroP") && principalOn == "mAddVenta") {
            if(!registrosProductos.isEmpty()){
                for(RegistroProducto x : registrosProductos.toArray()){

                    this.daoRegistroP.insertar(x);
                }

                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                DesktopNotify.showDesktopMessage("Productos almacenados correctamente", "Se agregaron " + registrosProductos.toArray().size() + " producto(s)", DesktopNotify.SUCCESS, 8000);
                mostrarModulos("mVentas");
                registrosProductos = null;
            }else{
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("Error al guardar el registro de productos", "No se obtuvieron los productos", DesktopNotify.FAIL, 8000);
            }
        }
        
        /* Boton de agregar producto a la venta */
        if (btn.getActionCommand().equals("addProductoV") && principalOn == "mAddVenta") {
            if (!addVentaVista.tfCantidad.getText().isEmpty() && !addVentaVista.tfPrecio.getText().isEmpty() && !addVentaVista.cbProducto.getSelectedItem().toString().equals("Seleccione")) {
                if (!addVentaVista.tfCantidad.getText().equals("0")) {
                    Producto newProducto = daoProducto.selectAllTo("descripcion_producto", addVentaVista.cbProducto.getSelectedItem().toString()).toArray().get(0);
                    
                    String precio[] = addVentaVista.tfPrecio.getText().split(" ");
                    
                    RegistroProducto obj = new RegistroProducto(Double.parseDouble(precio[1]),
                            Integer.parseInt(addVentaVista.tfCantidad.getText()), registroSelected,
                            newProducto);
                    
                    //System.out.println(obj.getProducto().getDescripcion());
                    
                    registrosProductos.insertar(obj);
                    addVentaVista.cbProducto.setSelectedIndex(0);
                    addVentaVista.tfCantidad.setText("");
                    addVentaVista.tfPrecio.setText("");
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Nuevo Registro - Producto ingresado", "La información del producto se guardó correctamente.", DesktopNotify.SUCCESS, 8000);
                    //mostrarModulos("mAddVenta");
                    mostrarTablaRP(registrosProductos, addVentaVista.tbRegistroP);
                }else {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Error al guardar producto", "La cantidad ingresada debe ser mayor a 0.", DesktopNotify.FAIL, 8000);
                    mostrarModulos("mAddVenta");
                }
                
            } else {
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("Error al guardar producto", "Ingrese todos los datos requeridos.", DesktopNotify.FAIL, 8000);
                mostrarModulos("mAddVenta");
            }
        }
        
        if (btn.getActionCommand().equals("ReporteHab")) {
            
            ListaSimple<Habitacion> habitaciones = daoHabitacion.selectAll();
            
            if(!habitaciones.isEmpty()){
                
                String path = "";

                JFileChooser file = new JFileChooser();
                file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int request = file.showSaveDialog(menu);

                if (request == JFileChooser.APPROVE_OPTION) {
                    path = file.getSelectedFile().getPath();
                    ListaSimple<Hotel> hotel = daoHotel.selectAll();

                    ExportPDF exporPdf = new ExportPDF();
                    exporPdf.setHotel(hotel.toArray().get(0));
                    exporPdf.setListHabitaciones(habitaciones);
                    exporPdf.setPath(path);
                    exporPdf.crearListaHabitaciones();
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Reporte generado", "Ruta: " + path, DesktopNotify.INFORMATION, 10000);
                }
            }else{
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("No hay datos", "Para generar un reporte se necesitan datos", DesktopNotify.INFORMATION, 10000);
            }

        }
        
        if (btn.getActionCommand().equals("ReportePro")) {
            
            ListaSimple<Producto> producto = daoProducto.selectAll();
            
            if(!producto.isEmpty()){
                
                String path = "";

                JFileChooser file = new JFileChooser();
                file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int request = file.showSaveDialog(menu);

                if (request == JFileChooser.APPROVE_OPTION) {
                    path = file.getSelectedFile().getPath();

                    ListaSimple<Hotel> hotel = daoHotel.selectAll();

                    ExportPDF exporPdf = new ExportPDF();
                    exporPdf.setHotel(hotel.toArray().get(0));
                    exporPdf.setListaProducto(producto);
                    exporPdf.setPath(path);
                    exporPdf.crearListaProducto();
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Reporte generado", "Ruta: " + path, DesktopNotify.INFORMATION, 10000);
                }
            }else{
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("No hay datos", "Para generar un reporte se necesitan datos", DesktopNotify.INFORMATION, 10000);
            }

        }
        
        if (btn.getActionCommand().equals("ReporteHabPro")) {

            String path = "";

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);   
            int request = file.showSaveDialog(menu);

            if (request == JFileChooser.APPROVE_OPTION) {
                path = file.getSelectedFile().getPath();
                ListaSimple<RegistroProducto> registroProducto = daoRegistroP.selectAllTo("fk_id_registro", "1");
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
                usuarioModal.setLocationRelativeTo(null);
                usuarioModal.iconPass.setVisible(true);
                usuarioModal.jtPass.setVisible(true);
                usuarioModal.jtPassRepet.setVisible(true);
            } else {
                usuarioModal.iconPass.setVisible(false);
                usuarioModal.jtPass.setVisible(false);
                usuarioModal.jtPassRepet.setVisible(false);
                usuarioModal.setSize(555, 470);
                usuarioModal.setLocationRelativeTo(null);
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
        if (principalOn.equals("mRegistro")) {
            
            if (btn.getActionCommand().equals("verificarRegistro") ) {
                
                try {
                    Date actual = f.parse(fechaActual);
                
                    Date fechaE = registroVista.fechaEntrada.getDate();
                    String fechaEntrada = f.format(fechaE);
                    Date fechaEntradaCompare = f.parse(fechaEntrada);
                
                
                    Date fechaS = registroVista.fechaSalida.getDate();
                    String fechaSalida = f.format(fechaS);
                    Date fechaSalidaCompare = f.parse(fechaSalida);
                    if ((registroVista.cbHuesped.getSelectedIndex() > 0) && (registroVista.cbEstado.getSelectedIndex() > 0)) {
                        
                        
                        
                        if (fechaEntradaCompare.before(actual)) {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Error en la fecha", "Fecha de entrada es anterior a la actual", DesktopNotify.WARNING, 8000);
                            
                        }else {
                            if (fechaEntradaCompare.after(fechaSalidaCompare)){
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Error en la fecha", "Fecha de salida es anterior a la fecha de entrada seleccionada", DesktopNotify.WARNING, 8000);
                                
                            }else{
                                registroVista.txtTotalPagar.setText(String.valueOf(obtenerDias(fechaEntrada, fechaSalida) * Double.parseDouble((registroVista.lbPrecio.getText().substring(1)))));
                                registroVista.txtTotalConDescuento.setText(registroVista.txtTotalPagar.getText());
                                registroVista.btnGuardarRegistro.setEnabled(true);
                            }
                        }
                    }else{
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Verifique los datos", "Falta que llene informacion", DesktopNotify.WARNING, 8000);
                                
                    }
                } catch (NumberFormatException e) {
                        
                } catch (ParseException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

            if (btn.getActionCommand().equals("guardarRegistro") ) {
                String huespedC = registroVista.cbHuesped.getSelectedItem().toString().substring(0, 10);
                String tipoRegistro = registroVista.cbEstado.getSelectedItem().toString();
                Date fechaE = registroVista.fechaEntrada.getDate();
                String fechaEntrada = f.format(fechaE);                               
                Date fechaS = registroVista.fechaSalida.getDate();
                String fechaSalida = f.format(fechaS);
//                double descuento = Double.parseDouble(registroVista.txtDescuento.getText());
                double adelanto = Double.parseDouble(registroVista.txtAdelanto.getText());
                double totalPagar = Double.parseDouble(registroVista.txtTotalConDescuento.getText());
                
                Cliente cliente = new Cliente();
                cliente.setDui(huespedC);
                
                Habitacion habitacion = new Habitacion();
                habitacion.setNumHabitacion(Integer.parseInt(registroVista.lbNumHab.getText()));
                if (registroVista.cbEstado.getSelectedItem().toString().equals("HOSPEDAJE")) {
                    habitacion.setDisposicion("OCUPADA");
                }else{
                    habitacion.setDisposicion(registroVista.cbEstado.getSelectedItem().toString());
                }
                
                Usuario user = new Usuario();
                user.setIdUsuario(usuario.getIdUsuario());
                
                Registro registro = new Registro(fechaEntrada, fechaSalida, tipoRegistro, 1, totalPagar, adelanto, 0, cliente, habitacion, user);
                if (daoRegistro.insert(registro)) {
                    
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Registro Satisfactorio", "Hospedaje o Reserva satisfactorio", DesktopNotify.SUCCESS, 8000);
                    
                    if (daoHabitacion.updateEstado(habitacion)) {
                        System.out.println("disposicion actualizada");
                    }
                    mostrarModulos("mRecepcion");
                }
            }
        }
    }
    public static long obtenerDias(String inicio, String fin){
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        long diff = -1;
        try {
            Date dateInicio = f.parse(inicio);
            Date dateFin = f.parse(fin);
            
            diff = Math.round((dateFin.getTime() - dateInicio.getTime()) / (double) 86400000);
        } catch (ParseException e) {
        }
        return diff;
    }
    
    public String formatoDecimal(Double precio) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);

        return formateador.format(precio);
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
                dato = x.getDui() + " | " + x.getNombre() + " " + x.getApellido();
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
                    
                    ImageIcon img_report = new ImageIcon(getClass().getResource("/img/file.png"));
                    JLabel lbImg_report = new JLabel(new ImageIcon(img_report.getImage()));
                    
                    md.addRow(new Object[]{
                    hab.getNumHabitacion(),
                    hab.getDescripcion(),
                    "$" + hab.getPrecio(),
                    hab.getTipoHabitacion().getNombre(),
                    hab.getDisposicion(),
                    lbImg_edit,
                    lbImg_delete, 
                    lbImg_report });
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

    public void eventosBotones(String btn) throws SQLException, IOException {
        if(modalOn.equals("usuarioModal") || modalOn.equals("modalDialog")){
            if(btn.equals("Agregar")){
                if(!usuarioModal.jtNom.getText().isEmpty() && !usuarioModal.jtApe.getText().isEmpty()
                        && usuarioModal.jDate.getDate() != null && !usuarioModal.jtTel.getText().isEmpty()
                        && usuarioModal.cbGenero.getSelectedIndex() > 0 && !usuarioModal.jtUser.getText().isEmpty()
                        && usuarioModal.cbRol.getSelectedIndex() > 0){                    
                    String nom = usuarioModal.jtNom.getText().trim().toUpperCase();
                    //nom = nom.replace(" ", "");
                    String ape = usuarioModal.jtApe.getText().trim().toUpperCase();
                    //ape = ape.replace(" ", "");
                    String tel = usuarioModal.jtTel.getText().trim();

                    Date fecha = usuarioModal.jDate.getDate();
                    DateFormat f = new SimpleDateFormat("yyyy/MM/dd");
                    String fechaNac = f.format(fecha);

                    String genero = usuarioModal.cbGenero.getSelectedItem().toString();
                    String nick = usuarioModal.jtUser.getText().trim();
                    String rol = usuarioModal.cbRol.getSelectedItem().toString();
                    
                    /* Edad */
                    DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    LocalDate birthday = LocalDate.parse(fechaNac, fmt);
                    LocalDate ahora = LocalDate.now();

                    Period periodo = Period.between(birthday, ahora);
                    
                    if (periodo.getYears() >= 18) {
                        if (validarNombre(nom) && validarNombre(ape)) {
                            if(usuarioSelected == null){
                                if (!usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()) {
                                    if(usuarioModal.jtPass.getText().equals(usuarioModal.jtPassRepet.getText())){
                                        String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText().trim(), Encriptacion.SHA256); //Encriptamos la clave

                                        ListaSimple<Usuario> existeUser = daoUsuario.selectAllTo("nick_usuario", usuarioModal.jtUser.getText());

                                        if(existeUser.isEmpty()){
                                            Usuario usuario = new Usuario(nom, ape, tel, fechaNac, genero, nick, rol, clave, 1);

                                            if(daoUsuario.insert(usuario)){
                                                //Mensaje de guardado
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                                DesktopNotify.showDesktopMessage("Usuario guardado", "El usuario ha sido alamcenado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                            }

                                            modalOn = "";
                                            usuarioModal.dispose();

                                        }else if (existeUser.toArray().get(0).getEstado() == 0){
                                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                            DesktopNotify.showDesktopMessage("Usuario " + usuarioModal.jtUser.getText() +  " no está disponible", "El nuevo nombre de usuario debe ser diferente.", DesktopNotify.WARNING, 10000);                             
                                        }else {
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
                                    if (usuarioSelected.getNick().equals(usuario.getNick())) {
                                        isLoged = true;
                                    }
                                    //Modificar
                                    ListaSimple<Usuario> existeUser = daoUsuario.selectAllTo("nick_usuario", usuarioModal.jtUser.getText());

                                    if (!nom.equals(usuarioSelected.getNombre()) || !ape.equals(usuarioSelected.getApellido())
                                            || !fechaNac.equals(usuarioSelected.getfNacimiento()) || !tel.equals(usuarioSelected.getTelefono())
                                            || !genero.equals(usuarioSelected.getGenero()) || !nick.equals(usuarioSelected.getNick())
                                            || !rol.equals(usuarioSelected.getRol())) {
                                        if(existeUser.isEmpty()){
                                            usuarioSelected.setNombre(nom);
                                            usuarioSelected.setApellido(ape);

                                            usuarioSelected.setfNacimiento(fechaNac);
                                            usuarioSelected.setTelefono(tel);
                                            usuarioSelected.setGenero(genero);
                                            usuarioSelected.setNick(nick);
                                            usuarioSelected.setRol(rol);

                                            if(daoUsuario.update(usuarioSelected)){ //Guardado
                                                //Mensaje de modificado
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                                DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                                if (isLoged) {
                                                    usuario.setNick(usuarioSelected.getNick());
                                                    actualizarHeader(usuarioSelected);
                                                }
                                                usuarioSelected = null;
                                                usuarioModal.dispose();
                                                isLoged = false;
                                            }else{ //Ocurrio un error
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                                DesktopNotify.showDesktopMessage("Error", "Usuario no actualizado", DesktopNotify.FAIL, 8000);
                                            }
                                        }else {
                                            usuarioSelected.setNombre(nom);
                                            usuarioSelected.setApellido(ape);

                                            usuarioSelected.setfNacimiento(fechaNac);
                                            usuarioSelected.setTelefono(tel);
                                            usuarioSelected.setGenero(genero);
                                            usuarioSelected.setRol(rol);

                                            if(existeUser.toArray().get(0).getNick().equals(usuarioSelected.getNick())){ 
                                                if(daoUsuario.update(usuarioSelected)){
                                                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                                    DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                                    if (isLoged) {
                                                        actualizarHeader(usuarioSelected);
                                                    }   
                                                    usuarioSelected = null;
                                                    usuarioModal.dispose();
                                                    isLoged = false;
                                                }else{ //Ocurrio un error
                                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                                    DesktopNotify.showDesktopMessage("Error", "Usuario no actualizado", DesktopNotify.FAIL, 8000);
                                                }

                                            }else if (existeUser.toArray().get(0).getEstado() == 0){
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                                DesktopNotify.showDesktopMessage("Usuario " + nick +  " no está disponible", "El nuevo nombre de usuario debe ser diferente.", DesktopNotify.WARNING, 10000);                             
                                            }else {
                                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                                DesktopNotify.showDesktopMessage("Usuario " + nick +  " ya existe", "El nuevo nombre de usuario debe ser diferente a los demás.", DesktopNotify.WARNING, 10000); 
                                            }   
                                        }
                                    }else if (usuarioModal.modiPassCheck.isSelected()) {
                                        if (!usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()){
                                            usuarioSelected.setNombre(nom);
                                            usuarioSelected.setApellido(ape);

                                            usuarioSelected.setfNacimiento(fechaNac);
                                            usuarioSelected.setTelefono(tel);
                                            usuarioSelected.setGenero(genero);
                                            usuarioSelected.setNick(nick);
                                            usuarioSelected.setRol(rol);

                                            if(usuarioModal.jtPass.getText().trim().equals(usuarioModal.jtPassRepet.getText().trim())){
                                                String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText().trim(), Encriptacion.SHA256); //Encriptamos la clave

                                                usuarioSelected.setClave(clave);

                                                if(daoUsuario.update(usuarioSelected)){ //Guardado
                                                    //Mensaje de modificado
                                                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                                    DesktopNotify.showDesktopMessage("Usuario actualizado", "El usuario ha sido modificado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                                    actualizarHeader(usuarioSelected);
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
                                        usuarioModal.dispose();
                                    }
                                }else{
                                    //Campos incompletos
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000); //8 seg
                                }
                            }
                        }else {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Nombre o Apellido Inválido", "El nombre o apellido ingresado es inválido.", DesktopNotify.WARNING, 8000); //8 seg
                        }
                    }else {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Fecha Inválida", "El usuario debe ser mayor de edad.", DesktopNotify.WARNING, 8000); //8 seg
                    }
                }else if (!usuarioModal.jtUser.getText().isEmpty() && !usuarioModal.jtPass.getText().isEmpty() && !usuarioModal.jtPassRepet.getText().isEmpty()) {
                    
                    /* CONFIRMAR ACCESO - ELIMINAR */
                    
                    if (usuarioModal.jtUser.getText().trim().equals(usuario.getNick())) {
                        String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText().trim(), Encriptacion.SHA256);

                        if (clave.equals(usuario.getClave())) {
                                usuarioSelected.setEstado(0);
                                
                                if(daoUsuario.update(usuarioSelected)){
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                    DesktopNotify.showDesktopMessage("Usuario eliminado", "El usuario ha sido eliminado exitosamente.", DesktopNotify.INFORMATION, 8000);
                                    usuarioModal.dispose();
                                }

                                if (usuarioSelected.getNick().equals(usuario.getNick())) {
                                    usuarioSelected = null;
                                    usuario = null; 
                                    isLoged = false;
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
                        
                        if(usuarioModal.jtPass.getText().trim().equals(usuarioModal.jtPassRepet.getText().trim())){
                            String clave = Encriptacion.getStringMessageDigest(usuarioModal.jtPass.getText().trim(), Encriptacion.SHA256);
                            
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
        
        if (modalOn.equals("huespedModal")) {
            if (btn.equals("Agregar")) {
                if (!huespedModal.tfDui.getText().isEmpty() && !huespedModal.tfNom.getText().isEmpty() && !huespedModal.tfApe.getText().isEmpty()
                        && !huespedModal.tfTel.getText().isEmpty() && !huespedModal.tfEmail.getText().isEmpty()) {
                    String dui = huespedModal.tfDui.getText().trim();
                    String nom = huespedModal.tfNom.getText().trim().toUpperCase();
                    String ape = huespedModal.tfApe.getText().trim().toUpperCase();
                    String tel = huespedModal.tfTel.getText().trim();
                    String email = huespedModal.tfEmail.getText().trim();
                    
                    if (validarNombre(nom) && validarNombre(ape)) {
                        if (validarEmail(email)) {
                            ListaSimple<Cliente> existeHuesped = daoCliente.selectAllTo("dui_cliente", dui);

                            if(existeHuesped.isEmpty()){
                                Cliente cliente = new Cliente(dui,nom, ape, tel, email);

                                if(daoCliente.insert(cliente)){
                                    //Mensaje de guardado
                                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                                    DesktopNotify.showDesktopMessage("Huesped guardado", "El huesped ha sido alamcenado exitosamente.", DesktopNotify.SUCCESS, 8000);
                                }

                                modalOn = "";
                                huespedModal.dispose();
                            }else {
                                String n[] = nom.split(" ");
                                String a[] = ape.split(" ");
                                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                                DesktopNotify.showDesktopMessage("Huesped " + n[0] + " " + a[0] +  " ya existe", "El huesped que intenta ingresar ya se encuentra registrado.", DesktopNotify.WARNING, 10000); 
                            }
                        }else {
                            DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                            DesktopNotify.showDesktopMessage("Email inválido", "El email ingresado no cumple con el formato requerido.", DesktopNotify.WARNING, 8000); //8 seg
                        }
                    }else {
                        DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                        DesktopNotify.showDesktopMessage("Nombre o Apellido Inválido", "El nombre o apellido ingresado es inválido.", DesktopNotify.WARNING, 8000); //8 seg
                    }
                }else {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                    DesktopNotify.showDesktopMessage("Campos vacíos", "Por favor rellene todos los campos.", DesktopNotify.WARNING, 8000);
                }
                
                llenarComboRegistro();
            }
            
            
        }
        
        if (btn.equals("Factura")) {
            
            if (registroSelected.getEstado() == 0) {
                String path = "";

                JFileChooser file = new JFileChooser();
                file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int request = file.showSaveDialog(menu);

                if (request == JFileChooser.APPROVE_OPTION) {
                    path = file.getSelectedFile().getPath();
                    ListaSimple<Registro> registro = daoRegistro.selectAllTo("id_registro", String.valueOf(registroSelected.getIdRegistro()));
                    ListaSimple<Hotel> hotel = daoHotel.selectAll();

                    ExportPDF exporPdf = new ExportPDF();
                    exporPdf.setHotel(hotel.toArray().get(0));
                    exporPdf.setListaRegistro(registro);
                    exporPdf.setPath(path);
                    exporPdf.crearFacturaProducto();
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Reporte generado", "Ruta: " + path, DesktopNotify.INFORMATION, 10000);
                }
            }else {
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("Registro no culminado", "El registro seleccionado aun no ha sido facturado.", DesktopNotify.INFORMATION, 10000);
            }

            

        }
        
        if (btn.equals("ReporteReg")) {

            String path = "";

            if(!habitacionSelected.getRegistros().toArray().isEmpty()){
                
                JFileChooser file = new JFileChooser();
                file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int request = file.showSaveDialog(menu);

                if (request == JFileChooser.APPROVE_OPTION) {
                    path = file.getSelectedFile().getPath();
                    ListaSimple<Registro> registro = daoRegistro.selectAllTo("fk_num_habitacion", String.valueOf(habitacionSelected.getNumHabitacion()));
                    ListaSimple<Hotel> hotel = daoHotel.selectAll();

                    ExportPDF exporPdf = new ExportPDF();
                    exporPdf.setHotel(hotel.toArray().get(0));
                    exporPdf.setListaRegistro(registro);
                    exporPdf.setPath(path);
                    exporPdf.crearDetalleHabitacion();
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Reporte generado", "Ruta: " + path, DesktopNotify.INFORMATION, 10000);
                }
                
            }else{
                DesktopNotify.setDefaultTheme(NotifyTheme.Red);
                DesktopNotify.showDesktopMessage("Habitación sin Registros", "Esta habitación aun no contiene un registro de huésped", DesktopNotify.INFORMATION, 10000);
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

    
    public void mostrarInfoHab2() throws SQLException {
//        Registro regis = daoRegistro.selectRegisHab();
        ListaSimple<Registro> regis = daoRegistro.selectAllTo("fk_num_habitacion", String.valueOf(registroSelected.getHabitacion().getNumHabitacion()));
        registroSelected = regis.toArray().get(0);
        addVentaVista.lbCliente.setText(regis.toArray().get(0).getCliente().getDui());

        addVentaVista.lbNumHab.setText(String.valueOf(regis.toArray().get(0).getHabitacion().getNumHabitacion()));
        addVentaVista.lbTipoHab.setText(regis.toArray().get(0).getHabitacion().getTipoHabitacion().getNombre());

        addVentaVista.lbPrecio.setText("$" + String.valueOf(formatoDecimal(regis.toArray().get(0).getHabitacion().getPrecio())));
        addVentaVista.lbFechaIn.setText(regis.toArray().get(0).getFechaEntrada());
        addVentaVista.lbFechaOut.setText(regis.toArray().get(0).getFechaSalida());

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
    
    public boolean validarNombre(String txt) {
        String regx = "^[\\p{L}\\p{M}]+([\\p{L}\\p{Pd}\\p{Zs}']*[\\p{L}\\p{M}])+$|^[\\p{L}\\p{M}]+$";
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }
    
    public boolean validarEmail(String txt) {
        String regx = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"; 
        Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(txt);
        return matcher.find();
    }

    public void cargarDashboard() throws SQLException {

        ListaSimple<Habitacion> habitacion = daoHabitacion.selectAll();
        ListaSimple<Producto> producto = daoProducto.selectAll();
        ListaSimple<Usuario> usuarios = daoUsuario.selectAll();
        ListaSimple<Registro> registro = daoRegistro.selectAll();

        int cantRegis = 0;
        int cantDispo = 0;
        int cantReserv = 0;
        int cantOcup = 0;
        int cantUsuario = 0;

        for (Registro x : registro.toArray()) {
            if (x.getEstado() == 1) {
                cantRegis++;
            }
        }

        /*ALERTAS TOTALES*/
        dashVista.lbTotHab.setText(String.valueOf(habitacion.toArray().size()));
        dashVista.lbTotProd.setText(String.valueOf(producto.toArray().size()));
        for (Usuario x : usuarios.toArray()) {
            if (x.getEstado() == 1) {
                cantUsuario++;
            } 
        }
        dashVista.lbTotUsu.setText(String.valueOf(cantUsuario));
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
    
    @Override
    public void actionPerformed(ActionEvent btn) {
        
        if (!principalOn.equals("Login")) {
            
            if(btn.getActionCommand().equals("ModificarInfo")){
                try {
                    configModal.dispose();
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
            
            if(btn.getActionCommand().equals("ReporteHab")){
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

            if(btn.getActionCommand().equals("AgregarHuesped")){
                try {
                    mostrarModals("agregarHuesped");
                } catch (SQLException ex) {
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
            if (btn.getActionCommand().equals("guardarRegistro")) {
                try {
                    accionesDeBotones(btn);
                } catch (IOException | SQLException e) {
                    System.out.println(e);
                }
            }
            if (btn.getActionCommand().equals("verificarRegistro")) {
                try {
                    accionesDeBotones(btn);
                } catch (IOException | SQLException e) {
                }
            }
            
            if (btn.getActionCommand().equals("Ventas")) {
                try {
                    mostrarModulos("mVentas");
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (btn.getActionCommand().equals("agregarProducto")) {
                try {
                    mostrarModals("nuevoProducto");
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (btn.getActionCommand().equals("modificarProducto")) {
                try {
                    mostrarModals("modiProducto");
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (btn.getActionCommand().equals("GuardarProducto")) {
                try {
                    accionesDeBotones(btn);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (btn.getActionCommand().equals("addProductoV")) {
                try {
                    accionesDeBotones(btn);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
            if (btn.getActionCommand().equals("addRegistroP")) {
                try {
                    accionesDeBotones(btn);

                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(btn.getActionCommand().equals("btnModProducto")){
                try {
                    accionesDeBotones(btn);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(btn.getActionCommand().equals("Registro")){
                try {
                    mostrarModulos("mListRegistro");
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
                    isLoged = false;
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
            } catch (SQLException | IOException ex) {
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
        
        if (modalOn.equals("modiProducto") && principalOn.equals("mVentas") && me.getSource() == modalMProducto.tbModP) {
            ListaSimple<Producto> producto = null;
            int fila = modalMProducto.tbModP.getSelectedRow();
            String codigo = modalMProducto.tbModP.getValueAt(fila, 0).toString();
            
            try {
                producto = this.daoProducto.selectCod(codigo);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            this.productoSelected = producto.toArray().get(0);
            
            this.modalMProducto.tfNomP.setText(productoSelected.getDescripcion());
            this.modalMProducto.tfPrecio.setText(String.valueOf(productoSelected.getPrecio()));
        }
        
        
        //ELIMINAR PRODUCTO EN REGISTRO_PRODUCTO
        if (principalOn.equals("mAddVenta") && me.getSource() == addVentaVista.tbRegistroP) {
            int col = addVentaVista.tbRegistroP.getSelectedColumn();
            try {
                if (col == 4) {
                    int fila = addVentaVista.tbRegistroP.getSelectedRow();
                    String desc = addVentaVista.tbRegistroP.getValueAt(fila, 0).toString();

                    Producto obj = daoProducto.selectAllTo("descripcion_producto", desc).toArray().get(0);
                    RegistroProducto registro = new RegistroProducto(obj);
                    
                    RegistroProducto encontrado = (RegistroProducto) registrosProductos.buscar(registro).getDato();
                    
                    registrosProductos.eliminar(encontrado);
                   // RegistroProducto registro = daoRegistroP.selectAllTo("fk_cod_producto", String.valueOf(obj.getCodigo())).toArray().get(0);
                    
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Producto eliminado del registro", "El producto se ha eliminado correctamente del registro.", DesktopNotify.SUCCESS, 8000);
                    mostrarTablaRP(registrosProductos, addVentaVista.tbRegistroP);
                }
            } catch (SQLException e) {
                System.out.println("ERROR en el mouse clicked del tipo > " + e);
            }
        }
        
        if (principalOn.equals("mUsuarios") && me.getSource() == usuarioVista.tbUsuarios) {

            int columna = usuarioVista.tbUsuarios.getSelectedColumn();

            try {
                if (columna == 8) {
                    int fila = usuarioVista.tbUsuarios.getSelectedRow();
                    String nick = usuarioVista.tbUsuarios.getValueAt(fila, 5).toString();
                    ListaSimple<Usuario> lista = daoUsuario.selectAllTo("nick_usuario", nick);
                    usuarioSelected = lista.toArray().get(0);
                    claveAcceso.setClave("");
                    mostrarModals("editarUsuario");
                } else if (columna == 9) {
                    int fila = usuarioVista.tbUsuarios.getSelectedRow();
                    String nick = usuarioVista.tbUsuarios.getValueAt(fila, 5).toString();
                    ListaSimple<Usuario> lista = daoUsuario.selectAllTo("nick_usuario", nick);
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

                    } else if(col == 7){
                        int fila = habitacionVista.tablaHabitaciones.getSelectedRow();
                        int numHab = (int) habitacionVista.tablaHabitaciones.getValueAt(fila, 0);

                        ListaSimple<Habitacion> habL  = daoHabitacion.selectAllTo("num_habitacion", String.valueOf(numHab));

                        habitacionSelected = habL.toArray().get(0);
                        eventosBotones("ReporteReg");
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR en el mouse clicked del tipo > " + e);
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if (principalOn.equals("mListRegistro") && me.getSource() == listadoRegisVista.tablaListaRegistros) {
                int col = listadoRegisVista.tablaListaRegistros.getSelectedColumn();
                try {

                    if(col == 10){
 
                        int fila = listadoRegisVista.tablaListaRegistros.getSelectedRow();
                        String numHab = listadoRegisVista.tablaListaRegistros.getValueAt(fila, 0).toString();
                 
                        ListaSimple<Registro> habL  = daoRegistro.selectAllTo("id_registro", numHab);

                        registroSelected = habL.toArray().get(0);

                        eventosBotones("Factura");
                    }
                } catch (SQLException e) {
                    System.out.println("ERROR en el mouse clicked del tipo > " + e);
                } catch (IOException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (principalOn.equals("mRecepcion")) {
            recepcionSelected = new Habitacion();
            recepcionSelected.setNumHabitacion(Integer.parseInt(me.getComponent().getName()));
            
            try {
                ListaSimple<Habitacion> th = daoHabitacion.selectAllTo("num_habitacion", me.getComponent().getName());
                recepcionSelected = th.toArray().get(0);
                if (recepcionSelected.getDisposicion().equals("DISPONIBLE")) {
                    mostrarModulos("mRegistro");
                } else if (recepcionSelected.getDisposicion().equals("OCUPADA")){
                    mostrarModulos("mFinalizar");
                    System.out.println("ENTRAMOS A OCUPADA");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //P
        try {
            if (principalOn.equals("mVentas")) {
                registroSelected = new Registro();
                recepcionSelected = new Habitacion();
                recepcionSelected.setNumHabitacion(Integer.parseInt(me.getComponent().getName()));
                registroSelected.setHabitacion(recepcionSelected);
                try {
                    mostrarModulos("mAddVenta");
                } catch (SQLException ex) {
                    System.out.println(ex);
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch(Exception e){
            System.out.println(e);
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
            if (me.getSource().equals(huespedModal.btnGuardar)) {
                eventosBotones("Agregar");
            }
        }catch (Exception e) {
            
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
    
    public void generarHabitacionesOcupadas() throws SQLException {

        ListaSimple<Habitacion> listaHab = this.daoHabitacion.selectAll();
        ListaSimple<Registro> regi = this.daoRegistro.selectAll();
        for (Habitacion x : listaHab.toArray()) {
            
            for(Registro r : regi.toArray()){
                if(r.getEstado() == 1 && x.getNumHabitacion() == r.getHabitacion().getNumHabitacion()){
                    if (x.getDisposicion().equals("OCUPADA")) {
                        GridBagConstraints gridBagConstraints;
                        JPanel panel = new javax.swing.JPanel();
                        JLabel lbNoHab = new JLabel();
                        JLabel lbDispo = new JLabel();
                        JLabel lbTipo = new JLabel();
                        JLabel lbIcono = new JLabel();
                        JScrollPane scroll = new JScrollPane();

                        panel.setBackground(new java.awt.Color(54, 173, 122));
                        lbDispo.setBackground(new java.awt.Color(33, 138, 93));

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
                        lbDispo.setText("GENERAR VENTA");
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
                        ventasVista.ventasPanel.add(scroll);
        //            recepVista.habPanel.add(scroll);
                    }
                }
            }

        }
//    }

    }
    
    public void mostrarTablaRP(ListaSimple<RegistroProducto> lista, JTable tabla) throws SQLException {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("0.00",simbolos);
        modelo = (DefaultTableModel) tabla.getModel();
        DefaultTableCellRenderer d = (DefaultTableCellRenderer) tabla.getCellRenderer(0, 0);
        modelo.setRowCount(0);

        tabla.getColumnModel().getColumn(0).setCellRenderer(d);
        tabla.getColumnModel().getColumn(1).setCellRenderer(d);
        tabla.getColumnModel().getColumn(2).setCellRenderer(d);
        tabla.getColumnModel().getColumn(3).setCellRenderer(d);

        tabla.setDefaultRenderer(Object.class, new ImgTabla());

        for (RegistroProducto x : lista.toArray()) {
            try {

                ImageIcon img_edit = new ImageIcon(getClass().getResource("/img/delete.png"));
                JLabel lbImgEdit = new JLabel(new ImageIcon(img_edit.getImage()));

                if (x.getRegistro().getHabitacion().getNumHabitacion() == registroSelected.getHabitacion().getNumHabitacion()) {
                    modelo.addRow(new Object[]{x.getProducto().getDescripcion(), x.getCantidad(),
                        "$ "+ formateador.format(x.getProducto().getPrecio()), "$ " + formateador.format(x.getSubtotal()), lbImgEdit});

                    tabla.setRowHeight(40);
                }
            } catch (Exception ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        if (modelo.getRowCount() < 1) {
            modelo.addRow(new Object[]{"", "No se encontraron resultados", "", ""});
        }
        tabla.setModel(modelo);
//        }
    }
    
    public void mostrarTablaM(JTable tablaM) throws SQLException {

        modelo = (DefaultTableModel) tablaM.getModel();
        modelo.setRowCount(0);

        for (Producto x : daoProducto.selectAll().toArray()) {
            try {

                modelo.addRow(new Object[]{x.getCodigo(), x.getDescripcion(), "$ " + formatoDecimal(x.getPrecio())});

            } catch (Exception ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (modelo.getRowCount() < 1) {
            modelo.addRow(new Object[]{"No se encontraron resultados"});
        }

        tablaM.setModel(modelo);
    }
    
    public void llenarComboProducto() throws SQLException {
        ListaSimple<Producto> comboProducto = daoProducto.selectAll();
        int tLista = 0;
//        addVentaVista.cbProducto.removeAllItems();
        try {
            tLista = daoProducto.selectAll().toArray().size();
            for (int i = 0; i <= (tLista - 1); i++) {
                addVentaVista.cbProducto.addItem(comboProducto.toArray().get(i).getDescripcion());
//                addVentaVista.tfPrecio.setText(String.valueOf(comboProducto.toArray().get(i).getPrecio()));
            }
        } catch (SQLException e) {
            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, e);
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

    }

    @Override
    public void keyPressed(KeyEvent ke) {

        /* RECEPCION */
        if (principalOn.equals("mListRegistro")) {
            try {
                ListaSimple<Registro> lista = daoRegistro.buscar(listadoRegisVista.tfBusqueda.getText() + ke.getKeyChar());
                
                if (!lista.isEmpty()) {
                    mostrarBusqueda(lista, listadoRegisVista.tablaListaRegistros);
                } else {
                    mostrarDatos(listadoRegisVista.tablaListaRegistros);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* CONTROL DE USUARIOS */
        if (principalOn.equals("mUsuarios")) {
            ListaSimple<Usuario> lista = daoUsuario.buscar(usuarioVista.tfBusqueda.getText() + ke.getKeyChar());
            
            if (lista.isEmpty()) {
                try {
                    mostrarDatos(usuarioVista.tbUsuarios);
                } catch (SQLException ex) {
                    Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                mostrarBusqueda(lista, usuarioVista.tbUsuarios);
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent ke) {

        if (principalOn.equals("mAddVenta")) {
            DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
            simbolos.setDecimalSeparator('.');
            DecimalFormat formateador = new DecimalFormat("0.00",simbolos);
            try {
                if (!addVentaVista.tfCantidad.getText().isEmpty()) {
                    Producto producto = daoProducto.selectAllTo("descripcion_producto", addVentaVista.cbProducto.getSelectedItem().toString()).toArray().get(0);
                
                    int cantidad = Integer.parseInt(addVentaVista.tfCantidad.getText());
                    double precioProducto = producto.getPrecio();
                    
                    if (cantidad > 0) {
                        addVentaVista.tfPrecio.setText("$ " + String.valueOf(formateador.format(cantidad * precioProducto)));
                    }
                }else {
                    addVentaVista.tfPrecio.setText("");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
            
        if (principalOn.equals("mRegistro")) {
            Date fechaE = registroVista.fechaEntrada.getDate();
            String fechaEntrada = f.format(fechaE);    
            Date fechaS = registroVista.fechaSalida.getDate();
            String fechaSalida = f.format(fechaS);
            
            if (ke.getSource().equals(registroVista.txtDescuento)) {
                if (registroVista.txtDescuento.getText().isEmpty() && !registroVista.txtDescuento.getText().equals(".")) {
                    
                    registroVista.txtTotalConDescuento.setText(String.valueOf(obtenerDias(fechaEntrada, fechaSalida) * Double.parseDouble((registroVista.lbPrecio.getText().substring(1)))));
                }else{
                    registroVista.txtTotalConDescuento.setText(String.valueOf((obtenerDias(fechaEntrada, fechaSalida) * Double.parseDouble(registroVista.lbPrecio.getText().substring(1))) - Double.parseDouble(registroVista.txtDescuento.getText())));
                    
                }

            }
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if (registroVista.cbEstado.getSelectedItem().equals("HOSPEDAJE")) {
            registroVista.fechaEntrada.setEnabled(false);
        }
    }
    
}
