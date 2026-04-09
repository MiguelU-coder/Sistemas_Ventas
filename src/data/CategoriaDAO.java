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
            ps = CON.conectar().prepareStatement("SELECT * FROM categoria WHERE nombre LIKE ? ");
            ps.setString(1,"%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()){
                registros.add(new Categoria(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getBoolean(4)));
                System.out.println("A new Category was created");

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return registros;
    }

    @Override
    public boolean insertar(Categoria obj) {
        return false;
    }

    @Override
    public boolean actualizar(Categoria obj) {
        return false;
    }

    @Override
    public boolean desactivar(int id) {
        return false;
    }

    @Override
    public int total() {
        return 0;
    }

    @Override
    public boolean existe(String texto) {
        return false;
    }
}
