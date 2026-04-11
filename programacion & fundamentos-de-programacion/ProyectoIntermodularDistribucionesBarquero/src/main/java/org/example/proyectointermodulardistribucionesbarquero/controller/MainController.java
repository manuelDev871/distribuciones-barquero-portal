package org.example.proyectointermodulardistribucionesbarquero.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.proyectointermodulardistribucionesbarquero.dataAccess.ClienteDAO;
import org.example.proyectointermodulardistribucionesbarquero.dataAccess.ProductoDAO;
import org.example.proyectointermodulardistribucionesbarquero.model.Cliente;
import org.example.proyectointermodulardistribucionesbarquero.model.Producto;

import java.util.List;

public class MainController {

    // 🔹 TABLA
    @FXML private TableView<Object> tablaClientes;
    @FXML private TableColumn<Object, String> colNombre;
    @FXML private TableColumn<Object, String> colEmail;
    @FXML private TableColumn<Object, String> colTelefono;
    @FXML private TableColumn<Object, String> colDireccion;

    @FXML private Label lblTitulo;

    // 🔹 DAO
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    // 🔹 CONTROL DE MODO
    private String modoActual = "clientes";

    // 🔹 INIT
    @FXML
    public void initialize() {
        mostrarClientes();
    }

    // CLIENTES
    @FXML
    private void mostrarClientes() {

        modoActual = "clientes";
        lblTitulo.setText("Gestión de Clientes");

        tablaClientes.getItems().clear();

        colNombre.setText("Nombre");
        colEmail.setText("Email");
        colTelefono.setText("Teléfono");
        colDireccion.setText("Dirección");

        colNombre.setCellValueFactory(data -> {
            Cliente c = (Cliente) data.getValue();
            return new javafx.beans.property.SimpleStringProperty(c.getNombre());
        });

        colEmail.setCellValueFactory(data -> {
            Cliente c = (Cliente) data.getValue();
            return new javafx.beans.property.SimpleStringProperty(c.getEmail());
        });

        colTelefono.setCellValueFactory(data -> {
            Cliente c = (Cliente) data.getValue();
            return new javafx.beans.property.SimpleStringProperty(c.getTelefono());
        });

        colDireccion.setCellValueFactory(data -> {
            Cliente c = (Cliente) data.getValue();
            return new javafx.beans.property.SimpleStringProperty(c.getDireccion());
        });

        List<Cliente> lista = clienteDAO.obtenerClientes();
        tablaClientes.getItems().addAll(lista);
    }

    // PRODUCTOS
    @FXML
    private void mostrarProductos() {

        modoActual = "productos";
        lblTitulo.setText("Gestión de Productos");

        tablaClientes.getItems().clear();

        colNombre.setText("Nombre");
        colEmail.setText("Precio");
        colTelefono.setText("Stock");
        colDireccion.setText("Proveedor");

        // Nombre
        colNombre.setCellValueFactory(data -> {
            Producto p = (Producto) data.getValue();
            return new javafx.beans.property.SimpleStringProperty(p.getNombre());
        });

        // Precio
        colEmail.setCellValueFactory(data -> {
            Producto p = (Producto) data.getValue();
            return new javafx.beans.property.SimpleStringProperty(String.valueOf(p.getPrecio()));
        });

        // Stock
        colTelefono.setCellValueFactory(data -> {
            Producto p = (Producto) data.getValue();
            return new javafx.beans.property.SimpleStringProperty(
                    String.valueOf(p.getStock())
            );
        });

        colTelefono.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    int stock = Integer.parseInt(item);

                    if (stock <= 10) {
                        setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        // Proveedor
        colDireccion.setCellValueFactory(data -> {
            Producto p = (Producto) data.getValue();
            return new javafx.beans.property.SimpleStringProperty(
                    p.getNombreProveedor()
            );
        });

        List<Producto> lista = productoDAO.obtenerProductos();
        tablaClientes.getItems().addAll(lista);
    }

    // AÑADIR
    @FXML
    private void anhadir() {

        try {
            FXMLLoader loader;

            if (modoActual.equals("clientes")) {
                loader = new FXMLLoader(getClass().getResource(
                        "/org/example/proyectointermodulardistribucionesbarquero/form-cliente.fxml"));
            } else {
                loader = new FXMLLoader(getClass().getResource(
                        "/org/example/proyectointermodulardistribucionesbarquero/form-producto.fxml"));
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.showAndWait();

            refrescar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EDITAR
    @FXML
    private void editar() {

        Object seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            System.out.println("Selecciona un elemento");
            return;
        }

        try {
            FXMLLoader loader;

            if (modoActual.equals("clientes")) {

                Cliente cliente = (Cliente) seleccionado;

                loader = new FXMLLoader(getClass().getResource(
                        "/org/example/proyectointermodulardistribucionesbarquero/form-cliente.fxml"));

                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));

                FormClienteController controller = loader.getController();
                controller.setCliente(cliente);

                stage.showAndWait();

            } else {

                Producto producto = (Producto) seleccionado;

                loader = new FXMLLoader(getClass().getResource(
                        "/org/example/proyectointermodulardistribucionesbarquero/form-producto.fxml"));

                Stage stage = new Stage();
                stage.setScene(new Scene(loader.load()));

                FormProductoController controller = loader.getController();
                controller.setProducto(producto);

                stage.showAndWait();
            }

            refrescar();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ELIMINAR
    @FXML
    private void eliminar() {

        Object seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            System.out.println("Selecciona un elemento");
            return;
        }

        if (modoActual.equals("clientes")) {

            Cliente cliente = (Cliente) seleccionado;
            clienteDAO.eliminarCliente(cliente.getId_cliente());

        } else {

            Producto producto = (Producto) seleccionado;
            productoDAO.eliminarProducto(producto.getId_producto());
        }

        refrescar();
    }

    // REFRESCAR
    private void refrescar() {
        if (modoActual.equals("clientes")) {
            mostrarClientes();
        } else {
            mostrarProductos();
        }
    }

    @FXML
    private void abrirPedidos() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/org/example/proyectointermodulardistribucionesbarquero/pedidos-view.fxml")
            );

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Gestión de Pedidos");
            stage.showAndWait();

            refrescar();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}