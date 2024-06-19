package e;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conector {
    Connection conectar = null;

    public Connection conexion() {
        try {
            // Correcting the method to connect to the database
            conectar = DriverManager.getConnection("jdbc:mysql://localhost:3308/empresa", "root", "1234");
            // Showing a message dialog to indicate successful connection
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (SQLException e) {
            // Handling SQL exceptions and showing a message dialog with the error message
            JOptionPane.showMessageDialog(null, "Error de la conexion: " + e.getMessage());
        }
        return conectar;
    }
}
