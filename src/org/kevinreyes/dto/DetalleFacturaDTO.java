/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.dto;

import org.kevinreyes.model.DetalleFactura;

/**
 *
 * @author reyes
 */
public class DetalleFacturaDTO {
    private static DetalleFacturaDTO instance;
    private DetalleFactura detalleFactura;
    
    private DetalleFacturaDTO(){
    
    }
    
    public static DetalleFacturaDTO getDetalleFacturaDTO(){
        if(instance == null){
            instance = new DetalleFacturaDTO();
        }
        
        return instance;
    }

    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }
    
    
}
