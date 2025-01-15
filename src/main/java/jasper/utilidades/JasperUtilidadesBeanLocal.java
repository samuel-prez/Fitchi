package jasper.utilidades;

import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Samuel P.
 */
@Local
public interface JasperUtilidadesBeanLocal {

    public void generarReporte(String ruraArchivoJrxml, Map<String, Object> parametros,
            String rutaCarpetaDestino, String nombrePdfGenerado);

    public void descargarReporte(String ruraArchivoJrxml, Map<String, Object> parametros,
            String nombrePdfGenerado);
  

}
