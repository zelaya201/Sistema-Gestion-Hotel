/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.entidades;

/**
 *
 * @author Mario Zelaya
 */
public class Usuario implements Comparable<Usuario>{
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String telefono;
    private String fNacimiento;
    private int edad;
    private String genero;
    private String nick;
    private String rol;
    private String clave;
    private int estado;

    public Usuario() {
    }    

    public Usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public Usuario(String nick, String clave, int estado) {
        this.nick = nick;
        this.clave = clave;
        this.estado = estado;
    }

    public Usuario(String nombre, String apellido, String telefono, String fNacimiento, String genero, String nick, String rol, String clave, int estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fNacimiento = fNacimiento;
        this.genero = genero;
        this.nick = nick;
        this.rol = rol;
        this.clave = clave;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nomUsuario) {
        this.nombre = nomUsuario;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apeUsuario) {
        this.apellido = apeUsuario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telUsuario) {
        this.telefono = telUsuario;
    }

    public String getfNacimiento() {
        return fNacimiento;
    }

    public void setfNacimiento(String fNacimiento) {
        this.fNacimiento = fNacimiento;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edadUsuario) {
        this.edad = edadUsuario;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genUsuario) {
        this.genero = genUsuario;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public int compareTo(Usuario t) {
        Usuario actual = this;
        return (actual.getNombre().compareToIgnoreCase(t.getNombre()));
    }
}
