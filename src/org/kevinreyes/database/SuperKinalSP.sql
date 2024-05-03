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
	create procedure sp_AgregarCompras(in fechComp date, in totComp  decimal (10.2))
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
			insert into CategoriaProductos (nombreCategoria , descripcionCategoria )
			values (nom, des);
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_ListarCategoriaProductos()
		begin 
			select
				CategoriaProductos.categoriaProductoId, 
				CategoriaProductos.nombreCategoria,  
				CategoriaProductos.descripcionCategoria  
					from CategoriaProductos;
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_EliminarCategoriaProductos(in catId  int)
		begin
			delete
			from CategoriaProductos 
				where categoriaProductoId  =  catId;
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_BuscarCategoriaProductos(in catId  int)
		begin
			select 	
				CategoriaProductos.categoriaProductoId, 
				CategoriaProductos.nombreCategoria,  
				CategoriaProductos.descripcionCategoria  
					from CategoriaProductos
					where categoriaProductoId  = catId;	
		end$$
DELIMITER ;

DELIMITER $$ 
	create procedure sp_EditarCategoriaProductos(in catId int, in nom varchar (30), in des varchar (100))
		begin
			update CategoriaProductos
				set
					nombreCategoria  = nom,
					descripcionCategoria = ape
					where categoriaProductoId = catId;
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

DELIMITER $$ 
	create procedure sp_EliminarDistribuidores(in disId int)
		begin
			delete
			from Distribuidores 
				where distribuidorId =  disId;
		end$$
DELIMITER ;

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

DELIMITER $$ 
	create procedure sp_EditarDistribuidores(in disId int, in nom varchar (30), in dir varchar (200), in nit varchar(15), in tel varchar(15),  in web varchar(50))
		begin
			update Distribuidores
				set
					nombreDistribuidor = nom,
					direccionDistribuidor = dir,
					nitDistribuidor = nit,
					telefonoDistribuidor = tel ,
					web = web
					where clienteId = cliId;
	end$$
DELIMITER ;


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



-- Productos
DELIMITER $$
	create procedure sp_agregarProducto(in nom varchar(50),in des varchar(100),in can int, in preU decimal(10,2),in preM decimal(10,2),in preC decimal(10,2), in ima blob, in disId int, in catId int)
		begin
			insert into Productos(nombreProducto, descripcionProducto, cantidadStock, precioUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductosId ) 
            values(nom, des, can, preU, preM, preC, ima, disId, catId);
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_listarProducto()
		begin 
			select * from Productos;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_buscarProducto(in proId int)
		begin 
			select * from Productos
			where productoId = proId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_eliminarProducto(in proId int)
		begin
			delete from Productos
				where productoId = proId;
		end $$
DELIMITER ;

DELIMITER $$
	create procedure sp_editarProducto(in proId int, in nom varchar(50),in des varchar(100),in can int, in preU decimal(10,2),in preM decimal(10,2),in preC decimal(10,2), in ima blob, in disId int, in catId int )
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

delimiter $$
	create procedure sp_AgregarEmpleados (in nomEmp varchar (30), in apeEmp  varchar (30), in sud decimal (10, 2), in horEntr time, in horSld time, in cargId int, in encaId int)
		begin 
			insert into Empleados (nombreEmpleado , apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargoId, encargadoId)
				values (nomEmp, apeEmp, sud, horEntr, horSld, cargId, encaId);
		end$$
delimiter ;



delimiter $$
	create procedure sp_ListarEmpleados ()
		begin 
			select 
				Empleados.nombreEmpleado,
                Empleados.apellidoEmpleado,
                Empleados.sueldo,
                Empleados.horaEntrada,
                Empleados.horaSalida,
                Empleados.cargoId,
                Empleados.encargadoId
					FROM Empleados;
		end $$
delimiter ;


delimiter $$
	create procedure sp_EliminarEmpleados (in empId int)
		begin
			delete
				from Empleados
					where empleadoId = empId;
		end $$
delimiter ;

delimiter $$
	create procedure sp_BuscarEmpleados (in empId int)
		begin 
			select
				Empleados.nombreEmpleado,
                Empleados.apellidoEmpleado,
                Empleados.sueldo,
                Empleados.horaEntrada,
                Empleados.horaSalida,
                Empleados.cargoId,
                Empleados.encargadoId
					from Empleados 
						where empleadoId = empId;
		end $$
delimiter ;

delimiter $$
	create procedure sp_EditarEmpleados (in empId int, in nomEmp varchar (30), in apeEmp  varchar (30), in sud decimal (10, 2), in horEntr time, in horSld time, in cargId int, in encaId int)
		begin
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
		end $$
delimiter ;




-- FACTURAS --

delimiter $$
	create procedure sp_AgregarFacturas (in fech date, in hor time, in tot decimal (10, 2), in cliId int, in empId int)
		begin 
			insert into Facturas (fecha, hora, total, clienteId, empleadoId)
				values (fech, hor, tot, cliId, empId);
		end$$
delimiter ;


delimiter $$
	create procedure sp_ListarFacturas ()
		begin 
			select 
				Facturas.facturaId,
				Facturas.fecha,
                Facturas.hora,
                Facturas.total,
                Facturas.clienteId,
                Facturas.empleadoId
					FROM Facturas;
		end $$
delimiter ;


delimiter $$
	create procedure sp_EliminarFacturas (in facId int)
		begin
			delete
				from Facturas
					where facturaId = facId;
		end $$
delimiter ;

delimiter $$
	create procedure sp_BuscarFacturas (in facId int)
		begin 
			select
				Facturas.fecha,
                Facturas.hora,
                Facturas.total,
                Facturas.clienteId,
                Facturas.empleadoId
					from Facturas
						where facturaId = facId;
		end $$
delimiter ;

delimiter $$
	create procedure sp_EditarFacturas (in facId int, in fech date, in hor time, in tot decimal (10, 2), in cliId int, in empId int)
		begin
			update Facturas
				set	
					fecha = fech,
					hora = hor,
					total = tot,
					clienteId = cliId,
					empleadoId = empId
					where facturaId = facId;
		end $$
delimiter ;




-- TICKET SOPORTE --
delimiter $$
	create procedure sp_AgregarTicketSoporte (in descTick varchar (250), in cliId int, in facId int)
		begin 
			insert into Facturas (descripcionTicket, estatuts , clienteId , facturaId)
				values (descTick, 'Recien Creado', cliId, facId);
		end$$
delimiter ;

delimiter $$
	create procedure sp_ListarTicketSoporte ()
		begin 
			select 
				TicketSoporte.ticketSoporteId ,
				TicketSoporte.descripcionTicket,
                TicketSoporte.estatuts,
                TicketSoporte.clienteId,
                TicketSoporte.facturaId
					FROM TicketSoporte;
		end $$
delimiter ;

delimiter $$
	create procedure sp_EliminarTicketSoporte  (in tickSopId int)
		begin
			delete
				from TicketSoporte 
					where ticketSoporteId = tickSopId;
		end $$
delimiter ;

delimiter $$
	create procedure sp_BuscarTicketSoporte (in tickSopId int)
		begin 
			select
				TicketSoporte.descripcionTicket,
                TicketSoporte.estatuts,
                TicketSoporte.clienteId,
                TicketSoporte.facturaId
					from TicketSoporte
						where ticketSoporteId = tickSopId;
		end $$
delimiter ;

delimiter $$
	create procedure sp_EditarTicketSoporte (in tickSopId int, in descTick varchar (250), in est varchar (30), in cliId int, in facId int)
		begin
			update TicketSoporte
				set	
					descripcionTicket = descTick,
					estatuts = est,
					clienteId = cliId,
					facturaId = facId
					where ticketSoporteId = tickSopId;
		end $$
delimiter ;




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


 


