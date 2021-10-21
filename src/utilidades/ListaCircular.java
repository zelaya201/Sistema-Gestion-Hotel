/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author Luis Vaquerano
 */
public class ListaCircular<T> {
    private Nodo lista;

    public ListaCircular() {
        this.lista = null;
    }
    public boolean isEmpty(){
        return lista == null;
    }    
    public ArrayList<T> toArray(){
        Nodo aux = lista;
        ArrayList<T> array = new ArrayList();
        if (!isEmpty()){
        do {
            array.add((T)aux.getDato());
            aux = aux.getSiguiente();
        } while (aux != lista);
        }
        return array;
    }
    public <T extends Comparable> void insertar(T dato){
        Nodo nuevo = new Nodo (dato);
        if (isEmpty()){
            lista = nuevo;
            nuevo.setSiguiente(lista);
        } else if (dato.compareTo(lista.getDato()) < 0){
            Nodo ultimo = ultimo();
            nuevo.setSiguiente(lista);
            lista = nuevo;
            ultimo.setSiguiente(lista);   
        } else {
            Nodo ant = ubicar(dato);
            nuevo.setSiguiente(ant.getSiguiente());
            ant.setSiguiente(nuevo);
        }
    }
     
    private Nodo ultimo(){
        Nodo aux = lista;
        while ((aux.getSiguiente() != lista)){
            aux = aux.getSiguiente();
        }
        return aux;
    }
    private <T extends Comparable> Nodo ubicar(T dato){
        Nodo aux = lista;
        Nodo ant = lista;

        while ((aux.getSiguiente() != lista) && 
                (dato.compareTo(aux.getDato())>0)){
            ant = aux;
            aux = aux.getSiguiente();
        }
        if (dato.compareTo(aux.getDato()) > 0){
            ant = aux;
        }
        return aux;
    }
    
    public <T extends Comparable> Nodo buscar (T dato){
        Nodo aux = lista;
        if (aux != null){
        do{
            if (dato.compareTo(aux.getDato()) == 0){
                return aux;
            }
            aux = aux.getSiguiente();
        } while (aux != lista);
        }
        return null;
    }

    public <T extends Comparable> void eliminar (T dato){
        Nodo quitar = buscar (dato);
        if (quitar != null){
            if (quitar == lista){
                if (lista.getSiguiente() != lista){
                   Nodo ultimo = ultimo();
                   lista = quitar.getSiguiente(); 
                   ultimo.setSiguiente(lista);
                } else {
                    lista = null;
                } 
            } else {
                Nodo ant = ubicar (dato);
                ant.setSiguiente(quitar.getSiguiente());
            }
            quitar = null;
        }
    }
}