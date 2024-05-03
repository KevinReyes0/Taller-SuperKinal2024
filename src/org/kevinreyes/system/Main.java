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
import org.kevinreyes.controllers.MenuClientesController;
import org.kevinreyes.controllers.MenuPrincipalController;
import org.kevinreyes.controllers.FormClientesController;
import org.kevinreyes.controllers.MenuCargosController;
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
