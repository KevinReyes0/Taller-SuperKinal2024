/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.controllers;

import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.kevinreyes.dao.Conexion;
import org.kevinreyes.dto.PromocionDTO;
import org.kevinreyes.model.Productos;
import org.kevinreyes.model.Promociones;
import org.kevinreyes.system.Main;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FormPromocionesController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
   @FXML
    Button btnRegresar,btnGuardar;
   
   @FXML
   TextField tfPromocionId,tfPrecio,tfDescripcion,tfFechaIn,tfFechaFin;
   
   @FXML
   ComboBox cmbProducto;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(PromocionDTO.getPromocionDTO().getPromociones() != null){
            cargarDatos(PromocionDTO.getPromocionDTO().getPromociones());
        }
        cmbProducto.setItems(listarProductos());
    }  
    
     @FXML
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresar){
            PromocionDTO.getPromocionDTO().setPromociones(null);
            stage.menuPromocionesView();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                    agregarPromociones();
                    stage.menuPromocionesView();
   
            }else if(op == 2){
                    editarPromociones();
                    PromocionDTO.getPromocionDTO().setPromociones(null);
                    stage.menuPromocionesView();
                
            }
        }
    }

    
    public void cargarDatos(Promociones promociones){
        tfPromocionId.setText(Integer.toString(promociones.getPromocionId()));
        tfPrecio.setText(Double.toString(promociones.getPrecioPromocion()));
        tfDescripcion.setText(promociones.getDescripcionPromocion());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
        tfFechaIn.setText(formatoFecha.format(promociones.getFechaInicio()));
        tfFechaFin.setText(formatoFecha.format(promociones.getFechaFinalizacion()));
    }
    
    public void agregarPromociones(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_agregarPromociones(?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfPrecio.getText());
            statement.setString(2, tfDescripcion.getText());
            statement.setString(3, tfFechaIn.getText());
            statement.setString(4, tfFechaFin.getText());
            statement.setInt(5,((Productos)cmbProducto.getSelectionModel().getSelectedItem()).getProductoId());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void editarPromociones(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EditarPromociones(?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfPromocionId.getText()));
            statement.setString(2, tfPrecio.getText());
            statement.setString(3, tfDescripcion.getText());
            statement.setString(4, tfFechaIn.getText());
            statement.setString(5, tfFechaFin.getText());
            statement.setInt(6,((Productos)cmbProducto.getSelectionModel().getSelectedItem()).getProductoId());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
   public ObservableList<Productos> listarProductos(){
        ArrayList<Productos> productos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int productoId = resultSet.getInt("productoId");
                String nombre = resultSet.getString("nombreProducto");
                String descripcion = resultSet.getString("descripcionProducto");
                int cantidad = resultSet.getInt("cantidadStock");
                double precioUnitario = resultSet.getDouble("precioVentaUnitario");
                double precioMayor = resultSet.getDouble("precioVentaMayor");
                double precioCompra = resultSet.getDouble("precioCompra");
                Blob imagen = resultSet.getBlob("imagenProducto");
                String distribuidorId = resultSet.getString("distribuidor");
                String categoriaId = resultSet.getString("Categoria");
            
                productos.add(new Productos(productoId, nombre, descripcion, cantidad, precioUnitario, precioMayor, precioCompra, imagen, distribuidorId, categoriaId));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
                }
                
                if(statement != null){
                    statement.close();
                }
                
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
        
        return FXCollections.observableList(productos);
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }
    
   
    
}
