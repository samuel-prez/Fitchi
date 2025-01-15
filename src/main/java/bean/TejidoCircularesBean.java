package bean;

import entity.Estilo;
import entity.TejidoCirculares;
import entity.Maquina;
import entity.Embalaje;
import entity.HiloParametro;
import entity.HiloTejidoCirculares;
import entity.HiloTension;
import entity.HiloTubular;
import entity.MaterialEstilo;
import entity.PrefijadoObs;
import entity.Usuario;
import facade.EstiloFacade;
import facade.MaquinaFacade;
import facade.TejidoCircularesFacade;
import facade.EmbalajeFacade;
import facade.HiloParametroFacade;
import facade.HiloTejidoCircularesFacade;
import facade.HiloTensionFacade;
import facade.HiloTubularFacade;
import facade.MaterialEstiloFacade;
import facade.PrefijadoObsFacade;
import facade.UsuarioFacade;
import jasper.utilidades.JasperUtilidadesBeanLocal;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.event.ReorderEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class TejidoCircularesBean implements Serializable {

    @EJB
    private EstiloFacade estiloFacade;
    @EJB
    private TejidoCircularesFacade tejidoCircularesFacade;
    @EJB
    private MaquinaFacade maquinaFacade;
    @EJB
    private EmbalajeFacade embalajeFacade;
    @EJB
    private HiloParametroFacade hiloParametroFacade;
    @EJB
    private HiloTejidoCircularesFacade hiloTejidoCircularesFacade;
    @EJB
    private HiloTensionFacade hiloTensionFacade;
    @EJB
    private HiloTubularFacade hiloTubularFacade;
    @EJB
    private PrefijadoObsFacade prefijadoObsFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private MaterialEstiloFacade materialEstiloFacade;
    @EJB
    private JasperUtilidadesBeanLocal jasperUtilidadesBean;

    private String estiloRecibido;
    private Estilo estilo;
    private TejidoCirculares tejidoCirculares;
    private HiloTejidoCirculares hiloTejidoCirculares;
    private HiloTejidoCirculares hiloTejidoCircularesSeleccionado;
    private HiloTension hiloTension;
    private HiloTension hiloTensionSeleccionado;
    private HiloTubular hiloTubular;
    private HiloTubular hiloTubularSeleccionado;
    private List<Maquina> maquinaList;
    private List<Embalaje> embalajeList;
    private List<HiloParametro> hiloParametroList;
    private List<HiloTejidoCirculares> hiloTejidoCircularesList;
    private List<HiloTension> hiloTensionList;
    private List<HiloTubular> hiloTubularList;
    private List<PrefijadoObs> prefijadoObsList;
    private List<TejidoCirculares> tejidoCircularesList;
    private List<MaterialEstilo> materialEstiloList;
    private List<String[]> prefijadoList;
    private String[] stringList;
    private Usuario usuario;
    private Date fechaActual;
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
        traerUsuario();
        buscarEstilo();
        llenarListas();
        validarPermisos();
    }
    
    public void abrirDlgHiloEditar() {
        llenarMaterialEstilo();
        PrimeFaces.current().executeScript("PF('dlgHiloEditar').show();");
    }
    
    public String redirect(String opc) {
        return opc + "?faces-redirect=true&amp;estiloRecibido=" + estiloRecibido;
    }
    
    public void imprimirReporte() {

        String rutaWebPages = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String rutaCarpetaReportes = rutaWebPages + File.separator + "reports" + File.separator;
        String rutaReportePaqueteTecnico = rutaCarpetaReportes + File.separator + "tejido_circulares.jrxml";
        Map<String, Object> parametros1 = new HashMap<>();
        parametros1.put("ID_ESTILO", estilo.getIdEstilo());
        parametros1.put("SUBREPORT_DIR", rutaCarpetaReportes);
        String nombrePDFGenerado = "tejido_circulares_" + estilo.getEstilo() + ".pdf";
        jasperUtilidadesBean.descargarReporte(rutaReportePaqueteTecnico, parametros1, nombrePDFGenerado);

    }
    
    public void onRowReorderHiloTejido(ReorderEvent event) {
        int cont = 0;
        for (HiloTejidoCirculares pr : hiloTejidoCircularesList) {
            pr.setOrden(cont);
            cont++;
            hiloTejidoCircularesFacade.edit(pr);
        }
    }

    public void buscarEstilo() {
        String namedQuery = "Estilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", Integer.valueOf(estiloRecibido));
        estilo = estiloFacade.findByNamedQuery(namedQuery, parametros).get(0);
        //buscar tejidoCirculares también
        String namedQuery2 = "TejidoCirculares.findByIdEstilo";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        tejidoCircularesList = tejidoCircularesFacade.findByNamedQuery(namedQuery2, parametros2);
        if (tejidoCircularesList.isEmpty()) {
            tejidoCirculares = new TejidoCirculares();
            tejidoCirculares.setCreado(fechaActual);
            tejidoCirculares.setIdCreado(usuario);
            tejidoCirculares.setTemperatura(95);
            tejidoCirculares.setIdEstilo(estilo);
            tejidoCircularesFacade.create(tejidoCirculares);
        } else {
            tejidoCirculares = tejidoCircularesList.get(0);
        }
    }
    
    public void abrirDlgHilos() {
        //jalar listas necesarias
        hiloTejidoCirculares = new HiloTejidoCirculares();
        llenarMaterialEstilo();
        PrimeFaces.current().executeScript("PF('dlgHilo').show();");
    }
    public void abrirDlgTension() {
        //jalar listas necesarias
        hiloTension = new HiloTension();
        llenarMaterialEstilo();
        PrimeFaces.current().executeScript("PF('dlgTension').show();");
    }
    public void abrirDlgTubular() {
        //jalar listas necesarias
        hiloTubular = new HiloTubular();
        //luego añadir para que no busque el material estilo si materialEstiloList no está vacío
        llenarMaterialEstilo();
        PrimeFaces.current().executeScript("PF('dlgTubular').show();");
    }
    
    public void llenarMaterialEstilo() {
        String namedQuery = "MaterialEstilo.findByIdEstiloAndIdArea";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        parametros.put("idArea", 1);
        materialEstiloList = materialEstiloFacade.findByNamedQuery(namedQuery, parametros);
    }
    
    public void guardar() {
        tejidoCircularesFacade.edit(tejidoCirculares);
        lanzarMensajeInformacion("Cambios guardados");
    }
    
    public void guardarHiloTejidoCirculares() {
        hiloTejidoCirculares.setIdTejidoCirculares(tejidoCirculares);
        hiloTejidoCirculares.setOrden(hiloTejidoCircularesList.size());
        hiloTejidoCircularesFacade.create(hiloTejidoCirculares);
        hiloTejidoCircularesList.add(hiloTejidoCirculares);
        tejidoCirculares.setActualizado(fechaActual);
        tejidoCirculares.setIdActualizado(usuario);
        tejidoCircularesFacade.edit(tejidoCirculares);
        PrimeFaces.current().executeScript("PF('dlgHilo').hide();");
    }
    
    public void editarHiloTejidoCirculares() {
        hiloTejidoCircularesFacade.edit(hiloTejidoCirculares);
        tejidoCirculares.setActualizado(fechaActual);
        tejidoCirculares.setIdActualizado(usuario);
        tejidoCircularesFacade.edit(tejidoCirculares);
        PrimeFaces.current().executeScript("PF('dlgHiloEditar').hide();");
    }
    
    public void guardarHiloTension() {
        hiloTension.setIdTejidoCirculares(tejidoCirculares);
        hiloTensionFacade.create(hiloTension);
        hiloTensionList.add(hiloTension);
        PrimeFaces.current().executeScript("PF('dlgTension').hide();");
    }
    
    public void guardarHiloTubular() {
        hiloTubular.setIdTejidoCirculares(tejidoCirculares);
        hiloTubularFacade.create(hiloTubular);
        hiloTubularList.add(hiloTubular);
        PrimeFaces.current().executeScript("PF('dlgTubular').hide();");
    }

    public void traerUsuario() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombreUsuario").toString();
        String namedQuery = "Usuario.findByUsuario";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", nombreUsuario);
        usuario = usuarioFacade.findByNamedQuery(namedQuery, parametros).get(0);
    }

    public void llenarListas() {
        hiloTejidoCirculares = new HiloTejidoCirculares();//it should not  be necessary
        maquinaList = maquinaFacade.findAll();
        embalajeList = embalajeFacade.findAll();
        hiloParametroList = hiloParametroFacade.findAll();
        prefijadoObsList = prefijadoObsFacade.findAll();
        //llenar hiloTejidoCircular
        String namedQuery = "HiloTejidoCirculares.findTejidoCirculares";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idTejidoCirculares", tejidoCirculares);
        hiloTejidoCircularesList = hiloTejidoCircularesFacade.findByNamedQuery(namedQuery, parametros);
        //llenar hiloTension
        String namedQuery2 = "HiloTension.findByIdTejidoCirculares";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idTejidoCirculares", tejidoCirculares);
        hiloTensionList = hiloTensionFacade.findByNamedQuery(namedQuery2, parametros2);
        //llenar hiloTubular
        String namedQuery3 = "HiloTubular.findByIdTejidoCirculares";
        Map<String, Object> parametros3 = new HashMap<>();
        parametros3.put("idTejidoCirculares", tejidoCirculares);
        hiloTubularList = hiloTubularFacade.findByNamedQuery(namedQuery3, parametros3);
        llenarPrefijado();
    }
    
    public void borrarHiloTejidoCirculares() {
        hiloTejidoCircularesFacade.remove(hiloTejidoCircularesSeleccionado);
        String namedQuery = "HiloTejidoCirculares.findTejidoCirculares";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idTejidoCirculares", tejidoCirculares);
        hiloTejidoCircularesList = hiloTejidoCircularesFacade.findByNamedQuery(namedQuery, parametros);
    }
    
    public void borrarHiloTension() {
        hiloTensionFacade.remove(hiloTensionSeleccionado);
        String namedQuery2 = "HiloTension.findByIdTejidoCirculares";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idTejidoCirculares", tejidoCirculares);
        hiloTensionList = hiloTensionFacade.findByNamedQuery(namedQuery2, parametros2);
    }
    
    public void borrarHiloTubular() {
        hiloTubularFacade.remove(hiloTubularSeleccionado);
        String namedQuery3 = "HiloTubular.findByIdTejidoCirculares";
        Map<String, Object> parametros3 = new HashMap<>();
        parametros3.put("idTejidoCirculares", tejidoCirculares);
        hiloTubularList = hiloTubularFacade.findByNamedQuery(namedQuery3, parametros3);
    }
    
    public void llenarPrefijado() {
        prefijadoList = new ArrayList<>();
        stringList = new String[4];
        stringList[0] = "1";
        stringList[1] = "Salida de aire";
        stringList[2] = "1.29";
        stringList[3] = "";
        prefijadoList.add(stringList);
        stringList = new String[4];
        stringList[0] = "2";
        stringList[1] = "Entrada de vapor";
        stringList[2] = "4.50";
        stringList[3] = "";
        prefijadoList.add(stringList);
        stringList = new String[4];
        stringList[0] = "3";
        stringList[1] = "Vapor caliente - Prefijación de la medida";
        stringList[2] = "35";
        stringList[3] = "";
        prefijadoList.add(stringList);
        stringList = new String[4];
        stringList[0] = "4";
        stringList[1] = "Salida de vapor";
        stringList[2] = "2.30";
        stringList[3] = "";
        prefijadoList.add(stringList);
        stringList = new String[4];
        stringList[0] = "5";
        stringList[1] = "Enfriamento de la medida";
        stringList[2] = "9";
        stringList[3] = "";
        prefijadoList.add(stringList);
        stringList = new String[4];
        stringList[0] = "6";
        stringList[1] = "Entrada de aire";
        stringList[2] = "70";
        stringList[3] = "";
        prefijadoList.add(stringList);
        stringList = new String[4];
        stringList[0] = "7";
        stringList[1] = "Descarga de descondensación";
        stringList[2] = "35";
        stringList[3] = "";
        prefijadoList.add(stringList);
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
    
    public void lanzarMensajeInformacion(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", mensaje));
    }

    public void lanzarMensajeAdvertencia(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", mensaje));
    }

    public void lanzarMensajeError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje));
    }

    public HiloTejidoCirculares getHiloTejidoCircularesSeleccionado() {
        return hiloTejidoCircularesSeleccionado;
    }

    public void setHiloTejidoCircularesSeleccionado(HiloTejidoCirculares hiloTejidoCircularesSeleccionado) {
        this.hiloTejidoCircularesSeleccionado = hiloTejidoCircularesSeleccionado;
    }

    
    
    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public HiloTension getHiloTension() {
        return hiloTension;
    }

    public void setHiloTension(HiloTension hiloTension) {
        this.hiloTension = hiloTension;
    }

    public HiloTubular getHiloTubular() {
        return hiloTubular;
    }

    public List<String[]> getPrefijadoList() {
        return prefijadoList;
    }

    public HiloTension getHiloTensionSeleccionado() {
        return hiloTensionSeleccionado;
    }

    public void setHiloTensionSeleccionado(HiloTension hiloTensionSeleccionado) {
        this.hiloTensionSeleccionado = hiloTensionSeleccionado;
    }

    public HiloTubular getHiloTubularSeleccionado() {
        return hiloTubularSeleccionado;
    }

    public void setHiloTubularSeleccionado(HiloTubular hiloTubularSeleccionado) {
        this.hiloTubularSeleccionado = hiloTubularSeleccionado;
    }

    public void setPrefijadoList(List<String[]> prefijadoList) {
        this.prefijadoList = prefijadoList;
    }

    public void setHiloTubular(HiloTubular hiloTubular) {
        this.hiloTubular = hiloTubular;
    }

    public HiloTejidoCirculares getHiloTejidoCirculares() {
        return hiloTejidoCirculares;
    }

    public List<MaterialEstilo> getMaterialEstiloList() {
        return materialEstiloList;
    }

    public void setMaterialEstiloList(List<MaterialEstilo> materialEstiloList) {
        this.materialEstiloList = materialEstiloList;
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

    public void setHiloTejidoCirculares(HiloTejidoCirculares hiloTejidoCirculares) {
        this.hiloTejidoCirculares = hiloTejidoCirculares;
    }

    public TejidoCirculares getTejidoCirculares() {
        return tejidoCirculares;
    }

    public void setTejidoCirculares(TejidoCirculares tejidoCirculares) {
        this.tejidoCirculares = tejidoCirculares;
    }

    public List<Maquina> getMaquinaList() {
        return maquinaList;
    }

    public void setMaquinaList(List<Maquina> maquinaList) {
        this.maquinaList = maquinaList;
    }

    public List<Embalaje> getEmbalajeList() {
        return embalajeList;
    }

    public void setEmbalajeList(List<Embalaje> embalajeList) {
        this.embalajeList = embalajeList;
    }

    public List<HiloParametro> getHiloParametroList() {
        return hiloParametroList;
    }

    public void setHiloParametroList(List<HiloParametro> hiloParametroList) {
        this.hiloParametroList = hiloParametroList;
    }

    public List<HiloTejidoCirculares> getHiloTejidoCircularesList() {
        return hiloTejidoCircularesList;
    }

    public void setHiloTejidoCircularesList(List<HiloTejidoCirculares> hiloTejidoCircularesList) {
        this.hiloTejidoCircularesList = hiloTejidoCircularesList;
    }

    public List<HiloTension> getHiloTensionList() {
        return hiloTensionList;
    }

    public void setHiloTensionList(List<HiloTension> hiloTensionList) {
        this.hiloTensionList = hiloTensionList;
    }

    public List<HiloTubular> getHiloTubularList() {
        return hiloTubularList;
    }

    public void setHiloTubularList(List<HiloTubular> hiloTubularList) {
        this.hiloTubularList = hiloTubularList;
    }

    public List<PrefijadoObs> getPrefijadoObsList() {
        return prefijadoObsList;
    }

    public void setPrefijadoObsList(List<PrefijadoObs> prefijadoObsList) {
        this.prefijadoObsList = prefijadoObsList;
    }

}
