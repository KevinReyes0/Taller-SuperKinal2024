/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.controllers;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.kevinreyes.dao.Conexion;
import org.kevinreyes.dto.CategoriaProductosDTO;
import org.kevinreyes.model.CategoriaProductos;
import org.kevinreyes.system.Main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuCategoriaProductosController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null; 
    
    @FXML
    TableView tblCategoriaId;
    
    @FXML
    TableColumn colCategoriaId, colNombreCategoria, colDescripcionCategoria;
    
    @FXML
    Button  btnEliminar, btnBuscar, btnAgregar, btnEditar, btnReportes, btnRegresar;
    
    @FXML
    TextField tfBuscar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarLista();
    }  
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnEliminar){
            int catId = ((CategoriaProductos)tblCategoriaId.getSelectionModel().getSelectedItem()).getCategoriaProductosId();
            eliminarCategoriaPorductos(catId);
            cargarLista();
        }else if (event.getSource() == btnBuscar){
            tblCategoriaId.getItems().clear();
            if(tfBuscar.getText().equals("")){
                cargarLista();
            }else{
                tblCategoriaId.getItems().add(buscarCategoriaProductos());
                colCategoriaId.setCellValueFactory(new PropertyValueFactory<CategoriaProductos, Integer >("categoriaProductosId"));
                colNombreCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProductos, Integer >("nombreCategoria"));
                colDescripcionCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProductos, Integer >("decripcionCategoria"));
            }
        }else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnAgregar){
            stage.formCategoriaProductosView(1);
        }else if(event.getSource() == btnEditar){
            CategoriaProductosDTO.getCategoriaProductosDTO().setCategoriaProductos((CategoriaProductos)tblCategoriaId.getSelectionModel().getSelectedItem());
            stage.formCategoriaProductosView(2);
        }
    }
    
    
    public void cargarLista (){
        tblCategoriaId.setItems(listarCategoriaProductos());
        colCategoriaId.setCellValueFactory(new PropertyValueFactory<CategoriaProductos, Integer >("categoriaProductosId"));
        colNombreCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProductos, Integer >("nombreCategoria"));
        colDescripcionCategoria.setCellValueFactory(new PropertyValueFactory<CategoriaProductos, Integer >("decripcionCategoria"));

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
    
    public void eliminarCategoriaPorductos(int catId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EliminarCategoriaProductos(?)";
            statement = conexion.prepareStatement (sql);
            statement.setInt(1, catId);
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
    
    public CategoriaProductos buscarCategoriaProductos(){
        CategoriaProductos categoriaProductos = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarCategoriaProductos(?)";
            statement = conexion.prepareStatement (sql);
            statement.setInt(1, Integer.parseInt(tfBuscar.getText()));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int categoriaId = resultSet.getInt("categoriaProductosId");
                String nombre = resultSet.getString("nombreCategoria");
                String descripcion = resultSet.getString("decripcionCategoria");
                
                categoriaProductos = (new CategoriaProductos(categoriaId, nombre, descripcion));
            }   
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
        return categoriaProductos;
        
    }
    

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
    
    
}
