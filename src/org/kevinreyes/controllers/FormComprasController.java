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
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import org.kevinreyes.dao.Conexion;
import org.kevinreyes.dto.ComprasDTO;
import org.kevinreyes.model.Compras;
import org.kevinreyes.system.Main;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FormComprasController implements Initializable {
    private Main stage;
    private int op;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
   @FXML
    Button btnCancelar, btnGuardar;
   
   @FXML
   TextField tfComprasId, tfFecha, tfTotal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(ComprasDTO.getComprasDTO().getCompras() != null){
            cargarDatos(ComprasDTO.getComprasDTO().getCompras());
        }
    } 
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnCancelar) {
            ComprasDTO.getComprasDTO().setCompras(null);
            stage.menuComprasView();
        } else if (event.getSource() == btnGuardar) {
            stage.menuComprasView();
            if (op == 2) {
                    editarCompra();
                    ComprasDTO.getComprasDTO().setCompras(null);
                    stage.menuComprasView();
            }
        }
    
    }

    
    public void cargarDatos(Compras compras) {
    tfComprasId.setText(Integer.toString(compras.getCompraId()));

    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
    tfFecha.setText(formato.format(compras.getFechaCompra()));
    }

    
    public void editarCompra(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "CALL sp_EditarCompras(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfComprasId.getText()));
            statement.setString(2,tfFecha.getText());
            statement.setString(3,tfTotal.getText());
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
