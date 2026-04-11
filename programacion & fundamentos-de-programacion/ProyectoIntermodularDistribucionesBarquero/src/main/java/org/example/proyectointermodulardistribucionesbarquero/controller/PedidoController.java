package org.example.proyectointermodulardistribucionesbarquero.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodulardistribucionesbarquero.dataAccess.ClienteDAO;
import org.example.proyectointermodulardistribucionesbarquero.dataAccess.ProductoDAO;
import org.example.proyectointermodulardistribucionesbarquero.model.*;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {

    @FXML private ComboBox<Cliente> cbCliente;
    @FXML private ComboBox<Producto> cbProducto;
    @FXML private TextField txtCantidad;
    @FXML private Label lblTotal;

    @FXML private TableView<DetallePedido> tablaDetalle;
    @FXML private TableColumn<DetallePedido, String> colProducto;
    @FXML private TableColumn<DetallePedido, String> colCantidad;
    @FXML private TableColumn<DetallePedido, String> colPrecio;

    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    private List<DetallePedido> carrito = new ArrayList<>();

    @FXML
    public void initialize() {

        cbCliente.getItems().addAll(clienteDAO.obtenerClientes());
        cbProducto.getItems().addAll(productoDAO.obtenerProductos());

        // 🔹 mostrar nombres en combos
        cbCliente.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Cliente c, boolean empty) {
                super.updateItem(c, empty);
                setText(empty ? "" : c.getNombre());
            }
        });

        cbCliente.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Cliente c, boolean empty) {
                super.updateItem(c, empty);
                setText(empty ? "" : c.getNombre());
            }
        });

        cbProducto.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Producto p, boolean empty) {
                super.updateItem(p, empty);
                setText(empty ? "" : p.getNombre());
            }
        });

        cbProducto.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Producto p, boolean empty) {
                super.updateItem(p, empty);
                setText(empty ? "" : p.getNombre());
            }
        });

        // 🔹 columnas tabla
        colProducto.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getNombreProducto()
                )
        );

        colCantidad.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(data.getValue().getCantidad())
                )
        );

        colPrecio.setCellValueFactory(data ->
                new javafx.beans.property.SimpleStringProperty(
                        String.valueOf(data.getValue().getPrecio_unitario())
                )
        );
    }

    // AÑADIR PRODUCTO
    @FXML
    private void anadirProducto() {

        try {
            Producto producto = cbProducto.getValue();

            if (producto == null || txtCantidad.getText().isEmpty()) {
                mostrarAlerta("Datos incompletos");
                return;
            }

            int cantidad = Integer.parseInt(txtCantidad.getText());

            if (cantidad <= 0) {
                mostrarAlerta("Cantidad inválida");
                return;
            }

            // CONTROL DE STOCK
            if (cantidad > producto.getStock()) {
                mostrarAlerta("No hay suficiente stock disponible");
                return;
            }

            // EVITAR DUPLICADOS
            for (DetallePedido dp : carrito) {
                if (dp.getId_producto() == producto.getId_producto()) {
                    mostrarAlerta("Este producto ya está añadido");
                    return;
                }
            }

            DetallePedido d = new DetallePedido(
                    0,
                    cantidad,
                    producto.getPrecio(),
                    0,
                    producto.getId_producto()
            );

            d.setNombreProducto(producto.getNombre());

            carrito.add(d);
            tablaDetalle.getItems().setAll(carrito);

            actualizarTotal();

        } catch (NumberFormatException e) {
            mostrarAlerta("Cantidad inválida");
        }
    }

    // CREAR PEDIDO
    @FXML
    private void crearPedido() {

        Cliente cliente = cbCliente.getValue();

        if (cliente == null || carrito.isEmpty()) {
            mostrarAlerta("Selecciona cliente y productos");
            return;
        }

        Pedido pedido = new Pedido(
                0,
                java.time.LocalDate.now().toString(),
                "pendiente",
                cliente.getId_cliente(),
                1 // empleado fijo
        );

        new org.example.proyectointermodulardistribucionesbarquero.dataAccess.PedidoDAO()
                .insertarPedido(pedido, carrito);

        mostrarAlerta("Pedido creado correctamente");

        carrito.clear();
        tablaDetalle.getItems().clear();

        lblTotal.setText("0.00 €");
    }

    private void actualizarTotal() {

        double total = 0;

        for (DetallePedido d : carrito) {
            total += d.getCantidad() * d.getPrecio_unitario();
        }

        lblTotal.setText(String.format("%.2f €", total));
    }

    // ALERTAS
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}