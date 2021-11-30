package vistas.modulos;

import static com.itextpdf.kernel.pdf.PdfName.Pattern;
import controlador.Controlador;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import utilidades.TextPrompt;

public class ModalCliente extends javax.swing.JDialog {

    VistaUsuario vistaUsuario;
    
    public ModalCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.vistaUsuario = vistaUsuario;
        new TextPrompt("DUI", tfDui);
        new TextPrompt("Nombre", tfNom);
        new TextPrompt("Apellido", tfApe);
        new TextPrompt("Teléfono", tfTel);
        new TextPrompt("Email: ejemplo@dominio.com", tfEmail);
    }
    
    public void setControlador(Controlador control){
        this.btnGuardar.addMouseListener(control);
    }
    
    public void iniciar(){
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        rSDateChooser1 = new newscomponents.RSDateChooser();
        rSDateChooser2 = new newscomponents.RSDateChooser();
        jPanel1 = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JLabel();
        form = new javax.swing.JPanel();
        iconUser = new javax.swing.JLabel();
        tfApe = new javax.swing.JTextField();
        iconName = new javax.swing.JLabel();
        tfDui = new javax.swing.JFormattedTextField();
        tfEmail = new javax.swing.JTextField();
        tfNom = new javax.swing.JTextField();
        iconTel = new javax.swing.JLabel();
        tfTel = new javax.swing.JFormattedTextField();
        iconTel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        header.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        header.setForeground(new java.awt.Color(51, 51, 51));
        header.setText("Nuevo Huesped");
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel1.add(header, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0, 15, 0};
        jPanel2Layout.rowHeights = new int[] {0};
        jPanel2.setLayout(jPanel2Layout);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cancelar");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel1MousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(jLabel1, gridBagConstraints);

        btnGuardar.setBackground(new java.awt.Color(0, 102, 204));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardar.setText("Guardar");
        btnGuardar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 204), 1, true));
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 10, 0);
        jPanel2.add(btnGuardar, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel2, gridBagConstraints);

        form.setBackground(new java.awt.Color(255, 255, 255));
        form.setLayout(new java.awt.GridBagLayout());

        iconUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/mail_22px.png"))); // NOI18N
        iconUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(12, 30, 12, 0);
        form.add(iconUser, gridBagConstraints);

        tfApe.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        tfApe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfApeActionPerformed(evt);
            }
        });
        tfApe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfApeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfApeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfApeKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 0, 30);
        form.add(tfApe, gridBagConstraints);

        iconName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/human_head_22px.png"))); // NOI18N
        iconName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        form.add(iconName, gridBagConstraints);

        tfDui.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        try {
            tfDui.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("########-#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 30);
        form.add(tfDui, gridBagConstraints);

        tfEmail.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        tfEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfEmailActionPerformed(evt);
            }
        });
        tfEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEmailKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfEmailKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 360;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 30);
        form.add(tfEmail, gridBagConstraints);

        tfNom.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        tfNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomActionPerformed(evt);
            }
        });
        tfNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfNomKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfNomKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNomKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        form.add(tfNom, gridBagConstraints);

        iconTel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/identification_documents_22px.png"))); // NOI18N
        iconTel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(12, 30, 0, 0);
        form.add(iconTel, gridBagConstraints);

        tfTel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        try {
            tfTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 30);
        form.add(tfTel, gridBagConstraints);

        iconTel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconTel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/phone_22px.png"))); // NOI18N
        iconTel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(12, 30, 0, 0);
        form.add(iconTel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(form, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MousePressed
        this.dispose();
    }//GEN-LAST:event_jLabel1MousePressed

    private void tfApeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfApeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfApeActionPerformed

    private void tfApeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfApeKeyTyped
//        char val = evt.getKeyChar();
//        if((val<'a' || val>'z') && (val<'A' || val>'Z') && (val != ' ') && (val !='ñ')&& (val !='Ñ')&&(val<'á'|| val>'ú')&&(val<'Á'||val>'Ú')){
//            evt.consume();
//        }
        
    }//GEN-LAST:event_tfApeKeyTyped

    private void tfEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailActionPerformed

    private void tfEmailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEmailKeyTyped
        char val = evt.getKeyChar();
        if((val<'a' || val>'z') && (val < '0' || val > '9') && (val == ' '|| val != '.') && val != '@' && val != '_') {
            evt.consume();
        }
        
        if (val == '@' && tfEmail.getText().contains("@")) {
            evt.consume();
        }
    }//GEN-LAST:event_tfEmailKeyTyped

    private void tfNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomActionPerformed

    private void tfNomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomKeyTyped

    }//GEN-LAST:event_tfNomKeyTyped

    private void tfNomKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomKeyPressed
        
    }//GEN-LAST:event_tfNomKeyPressed

    private void tfApeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfApeKeyPressed
        
    }//GEN-LAST:event_tfApeKeyPressed

    private void tfNomKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomKeyReleased
        tfNom.setText(tfNom.getText().toUpperCase());
    }//GEN-LAST:event_tfNomKeyReleased

    private void tfApeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfApeKeyReleased
        tfApe.setText(tfApe.getText().toUpperCase());
    }//GEN-LAST:event_tfApeKeyReleased

    private void tfEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEmailKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tfEmailKeyReleased

    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ModalUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        
//        
//        
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ModalUsuario dialog = new ModalUsuario(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel btnGuardar;
    public javax.swing.JPanel form;
    public javax.swing.JLabel header;
    public javax.swing.JLabel iconName;
    public javax.swing.JLabel iconTel;
    public javax.swing.JLabel iconTel1;
    public javax.swing.JLabel iconUser;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private newscomponents.RSDateChooser rSDateChooser1;
    private newscomponents.RSDateChooser rSDateChooser2;
    public javax.swing.JTextField tfApe;
    public javax.swing.JFormattedTextField tfDui;
    public javax.swing.JTextField tfEmail;
    public javax.swing.JTextField tfNom;
    public javax.swing.JFormattedTextField tfTel;
    // End of variables declaration//GEN-END:variables
}
