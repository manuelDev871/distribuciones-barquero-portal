## 3. Modelo relacional

A partir del diagrama E/R se ha generado el siguiente modelo relacional:

### Tablas

* clientes (id_cliente PK, nombre, email, telefono, direccion)
* empleados (id_empleado PK, nombre, puesto)
* categorias (id_categoria PK, nombre)
* proveedores (id_proveedor PK, nombre, telefono)
* productos (id_producto PK, nombre, precio, stock, id_categoria FK, id_proveedor FK)
* pedidos (id_pedido PK, fecha, estado, id_cliente FK, id_empleado FK)
* detallePedido (id_detalle PK, cantidad, precio_unitario, id_pedido FK, id_producto FK)

### Claves primarias

Cada tabla dispone de una clave primaria que identifica de forma única cada registro.

### Claves foráneas

* pedidos.id_cliente → clientes.id_cliente
* pedidos.id_empleado → empleado.id_empleado
* productos.id_categoria → categorias.id_categoria
* productos.id_proveedor → proveedor.id_proveedor
* detallePedido.id_pedido → pedidos.id_pedido
* detallePedido.id_producto → productos.id_producto


