/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;

/**
 *
 * @author Luis Vaquerano
 */
public class ColaPrioridadN <T> {
    private ArrayList<Nodo> cola;
    
    public ColaPrioridadN(){
        
    }
    
    public ColaPrioridadN(int p) {
        this.cola = new ArrayList();
        for (int i = 0; i < p; i++) {
            cola.add(null);
        }
    }
    
    public boolean isEmpty() {
        if (prioridad() != -1) {
            return false;
        }
        
        return true;
    }
    
    public int prioridad() {
        for (int i = 0; i < cola.size(); i++){
             if (cola.get(i) != null) {
                 return i;
             }
        }
        return -1;
    }
    
    public void offer (T a, int pr) {
        Nodo n = new Nodo(a);
        Nodo fr = cola.get(pr);
        Nodo fn = cola.get(pr);
        
        if (fr == null) {
            cola.set(pr, n);
        }else {
            while(fn.getSiguiente() != null) {
                fn = fn.getSiguiente();
            }
            fn.setSiguiente(n);
        }
    }
    
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        
        int p = prioridad();
        Nodo fr = cola.get(p);
        T dato = (T)fr.getDato();
        fr = fr.getSiguiente();
        
        cola.set(p, fr);
        
        
        return dato;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        
        int p = prioridad();
        Nodo fr = cola.get(p);
        T dato = (T) fr.getDato();
        
        return dato;
    }
    
    public ArrayList<T> toArray() {
        ArrayList<T> datos = new ArrayList();
        
        if (!isEmpty()) {
            for (int i = 0; i < cola.size(); i++) {
                Nodo fr = cola.get(i);
                
                while(fr != null) {
                    datos.add((T) fr.getDato()); 
                    fr = fr.getSiguiente();
                }
            }
        }
        
        return datos;
    }
    
    public ArrayList<T> toArray(int p) {
        ArrayList<T> datos = new ArrayList();
        Nodo fr = cola.get(p);
        
        while (fr != null) {
            datos.add((T)fr.getDato());
            fr = fr.getSiguiente();
        }
        
        return datos;
    }
}
