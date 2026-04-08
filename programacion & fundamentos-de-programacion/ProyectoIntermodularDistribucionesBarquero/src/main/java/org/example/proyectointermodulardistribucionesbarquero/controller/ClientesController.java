package org.example.proyectointermodulardistribucionesbarquero.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.proyectointermodulardistribucionesbarquero.dataAccess.ClienteDAO;
import org.example.proyectointermodulardistribucionesbarquero.model.Cliente;

import java.util.List;

public class ClientesController {

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colEmail;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    private ClienteDAO dao = new ClienteDAO();

    @FXML
    public void initialize() {

        colNombre.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        colTelefono.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelefono()));

        cargarClientes();
    }

    private void cargarClientes() {
        List<Cliente> lista = dao.obtenerClientes();
        tablaClientes.getItems().setAll(lista);
    }

    @FXML
    private void añadirCliente() {
        System.out.println("Añadir cliente (pendiente)");
    }

    @FXML
    private void eliminarCliente() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccionado != null) {
            dao.eliminarCliente(seleccionado.getId_cliente());
            cargarClientes();
        }
    }
}