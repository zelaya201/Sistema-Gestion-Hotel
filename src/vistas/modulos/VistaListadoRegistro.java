package vistas.modulos;

import controlador.Controlador;

public class VistaListadoRegistro extends javax.swing.JPanel {

    public VistaListadoRegistro() {
        initComponents();
    }
    
    public void setControlador(Controlador control){
        
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
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaHabitaciones = new rojerusan.RSTableMetro();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        habPanel.setBackground(new java.awt.Color(255, 255, 255));
        habPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(4, 0, 0, 0, new java.awt.Color(210, 214, 222)));
        habPanel.setAutoscrolls(true);
        habPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setViewportBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jScrollPane2.setOpaque(false);

        tablaHabitaciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(249, 249, 249)));
        tablaHabitaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id de Registro", "N° de Habitación", "Descripción", "Cliente", "Recepcionista", "Tipo", "Estado", "Fecha de Entrada", "Fecha de Salida", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaHabitaciones.setAltoHead(30);
        tablaHabitaciones.setColorBackgoundHead(new java.awt.Color(249, 249, 249));
        tablaHabitaciones.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        tablaHabitaciones.setColorBordeHead(new java.awt.Color(255, 255, 255));
        tablaHabitaciones.setColorFilasBackgound2(new java.awt.Color(249, 249, 249));
        tablaHabitaciones.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        tablaHabitaciones.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        tablaHabitaciones.setColorForegroundHead(new java.awt.Color(0, 0, 0));
        tablaHabitaciones.setColorSelForeground(new java.awt.Color(51, 51, 51));
        tablaHabitaciones.setFont(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaHabitaciones.setFuenteFilas(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaHabitaciones.setFuenteFilasSelect(new java.awt.Font("Calibri Light", 0, 14)); // NOI18N
        tablaHabitaciones.setFuenteHead(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        tablaHabitaciones.setGridColor(new java.awt.Color(255, 255, 255));
        tablaHabitaciones.setGrosorBordeFilas(0);
        tablaHabitaciones.setGrosorBordeHead(0);
        tablaHabitaciones.setMultipleSeleccion(false);
        tablaHabitaciones.setRowHeight(40);
        tablaHabitaciones.getTableHeader().setResizingAllowed(false);
        tablaHabitaciones.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tablaHabitaciones);
        if (tablaHabitaciones.getColumnModel().getColumnCount() > 0) {
            tablaHabitaciones.getColumnModel().getColumn(0).setResizable(false);
            tablaHabitaciones.getColumnModel().getColumn(0).setPreferredWidth(20);
            tablaHabitaciones.getColumnModel().getColumn(1).setResizable(false);
            tablaHabitaciones.getColumnModel().getColumn(1).setPreferredWidth(20);
            tablaHabitaciones.getColumnModel().getColumn(2).setResizable(false);
            tablaHabitaciones.getColumnModel().getColumn(3).setResizable(false);
            tablaHabitaciones.getColumnModel().getColumn(4).setResizable(false);
            tablaHabitaciones.getColumnModel().getColumn(5).setResizable(false);
            tablaHabitaciones.getColumnModel().getColumn(5).setPreferredWidth(10);
            tablaHabitaciones.getColumnModel().getColumn(6).setResizable(false);
            tablaHabitaciones.getColumnModel().getColumn(6).setPreferredWidth(10);
            tablaHabitaciones.getColumnModel().getColumn(7).setResizable(false);
            tablaHabitaciones.getColumnModel().getColumn(7).setPreferredWidth(10);
            tablaHabitaciones.getColumnModel().getColumn(8).setResizable(false);
            tablaHabitaciones.getColumnModel().getColumn(8).setPreferredWidth(10);
            tablaHabitaciones.getColumnModel().getColumn(9).setResizable(false);
        }

        habPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(habPanel, java.awt.BorderLayout.CENTER);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.GridBagLayout());

        jLabel3.setBackground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Bahnschrift", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(42, 53, 66));
        jLabel3.setText("Habitación / Listado de Registros");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 20.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 0, 15, 0);
        jPanel3.add(jLabel3, gridBagConstraints);

        add(jPanel3, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel habPanel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    public rojerusan.RSTableMetro tablaHabitaciones;
    // End of variables declaration//GEN-END:variables
}