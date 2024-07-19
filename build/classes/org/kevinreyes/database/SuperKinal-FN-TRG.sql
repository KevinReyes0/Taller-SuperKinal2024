use SuperKinal;

-- funcion calcular total
DELIMITER $$
	create function fn_calcularTotal(factId int) returns decimal(10,2) deterministic
    begin
		declare total decimal(10,2) default 0.0;
        declare precio decimal(10,2);
        declare i int default 1;
        declare curFacturaId, curProductoId int;
        declare cursorDetalleFactura cursor for 
			select DF.facturaId, DF.productoId from DetalleFactura DF;
		open cursorDetalleFactura;
        totalLoop : loop
        fetch cursorDetalleFactura into curFacturaId,curProductoId;
        if (factID = curFacturaId)then
			set precio = (select P.precioVentaUnitario from Productos P where P.productoId = curProductoId);
            set total = total + precio;
        end if;
        if i = 	(select count(*)from DetalleFactura)then
        leave totalLoop;
        end if;
        set i = i + 1;
        end loop totalLoop;
        call sp_asignarTotal(total,factId);
        return total;
    end$$
DELIMITER ;
select fn_calcularTotal(1);
select * from facturas;
DELIMITER $$
	create trigger tg_totalFactura
    after insert on DetalleFactura
    for each row
    begin
		declare total decimal(10,2);
        set total = fn_calcularTotal(new.facturaId);
    end$$
DELIMITER ;
tiene menú contextual

Delimiter $$
create function fn_eliminarStock(productId int) returns int deterministic
begin
    declare stockActual int default 0;
    declare cantidadComprada int default 0;

    select cantidadStock into cantidadComprada from Productos where productoId = productId;
    
    set stockActual = cantidadComprada - 1;
    
    call sp_modificarStock(productId, stockActual);
    
    return stockActual;
end $$
Delimiter ;

select fn_eliminarStock(1);

Delimiter $$
create function fn_aumentarStock(productId int) returns int deterministic
begin
    declare stockActual int default 0;
    declare cantidadComprada int default 0;
    declare cantidad int default 0;
	
    select cantidadStock into cantidad from productos where productoId = productId LIMIT 1;
    select cantidadCompra into cantidadComprada from detalleCompra where productoId = productId LIMIT 1;
    
    set stockActual = stockActual + cantidadComprada + cantidad;
    
    call sp_modificarStockCompra(productId, stockActual);
    
    return stockActual;
end $$
Delimiter ;



