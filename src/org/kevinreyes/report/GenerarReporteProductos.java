/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevinreyes.report;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.kevinreyes.dao.Conexion;
import win.zqxu.jrviewer.JRViewerFX;

/**
 *
 * @author reyes
 */
public class GenerarReporteProductos {
    private static GenerarReporteProductos instance;
    private static Connection conexion = null;
    private GenerarReporteProductos(){
    
    }
    
    public static GenerarReporteProductos getInstance (){
        if(instance == null){
            instance = new GenerarReporteProductos();
        }
        
        return instance;
    }
    
    public void generarProducto(int proId){
        try{    
            conexion = Conexion.getInstance().obtenerConexion();        
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("proId", proId); 
            InputStream jasperPath = GenerarReporte.class.getResourceAsStream("/org/kevinreyes/report/Productos.jasper");
            JasperPrint reporte = JasperFillManager.fillReport(jasperPath, parametros, conexion);
            Stage reportStage = new Stage();
            JRViewerFX reportViewer = new JRViewerFX(reporte);
            Pane root = new Pane();
            root.getChildren().add(reportViewer);
            reportViewer.setPrefSize(800, 600);
            Scene scene = new Scene(root);
            reportStage.setScene(scene);
            reportStage.setTitle("Productos");
            reportStage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
