/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.dto;

import org.kevinreyes.model.Compras;

/**
 *
 * @author reyes
 */
public class ComprasDTO {
    private static ComprasDTO instance;
    private Compras compras;
    
    private ComprasDTO(){
    
    }
    
    public static ComprasDTO getComprasDTO(){
        if(instance == null){
            instance = new ComprasDTO();
        }
        
        return instance;
    }

    public Compras getCompras() {
        return compras;
    }

    public void setCompras(Compras compras) {
        this.compras = compras;
    }
}
