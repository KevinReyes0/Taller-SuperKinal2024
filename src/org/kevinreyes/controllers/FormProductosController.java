/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import org.kevinreyes.dao.Conexion;
import org.kevinreyes.dto.ProductoDTO;
import org.kevinreyes.model.CategoriaProductos;
import org.kevinreyes.model.Distribuidores;
import org.kevinreyes.model.Productos;
import org.kevinreyes.system.Main;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FormProductosController implements Initializable {
    private Main stage;
    private int op;
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    private List<File> files = null;
    
    @FXML
    Button btnCancelar,btnGuardar;
   
    @FXML
    TextField tfProductoId,tfNombre,tfDescripcion,tfCantidadStock,tfPrecioUnidad,tfPrecioMayor,tfPrecioCompra;

    @FXML
    ComboBox cmbDistribuidores,cmbCategorias;
    
    @FXML
    ImageView imgCargar, imgMostrar;
    /**
     * Initializes the controller class.
     */
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ProductoDTO.getProductoDTO().getProductos() != null){
            cargarDatos(ProductoDTO.getProductoDTO().getProductos());
        }
        cmbDistribuidores.setItems(listarDistribuidores());
        cmbCategorias.setItems(listarCategoriaProductos());
        
    }   
    @FXML
    public void handleButtonAction(ActionEvent event){
    
        if(event.getSource() == btnCancelar){
            ProductoDTO.getProductoDTO().setProductos(null);
            stage.menuProductosView();
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                agregarProducto();
                stage.menuProductosView(); 
            }else if(op == 2){
                editarProducto();
                ProductoDTO.getProductoDTO().setProductos(null);
                stage.menuProductosView(); 
            }
        }
    }
    
    @FXML
    public void handleOnDrop(DragEvent event){
        try{
            files = event.getDragboard().getFiles();
            FileInputStream file = new FileInputStream(files.get(0));
            Image image = new Image(file);
            imgCargar.setImage(image);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleOnDrag(DragEvent event){
        if(event.getDragboard().hasFiles()){
            event.acceptTransferModes(TransferMode.ANY);
        }
    }
    
    public void agregarProducto(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_agregarProductos(?,?,?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfDescripcion.getText());
            statement.setString(3, tfCantidadStock.getText());
            statement.setString(4, tfPrecioUnidad.getText());
            statement.setString(5, tfPrecioMayor.getText());
            statement.setString(6, tfPrecioCompra.getText());

            if (files != null && !files.isEmpty() && files.get(0) != null) {
                InputStream img = new FileInputStream(files.get(0));
                statement.setBinaryStream(7, img);
            } else {
                statement.setBinaryStream(7, null); 
            }
            statement.setInt(8,((Distribuidores)cmbDistribuidores.getSelectionModel().getSelectedItem()).getDistribuidorId());
            statement.setInt(9,((CategoriaProductos)cmbCategorias.getSelectionModel().getSelectedItem()).getCategoriaProductosId());
            statement.execute();
        }catch(Exception e){
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
    
    public void editarProducto(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EditarProductos(?,?,?,?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfProductoId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfDescripcion.getText());
            statement.setString(4, tfCantidadStock.getText());
            statement.setString(5, tfPrecioUnidad.getText());
            statement.setString(6, tfPrecioMayor.getText());
            statement.setString(7, tfPrecioCompra.getText());
            if (files != null && !files.isEmpty() && files.get(0) != null) {
                InputStream img = new FileInputStream(files.get(0));
                statement.setBinaryStream(8, img);
            } else {
                statement.setBinaryStream(8, null); 
            }
            statement.setInt(9,((Distribuidores)cmbDistribuidores.getSelectionModel().getSelectedItem()).getDistribuidorId());
            statement.setInt(10,((CategoriaProductos)cmbCategorias.getSelectionModel().getSelectedItem()).getCategoriaProductosId());
            statement.execute();
        }catch(Exception e){
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
   
    
    public void cargarDatos(Productos productos) {
        try {
            tfProductoId.setText(Integer.toString(productos.getProductoId()));
            tfNombre.setText(productos.getNombreProducto());
            tfDescripcion.setText(productos.getDescripcionProducto());
            tfCantidadStock.setText(Integer.toString(productos.getCantidadStock()));
            tfPrecioUnidad.setText(Double.toString(productos.getPrecioVentaUnitario()));
            tfPrecioMayor.setText(Double.toString(productos.getPrecioVentaMayor()));
            tfPrecioCompra.setText(Double.toString(productos.getPrecioCompra()));

            if (productos.getImagenProducto() != null) {
                InputStream file = productos.getImagenProducto().getBinaryStream();
                Image image = new Image(file);
                imgMostrar.setImage(image);
            } else {
                imgMostrar.setImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    
    public ObservableList<Distribuidores> listarDistribuidores(){
        ArrayList<Distribuidores> distribuidores = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_ListarDistribuidores()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int distribuidorId = resultSet.getInt("distribuidorId");
                String nombre = resultSet.getString("nombreDistribuidor");
                String direccion = resultSet.getString("direccionDistribuidor");
                String nit = resultSet.getString("nitDistribuidor");
                String telefono = resultSet.getString("telefonoDistribuidor");
                String web = resultSet.getString("web");
            
                distribuidores.add(new Distribuidores(distribuidorId, nombre, direccion, nit, telefono, web));
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
        
        
        return FXCollections.observableList(distribuidores);
    }
    
    public ObservableList<CategoriaProductos> listarCategoriaProductos(){
        ArrayList<CategoriaProductos> categoriaProductos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarCategoriaProductos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int categoriaId = resultSet.getInt("categoriaProductosId");
                String nombre = resultSet.getString("nombreCategoria");
                String descripcion = resultSet.getString("decripcionCategoria");
                
                categoriaProductos.add(new CategoriaProductos(categoriaId, nombre, descripcion));
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
        
        
        return FXCollections.observableList(categoriaProductos);
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
