package data;

import database.Conexion;
import data.interfaces.CrudSimpleInterface;
import entities.Categoria;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CategoriaDAO implements CrudSimpleInterface<Categoria> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public CategoriaDAO(){
        CON = Conexion.getInstancia();
    }
    @Override
    public List<Categoria> listar(String texto) {
        List<Categoria> registros = new ArrayList();

        try {
            ps = CON.obtenerConexion().prepareStatement("SELECT * FROM categoria WHERE nombre LIKE ? ", ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
            ps.setString(1,"%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()){
                registros.add(new Categoria(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
            }

            ps.close();
            rs.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return registros;
    }

    @Override
    public boolean insertar(Categoria obj) {
        resp = false;
        try {
            ps = CON.obtenerConexion().prepareStatement("INSERT INTO categoria (nombre, descripcion, activo) VALUES (?,?,1)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            System.err.println("ERROR AL INSERTAR CATEGORÍA:");
            System.err.println("Mensaje: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return resp;
    }

    @Override
    public boolean actualizar(Categoria obj) {
        resp = false;
        try {
            ps = CON.obtenerConexion().prepareStatement("UPDATE categoria SET nombre =?, descripcion=? WHERE id =?");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setInt(3, obj.getId());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return resp;
    }

    @Override
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps = CON.obtenerConexion().prepareStatement("UPDATE categoria SET activo=0 WHERE id =?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return resp;
    }

    @Override
    public boolean activar(int id) {
        resp = false;
        try {
            ps = CON.obtenerConexion().prepareStatement("UPDATE categoria SET activo=1 WHERE id =?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return resp;
    }
    
    @Override
    public int total() {
        int totalRegistros = 0;
        try {
            ps = CON.obtenerConexion().prepareStatement("SELECT COUNT(id) FROM categoria");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return totalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        resp = false;
        try {
            ps = CON.obtenerConexion().prepareStatement("SELECT nombre FROM categoria WHERE nombre=?");
            ps.setString(1, texto);
            rs = ps.executeQuery();
            if (rs.next()) {
                resp = true;
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return resp;
    }
}
