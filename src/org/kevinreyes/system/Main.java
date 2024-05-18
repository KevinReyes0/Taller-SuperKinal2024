/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.system;

import javafx.application.Application;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.InputStream;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import org.kevinreyes.controllers.FormCargosController;
import org.kevinreyes.controllers.FormCategoriaProductosController;
import org.kevinreyes.controllers.MenuClientesController;
import org.kevinreyes.controllers.MenuPrincipalController;
import org.kevinreyes.controllers.FormClientesController;
import org.kevinreyes.controllers.FormComprasController;
import org.kevinreyes.controllers.FormDistribuidoresController;
import org.kevinreyes.controllers.FormEmpleadosController;
import org.kevinreyes.controllers.FormProductosController;
import org.kevinreyes.controllers.MenuCargosController;
import org.kevinreyes.controllers.MenuCategoriaProductosController;
import org.kevinreyes.controllers.MenuComprasController;
import org.kevinreyes.controllers.MenuDistribuidoresController;
import org.kevinreyes.controllers.MenuEmpleadosController;
import org.kevinreyes.controllers.MenuFacturasController;
import org.kevinreyes.controllers.MenuProductosController;
import org.kevinreyes.controllers.MenuTicketSoporteController;

/**
 *
 * @author reyes
 */
public class Main extends Application {
    private Stage stage;
    private Scene scene;
    private final String URLVIEW = "/org/kevinreyes/view/";
    @Override
    public void start(Stage stage){
        this.stage = stage;
        stage.setTitle("Super Despensa App");
        menuPrincipalView();    
       
        stage.show();
    }
    
    public Initializable switchScene(String fxmlName, int width, int height) throws Exception{
        Initializable resultado;
        
        FXMLLoader loader = new FXMLLoader();
        
        InputStream file = Main.class.getResourceAsStream(URLVIEW + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(URLVIEW + fxmlName));
        
        scene = new Scene((AnchorPane)loader.load(file), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        
        resultado = (Initializable)loader.getController();
        return resultado;
    }
    
    public void menuPrincipalView(){
        try{
            MenuPrincipalController menuPrincipalView = (MenuPrincipalController)switchScene("MenuPrincipalView.fxml", 980, 725);
            menuPrincipalView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    //Cargos
    public void menuCargosView(){
        try{
            MenuCargosController menuCargosView = (MenuCargosController)switchScene("MenuCargosView.fxml", 1350, 650);
            menuCargosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
     public void formCargosView(int op){
        try{
            FormCargosController formCargosView = (FormCargosController)switchScene("FormCargosView.fxml", 500, 640);
            formCargosView.setOp(op);
            formCargosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
     
    
     
    //Compras
    public void menuComprasView(){
        try{
            MenuComprasController menuComprasView = (MenuComprasController)switchScene("MenuComprasView.fxml", 1350, 650);
            menuComprasView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void formComprasView(int op){
        try{
            FormComprasController formComprasView = (FormComprasController)switchScene("FormComprasView.fxml", 500, 640);
            formComprasView.setOp(op);
            formComprasView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    //CategoriaProductos
    public void menuCategoriaProductos(){
        try{
            MenuCategoriaProductosController menuCategoriaProductosView = (MenuCategoriaProductosController)switchScene("MenuCategoriaProductosView.fxml", 1350, 650);
            menuCategoriaProductosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void formCategoriaProductosView(int op){
        try{
            FormCategoriaProductosController formCategoriaProductosView = (FormCategoriaProductosController)switchScene("FormCategoriaProductosView.fxml", 500, 640);
            formCategoriaProductosView.setOp(op);
            formCategoriaProductosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    //Distribuidores
    public void menuDistribuidoresView(){
        try{
            MenuDistribuidoresController menuDistribuidoresView = (MenuDistribuidoresController)switchScene("MenuDistribuidoresView.fxml", 1350, 650);
            menuDistribuidoresView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void formDistribuidoresView(int op){
        try{
            FormDistribuidoresController formDistribuidoresView = (FormDistribuidoresController)switchScene("FormDistribuidoresView.fxml", 500, 640);
            formDistribuidoresView.setOp(op);
            formDistribuidoresView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
     
    //Clientes
    public void menuClientesView(){
        try{
            MenuClientesController menuClientesView = (MenuClientesController)switchScene("MenuClientesView.fxml", 1350, 650);
            menuClientesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    public void formClientesView(int op){
        try{
            FormClientesController formClientesView = (FormClientesController)switchScene("FormClientesView.fxml", 500, 640);
            formClientesView.setOp(op);
            formClientesView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    //Empleados
    public void menuEmpleadosView(){
        try{
            MenuEmpleadosController menuEmpleadosView = (MenuEmpleadosController)switchScene("MenuEmpleadosView.fxml", 1350, 650);
            menuEmpleadosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void formEmpleadosView(int op){
        try{
            FormEmpleadosController formEmpleadosView = (FormEmpleadosController)switchScene("FormEmpleadosView.fxml", 500, 640);
            formEmpleadosView.setOp(op);
            formEmpleadosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    //Productos
    public void menuProductosView(){
        try{
            MenuProductosController menuProductosView = (MenuProductosController)switchScene("MenuProductosView.fxml", 1350, 650);
            menuProductosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void formProductosView(int op){
        try{
            FormProductosController formProductosView = (FormProductosController)switchScene("FormProductosView.fxml", 100, 600);
            formProductosView.setOp(op);
            formProductosView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    //Factura
    public void menuFacturaView(){
        try{
            MenuFacturasController menuFacturasView = (MenuFacturasController)switchScene("MenuFacturasView.fxml", 1350, 650);
            menuFacturasView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    
    //Ticket Soporte
    public void menuTicketSoporteView(){
        try{
            
            MenuTicketSoporteController menuTicketSoporteView = (MenuTicketSoporteController)switchScene("MenuTicketSoporteView.fxml", 1350, 650);
            menuTicketSoporteView.setStage(this);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
}
