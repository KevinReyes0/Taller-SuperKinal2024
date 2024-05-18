use SuperKinal;

-- Cargos 
DELIMITER $$ 
	create procedure sp_AgregarCargos(in nom varchar (30), in des varchar (100))
		begin 	
			insert into Cargos (nombreCargo , descripcionCargo) 
				values (nom, des);
		end $$
DELIMITER ;
call sp_AgregarCargos('Gerente', 'Administra el super mecado');
call sp_AgregarCargos('Gerente', 'Administra el super mecado');
call sp_AgregarCargos('Gerente', 'Administra el super mecado');
select * from Cargos;

DELIMITER $$ 
	create procedure sp_ListarCargos()
		begin 
			select
				Cargos.cargoId,
				Cargos.nombreCargo,
				Cargos.descripcionCargo  
					from Cargos;
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_EliminarCargos(in carId int)
		begin
			delete
			from Cargos 
				where cargoId =  carId;
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_BuscarCargos(in carId int)
		begin
			select 	
				Cargos.cargoId,
				Cargos.nombreCargo,
				Cargos.descripcionCargo
					from Cargos
					where cargoId = carId;
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_EditarCargos(in carId int, in nom varchar (30), in des varchar (100))
		begin
			update Cargos
				set
					nombreCargo  = nom,
					descripcionCargo  = des
					where cargoId  = carId;
		end$$
DELIMITER ;


-- COMPRAS -- 
DELIMITER $$ 
	create procedure sp_AgregarCompras(in fec date, in tot  decimal (10.2))
		begin 	
			insert into Compras (fechaCompra, totalCompra)
			values (fec, tot);
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_ListarCompras()
		begin 
			select
				Compras.compraId ,
				Compras.fechaCompra, 
				Compras.totalCompra  
					from Compras;
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_EliminarCompras(in compId  int)
		begin
			delete
				from Compras 
					where compraId  =  compId;
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_BuscarCompras(in compId  int)
		begin
			select 	
				Compras.compraId,
				Compras.fechaCompra, 
				Compras.totalCompra 
					from Compras
						where compraId = compId;
		end$$	
DELIMITER ;

DELIMITER $$ 
	create procedure sp_EditarCompras(in compId int,in fechComp date, in totComp  decimal (10.2))
		begin
			update Compras
				set
					fechaCompra = fechComp,
					totalCompra = totComp
					where compraId   = compId;
		end$$
DELIMITER ;

-- CATEGORIA PRODUCTOS --
DELIMITER $$ 
	create procedure sp_AgregarCategoriaProductos(in nom varchar (30), in des varchar (100))
		begin 	
			insert into CategoriaProductos (nombreCategoria , decripcionCategoria)
			values (nom, des);
		end$$
DELIMITER ;

call sp_AgregarCategoriaProductos('Comida', 'Todo lo relacionado con los alimentos');

select * from CategoriaProductos;
DELIMITER $$ 
	create procedure sp_ListarCategoriaProductos()
		begin 
			select
				CategoriaProductos.categoriaProductosId, 
				CategoriaProductos.nombreCategoria,  
				CategoriaProductos.decripcionCategoria  
					from CategoriaProductos;
		end$$
DELIMITER ;
call sp_ListarCategoriaProductos();
DELIMITER $$ 
	create procedure sp_EliminarCategoriaProductos(in catId  int)
		begin
			delete
			from CategoriaProductos 
				where categoriaProductosId  =  catId;
		end$$
DELIMITER ;

call sp_EliminarCategoriaProductos(3);

DELIMITER $$ 
	create procedure sp_BuscarCategoriaProductos(in catId  int)
		begin
			select 	
				CategoriaProductos.categoriaProductosId, 
				CategoriaProductos.nombreCategoria,  
				CategoriaProductos.decripcionCategoria 
					from CategoriaProductos
					where categoriaProductosId  = catId;	
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_EditarCategoriaProductos(in catId int, in nom varchar (30), in des varchar (100))
		begin
			update CategoriaProductos
				set
					nombreCategoria  = nom,
					decripcionCategoria = des
					where categoriaProductosId = catId;
		end$$
DELIMITER ;

-- DISTRIBUIDORES --
DELIMITER $$ 
	create procedure sp_AgregarDistribuidores(in nom varchar (30), in dir varchar (200), in nit varchar(15), in tel varchar(15),  in web varchar(50))
		begin 	
			insert into Distribuidores (nombreDistribuidor , direccionDistribuidor ,nitDistribuidor ,telefonoDistribuidor ,web )
			values (nom, dir,nit,tel,web);
		end$$
DELIMITER ; 
call sp_AgregarDistribuidores('Carnes', 'Quiche', '2020-602', '8989-3202', 'www.CarnesGuatemala.com');
DELIMITER $$ 
	create procedure sp_ListarDistribuidores()
		begin 
			select
				Distribuidores.distribuidorId,
				Distribuidores.nombreDistribuidor,
				Distribuidores.direccionDistribuidor,
				Distribuidores.nitDistribuidor,
				Distribuidores.telefonoDistribuidor,
				Distribuidores.web 
					from Distribuidores;
		end$$
DELIMITER ;
call sp_ListarDistribuidores();

DELIMITER $$ 
	create procedure sp_EliminarDistribuidores(in disId int)
		begin
			delete
			from Distribuidores 
				where distribuidorId =  disId;
		end$$
DELIMITER ;
call sp_EliminarDistribuidores(3);

DELIMITER $$ 
	create procedure sp_BuscarDistribuidores(in disId int)
		begin
			select 	
				Distribuidores.distribuidorId,
				Distribuidores.nombreDistribuidor,
				Distribuidores.direccionDistribuidor,
				Distribuidores.nitDistribuidor,
				Distribuidores.telefonoDistribuidor,
				Distribuidores.web 
					from Distribuidores
					where distribuidorId = disId;
		end$$
DELIMITER ;
call sp_BuscarDistribuidores(2);

DELIMITER $$ 
	create procedure sp_EditarDistribuidores(in disId int, in nom varchar (30), in dir varchar (200), in nit varchar(15), in tel varchar(15),  in web_ varchar(50))
		begin
			update Distribuidores
				set
					nombreDistribuidor = nom,
					direccionDistribuidor = dir,
					nitDistribuidor = nit,
					telefonoDistribuidor = tel ,
					web = web_
					where distribuidorId = disId;
	end$$
DELIMITER ;

call sp_EditarDistribuidores(1, 'Samsung', 'Corea del Sur', '2023-465', '911750015', 'www.samsung.com');



-- Clientes --
DELIMITER $$ 
	create procedure sp_AgregarClientes(in nom varchar (30), in ape varchar (30), in tel varchar (15), in dir varchar (150), in nit_ varchar(15))
		begin 	
			insert into Clientes (nombre, apellido,telefono,direccion,nit)
			values (nom, ape,tel,dir,nit_);
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_ListarClientes()
		begin 
			select
				Clientes.clienteId,
				Clientes.nombre,
				Clientes.apellido,
				Clientes.telefono,
				Clientes.direccion,
				Clientes.nit
					from Clientes;
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_EliminarClientes(in cliId int)
		begin
			delete
			from Clientes 
				where clienteId =  cliId;
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_BuscarClientes(in cliId int)
		begin
			select	
				Clientes.clienteId,
				Clientes.nombre,
				Clientes.apellido,
				Clientes.telefono,
				Clientes.direccion,
				Clientes.nit
					from Clientes
					where clienteId = cliId;
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_EditarClientes(in cliId int,in nom varchar (30), in ape varchar (30), in tel varchar (15), in dir varchar (150), in ni varchar(15))
		begin
			update Clientes
				set
					nombre = nom,
					apellido = ape,
					telefono = tel,
					direccion = dir,
					nit = ni
					where clienteId = cliId;
		end$$
DELIMITER ;



-- Promociones
DELIMITER $$
	create procedure sp_agregarPromociones(prePro decimal (10,2), desPro varchar (100), fecI date, fecF date, in proId int)
		begin
			insert into Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId )
            values(prePro,desPro, fecI, fecF, proId);
			
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_listarPromociones()
		begin
			select
			Promociones.promocionId,
			Promociones.precioPromocion,
			Promociones.descripcionPromocion,
			Promociones.fechaInicio,
			Promociones.fechaFinalizacion,
			Promociones.productoId
				from Promociones;

		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_eliminarPromociones(in proId int)
		begin
			delete
				from Promociones
				where promocionId = proId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_buscarPromociones(in proId int)
		begin
			select
				Promociones.promocionId,
				Promociones.precioPromocion,
				Promociones.descripcionPromocion,
				Promociones.fechaInicio,
				Promociones.fechaFinalizacion,
				Promociones.productoId
					from Promociones
					where promocionId = proId;
		end $$
DELIMITER  ;

DELIMITER $$
	create procedure sp_editarPromociones(in promId int, prePro decimal (10,2), desPro varchar (100), fecI date, fecF date, in proId int )
		begin
			update Promociones
				set
					precioPromocion = prePro,
					descripcionPromocion = desPro,
					fechaInicio = fecI,
					fechFinalizacion = fecF,
					profuctoId = proId
					where promocionId = promId;
		end $$
DELIMITER ;



DELIMITER $$
	create procedure sp_agregarProductos(in nom varchar(50),in des varchar(100),in can int, in preU decimal(10,2),in preM decimal(10,2),in preC decimal(10,2), in ima longblob, in disId int, in catId int)
		begin
			insert into Productos(nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductosId ) 
            values(nom, des, can, preU, preM, preC, ima, disId, catId);
		end $$
DELIMITER ;

CALL sp_agregarProductos('Iphone 15 pro', '256 rom, A17 Pro, 8 GB ram', 45, 8000, 7300, 7000, NULL, 1, 1);

DELIMITER $$
	create procedure sp_listarProductos()
		begin 
			select P.productoId, P.nombreProducto, P.descripcionProducto, P.cantidadStock, P.precioVentaUnitario, P.precioVentaMayor,  P.precioCompra,P.imagenProducto, 
				concat("Distribuidor: ", D.nombreDistribuidor) as distribuidor,
				concat("Categoría: ", CP.nombreCategoria) as categoria
			from Productos P
			left join Distribuidores D on P.distribuidorId = D.distribuidorId
			left join CategoriaProductos CP on P.categoriaproductosId = CP.categoriaproductosId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_buscarProductos(in proId int)
		begin 
			select P.productoId, P.nombreProducto, P.descripcionProducto, P.cantidadStock, P.precioVentaUnitario, P.precioVentaMayor,  P.precioCompra,P.imagenProducto, 
				concat("Distribuidor: ", D.nombreDistribuidor) as distribuidor,
				concat("Categoría: ", CP.nombreCategoria) as categoria
			from Productos P
			left join Distribuidores D on P.distribuidorId = D.distribuidorId
			left join CategoriaProductos CP on P.categoriaproductosId = CP.categoriaproductosId
			where productoId = proId;
		end $$
DELIMITER ;

CALL sp_buscarProductos(1);

DELIMITER $$
	create procedure sp_eliminarProductos(in proId int)
		begin
			delete from Productos
				where productoId = proId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_editarProductos(in proId int, in nom varchar(50),in des varchar(100),in can int, in preU decimal(10,2),in preM decimal(10,2),in preC decimal(10,2), in ima longblob, in disId int, in catId int )
		begin
			update Productos	
				set 
				nombreProducto = nom,
				descripcionProduto = des,
				cantidadStock = can,
				precioVentaUnitario = preU,
				precioVentaMayor = preM,
				precioCompra = preC,
				imagenProducto = ima,
				distribuidorId = disId,
				categoriaProductosId = catId
				where productoId = proId;
		end $$
DELIMITER ;


-- DetalleCompra
DELIMITER $$
	create procedure sp_agregarDetalleCompra(in canC int, in proId int, in comId int)
		begin
			insert into DetalleCompra(cantidadCompra, productoId, compraId) 
            values(canC, proId, comId);
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_listarDetalleCompra()
		begin
			select
				DC.detalleCompraId,
				DC.cantidadCompra,
				DC.productoId,
				DC.compraId
					from DetalleCompra DC;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_buscarDetalleCompra(in detCId int)
		begin
			select * from DetalleCompra
					where detalleCompraId = detCId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_eliminarDetalleCompra(in detCId int)
		begin
			delete
				from DetalleCompra
					where detalleCompraId = detCId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_editarDetalleCompra(in detCId int, in canC int, in proId int, in comId int)
		begin
			update DetalleCompra
				set
					cantidadCompra = canC,
					productoId = proId,
					compraId = comId
						where detalleCompraId = detCId;
		end $$
DELIMITER ;


-- Empleados -- 

DELIMITER $$
	create procedure sp_AgregarEmpleados (in nomEmp varchar (30), in apeEmp  varchar (30), in sud decimal (10, 2), in horEntr time, in horSld time, in cargId int, in encaId int)
		BEGIN 
			insert into Empleados (nombreEmpleado , apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargoId, encargadoId)
				values (nomEmp, apeEmp, sud, horEntr, horSld, cargId, encaId);
		END $$
DELIMITER ;

call sp_AgregarEmpleados('Jorge', 'Peralta', 3420.90, '8:00:00', '17:00:00', 1, null);

call sp_AgregarEmpleados('Jorge', 'Peralta', 3420.90, '8:00:00', '17:00:00', 1, null);

call sp_AgregarEmpleados('Jorge', 'Peralta', 3420.90, '8:00:00', '17:00:00', 1, null);

DELIMITER $$
create procedure sp_ListarEmpleados()
	BEGIN
		select 
			E1.empleadoId, E1.nombreEmpleado, E1.apellidoEmpleado, E1.sueldo, E1.horaEntrada, E1.horaSalida,
			C.nombreCargo,
			E2.nombreEmpleado as 'Encargado' from Empleados E1
			join Cargos C on C.cargoId = E1.cargoId
			left join Empleados E2 on E1.encargadoId = E2.empleadoId;
    END $$
DELIMITER ;
call sp_listarEmpleados();

DELIMITER $$
	create procedure sp_EliminarEmpleados (in empId int)
		BEGIN
			delete
				from Empleados
					where empleadoId = empId;
		END $$
DELIMITER ;

DELIMITER $$
	create procedure sp_BuscarEmpleados (in empId int)
		BEGIN 
			select
			E1.empleadoId, E1.nombreEmpleado, E1.apellidoEmpleado, E1.sueldo, E1.horaEntrada, E1.horaSalida,
			C.nombreCargo,
			E2.nombreEmpleado as 'Encargado' from Empleados E1
			join Cargos C on C.cargoId = E1.cargoId
			left join Empleados E2 on E1.encargadoId = E2.empleadoId
						where  E1.empleadoId = empId;
		END $$
DELIMITER ;
call sp_BuscarEmpleados(1);  

DELIMITER $$
	create procedure sp_EditarEmpleados (in empId int, in nomEmp varchar (30), in apeEmp  varchar (30), in sud decimal (10, 2), in horEntr time, in horSld time, in cargId int, in encaId int)
		BEGIN
			update Empleados
				set	
					nombreEmpleado = nomEmp,
					apellidoEmpleado = apeEmp,
					sueldo = sud,
					horaEntrada = horEntr, 
					horaSalida = horSld,
					cargoId = cargId,
					encargadoId = encaId
					where empleadoId = empId;
		END $$
DELIMITER ;



-- Facturas --
DELIMITER $$
	create procedure sp_AgregarFacturas (in fech date, in hor time, in tot decimal (10, 2), in cliId int, in empId int)
		BEGIN 
			insert into Facturas (fecha, hora, total, clienteId, empleadoId)
				values (fech, hor, tot, cliId, empId);
		END $$
DELIMITER ;

call sp_AgregarFacturas('2020-05-23', '10:00:00', 100.00, 1, 1);

DELIMITER $$
create procedure sp_ListarFacturas ()
	begin
		select F.facturaId, F.fecha, F.hora, F.total,
			C.nombre as cliente,
            E.nombreEmpleado as empleado from Facturas F
            join Clientes C on F.clienteId = C.clienteId
            join Empleados E on F.empleadoId = E.empleadoId;
    end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_EliminarFacturas (in facId int)
		BEGIN
			delete
				from Facturas
					where facturaId = facId;
		END $$
DELIMITER ;

DELIMITER $$
	create procedure sp_BuscarFacturas (in facId int)
		BEGIN 
			select
				Facturas.facturaId,
				Facturas.fecha,
                Facturas.hora,
                Facturas.total,
                Facturas.clienteId,
                Facturas.empleadoId
					from Facturas
						where facturaId = facId;
		END $$
DELIMITER ;

DELIMITER $$
	create procedure sp_EditarFacturas (in facId int, in fech date, in hor time, in tot decimal (10, 2), in cliId int, in empId int)
		BEGIN
			update Facturas
				set	
					fecha = fech,
					hora = hor,
					total = tot,
					clienteId = cliId,
					empleadoId = empId
					where facturaId = facId;
		END $$
DELIMITER ;


-- TicketSoporte
DELIMITER $$
create procedure sp_agregarTicketSoporte(in descTick varchar (250), in cliId int, in facId int)
BEGIN 
	insert into TicketSoporte (descripcionTicket, estatus , clienteId , facturaId)
		values (descTick, 'Recien Creado', cliId, facId);
END $$
DELIMITER ;

call sp_agregarTicketSoporte('Problema del wifi', 1, 1);

DELIMITER $$
create procedure sp_listarTicketSoporte()
BEGIN 
	select TS.ticketSoporteId, TS.descripcionTicket, TS.estatus,
		CONCAT('Id: ', C.clienteId, ' | ', C.nombre, ' ', C.apellido) As 'cliente',
		TS.facturaId from TicketSoporte TS
	join Clientes C on TS.clienteId = C.clienteId;
END $$
DELIMITER ;
select * from TicketSoporte;

DELIMITER $$
create procedure sp_eliminarTicketSoporte(in tickSopId int)
BEGIN
	delete
		from TicketSoporte 
			where ticketSoporteId = tickSopId;
END $$
DELIMITER ;

DELIMITER $$
create procedure sp_buscarTicketSoporte(in tickSopId int)
BEGIN 
	select
		TicketSoporte.ticketSoporteId,
		TicketSoporte.descripcionTicket,
		TicketSoporte.estatus,
		TicketSoporte.clienteId,
		TicketSoporte.facturaId
			from TicketSoporte
				where ticketSoporteId = tickSopId;
END $$
DELIMITER ;
call sp_buscarTicketSoporte(1);

DELIMITER $$
create procedure sp_editarTicketSoporte(in tickSopId int, in descTick varchar (250), in est varchar (30), in cliId int, in facId int)
BEGIN
	update TicketSoporte
		set	
			descripcionTicket = descTick,
			estatus = est,
			clienteId = cliId,
			facturaId = facId
			where ticketSoporteId = tickSopId;
END $$
DELIMITER ;
select * from TicketSoporte;



-- DETALLE FACTURA --
delimiter $$
	create procedure sp_AgregarDetalleFactura  (in factId int, in proId int)
		begin 
			insert into DetalleFactura  (facturaId, productoId)
				values (factId, proId);
		end$$
delimiter ;

delimiter $$
	create procedure sp_ListarDetalleFactura  ()
		begin 
			select 
				DetalleFactura.detalleFacturaId,
				DetalleFactura.facturaId,
                DetalleFactura.productoId
					FROM DetalleFactura;
		end $$
delimiter ;

delimiter $$
	create procedure sp_EliminarDetalleFactura   (in detaFacId int)
		begin
			delete
				from DetalleFactura  
					where detalleFacturaId  = detaFacId;
		end $$
delimiter ;

delimiter $$
	create procedure sp_BuscarDetalleFactura  (in detaFacId int)
		begin 
			select
				DetalleFactura.facturaId,
                DetalleFactura.productoId
					from DetalleFactura 
						where detalleFacturaId  = detaFacId;
		end $$
delimiter ;

delimiter $$
	create procedure sp_EditarDetalleFactura  (in detaFacId int, in factId int, in proId int)
		begin
			update DetalleFactura 
				set	
					facturaId = factId,
					productoId = proId
					where detalleFacturaId  = detaFacId;
		end $$
delimiter ;


-- Usuario

delimiter $$
	create procedure sp_AgregarUsuarios  (usu varchar (30), con varchar(100), nivAccId int, emp int)
		begin 
			insert into Usuarios  (usuario, contrasenia, nivelAccesoId, empleadoId)
				values (usu, con, nivAccId, emp);
		end$$
delimiter ;
call sp_AgregarUsuarios('Kevin1', '1234', 1, 1);
select * from nivelesAcceso;

delimiter $$
	create procedure sp_BuscarUsuario (us varchar (30))
		begin
			select * from Usuarios
				where usuario = us;
		end $$
delimiter ;

 


