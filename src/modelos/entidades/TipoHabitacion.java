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
public class TipoHabitacion implements Comparable<TipoHabitacion>{
    private int idTipo;
    private String nombre;
    private int cantidad;
    private ListaSimple<Habitacion> habitaciones;

    public TipoHabitacion() {
    }

    public TipoHabitacion(int idTipo) {
        this.idTipo = idTipo;
    }

    public TipoHabitacion(String nombre, int cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ListaSimple<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ListaSimple<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public int compareTo(TipoHabitacion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
