package vistas.modulos;

import controlador.Controlador;

public class VistaRegistro extends javax.swing.JPanel {

    public VistaRegistro() {
        initComponents();
        btnGuardarRegistro.setActionCommand("guardarRegistro");
        btnVerificarRegistro.setActionCommand("verificarRegistro");
        btnAddHuesped.setActionCommand("AgregarHuesped");
        btnGuardarRegistro.setEnabled(false);
    }

    public void setControlador(Controlador control) {
        this.btnGuardarRegistro.addActionListener(control);
        this.cbEstado.addItemListener(control);
        this.btnVerificarRegistro.addActionListener(control);
        this.txtDescuento.addKeyListener(control);
        this.btnAddHuesped.addActionListener(control);
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbPrecio = new javax.swing.JLabel();
        lbDescrip = new javax.swing.JLabel();
        lbNumHab = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbTipoHab = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbHuesped = new RSMaterialComponent.RSComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        fechaEntrada = new newscomponents.RSDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtDescuento = new RSMaterialComponent.RSTextFieldIconOne();
        txtAdelanto = new RSMaterialComponent.RSTextFieldIconOne();
        btnAddHuesped = new RSMaterialComponent.RSButtonIconOne();
        cbEstado = new RSMaterialComponent.RSComboBox();
        jLabel13 = new javax.swing.JLabel();
        fechaSalida = new newscomponents.RSDateChooser();
        jLabel14 = new javax.swing.JLabel();
        txtTotalPagar = new RSMaterialComponent.RSTextFieldOne();
        jLabel15 = new javax.swing.JLabel();
        txtTotalConDescuento = new RSMaterialComponent.RSTextFieldOne();
        btnGuardarRegistro = new newscomponents.RSButtonIcon_new();
        btnCulminarRegistro = new newscomponents.RSButtonIcon_new();
        btnVerificarRegistro = new newscomponents.RSButtonIcon_new();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        habPanel.setBackground(new java.awt.Color(255, 255, 255));
        habPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(210, 214, 222)));
        habPanel.setAutoscrolls(true);
        habPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEmptyBorder(35, 35, 35, 35), new javax.swing.border.LineBorder(new java.awt.Color(102, 204, 255), 1, true)));
        jPanel1.setForeground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DETALLES DE HABITACIÓN");
        jLabel1.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 10, 15);
        jPanel1.add(jLabel1, gridBagConstraints);

        lbEstado.setBackground(new java.awt.Color(255, 255, 255));
        lbEstado.setForeground(new java.awt.Color(51, 51, 51));
        lbEstado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbEstado.setText("-");
        lbEstado.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 15, 15);
        jPanel1.add(lbEstado, gridBagConstraints);

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("PRECIO:");
        jLabel4.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("ESTADO:");
        jLabel5.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanel1.add(jLabel5, gridBagConstraints);

        lbPrecio.setForeground(new java.awt.Color(51, 51, 51));
        lbPrecio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbPrecio.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanel1.add(lbPrecio, gridBagConstraints);

        lbDescrip.setBackground(new java.awt.Color(255, 255, 255));
        lbDescrip.setForeground(new java.awt.Color(51, 51, 51));
        lbDescrip.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDescrip.setText("-");
        lbDescrip.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 15, 10);
        jPanel1.add(lbDescrip, gridBagConstraints);

        lbNumHab.setBackground(new java.awt.Color(255, 255, 255));
        lbNumHab.setForeground(new java.awt.Color(51, 51, 51));
        lbNumHab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNumHab.setText("-");
        lbNumHab.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 10);
        jPanel1.add(lbNumHab, gridBagConstraints);

        jLabel7.setBackground(new java.awt.Color(153, 153, 153));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("N° DE HABITACIÓN:");
        jLabel7.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 10);
        jPanel1.add(jLabel7, gridBagConstraints);

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("DESCRIPCIÓN:");
        jLabel6.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 10);
        jPanel1.add(jLabel6, gridBagConstraints);

        jLabel8.setBackground(new java.awt.Color(153, 153, 153));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("TIPO DE HABITACIÓN:");
        jLabel8.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(jLabel8, gridBagConstraints);

        lbTipoHab.setForeground(new java.awt.Color(51, 51, 51));
        lbTipoHab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTipoHab.setText("-");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 20;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        jPanel1.add(lbTipoHab, gridBagConstraints);

        habPanel.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("HUESPED");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 5, 10);
        jPanel2.add(jLabel2, gridBagConstraints);

        cbHuesped.setForeground(new java.awt.Color(0, 0, 0));
        cbHuesped.setColorArrow(new java.awt.Color(61, 137, 248));
        cbHuesped.setColorBorde(new java.awt.Color(204, 204, 204));
        cbHuesped.setColorFondo(new java.awt.Color(255, 255, 255));
        cbHuesped.setColorSeleccion(new java.awt.Color(61, 137, 248));
        cbHuesped.setColorSeleccionTXT(new java.awt.Color(0, 0, 0));
        cbHuesped.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbHuesped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbHuespedActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 5, 10);
        jPanel2.add(cbHuesped, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("TIPO DE REGISTRO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("FECHA DE ENTRADA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 5, 10);
        jPanel2.add(jLabel10, gridBagConstraints);

        fechaEntrada.setBackground(new java.awt.Color(153, 153, 153));
        fechaEntrada.setBgColor(new java.awt.Color(153, 153, 153));
        fechaEntrada.setEnabled(false);
        fechaEntrada.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fechaEntrada.setFormatDate("dd/MM/yyyy\n");
        fechaEntrada.setRequestFocusEnabled(false);
        fechaEntrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                fechaEntradaMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 5, 10);
        jPanel2.add(fechaEntrada, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("DESCUENTO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 5, 5);
        jPanel2.add(jLabel11, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("ADELANTO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel2.add(jLabel12, gridBagConstraints);

        txtDescuento.setForeground(new java.awt.Color(0, 0, 0));
        txtDescuento.setBorderColor(new java.awt.Color(204, 204, 204));
        txtDescuento.setColorIcon(new java.awt.Color(153, 153, 153));
        txtDescuento.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        txtDescuento.setPlaceholder("");
        txtDescuento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescuentoKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 30, 5, 5);
        jPanel2.add(txtDescuento, gridBagConstraints);

        txtAdelanto.setForeground(new java.awt.Color(0, 0, 0));
        txtAdelanto.setBorderColor(new java.awt.Color(204, 204, 204));
        txtAdelanto.setColorIcon(new java.awt.Color(153, 153, 153));
        txtAdelanto.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.MONETIZATION_ON);
        txtAdelanto.setPlaceholder("");
        txtAdelanto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdelantoActionPerformed(evt);
            }
        });
        txtAdelanto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAdelantoKeyTyped(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 10);
        jPanel2.add(txtAdelanto, gridBagConstraints);

        btnAddHuesped.setBackground(new java.awt.Color(61, 137, 248));
        btnAddHuesped.setText("addHuesped");
        btnAddHuesped.setActionCommand("nuevoHuesped");
        btnAddHuesped.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.PERSON_ADD);
        btnAddHuesped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddHuespedActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, -10, 5, 5);
        jPanel2.add(btnAddHuesped, gridBagConstraints);

        cbEstado.setForeground(new java.awt.Color(0, 0, 0));
        cbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "HOSPEDAJE", "RESERVACION" }));
        cbEstado.setColorArrow(new java.awt.Color(61, 137, 248));
        cbEstado.setColorBorde(new java.awt.Color(255, 255, 255));
        cbEstado.setColorFondo(new java.awt.Color(255, 255, 255));
        cbEstado.setColorSeleccion(new java.awt.Color(61, 137, 248));
        cbEstado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cbEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEstadoItemStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(cbEstado, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("FECHA DE SALIDA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(jLabel13, gridBagConstraints);

        fechaSalida.setBackground(new java.awt.Color(153, 153, 153));
        fechaSalida.setBgColor(new java.awt.Color(153, 153, 153));
        fechaSalida.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        fechaSalida.setFormatDate("dd/MM/yyyy");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(fechaSalida, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("TOTAL A PAGAR");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(jLabel14, gridBagConstraints);

        txtTotalPagar.setEditable(false);
        txtTotalPagar.setForeground(new java.awt.Color(0, 0, 0));
        txtTotalPagar.setBorderColor(new java.awt.Color(153, 153, 153));
        txtTotalPagar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTotalPagar.setPlaceholder("");
        txtTotalPagar.setSelectionColor(new java.awt.Color(102, 153, 255));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanel2.add(txtTotalPagar, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("TOTAL A PAGAR - DESCUENTO");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(jLabel15, gridBagConstraints);

        txtTotalConDescuento.setEditable(false);
        txtTotalConDescuento.setForeground(new java.awt.Color(0, 0, 0));
        txtTotalConDescuento.setBorderColor(new java.awt.Color(153, 153, 153));
        txtTotalConDescuento.setFocusable(false);
        txtTotalConDescuento.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtTotalConDescuento.setPlaceholder("");
        txtTotalConDescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalConDescuentoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 20);
        jPanel2.add(txtTotalConDescuento, gridBagConstraints);

        btnGuardarRegistro.setBackground(new java.awt.Color(61, 137, 248));
        btnGuardarRegistro.setText("Agregar Registro");
        btnGuardarRegistro.setFocusPainted(false);
        btnGuardarRegistro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardarRegistro.setHideActionText(true);
        btnGuardarRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardarRegistro.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.ADD);
        btnGuardarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRegistroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 30, 100, 40);
        jPanel2.add(btnGuardarRegistro, gridBagConstraints);

        btnCulminarRegistro.setBackground(new java.awt.Color(241, 123, 55));
        btnCulminarRegistro.setText("Culminar Registro");
        btnCulminarRegistro.setBackgroundHover(new java.awt.Color(232, 100, 24));
        btnCulminarRegistro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCulminarRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCulminarRegistro.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CLEAR_ALL);
        btnCulminarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCulminarRegistroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 40, 100, 20);
        jPanel2.add(btnCulminarRegistro, gridBagConstraints);

        btnVerificarRegistro.setBackground(new java.awt.Color(61, 137, 248));
        btnVerificarRegistro.setText("Verificar Datos");
        btnVerificarRegistro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVerificarRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnVerificarRegistro.setIcons(rojeru_san.efectos.ValoresEnum.ICONS.CHECK);
        btnVerificarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerificarRegistroActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 5;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(20, 28, 100, 4);
        jPanel2.add(btnVerificarRegistro, gridBagConstraints);

        habPanel.add(jPanel2, java.awt.BorderLayout.CENTER);

        add(habPanel, java.awt.BorderLayout.CENTER);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(42, 53, 66));
        jLabel3.setText("Recepción / Registro de Hospedaje");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        add(jPanel3, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void txtAdelantoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdelantoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdelantoActionPerformed

    private void btnAddHuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddHuespedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddHuespedActionPerformed

    private void cbHuespedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbHuespedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbHuespedActionPerformed

    private void btnGuardarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRegistroActionPerformed

    }//GEN-LAST:event_btnGuardarRegistroActionPerformed

    private void txtTotalConDescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalConDescuentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalConDescuentoActionPerformed

    private void cbEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEstadoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbEstadoItemStateChanged

    private void fechaEntradaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fechaEntradaMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_fechaEntradaMousePressed

    private void btnCulminarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCulminarRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCulminarRegistroActionPerformed

    private void txtDescuentoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoKeyTyped
        char val = evt.getKeyChar();
        if ((val < '0' || val > '9') && (val != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtDescuentoKeyTyped

    private void txtAdelantoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAdelantoKeyTyped
        char val = evt.getKeyChar();
        if ((val < '0' || val > '9') && (val != '.')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtAdelantoKeyTyped

    private void btnVerificarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerificarRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerificarRegistroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private RSMaterialComponent.RSButtonIconOne btnAddHuesped;
    private newscomponents.RSButtonIcon_new btnCulminarRegistro;
    public newscomponents.RSButtonIcon_new btnGuardarRegistro;
    private newscomponents.RSButtonIcon_new btnVerificarRegistro;
    public RSMaterialComponent.RSComboBox cbEstado;
    public RSMaterialComponent.RSComboBox cbHuesped;
    public newscomponents.RSDateChooser fechaEntrada;
    public newscomponents.RSDateChooser fechaSalida;
    public javax.swing.JPanel habPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JLabel lbDescrip;
    public javax.swing.JLabel lbEstado;
    public javax.swing.JLabel lbNumHab;
    public javax.swing.JLabel lbPrecio;
    public javax.swing.JLabel lbTipoHab;
    public RSMaterialComponent.RSTextFieldIconOne txtAdelanto;
    public RSMaterialComponent.RSTextFieldIconOne txtDescuento;
    public RSMaterialComponent.RSTextFieldOne txtTotalConDescuento;
    public RSMaterialComponent.RSTextFieldOne txtTotalPagar;
    // End of variables declaration//GEN-END:variables
}
