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
public class Test {
    public static void main(String[] args) {
        Libro obj1 = new Libro("978-84-481-5631-2", "Estructura de Datos en Java", "Luis Joyanes Aguilar", "MCGRAW-HILL");
        Libro obj2 = new Libro("978-958-99930-2-6", "Análisis de Algoritmos", "Sergio Augusto Cargona Torres", "ELIZCOM");
        Libro obj3 = new Libro("978-987-1609-36-9", "Java a Fondo", "Pablo Augusto Sznajdleder", "MCGRAW-HILL");
        
        ListaCircularDoble listita = new ListaCircularDoble();
        
        listita.insertar(obj1);
        listita.insertar(obj2);
        listita.insertar(obj3);
        
        for (Object x: listita.toArrayAsc()) {
            System.out.println(x);
        } 
        
        System.out.println("--------------");
        
        listita.eliminar(obj3);
        
        for (Object x: listita.toArrayDesc()) {
            System.out.println(x);
        }
        
        System.out.println("--------------");
        
        listita.insertarFinal(obj3);
        
        for (Object x: listita.toArrayAsc()) {
            System.out.println(x);
        }
    }
}
