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
import org.kevinreyes.dto.ClienteDTO;
import org.kevinreyes.model.Cliente;
import org.kevinreyes.report.GenerarReporteUsuario;
import org.kevinreyes.system.Main;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class MenuClientesController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null; 
    
    @FXML
    TableView tblClientes;
    
    @FXML
    TableColumn colClienteId, colNombre, colApellido, colTelefono, colDireccion, colNit;
    
    @FXML
    Button btnAgregar, btnEditar, btnRegresar, btnEliminar, btnBuscar, btnReportes, btnVerCliente;
    
    @FXML
    TextField tfBuscar;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      cargarLista(); 
      
    } 
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnAgregar){
            stage.formClientesView(1);
        }else if(event.getSource() == btnEditar){
            ClienteDTO.getClienteDTO().setCliente((Cliente)tblClientes.getSelectionModel().getSelectedItem());
            stage.formClientesView(2);
        }else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if (event.getSource() == btnEliminar){
            int cliId = ((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getClienteId();
            eliminarClientes(cliId);
            cargarLista();
        }else if (event.getSource() == btnBuscar){
            tblClientes.getItems().clear();
            if(tfBuscar.getText().equals("")){
                cargarLista();
            }else{
                tblClientes.getItems().add(buscarCliente());    
                colClienteId.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("clienteId"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("nombre"));
                colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("apellido"));
                colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("telefono"));
                colDireccion.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("direccion"));
                colNit.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("nit"));
                
            }
        }else if(event.getSource() == btnVerCliente){
            GenerarReporteUsuario.getInstance().generarCliente(((Cliente)tblClientes.getSelectionModel().getSelectedItem()).getClienteId());
        }
     
    }
    
    public void cargarLista (){
        tblClientes.setItems(listarClientes());
        colClienteId.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("clienteId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("direccion"));
        colNit.setCellValueFactory(new PropertyValueFactory<Cliente, Integer >("nit"));
    }
    
    public ObservableList<Cliente> listarClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarClientes()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int clienteId = resultSet.getInt("clienteId");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                String nit = resultSet.getString("nit");
                
                clientes.add(new Cliente(clienteId, nombre, apellido, telefono, direccion, nit));
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
        return FXCollections.observableList(clientes);
    }
    
    public void eliminarClientes(int cliId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_EliminarClientes(?)";
            statement = conexion.prepareStatement (sql);
            statement.setInt(1, cliId);
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
    
    public Cliente buscarCliente(){
        Cliente cliente = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_BuscarClientes(?)";
            statement = conexion.prepareStatement (sql);
            statement.setInt(1, Integer.parseInt(tfBuscar.getText()));
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                int clienteId = resultSet.getInt("clienteId");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String telefono = resultSet.getString("telefono");
                String direccion = resultSet.getString("direccion");
                String nit = resultSet.getString("nit");
                
                cliente = (new Cliente(clienteId, nombre, apellido, telefono, direccion, nit));
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
        return cliente;
        
    }
    

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
    
}
