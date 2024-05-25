/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.dto;

import org.kevinreyes.model.Promociones;

/**
 *
 * @author reyes
 */
public class PromocionDTO {
    private static PromocionDTO instance;
    private Promociones promociones;
    
    private PromocionDTO(){
    
    }
    
    public static PromocionDTO getPromocionDTO(){
        if(instance == null){
            instance = new PromocionDTO();
        }
        
        return instance;
    }

    public Promociones getPromociones() {
        return promociones;
    }

    public void setPromociones(Promociones promociones) {
        this.promociones = promociones;
    }
    
    

}
