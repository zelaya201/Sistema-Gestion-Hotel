/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.entidades;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.dao.RegistroProductoDao;
import utilidades.ListaSimple;

/**
 *
 * @author Mario Zelaya
 */
public class Producto implements Comparable<Producto>{
    private String codigo;
    private String descripcion;
    private double precio;
    private ListaSimple<RegistroProducto> registrosProductos;

    public Producto() {
    }

    public Producto(String codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto(String codigo) {
        this.codigo = codigo;
    }

    public Producto(String codigo, String descripcion, double precio, ListaSimple<RegistroProducto> registrosProductos) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.registrosProductos = registrosProductos;
    }

    public Producto(String descripcion, double precio, ListaSimple<RegistroProducto> registrosProductos) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.registrosProductos = registrosProductos;
    }

    public Producto(String descripcion, double precio) {
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ListaSimple<RegistroProducto> getRegistrosProductos() {
        try {
            RegistroProductoDao daoRegistro = new RegistroProductoDao();
            registrosProductos = daoRegistro.selectAllTo("fk_cod_producto", this.getCodigo());            
        } catch (SQLException ex) {
            Logger.getLogger(Habitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registrosProductos;
    }

    public void setRegistrosProductos(ListaSimple<RegistroProducto> registrosProductos) {
        this.registrosProductos = registrosProductos;
    }

    @Override
    public int compareTo(Producto t) {
        Producto actual = this;
        return (actual.getDescripcion().compareToIgnoreCase(t.getDescripcion()));
    }
}
