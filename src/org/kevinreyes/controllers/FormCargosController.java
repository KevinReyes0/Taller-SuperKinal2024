/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.kevinreyes.dao.Conexion;
import org.kevinreyes.dto.CargoDTO;
import org.kevinreyes.model.Cargos;
import org.kevinreyes.system.Main;
import org.kevinreyes.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormCargosController implements Initializable {
    private Main stage;
    private int op;
    private static Connection conexion;
    private static PreparedStatement statement;
    @FXML
    TextField tfCargoId, tfNombreCargo;
    
    @FXML
    TextArea taDescripcionCargo;
    
    @FXML
    Button btnCancelar, btnGuardar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CargoDTO.getCargoDTO().getCargos() != null){
            cargarDatos(CargoDTO.getCargoDTO().getCargos());
        }
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuCargosView();
            CargoDTO.getCargoDTO().setCargos(null);
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombreCargo.getText().equals("") && !taDescripcionCargo.getText().equals("") ){
                    agregarCargos();
                    stage.menuCargosView();
                }else {
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(100);
                    tfNombreCargo.requestFocus();
                    return; 
                }

            }else if(op == 2){
                editarCargo();
                CargoDTO.getCargoDTO().setCargos(null);
                stage.menuCargosView();
            }
                
        }
    }
    

    public void cargarDatos(Cargos cargos){
            tfCargoId.setText(Integer.toString(cargos.getCargoId()));
            tfNombreCargo.setText(cargos.getNombreCargo());
            taDescripcionCargo.setText(cargos.getDescripcionCargo());
    }

    public void agregarCargos(){
            try{
                conexion = Conexion.getInstance().obtenerConexion();
                String sql = "call sp_AgregarCargos(?, ?)";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, tfNombreCargo.getText());
                statement.setString(2, taDescripcionCargo.getText());
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
    
    public void editarCargo(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EditarCargos(?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCargoId.getText()));;
            statement.setString(2, tfNombreCargo.getText());
            statement.setString(3, taDescripcionCargo.getText());;
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
