package org.example.proyectointermodulardistribucionesbarquero.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.proyectointermodulardistribucionesbarquero.dataAccess.ClienteDAO;
import org.example.proyectointermodulardistribucionesbarquero.model.Cliente;

public class FormClienteController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtEmail;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDireccion;

    private ClienteDAO dao = new ClienteDAO();

    @FXML
    private void guardarCliente() {

        String nombre = txtNombre.getText();
        String email = txtEmail.getText();
        String telefono = txtTelefono.getText();
        String direccion = txtDireccion.getText();

        if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty() || direccion.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios");
            return;
        }

        try {

            if (clienteEditar == null) {
                // INSERT
                Cliente nuevo = new Cliente(0, nombre, email, telefono, direccion);
                dao.insertarCliente(nuevo);
            } else {
                // UPDATE
                clienteEditar.setNombre(nombre);
                clienteEditar.setEmail(email);
                clienteEditar.setTelefono(telefono);
                clienteEditar.setDireccion(direccion);

                dao.actualizarCliente(clienteEditar);
            }

            Stage stage = (Stage) txtNombre.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            mostrarAlerta("Error", "Problema al guardar");
        }
    }

    @FXML
    private void cerrarVentana() {
        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.close();
    }

    private Cliente clienteEditar = null;

    public void setCliente(Cliente cliente) {

        this.clienteEditar = cliente;

        txtNombre.setText(cliente.getNombre());
        txtEmail.setText(cliente.getEmail());
        txtTelefono.setText(cliente.getTelefono());
        txtDireccion.setText(cliente.getDireccion());
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}