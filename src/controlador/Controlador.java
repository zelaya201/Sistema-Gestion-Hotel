/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import utilidades.ExportPDF;
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
    
    public void eventosBotones(ActionEvent e) throws FileNotFoundException, IOException{
        if (e.getActionCommand().equals("Reporte")) {

            String path = "";

            JFileChooser file = new JFileChooser();
            file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int request = file.showSaveDialog(registrarHab);

            if (request == JFileChooser.APPROVE_OPTION) {
                path = file.getSelectedFile().getPath();
                
                new ExportPDF(path);
            }

        }
 
    }

    @Override
    public void actionPerformed(ActionEvent btn) {
        if(btn.getActionCommand().equals("Agregar")){
            mostrarVista("VistaRegistrarhab");
        }else if(btn.getActionCommand().equals("Reporte")){
            try {
                eventosBotones(btn);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    
    
    
}
