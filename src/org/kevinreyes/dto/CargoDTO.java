/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.dto;

import org.kevinreyes.model.Cargos;

/**
 *
 * @author informatica
 */
public class CargoDTO {
    private static CargoDTO instance;
    private Cargos cargos;

    public CargoDTO() {
    }
    
    public static CargoDTO getCargoDTO(){
        if(instance == null){
            instance = new CargoDTO();
        }
        
        return instance;
    }

    public static CargoDTO getInstance() {
        return instance;
    }

    public static void setInstance(CargoDTO instance) {
        CargoDTO.instance = instance;
    }

    public Cargos getCargos() {
        return cargos;
    }

    public void setCargos(Cargos cargos) {
        this.cargos = cargos;
    }
    
    

}
