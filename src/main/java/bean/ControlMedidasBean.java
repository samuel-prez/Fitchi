/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import entity.Estilo;
import entity.Usuario;
import entity.ControlMedidas;
import entity.ControlMedidasTalla;
import entity.ControlMedidasParametro;
import entity.ParametroPrendaEstilo;
import entity.ParametroTallajeTalla;
import entity.TallaEstilo;
import facade.ControlMedidasFacade;
import facade.ControlMedidasTallaFacade;
import facade.ControlMedidasParametroFacade;
import facade.EstiloFacade;
import facade.UsuarioFacade;
import facade.TallaEstiloFacade;
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
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ReorderEvent;
import org.primefaces.model.file.UploadedFile;
import utility.Archivos;

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class ControlMedidasBean implements Serializable {

    @EJB
    private EstiloFacade estiloFacade;
    @EJB
    private JasperUtilidadesBeanLocal jasperUtilidadesBean;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ControlMedidasFacade controlMedidasFacade;
    @EJB
    private ControlMedidasTallaFacade controlMedidasTallaFacade;
    @EJB
    private ControlMedidasParametroFacade controlMedidasParametroFacade;
    @EJB
    private TallaEstiloFacade tallaEstiloFacade;

    private String estiloRecibido;
    private Estilo estilo;
    private ControlMedidas controlMedidas;
    private Usuario usuario;
    private ControlMedidasTalla controlMedidasTalla;
    private ControlMedidasTalla controlMedidasTallaSeleccionado;
    private List<ControlMedidas> controlMedidasList;
    private List<ControlMedidasTalla> controlMedidasTallaList;
    private List<ControlMedidasParametro> controlMedidasParametroList;
    private List<TallaEstilo> tallaEstiloList;
    private TallaEstilo tallaEstilo;
    private Date fechaActual;
    private final String ruta = "C:\\Cargues\\Medidas";
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
        controlMedidas = new ControlMedidas();
        controlMedidasTalla = new ControlMedidasTalla();
        tallaEstilo = null;
        traerUsuario();
        buscarEstilo();
        llenarListas();
        validarPermisos();
    }
    
    public String redirect(String opc) {
        return opc + "?faces-redirect=true&amp;estiloRecibido=" + estiloRecibido;
    }
    
    public void borrarControlMedidaTalla() {
        controlMedidasTallaFacade.remove(controlMedidasTallaSeleccionado);
        String namedQuery = "ControlMedidasTalla.findByTalla";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idTallaEstilo", tallaEstilo);
        controlMedidasTallaList = controlMedidasTallaFacade.findByNamedQuery(namedQuery, parametros);
        int cont = 0;
        for (ControlMedidasTalla pr : controlMedidasTallaList) {
            pr.setOrden(cont);
            cont++;
            controlMedidasTallaFacade.edit(pr);
        }
    }
    
    public void onRowReorderRevision(ReorderEvent event) {
        int cont = 0;
        for (ControlMedidasTalla pr : controlMedidasTallaList) {
            pr.setOrden(cont);
            cont++;
            controlMedidasTallaFacade.edit(pr);
        }
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

    public void llenarListas() {
        String namedQuery = "TallaEstilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        tallaEstiloList = tallaEstiloFacade.findByNamedQuery(namedQuery, parametros);
    }

    public void buscarEstilo() {
        String namedQuery = "Estilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", Integer.valueOf(estiloRecibido));
        estilo = estiloFacade.findByNamedQuery(namedQuery, parametros).get(0);
        //se busca el encabezado controlMedidas
        String namedQuery2 = "ControlMedidas.findByIdEstilo";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        controlMedidasList = controlMedidasFacade.findByNamedQuery(namedQuery2, parametros2);
        if (controlMedidasList.isEmpty()) {
            controlMedidas = new ControlMedidas();
            controlMedidas.setCreado(fechaActual);
            controlMedidas.setIdCreado(usuario);
            controlMedidas.setIdEstilo(estilo);
            controlMedidasFacade.create(controlMedidas);
        } else {
            controlMedidas = controlMedidasList.get(0);
        }
    }

    public void buscarControlMedidasTalla() {
        String namedQuery = "ControlMedidasTalla.findByTalla";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idTallaEstilo", tallaEstilo);
        controlMedidasTallaList = controlMedidasTallaFacade.findByNamedQuery(namedQuery, parametros);
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {

        UploadedFile archivoASubir = event.getFile();
        String nombreArchivo = "Estilo " + estilo.getEstilo();
        String extension = Archivos.getExtension(archivoASubir.getFileName());
        byte[] contenidoArchivo = archivoASubir.getContent();
        File archivoSubido = Archivos.subirArchivo(ruta, extension,
                contenidoArchivo, nombreArchivo, true);
        controlMedidas.setImagen(archivoSubido.getName());
        controlMedidasFacade.edit(controlMedidas);
    }

    public void abrirDlgParametro() {
        controlMedidasParametroList = controlMedidasParametroFacade.findAll();
        controlMedidasTalla = new ControlMedidasTalla();
        PrimeFaces.current().executeScript("PF('dlgParametro').show();");
    }
    
    public void abrirDlgParametroEditar() {
        controlMedidasParametroList = controlMedidasParametroFacade.findAll();
        PrimeFaces.current().executeScript("PF('dlgParametroEditar').show();");
    }

    public void dtTallaSeleccion() {
        String namedQuery = "ControlMedidasTalla.findByTalla";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idTallaEstilo", tallaEstilo);
        controlMedidasTallaList = controlMedidasTallaFacade.findByNamedQuery(namedQuery, parametros);
    }
    
    public void guardarControlMedidasTalla() {
        controlMedidasTalla.setIdControlMedidas(controlMedidas);
        controlMedidasTalla.setOrden(controlMedidasTallaList.size());
        controlMedidasTalla.setIdTallaEstilo(tallaEstilo);
        controlMedidasTallaFacade.create(controlMedidasTalla);
        controlMedidasTallaList.add(controlMedidasTalla);
        lanzarMensajeInformacion("Medida creada");
        PrimeFaces.current().executeScript("PF('dlgParametro').hide();");
    }
    
    public void guardarControlMedidasTallaEditar() {
        controlMedidasTallaFacade.edit(controlMedidasTalla);
        lanzarMensajeInformacion("Medida editada");
        PrimeFaces.current().executeScript("PF('dlgParametroEditar').hide();");
    }
    
    public void guardarObs() {
        controlMedidasFacade.edit(controlMedidas);
        lanzarMensajeInformacion("Guardado");
    }
    
    public void imprimirReporte() {
        String rutaWebPages = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String rutaCarpetaReportes = rutaWebPages + File.separator + "reports" + File.separator;
        String rutaReportePaqueteTecnico = rutaCarpetaReportes + File.separator + "control_medidas.jrxml";
        Map<String, Object> parametros1 = new HashMap<>();
        parametros1.put("ID_ESTILO", estilo.getIdEstilo());
        parametros1.put("SUBREPORT_DIR", rutaCarpetaReportes);
        //parametros1.put("ID_CONTROL_MEDIDAS", controlMedidas.getIdControlMedidas());
        String nombrePDFGenerado = "control_medidas " + estilo.getEstilo() + ".pdf";
        jasperUtilidadesBean.descargarReporte(rutaReportePaqueteTecnico, parametros1, nombrePDFGenerado);
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

    public ControlMedidasTalla getControlMedidasTallaSeleccionado() {
        return controlMedidasTallaSeleccionado;
    }

    public void setControlMedidasTallaSeleccionado(ControlMedidasTalla controlMedidasTallaSeleccionado) {
        this.controlMedidasTallaSeleccionado = controlMedidasTallaSeleccionado;
    }

    public String getEstiloRecibido() {
        return estiloRecibido;
    }

    public void setEstiloRecibido(String estiloRecibido) {
        this.estiloRecibido = estiloRecibido;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public ControlMedidasTalla getControlMedidasTalla() {
        return controlMedidasTalla;
    }

    public void setControlMedidasTalla(ControlMedidasTalla controlMedidasTalla) {
        this.controlMedidasTalla = controlMedidasTalla;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public ControlMedidas getControlMedidas() {
        return controlMedidas;
    }

    public void setControlMedidas(ControlMedidas controlMedidas) {
        this.controlMedidas = controlMedidas;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<ControlMedidas> getControlMedidasList() {
        return controlMedidasList;
    }

    public void setControlMedidasList(List<ControlMedidas> controlMedidasList) {
        this.controlMedidasList = controlMedidasList;
    }

    public List<ControlMedidasTalla> getControlMedidasTallaList() {
        return controlMedidasTallaList;
    }

    public void setControlMedidasTallaList(List<ControlMedidasTalla> controlMedidasTallaList) {
        this.controlMedidasTallaList = controlMedidasTallaList;
    }

    public List<ControlMedidasParametro> getControlMedidasParametroList() {
        return controlMedidasParametroList;
    }

    public void setControlMedidasParametroList(List<ControlMedidasParametro> controlMedidasParametroList) {
        this.controlMedidasParametroList = controlMedidasParametroList;
    }

    public List<TallaEstilo> getTallaEstiloList() {
        return tallaEstiloList;
    }

    public void setTallaEstiloList(List<TallaEstilo> tallaEstiloList) {
        this.tallaEstiloList = tallaEstiloList;
    }

    public TallaEstilo getTallaEstilo() {
        return tallaEstilo;
    }

    public void setTallaEstilo(TallaEstilo tallaEstilo) {
        this.tallaEstilo = tallaEstilo;
    }

}
