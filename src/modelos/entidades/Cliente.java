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
public class Cliente implements Comparable<Cliente>{
    private String dui;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private ListaSimple<Habitacion> habitaciones;

    public Cliente() {
    }

    public Cliente(String dui) {
        this.dui = dui;
    }

    public Cliente(String dui, String nombre, String apellido, String telefono, String email) {
        this.dui = dui;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public ListaSimple<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ListaSimple<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public int compareTo(Cliente t) {
        Cliente actual = this;
        return (actual.getNombre().compareToIgnoreCase(t.getNombre()));
    }
    
    
}