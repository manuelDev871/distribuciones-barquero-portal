package org.example.proyectointermodulardistribucionesbarquero.dataAccess;

import org.example.proyectointermodulardistribucionesbarquero.db.Conexion;
import org.example.proyectointermodulardistribucionesbarquero.model.DetallePedido;
import org.example.proyectointermodulardistribucionesbarquero.model.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class PedidoDAO {

    public void insertarPedido(Pedido pedido, List<DetallePedido> detalles) {

        String sqlPedido = "INSERT INTO pedidos (fecha, estado, id_cliente, id_empleado) VALUES (?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO detalle_pedido (cantidad, precio_unitario, id_pedido, id_producto) VALUES (?, ?, ?, ?)";
        String sqlStock = "UPDATE productos SET stock = stock - ? WHERE id_producto = ?";

        try (Connection con = Conexion.conectar()) {

            con.setAutoCommit(false); // 🔥 TRANSACCIÓN

            // 🔹 INSERT PEDIDO
            PreparedStatement psPedido = con.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            psPedido.setString(1, pedido.getFecha());
            psPedido.setString(2, pedido.getEstado());
            psPedido.setInt(3, pedido.getId_cliente());
            psPedido.setInt(4, pedido.getId_empleado());
            psPedido.executeUpdate();

            ResultSet rs = psPedido.getGeneratedKeys();
            rs.next();
            int idPedido = rs.getInt(1);

            // 🔹 INSERT DETALLES + RESTAR STOCK
            PreparedStatement psDetalle = con.prepareStatement(sqlDetalle);
            PreparedStatement psStock = con.prepareStatement(sqlStock);

            for (DetallePedido d : detalles) {

                // 👉 INSERT DETALLE
                psDetalle.setInt(1, d.getCantidad());
                psDetalle.setDouble(2, d.getPrecio_unitario());
                psDetalle.setInt(3, idPedido);
                psDetalle.setInt(4, d.getId_producto());
                psDetalle.executeUpdate();

                // 🔥 RESTAR STOCK
                psStock.setInt(1, d.getCantidad());
                psStock.setInt(2, d.getId_producto());
                psStock.executeUpdate();
            }

            con.commit(); // ✅ TODO OK

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}