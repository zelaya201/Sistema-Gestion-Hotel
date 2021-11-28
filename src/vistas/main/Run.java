/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.main;

import controlador.Controlador;

/**
 *
 * @author Adonay
 */
public class Run {
    public static void main(String[] args) {
//        Menu menu = new Menu();
//        new Controlador(menu);
        Login login = new Login();
        new Controlador(login);
    }
}
