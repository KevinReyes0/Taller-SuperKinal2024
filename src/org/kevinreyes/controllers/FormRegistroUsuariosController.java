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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.kevinreyes.dao.Conexion;
import org.kevinreyes.model.Empleado;
import org.kevinreyes.model.NivelesAcceso;
import org.kevinreyes.system.Main;
import org.kevinreyes.utils.PasswordUtils;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class FormRegistroUsuariosController implements Initializable {
    private Main Stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    TextField tfUsuario,tfContrasenia;
    @FXML
    ComboBox cmbEmpleado,cmbNivelAcceso;
    @FXML
    Button btnGuardar,btnCancelar,btnRegistrarEmpleado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbEmpleado.setItems(listarEmpleados());
        cmbNivelAcceso.setItems(listarNivelesAcceso());
    }  
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnGuardar){
            agregarUsuarios();
            Stage.loginView();
        }else if(event.getSource() == btnCancelar){
            Stage.loginView();
        }else if (event.getSource() == btnRegistrarEmpleado){
            Stage.formEmpleadosView(3);
        } 
    }
    
     public ObservableList<Empleado> listarEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarEmpleados()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int empleadoId = resultSet.getInt("empleadoId");
                String nombreEmpleado = resultSet.getString("nombreEmpleado");
                String apellidoEmpleado = resultSet.getString("apellidoEmpleado");
                double sueldo = resultSet.getDouble("sueldo");
                String horaEntrada = resultSet.getString("horaEntrada");
                String horaSalida = resultSet.getString("horaSalida");
                String cargo = resultSet.getString("cargo");
                String encargado = resultSet.getString("nombreEncargado");

                empleados.add(new Empleado(empleadoId, nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargo, encargado));
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
        return FXCollections.observableList(empleados);
    }
     
    public void agregarUsuarios(){
        try{
           conexion = Conexion.getInstance().obtenerConexion();
           String sql = "call sp_AgregarUsuarios(?,?,?,?)";
           statement = conexion.prepareStatement(sql);
           statement.setString(1, tfUsuario.getText());
           statement.setString(2, PasswordUtils.getInstance().encrytedPaaword(tfContrasenia.getText()));
           statement.setInt(3, ((NivelesAcceso)cmbNivelAcceso.getSelectionModel().getSelectedItem()).getNivelAccesoId());
           statement.setInt(4, ((Empleado)cmbEmpleado.getSelectionModel().getSelectedItem()).getEmpleadoId());
           statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    
    public ObservableList<NivelesAcceso> listarNivelesAcceso(){
        ArrayList<NivelesAcceso> nivelesAcceso = new ArrayList<>();
         
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarNivelesAcceso()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int nivelAccesoId = resultSet.getInt("nivelAccesoId");
                String nivelAcceso = resultSet.getString("nivelAcceso");
                
                nivelesAcceso.add(new NivelesAcceso(nivelAccesoId,nivelAcceso));
            }
        }catch(SQLException e){
                System.out.println(e.getMessage());
                e.printStackTrace();

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
                    e.printStackTrace();

                } 
            }
        
        return FXCollections.observableList(nivelesAcceso);
    }

    public Main getStage() {
        return Stage;
    }

    public void setStage(Main Stage) {
        this.Stage = Stage;
    }
    
    
    
}
