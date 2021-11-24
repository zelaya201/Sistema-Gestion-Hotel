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
public class Registro implements Comparable<Registro>{
    private int idRegistro;
    private String fechaEntrada;
    private String fechaSalida;
    private String tipo;
    private int estado;
    private double total;
    private double deposito;
    private double mora;
    private Cliente cliente;
    private Habitacion habitacion;
    private Usuario usuario;
    private ListaSimple<RegistroProducto> registrosProductos;

    public Registro() {
    }

    public Registro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Registro(String fechaEntrada, String fechaSalida, String tipo, int estado, double total, double deposito, double mora, Cliente cliente, Habitacion habitacion, Usuario usuario) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.tipo = tipo;
        this.estado = estado;
        this.total = total;
        this.deposito = deposito;
        this.mora = mora;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.usuario = usuario;
    }

    public Registro(String fechaEntrada, String fechaSalida, String tipo, int estado, double total, double deposito, double mora) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.tipo = tipo;
        this.estado = estado;
        this.total = total;
        this.deposito = deposito;
        this.mora = mora;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDeposito() {
        return deposito;
    }

    public void setDeposito(double deposito) {
        this.deposito = deposito;
    }

    public double getMora() {
        return mora;
    }

    public void setMora(double mora) {
        this.mora = mora;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ListaSimple<RegistroProducto> getRegistrosProductos() {
        return registrosProductos;
    }

    public void setRegistrosProductos(ListaSimple<RegistroProducto> registrosProductos) {
        this.registrosProductos = registrosProductos;
    }

    @Override
    public int compareTo(Registro t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
