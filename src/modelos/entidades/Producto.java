/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.entidades;

import utilidades.ListaSimple;

/**
 *
 * @author Mario Zelaya
 */
public class Producto implements Comparable{
    private String codigo;
    private String descripcion;
    private double precio;
    private ListaSimple<RegistroProducto> registrosProductos;

    public Producto() {
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
        return registrosProductos;
    }

    public void setRegistrosProductos(ListaSimple<RegistroProducto> registrosProductos) {
        this.registrosProductos = registrosProductos;
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
