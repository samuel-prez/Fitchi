package jasper.utilidades;

import jasper.conexiones.ConnectionUtils;
import javax.ejb.Stateless;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

/**
 *
 * @author Samuel P.
 */
@Stateless
public class JasperUtilidadesBean implements JasperUtilidadesBeanLocal {

    @Override
    public void generarReporte(String ruraArchivoJrxml, Map<String, Object> parametros,
            String rutaCarpetaDestino, String nombrePdfGenerado) {
        try {
            // Compilar el jrxml.
            //System.out.println("-----rutaReporte " + ruraArchivoJrxml);
            JasperReport jasperReport = JasperCompileManager.compileReport(ruraArchivoJrxml);
            Connection conn = ConnectionUtils.getConnection();
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, conn);
            // Buscarlo en un directorio ya existente.
            File outDir = new File(rutaCarpetaDestino);
            outDir.mkdirs();
            // PDF Exporter.
            JRPdfExporter exporter = new JRPdfExporter();
            ExporterInput exporterInput = new SimpleExporterInput(print);
            // ExporterInput
            exporter.setExporterInput(exporterInput);
            // ExporterOutput
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                    rutaCarpetaDestino + File.separator + nombrePdfGenerado);
            // Output
            exporter.setExporterOutput(exporterOutput);
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();

            //System.out.print("Done!");
        } catch (JRException ex) {
            Logger.getLogger(JasperUtilidadesBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JasperUtilidadesBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JasperUtilidadesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void descargarReporte(String ruraArchivoJrxml, Map<String, Object> parametros, String nombrePdfGenerado) {
        try {

            // Primero compilar reporte jrxml.
            System.out.println("-----rutaReporte " + ruraArchivoJrxml);
            JasperReport jasperReport = JasperCompileManager.compileReport(ruraArchivoJrxml);
            Connection conn = ConnectionUtils.getConnection();
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parametros, conn);
            HttpServletResponse httpServletResponse
                    = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.addHeader("Content-disposition", "attachment; filename=\"" + nombrePdfGenerado + "\"");
            ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
            JasperExportManager.exportReportToPdfStream(print, servletOutputStream);
            servletOutputStream.flush();
            servletOutputStream.close();
            FacesContext.getCurrentInstance().responseComplete();
            System.out.print("Done!");
        } catch (JRException ex) {
            Logger.getLogger(JasperUtilidadesBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(JasperUtilidadesBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JasperUtilidadesBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JasperUtilidadesBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }     
         
}
