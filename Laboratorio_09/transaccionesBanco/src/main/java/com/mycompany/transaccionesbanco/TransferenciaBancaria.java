
package com.mycompany.transaccionesbanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransferenciaBancaria {

    public static void main(String[] args) {
        int idRemitente = 1;
        int idReceptor = 2;
        double monto = 100.00;

        Connection connection = Database.getConnection();
        PreparedStatement deducirSaldoStmt = null;
        PreparedStatement agregarSaldoStmt = null;
        PreparedStatement registrarTransaccionStmt = null;

        try {
            connection.setAutoCommit(false);

            // Deducir saldo del remitente
            String deducirSaldoSQL = "UPDATE cuentas SET saldo = saldo - ? WHERE id = ?";
            deducirSaldoStmt = connection.prepareStatement(deducirSaldoSQL);
            deducirSaldoStmt.setDouble(1, monto);
            deducirSaldoStmt.setInt(2, idRemitente);
            deducirSaldoStmt.executeUpdate();

            // Agregar saldo al receptor
            String agregarSaldoSQL = "UPDATE cuentas SET saldo = saldo + ? WHERE id = ?";
            agregarSaldoStmt = connection.prepareStatement(agregarSaldoSQL);
            agregarSaldoStmt.setDouble(1, monto);
            agregarSaldoStmt.setInt(2, idReceptor);
            agregarSaldoStmt.executeUpdate();

            // Registrar transacción
            String registrarTransaccionSQL = "INSERT INTO transacciones (id_remitente, id_receptor, monto) VALUES (?, ?, ?)";
            registrarTransaccionStmt = connection.prepareStatement(registrarTransaccionSQL);
            registrarTransaccionStmt.setInt(1, idRemitente);
            registrarTransaccionStmt.setInt(2, idReceptor);
            registrarTransaccionStmt.setDouble(3, monto);
            registrarTransaccionStmt.executeUpdate();

            connection.commit();
            System.out.println("Transferencia realizada exitosamente");

        } catch (SQLException ex) {
            System.err.println("ERROR: " + ex.getMessage());
            if (connection != null) {
                try {
                    System.out.println("Rollback");
                    connection.rollback();
                } catch (SQLException ex1) {
                    System.err.println("No se pudo deshacer: " + ex1.getMessage());
                }
            }
        } finally {
            try {
                if (deducirSaldoStmt != null) deducirSaldoStmt.close();
                if (agregarSaldoStmt != null) agregarSaldoStmt.close();
                if (registrarTransaccionStmt != null) registrarTransaccionStmt.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}

