package com.mycompany.tareascrud.gui;

import com.mycompany.tareascrud.logica.Controladora;
import com.mycompany.tareascrud.logica.Tarea;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

public class DetallesTarea extends javax.swing.JFrame {
    int numTarea;
    Controladora control = null;

    public DetallesTarea(int numTarea) {
        initComponents();
        this.numTarea = numTarea;
        control = new Controladora();
        cargarDatos();
        cargarTabla();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        labelPropietario = new javax.swing.JLabel();
        labelDescripcion = new javax.swing.JLabel();
        brnSeleccionarArchivo = new javax.swing.JButton();
        labelFechaEntrega = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaArchivos = new javax.swing.JTable();
        btnEditarTarea = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelTitulo.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        labelTitulo.setText("Titulo");

        labelPropietario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelPropietario.setText("Propietario");

        labelDescripcion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelDescripcion.setText("Descripcion");

        brnSeleccionarArchivo.setText("Seleccionar archivo");
        brnSeleccionarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnSeleccionarArchivoActionPerformed(evt);
            }
        });

        labelFechaEntrega.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelFechaEntrega.setText("Fecha de entrega");

        tablaArchivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaArchivos);

        btnEditarTarea.setText("Editar tarea");
        btnEditarTarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarTareaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDescripcion)
                    .addComponent(labelTitulo)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(brnSeleccionarArchivo)
                            .addGap(18, 18, 18)
                            .addComponent(btnEditarTarea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(labelPropietario)
                            .addGap(83, 83, 83)
                            .addComponent(labelFechaEntrega))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(labelTitulo)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPropietario)
                    .addComponent(labelFechaEntrega))
                .addGap(18, 18, 18)
                .addComponent(labelDescripcion)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brnSeleccionarArchivo)
                    .addComponent(btnEditarTarea))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
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

    private void brnSeleccionarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnSeleccionarArchivoActionPerformed
        JFileChooser archivo = new JFileChooser();
        archivo.showOpenDialog(this);
    }//GEN-LAST:event_brnSeleccionarArchivoActionPerformed

    private void btnEditarTareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarTareaActionPerformed
        ModificarTarea pantalla = new ModificarTarea(numTarea);
        pantalla.setVisible(true);
        pantalla.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnEditarTareaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brnSeleccionarArchivo;
    private javax.swing.JButton btnEditarTarea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelDescripcion;
    private javax.swing.JLabel labelFechaEntrega;
    private javax.swing.JLabel labelPropietario;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTable tablaArchivos;
    // End of variables declaration//GEN-END:variables

    private void cargarDatos() {
         Tarea tarea = control.traerTarea(numTarea);
         labelTitulo.setText(tarea.getNombre());
         labelPropietario.setText(tarea.getPropietario().getNombre() + " " + tarea.getPropietario().getApellido());
         labelFechaEntrega.setText(tarea.getFechaEntrega().toString());
         labelDescripcion.setText(tarea.getDescripcion());
    }

    private void cargarTabla() {
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        String titulos[] = {"Nombre", "Opciones"};
        tableModel.setColumnIdentifiers(titulos);
        
        
    }
}
