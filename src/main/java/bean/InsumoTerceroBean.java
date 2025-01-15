package bean;

import entity.Estilo;
import entity.InsumoTercero;
import entity.MaterialEstilo;
import entity.Usuario;
import facade.EstiloFacade;
import facade.InsumoTerceroFacade;
import facade.MaterialEstiloFacade;
import facade.UsuarioFacade;
import jasper.utilidades.JasperUtilidadesBeanLocal;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import utility.Archivos;

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class InsumoTerceroBean implements Serializable{
    @EJB
    private EstiloFacade estiloFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private MaterialEstiloFacade materialEstiloFacade;
    @EJB
    private InsumoTerceroFacade insumoTerceroFacade;
    @EJB
    private JasperUtilidadesBeanLocal jasperUtilidadesBean;
    
    private String estiloRecibido;
    private Estilo estilo;
    private Usuario usuario;
    private Date fechaActual;
    private final String ruta = "C:\\Cargues\\Insumo";
    private List<MaterialEstilo> materialEstiloConfeccionList;
    private List<InsumoTercero> insumoTerceroList;
    private InsumoTercero insumoTercero;
    private InsumoTercero insumoTerceroSeleccionado;
    private boolean disableDatos;
    private boolean dsbEdicion;
    private boolean dsbImpresion;
    private final int idRolConsulta = 3;
    private final int idRolFull = 2;
    private final int idRolCalidad = 4;
    
    @PostConstruct
    public void init() {
        fechaActual = Calendar.getInstance().getTime();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        estiloRecibido = params.get("estiloRecibido");
        insumoTercero = new InsumoTercero();
        insumoTerceroSeleccionado = new InsumoTercero();
        traerUsuario();
        buscarEstilo();
        llenarListas();
        disableDatos = true;
        validarPermisos();
    }
    
    public String redirect(String opc) {
        return opc + "?faces-redirect=true&amp;estiloRecibido=" + estiloRecibido;
    }
    
    private void validarPermisos() {
        switch (usuario.getIdRol().getIdRol()) {
            case idRolConsulta:
                dsbEdicion = true;
                dsbImpresion = true;
                break;
            case idRolFull:
                dsbEdicion = false;
                dsbImpresion = false;
                break;
            case idRolCalidad:
                dsbEdicion = true;
                dsbImpresion = false;
                break;
            default:
                dsbEdicion = false;
                dsbImpresion = false;
                break;
        }
    }
    
    public void traerUsuario() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombreUsuario").toString();
        String namedQuery = "Usuario.findByUsuario";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", nombreUsuario);
        usuario = usuarioFacade.findByNamedQuery(namedQuery, parametros).get(0);
    }
    
    public void buscarEstilo() {
        String namedQuery = "Estilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", Integer.valueOf(estiloRecibido));
        estilo = estiloFacade.findByNamedQuery(namedQuery, parametros).get(0);
    }
    
    public void llenarListas() {
        String namedQuery = "MaterialEstilo.findByIdEstiloAndArea";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        parametros.put("nombre", "CONFECCION");
        materialEstiloConfeccionList = materialEstiloFacade.findByNamedQuery(namedQuery, parametros);
        namedQuery = "InsumoTercero.findByIdEstilo";
        parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        insumoTerceroList = insumoTerceroFacade.findByNamedQuery(namedQuery, parametros);
    }
    
    public void imprimirReporte() {
        String rutaWebPages = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String rutaCarpetaReportes = rutaWebPages + File.separator + "reports" + File.separator;
        String rutaReportePaqueteTecnico = rutaCarpetaReportes + File.separator + "insumo_tercero.jrxml";
        Map<String, Object> parametros1 = new HashMap<>();
        parametros1.put("ID_INSUMO_TERCERO",insumoTerceroSeleccionado.getIdInsumoTercero());
        parametros1.put("SUBREPORT_DIR", rutaCarpetaReportes);
        String nombrePDFGenerado = "insumo_tercero_" + estilo.getEstilo() + ".pdf";
        jasperUtilidadesBean.descargarReporte(rutaReportePaqueteTecnico, parametros1, nombrePDFGenerado);
    }
    
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        UploadedFile archivoASubir = event.getFile();
        String nombreArchivo = "Estilo " + estilo.getEstilo();
        String extension = Archivos.getExtension(archivoASubir.getFileName());
        byte[] contenidoArchivo = archivoASubir.getContent();
        File archivoSubido = Archivos.subirArchivo(ruta, extension,contenidoArchivo, nombreArchivo, true);
        insumoTerceroSeleccionado.setImagen(archivoSubido.getName());
        insumoTerceroFacade.edit(insumoTerceroSeleccionado);
    }
    
    public void añadirMaterial() {
        insumoTercero.setIdEstilo(estilo);
        insumoTercero.setCreado(fechaActual);
        insumoTercero.setIdCreado(usuario);
        insumoTerceroFacade.create(insumoTercero);
        insumoTercero = new InsumoTercero();
        String namedQuery = "InsumoTercero.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        insumoTerceroList = insumoTerceroFacade.findByNamedQuery(namedQuery, parametros);
    }
    
    public void borrarInsumo() {
        insumoTerceroFacade.remove(insumoTerceroSeleccionado);
        //llena la lista que se actualizó con el borrado
        String namedQuery = "InsumoTercero.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        insumoTerceroList = insumoTerceroFacade.findByNamedQuery(namedQuery, parametros);
    }
    
    public void dtMaterialSelect() {
        disableDatos = false;
    }
    public void dtMaterialUnSelect() {
        disableDatos = true;
    }
    
    public void guardarInsumo() {
        insumoTerceroFacade.edit(insumoTerceroSeleccionado);
        lanzarMensajeInformacion("Campos guardados");
    }
    
    public void lanzarMensajeInformacion(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", mensaje));
    }

    public void lanzarMensajeAdvertencia(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", mensaje));
    }

    public void lanzarMensajeError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje));
    }

    public String getEstiloRecibido() {
        return estiloRecibido;
    }

    public boolean isDisableDatos() {
        return disableDatos;
    }

    public void setDisableDatos(boolean disableDatos) {
        this.disableDatos = disableDatos;
    }

    public InsumoTercero getInsumoTerceroSeleccionado() {
        return insumoTerceroSeleccionado;
    }

    public void setInsumoTerceroSeleccionado(InsumoTercero insumoTerceroSeleccionado) {
        this.insumoTerceroSeleccionado = insumoTerceroSeleccionado;
    }

    public void setEstiloRecibido(String estiloRecibido) {
        this.estiloRecibido = estiloRecibido;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public boolean isDsbEdicion() {
        return dsbEdicion;
    }

    public void setDsbEdicion(boolean dsbEdicion) {
        this.dsbEdicion = dsbEdicion;
    }

    public boolean isDsbImpresion() {
        return dsbImpresion;
    }

    public void setDsbImpresion(boolean dsbImpresion) {
        this.dsbImpresion = dsbImpresion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<MaterialEstilo> getMaterialEstiloConfeccionList() {
        return materialEstiloConfeccionList;
    }

    public void setMaterialEstiloConfeccionList(List<MaterialEstilo> materialEstiloConfeccionList) {
        this.materialEstiloConfeccionList = materialEstiloConfeccionList;
    }

    public List<InsumoTercero> getInsumoTerceroList() {
        return insumoTerceroList;
    }

    public void setInsumoTerceroList(List<InsumoTercero> insumoTerceroList) {
        this.insumoTerceroList = insumoTerceroList;
    }

    public InsumoTercero getInsumoTercero() {
        return insumoTercero;
    }

    public void setInsumoTercero(InsumoTercero insumoTercero) {
        this.insumoTercero = insumoTercero;
    }
    
    
    
}
