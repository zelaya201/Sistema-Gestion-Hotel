/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.entidades;

/**
 *
 * @author Luis Vaquerano
 */
public class Registro_Producto implements Comparable<Registro_Producto>{
    private double subtotal_registro_producto;
    private int cant_registro_producto;
    private Registro registro;
    private Producto producto;

    public Registro_Producto() {
    }

    public Registro_Producto(double subtotal_registro_producto, int cant_registro_producto, Registro registro, Producto producto) {
        this.subtotal_registro_producto = subtotal_registro_producto;
        this.cant_registro_producto = cant_registro_producto;
        this.registro = registro;
        this.producto = producto;
    }

    public double getSubtotal_registro_producto() {
        return subtotal_registro_producto;
    }

    public void setSubtotal_registro_producto(double subtotal_registro_producto) {
        this.subtotal_registro_producto = subtotal_registro_producto;
    }

    public int getCant_registro_producto() {
        return cant_registro_producto;
    }

    public void setCant_registro_producto(int cant_registro_producto) {
        this.cant_registro_producto = cant_registro_producto;
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
    public int compareTo(Registro_Producto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
