/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.ArrayList;
import modelos.entidades.Habitacion;

/**
 *
 * @author Luis Vaquerano
 * @param <T>
 */
public class listaDoble<T>{
    private NodoDoble lista;

    public listaDoble() {
        this.lista = null;
    }
    
    public boolean isEmpty(){
        return lista == null;
    }    
    
    public ArrayList<T> toArrayAsc(){
        NodoDoble aux = lista;
        ArrayList<T> array = new ArrayList();
        while(aux!=null){
            array.add((T)aux.getDato());
            aux = aux.getSiguiente();
        }
        return array;
    }
    
    public ArrayList<T> toArrayDesc(){
        NodoDoble aux = lista;
        while (aux.getSiguiente() != null){
            aux = aux.getSiguiente();
        }
        ArrayList<T> array = new ArrayList();
        while (aux != null){
            array.add((T) aux.getDato());
            aux = aux.getAnterior();
        }
        return array;
    }
    public <T extends Comparable> void insertar(T dato){
        NodoDoble nuevo = new NodoDoble (dato);
        if (isEmpty()){
            lista = nuevo;
        } else if (dato.compareTo(lista.getDato()) < 0){
            nuevo.setSiguiente(lista);
            lista.setAnterior(nuevo);
            lista = nuevo;
        } else {
            NodoDoble ant = ubicar(dato);
            nuevo.setSiguiente(ant.getSiguiente());
            if (ant.getSiguiente() != null){
                ant.getSiguiente().setAnterior(nuevo);
            }
            ant.setSiguiente(nuevo);
            nuevo.setAnterior(ant);
        }
    }
    
    private <T extends Comparable> NodoDoble ubicar(T dato){
        NodoDoble aux = lista;
        NodoDoble ant = lista;

        while ((aux.getSiguiente() != null) && 
                (dato.compareTo(aux.getDato())>0)){
            ant = aux;
            aux = aux.getSiguiente();                                            
        }
        if (dato.compareTo(aux.getDato()) > 0){
            ant = aux;
        }
        return ant;
    }
    
    private <T extends Comparable> NodoDoble buscar(T dato){
        NodoDoble aux = lista;
        while (aux != null){
            if (dato.compareTo(aux.getDato()) == 0){
                return aux;
            }
            aux = aux.getSiguiente();
        }
        return null;
    }
    
    public <T extends Comparable> void eliminar (T dato){
        NodoDoble quitar = buscar(dato);
        if (quitar != null){
            if (quitar == lista){
                lista = quitar.getSiguiente();
                if (lista != null){
                    lista.setAnterior(null);
                }
            } else {
                NodoDoble ant = ubicar(dato);
                ant.setSiguiente(quitar.getSiguiente());
                if (quitar.getSiguiente() != null){
                    quitar.getSiguiente().setAnterior(ant);
                }
            }
            quitar = null;
        }
    }

    
}