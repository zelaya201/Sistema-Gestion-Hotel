/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author Mario Zelaya
 */
public class NodoArbol<T> {
    private T dato;
    private NodoArbol izdo;
    private NodoArbol dcho;
    int alt;

    public NodoArbol(T dato) {
        this.dato = dato;
        this.izdo = null;
        this.dcho = null;
        this.alt = 0;
    }

    public NodoArbol(T dato, NodoArbol izdo, NodoArbol dcho) {
        this.dato = dato;
        this.izdo = izdo;
        this.dcho = dcho;
        this.alt = 0;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoArbol getIzdo() {
        return izdo;
    }

    public void setIzdo(NodoArbol izdo) {
        this.izdo = izdo;
    }

    public NodoArbol getDcho() {
        return dcho;
    }

    public void setDcho(NodoArbol dcho) {
        this.dcho = dcho;
    }

    public int getAlt() {
        return alt;
    }

    public void setAlt(int alt) {
        this.alt = alt;
    }
    
    @Override
    public String toString() {
        return dato + "";
    }
}
