package vistas.modulos;

import controlador.Controlador;
import utilidades.TextPrompt;

public class ModalUsuario extends javax.swing.JDialog {

    VistaUsuario vistaUsuario;
    
    public ModalUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        this.vistaUsuario = vistaUsuario;
        new TextPrompt("Nombre", jtNom);
        new TextPrompt("Apellido", jtApe);
        new TextPrompt("Teléfono", jtTel);
        new TextPrompt("Usuario", jtUser);
        new TextPrompt("Contraseña", jtPass);
        new TextPrompt("Repetir contraseña", jtPassRepet);
        this.modiPassCheck.setActionCommand("checkbox");
    }
    
    public void setControlador(Controlador control){
        this.btnBaja.addMouseListener(control);
        this.btnGuardar.addMouseListener(control);
        this.modiPassCheck.addActionListener(control);
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
        btnBaja = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JLabel();
        form = new javax.swing.JPanel();
        iconUser = new javax.swing.JLabel();
        jtApe = new javax.swing.JTextField();
        iconName = new javax.swing.JLabel();
        iconBirthday = new javax.swing.JLabel();
        iconPass = new javax.swing.JLabel();
        jtPass = new javax.swing.JPasswordField();
        jtPassRepet = new javax.swing.JPasswordField();
        jDate = new com.toedter.calendar.JDateChooser();
        iconRol = new javax.swing.JLabel();
        cbRol = new javax.swing.JComboBox<>();
        jtTel = new javax.swing.JFormattedTextField();
        jtUser = new javax.swing.JTextField();
        jtNom = new javax.swing.JTextField();
        iconTel = new javax.swing.JLabel();
        cbGenero = new javax.swing.JComboBox<>();
        modiPassCheck = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        header.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        header.setForeground(new java.awt.Color(51, 51, 51));
        header.setText("Nuevo Usuario");
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.weightx = 5.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
        jPanel1.add(header, gridBagConstraints);

        btnBaja.setBackground(new java.awt.Color(255, 102, 102));
        btnBaja.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnBaja.setForeground(new java.awt.Color(255, 255, 255));
        btnBaja.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBaja.setText("Dar de baja");
        btnBaja.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 102, 102), 1, true));
        btnBaja.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBaja.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 50;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        jPanel1.add(btnBaja, gridBagConstraints);

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
        iconUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_22px.png"))); // NOI18N
        iconUser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(12, 30, 0, 0);
        form.add(iconUser, gridBagConstraints);

        jtApe.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        jtApe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtApeActionPerformed(evt);
            }
        });
        jtApe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtApeKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(20, 5, 0, 30);
        form.add(jtApe, gridBagConstraints);

        iconName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/human_head_22px.png"))); // NOI18N
        iconName.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 0, 0);
        form.add(iconName, gridBagConstraints);

        iconBirthday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconBirthday.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/birthday_cake_22px.png"))); // NOI18N
        iconBirthday.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(12, 30, 0, 0);
        form.add(iconBirthday, gridBagConstraints);

        iconPass.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/key_22px.png"))); // NOI18N
        iconPass.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.ipadx = 16;
        gridBagConstraints.insets = new java.awt.Insets(12, 30, 12, 0);
        form.add(iconPass, gridBagConstraints);

        jtPass.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 12, 0);
        form.add(jtPass, gridBagConstraints);

        jtPassRepet.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 12, 30);
        form.add(jtPassRepet, gridBagConstraints);

        jDate.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 360;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 30);
        form.add(jDate, gridBagConstraints);

        iconRol.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconRol.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/leadership_22px.png"))); // NOI18N
        iconRol.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(12, 30, 0, 0);
        form.add(iconRol, gridBagConstraints);

        cbRol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione rol", "Administrador", "Recepcionista" }));
        cbRol.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 30);
        form.add(cbRol, gridBagConstraints);

        jtTel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        try {
            jtTel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 0);
        form.add(jtTel, gridBagConstraints);

        jtUser.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        jtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtUserActionPerformed(evt);
            }
        });
        jtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtUserKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 360;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(12, 0, 0, 30);
        form.add(jtUser, gridBagConstraints);

        jtNom.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        jtNom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtNomActionPerformed(evt);
            }
        });
        jtNom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jtNomKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 22;
        gridBagConstraints.insets = new java.awt.Insets(20, 0, 0, 0);
        form.add(jtNom, gridBagConstraints);

        iconTel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconTel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/resume_22px.png"))); // NOI18N
        iconTel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(12, 30, 0, 0);
        form.add(iconTel, gridBagConstraints);

        cbGenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione su genero", "Masculino", "Femenino" }));
        cbGenero.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 150;
        gridBagConstraints.ipady = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(12, 5, 0, 30);
        form.add(cbGenero, gridBagConstraints);

        modiPassCheck.setBackground(new java.awt.Color(255, 255, 255));
        modiPassCheck.setText("Modificar Contraseñas");
        modiPassCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modiPassCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.insets = new java.awt.Insets(12, 123, 0, 0);
        form.add(modiPassCheck, gridBagConstraints);

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

    private void jtApeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtApeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtApeActionPerformed

    private void jtApeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtApeKeyTyped
        
    }//GEN-LAST:event_jtApeKeyTyped

    private void jtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtUserActionPerformed

    private void jtUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtUserKeyTyped
        char val = evt.getKeyChar();
        if((val<'a' || val>'z') && (val < '0' || val > '9')) evt.consume();
    }//GEN-LAST:event_jtUserKeyTyped

    private void jtNomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtNomActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNomActionPerformed

    private void jtNomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtNomKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jtNomKeyTyped

    private void modiPassCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modiPassCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_modiPassCheckActionPerformed

    
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
    public javax.swing.JLabel btnBaja;
    public javax.swing.JLabel btnGuardar;
    public javax.swing.JComboBox<String> cbGenero;
    public javax.swing.JComboBox<String> cbRol;
    public javax.swing.JPanel form;
    public javax.swing.JLabel header;
    public javax.swing.JLabel iconBirthday;
    public javax.swing.JLabel iconName;
    public javax.swing.JLabel iconPass;
    public javax.swing.JLabel iconRol;
    public javax.swing.JLabel iconTel;
    public javax.swing.JLabel iconUser;
    public com.toedter.calendar.JDateChooser jDate;
    private javax.swing.JLabel jLabel1;
    public javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JTextField jtApe;
    public javax.swing.JTextField jtNom;
    public javax.swing.JPasswordField jtPass;
    public javax.swing.JPasswordField jtPassRepet;
    public javax.swing.JFormattedTextField jtTel;
    public javax.swing.JTextField jtUser;
    public javax.swing.JCheckBox modiPassCheck;
    private newscomponents.RSDateChooser rSDateChooser1;
    private newscomponents.RSDateChooser rSDateChooser2;
    // End of variables declaration//GEN-END:variables
}
