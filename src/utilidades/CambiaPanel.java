
package utilidades;

import javax.swing.JPanel;

public class CambiaPanel {
    
    private JPanel container;
    private JPanel content;

    public CambiaPanel(JPanel container, JPanel content) { //Panel contenedor - Panel Modulo
        this.container = container;
        this.content = content;
        this.container.removeAll();
        this.container.validate();
        this.container.repaint();
        
        this.container.add(this.content);
        this.container.validate();
        this.container.repaint();
    }

}
