/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import static java.awt.Color.red;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LookAndFeel;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author Mario Zelaya 
 */
public class MyComboBoxUI extends BasicComboBoxUI{
    @Override
    protected void installDefaults() {
        super.installDefaults();
        LookAndFeel.uninstallBorder(comboBox);
    }

//    @Override
//    protected JButton createArrowButton() {
//        JButton b = super.createArrowButton();
//        return b;
//    }
    
    @Override 
  protected JButton createArrowButton() { 
    JButton button = new JButton(); 
    //se quita el efecto 3d del boton, sombra y darkShadow no se aplican 
    button.setText("");
    button.setContentAreaFilled(false);
    button.setIcon(new ImageIcon(getClass().getResource("/img/sort_down_15px.png")));
    return button;
  } 
    
    @Override
    public void configureArrowButton() {
        super.configureArrowButton();
        //arrowButton.setPreferredSize(new Dimension(500,50));
        arrowButton.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }
}

