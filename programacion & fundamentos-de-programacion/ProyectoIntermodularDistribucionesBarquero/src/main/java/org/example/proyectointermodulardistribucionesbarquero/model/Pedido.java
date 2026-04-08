package org.example.proyectointermodulardistribucionesbarquero.model;

public class Pedido {

    private int id_pedido;
    private String fecha;
    private String estado;
    private int id_cliente;
    private int id_empleado;

    public Pedido() {}

    public Pedido(int id_pedido, String fecha, String estado, int id_cliente, int id_empleado) {
        this.id_pedido = id_pedido;
        this.fecha = fecha;
        this.estado = estado;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
    }

    public int getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(int id_pedido) {
        this.id_pedido = id_pedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }
}