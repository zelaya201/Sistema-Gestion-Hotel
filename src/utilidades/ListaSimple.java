/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;

/**
 *
 * @author Adonay
 */
public class ListaSimple <T>{
    private Nodo lista;
    
    public ListaSimple(){
        this.lista = null;
    }
    
    public boolean isEmpty(){
        return lista == null;
    }
    
    public ArrayList<T> toArray(){
        
        Nodo aux = lista;
        ArrayList array = new ArrayList();
        
        while(aux != null){
            array.add((T)aux.getDato());
            aux = aux.getSiguiente();
        }
        
        return array;
    }
    
    public <T extends Comparable> void insertar(T dato) {
        Nodo nuevo = new Nodo(dato);
        if (isEmpty()) {
            lista = nuevo;
        }else if (dato.compareTo(lista.getDato()) < 0) {
            nuevo.setSiguiente(lista);
            lista = nuevo;
        }else {
            Nodo ant = ubicar(dato);
            nuevo.setSiguiente(ant.getSiguiente());
            ant.setSiguiente(nuevo);
        }
    }
    
    private <T extends Comparable> Nodo ubicar(T dato) {
        Nodo aux = lista;
        Nodo ant = lista;
        
        while ((aux.getSiguiente() != null) && dato.compareTo(aux.getDato()) > 0) {
            dato.compareTo(aux.getDato());
            ant = aux;
            aux = aux.getSiguiente();
        } 
        
        if (dato.compareTo(aux.getDato()) > 0) {
            ant = aux;
        }
        
        return ant;
    }
    
    public <T extends Comparable> Nodo buscar(T dato) {
        Nodo aux = lista;
        
        while(aux != null) {
            if (dato.compareTo(aux.getDato()) == 0) {
                return aux;
            }
            aux = aux.getSiguiente();
        }
        
        return null;
    }
    
    public <T extends Comparable> void eliminar(T dato) {
        Nodo quitar = buscar(dato);
        
        if (quitar != null) {
            if (quitar == lista) {
                lista = quitar.getSiguiente();
            }else {
                Nodo ant = ubicar(dato);
                
                ant.setSiguiente(quitar.getSiguiente());
            }
            
            quitar = null;
        }  
    }
}
