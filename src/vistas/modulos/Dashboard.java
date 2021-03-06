
package vistas.modulos;

public class Dashboard extends javax.swing.JPanel {

    public Dashboard() {
        initComponents();
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

        header = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        article1 = new javax.swing.JPanel();
        totales = new javax.swing.JPanel();
        totalEmpleados = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbTotHab = new javax.swing.JLabel();
        totalUsuarios = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lbTotUsu = new javax.swing.JLabel();
        totalProductos = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbTotProd = new javax.swing.JLabel();
        totalVentas = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbTotFac = new javax.swing.JLabel();
        barChart = new javax.swing.JPanel();
        pChart = new javax.swing.JPanel();
        alertas = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbHabDis = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbHabRes = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lbHabOcu = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        article2 = new javax.swing.JPanel();
        productos = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaHabAgre = new rojerusan.RSTableMetro();
        ventas = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaUltFact = new rojerusan.RSTableMetro();
        jLabel2 = new javax.swing.JLabel();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        header.setOpaque(false);
        header.setLayout(new java.awt.GridBagLayout());

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(42, 53, 66));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/home_22px.png"))); // NOI18N
        jLabel3.setText("Dashboard / home");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 10.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 0);
        header.add(jLabel3, gridBagConstraints);

        add(header, java.awt.BorderLayout.PAGE_START);

        article1.setBackground(new java.awt.Color(255, 255, 255));
        article1.setOpaque(false);
        article1.setLayout(new javax.swing.BoxLayout(article1, javax.swing.BoxLayout.LINE_AXIS));

        totales.setBackground(new java.awt.Color(51, 122, 183));
        totales.setOpaque(false);
        totales.setLayout(new javax.swing.BoxLayout(totales, javax.swing.BoxLayout.PAGE_AXIS));

        totalEmpleados.setBackground(new java.awt.Color(243, 156, 18));
        totalEmpleados.setLayout(new java.awt.GridBagLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bedroom_60px.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        totalEmpleados.add(jLabel12, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Total de Habitaciones");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        totalEmpleados.add(jLabel13, gridBagConstraints);

        lbTotHab.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTotHab.setForeground(new java.awt.Color(255, 255, 255));
        lbTotHab.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        totalEmpleados.add(lbTotHab, gridBagConstraints);

        totales.add(totalEmpleados);

        totalUsuarios.setBackground(new java.awt.Color(0, 166, 90));
        totalUsuarios.setLayout(new java.awt.GridBagLayout());

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/user_group_60px.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        totalUsuarios.add(jLabel18, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Total de Usuarios");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        totalUsuarios.add(jLabel19, gridBagConstraints);

        lbTotUsu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTotUsu.setForeground(new java.awt.Color(255, 255, 255));
        lbTotUsu.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        totalUsuarios.add(lbTotUsu, gridBagConstraints);

        totales.add(totalUsuarios);

        totalProductos.setBackground(new java.awt.Color(0, 192, 239));
        totalProductos.setLayout(new java.awt.GridBagLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/product_60px.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        totalProductos.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Total de Productos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        totalProductos.add(jLabel10, gridBagConstraints);

        lbTotProd.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTotProd.setForeground(new java.awt.Color(255, 255, 255));
        lbTotProd.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        totalProductos.add(lbTotProd, gridBagConstraints);

        totales.add(totalProductos);

        totalVentas.setBackground(new java.awt.Color(51, 122, 183));
        totalVentas.setLayout(new java.awt.GridBagLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/purchase_order_60px.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        totalVentas.add(jLabel6, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Total de Facturas Abiertas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        totalVentas.add(jLabel7, gridBagConstraints);

        lbTotFac.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbTotFac.setForeground(new java.awt.Color(255, 255, 255));
        lbTotFac.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 20);
        totalVentas.add(lbTotFac, gridBagConstraints);

        totales.add(totalVentas);

        article1.add(totales);

        barChart.setOpaque(false);
        barChart.setLayout(new java.awt.GridBagLayout());

        pChart.setBackground(new java.awt.Color(255, 255, 255));
        pChart.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 0, 0, new java.awt.Color(51, 204, 0)));
        pChart.setLayout(new javax.swing.BoxLayout(pChart, javax.swing.BoxLayout.LINE_AXIS));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        barChart.add(pChart, gridBagConstraints);

        article1.add(barChart);

        alertas.setOpaque(false);
        alertas.setLayout(new java.awt.GridBagLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setBackground(new java.awt.Color(0, 166, 90));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Disponibles");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel2.add(jLabel1, gridBagConstraints);

        lbHabDis.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbHabDis.setForeground(new java.awt.Color(255, 255, 255));
        lbHabDis.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 10, 0);
        jPanel2.add(lbHabDis, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bunk_bed_60px.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        jPanel2.add(jLabel17, gridBagConstraints);

        jPanel1.add(jPanel2);

        jPanel3.setBackground(new java.awt.Color(61, 137, 248));
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Reservadas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel3.add(jLabel8, gridBagConstraints);

        lbHabRes.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbHabRes.setForeground(new java.awt.Color(255, 255, 255));
        lbHabRes.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel3.add(lbHabRes, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/hotel_upgrade_60px.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel3.add(jLabel20, gridBagConstraints);

        jPanel1.add(jPanel3);

        jPanel4.setBackground(new java.awt.Color(223, 56, 56));
        jPanel4.setLayout(new java.awt.GridBagLayout());

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Ocupadas");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        jPanel4.add(jLabel11, gridBagConstraints);

        lbHabOcu.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbHabOcu.setForeground(new java.awt.Color(255, 255, 255));
        lbHabOcu.setText("0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        jPanel4.add(lbHabOcu, gridBagConstraints);

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/locker_60px.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        jPanel4.add(jLabel21, gridBagConstraints);

        jPanel1.add(jPanel4);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        alertas.add(jPanel1, gridBagConstraints);

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Habitaciones");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 0, 0, new java.awt.Color(0, 166, 90)));
        jLabel5.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 10, 0, 0);
        alertas.add(jLabel5, gridBagConstraints);

        article1.add(alertas);

        add(article1, java.awt.BorderLayout.CENTER);

        article2.setBackground(new java.awt.Color(255, 255, 255));
        article2.setOpaque(false);
        article2.setLayout(new javax.swing.BoxLayout(article2, javax.swing.BoxLayout.LINE_AXIS));

        productos.setBackground(new java.awt.Color(255, 255, 255));
        productos.setOpaque(false);
        productos.setLayout(new java.awt.GridBagLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Habitaciones disponibles");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 0, 0, new java.awt.Color(204, 204, 204)));
        jLabel4.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 10);
        productos.add(jLabel4, gridBagConstraints);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane3.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane3.setOpaque(false);

        tablaHabAgre.setBackground(new java.awt.Color(255, 255, 255));
        tablaHabAgre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(249, 249, 249)));
        tablaHabAgre.setForeground(new java.awt.Color(255, 255, 255));
        tablaHabAgre.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Numero", "Descripci??n", "Tipo", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaHabAgre.setAltoHead(30);
        tablaHabAgre.setColorBackgoundHead(new java.awt.Color(249, 249, 249));
        tablaHabAgre.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tablaHabAgre.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tablaHabAgre.setColorFilasBackgound2(new java.awt.Color(249, 249, 249));
        tablaHabAgre.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tablaHabAgre.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tablaHabAgre.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tablaHabAgre.setColorSelBackgound(new java.awt.Color(240, 240, 240));
        tablaHabAgre.setColorSelForeground(new java.awt.Color(51, 51, 51));
        tablaHabAgre.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaHabAgre.setFuenteFilas(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaHabAgre.setFuenteFilasSelect(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaHabAgre.setFuenteHead(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tablaHabAgre.setGridColor(new java.awt.Color(255, 255, 255));
        tablaHabAgre.setGrosorBordeFilas(0);
        tablaHabAgre.setGrosorBordeHead(0);
        tablaHabAgre.setMultipleSeleccion(false);
        tablaHabAgre.setRowHeight(40);
        tablaHabAgre.setRowSelectionAllowed(false);
        tablaHabAgre.getTableHeader().setResizingAllowed(false);
        tablaHabAgre.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tablaHabAgre);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 10);
        productos.add(jScrollPane3, gridBagConstraints);

        article2.add(productos);

        ventas.setOpaque(false);
        ventas.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setOpaque(false);

        tablaUltFact.setBackground(new java.awt.Color(255, 255, 255));
        tablaUltFact.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(249, 249, 249)));
        tablaUltFact.setForeground(new java.awt.Color(255, 255, 255));
        tablaUltFact.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No. de Factura", "Cliente", "Fecha", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaUltFact.setAltoHead(30);
        tablaUltFact.setColorBackgoundHead(new java.awt.Color(249, 249, 249));
        tablaUltFact.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tablaUltFact.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tablaUltFact.setColorFilasBackgound2(new java.awt.Color(249, 249, 249));
        tablaUltFact.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tablaUltFact.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tablaUltFact.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tablaUltFact.setColorSelBackgound(new java.awt.Color(240, 240, 240));
        tablaUltFact.setColorSelForeground(new java.awt.Color(51, 51, 51));
        tablaUltFact.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaUltFact.setFuenteFilas(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaUltFact.setFuenteFilasSelect(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaUltFact.setFuenteHead(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tablaUltFact.setGridColor(new java.awt.Color(255, 255, 255));
        tablaUltFact.setGrosorBordeFilas(0);
        tablaUltFact.setGrosorBordeHead(0);
        tablaUltFact.setMultipleSeleccion(false);
        tablaUltFact.setRowHeight(40);
        tablaUltFact.setRowSelectionAllowed(false);
        tablaUltFact.getTableHeader().setResizingAllowed(false);
        tablaUltFact.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaUltFact);
        if (tablaUltFact.getColumnModel().getColumnCount() > 0) {
            tablaUltFact.getColumnModel().getColumn(3).setHeaderValue("Total");
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 5, 0, 0);
        ventas.add(jScrollPane2, gridBagConstraints);

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("??ltimas facturas finalizadas");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 0, 0, new java.awt.Color(102, 200, 239)));
        jLabel2.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 15;
        gridBagConstraints.insets = new java.awt.Insets(10, 5, 0, 0);
        ventas.add(jLabel2, gridBagConstraints);

        article2.add(ventas);

        add(article2, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel alertas;
    public javax.swing.JPanel article1;
    private javax.swing.JPanel article2;
    public javax.swing.JPanel barChart;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JLabel lbHabDis;
    public javax.swing.JLabel lbHabOcu;
    public javax.swing.JLabel lbHabRes;
    public javax.swing.JLabel lbTotFac;
    public javax.swing.JLabel lbTotHab;
    public javax.swing.JLabel lbTotProd;
    public javax.swing.JLabel lbTotUsu;
    public javax.swing.JPanel pChart;
    private javax.swing.JPanel productos;
    public rojerusan.RSTableMetro tablaHabAgre;
    public rojerusan.RSTableMetro tablaUltFact;
    private javax.swing.JPanel totalEmpleados;
    public javax.swing.JPanel totalProductos;
    public javax.swing.JPanel totalUsuarios;
    public javax.swing.JPanel totalVentas;
    public javax.swing.JPanel totales;
    private javax.swing.JPanel ventas;
    // End of variables declaration//GEN-END:variables
}
