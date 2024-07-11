
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
public class GenerarReporte {
    private static GenerarReporte instance;
    private static Connection conexion = null;
    private GenerarReporte(){
    
    }
    
    public static GenerarReporte getInstance (){
        if(instance == null){
            instance = new GenerarReporte();
        }
        
        return instance;
    }
    
    public void generarFactura(int factId){
        try{    
            // paso 1: Abrir la conexion a la DB
            conexion = Conexion.getInstance().obtenerConexion();
            
            // paso 2: definir los parametros del reporte
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("factId", factId);
            
            //paso 3: crear el reporte
            InputStream jasperPath = GenerarReporte.class.getResourceAsStream("/org/kevinreyes/report/Factura.jasper");
            JasperPrint reporte = JasperFillManager.fillReport(jasperPath, parametros, conexion);
            
            //paso 4: crear la ventana de javafx para mostrar el repote 
            Stage reportStage = new Stage();
            
            // paso 5: llenar el stage con el reporte
            JRViewerFX reportViewer = new JRViewerFX(reporte);
            Pane root = new Pane();
            root.getChildren().add(reportViewer);
            reportViewer.setPrefSize(1000, 800);
            Scene scene = new Scene(root);
            reportStage.setScene(scene);
            reportStage.setTitle("Factura");
            reportStage.show();
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
