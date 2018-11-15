DROP TABLE venta;
DROP TABLE cliente;
DROP TABLE producto;

CREATE TABLE PRODUCTO(
IDPRODUCTO INT NOT NULL PRIMARY KEY, 
DESCRIPCION VARCHAR(50)NOT NULL, 
STOCKACTUAL INT, 
STOCKMINIMO INT, 
PVP INT
);

CREATE TABLE CLIENTE(
IDCLIENTE INT NOT NULL PRIMARY KEY, 
NOMBRE VARCHAR(50) NOT NULL, 
DIRECCION VARCHAR(50),
POBLACION VARCHAR(50),
TELEF VARCHAR(9),
NIF VARCHAR(9)
);

CREATE TABLE VENTA(
IDVENTA INT NOT NULL PRIMARY KEY, 
FECHAVENTA DATE NOT NULL, 
IDCLIENTE INT ,
IDPRODUCTO INT,
CANTIDAD INT,
CONSTRAINT PRODUCTO_fk FOREIGN KEY (IDPRODUCTO) REFERENCES PRODUCTO(IDPRODUCTO),
CONSTRAINT CLIENTE_fk FOREIGN KEY(IDCLIENTE) REFERENCES CLIENTE(IDCLIENTE) 
);