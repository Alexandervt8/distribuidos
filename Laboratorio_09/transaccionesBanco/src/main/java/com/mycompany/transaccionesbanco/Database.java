package com.mycompany.transaccionesbanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private final static String bd = "miBaseDeDatos";
    private final static String login = "root";
    private final static String password = "123456";
    private final static String url = "jdbc:mysql://localhost/" + bd;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, login, password);
            if (conn != null) {
                System.out.println("Conectado a la base de datos [" + bd + "]");
            }
            return conn;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}

