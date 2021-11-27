/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mario Zelaya
 */
public class ArbolBB extends ArbolBinario{
    
    public ArbolBB() {
        super();
    }
    
    public <T extends Comparable> void insertar(T dato) {
        super.setRaiz(insertar (dato, super.getRaiz()));
        
    }
    
    private <T extends Comparable> NodoArbol insertar(T dato, NodoArbol r) {
        if (r == null) {
            r = new NodoArbol(dato);
        }else if (dato.compareTo(r.getDato()) < 0) {
            NodoArbol izq;
            izq = insertar(dato, r.getIzdo());
            r.setIzdo(izq);
        }else if (dato.compareTo(r.getDato()) > 0){
            NodoArbol dcho;
            dcho = insertar(dato, r.getDcho());
            r.setDcho(dcho);
        }else {
            JOptionPane.showMessageDialog(null, "Duplicado");
        }
        return r;
    }
    
    private <T extends Comparable> NodoArbol eliminar(T dato, NodoArbol r) {
        if (r == null) {
            JOptionPane.showMessageDialog(null, "No hay un nodo a eliminar");
        }else if (dato.compareTo(r.getDato()) < 0) {
            NodoArbol izq;
            izq = eliminar(dato, r.getIzdo());
            r.setIzdo(izq);
        }else if (dato.compareTo(r.getDato()) > 0) {
            NodoArbol dcho;
            dcho = eliminar(dato, r.getDcho());
            r.setDcho(dcho);
        }else { //NodoArbol encontrado
            NodoArbol q;
            q = r;
            
            if (q.getIzdo() == null) {
                r = q.getDcho();
            }else if (q.getDcho() == null) {
                r = q.getIzdo();
            }else {
                q = reemplazar(q);
            }
            
            q = null;
        }
        
        return r;
    }
    
    public <T extends Comparable> void eliminar(T dato) {
         super.setRaiz(eliminar (dato, super.getRaiz()));

    }
    
    private NodoArbol reemplazar (NodoArbol actual) {
        NodoArbol a, p;
        p = actual;
        a = actual.getIzdo();
        
        while(a.getDcho() != null) {
            p = a;
            a = a.getDcho();
        }
        actual.setDato(a.getDato());
        
        if (p == actual) {
            p.setIzdo(a.getIzdo());
        }else {
            p.setDcho(a.getIzdo());
        }
        
        return a;
    }
           
    
    public ArrayList NID() {
        ArrayList x = new ArrayList();
        
        return super.preOrdenNID(super.getRaiz(), x);
    }
    
    public ArrayList IND() {
        ArrayList x = new ArrayList();
        
        return super.inOrdenIND(super.getRaiz(), x);
    }
    
    public ArrayList IDN() {
        ArrayList x = new ArrayList();
        
        return super.postOrdenIDN(super.getRaiz(), x);
    }
}
