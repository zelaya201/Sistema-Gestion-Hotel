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
public class Producto implements Comparable<Producto>{
    private int cod_producto;
    private String descripcion_producto;
    private double precio_producto;

    public Producto() {
    }

    public Producto(int cod_producto, String descripcion_producto, double precio_producto) {
        this.cod_producto = cod_producto;
        this.descripcion_producto = descripcion_producto;
        this.precio_producto = precio_producto;
    }

    public int getCod_producto() {
        return cod_producto;
    }

    public void setCod_producto(int cod_producto) {
        this.cod_producto = cod_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public double getPrecio_producto() {
        return precio_producto;
    }

    public void setPrecio_producto(double precio_producto) {
        this.precio_producto = precio_producto;
    } 
    
    @Override
    public int compareTo(Producto o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
