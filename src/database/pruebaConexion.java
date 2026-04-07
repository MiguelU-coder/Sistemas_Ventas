/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

/**
 *
 * @author MiguelAngelMolinaPue
 */
public class pruebaConexion {
    public static void main(String[] args) {
        conexion con = new conexion();
        con.conectar();
        if (con.cadena != null) {
            System.out.println("Conectado");
        } else {
            System.out.println("Desconectado");
        }
    }
}
