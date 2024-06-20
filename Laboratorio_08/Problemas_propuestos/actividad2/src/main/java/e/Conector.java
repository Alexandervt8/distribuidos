package e;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conector {
    
    public Connection conexion() {
        String url = "jdbc:mysql://localhost:3306/empresa";
        String user = "root";
        String password = "123456";

        Connection conectar = null;

        try {
            // Registrar el driver
            //Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conectar = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa!");
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
        } catch (SQLException e) {
            // Handling SQL exceptions and showing a message dialog with the error message
            JOptionPane.showMessageDialog(null, "Error de la conexion: " + e.getMessage());
        }
        return conectar;
    }
}
