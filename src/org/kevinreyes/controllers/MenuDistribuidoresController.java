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
import org.kevinreyes.dto.DistribuidorDTO;
import org.kevinreyes.model.Distribuidores;
import org.kevinreyes.system.Main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuDistribuidoresController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null; 
    
    @FXML
    TableView tblDistribuidor;
    
    @FXML
    TableColumn colDistribuidorId, colNombre, colDireccion, colNit, colTelefono, colWeb;
    
    @FXML
    Button btnAgregar, btnEditar, btnRegresar, btnEliminar, btnBuscar, btnReportes;
    
    @FXML
    TextField tfBuscar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarLista();
    }    
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnEliminar){
            int disId = ((Distribuidores)tblDistribuidor.getSelectionModel().getSelectedItem()).getDistribuidorId();
            eliminarDistribuidores(disId);
            cargarLista();
        }else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if (event.getSource() == btnBuscar){
            tblDistribuidor.getItems().clear();
            if(tfBuscar.getText().equals("")){
                cargarLista();
            }else{
                tblDistribuidor.getItems().add(buscarDistribuidores());    
                colDistribuidorId.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("distribuidorId"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("nombreDistribuidor"));
                colDireccion.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("direccionDistribuidor"));
                colNit.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("nitDistribuidor"));
                colTelefono.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("telefonoDistribuidor"));
                colWeb.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("web"));
                
            }
        }else if(event.getSource() == btnAgregar){
            stage.formDistribuidoresView(1);
        }else if(event.getSource() == btnEditar){
            DistribuidorDTO.getDistribuidorDTO().setDistribuidores((Distribuidores)tblDistribuidor.getSelectionModel().getSelectedItem());
            stage.formDistribuidoresView(2);
        }
     
    }
    
    
    public void cargarLista (){
        tblDistribuidor.setItems(listarDistribuidores());
        colDistribuidorId.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("distribuidorId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("nombreDistribuidor"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("direccionDistribuidor"));
        colNit.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("nitDistribuidor"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("telefonoDistribuidor"));
        colWeb.setCellValueFactory(new PropertyValueFactory<Distribuidores, Integer >("web"));
    }
    
    public ObservableList<Distribuidores> listarDistribuidores(){
        ArrayList<Distribuidores> distribuidores = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarDistribuidores()";
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
    
    public void eliminarDistribuidores (int disId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EliminarDistribuidores(?)";
            statement = conexion.prepareStatement (sql);
            statement.setInt(1, disId);
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
    
    public Distribuidores buscarDistribuidores(){
        Distribuidores distribuidores = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarDistribuidores(?)";
            statement = conexion.prepareStatement (sql);
            statement.setInt(1, Integer.parseInt(tfBuscar.getText()));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int distribuidorId = resultSet.getInt("distribuidorId");
                String nombre = resultSet.getString("nombreDistribuidor");
                String direccion = resultSet.getString("direccionDistribuidor");
                String nit = resultSet.getString("nitDistribuidor");
                String telefono = resultSet.getString("telefonoDistribuidor");
                String web = resultSet.getString("web");
                
                distribuidores = (new Distribuidores(distribuidorId, nombre, direccion, nit, telefono, web));
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
        return distribuidores;
        
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
    
}
