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
 * @param <T>
 */
public class ListaCircularDoble <T>{
    private NodoDoble lista;
    
    public ListaCircularDoble(){
        this.lista = null;
    }
    
    public boolean isEmpty(){
        return lista == null;
    }
    
    public ArrayList<T> toArrayAsc(){
        NodoDoble aux = lista;
        ArrayList<T> array = new ArrayList();
        
        if (!isEmpty()) {
            do {
                array.add((T)aux.getDato());
                aux = aux.getSiguiente();
            } while (aux != lista);
        }
        
        return array;
    }
    
    public ArrayList<T> toArrayDesc(){
        NodoDoble ultimo = ultimo();
        
        ArrayList<T> array = new ArrayList();
        
        if (!isEmpty()) {
            do {
                array.add((T)ultimo.getDato());
                ultimo = ultimo.getAnterior();
            }while(ultimo != lista.getAnterior());
        }

        return array;
    }
    
    public <T extends Comparable> void insertar(T dato) {
        NodoDoble nuevo = new NodoDoble(dato);
        
        if (isEmpty()) {
            lista = nuevo;
            nuevo.setSiguiente(lista);
            nuevo.setAnterior(lista);
        }else if (dato.compareTo(lista.getDato()) < 0) {
            NodoDoble ultimo = ultimo();
            
            nuevo.setSiguiente(lista);
            lista.setAnterior(nuevo); // agregar
            lista = nuevo;
            
            lista.setAnterior(ultimo);
            
            ultimo.setSiguiente(lista);
        }else {
            NodoDoble ant = ubicar(dato);
                        
            nuevo.setSiguiente(ant.getSiguiente());
            
            if (ant.getSiguiente() != null) {
                ant.getSiguiente().setAnterior(nuevo);
            }
            
            ant.setSiguiente(nuevo);
            nuevo.setAnterior(ant);
        }
    }
    
    public void insertarInicio(T dato) {        
        if (!isEmpty()) {
            NodoDoble nuevo = new NodoDoble(dato);
            NodoDoble ultimo = ultimo();
            
            nuevo.setSiguiente(lista);
            nuevo.setAnterior(ultimo);
            ultimo.setSiguiente(nuevo);
            
            lista = nuevo;
        }
    }
    
    public <T extends Comparable> void insertarAntes(T dato, T parametro) {
        if (!isEmpty() && buscar(parametro) != null) {
            NodoDoble nuevo = new NodoDoble(dato);
            NodoDoble referencia = buscar(parametro);
            
            if (nuevo != null) {
                if (referencia == lista) {
                    NodoDoble ultimo = ultimo();
                    nuevo.setSiguiente(referencia);

                    referencia.setAnterior(nuevo); // agregar

                    nuevo.setAnterior(ultimo);
                    ultimo.setSiguiente(nuevo);
                    lista = nuevo;
                }else {
                    referencia.getAnterior().setSiguiente(nuevo);
                    nuevo.setAnterior(referencia.getAnterior());
                    
                    nuevo.setSiguiente(referencia);
                    referencia.setAnterior(nuevo);
                }
            }
        }
    }
    

    public <T extends Comparable> void insertarFinal(T dato) {

        if (!isEmpty()) {
            NodoDoble nuevo = new NodoDoble(dato);
            NodoDoble ultimo = ultimo();
            
            nuevo.setAnterior(ultimo);
            ultimo.setSiguiente(nuevo);
            
            nuevo.setSiguiente(lista);
        }
    }
    
    private <T extends Comparable> NodoDoble ubicar(T dato) {
        NodoDoble aux = lista;
        NodoDoble ant = lista;
        
        while ((aux.getSiguiente() != lista) 
                && dato.compareTo(aux.getDato()) > 0) {
            dato.compareTo(aux.getDato());
            ant = aux;
            aux = aux.getSiguiente();
        } 
        
        if (dato.compareTo(aux.getDato()) > 0) {
            ant = aux;
        }
        
        return ant;
    }
    
    public <T extends Comparable> NodoDoble buscar(T dato) {
        NodoDoble aux = lista;
        
        if (aux != null) {
            do {
                if (dato.compareTo(aux.getDato()) == 0) {
                    return aux;
                }
                aux = aux.getSiguiente();
            } while (aux != lista);
        }
        
        return null;
    }
    
    public <T extends Comparable> void eliminar(T dato) {
        NodoDoble quitar = buscar(dato);
        NodoDoble ultimo = ultimo();
        
        if (quitar != null) {
            if (quitar == lista) {
                if (lista.getSiguiente() != lista) {
                    
                    lista = quitar.getSiguiente();
                    
                    if (lista != null) {
                        lista.setAnterior(ultimo);
                    }
                    
                    ultimo.setSiguiente(lista);
                }else {
                    lista = null;
                }
                
            }else {
                if (quitar == ultimo) {
                    NodoDoble ant = ubicar(dato);
                    
                    ant.setSiguiente(lista);
                }else {
                    NodoDoble ant = ubicar(dato);
                
                    ant.setSiguiente(quitar.getSiguiente());

                    if (quitar.getSiguiente() != null) {
                        quitar.getSiguiente().setAnterior(ant);
                    }
                }
            }
            
            quitar = null;
        }   
    }
    
    private NodoDoble ultimo() {
        NodoDoble aux = lista;
        
        while((aux.getSiguiente() != lista)) {
            aux = aux.getSiguiente();
        }
        
        return aux;
    }
}
