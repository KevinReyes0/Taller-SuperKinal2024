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
import javafx.scene.control.TextField;
import org.kevinreyes.dao.Conexion;
import org.kevinreyes.dto.DistribuidorDTO;
import org.kevinreyes.model.Distribuidores;

import org.kevinreyes.system.Main;
import org.kevinreyes.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormDistribuidoresController implements Initializable {
    private Main stage;
    private int op;
    private static Connection conexion;
    private static PreparedStatement statement;
    
    @FXML
    TextField tfDistribuidorId, tfNombre, tfDireccion, tfNit, tfNoTelefono, tfWeb;
    @FXML
    Button btnCancelar, btnGuardar;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(DistribuidorDTO.getDistribuidorDTO().getDistribuidores() != null){
            cargarDatos(DistribuidorDTO.getDistribuidorDTO().getDistribuidores());
        }
    } 
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuDistribuidoresView();
            DistribuidorDTO.getDistribuidorDTO().setDistribuidores(null);
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombre.getText().equals("") && !tfDireccion.getText().equals("") && !tfNit.getText().equals("") && !tfNoTelefono.getText().equals("") && !tfWeb.getText().equals("")){
                    agregarCliente();
                    stage.menuDistribuidoresView();
                }else {
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(100);
                    tfNombre.requestFocus();
                    return; 
                }

            }else if(op == 2){
                editarDistribuidores();
                DistribuidorDTO.getDistribuidorDTO().setDistribuidores(null);
                stage.menuDistribuidoresView();
            
            }
             
        }
    }
    
    public void cargarDatos(Distribuidores distribuidores){
        tfDistribuidorId.setText(Integer.toString(distribuidores.getDistribuidorId()));
        tfNombre.setText(distribuidores.getNombreDistribuidor());
        tfDireccion.setText(distribuidores.getDireccionDistribuidor());
        tfNit.setText(distribuidores.getNitDistribuidor());
        tfNoTelefono.setText(distribuidores.getTelefonoDistribuidor());
        tfWeb.setText(distribuidores.getWeb());
        
    }
    
    public void agregarCliente(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_AgregarDistribuidores(?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfDireccion.getText());
            statement.setString(3, tfNit.getText());
            statement.setString(4, tfNoTelefono.getText());
            statement.setString(5, tfWeb.getText());
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
    
    public void editarDistribuidores(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EditarDistribuidores(?, ?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfDistribuidorId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfDireccion.getText());
            statement.setString(4, tfNit.getText());
            statement.setString(5, tfNoTelefono.getText());
            statement.setString(6, tfWeb.getText());;
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

    
