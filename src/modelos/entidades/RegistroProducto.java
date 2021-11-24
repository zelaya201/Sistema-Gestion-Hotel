/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.entidades;

/**
 *
 * @author Mario Zelaya
 */
public class RegistroProducto implements Comparable<RegistroProducto>{
    private double subtotal;
    private int cantidad;
    private Registro registro;
    private Producto producto;

    public RegistroProducto() {
    }

    public RegistroProducto(double subtotal, int cantidad, Registro registro, Producto producto) {
        this.subtotal = subtotal;
        this.cantidad = cantidad;
        this.registro = registro;
        this.producto = producto;
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
        return registro;
    }

    public void setRegistro(Registro registro) {
        this.registro = registro;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public int compareTo(RegistroProducto t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
