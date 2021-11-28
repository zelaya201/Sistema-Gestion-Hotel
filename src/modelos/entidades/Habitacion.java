/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.entidades;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.dao.HotelDao;
import modelos.dao.RegistroDao;
import modelos.dao.TipoHabitacionDao;
import utilidades.ListaSimple;

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
    private TipoHabitacion tipoHabitacion;
    private Hotel hotel;
    private ListaSimple<Registro> registros;

    public Habitacion() {
    }

    public Habitacion(int numHabitacion) {
        this.numHabitacion = numHabitacion;
    }

    public Habitacion(int numHabitacion, String descripcion, double precio, int estado, String disposicion, TipoHabitacion tipoHabitacion, Hotel hotel) {
        this.numHabitacion = numHabitacion;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.disposicion = disposicion;
        this.tipoHabitacion = tipoHabitacion;
        this.hotel = hotel;
    }

    public Habitacion(String descripcion, double precio, int estado, String disposicion) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.disposicion = disposicion;
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
        try {
            TipoHabitacionDao daoTipo = new TipoHabitacionDao();
            tipoHabitacion = daoTipo.selectId(tipoHabitacion.getIdTipo()).toArray().get(0);
        } catch (SQLException ex) {
            Logger.getLogger(Habitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }

    public Hotel getHotel() {
        try {
            HotelDao daoHotel = new HotelDao();
            hotel = daoHotel.selectAll().toArray().get(0);
            
        } catch (SQLException ex) {
            Logger.getLogger(Habitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public ListaSimple<Registro> getRegistros() {
        try {
            RegistroDao daoRegistro = new RegistroDao();
            registros = daoRegistro.selectAllTo("fk_num_habitacion", String.valueOf(this.getNumHabitacion()));
            
        } catch (SQLException ex) {
            Logger.getLogger(Habitacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return registros;
    }

    public void setRegistros(ListaSimple<Registro> registros) {
        this.registros = registros;
    }
       

    @Override
    public int compareTo(Habitacion t) {
        if (numHabitacion > t.getNumHabitacion()){
            return 1;
        } else if (numHabitacion < t.getNumHabitacion()) {
            return -1;
        }else {
            return 0;
        }
    }
}
