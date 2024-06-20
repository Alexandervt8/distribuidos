package com.mycompany.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Base {

    public static void main(String[] args) {
        System.out.println("Programa usando conector ODBC");
        String url = "jdbc:mysql://localhost:3306/empresamsql";
        String user = "root";
        String password = "123456";

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Registrar el driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa!");

            // Crear un statement y ejecutar una consulta
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Productos");

            // Procesar los resultados
            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3)+ " | " + rs.getString(4) + " | " + rs.getString(5));
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Cerrar los recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

