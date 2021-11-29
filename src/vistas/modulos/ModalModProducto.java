package vistas.modulos;

import controlador.Controlador;

public class ModalModProducto extends javax.swing.JDialog {
    
    
    public ModalModProducto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        btnModProducto.setActionCommand("btnModProducto");
        btnGenerarRep.setActionCommand("ReportePro");
    }
    
    public void setControlador(Controlador control){
        btnModProducto.addActionListener(control);
        this.tbModP.addMouseListener(control);
        this.btnGenerarRep.addActionListener(control);
    }
    
    public void iniciar(){
        this.setVisible(true);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        form = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        body = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfNomP = new javax.swing.JTextField();
        tfPrecio = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnModProducto = new newscomponents.RSButtonIcon_new();
        btnGenerarRep = new newscomponents.RSButtonIcon_new();
        body1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbModP = new rojerusan.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        form.setBackground(new java.awt.Color(255, 255, 255));

        header.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        header.setForeground(new java.awt.Color(51, 51, 51));
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("Listado de Productos");
        header.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));

        body.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("PRECIO: ($)");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("NOMBRE DE PRODUCTO:");

        tfNomP.setBackground(new java.awt.Color(255, 255, 255));
        tfNomP.setForeground(new java.awt.Color(0, 0, 0));
        tfNomP.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        tfNomP.setCaretColor(new java.awt.Color(0, 0, 0));
        tfNomP.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        tfNomP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomPActionPerformed(evt);
            }
        });
        tfNomP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNomPKeyTyped(evt);
            }
        });

        tfPrecio.setBackground(new java.awt.Color(255, 255, 255));
        tfPrecio.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        tfPrecio.setCaretColor(new java.awt.Color(0, 0, 0));
        tfPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPrecioActionPerformed(evt);
            }
        });
        tfPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfPrecioKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bodyLayout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfNomP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(tfPrecio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addGap(184, 184, 184))
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addComponent(tfNomP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(tfPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bodyLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel5)))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        btnModProducto.setBackground(new java.awt.Color(61, 137, 248));
        btnModProducto.setText("Guardar cambios");
        btnModProducto.setBackgroundHover(new java.awt.Color(39, 116, 229));
        btnModProducto.setFocusPainted(false);
        btnModProducto.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnModProducto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnModProducto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnModProducto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SAVE);
        btnModProducto.setOpaque(true);
        btnModProducto.setSizeIcon(22.0F);
        btnModProducto.setVerifyInputWhenFocusTarget(false);
        btnModProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModProductoActionPerformed(evt);
            }
        });

        btnGenerarRep.setBackground(new java.awt.Color(61, 137, 248));
        btnGenerarRep.setText("Generar reporte");
        btnGenerarRep.setBackgroundHover(new java.awt.Color(39, 116, 229));
        btnGenerarRep.setFocusPainted(false);
        btnGenerarRep.setFont(new java.awt.Font("Bahnschrift", 0, 16)); // NOI18N
        btnGenerarRep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGenerarRep.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnGenerarRep.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FILE_UPLOAD);
        btnGenerarRep.setOpaque(true);
        btnGenerarRep.setSizeIcon(22.0F);
        btnGenerarRep.setVerifyInputWhenFocusTarget(false);
        btnGenerarRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarRepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(btnModProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125)
                .addComponent(btnGenerarRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerarRep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        body1.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setOpaque(false);

        tbModP.setBackground(new java.awt.Color(255, 255, 255));
        tbModP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(249, 249, 249)));
        tbModP.setForeground(new java.awt.Color(255, 255, 255));
        tbModP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Producto", "Precio Unitario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbModP.setAltoHead(30);
        tbModP.setColorBackgoundHead(new java.awt.Color(249, 249, 249));
        tbModP.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tbModP.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tbModP.setColorFilasBackgound2(new java.awt.Color(249, 249, 249));
        tbModP.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tbModP.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tbModP.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tbModP.setColorSelBackgound(new java.awt.Color(240, 240, 240));
        tbModP.setColorSelForeground(new java.awt.Color(51, 51, 51));
        tbModP.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tbModP.setFuenteFilas(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tbModP.setFuenteFilasSelect(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tbModP.setFuenteHead(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tbModP.setGridColor(new java.awt.Color(255, 255, 255));
        tbModP.setGrosorBordeFilas(0);
        tbModP.setGrosorBordeHead(0);
        tbModP.setMultipleSeleccion(false);
        tbModP.setRowHeight(40);
        tbModP.getTableHeader().setResizingAllowed(false);
        tbModP.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbModP);

        javax.swing.GroupLayout body1Layout = new javax.swing.GroupLayout(body1);
        body1.setLayout(body1Layout);
        body1Layout.setHorizontalGroup(
            body1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, body1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        body1Layout.setVerticalGroup(
            body1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(body1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout formLayout = new javax.swing.GroupLayout(form);
        form.setLayout(formLayout);
        formLayout.setHorizontalGroup(
            formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(formLayout.createSequentialGroup()
                .addGroup(formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(body1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        formLayout.setVerticalGroup(
            formLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(body1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(form, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfNomPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfNomPActionPerformed

    private void tfNomPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNomPKeyTyped
        char val = evt.getKeyChar();
        if((val<'a' || val>'z') && (val < 'A' || val > 'Z') && (val < ' ')) evt.consume();
    }//GEN-LAST:event_tfNomPKeyTyped

    private void tfPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPrecioActionPerformed

    private void tfPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfPrecioKeyTyped
        char val = evt.getKeyChar();
        if((val < '0' || val > '9') && (val!='.')) evt.consume();
    }//GEN-LAST:event_tfPrecioKeyTyped

    private void btnModProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModProductoActionPerformed

    }//GEN-LAST:event_btnModProductoActionPerformed

    private void btnGenerarRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarRepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGenerarRepActionPerformed

    
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
    public javax.swing.JPanel body;
    public javax.swing.JPanel body1;
    public newscomponents.RSButtonIcon_new btnGenerarRep;
    public newscomponents.RSButtonIcon_new btnModProducto;
    public javax.swing.JPanel form;
    public javax.swing.JLabel header;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    public rojerusan.RSTableMetro tbModP;
    public javax.swing.JTextField tfNomP;
    public javax.swing.JTextField tfPrecio;
    // End of variables declaration//GEN-END:variables
}
