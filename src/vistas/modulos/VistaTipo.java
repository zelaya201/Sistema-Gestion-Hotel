package vistas.modulos;

import controlador.Controlador;

public class VistaTipo extends javax.swing.JPanel {

    public VistaTipo() {
        initComponents();  
        btnGuardarTipo.setActionCommand("GuardarTipo");
        btnCancelarTipo.setActionCommand("CancelarTipo");
        
    }
    
    public void setControlador(Controlador control){
        this.btnGuardarTipo.addActionListener(control);
        this.btnCancelarTipo.addActionListener(control);
        
        tablaTiposHab.addMouseListener(control);
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

        habPanel = new javax.swing.JPanel();
        registroTipo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfNombreTipo = new RSMaterialComponent.RSTextFieldMaterial();
        jLabel4 = new javax.swing.JLabel();
        tfCantidadTipo = new RSMaterialComponent.RSTextFieldMaterial();
        btnCancelarTipo = new newscomponents.RSButtonIcon_new();
        btnGuardarTipo = new newscomponents.RSButtonIcon_new();
        tablaTipos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTiposHab = new rojerusan.RSTableMetro();
        descripcion = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        habPanel.setBackground(new java.awt.Color(255, 255, 255));
        habPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(210, 214, 222)));
        habPanel.setAutoscrolls(true);
        habPanel.setLayout(new java.awt.BorderLayout());

        registroTipo.setBackground(new java.awt.Color(255, 255, 255));
        registroTipo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(35, 35, 35, 35), new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true)));
        registroTipo.setForeground(new java.awt.Color(51, 51, 51));
        registroTipo.setLayout(new java.awt.GridBagLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TIPO DE HABITACIÓN");
        jLabel1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        registroTipo.add(jLabel1, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 5);
        registroTipo.add(jLabel2, gridBagConstraints);

        tfNombreTipo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tfNombreTipo.setPlaceholder("Ej: Doble");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        registroTipo.add(tfNombreTipo, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Cantidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        registroTipo.add(jLabel4, gridBagConstraints);

        tfCantidadTipo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tfCantidadTipo.setPlaceholder("Ej: 2");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 20);
        registroTipo.add(tfCantidadTipo, gridBagConstraints);

        btnCancelarTipo.setBackground(new java.awt.Color(255, 102, 102));
        btnCancelarTipo.setText("Cancelar");
        btnCancelarTipo.setBackgroundHover(new java.awt.Color(61, 137, 248));
        btnCancelarTipo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelarTipo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CANCEL);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 15, 0);
        registroTipo.add(btnCancelarTipo, gridBagConstraints);

        btnGuardarTipo.setBackground(new java.awt.Color(61, 137, 248));
        btnGuardarTipo.setText("Registrar");
        btnGuardarTipo.setFocusPainted(false);
        btnGuardarTipo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarTipo.setHideActionText(true);
        btnGuardarTipo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardarTipo.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnGuardarTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTipoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 5);
        registroTipo.add(btnGuardarTipo, gridBagConstraints);

        habPanel.add(registroTipo, java.awt.BorderLayout.PAGE_START);

        tablaTipos.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setOpaque(false);

        tablaTiposHab.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(249, 249, 249)));
        tablaTiposHab.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nombre", "Cantidad", "Editar", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaTiposHab.setAltoHead(30);
        tablaTiposHab.setColorBackgoundHead(new java.awt.Color(249, 249, 249));
        tablaTiposHab.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tablaTiposHab.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tablaTiposHab.setColorFilasBackgound2(new java.awt.Color(249, 249, 249));
        tablaTiposHab.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tablaTiposHab.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tablaTiposHab.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tablaTiposHab.setColorSelForeground(new java.awt.Color(51, 51, 51));
        tablaTiposHab.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaTiposHab.setFuenteFilas(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaTiposHab.setFuenteFilasSelect(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaTiposHab.setFuenteHead(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tablaTiposHab.setGridColor(new java.awt.Color(255, 255, 255));
        tablaTiposHab.setGrosorBordeFilas(0);
        tablaTiposHab.setGrosorBordeHead(0);
        tablaTiposHab.setMultipleSeleccion(false);
        tablaTiposHab.setRowHeight(40);
        tablaTiposHab.getTableHeader().setResizingAllowed(false);
        tablaTiposHab.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaTiposHab);
        if (tablaTiposHab.getColumnModel().getColumnCount() > 0) {
            tablaTiposHab.getColumnModel().getColumn(0).setResizable(false);
            tablaTiposHab.getColumnModel().getColumn(1).setResizable(false);
            tablaTiposHab.getColumnModel().getColumn(2).setResizable(false);
            tablaTiposHab.getColumnModel().getColumn(3).setResizable(false);
            tablaTiposHab.getColumnModel().getColumn(3).setPreferredWidth(70);
            tablaTiposHab.getColumnModel().getColumn(4).setResizable(false);
            tablaTiposHab.getColumnModel().getColumn(4).setPreferredWidth(70);
        }

        javax.swing.GroupLayout tablaTiposLayout = new javax.swing.GroupLayout(tablaTipos);
        tablaTipos.setLayout(tablaTiposLayout);
        tablaTiposLayout.setHorizontalGroup(
            tablaTiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1268, Short.MAX_VALUE)
        );
        tablaTiposLayout.setVerticalGroup(
            tablaTiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
        );

        habPanel.add(tablaTipos, java.awt.BorderLayout.CENTER);

        add(habPanel, java.awt.BorderLayout.CENTER);

        descripcion.setOpaque(false);
        descripcion.setLayout(new java.awt.GridBagLayout());

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(42, 53, 66));
        jLabel3.setText("Habitación / Nuevo Tipo de Habitación");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 0);
        descripcion.add(jLabel3, gridBagConstraints);

        add(descripcion, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoActionPerformed

    }//GEN-LAST:event_btnGuardarTipoActionPerformed
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public newscomponents.RSButtonIcon_new btnCancelarTipo;
    public newscomponents.RSButtonIcon_new btnGuardarTipo;
    private javax.swing.JPanel descripcion;
    public javax.swing.JPanel habPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel registroTipo;
    private javax.swing.JPanel tablaTipos;
    public rojerusan.RSTableMetro tablaTiposHab;
    public RSMaterialComponent.RSTextFieldMaterial tfCantidadTipo;
    public RSMaterialComponent.RSTextFieldMaterial tfNombreTipo;
    // End of variables declaration//GEN-END:variables
}
