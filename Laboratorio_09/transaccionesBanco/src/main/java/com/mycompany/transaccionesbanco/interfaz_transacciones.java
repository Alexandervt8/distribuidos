package com.mycompany.transaccionesbanco;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class interfaz_transacciones extends javax.swing.JFrame {

    public interfaz_transacciones() {
        initComponents();
        actualizarTablaTransacciones();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "id_remitente", "id_receptor", "monto", "fecha"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Remitente");

        jLabel2.setText("Receptor");

        jLabel3.setText("Monto");

        jButton1.setText("Hacer transaccion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(jTextField2)
                            .addComponent(jTextField3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jButton1)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        hacerTransaccion();
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void hacerTransaccion() {
        int idRemitente = Integer.parseInt(jTextField1.getText());
        int idReceptor = Integer.parseInt(jTextField2.getText());
        double monto = Double.parseDouble(jTextField3.getText());

        try (Connection connection = Database.getConnection()) {
            connection.setAutoCommit(false);

            // Verificar saldo del remitente
            String sqlSaldoRemitente = "SELECT saldo FROM cuentas WHERE id = ?";
            PreparedStatement stmtSaldoRemitente = connection.prepareStatement(sqlSaldoRemitente);
            stmtSaldoRemitente.setInt(1, idRemitente);
            ResultSet rsRemitente = stmtSaldoRemitente.executeQuery();

            if (rsRemitente.next()) {
                double saldoRemitente = rsRemitente.getDouble("saldo");
                if (saldoRemitente >= monto) {
                    // Deduce saldo del remitente
                    String sqlDeduceSaldo = "UPDATE cuentas SET saldo = saldo - ? WHERE id = ?";
                    PreparedStatement stmtDeduceSaldo = connection.prepareStatement(sqlDeduceSaldo);
                    stmtDeduceSaldo.setDouble(1, monto);
                    stmtDeduceSaldo.setInt(2, idRemitente);
                    stmtDeduceSaldo.executeUpdate();

                    // Agregar saldo al receptor
                    String sqlAgregarSaldo = "UPDATE cuentas SET saldo = saldo + ? WHERE id = ?";
                    PreparedStatement stmtAgregarSaldo = connection.prepareStatement(sqlAgregarSaldo);
                    stmtAgregarSaldo.setDouble(1, monto);
                    stmtAgregarSaldo.setInt(2, idReceptor);
                    stmtAgregarSaldo.executeUpdate();

                    // Registrar la transacción
                    String sqlTransaccion = "INSERT INTO transacciones (id_remitente, id_receptor, monto, fecha) VALUES (?, ?, ?, NOW())";
                    PreparedStatement stmtTransaccion = connection.prepareStatement(sqlTransaccion);
                    stmtTransaccion.setInt(1, idRemitente);
                    stmtTransaccion.setInt(2, idReceptor);
                    stmtTransaccion.setDouble(3, monto);
                    stmtTransaccion.executeUpdate();

                    connection.commit();
                    JOptionPane.showMessageDialog(this, "Transacción realizada exitosamente");
                    actualizarTablaTransacciones();
                } else {
                    JOptionPane.showMessageDialog(this, "El remitente no tiene suficiente saldo");
                    connection.rollback();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Remitente no encontrado");
                connection.rollback();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al realizar la transacción: " + e.getMessage());
        }
    }

    private void actualizarTablaTransacciones() {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM transacciones";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            while (resultSet.next()) {
                model.addRow(new Object[]{
                    resultSet.getInt("id"),
                    resultSet.getInt("id_remitente"),
                    resultSet.getInt("id_receptor"),
                    resultSet.getDouble("monto"),
                    resultSet.getTimestamp("fecha")
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar la tabla de transacciones: " + e.getMessage());
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new interfaz_transacciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}
