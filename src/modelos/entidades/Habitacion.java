/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.entidades;

/**
 *
 * @author Mario Zelaya
 */
public class Habitacion implements Comparable<Habitacion>{
    private int numHabitacion;
    private String descripcion;
    private double precio;
    private int estado;
    private String disposicion;
    private TipoHabitacion tipo;
    private Hotel hotel;

    public Habitacion() {
    }

    public Habitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public Habitacion(String descripcion, double precio, int estado, String disposicion) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.disposicion = disposicion;
    }

    
    public Habitacion(String descripcion, double precio, int estado, String disposicion, TipoHabitacion tipo, Hotel hotel) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.disposicion = disposicion;
        this.tipo = tipo;
        this.hotel = hotel;
    }

    public int getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDisposicion() {
        return disposicion;
    }

    public void setDisposicion(String disposicion) {
        this.disposicion = disposicion;
    }

    public TipoHabitacion getTipoHabitacion() {
        return tipo;
    }

    public void setTipoHabitacion(TipoHabitacion tipo) {
        this.tipo = tipo;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }    

    @Override
    public int compareTo(Habitacion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
