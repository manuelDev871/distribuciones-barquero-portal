package org.example.proyectointermodulardistribucionesbarquero.dataAccess;

import org.example.proyectointermodulardistribucionesbarquero.db.Conexion;
import org.example.proyectointermodulardistribucionesbarquero.model.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    // 🔹 OBTENER PRODUCTOS (CON JOIN)
    public List<Producto> obtenerProductos() {

        List<Producto> lista = new ArrayList<>();

        String sql = """
        SELECT p.*, c.nombre AS categoria_nombre, pr.nombre AS proveedor_nombre
        FROM productos p
        JOIN categorias c ON p.id_categoria = c.id_categoria
        JOIN proveedores pr ON p.id_proveedor = pr.id_proveedor
        """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto p = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        rs.getInt("id_categoria"),
                        rs.getInt("id_proveedor")
                );

                // 🔥 AQUÍ SE ASIGNAN LOS NOMBRES
                p.setNombreCategoria(rs.getString("categoria_nombre"));
                p.setNombreProveedor(rs.getString("proveedor_nombre"));

                lista.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    // 🔹 INSERTAR
    public void insertarProducto(Producto p) {

        String sql = "INSERT INTO productos (nombre, precio, stock, id_categoria, id_proveedor) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getId_categoria());   // ✅ CORRECTO
            ps.setInt(5, p.getId_proveedor());   // ✅ CORRECTO

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error insert: " + e.getMessage());
        }
    }

    // 🔹 ELIMINAR
    public void eliminarProducto(int id) {

        String sql = "DELETE FROM productos WHERE id_producto=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error delete: " + e.getMessage());
        }
    }

    // 🔹 ACTUALIZAR
    public void actualizarProducto(Producto producto) {

        String sql = "UPDATE productos SET nombre=?, precio=?, stock=?, id_categoria=?, id_proveedor=? WHERE id_producto=?";

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, producto.getNombre());
            ps.setDouble(2, producto.getPrecio());
            ps.setInt(3, producto.getStock());
            ps.setInt(4, producto.getId_categoria());   // ✅ CORRECTO
            ps.setInt(5, producto.getId_proveedor());   // ✅ CORRECTO
            ps.setInt(6, producto.getId_producto());

            ps.executeUpdate();

            System.out.println("Producto actualizado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}