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
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import modelos.dao.HabitacionDao;
import modelos.dao.HotelDao;
import modelos.dao.ProductoDao;
import modelos.dao.RegistroDao;
import modelos.dao.RegistroProductoDao;
import modelos.entidades.Habitacion;
import modelos.entidades.Hotel;
import modelos.entidades.Producto;
import modelos.entidades.Registro;
import modelos.entidades.RegistroProducto;
import rojerusan.RSTableMetro;
import utilidades.CambiaPanel;
import utilidades.CodigoRecursivo;
import utilidades.ImgTabla;
import utilidades.ListaSimple;
import utilidades.ListaSimple;
import vistas.main.Menu;
import vistas.modulos.ModalAddProducto;
import vistas.modulos.ModalConfig;
import vistas.modulos.ModalEditConfig;
import vistas.modulos.ModalModProducto;
import vistas.modulos.VistaAddVenta;
import vistas.modulos.VistaRecepcion;
import vistas.modulos.VistaRegistro;
import vistas.modulos.VistaVentas;

/**
 *
 * @author Adonay
 */
public class Controlador implements ActionListener, MouseListener, ItemListener, KeyListener {

    DefaultTableModel modelo;
    DecimalFormat df = new DecimalFormat("#.##");
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

    /* VENTAS - PRODUCTOS */
    private Producto productoSelected = null;
    private RegistroDao daoRegistro = new RegistroDao();
    private RegistroProducto registroPSelected = null;
    RegistroProductoDao daoRegistroP = new RegistroProductoDao();
    private Registro registroSelected = null;
    VistaVentas ventasVista;
    private ModalAddProducto modalProducto;
    private VistaAddVenta addVentaVista;
    private ModalModProducto modalMProducto;
    ListaSimple<RegistroProducto> registrosProductos = new ListaSimple();

//    private Producto 
    private ProductoDao daoProducto = new ProductoDao();
    ListaSimple<Producto> productSelected = new ListaSimple();
    /* CONFIGURACIÓN */
    private ModalConfig configModal;
    private HotelDao daoHotel = new HotelDao();
    private ModalEditConfig configModalEdit;

    CodigoRecursivo codeR;

    public Controlador(Menu menu) {
        this.menu = menu;
        this.menu.setControlador(this);
        this.menu.iniciar();
    }

    public void mostrarModulos(String mod) throws SQLException {
        if (mod.equals("mConfig")) {
            configModal = new ModalConfig(new JFrame(), true);
            configModal.setControlador(this);
            principalOn = "mConfig";
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
            principalOn = "mRegistro";
            new CambiaPanel(menu.body, registroVista);
        } else if (mod.equals("mAddVenta")) {
            addVentaVista = new VistaAddVenta();
            addVentaVista.setControlador(this);
            mostrarInfoHab2();
            principalOn = "mAddVenta";
            new CambiaPanel(menu.body, addVentaVista);
            //mostrarTabla(addVentaVista.tbRegistroP);
            llenarComboProducto();
        }
    }

    public void mostrarModals(String modals) throws SQLException {
        if (modals.equals("modalConfig")) {
            configModal.dispose();
            configModalEdit = new ModalEditConfig(new JFrame(), true);
            configModalEdit.setControlador(this);
            modalOn = "modalConfig";
            mostrarInfoHotel();
            configModalEdit.iniciar();
            modalOn = "";
        }

        // VENTAS-PRODUCTOS
        if (modals.equals("nuevoProducto") && principalOn.equals("mVentas")) {

            modalProducto = new ModalAddProducto(new JFrame(), true);
            modalProducto.setControlador(this);
            modalOn = "nuevoProducto";
            modalProducto.iniciar();
//            modalOn = "";

        }

        if (modals.equals("modiProducto") && principalOn.equals("mVentas")) {
            modalMProducto = new ModalModProducto(new JFrame(), true);
            modalMProducto.setControlador(this);
            modalOn = "modiProducto";
            mostrarTablaM(modalMProducto.tbModP);
            modalMProducto.iniciar();

        }
    }

    public void accionesDeBotones(ActionEvent btn) throws SQLException {

        if (btn.getActionCommand().equals("GuardarInfo") && principalOn == "mConfig") {
            if (!configModalEdit.tfNom.getText().isEmpty() && !configModalEdit.tfDir.getText().isEmpty() && !configModalEdit.tfTel.getText().isEmpty()) {
                if (daoHotel.update(new Hotel(1, configModalEdit.tfNom.getText(), configModalEdit.tfDir.getText(), configModalEdit.tfTel.getText()))) {
                    DesktopNotify.setDefaultTheme(NotifyTheme.Green);
                    DesktopNotify.showDesktopMessage("Información de Hotel modificada", "La información del Hotel se modificó correctamente.", DesktopNotify.SUCCESS, 8000);
                    configModalEdit.dispose();
                    modalOn = "";
                    mostrarModulos("mConfig");
                }
            }
        }
        
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
        
        if (btn.getActionCommand().equals("GuardarProducto") && principalOn == "mVentas") {
            
            if (!modalProducto.tfNomP.getText().isEmpty() && !modalProducto.tfPrecio.getText().isEmpty()) {

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
                    mostrarTabla(registrosProductos, addVentaVista.tbRegistroP);
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
    }
    
    public String formatoDecimal(Double precio) {
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat formateador = new DecimalFormat("0.00", simbolos);

        return formateador.format(precio);
    }

    public void mostrarInfoHotel() throws SQLException {
        Hotel hotelInfo = daoHotel.selectAll().toArray().get(0);

        if (principalOn.equals("mConfig")) {
            if (modalOn.equals("modalConfig")) {
                configModalEdit.tfNom.setText(hotelInfo.getNombre());
                configModalEdit.tfDir.setText(hotelInfo.getDireccion());
                configModalEdit.tfTel.setText(hotelInfo.getTelefono());
            } else {
                configModal.lbNomHotel.setText(hotelInfo.getNombre());
                configModal.lbDirHotel.setText(hotelInfo.getDireccion());
                configModal.lbTelHotel.setText(hotelInfo.getTelefono());
            }
        }
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

    public void mostrarInfoHab() throws SQLException { //PARTIR DE AQUI PARA LA ELABORACION DE VENTAS POR HABITACION (Visto anteriormente en mostrarModulos)
        Habitacion habi = daoHabitacion.selectId(recepcionSelected.getNumHabitacion()).toArray().get(0);
        registroVista.lbDescrip.setText(habi.getDescripcion());
        registroVista.lbNumHab.setText(String.valueOf(habi.getNumHabitacion()));

        registroVista.lbEstado.setForeground(Color.white);

        if (habi.getTipoHabitacion().equals("DISPONIBLE")) {
            registroVista.lbEstado.setBackground(new Color(0, 166, 90));
        } else if (habi.getTipoHabitacion().equals("OCUPADA")) {
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
//            if (x.getDispo_habitacion().equals("OCUPADA")){
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
//        }
        }
    }

    @Override
    public void actionPerformed(ActionEvent btn) {
        if (btn.getActionCommand().equals("Configuracion")) {
            try {
                mostrarModulos("mConfig");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }

        if (btn.getActionCommand().equals("Recepcion")) {
            try {
                mostrarModulos("mRecepcion");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
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
            }
        }

        if (btn.getActionCommand().equals("addProductoV")) {
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if (btn.getActionCommand().equals("addRegistroP")) {
            try {
                accionesDeBotones(btn);
                
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (btn.getActionCommand().equals("ModificarInfo")) {
            try {
                mostrarModals("modalConfig");
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (btn.getActionCommand().equals("GuardarInfo")) {
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(btn.getActionCommand().equals("btnModProducto")){
            try {
                accionesDeBotones(btn);
            } catch (SQLException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        //TRABAJANDO...
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
                    mostrarTabla(registrosProductos, addVentaVista.tbRegistroP);
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
        
        try{
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
        }catch(Exception e){
            System.out.println(e);
        }

//        if (modalOn.equals("modiProducto") && principalOn.equals("mVentas") ){
//            productoSelected = new Producto();
//            productoSelected.setCodigo(me.getComponent().getName());
//            try {
//                mostrarModals("modiProducto");
//                modalMProducto.tfNomP.setText(modalMProducto.tbModP.getSelectedRows().toString());
//            } catch (SQLException ex) {
//                System.out.println(ex);
//                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
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

    public void mostrarTabla(ListaSimple<RegistroProducto> lista, JTable tabla) throws SQLException {
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

    @Override
    public void itemStateChanged(ItemEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
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
    }

}
