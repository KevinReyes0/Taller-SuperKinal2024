/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import org.kevinreyes.dto.PromocionDTO;
import org.kevinreyes.model.Promociones;
import org.kevinreyes.system.Main;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class MenuPromocionesController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion;
    private static PreparedStatement statement;
    private static ResultSet resultSet;
    
    @FXML
    TableView tblPromociones;
    
    @FXML
    TableColumn colPromocionId,colPrecio,colDescripcion,colFechaInicio,colFechaFin,colProductoId;
    
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
    private void handleButtonAction(ActionEvent event) {
    
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnAgregar){
            stage.formPromocionesView(1);
        }else if(event.getSource() == btnEditar){
            PromocionDTO.getPromocionDTO().setPromociones((Promociones)tblPromociones.getSelectionModel().getSelectedItem());
            stage.formPromocionesView(2);
        }else if(event.getSource() == btnEliminar){
                eliminarPromocion(((Promociones)tblPromociones.getSelectionModel().getSelectedItem()).getPromocionId());
                cargarDatos();
        }else if (event.getSource() == btnBuscar){
            tblPromociones.getItems().clear();
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
            tblPromociones.getItems().add(buscarPromocion());
            op = 0;
        }else{
            tblPromociones.setItems(listarPromociones()); 
        }
            colPromocionId.setCellValueFactory(new PropertyValueFactory<Promociones, Integer>("promocionId"));
            colPrecio.setCellValueFactory(new PropertyValueFactory<Promociones, Double>("precioPromocion"));
            colDescripcion.setCellValueFactory(new PropertyValueFactory<Promociones, String>("descripcionPromocion"));
            colFechaInicio.setCellValueFactory(new PropertyValueFactory<Promociones, Date>("fechaInicio"));
            colFechaFin.setCellValueFactory(new PropertyValueFactory<Promociones, Date>("fechaFinalizacion"));
            colProductoId.setCellValueFactory(new PropertyValueFactory<Promociones, String>("producto"));;
        
    }

    
    public ObservableList<Promociones> listarPromociones(){
        ArrayList<Promociones> promociones = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = " CALL sp_listarPromociones()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int promocionId = resultSet.getInt("promocionId");
                Double precioPromocion = resultSet.getDouble("precioPromocion");
                String descripcionPromocion = resultSet.getString("descripcionPromocion");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFinal = resultSet.getDate("fechaFinalizacion");
                String productoId = resultSet.getString("producto");
            
                promociones.add(new Promociones(promocionId,precioPromocion,descripcionPromocion,fechaInicio,fechaFinal,productoId));
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
        
        
        return FXCollections.observableList(promociones);
    }
    
    public void eliminarPromocion(int promId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_eliminarPromociones(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,promId);
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
    
    public Promociones buscarPromocion(){
        Promociones promociones = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_buscarPromociones(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,Integer.parseInt(tfBuscar.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                int promocionId = resultSet.getInt("promocionId");
                Double precioPromocion = resultSet.getDouble("precioPromocion");
                String descripcionPromocion = resultSet.getString("descripcionPromocion");
                Date fechaInicio = resultSet.getDate("fechaInicio");
                Date fechaFinal = resultSet.getDate("fechaFinalizacion");
                String productoId = resultSet.getString("producto");
            
                promociones = new Promociones(promocionId,precioPromocion,descripcionPromocion,fechaInicio,fechaFinal,productoId);

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
        return promociones;
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
