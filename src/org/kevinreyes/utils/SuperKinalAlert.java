/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.utils;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author informatica
 */
public class SuperKinalAlert {
    private static SuperKinalAlert instance;
    
    private SuperKinalAlert(){
    
    }
    
    public static SuperKinalAlert getInstance(){
        if(instance == null){
            instance = new SuperKinalAlert();
        }
        
        return instance;
    }
    
    public void mostrarAlertaInfo(int code){
         if(code == 100){//alerta campos pendientes por llenar
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Campos Vacios");
             alert.setHeaderText("Campors Vacios");
             alert.setContentText("Campos necesarios no han sido completados");
             alert.showAndWait(); 
         }else if(code == 101){//alerta confirmacion de creacion de registro
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setTitle("Confirmacion de registro");
             alert.setHeaderText("Confirmacion de registro");
             alert.setContentText("El registro se ha creado con exito");
             alert.showAndWait(); 
         }
    }
    
    public Optional<ButtonType> mostarAlertaConfirmacio(int code){
        Optional<ButtonType> action = null;
        
        return action;
    }
}
