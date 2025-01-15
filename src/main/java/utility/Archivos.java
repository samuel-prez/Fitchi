/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author ext_hvillanueva
 */
public class Archivos {

    /**
     * Metodo que retorna la extension del archivo enviado
     *
     * @param filename nombre completo del archivo enviado
     * @return la extension del archivo enviado
     */
    public static String getExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(index + 1);
        }
    }

    /**
     * Metodo que retorna la extension del archivo enviado
     *
     * @param filename nombre completo del archivo enviado
     * @return la extension del archivo enviado
     */
    public static String getNombreArchivoSinExtension(String filename) {
        int index = filename.lastIndexOf('.');
        if (index == -1) {
            return "";
        } else {
            return filename.substring(0, index);
        }
    }

    /**
     *
     * @param rutaCarpetaDestino carpeta donde se guarda el archivo enviado
     * @param extension extension del archivo enviado
     * @param data arreglo de bytes que contiene el archivo enviado
     * @param nombreArchivo El nombre con el que queda el archivo guardado
     * @param limpiarCarpetaDestino Bandera que indica si los archivos que
     * contiene la carpeta destino se deben eliminar
     * @param agregarDateAlNombre
     * @return
     */
    public static File subirArchivo(String rutaCarpetaDestino, String extension,
            byte[] data, String nombreArchivo, boolean agregarDateAlNombre) {
        try {
            String filename = nombreArchivo;
            if (agregarDateAlNombre) {
                Date fechaActual = Calendar.getInstance().getTime();
                DateFormat formatYearPre = new SimpleDateFormat("yyyy-MM-dd HHmmss");
                filename += " " + formatYearPre.format(fechaActual);
            }
            FileOutputStream fileOutputStream;
            String newFileName = rutaCarpetaDestino + File.separator + filename + "." + extension;
            System.out.println(newFileName);
            fileOutputStream = new FileOutputStream(new File(newFileName));
            fileOutputStream.write(data, 0, data.length);
            fileOutputStream.close();
            return new File(newFileName);
        } catch (IOException ex) {
            Logger.getLogger(Archivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Genera la url para abrir una imagen en una nueva pestaña
     *
     * @param rutaURLProyectoWeb La ruta del proyecto web actual. Eje:
     * localhost:8080/miProyecto
     * @param glassFishWebFrom La carpeta que se configuro en el arhivo
     * glassfish.xml en la propiedad rutaImagenes
     * @param rutaImagenBaseDatos La ruta de la imagen que viene de la base de
     * datos
     *
     * @return La url para abrir en una nueva pestaña
     */
    public static String getUrlGraphicImage(String rutaURLProyectoWeb, String glassFishWebFrom, String rutaImagenBaseDatos) {
        rutaImagenBaseDatos = rutaURLProyectoWeb + glassFishWebFrom + rutaImagenBaseDatos.replace("\\", "/");
        System.out.println("rutaImagenBaseDatos " + rutaImagenBaseDatos);
        return rutaImagenBaseDatos;

    }

}
