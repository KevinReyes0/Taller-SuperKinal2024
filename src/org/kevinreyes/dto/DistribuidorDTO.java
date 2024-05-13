/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.dto;

import org.kevinreyes.model.Distribuidores;

/**
 *
 * @author informatica
 */
public class DistribuidorDTO {
    private static DistribuidorDTO instance;
    private Distribuidores distribuidores;
    
    private DistribuidorDTO(){
    
    }
    
    public static DistribuidorDTO getDistribuidorDTO(){
        if(instance == null){
            instance = new DistribuidorDTO();
        }
        
        return instance;
    }

    public Distribuidores getDistribuidores() {
        return distribuidores;
    }

    public void setDistribuidores(Distribuidores distribuidores) {
        this.distribuidores = distribuidores;
    }
    
    
}
