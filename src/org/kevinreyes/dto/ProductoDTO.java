/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.dto;

import org.kevinreyes.model.Productos;

/**
 *
 * @author reyes
 */
public class ProductoDTO {
    private static ProductoDTO instance;
    private Productos productos;
    
    private ProductoDTO(){
    
    }
    
    public static ProductoDTO getProductoDTO(){
        if(instance == null){
            instance = new ProductoDTO();
        }
        
        return instance;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

}
