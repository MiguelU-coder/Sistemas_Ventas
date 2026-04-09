/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.interfaces;

import java.util.List;

public interface CrudSimpleInterface<T> {
   public List<T> listar(String texto);
   public boolean insertar (T obj);
   public boolean actualizar(T obj);
   public boolean desactivar (int id);
   public int total();
   public boolean existe(String texto);
}
