package bean;

import entity.Estilo;
import entity.Usuario;
import entity.ProcesoAcabado;
import entity.ProcesoAcabadoEtapa;
import entity.ProcesoAcabadoFase;
import entity.Aguja;
import entity.MaquinaConfeccion;
import entity.MaterialEstilo;
import entity.ParametroPrendaEstilo;
import entity.ParametroTallajeDoblez;
import entity.ProcesoConfeccionFase;
import facade.AgujaFacade;
import facade.EstiloFacade;
import facade.MaquinaConfeccionFacade;
import facade.MaterialEstiloFacade;
import facade.ParametroPrendaEstiloFacade;
import facade.ProcesoAcabadoEtapaFacade;
import facade.ProcesoAcabadoFacade;
import facade.ProcesoAcabadoFaseFacade;
import facade.UsuarioFacade;
import facade.ParametroTallajeDoblezFacade;
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
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.file.UploadedFile;
import utility.Archivos;
/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class ProcesoAcabadoBean implements Serializable{
    @EJB
    private EstiloFacade estiloFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ProcesoAcabadoFacade procesoAcabadoFacade;
    @EJB
    private ProcesoAcabadoEtapaFacade procesoAcabadoEtapaFacade;
    @EJB
    private ProcesoAcabadoFaseFacade procesoAcabadoFaseFacade;
    @EJB
    private MaquinaConfeccionFacade maquinaConfeccionFacade;
    @EJB
    private AgujaFacade agujaFacade;
    @EJB
    private ParametroTallajeDoblezFacade parametroTallajeDoblezFacade;
    @EJB
    private ParametroPrendaEstiloFacade parametroPrendaEstiloFacade;
    @EJB
    private MaterialEstiloFacade materialEstiloFacade;
    @EJB
    private JasperUtilidadesBeanLocal jasperUtilidadesBean;

    private String estiloRecibido;
    private Estilo estilo;
    private Usuario usuario;
    private Date fechaActual;
    private ProcesoAcabado procesoAcabado;
    private List<ProcesoAcabado> procesoAcabadoList;
    private List<ProcesoAcabadoEtapa> procesoAcabadoEtapaList;
    private List<ProcesoAcabadoFase> procesoAcabadoFaseList;
    private List<MaquinaConfeccion> maquinaConfeccionList;
    private List<Aguja> agujaList;
    private List<ParametroTallajeDoblez> parametroTallajeDoblezList;
    private List<ParametroPrendaEstilo> parametroPrendaEstiloList;
    private List<MaterialEstilo> materialEstiloAcabadoList;
    private List<ProcesoAcabadoFase> revisionList;
    private List<ProcesoAcabadoFase> empaqueList;
    private ProcesoAcabadoFase revision;
    private ProcesoAcabadoFase empaque;
    private ProcesoAcabadoFase faseSeleccionada;
    private final String ruta = "C:\\Cargues\\Acabado";
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
        faseSeleccionada = new ProcesoAcabadoFase();
        revision = new ProcesoAcabadoFase();
        empaque = new ProcesoAcabadoFase();
        traerUsuario();
        buscarEstilo();
        llenarListas();
        validarPermisos();
    }
    
    public void onRowEdit(RowEditEvent<ProcesoAcabadoFase> event) {
        procesoAcabadoFaseFacade.edit(event.getObject());
        lanzarMensajeInformacion("Línea editada");
    }
    
    public String redirect(String opc) {
        return opc + "?faces-redirect=true&amp;estiloRecibido=" + estiloRecibido;
    }
    
    public void onRowReorderRevision(ReorderEvent event) {
        int cont = 0;
        for (ProcesoAcabadoFase pr : revisionList) {
            pr.setOrden(cont);
            cont++;
            procesoAcabadoFaseFacade.edit(pr);
        }
    }
    
    public void onRowReorderEmpaque(ReorderEvent event) {
        int cont = 0;
        for (ProcesoAcabadoFase pr : empaqueList) {
            pr.setOrden(cont);
            cont++;
            procesoAcabadoFaseFacade.edit(pr);
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
    
    public void borrarFase(int opc) {
        procesoAcabadoFaseFacade.remove(faseSeleccionada);
        //llena la lista que se actualizó con el borrado
        String namedQuery = "ProcesoAcabadoFase.findByIdEtapa";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idProcesoAcabado", procesoAcabado);
        parametros.put("idEtapa", procesoAcabadoEtapaList.get(opc));
        switch (opc) {
            case 0: revisionList = procesoAcabadoFaseFacade.findByNamedQuery(namedQuery, parametros);
                break;
            case 1: empaqueList = procesoAcabadoFaseFacade.findByNamedQuery(namedQuery, parametros);
                break;
        }
    }
    
    public void añadirRevision() {
        revision.setIdProcesoAcabadoEtapa(procesoAcabadoEtapaList.get(0));
        revision.setIdProcesoAcabado(procesoAcabado);
        revision.setOrden(revisionList.size());
        procesoAcabadoFaseFacade.edit(revision);
        revision = new ProcesoAcabadoFase();
        String namedQuery = "ProcesoAcabadoFase.findByIdEtapa";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idProcesoAcabado", procesoAcabado);
        parametros.put("idEtapa", procesoAcabadoEtapaList.get(0));
        revisionList = procesoAcabadoFaseFacade.findByNamedQuery(namedQuery, parametros);
    }
    
    public void añadirEmpaque() {
        empaque.setIdProcesoAcabadoEtapa(procesoAcabadoEtapaList.get(1));
        empaque.setIdProcesoAcabado(procesoAcabado);
        empaque.setOrden(empaqueList.size());
        procesoAcabadoFaseFacade.edit(empaque);
        empaque = new ProcesoAcabadoFase();
        String namedQuery = "ProcesoAcabadoFase.findByIdEtapa";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idProcesoAcabado", procesoAcabado);
        parametros.put("idEtapa", procesoAcabadoEtapaList.get(1));
        empaqueList = procesoAcabadoFaseFacade.findByNamedQuery(namedQuery, parametros);
    }

    public void guardarObservacion() {
        procesoAcabadoFacade.edit(procesoAcabado);
        lanzarMensajeInformacion("Cantidades guardadas");
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
        String namedQuery2 = "ProcesoAcabado.findByIdEstilo";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        procesoAcabadoList = procesoAcabadoFacade.findByNamedQuery(namedQuery2, parametros2);
        if (procesoAcabadoList.isEmpty()) {
            procesoAcabado = new ProcesoAcabado();
            procesoAcabado.setCreado(fechaActual);
            procesoAcabado.setIdCreado(usuario);
            procesoAcabado.setIdEstilo(estilo);
            procesoAcabadoFacade.create(procesoAcabado);
        } else {
            procesoAcabado = procesoAcabadoList.get(0);
        }
    }

    public void llenarListas() {
        revision = new ProcesoAcabadoFase();
        empaque = new ProcesoAcabadoFase();
        agujaList = agujaFacade.findAll();
        maquinaConfeccionList = maquinaConfeccionFacade.findAll();
        procesoAcabadoEtapaList = procesoAcabadoEtapaFacade.findAll();
        String namedQuery = "ProcesoAcabadoFase.findByIdProcesoAcabado";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idProcesoAcabado", procesoAcabado);
        procesoAcabadoFaseList = procesoAcabadoFaseFacade.findByNamedQuery(namedQuery, parametros);
        String namedQuery2 = "ParametroTallajeDoblez.findByIdEstilo";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        parametroTallajeDoblezList = parametroTallajeDoblezFacade.findByNamedQuery(namedQuery2, parametros2);
        String namedQuery3 = "ParametroPrendaEstilo.findByIdEstilo";
        parametroPrendaEstiloList = parametroPrendaEstiloFacade.findByNamedQuery(namedQuery3, parametros2);//reuso parámetro
        String namedQuery4 = "MaterialEstilo.findByIdEstiloAndArea";
        Map<String, Object> parametros3 = new HashMap<>();
        parametros3.put("idEstilo", estilo);
        parametros3.put("nombre", "ACABADOS");
        materialEstiloAcabadoList = materialEstiloFacade.findByNamedQuery(namedQuery4, parametros3);
        String namedQuery5 = "ProcesoAcabadoFase.findByIdEtapa";
        Map<String, Object> parametros5 = new HashMap<>();
        parametros5.put("idProcesoAcabado", procesoAcabado);
        parametros5.put("idEtapa", procesoAcabadoEtapaList.get(0));
        revisionList = procesoAcabadoFaseFacade.findByNamedQuery(namedQuery5, parametros5);
        parametros5 = new HashMap<>();
        parametros5.put("idProcesoAcabado", procesoAcabado);
        parametros5.put("idEtapa", procesoAcabadoEtapaList.get(1));
        empaqueList = procesoAcabadoFaseFacade.findByNamedQuery(namedQuery5, parametros5);


    }

    public void imprimirReporte() {
        String rutaWebPages = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String rutaCarpetaReportes = rutaWebPages + File.separator + "reports" + File.separator;
        String rutaReportePaqueteTecnico = rutaCarpetaReportes + File.separator + "proceso_acabado.jrxml";
        Map<String, Object> parametros1 = new HashMap<>();
        parametros1.put("ID_ESTILO", estilo.getIdEstilo());
        parametros1.put("SUBREPORT_DIR", rutaCarpetaReportes);
        String nombrePDFGenerado = "proceso_acabado" + estilo.getEstilo() + ".pdf";
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

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        UploadedFile archivoASubir = event.getFile();
        String nombreArchivo = "Estilo " + estilo.getEstilo();
        String extension = Archivos.getExtension(archivoASubir.getFileName());
        byte[] contenidoArchivo = archivoASubir.getContent();
        File archivoSubido = Archivos.subirArchivo(ruta, extension,contenidoArchivo, nombreArchivo, true);
        procesoAcabado.setImagen(archivoSubido.getName());
        procesoAcabadoFacade.edit(procesoAcabado);
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

    public ProcesoAcabadoFase getRevision() {
        return revision;
    }

    public void setRevision(ProcesoAcabadoFase revision) {
        this.revision = revision;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public ProcesoAcabado getProcesoAcabado() {
        return procesoAcabado;
    }

    public void setProcesoAcabado(ProcesoAcabado procesoAcabado) {
        this.procesoAcabado = procesoAcabado;
    }

    public List<ProcesoAcabado> getProcesoAcabadoList() {
        return procesoAcabadoList;
    }

    public void setProcesoAcabadoList(List<ProcesoAcabado> procesoAcabadoList) {
        this.procesoAcabadoList = procesoAcabadoList;
    }

    public List<ProcesoAcabadoEtapa> getProcesoAcabadoEtapaList() {
        return procesoAcabadoEtapaList;
    }

    public void setProcesoAcabadoEtapaList(List<ProcesoAcabadoEtapa> procesoAcabadoEtapaList) {
        this.procesoAcabadoEtapaList = procesoAcabadoEtapaList;
    }

    public List<ProcesoAcabadoFase> getProcesoAcabadoFaseList() {
        return procesoAcabadoFaseList;
    }

    public void setProcesoAcabadoFaseList(List<ProcesoAcabadoFase> procesoAcabadoFaseList) {
        this.procesoAcabadoFaseList = procesoAcabadoFaseList;
    }

    public List<MaquinaConfeccion> getMaquinaConfeccionList() {
        return maquinaConfeccionList;
    }

    public void setMaquinaConfeccionList(List<MaquinaConfeccion> maquinaConfeccionList) {
        this.maquinaConfeccionList = maquinaConfeccionList;
    }

    public List<Aguja> getAgujaList() {
        return agujaList;
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

    public void setAgujaList(List<Aguja> agujaList) {
        this.agujaList = agujaList;
    }

    public List<ParametroTallajeDoblez> getParametroTallajeDoblezList() {
        return parametroTallajeDoblezList;
    }

    public void setParametroTallajeDoblezList(List<ParametroTallajeDoblez> parametroTallajeDoblezList) {
        this.parametroTallajeDoblezList = parametroTallajeDoblezList;
    }

    public List<ParametroPrendaEstilo> getParametroPrendaEstiloList() {
        return parametroPrendaEstiloList;
    }

    public void setParametroPrendaEstiloList(List<ParametroPrendaEstilo> parametroPrendaEstiloList) {
        this.parametroPrendaEstiloList = parametroPrendaEstiloList;
    }

    public List<MaterialEstilo> getMaterialEstiloAcabadoList() {
        return materialEstiloAcabadoList;
    }

    public void setMaterialEstiloAcabadoList(List<MaterialEstilo> materialEstiloAcabadoList) {
        this.materialEstiloAcabadoList = materialEstiloAcabadoList;
    }

    public List<ProcesoAcabadoFase> getRevisionList() {
        return revisionList;
    }

    public void setRevisionList(List<ProcesoAcabadoFase> revisionList) {
        this.revisionList = revisionList;
    }

    public List<ProcesoAcabadoFase> getEmpaqueList() {
        return empaqueList;
    }

    public void setEmpaqueList(List<ProcesoAcabadoFase> empaqueList) {
        this.empaqueList = empaqueList;
    }

    public ProcesoAcabadoFase getEmpaque() {
        return empaque;
    }

    public void setEmpaque(ProcesoAcabadoFase empaque) {
        this.empaque = empaque;
    }

    public ProcesoAcabadoFase getFaseSeleccionada() {
        return faseSeleccionada;
    }

    public void setFaseSeleccionada(ProcesoAcabadoFase faseSeleccionada) {
        this.faseSeleccionada = faseSeleccionada;
    }

    
    
}
