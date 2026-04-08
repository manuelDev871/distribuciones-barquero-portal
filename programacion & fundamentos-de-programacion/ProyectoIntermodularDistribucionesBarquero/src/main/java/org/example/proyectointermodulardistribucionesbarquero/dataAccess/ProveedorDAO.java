package org.example.proyectointermodulardistribucionesbarquero.dataAccess;

import org.example.proyectointermodulardistribucionesbarquero.db.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    public List<String> obtenerNombresProveedores() {
        List<String> lista = new ArrayList<>();

        String sql = "SELECT nombre FROM proveedores";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(rs.getString("nombre"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}
