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
public class Hotel implements Comparable{
    private int idHotel;
    private String nombre;
    private String direccion;
    private String telefono;
    private ListaSimple<Habitacion> habitaciones;

    public Hotel() {
    }

    public Hotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public Hotel(int idHotel, String nombre, String direccion, String telefono) {
        this.idHotel = idHotel;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Hotel(String nombre, String direccion, String telefono, ListaSimple<Habitacion> habitaciones) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.habitaciones = habitaciones;
    }

    public int getIdHotel() {
        return idHotel;
    }

    public void setIdHotel(int idHotel) {
        this.idHotel = idHotel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ListaSimple<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ListaSimple<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public int compareTo(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
