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
import org.kevinreyes.dto.CategoriaProductosDTO;
import org.kevinreyes.model.CategoriaProductos;
import org.kevinreyes.system.Main;
import org.kevinreyes.utils.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class FormCategoriaProductosController implements Initializable {
    private Main stage;
    private int op;
    private static Connection conexion;
    private static PreparedStatement statement;
    @FXML
    TextField tfCategoriaId, tfNombreCategoria;
    
    @FXML
    TextArea taDescripcionCategoria;
    
    @FXML
    Button btnCancelar, btnGuardar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CategoriaProductosDTO.getCategoriaProductosDTO().getCategoriaProductos() != null){
            cargarDatos(CategoriaProductosDTO.getCategoriaProductosDTO().getCategoriaProductos());
        }
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuCategoriaProductos();
            CategoriaProductosDTO.getCategoriaProductosDTO().setCategoriaProductos(null);
        }else if(event.getSource() == btnGuardar){
            if(op == 1){
                if(!tfNombreCategoria.getText().equals("") && !taDescripcionCategoria.getText().equals("")){
                    agregarCategoriaProductos();
                    stage.menuCategoriaProductos();
                }else {
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(100);
                    tfNombreCategoria.requestFocus();
                    return; 
                }

            }else if(op == 2){
                editarCategoriaProductos();
                CategoriaProductosDTO.getCategoriaProductosDTO().setCategoriaProductos(null);
                stage.menuCategoriaProductos();
            }
               
        }
    }
    
    public void cargarDatos(CategoriaProductos categoriaProductos){
            tfCategoriaId.setText(Integer.toString(categoriaProductos.getCategoriaProductosId()));
            tfNombreCategoria.setText(categoriaProductos.getNombreCategoria());
            taDescripcionCategoria.setText(categoriaProductos.getDecripcionCategoria());
    }

    public void agregarCategoriaProductos(){
            try{
                conexion = Conexion.getInstance().obtenerConexion();
                String sql = "call sp_AgregarCategoriaProductos(?, ?)";
                statement = conexion.prepareStatement(sql);
                statement.setString(1, tfNombreCategoria.getText());
                statement.setString(2, taDescripcionCategoria.getText());
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
    
    public void editarCategoriaProductos(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EditarCategoriaProductos(?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCategoriaId.getText()));;
            statement.setString(2, tfNombreCategoria.getText());
            statement.setString(3, taDescripcionCategoria.getText());
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
