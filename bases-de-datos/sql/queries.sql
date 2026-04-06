-- 1. Mostrar todos los clientes
SELECT * FROM clientes;

-- 2. Mostrar todos los productos
SELECT * FROM productos;

-- 3. Mostrar todos los pedidos
SELECT * FROM pedidos;

-- 4. Pedidos en estado 'pendiente'
SELECT * FROM pedidos
WHERE estado = 'pendiente';

-- 5. Productos con stock menor a 100
SELECT nombre, stock
FROM productos
WHERE stock < 100;

-- 6. Clientes con email específico
SELECT * FROM clientes
WHERE email = 'tapeo@gmail.com';

-- 7. Número total de pedidos
SELECT COUNT(*) AS total_pedidos
FROM pedidos;

-- 8. Precio medio de productos
SELECT AVG(precio) AS precio_medio
FROM productos;

-- 9. Total de stock disponible
SELECT SUM(stock) AS stock_total
FROM productos;

-- 10. Número de pedidos por cliente
SELECT id_cliente, COUNT(*) AS total_pedidos
FROM pedidos
GROUP BY id_cliente;

-- 11. Total vendido por pedido
SELECT id_pedido, SUM(cantidad * precio_unitario) AS total
FROM detalle_pedido
GROUP BY id_pedido;

-- 12. Productos ordenados por precio (de mayor a menor)
SELECT nombre, precio
FROM productos
ORDER BY precio DESC;

-- 13. Pedidos ordenados por fecha
SELECT * FROM pedidos
ORDER BY fecha DESC;

-- 14. Mostrar pedidos con nombre del cliente
SELECT p.id_pedido, p.fecha, p.estado, c.nombre AS cliente
FROM pedidos p
JOIN clientes c ON p.id_cliente = c.id_cliente;

-- 15. Mostrar pedidos con cliente y empleado
SELECT p.id_pedido, c.nombre AS cliente, e.nombre AS empleado, p.estado
FROM pedidos p
JOIN clientes c ON p.id_cliente = c.id_cliente
JOIN empleados e ON p.id_empleado = e.id_empleado;

-- 16. Mostrar detalle de pedidos con productos
SELECT dp.id_pedido, pr.nombre AS producto, dp.cantidad, dp.precio_unitario
FROM detalle_pedido dp
JOIN productos pr ON dp.id_producto = pr.id_producto;

-- 17. Total gastado por cada cliente
SELECT c.nombre, SUM(dp.cantidad * dp.precio_unitario) AS total_gastado
FROM clientes c
JOIN pedidos p ON c.id_cliente = p.id_cliente
JOIN detalle_pedido dp ON p.id_pedido = dp.id_pedido
GROUP BY c.nombre;

-- 18. Producto más vendido
SELECT pr.nombre, SUM(dp.cantidad) AS total_vendido
FROM productos pr
JOIN detalle_pedido dp ON pr.id_producto = dp.id_producto
GROUP BY pr.nombre
ORDER BY total_vendido DESC
LIMIT 1;
