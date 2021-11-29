/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.entidades;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.dao.ProductoDao;
import modelos.dao.RegistroDao;

/**
 *
 * @author Mario Zelaya
 */
public class RegistroProducto implements Comparable<RegistroProducto>{
    private int id_registro_producto;
    private double subtotal;
    private int cantidad;
    private Registro registro;
    private Producto producto;

    public RegistroProducto() {
    }

    public RegistroProducto(Producto producto) {
        this.producto = producto;
    }

    public RegistroProducto(double subtotal, int cantidad, Registro registro, Producto producto) {
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.registro = registro;
        this.producto = producto;
    }

    public RegistroProducto(int id_registro_producto, double subtotal, int cantidad, Registro registro, Producto producto) {
        this.id_registro_producto = id_registro_producto;
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.registro = registro;
        this.producto = producto;
    }

    public int getId_registro_producto() {
        return id_registro_producto;
    }

    public void setId_registro_producto(int id_registro_producto) {
        this.id_registro_producto = id_registro_producto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Registro getRegistro() {
        try {
            RegistroDao daoRegistro = new RegistroDao();
            registro = daoRegistro.selectAllTo("id_registro", String.valueOf(registro.getIdRegistro())).toArray().get(0);
        } catch (SQLException ex) {
            Logger.getLogger(RegistroProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public Producto getProducto() {
        try {
            ProductoDao daoProducto = new ProductoDao();
            producto = daoProducto.selectAllTo("cod_producto", producto.getCodigo()).toArray().get(0);
            
        } catch (SQLException ex) {
            Logger.getLogger(RegistroProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int compareTo(RegistroProducto t) {
        return 0;
    }
}
