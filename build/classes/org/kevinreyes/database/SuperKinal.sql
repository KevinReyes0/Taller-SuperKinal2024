drop database if exists SuperKinal;

create database if not exists SuperKinal;

use SuperKinal;

create table NivelesAcceso(
	nivelAccesoId int not null auto_increment,
    nivelAcceso varchar(40) not null,
    primary key PK_nivelAccesoId(nivelAccesoId)
);

create table Cargos(
	cargoId int not null auto_increment,
    nombreCargo varchar (30) not null,
    descripcionCargo varchar(100) not  null,
    primary key PK_cargoId (cargoId)
);

create table Compras(
	compraId int not null auto_increment,
    fechaCompra date,
    totalCompra decimal(10,2),
    primary key PK_compraId (compraId)
);

create table CategoriaProductos(
	categoriaProductosId int not null auto_increment,
    nombreCategoria varchar (30) not null,
    decripcionCategoria varchar (100) not null,
    primary key PK_categoriaProductosId (categoriaProductosId)
);

create table Distribuidores (
	distribuidorId int not null auto_increment,
    nombreDistribuidor varchar (30) not null,
    direccionDistribuidor varchar (200) not null,
    nitDistribuidor varchar (15) not null,
    telefonoDistribuidor varchar (15) not null,
    web varchar (50),
    primary key PK_distribuidorId (distribuidorId)
); 

create table Clientes(
	clienteId int not null auto_increment,
    nombre varchar (30)not null,
    apellido varchar (30) not null,
    telefono varchar (15),
    direccion varchar (150) not null,
	nit varchar (15),
    primary key Pk_clienteId (clienteId)
);

create table Productos(
	productoId int not null auto_increment,
    nombreProducto varchar (50) not null,
    descripcionProducto varchar (100),
    cantidadStock int not null,
    precioVentaUnitario decimal (10,2) not null,
    precioVentaMayor decimal (10,2) not null,
    precioCompra decimal (10,2) not null,
    imagenProducto blob,
    distribuidorId int not null,
    categoriaProductosId int not null,
    primary key PK_productoId (productoId),
    constraint FK_Productos_Distribuidores foreign key (distribuidorId)
		references Distribuidores (distribuidorId),
	constraint FK_Productos_CategoriaProductos foreign key (categoriaProductosId)
		references CategoriaProductos (categoriaProductosId)
);

create table Promociones(
	promocionId int not null auto_increment,
    precioPromocion decimal (10,2) not null,
    descripcionPromocion varchar (100),
    fechaInicio date not null,
    fechaFinalizacion date not null,
    productoId int not null,
    primary key PK_promocionId (promocionId),
    constraint FK_Promociones_Productos foreign key (productoId)
		references Productos (productoId)
);

create table DetalleCompra (
	detalleCompraId int not null auto_increment,
    cantidadCompra int not null,
    productoId int not null,
    compraId int not null,
    primary key PK_detalleCompraId (detalleCompraId),
    constraint FK_DetalleCompra_Productos foreign key (productoId)
		references Productos (productoId),
	constraint FK_DetalleCompra_Compras foreign key (compraId)
		references Compras (compraId)
);

create table Empleados (
	empleadoId int not null auto_increment,
    nombreEmpleado varchar (30) not null,
    apellidoEmpleado varchar (30) not null,
    sueldo decimal (10, 2) not null,
    horaEntrada time not null,
    horaSalida time not null,
    cargoId int not null,
    encargadoId int,
    primary key PK_empleadoId (empleadoId),
    constraint FK_EncargadoId foreign key Empleados (encargadoId)
		references Empleados (empleadoId),
	constraint FK_Empleados_Cargos foreign key (cargoId)
		references Cargos (cargoId)
);


create table Usuarios(
	usuarioId int not null auto_increment,
    usuario varchar(30) not null,
    contrasenia varchar(200) not null,
    nivelAccesoId int not null,
    empleadoId int not null,
    primary key PK_usuarioId(usuarioId),
    constraint FK_Usuarios_NivelesAcceso foreign key Usuarios(nivelAccesoId)
		references NivelesAcceso(nivelAccesoId),
	constraint PK_Usuarios_Empleados foreign key Usuarios(empleadoId)
		references Empleados(empleadoId)
);

create table Facturas (
	facturaId int not null auto_increment,
    fecha date not null,
    hora time not null,
    total decimal (10,2),
    clienteId int not null,
    empleadoId int not null,
    primary key PK_facturaId (facturaId),
    constraint FK_Facturas_Clientes foreign key (clienteId)
		references Clientes (clienteID),
	constraint FK_Facturas_Empleados foreign key (empleadoId)
		references Empleados (empleadoId)
);

create table TicketSoporte (
	ticketSoporteId int not null auto_increment,
    descripcionTicket varchar (250) not null,
    estatus varchar (30) not null,
    clienteId int not null,
    facturaId int not null,
    primary key PK_ticketSoporteId (ticketSoporteId),
    constraint FK_TicketSoporte_Clientes foreign key (clienteId)
		references Clientes (clienteId),
	constraint FK_TicketSoporte_Facturas foreign key (facturaId)
		references Facturas (facturaId)
);

create table DetalleFactura (
	detalleFacturaId int not null auto_increment,
    facturaId int not null,
    productoId int not null,
    primary key PK_detalleFacturaId (detalleFacturaId),
    constraint FK_DetalleFactura_Facturas foreign key (facturaId)
		references Facturas (facturaId),
	constraint FK_DetalleFactura_Productos foreign key (productoId)
		references Productos (productoId)
);
insert into Cargos(nombreCargo,descripcionCargo)values
		('Jefe','Dueño de la empresa'),
		('Empleado ','ayuda a vender en la tienda ');

insert into Empleados(nombreEmpleado ,apellidoEmpleado ,sueldo ,horaEntrada ,horaSalida ,cargoId)values
		('Kevin','reyes',55000.00 ,'06:30:00','17:30:00',1),
		('Jose','Ajcu',3000.00 ,'06:30:00','17:30:00',1);


insert into Clientes(nombre, apellido, telefono, direccion, nit) values
	('Sergio', 'Calderon', '9090=6023', 'Peten', 'CF'),
    ('Kevin', 'Reyes', '9020=6013', 'Antigua', 'CF');

insert into CategoriaProductos (nombreCategoria, decripcionCategoria) values
	('Tecnologia', 'Se trata de todo lo que tiene que ver con tecnologias'),
    ('Cocina', 'Se trata de toda el area de cocina');
    
insert into Distribuidores (nombreDistribuidor, direccionDistribuidor,nitDistribuidor,  telefonoDistribuidor, web) values
	('Carnes', 'Quiche', '2020-602', '8989-3202', 'www.CarnesGuatemala.com'),
    ('Muebles', 'Baja Verapaz', '2020-495', '5691-3979', 'www.MueblesGuatemala.com');

insert into NivelesAcceso(nivelAcceso)values
		('Administrador'),
		('Usuarios');

insert into Usuarios(usuario, contrasenia, nivelAccesoId, empleadoId)values
		('kevin', '$2a$10$IYgQkW2ADTG.rpZxiA9HquLd.voYWyY/7iu6a5m25RTAZygbw424W',1, 1);
        








