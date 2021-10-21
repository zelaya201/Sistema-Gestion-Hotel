/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.entidades;

import java.util.ArrayList;

/**
 *
 * @author Luis Vaquerano
 */
public class Tipo_Habitacion implements Comparable <Tipo_Habitacion>{
    private int id_tipo;
    private String nombre_tipo;
    private int cantidad_tipo;
    private ArrayList<Habitacion> habitaciones;

    public Tipo_Habitacion() {
    }

    public Tipo_Habitacion(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public Tipo_Habitacion(int id_tipo, String nombre_tipo, int cantidad_tipo) {
        this.id_tipo = id_tipo;
        this.nombre_tipo = nombre_tipo;
        this.cantidad_tipo = cantidad_tipo;
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public String getNombre_tipo() {
        return nombre_tipo;
    }

    public void setNombre_tipo(String nombre_tipo) {
        this.nombre_tipo = nombre_tipo;
    }

    public int getCantidad_tipo() {
        return cantidad_tipo;
    }

    public void setCantidad_tipo(int cantidad_tipo) {
        this.cantidad_tipo = cantidad_tipo;
    }

    public ArrayList<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(ArrayList<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    } 
    
    @Override
    public int compareTo(Tipo_Habitacion o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
