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
import org.kevinreyes.dto.CargoDTO;
import org.kevinreyes.model.Cargos;
import org.kevinreyes.system.Main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuCargosController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null; 
    
    @FXML
    TableView tblCargos;
    
    @FXML
    TableColumn colCargoId, colNombreCargo, colDescripcionCargo;
    
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
            int carId = ((Cargos)tblCargos.getSelectionModel().getSelectedItem()).getCargoId();
            eliminarCargos(carId);
            cargarLista();
        }else if (event.getSource() == btnBuscar){
            tblCargos.getItems().clear();
            if(tfBuscar.getText().equals("")){
                cargarLista();
            }else{
                tblCargos.getItems().add(buscarCargo());
                colCargoId.setCellValueFactory(new PropertyValueFactory<Cargos, Integer >("cargoId"));
                colNombreCargo.setCellValueFactory(new PropertyValueFactory<Cargos, Integer >("nombreCargo"));
                colDescripcionCargo.setCellValueFactory(new PropertyValueFactory<Cargos, Integer >("descripcionCargo"));
            }
        }else if(event.getSource() == btnAgregar){
            stage.formCargosView(1);
        }else if(event.getSource() == btnEditar){
            CargoDTO.getCargoDTO().setCargos((Cargos)tblCargos.getSelectionModel().getSelectedItem());
            stage.formCargosView(2);
        }else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }
    }
    
    public void cargarLista (){
        tblCargos.setItems(listarCargos());
        colCargoId.setCellValueFactory(new PropertyValueFactory<Cargos, Integer >("cargoId"));
        colNombreCargo.setCellValueFactory(new PropertyValueFactory<Cargos, Integer >("nombreCargo"));
        colDescripcionCargo.setCellValueFactory(new PropertyValueFactory<Cargos, Integer >("descripcionCargo"));

    }
    
    public ObservableList<Cargos> listarCargos(){
        ArrayList<Cargos> cargos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarCargos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int cargoId = resultSet.getInt("cargoId");
                String nombre = resultSet.getString("nombreCargo");
                String descripcion = resultSet.getString("descripcionCargo");
                
                cargos.add(new Cargos(cargoId, nombre, descripcion));
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
        return FXCollections.observableList(cargos);
    }
    
    public void eliminarCargos(int carId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EliminarCargos(?)";
            statement = conexion.prepareStatement (sql);
            statement.setInt(1, carId);
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
    
    public Cargos buscarCargo(){
        Cargos cargos = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarCargos(?)";
            statement = conexion.prepareStatement (sql);
            statement.setInt(1, Integer.parseInt(tfBuscar.getText()));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int cargoId = resultSet.getInt("cargoId");
                String nombre = resultSet.getString("nombreCargo");
                String descripcion = resultSet.getString("descripcionCargo");
                
                cargos = (new Cargos(cargoId, nombre, descripcion));
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
        return cargos;
        
    }
 
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
    
    
    
}
