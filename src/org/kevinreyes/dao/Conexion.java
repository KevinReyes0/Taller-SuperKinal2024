/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.dao;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author reyes
 */
public class Conexion {
    private static Conexion instance;
    
    private String url = "jdbc:mysql://localhost:3306/SuperKinal?serverTimezone=GMT-6&useSSL=false";
    private String user = "root";
    private String password = "2007108kr";
     
        private Conexion(){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch(ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
        }

        public static Conexion getInstance(){
            if(instance == null){
                instance = new Conexion();
            }

            return instance;
        }
        
        public Connection obtenerConexion()throws SQLException{
            return DriverManager.getConnection(url, user, password);
        }
        
        
       
}
