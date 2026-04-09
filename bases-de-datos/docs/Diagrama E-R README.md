## 2. Diagrama Entidad-Relación

El diagrama E/R representa las entidades principales del sistema y las relaciones entre ellas.

### Entidades

* clientes (id_cliente, nombre, email, telefono, direccion)
* empleados (id_empleado, nombre, puesto)
* pedidos (id_pedido, fecha, estado)
* detallePedido (id_detalle, cantidad, precio_unitario)
* productos (id_producto, nombre, precio, stock)
* categorias (id_categoria, nombre)
* proveedores (id_proveedor, nombre, telefono)

### Relaciones

* clientes realiza pedidos (1:N)
* empleados gestiona pedidos (1:N)
* pedidos contiene detallePedido (1:N)
* detallePedido incluye productos (N:1)
* productos pertenece a categorias (N:1)
* proveedores suministra productos (1:N)

### Cardinalidades

* Un cliente puede realizar varios pedidos, pero cada pedido pertenece a un solo cliente.
* Un empleado puede gestionar varios pedidos, pero cada pedido es gestionado por un único empleado.
* Un pedido contiene múltiples registros en detallePedido.
* Cada detallePedido hace referencia a un único producto.
* Una categoría puede tener múltiples productos.
* Un proveedor puede suministrar múltiples productos.

El modelo está diseñado para evitar redundancia y mantener la integridad de los datos.
