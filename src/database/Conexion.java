/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author MiguelAngelMolinaPue
 *
 * This class is encharged of
 * connecting the code to the database
 *
 */
public class Conexion {
   private final String DRIVER = "com.mysql.cj.jdbc.Driver";
   private final String URL = "jdbc:mysql://localhost:3308/";
   private final String DB = "sistemaventas";
   private final String USER = "root";
   private final String PASSWORD = "";

   private Connection cadena;
   private static Conexion instancia;

   private Conexion(){
       conectar();
   }

    private void conectar(){
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL + DB, USER, PASSWORD);
            System.out.println("   Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("   ERROR DE CONEXIÓN:");
            System.err.println("   Driver: " + DRIVER);
            System.err.println("   URL: " + URL + DB);
            System.err.println("   Usuario: " + USER);
            System.err.println("   Mensaje: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
            this.cadena = null;
        }
    }

    public Connection obtenerConexion(){
        if (this.cadena == null) {
            JOptionPane.showMessageDialog(null, "No hay conexión a la base de datos");
        }
        return this.cadena;
    }

    public void desconectar(){
        try {
            if (this.cadena != null && !this.cadena.isClosed()) {
                this.cadena.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

   public static Conexion getInstancia(){
       if (instancia == null){
           instancia = new Conexion();
       }
       return instancia;
   }
}
