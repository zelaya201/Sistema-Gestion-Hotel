/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import vistas.main.Regis_hab;
import vistas.main.Vista_hab;

/**
 *
 * @author Adonay
 */
public class Controlador implements ActionListener{

    private Vista_hab vista;
    private Regis_hab registrarHab;

    public Controlador(Vista_hab vista) {
        this.vista = vista;
        mostrarVista("habitacion");
    }
    
    public void mostrarVista(String str){
        if(str.equals("habitacion")){
            vista.setControlador(this);
            vista.iniciar();
        }else if(str.equals("VistaRegistrarhab")){
            registrarHab = new Regis_hab(new JFrame(), true);
            registrarHab.setControlador(this);
            registrarHab.iniciar();
        }
    }

    @Override
    public void actionPerformed(ActionEvent btn) {
        
        if(btn.getActionCommand().equals("Agregar")){
            mostrarVista("VistaRegistrarhab");
        }
    }
    
    
    
    
}
