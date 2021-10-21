/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos.entidades;

/**
 *
 * @author Luis Vaquerano
 */
public class Cliente implements Comparable<Cliente>{
    private String dui_cliente;
    private String nom_cliente;
    private String ape_cliente;
    private String tel_cliente;
    private String email_cliente;

    public Cliente() {
    }

    public Cliente(String dui_cliente, String nom_cliente, String ape_cliente, String tel_cliente, String email_cliente) {
        this.dui_cliente = dui_cliente;
        this.nom_cliente = nom_cliente;
        this.ape_cliente = ape_cliente;
        this.tel_cliente = tel_cliente;
        this.email_cliente = email_cliente;
    }

    public String getDui_cliente() {
        return dui_cliente;
    }

    public void setDui_cliente(String dui_cliente) {
        this.dui_cliente = dui_cliente;
    }

    public String getNom_cliente() {
        return nom_cliente;
    }

    public void setNom_cliente(String nom_cliente) {
        this.nom_cliente = nom_cliente;
    }

    public String getApe_cliente() {
        return ape_cliente;
    }

    public void setApe_cliente(String ape_cliente) {
        this.ape_cliente = ape_cliente;
    }

    public String getTel_cliente() {
        return tel_cliente;
    }

    public void setTel_cliente(String tel_cliente) {
        this.tel_cliente = tel_cliente;
    }

    public String getEmail_cliente() {
        return email_cliente;
    }

    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }
    
    @Override
    public int compareTo(Cliente o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
