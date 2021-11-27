/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;

/**
 *
 * @author Mario Zelaya
 */
public class ArbolBinario {
    NodoArbol raiz;

    public ArbolBinario() {
        raiz = null;
    }
    
    public boolean isEmpty() {
        return raiz == null;
    }
    
    protected ArrayList preOrdenNID(NodoArbol r, ArrayList a) {
        if (r != null) {
            a.add(r.getDato());
            preOrdenNID(r.getIzdo(), a);
            preOrdenNID(r.getDcho(), a);
        }
        
        return a;
    }
    
    protected ArrayList inOrdenIND(NodoArbol r, ArrayList a) {
        if (r != null) {
            inOrdenIND(r.getIzdo(), a);
            a.add(r.getDato());
            inOrdenIND(r.getDcho(), a);
        }
        
        return a;
    }
    
    protected ArrayList postOrdenIDN(NodoArbol r, ArrayList a) {
        if (r != null) {
            postOrdenIDN(r.getIzdo(), a);
            postOrdenIDN(r.getDcho(), a);
            a.add(r.getDato());
        }
        
        return a;
    }
    
    public <T extends Comparable> NodoArbol buscar(T dato) {
        return buscar(dato, raiz);
    }
    
    private <T extends Comparable> NodoArbol buscar(T dato, NodoArbol r) {
        if (r == null) {
            return null;
        }else if (dato.compareTo(r.getDato()) == 0) {
            return r;
        }else if (dato.compareTo(r.getDato()) < 0) {
            return buscar(dato, r.getIzdo());
        }else {
            return buscar(dato, r.getDcho());
        }
    }

    public NodoArbol getRaiz() {
        return raiz;
    }

    public void setRaiz(NodoArbol raiz) {
        this.raiz = raiz;
    }
}
