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
public class Hotel implements Comparable<Hotel>{
    private int id_hotel;
    private String nom_hotel;
    private String dir_hotel;
    private String tel_hotel;

    public Hotel() {
    }

    public Hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public Hotel(int id_hotel, String nom_hotel, String dir_hotel, String tel_hotel) {
        this.id_hotel = id_hotel;
        this.nom_hotel = nom_hotel;
        this.dir_hotel = dir_hotel;
        this.tel_hotel = tel_hotel;
    }

    public int getId_hotel() {
        return id_hotel;
    }

    public void setId_hotel(int id_hotel) {
        this.id_hotel = id_hotel;
    }

    public String getNom_hotel() {
        return nom_hotel;
    }

    public void setNom_hotel(String nom_hotel) {
        this.nom_hotel = nom_hotel;
    }

    public String getDir_hotel() {
        return dir_hotel;
    }

    public void setDir_hotel(String dir_hotel) {
        this.dir_hotel = dir_hotel;
    }

    public String getTel_hotel() {
        return tel_hotel;
    }

    public void setTel_hotel(String tel_hotel) {
        this.tel_hotel = tel_hotel;
    }

    @Override
    public int compareTo(Hotel o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
