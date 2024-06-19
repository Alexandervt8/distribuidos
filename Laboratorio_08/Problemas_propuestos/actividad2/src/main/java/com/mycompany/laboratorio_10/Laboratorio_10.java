package com.mycompany.laboratorio_10;

import java.sql.*;

public class Laboratorio_10 {
static String user="root";
static String pass="1234";
static String url="jdbc:odbc:base";
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Connection cnn=null;
        try{
            System.out.println("llegue aqui");
        Class.forName("com.jdbc.odbc.JdbcOdbcDriver").newInstance();
        System.out.println("ahora aqui");
        
        cnn=DriverManager.getConnection(url, user, pass);
        
        if(cnn!=null){
        System.out.println("Se conecto a la base de datos");
        }else{System.out.println("No se conecto a la base de datos");}
        }catch(ClassNotFoundException cnfex){
        cnfex.printStackTrace();
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }    
}
