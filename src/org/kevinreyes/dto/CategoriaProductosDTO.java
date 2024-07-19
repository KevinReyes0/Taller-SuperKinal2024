/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.dto;

import org.kevinreyes.model.CategoriaProductos;
/**
 *
 * @author informatica
 */
public class CategoriaProductosDTO {
    private static CategoriaProductosDTO instance;
    private CategoriaProductos categoriaProductos;

    public CategoriaProductosDTO() {
    }
    
    public static CategoriaProductosDTO getCategoriaProductosDTO(){
        if(instance == null){
            instance = new CategoriaProductosDTO();
        }
        
        return instance;
    }

    public CategoriaProductos getCategoriaProductos() {
        return categoriaProductos;
    }

    public void setCategoriaProductos(CategoriaProductos categoriaProductos) {
        this.categoriaProductos = categoriaProductos;
    }
    
    
}
