/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.entidades;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.dao.TipoHabitacionDao;

/**
 *
 * @author Luis Vaquerano
 */
public class Habitacion implements Comparable<Habitacion>{
    private String id_habitacion;
    private int num_habitacion;
    private String descr_habitacion;
    private double precio_habitacion;
    private int estado_habitacion;
    private String dispo_habitacion;
    
    private Hotel hotel;
    private Tipo_Habitacion tipoH;
    
    public Habitacion() {
    }

    public Habitacion(String id_habitacion, int num_habitacion, String descr_habitacion, double precio_habitacion, int estado_habitacion, String dispo_habitacion, Hotel hotel, Tipo_Habitacion tipoH) {
        this.id_habitacion = id_habitacion;
        this.num_habitacion = num_habitacion;
        this.descr_habitacion = descr_habitacion;
        this.precio_habitacion = precio_habitacion;
        this.estado_habitacion = estado_habitacion;
        this.dispo_habitacion = dispo_habitacion;
        this.hotel = hotel;
        this.tipoH = tipoH;
    }

    public String getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(String id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public int getNum_habitacion() {
        return num_habitacion;
    }

    public void setNum_habitacion(int num_habitacion) {
        this.num_habitacion = num_habitacion;
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

    public String getDispo_habitacion() {
        return dispo_habitacion;
    }

    public void setDispo_habitacion(String dispo_habitacion) {
        this.dispo_habitacion = dispo_habitacion;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Tipo_Habitacion getTipoH() {
        try {
            TipoHabitacionDao tipoHabitacionDao = new TipoHabitacionDao();
            this.tipoH = tipoHabitacionDao.selectId(this.tipoH.getId_tipo()).toArrayAsc().get(0);
        } catch (SQLException ex) {
            Logger.getLogger(Habitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.tipoH;
    }

    public void setTipoH(Tipo_Habitacion tipoH) {
        this.tipoH = tipoH;
    }
    
    @Override
    public int compareTo(Habitacion o) {
        if (num_habitacion > o.getNum_habitacion()){
            return 1;
        } else if (num_habitacion < o.getNum_habitacion()) {
            return -1;
        }else {
            return 0;
        }
    }
    @Override
    public String toString() {
        return id_habitacion + " --- " + num_habitacion + " --- " + descr_habitacion + " --- " +
                precio_habitacion + " --- " +
                estado_habitacion + " --- " + dispo_habitacion;
    }  
}