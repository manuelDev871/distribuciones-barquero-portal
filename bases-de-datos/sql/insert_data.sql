-- INSERT DATA

INSERT INTO categorias (nombre) VALUES
('Bebidas'),
('Snacks'),
('Productos frescos'),
('Congelados');

INSERT INTO proveedores (nombre, telefono) VALUES
('Distribuciones Coca-Cola', '600123456'),
('PepsiCo España', '600234567'),
('Snack Iberia', '600345678'),
('Frutas del Sur', '600456789');

INSERT INTO clientes (nombre, email, telefono, direccion) VALUES
('Bar El Tapeo', 'tapeo@gmail.com', '611111111', 'Calle Mayor 12'),
('Restaurante La Plaza', 'plaza@gmail.com', '622222222', 'Avenida Centro 5'),
('Cafetería Sol', 'sol@gmail.com', '633333333', 'Calle Sol 8'),
('Supermercado Díaz', 'diaz@gmail.com', '644444444', 'Polígono Norte 3');

INSERT INTO empleados (nombre, puesto) VALUES
('Juan Pérez', 'Repartidor'),
('María López', 'Administración'),
('Carlos Ruiz', 'Comercial');

INSERT INTO productos (nombre, precio, stock, id_categoria, id_proveedor) VALUES
('Coca-Cola 33cl', 1.20, 200, 1, 1),
('Pepsi 33cl', 1.10, 180, 1, 2),
('Patatas fritas', 0.90, 150, 2, 3),
('Nachos', 1.50, 120, 2, 3),
('Manzanas', 2.00, 100, 3, 4),
('Helado vainilla', 3.50, 80, 4, 2);

INSERT INTO pedidos (fecha, estado, id_cliente, id_empleado) VALUES
('2026-04-01', 'pendiente', 1, 1),
('2026-04-02', 'enviado', 2, 2),
('2026-04-03', 'entregado', 3, 1),
('2026-04-04', 'pendiente', 4, 3);

INSERT INTO detalle_pedido (cantidad, precio_unitario, id_pedido, id_producto) VALUES
(10, 1.20, 1, 1),
(5, 0.90, 1, 3),
(20, 1.10, 2, 2),
(10, 1.50, 2, 4),
(15, 2.00, 3, 5),
(5, 3.50, 3, 6),
(8, 1.20, 4, 1),
(6, 0.90, 4, 3);
