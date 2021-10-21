/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.main;

import controlador.Controlador;
import javax.swing.JFrame;
import utilidades.TextPrompt;

/**
 *
 * @author walte
 */
public class Vista_hab extends javax.swing.JFrame {

    /**
     * Creates new form Vista_hab
     */
    public Vista_hab() {
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        new TextPrompt("Buscar habitación por: No. de Habitación, Tipo", jTbuscar);
        btncrearReporte.setActionCommand("Reporte");
        btnagregarhab.setActionCommand("Agregar");
    }
    
    public void iniciar(){
        this.setVisible(true);
    }
    
    public void setControlador(Controlador control){
        btnagregarhab.addActionListener(control);
        btncrearReporte.addActionListener(control);
        tbregishab.addMouseListener(control);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTbuscar = new javax.swing.JTextField();
        btnbuscar = new newscomponents.RSButtonIcon_new();
        btnagregarhab = new newscomponents.RSButtonIcon_new();
        btncrearReporte = new newscomponents.RSButtonIcon_new();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbregishab = new rojerusan.RSTableMetro();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(245, 245, 245));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTbuscar.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 204)), javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 0)));
        jTbuscar.setMinimumSize(new java.awt.Dimension(6, 18));
        jTbuscar.setPreferredSize(new java.awt.Dimension(6, 18));
        jTbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTbuscarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTbuscarKeyTyped(evt);
            }
        });

        btnbuscar.setBackground(new java.awt.Color(61, 137, 248));
        btnbuscar.setText("Buscar");
        btnbuscar.setFocusPainted(false);
        btnbuscar.setHideActionText(true);
        btnbuscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnbuscar.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.SEARCH);
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        btnagregarhab.setBackground(new java.awt.Color(61, 137, 248));
        btnagregarhab.setText("Agregar Hab");
        btnagregarhab.setActionCommand("Agregar");
        btnagregarhab.setFocusPainted(false);
        btnagregarhab.setHideActionText(true);
        btnagregarhab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnagregarhab.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnagregarhab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarhabActionPerformed(evt);
            }
        });

        btncrearReporte.setBackground(new java.awt.Color(61, 137, 248));
        btncrearReporte.setText("Crear reporte");
        btncrearReporte.setFocusPainted(false);
        btncrearReporte.setHideActionText(true);
        btncrearReporte.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btncrearReporte.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.FILE_DOWNLOAD);
        btncrearReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncrearReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(543, 543, 543)
                .addComponent(jTbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186)
                .addComponent(btncrearReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnagregarhab, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncrearReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnagregarhab, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Habitación / Registro de Habitación");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setOpaque(false);

        tbregishab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(249, 249, 249)));
        tbregishab.setForeground(new java.awt.Color(255, 255, 255));
        tbregishab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id. de Habitación", "No. de Habitación", "Descripción", "Precio", "Tipo ", "Disposición", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbregishab.setAltoHead(30);
        tbregishab.setColorBackgoundHead(new java.awt.Color(249, 249, 249));
        tbregishab.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tbregishab.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tbregishab.setColorFilasBackgound2(new java.awt.Color(249, 249, 249));
        tbregishab.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tbregishab.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tbregishab.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tbregishab.setColorSelForeground(new java.awt.Color(51, 51, 51));
        tbregishab.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tbregishab.setFuenteFilas(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tbregishab.setFuenteFilasSelect(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tbregishab.setFuenteHead(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tbregishab.setGridColor(new java.awt.Color(255, 255, 255));
        tbregishab.setGrosorBordeFilas(0);
        tbregishab.setGrosorBordeHead(0);
        tbregishab.setMultipleSeleccion(false);
        tbregishab.setRowHeight(40);
        tbregishab.getTableHeader().setResizingAllowed(false);
        tbregishab.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tbregishab);
        if (tbregishab.getColumnModel().getColumnCount() > 0) {
            tbregishab.getColumnModel().getColumn(0).setResizable(false);
            tbregishab.getColumnModel().getColumn(1).setResizable(false);
            tbregishab.getColumnModel().getColumn(2).setResizable(false);
            tbregishab.getColumnModel().getColumn(3).setResizable(false);
            tbregishab.getColumnModel().getColumn(4).setResizable(false);
            tbregishab.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTbuscarKeyReleased

    }//GEN-LAST:event_jTbuscarKeyReleased

    private void jTbuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTbuscarKeyTyped
        char val = evt.getKeyChar();
        if((val<'a' || val>'z') && (val<'A' || val>'Z') && (val != ' ') && (val !='ñ')&& (val !='Ñ')&&(val<'á'|| val>'ú')&&(val<'Á'||val>'Ú')){
            evt.consume();
        }
    }//GEN-LAST:event_jTbuscarKeyTyped

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        //JOptionPane.showMessageDialog(null, "AQUI OBVIAMENTE FUNCIONA");
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btnagregarhabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarhabActionPerformed
        
    }//GEN-LAST:event_btnagregarhabActionPerformed

    private void btncrearReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncrearReporteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncrearReporteActionPerformed

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
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Vista_hab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Vista_hab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Vista_hab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Vista_hab.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Vista_hab().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public newscomponents.RSButtonIcon_new btnagregarhab;
    public newscomponents.RSButtonIcon_new btnbuscar;
    public newscomponents.RSButtonIcon_new btncrearReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTbuscar;
    public rojerusan.RSTableMetro tbregishab;
    // End of variables declaration//GEN-END:variables
}
