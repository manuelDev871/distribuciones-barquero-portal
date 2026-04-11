package org.example.proyectointermodulardistribucionesbarquero.model;

public class Producto {

    private int id_producto;
    private String nombre;
    private double precio;
    private int stock;

    // 🔹 PARA BD
    private int id_categoria;
    private int id_proveedor;

    // 🔹 PARA MOSTRAR
    private String nombreCategoria;
    private String nombreProveedor;

    public Producto() {}

    // 🔹 CONSTRUCTOR PRINCIPAL (BD)
    public Producto(int id_producto, String nombre, double precio, int stock, int id_categoria, int id_proveedor) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.id_categoria = id_categoria;
        this.id_proveedor = id_proveedor;
    }

    // 🔹 GETTERS / SETTERS
    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
}