package org.example.proyectointermodulardistribucionesbarquero.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectointermodulardistribucionesbarquero.dataAccess.ProductoDAO;
import org.example.proyectointermodulardistribucionesbarquero.dataAccess.ProveedorDAO;
import org.example.proyectointermodulardistribucionesbarquero.model.Producto;

public class FormProductoController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtStock;
    @FXML private ComboBox<String> cbCategoria;
    @FXML private ComboBox<String> cbProveedor;

    private ProductoDAO dao = new ProductoDAO();
    private ProveedorDAO proveedorDAO = new ProveedorDAO();

    private Producto producto; // para editar

    // 🔹 INICIALIZACIÓN
    @FXML
    public void initialize() {
        cbCategoria.getItems().addAll("Bebidas", "Alimentación", "Limpieza");
        cbProveedor.getItems().addAll(proveedorDAO.obtenerNombresProveedores());
    }

    // 🔹 GUARDAR PRODUCTO
    @FXML
    private void guardarProducto() {

        try {
            String nombre = txtNombre.getText();
            String precioTexto = txtPrecio.getText();
            String stockTexto = txtStock.getText();

            // VALIDACIONES
            if (nombre.isEmpty() || precioTexto.isEmpty() || stockTexto.isEmpty()) {
                mostrarAlerta("Todos los campos son obligatorios");
                return;
            }

            if (cbCategoria.getValue() == null || cbProveedor.getValue() == null) {
                mostrarAlerta("Selecciona categoría y proveedor");
                return;
            }

            double precio = Double.parseDouble(precioTexto);
            int stock = Integer.parseInt(stockTexto);

            int idCategoria = switch (cbCategoria.getValue()) {
                case "Bebidas" -> 5;
                case "Alimentación" -> 6;
                case "Snacks" -> 7;
                default -> 0;
            };

            int idProveedor = cbProveedor.getSelectionModel().getSelectedIndex() + 1;

            if (producto == null) {
                Producto nuevo = new Producto(0, nombre, precio, stock, idCategoria, idProveedor);
                dao.insertarProducto(nuevo);
                mostrarAlerta("Producto añadido correctamente");
            } else {
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                producto.setStock(stock);
                producto.setId_categoria(idCategoria);
                producto.setId_proveedor(idProveedor);

                dao.actualizarProducto(producto);
                mostrarAlerta("Producto actualizado correctamente");
            }

            cerrarVentana();

        } catch (NumberFormatException e) {
            mostrarAlerta("Precio y stock deben ser números válidos");
        } catch (Exception e) {
            mostrarAlerta("Error al guardar producto");
        }
    }

    // 🔹 CARGAR DATOS PARA EDITAR
    public void setProducto(Producto producto) {
        this.producto = producto;

        txtNombre.setText(producto.getNombre());
        txtPrecio.setText(String.valueOf(producto.getPrecio()));
        txtStock.setText(String.valueOf(producto.getStock()));

        // seleccionar categoría y proveedor
        cbCategoria.getSelectionModel().select(producto.getId_categoria() - 1);
        cbProveedor.getSelectionModel().select(producto.getId_proveedor() - 1);
    }

    // 🔹 CERRAR VENTANA
    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}