package bean;

import entity.Estilo;
import entity.Usuario;
import entity.ProcesoConfeccion;
import entity.ProcesoConfeccionEtapa;
import entity.ProcesoConfeccionFase;
import entity.Aguja;
import entity.MaquinaConfeccion;
import entity.MaterialEstilo;
import entity.ParametroPrendaEstilo;
import entity.ParametroTallajeDoblez;
import facade.AgujaFacade;
import facade.EstiloFacade;
import facade.MaquinaConfeccionFacade;
import facade.MaterialEstiloFacade;
import facade.ParametroPrendaEstiloFacade;
import facade.ProcesoConfeccionEtapaFacade;
import facade.ProcesoConfeccionFacade;
import facade.ProcesoConfeccionFaseFacade;
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
public class ProcesoConfeccionBean implements Serializable {

    @EJB
    private EstiloFacade estiloFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ProcesoConfeccionFacade procesoConfeccionFacade;
    @EJB
    private ProcesoConfeccionEtapaFacade procesoConfeccionEtapaFacade;
    @EJB
    private ProcesoConfeccionFaseFacade procesoConfeccionFaseFacade;
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
    private ProcesoConfeccion procesoConfeccion;
    private List<ProcesoConfeccion> procesoConfeccionList;
    private List<ProcesoConfeccionEtapa> procesoConfeccionEtapaList;
    private List<ProcesoConfeccionFase> procesoConfeccionFaseList;
    private List<MaquinaConfeccion> maquinaConfeccionList;
    private List<Aguja> agujaList;
    private List<ParametroTallajeDoblez> parametroTallajeDoblezList;
    private List<ParametroPrendaEstilo> parametroPrendaEstiloList;
    private List<MaterialEstilo> materialEstiloConfeccionList;
    private List<ProcesoConfeccionFase> corteTelaList;
    private List<ProcesoConfeccionFase> corteTubularList;
    private List<ProcesoConfeccionFase> armarPrendaList;
    private ProcesoConfeccionFase corteTela;
    private ProcesoConfeccionFase corteTubular;
    private ProcesoConfeccionFase armarPrenda;
    private ProcesoConfeccionFase faseSeleccionada;
    private final String ruta = "C:\\Cargues\\Confeccion";
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
        faseSeleccionada = new ProcesoConfeccionFase();
        corteTela = new ProcesoConfeccionFase();
        corteTubular = new ProcesoConfeccionFase();
        armarPrenda = new ProcesoConfeccionFase();
        traerUsuario();
        buscarEstilo();
        llenarListas();
        validarPermisos();
    }
    
    public void onRowEdit(RowEditEvent<ProcesoConfeccionFase> event) {
        //FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getCode()));
        //FacesContext.getCurrentInstance().addMessage(null, msg);
        procesoConfeccionFaseFacade.edit(event.getObject());
        lanzarMensajeInformacion("Línea editada");
    }

    public String redirect(String opc) {
        return opc + "?faces-redirect=true&amp;estiloRecibido=" + estiloRecibido;
    }

    public void onRowReorderCorteTela(ReorderEvent event) {
        int cont = 0;
        for (ProcesoConfeccionFase pr : corteTelaList) {
            pr.setOrden(cont);
            cont++;
            procesoConfeccionFaseFacade.edit(pr);
        }
    }

    public void onRowReorderCorteTubular(ReorderEvent event) {
        int cont = 0;
        for (ProcesoConfeccionFase pr : corteTubularList) {
            pr.setOrden(cont);
            cont++;
            procesoConfeccionFaseFacade.edit(pr);
        }
    }

    public void onRowReorderArmarPrenda(ReorderEvent event) {
        int cont = 0;
        for (ProcesoConfeccionFase pr : armarPrendaList) {
            pr.setOrden(cont);
            cont++;
            procesoConfeccionFaseFacade.edit(pr);
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
        procesoConfeccionFaseFacade.remove(faseSeleccionada);
        //llena la lista que se actualizó con el borrado
        String namedQuery = "ProcesoConfeccionFase.findByIdEtapa";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idProcesoConfeccion", procesoConfeccion);
        parametros.put("idEtapa", procesoConfeccionEtapaList.get(opc));
        switch (opc) {
            case 0:
                corteTelaList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery, parametros);
                break;
            case 1:
                corteTubularList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery, parametros);
                break;
            case 2:
                armarPrendaList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery, parametros);
                break;
        }
    }

    public void añadirCorteTela() {
        corteTela.setIdProcesoConfeccionEtapa(procesoConfeccionEtapaList.get(0));
        corteTela.setIdProcesoConfeccion(procesoConfeccion);
        corteTela.setOrden(0);
        procesoConfeccionFaseFacade.edit(corteTela);
        corteTela = new ProcesoConfeccionFase();
        int cont = 1;
        for (ProcesoConfeccionFase pr : corteTelaList) {
            pr.setOrden(cont);
            cont++;
            procesoConfeccionFaseFacade.edit(pr);
        }
        String namedQuery = "ProcesoConfeccionFase.findByIdEtapa";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idProcesoConfeccion", procesoConfeccion);
        parametros.put("idEtapa", procesoConfeccionEtapaList.get(0));
        corteTelaList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery, parametros);
    }

    public void añadirCorteTubular() {
        corteTubular.setIdProcesoConfeccionEtapa(procesoConfeccionEtapaList.get(1));
        corteTubular.setIdProcesoConfeccion(procesoConfeccion);
        corteTubular.setOrden(0);
        procesoConfeccionFaseFacade.edit(corteTubular);
        corteTubular = new ProcesoConfeccionFase();
        int cont = 1;
        for (ProcesoConfeccionFase pr : corteTubularList) {
            pr.setOrden(cont);
            cont++;
            procesoConfeccionFaseFacade.edit(pr);
        }
        String namedQuery = "ProcesoConfeccionFase.findByIdEtapa";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idProcesoConfeccion", procesoConfeccion);
        parametros.put("idEtapa", procesoConfeccionEtapaList.get(1));
        corteTubularList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery, parametros);
    }

    public void añadirArmarPrenda() {
        armarPrenda.setIdProcesoConfeccionEtapa(procesoConfeccionEtapaList.get(2));
        armarPrenda.setIdProcesoConfeccion(procesoConfeccion);
        armarPrenda.setOrden(0);
        procesoConfeccionFaseFacade.edit(armarPrenda);
        armarPrenda = new ProcesoConfeccionFase();
        int cont = 1;
        for (ProcesoConfeccionFase pr : armarPrendaList) {
            pr.setOrden(cont);
            cont++;
            procesoConfeccionFaseFacade.edit(pr);
        }
        String namedQuery = "ProcesoConfeccionFase.findByIdEtapa";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idProcesoConfeccion", procesoConfeccion);
        parametros.put("idEtapa", procesoConfeccionEtapaList.get(2));
        armarPrendaList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery, parametros);
    }

    public void guardarCantidades() {
        procesoConfeccionFacade.edit(procesoConfeccion);
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
        String namedQuery2 = "ProcesoConfeccion.findByIdEstilo";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        procesoConfeccionList = procesoConfeccionFacade.findByNamedQuery(namedQuery2, parametros2);
        if (procesoConfeccionList.isEmpty()) {
            procesoConfeccion = new ProcesoConfeccion();
            procesoConfeccion.setCreado(fechaActual);
            procesoConfeccion.setIdCreado(usuario);
            procesoConfeccion.setIdEstilo(estilo);
            procesoConfeccionFacade.create(procesoConfeccion);
        } else {
            procesoConfeccion = procesoConfeccionList.get(0);
        }
    }

    public void llenarListas() {
        corteTela = new ProcesoConfeccionFase();
        corteTubular = new ProcesoConfeccionFase();
        armarPrenda = new ProcesoConfeccionFase();
        agujaList = agujaFacade.findAll();
        maquinaConfeccionList = maquinaConfeccionFacade.findAll();
        procesoConfeccionEtapaList = procesoConfeccionEtapaFacade.findAll();
        String namedQuery = "ProcesoConfeccionFase.findByIdProcesoConfeccion";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idProcesoConfeccion", procesoConfeccion);
        procesoConfeccionFaseList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery, parametros);
        String namedQuery2 = "ParametroTallajeDoblez.findByIdEstilo";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        parametroTallajeDoblezList = parametroTallajeDoblezFacade.findByNamedQuery(namedQuery2, parametros2);
        String namedQuery3 = "ParametroPrendaEstilo.findByIdEstilo";
        parametroPrendaEstiloList = parametroPrendaEstiloFacade.findByNamedQuery(namedQuery3, parametros2);//reuso parámetro
        String namedQuery4 = "MaterialEstilo.findByIdEstiloAndArea";
        Map<String, Object> parametros3 = new HashMap<>();
        parametros3.put("idEstilo", estilo);
        parametros3.put("nombre", "CONFECCION");
        materialEstiloConfeccionList = materialEstiloFacade.findByNamedQuery(namedQuery4, parametros3);
        String namedQuery5 = "ProcesoConfeccionFase.findByIdEtapa";
        Map<String, Object> parametros5 = new HashMap<>();
        parametros5.put("idProcesoConfeccion", procesoConfeccion);
        parametros5.put("idEtapa", procesoConfeccionEtapaList.get(0));
        corteTelaList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery5, parametros5);
        parametros5 = new HashMap<>();
        parametros5.put("idProcesoConfeccion", procesoConfeccion);
        parametros5.put("idEtapa", procesoConfeccionEtapaList.get(1));
        corteTubularList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery5, parametros5);
        parametros5 = new HashMap<>();
        parametros5.put("idProcesoConfeccion", procesoConfeccion);
        parametros5.put("idEtapa", procesoConfeccionEtapaList.get(2));
        armarPrendaList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery5, parametros5);

    }

    public void imprimirReporte() {

        String rutaWebPages = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String rutaCarpetaReportes = rutaWebPages + File.separator + "reports" + File.separator;
        String rutaReportePaqueteTecnico = rutaCarpetaReportes + File.separator + "proceso_confeccion.jrxml";
        Map<String, Object> parametros1 = new HashMap<>();
        parametros1.put("ID_ESTILO", estilo.getIdEstilo());
        parametros1.put("SUBREPORT_DIR", rutaCarpetaReportes);
        String nombrePDFGenerado = "proceso_confeccion" + estilo.getEstilo() + ".pdf";
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
        File archivoSubido = Archivos.subirArchivo(ruta, extension, contenidoArchivo, nombreArchivo, true);
        procesoConfeccion.setImagen(archivoSubido.getName());
        procesoConfeccionFacade.edit(procesoConfeccion);
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public ProcesoConfeccionFase getFaseSeleccionada() {
        return faseSeleccionada;
    }

    public void setFaseSeleccionada(ProcesoConfeccionFase faseSeleccionada) {
        this.faseSeleccionada = faseSeleccionada;
    }

    public List<ProcesoConfeccionFase> getCorteTubularList() {
        return corteTubularList;
    }

    public void setCorteTubularList(List<ProcesoConfeccionFase> corteTubularList) {
        this.corteTubularList = corteTubularList;
    }

    public List<ProcesoConfeccionFase> getArmarPrendaList() {
        return armarPrendaList;
    }

    public void setArmarPrendaList(List<ProcesoConfeccionFase> armarPrendaList) {
        this.armarPrendaList = armarPrendaList;
    }

    public List<MaterialEstilo> getMaterialEstiloConfeccionList() {
        return materialEstiloConfeccionList;
    }

    public void setMaterialEstiloConfeccionList(List<MaterialEstilo> materialEstiloConfeccionList) {
        this.materialEstiloConfeccionList = materialEstiloConfeccionList;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public List<ParametroPrendaEstilo> getParametroPrendaEstiloList() {
        return parametroPrendaEstiloList;
    }

    public ProcesoConfeccionFase getCorteTela() {
        return corteTela;
    }

    public void setCorteTela(ProcesoConfeccionFase corteTela) {
        this.corteTela = corteTela;
    }

    public ProcesoConfeccionFase getCorteTubular() {
        return corteTubular;
    }

    public List<ProcesoConfeccionFase> getCorteTelaList() {
        return corteTelaList;
    }

    public void setCorteTelaList(List<ProcesoConfeccionFase> corteTelaList) {
        this.corteTelaList = corteTelaList;
    }

    public void setCorteTubular(ProcesoConfeccionFase corteTubular) {
        this.corteTubular = corteTubular;
    }

    public ProcesoConfeccionFase getArmarPrenda() {
        return armarPrenda;
    }

    public void setArmarPrenda(ProcesoConfeccionFase armarPrenda) {
        this.armarPrenda = armarPrenda;
    }

    public void setParametroPrendaEstiloList(List<ParametroPrendaEstilo> parametroPrendaEstiloList) {
        this.parametroPrendaEstiloList = parametroPrendaEstiloList;
    }

    public List<ProcesoConfeccion> getProcesoConfeccionList() {
        return procesoConfeccionList;
    }

    public void setProcesoConfeccionList(List<ProcesoConfeccion> procesoConfeccionList) {
        this.procesoConfeccionList = procesoConfeccionList;
    }

    public ProcesoConfeccion getProcesoConfeccion() {
        return procesoConfeccion;
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

    public void setProcesoConfeccion(ProcesoConfeccion procesoConfeccion) {
        this.procesoConfeccion = procesoConfeccion;
    }

    public List<ProcesoConfeccionEtapa> getProcesoConfeccionEtapaList() {
        return procesoConfeccionEtapaList;
    }

    public void setProcesoConfeccionEtapaList(List<ProcesoConfeccionEtapa> procesoConfeccionEtapaList) {
        this.procesoConfeccionEtapaList = procesoConfeccionEtapaList;
    }

    public List<ProcesoConfeccionFase> getProcesoConfeccionFaseList() {
        return procesoConfeccionFaseList;
    }

    public void setProcesoConfeccionFaseList(List<ProcesoConfeccionFase> procesoConfeccionFaseList) {
        this.procesoConfeccionFaseList = procesoConfeccionFaseList;
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

    public void setAgujaList(List<Aguja> agujaList) {
        this.agujaList = agujaList;
    }

    public List<ParametroTallajeDoblez> getParametroTallajeDoblezList() {
        return parametroTallajeDoblezList;
    }

    public void setParametroTallajeDoblezList(List<ParametroTallajeDoblez> parametroTallajeDoblezList) {
        this.parametroTallajeDoblezList = parametroTallajeDoblezList;
    }

}
