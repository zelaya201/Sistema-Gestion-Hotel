/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.entidades;

import modelos.entidades.Habitacion;

/**
 *
 * @author Luis Vaquerano
 */
public class Registro implements Comparable <Registro>{
    private int id_registro;
    private String fentrada_registro;
    private String fsalida_registro;
    private String tipo_registro;
    private int estado_registro;
    private double total_registro;
    private double deposito_registro;
    private double mora_registro;
    private Habitacion habitacion;
    private Cliente cliente;

    public Registro() {
    }

    public Registro(int id_registro, String fentrada_registro, String fsalida_registro, String tipo_registro, int estado_registro, double total_registro, double deposito_registro, double mora_registro, Habitacion habitacion, Cliente cliente) {
        this.id_registro = id_registro;
        this.fentrada_registro = fentrada_registro;
        this.fsalida_registro = fsalida_registro;
        this.tipo_registro = tipo_registro;
        this.estado_registro = estado_registro;
        this.total_registro = total_registro;
        this.deposito_registro = deposito_registro;
        this.mora_registro = mora_registro;
        this.habitacion = habitacion;
        this.cliente = cliente;
    }

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public String getFentrada_registro() {
        return fentrada_registro;
    }

    public void setFentrada_registro(String fentrada_registro) {
        this.fentrada_registro = fentrada_registro;
    }

    public String getFsalida_registro() {
        return fsalida_registro;
    }

    public void setFsalida_registro(String fsalida_registro) {
        this.fsalida_registro = fsalida_registro;
    }

    public String getTipo_registro() {
        return tipo_registro;
    }

    public void setTipo_registro(String tipo_registro) {
        this.tipo_registro = tipo_registro;
    }

    public int getEstado_registro() {
        return estado_registro;
    }

    public void setEstado_registro(int estado_registro) {
        this.estado_registro = estado_registro;
    }

    public double getTotal_registro() {
        return total_registro;
    }

    public void setTotal_registro(double total_registro) {
        this.total_registro = total_registro;
    }

    public double getDeposito_registro() {
        return deposito_registro;
    }

    public void setDeposito_registro(double deposito_registro) {
        this.deposito_registro = deposito_registro;
    }

    public double getMora_registro() {
        return mora_registro;
    }

    public void setMora_registro(double mora_registro) {
        this.mora_registro = mora_registro;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public int compareTo(Registro o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
