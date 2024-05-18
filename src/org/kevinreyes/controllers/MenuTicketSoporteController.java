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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.kevinreyes.dao.Conexion;
import org.kevinreyes.model.Cliente;
import org.kevinreyes.model.TicketSoporte;
import org.kevinreyes.system.Main;

/**
 * FXML Controller class
 *
 * @author reyes
 */
public class MenuTicketSoporteController implements Initializable {
    private Main stage;
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null; 
    
    @FXML
    ComboBox cmbEstatus, cmbCliente;
    
    @FXML
    Button btnRegresar, btnGuardar, btnVaciarForm;
    
    @FXML
    TableView tblClientes;
    
    @FXML
    TableColumn colTicketId, colDescripcion, colEstatus, colClienteId, colFacturaId;
    
    @FXML
    TextArea txDescripcion;
    
    @FXML
    TextField tfTicketId;
    
    @FXML
    public void handleButtonAction (ActionEvent event){
        if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnGuardar){
            if(tfTicketId.getText().equals("")){
                agregarTicket();
                cargarDatos();
            }else{
                editarTicket();
                cargarDatos();                 
            }
        }else if(event.getSource() == btnVaciarForm){
            vaciarCampos();
        }
    }
    
    // vacia todos los campos para poder agregar un nuevo ticket
    public void vaciarCampos(){
        tfTicketId.clear();
        txDescripcion.clear();
        cmbEstatus.getSelectionModel().clearSelection();
        cmbCliente.getSelectionModel().clearSelection();
    }
    
    //cargar datos en la tableView
    public void cargarDatos(){
        tblClientes.setItems(listarTickets());
        colTicketId.setCellValueFactory(new PropertyValueFactory<TicketSoporte, Integer >("ticketSoportId"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<TicketSoporte, Integer >("descripcionTicket"));
        colEstatus.setCellValueFactory(new PropertyValueFactory<TicketSoporte, Integer >("estatus"));
        colClienteId.setCellValueFactory(new PropertyValueFactory<TicketSoporte, Integer >("cliente"));
        colFacturaId.setCellValueFactory(new PropertyValueFactory<TicketSoporte, Integer >("facturaId"));
    }
    
    // carga datos en los campos a editar
    public void cargarDatosEditar (){
        TicketSoporte ts = (TicketSoporte)tblClientes.getSelectionModel().getSelectedItem();
        if(ts != null){
            tfTicketId.setText(Integer.toString(ts.getTicketSoportId()));
            txDescripcion.setText(ts.getDescripcionTicket());
            cmbEstatus.getSelectionModel().select(0);
            cmbCliente.getSelectionModel().select(obtenerIndexCliente());
        }
    }
    
    //cargar combobox de clientes
    public int obtenerIndexCliente(){
         int INDEX = 0;
         for(int i = 0; 1 <= cmbCliente.getItems().size(); i++){
             String clienteCmb = cmbCliente.getItems().get(i).toString();
             String clienteTbl = ((TicketSoporte )tblClientes.getSelectionModel().getSelectedItem()).getCliente();
             
             if(clienteCmb.equals(clienteTbl)){
                 INDEX = i;
                 break;
             }
         }
         return INDEX;
    }
    
    //cargar combobox de estatus
    public void cargarCmbEstatus(){
        cmbEstatus.getItems().add("En proceso");
        cmbEstatus.getItems().add("Finalizado");
        
    }
    
    //cosulta sp_ListarTicketSoporte
    public ObservableList<TicketSoporte> listarTickets() {
        ArrayList<TicketSoporte> tickets = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarTicketSoporte ()";
            statement = conexion.prepareStatement (sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int ticketSoporteId = resultSet.getInt("ticketSoporteId");
                String descripcion = resultSet.getString("descripcionTicket");
                String estatus = resultSet.getString ("estatus");
                String cliente = resultSet.getString ("cliente");
                int facturaId = resultSet.getInt("facturaId");
                
                tickets.add(new TicketSoporte(ticketSoporteId, descripcion, estatus, cliente, facturaId));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
                if(resultSet != null){
                    resultSet.close();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableList(tickets);
    
        }
    
    // cosulta sp_ListarClientes(se utiliza para llenar el combobox clientes)
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
    
    public void agregarTicket(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "sp_AgregarTicketSoporte(?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, txDescripcion.getText());
            statement.setInt(2, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(3, 1);
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
    
    public void editarTicket(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "sp_EditarTicketSoporte(?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfTicketId.getText()));
            statement.setString(2, txDescripcion.getText());
            statement.setString(3, cmbEstatus.getSelectionModel().getSelectedItem().toString());
            statement.setInt(4, ((Cliente)cmbCliente.getSelectionModel().getSelectedItem()).getClienteId());
            statement.setInt(5, 1);
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
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarCmbEstatus();
        cmbCliente.setItems(listarClientes()); 
        cargarDatos();
    }   

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
    
}
