/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.entidades;

import modelos.entidades.Hotel;

/**
 *
 * @author Luis Vaquerano
 */
public class Habitacion implements Comparable<Habitacion>{
    private int id_habitacion;
    private String descr_habitacion;
    private double precio_habitacion;
    private int estado_habitacion;
    private int dispo_habitacion;
    
    private Hotel hotel;
    private Tipo_Habitacion tipoH;
    
    public Habitacion() {
    }

    public Habitacion(int id_habitacion, String descr_habitacion, double precio_habitacion, int estado_habitacion, int dispo_habitacion, Hotel hotel, Tipo_Habitacion tipoH) {
        this.id_habitacion = id_habitacion;
        this.descr_habitacion = descr_habitacion;
        this.precio_habitacion = precio_habitacion;
        this.estado_habitacion = estado_habitacion;
        this.dispo_habitacion = dispo_habitacion;
        this.hotel = hotel;
        this.tipoH = tipoH;
    }

    public int getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(int id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public String getDescr_habitacion() {
        return descr_habitacion;
    }

    public void setDescr_habitacion(String descr_habitacion) {
        this.descr_habitacion = descr_habitacion;
    }

    public double getPrecio_habitacion() {
        return precio_habitacion;
    }

    public void setPrecio_habitacion(double precio_habitacion) {
        this.precio_habitacion = precio_habitacion;
    }

    public int getEstado_habitacion() {
        return estado_habitacion;
    }

    public void setEstado_habitacion(int estado_habitacion) {
        this.estado_habitacion = estado_habitacion;
    }

    public int getDispo_habitacion() {
        return dispo_habitacion;
    }

    public void setDispo_habitacion(int dispo_habitacion) {
        this.dispo_habitacion = dispo_habitacion;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Tipo_Habitacion getTipoH() {
        return tipoH;
    }

    public void setTipoH(Tipo_Habitacion tipoH) {
        this.tipoH = tipoH;
    }
    
    @Override
    public int compareTo(Habitacion o) {
        if (precio_habitacion >= o.getPrecio_habitacion()){
            return 1;
        } else {
            return -1;
        }
    }
    @Override
    public String toString() {
        return id_habitacion + " --- " + descr_habitacion + " --- " +
                precio_habitacion + " --- " +
                estado_habitacion + " --- " + dispo_habitacion;
    }  
}