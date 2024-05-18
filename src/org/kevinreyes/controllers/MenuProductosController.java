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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.kevinreyes.dao.Conexion;
import org.kevinreyes.dto.ProductoDTO;
import org.kevinreyes.model.Productos;
import org.kevinreyes.system.Main;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class MenuProductosController implements Initializable {
    
    private Main stage;
    private int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TableView tblProductos;
    
    @FXML
    TableColumn colProductoId,colNombre,colDescripcion,colCantidad,colPrecioUnidad,colPrecioMayor,colPrecioCompra,colImagen,colDistribuidorId,colCategoriaPId;
    
    @FXML
    Button btnRegresar,btnAgregar,btnEditar,btnEliminar,btnBuscar;
    
    @FXML
    TextField tfBuscar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }  
    
    @FXML
    public void handleButtonAction(ActionEvent event){

        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnAgregar){
            stage.formProductosView(1);
        }else if(event.getSource() == btnEditar){
            ProductoDTO.getProductoDTO().setProductos((Productos)tblProductos.getSelectionModel().getSelectedItem());
            stage.formProductosView(2);
        }else if(event.getSource() == btnEliminar){
                eliminarProducto(((Productos)tblProductos.getSelectionModel().getSelectedItem()).getProductoId());
                cargarDatos();
        }else if (event.getSource() == btnBuscar){
            tblProductos.getItems().clear();
            if(tfBuscar.getText().equals("")){
                cargarDatos();
            
            }else{
              op = 3;
                cargarDatos();
           }
        }
    }
    
    public void cargarDatos(){
        if(op == 3){
            tblProductos.getItems().add(buscarProducto());
            op = 0;
        }else{
            tblProductos.setItems(listarProductos()); 
        }
            colProductoId.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("productoId"));
            colNombre.setCellValueFactory(new PropertyValueFactory<Productos, String>("nombreProducto"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<Productos, String>("descripcionProducto"));
            colCantidad.setCellValueFactory(new PropertyValueFactory<Productos, Integer>("cantidadStock"));
            colPrecioUnidad.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioVentaUnitario"));
            colPrecioMayor.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioVentaMayor"));
            colPrecioCompra.setCellValueFactory(new PropertyValueFactory<Productos, Double>("precioCompra"));
            colImagen.setCellValueFactory(new PropertyValueFactory<Productos, Blob>("imagenProducto"));
            colDistribuidorId.setCellValueFactory(new PropertyValueFactory<Productos, String>("Distribuidor"));
            colCategoriaPId.setCellValueFactory(new PropertyValueFactory<Productos, String>("Categoria"));
        
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
    
    public void eliminarProducto(int proId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EliminarProducto(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,proId);
            statement.execute();
            
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
    }
    
    public Productos buscarProducto(){
        Productos productos = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_BuscarProductos(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(tfBuscar.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
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
            
                productos = new Productos(productoId, nombre, descripcion, cantidad, precioUnitario, precioMayor, precioCompra, imagen, distribuidorId, categoriaId);
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
        return productos;
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
    
}
