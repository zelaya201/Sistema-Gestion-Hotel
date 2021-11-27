/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.entidades;

/**
 *
 * @author Mario Zelaya
 */
public class ClaveAcceso implements Comparable<ClaveAcceso>{
    private String clave;

    public ClaveAcceso() {
    }

    public ClaveAcceso(String clave) {
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Override
    public int compareTo(ClaveAcceso t) {
        ClaveAcceso actual = this;
        return (actual.getClave().compareToIgnoreCase(t.getClave()));
    }
}
