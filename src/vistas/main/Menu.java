package vistas.main;

import controlador.Controlador;
import java.awt.Color;
import javax.swing.JFrame;


public class Menu extends javax.swing.JFrame {

    boolean menuActived = true;
    boolean despleg = false;
    
    public Menu() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.btnListRegistro.setVisible(false);
        this.btnHab.setVisible(false);
        this.btnTipoHab.setVisible(false);
        this.btnDash.setActionCommand("Dashboard");
        this.btnProducto.setActionCommand("Productos");
        this.btnTipoHab.setActionCommand("Movimientos");
        this.btnConfig.setActionCommand("Configuracion");
        this.btnRecepcion.setActionCommand("Recepcion");
    }

    public void setControlador(Controlador control){
        this.btnDash.addActionListener(control);
        this.btnProducto.addActionListener(control);
        this.btnTipoHab.addActionListener(control);
        this.btnConfig.addActionListener(control);
        this.btnRecepcion.addActionListener(control);
    }
    
    public void iniciar(){
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        aside = new javax.swing.JPanel();
        modulos = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnConfig = new newscomponents.RSButtonIcon_new();
        btnDash = new newscomponents.RSButtonIcon_new();
        btnTipoHab = new newscomponents.RSButtonIcon_new();
        btnProducto = new newscomponents.RSButtonIcon_new();
        btnRecepcion = new newscomponents.RSButtonIcon_new();
        btnHab = new newscomponents.RSButtonIcon_new();
        btbHabitacion = new newscomponents.RSButtonIcon_new();
        btnReportes = new newscomponents.RSButtonIcon_new();
        btnUsuario = new newscomponents.RSButtonIcon_new();
        btnListRegistro = new newscomponents.RSButtonIcon_new();
        header = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        btnMenu = new RSMaterialComponent.RSButtonIconOne();
        body = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        aside.setBackground(new java.awt.Color(42, 53, 66));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 2);
        flowLayout1.setAlignOnBaseline(true);
        aside.setLayout(flowLayout1);

        modulos.setOpaque(false);
        modulos.setLayout(new java.awt.GridBagLayout());

        jLabel13.setBackground(new java.awt.Color(37, 46, 57));
        jLabel13.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("MENÚ");
        jLabel13.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jLabel13.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 202;
        gridBagConstraints.ipady = 23;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 6, 0);
        modulos.add(jLabel13, gridBagConstraints);

        btnConfig.setBackground(new java.awt.Color(42, 53, 66));
        btnConfig.setText("Configuración");
        btnConfig.setActionCommand("Kardex");
        btnConfig.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnConfig.setFocusPainted(false);
        btnConfig.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnConfig.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnConfig.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnConfig.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SETTINGS);
        btnConfig.setSizeIcon(25.0F);
        btnConfig.setVerifyInputWhenFocusTarget(false);
        btnConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        modulos.add(btnConfig, gridBagConstraints);

        btnDash.setBackground(new java.awt.Color(241, 123, 55));
        btnDash.setText("Dashboard");
        btnDash.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnDash.setFocusPainted(false);
        btnDash.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnDash.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnDash.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnDash.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.DASHBOARD);
        btnDash.setSizeIcon(25.0F);
        btnDash.setVerifyInputWhenFocusTarget(false);
        btnDash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        modulos.add(btnDash, gridBagConstraints);

        btnTipoHab.setBackground(new java.awt.Color(51, 65, 80));
        btnTipoHab.setText("Tipo de Habitación");
        btnTipoHab.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnTipoHab.setFocusPainted(false);
        btnTipoHab.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnTipoHab.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnTipoHab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnTipoHab.setIconTextGap(10);
        btnTipoHab.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHEVRON_RIGHT);
        btnTipoHab.setOpaque(true);
        btnTipoHab.setRequestFocusEnabled(false);
        btnTipoHab.setSizeIcon(25.0F);
        btnTipoHab.setVerifyInputWhenFocusTarget(false);
        btnTipoHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTipoHabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        modulos.add(btnTipoHab, gridBagConstraints);

        btnProducto.setBackground(new java.awt.Color(42, 53, 66));
        btnProducto.setText("Productos");
        btnProducto.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnProducto.setFocusPainted(false);
        btnProducto.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnProducto.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnProducto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnProducto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ARCHIVE);
        btnProducto.setSizeIcon(25.0F);
        btnProducto.setVerifyInputWhenFocusTarget(false);
        btnProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        modulos.add(btnProducto, gridBagConstraints);

        btnRecepcion.setBackground(new java.awt.Color(42, 53, 66));
        btnRecepcion.setText("Recepción");
        btnRecepcion.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnRecepcion.setFocusPainted(false);
        btnRecepcion.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnRecepcion.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnRecepcion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnRecepcion.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.RECEIPT);
        btnRecepcion.setSizeIcon(25.0F);
        btnRecepcion.setVerifyInputWhenFocusTarget(false);
        btnRecepcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecepcionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        modulos.add(btnRecepcion, gridBagConstraints);

        btnHab.setBackground(new java.awt.Color(51, 65, 80));
        btnHab.setText("Habitaciones");
        btnHab.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnHab.setFocusPainted(false);
        btnHab.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnHab.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnHab.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnHab.setIconTextGap(10);
        btnHab.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHEVRON_RIGHT);
        btnHab.setOpaque(true);
        btnHab.setSizeIcon(25.0F);
        btnHab.setVerifyInputWhenFocusTarget(false);
        btnHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHabActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        modulos.add(btnHab, gridBagConstraints);

        btbHabitacion.setBackground(new java.awt.Color(42, 53, 66));
        btbHabitacion.setText("Habitación");
        btbHabitacion.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btbHabitacion.setFocusPainted(false);
        btbHabitacion.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btbHabitacion.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btbHabitacion.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btbHabitacion.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.HOTEL);
        btbHabitacion.setOpaque(true);
        btbHabitacion.setSizeIcon(25.0F);
        btbHabitacion.setVerifyInputWhenFocusTarget(false);
        btbHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbHabitacionActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        modulos.add(btbHabitacion, gridBagConstraints);

        btnReportes.setBackground(new java.awt.Color(42, 53, 66));
        btnReportes.setText("Reportes");
        btnReportes.setActionCommand("Kardex");
        btnReportes.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnReportes.setFocusPainted(false);
        btnReportes.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnReportes.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnReportes.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnReportes.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ASSIGNMENT);
        btnReportes.setSizeIcon(25.0F);
        btnReportes.setVerifyInputWhenFocusTarget(false);
        btnReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReportesActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        modulos.add(btnReportes, gridBagConstraints);

        btnUsuario.setBackground(new java.awt.Color(42, 53, 66));
        btnUsuario.setText("Usuarios");
        btnUsuario.setActionCommand("Kardex");
        btnUsuario.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnUsuario.setFocusPainted(false);
        btnUsuario.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnUsuario.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PEOPLE);
        btnUsuario.setSizeIcon(25.0F);
        btnUsuario.setVerifyInputWhenFocusTarget(false);
        btnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuarioActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        modulos.add(btnUsuario, gridBagConstraints);

        btnListRegistro.setBackground(new java.awt.Color(51, 65, 80));
        btnListRegistro.setText("Listado de Registros");
        btnListRegistro.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnListRegistro.setFocusPainted(false);
        btnListRegistro.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnListRegistro.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnListRegistro.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnListRegistro.setIconTextGap(10);
        btnListRegistro.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHEVRON_RIGHT);
        btnListRegistro.setOpaque(true);
        btnListRegistro.setSizeIcon(25.0F);
        btnListRegistro.setVerifyInputWhenFocusTarget(false);
        btnListRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListRegistroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        modulos.add(btnListRegistro, gridBagConstraints);

        aside.add(modulos);

        getContentPane().add(aside, java.awt.BorderLayout.LINE_START);

        header.setBackground(new java.awt.Color(61, 137, 248));
        header.setLayout(new java.awt.GridBagLayout());

        jLabel10.setBackground(new java.awt.Color(61, 137, 248));
        jLabel10.setFont(new java.awt.Font("Bahnschrift", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Sistema de Gestión Hotelera | Panel de Control");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.weightx = 10.0;
        header.add(jLabel10, gridBagConstraints);

        btnMenu.setBackground(new java.awt.Color(61, 137, 248));
        btnMenu.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnMenu.setFocusPainted(false);
        btnMenu.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MENU);
        btnMenu.setSizeIcon(25.0F);
        btnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        header.add(btnMenu, gridBagConstraints);

        getContentPane().add(header, java.awt.BorderLayout.PAGE_START);

        body.setBackground(new java.awt.Color(236, 240, 245));
        body.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        body.setForeground(new java.awt.Color(204, 204, 204));
        body.setLayout(new javax.swing.BoxLayout(body, javax.swing.BoxLayout.LINE_AXIS));
        getContentPane().add(body, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuActionPerformed
        if(menuActived == true){
            menuActived = false;
            this.aside.setVisible(false);
        }else{
            menuActived = true;
            this.aside.setVisible(true);
        }
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnTipoHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTipoHabActionPerformed
        resetMenu();
        this.btnTipoHab.setBackground(new Color(241,123,55));
    }//GEN-LAST:event_btnTipoHabActionPerformed

    public void resetMenu(){
        this.btnDash.setBackground(new Color(42,53,66));
        this.btnProducto.setBackground(new Color(42,53,66));
        this.btnTipoHab.setBackground(new Color(42,53,66));
        this.btnRecepcion.setBackground(new Color(42,53,66));
    }
    
    private void btnDashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashActionPerformed
        resetMenu();
        this.btnDash.setBackground(new Color(241,123,55));
    }//GEN-LAST:event_btnDashActionPerformed

    private void btnProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductoActionPerformed
        resetMenu();
        this.btnProducto.setBackground(new Color(241,123,55));
    }//GEN-LAST:event_btnProductoActionPerformed

    private void btnConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigActionPerformed

    }//GEN-LAST:event_btnConfigActionPerformed

    private void btnRecepcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecepcionActionPerformed
        resetMenu();
        this.btnRecepcion.setBackground(new Color(241,123,55));
    }//GEN-LAST:event_btnRecepcionActionPerformed

    private void btnHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHabActionPerformed

    private void btbHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbHabitacionActionPerformed
        if(despleg != true){
            this.btnListRegistro.setVisible(true);
            this.btnHab.setVisible(true);
            this.btnTipoHab.setVisible(true);
            despleg = true;
        }else{
            this.btnListRegistro.setVisible(false);
            this.btnHab.setVisible(false);
            this.btnTipoHab.setVisible(false);
            despleg = false;
        }
        
    }//GEN-LAST:event_btbHabitacionActionPerformed

    private void btnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnReportesActionPerformed

    private void btnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUsuarioActionPerformed

    private void btnListRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnListRegistroActionPerformed

    
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Menu().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel aside;
    public javax.swing.JPanel body;
    private newscomponents.RSButtonIcon_new btbHabitacion;
    public newscomponents.RSButtonIcon_new btnConfig;
    public newscomponents.RSButtonIcon_new btnDash;
    private newscomponents.RSButtonIcon_new btnHab;
    private newscomponents.RSButtonIcon_new btnListRegistro;
    private RSMaterialComponent.RSButtonIconOne btnMenu;
    public newscomponents.RSButtonIcon_new btnProducto;
    public newscomponents.RSButtonIcon_new btnRecepcion;
    public newscomponents.RSButtonIcon_new btnReportes;
    private newscomponents.RSButtonIcon_new btnTipoHab;
    public newscomponents.RSButtonIcon_new btnUsuario;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    public javax.swing.JPanel modulos;
    // End of variables declaration//GEN-END:variables
}
