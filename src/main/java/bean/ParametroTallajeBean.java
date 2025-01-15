package bean;

import entity.Estilo;
import entity.HiloParametro;
import entity.HiloTubular;
import entity.Usuario;
import entity.TallaEstilo;
import entity.ParametroTallaje;
import entity.ParametroTallajeTalla;
import entity.ParametroTallajeDimension;
import entity.ParametroTallajeAlista;
import entity.ParametroTallajeDoblez;
import entity.TubularDoblez;
import facade.EstiloFacade;
import facade.HiloParametroFacade;
import facade.HiloTensionFacade;
import facade.HiloTubularFacade;
import facade.UsuarioFacade;
import facade.ParametroTallajeFacade;
import facade.ParametroTallajeTallaFacade;
import facade.ParametroTallajeDimensionFacade;
import facade.ParametroTallajeAlistaFacade;
import facade.ParametroTallajeDoblezFacade;
import facade.TallaEstiloFacade;
import facade.TubularDoblezFacade;
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

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class ParametroTallajeBean implements Serializable {

    @EJB
    private EstiloFacade estiloFacade;
    @EJB
    private HiloParametroFacade hiloParametroFacade;
    @EJB
    private HiloTubularFacade hiloTubularFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private JasperUtilidadesBeanLocal jasperUtilidadesBean;
    @EJB
    private ParametroTallajeFacade parametroTallajeFacade;
    @EJB
    private ParametroTallajeTallaFacade parametroTallajeTallaFacade;
    @EJB
    private ParametroTallajeDimensionFacade parametroTallajeDimensionFacade;
    @EJB
    private ParametroTallajeAlistaFacade parametroTallajeAlistaFacade;
    @EJB
    private TallaEstiloFacade tallaEstiloFacade;
    @EJB
    private ParametroTallajeDoblezFacade parametroTallajeDoblezFacade;
    @EJB
    private TubularDoblezFacade tubularDoblezFacade;

    private String estiloRecibido;
    private Estilo estilo;
    private Usuario usuario;
    private Date fechaActual;
    private ParametroTallaje parametroTallaje;
    private ParametroTallajeTalla parametroTallajeTalla;
    private ParametroTallajeTalla pttCopiado;
    private ParametroTallajeTalla pttPegado;
    private ParametroTallajeDimension parametroTallajeDimension;
    private ParametroTallajeDimension ptdSeleccionado;
    private ParametroTallajeAlista parametroTallajeAlista;
    private ParametroTallajeAlista ptaSeleccionado;
    private ParametroTallajeDoblez parametroTallajeDoblez;
    private ParametroTallajeDoblez ptdoSeleccionado;
    private List<ParametroTallaje> parametroTallajeList;
    private List<ParametroTallajeTalla> parametroTallajeTallaList;
    private List<ParametroTallajeDimension> parametroTallajeDimensionList;
    private List<ParametroTallajeAlista> parametroTallajeAlistaList;
    private List<ParametroTallajeDoblez> parametroTallajeDoblezList;
    private List<HiloParametro> hiloParametroList;
    private List<TallaEstilo> tallaEstiloList;
    private List<HiloTubular> hiloTubularList;
    private List<TubularDoblez> tubularDoblezList;
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
        parametroTallajeDimension = new ParametroTallajeDimension();
        parametroTallajeAlista = new ParametroTallajeAlista();
        parametroTallajeDoblez = new ParametroTallajeDoblez();
        parametroTallajeTalla = new ParametroTallajeTalla();
        traerUsuario();
        buscarEstilo();
        llenarListas();
        validarPermisos();
    }

    public String redirect(String opc) {
        return opc + "?faces-redirect=true&amp;estiloRecibido=" + estiloRecibido;
    }

    public void editarDimension() {
        parametroTallajeDimensionFacade.edit(parametroTallajeDimension);
        System.out.println("Registro editado");
    }

    public void borrarPtd() { //borrar parametroTallajeDimensionSeleccionado
        parametroTallajeDimensionFacade.remove(ptdSeleccionado);
        String namedQuery = "ParametroTallajeDimension.findByIdParametroTallajeTalla";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idParametroTallajeTalla", parametroTallajeTalla);
        parametroTallajeDimensionList = parametroTallajeDimensionFacade.findByNamedQuery(namedQuery, parametros);
    }

    public void borrarPta() { //borrar parametroTallajeAlistamientoSeleccionado
        parametroTallajeAlistaFacade.remove(ptaSeleccionado);
        String namedQuery2 = "ParametroTallajeAlista.findByIdParametroTallajeTalla";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idParametroTallajeTalla", parametroTallajeTalla);
        parametroTallajeAlistaList = parametroTallajeAlistaFacade.findByNamedQuery(namedQuery2, parametros2);
    }

    public void borrarPtdo() { //borrar parametroTallajeDoblezSeleccionado
        parametroTallajeDoblezFacade.remove(ptdoSeleccionado);
        String namedQuery3 = "ParametroTallajeDoblez.findByIdParametroTallajeTalla";
        Map<String, Object> parametros3 = new HashMap<>();
        parametros3.put("idParametroTallajeTalla", parametroTallajeTalla);
        parametroTallajeDoblezList = parametroTallajeDoblezFacade.findByNamedQuery(namedQuery3, parametros3);
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

    public void imprimirReporte() {
        String rutaWebPages = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String rutaCarpetaReportes = rutaWebPages + File.separator + "reports" + File.separator;
        String rutaReportePaqueteTecnico = rutaCarpetaReportes + File.separator + "parametro_tallaje.jrxml";
        Map<String, Object> parametros1 = new HashMap<>();
        parametros1.put("ID_ESTILO", estilo.getIdEstilo());
        parametros1.put("SUBREPORT_DIR", rutaCarpetaReportes);
        String nombrePDFGenerado = "parametro_tallaje " + estilo.getEstilo() + ".pdf";
        jasperUtilidadesBean.descargarReporte(rutaReportePaqueteTecnico, parametros1, nombrePDFGenerado);
    }

    public void imprimirReporteTubular() {
        String rutaWebPages = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String rutaCarpetaReportes = rutaWebPages + File.separator + "reports" + File.separator;
        String rutaReportePaqueteTecnico = rutaCarpetaReportes + File.separator + "tubular_doblez.jrxml";
        Map<String, Object> parametros1 = new HashMap<>();
        parametros1.put("ID_ESTILO", estilo.getIdEstilo());
        parametros1.put("SUBREPORT_DIR", rutaCarpetaReportes);
        String nombrePDFGenerado = "tubular_doblez " + estilo.getEstilo() + ".pdf";
        jasperUtilidadesBean.descargarReporte(rutaReportePaqueteTecnico, parametros1, nombrePDFGenerado);
    }

    public void guardarObs() {
        parametroTallajeTallaFacade.edit(parametroTallajeTalla);
        lanzarMensajeInformacion("Guardado");
    }

    public void guardarLector() {
        System.out.println("enrta");
    }

    public void guardarDimension() {
        if (parametroTallajeDimension.getIdParametroTallajeDimension() == null) {
            parametroTallajeDimension.setIdParametroTallajeTalla(parametroTallajeTalla);
            parametroTallajeDimensionFacade.create(parametroTallajeDimension);
            parametroTallajeDimensionList.add(parametroTallajeDimension);
            lanzarMensajeInformacion("Parametro creado");
        } else {
            parametroTallajeDimensionFacade.edit(parametroTallajeDimension);
            lanzarMensajeInformacion("Parametro editado");
        }
        PrimeFaces.current().executeScript("PF('dlgDimension').hide();");
    }

    public void guardarAlista() {
        parametroTallajeAlista.setIdParametroTallajeTalla(parametroTallajeTalla);
        parametroTallajeAlistaFacade.create(parametroTallajeAlista);
        parametroTallajeAlistaList.add(parametroTallajeAlista);
        PrimeFaces.current().executeScript("PF('dlgAlista').hide();");
    }
    public void editarAlista() {
        parametroTallajeAlistaFacade.edit(parametroTallajeAlista);
        PrimeFaces.current().executeScript("PF('dlgAlistaEditar').hide();");
    }
    
    public void onRowReorder(ReorderEvent event) {
        int cont = 0;
        for (ParametroTallajeAlista pr : parametroTallajeAlistaList) {
            pr.setOrden(cont);
            cont++;
            parametroTallajeAlistaFacade.edit(pr);
        }
    }

    public void guardarDoblez() {
        parametroTallajeDoblez.setIdParametroTallajeTalla(parametroTallajeTalla);
        parametroTallajeDoblezFacade.create(parametroTallajeDoblez);
        parametroTallajeDoblezList.add(parametroTallajeDoblez);
        PrimeFaces.current().executeScript("PF('dlgDoblez').hide();");
    }

    public void editarDoblez() {
        parametroTallajeDoblezFacade.edit(parametroTallajeDoblez);
        lanzarMensajeInformacion("Tubular doblez editado");
        PrimeFaces.current().executeScript("PF('dlgDoblez').hide();");
    }

    public void copiar() {
        List<ParametroTallajeDimension> ptdCopiado;
        List<ParametroTallajeAlista> ptaCopiado;
        List<ParametroTallajeDoblez> ptdzCopiado;
        //copiar dimensión
        String namedQuery = "ParametroTallajeDimension.findByIdParametroTallajeTalla";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idParametroTallajeTalla", pttCopiado);
        ptdCopiado = parametroTallajeDimensionFacade.findByNamedQuery(namedQuery, parametros);
        for (ParametroTallajeDimension ptd : ptdCopiado) {
            ptd.setIdParametroTallajeTalla(pttPegado);
            ptd.setIdParametroTallajeDimension(null);//para que tome un nuevo consecutivo porque no sé si el .create lo hace
            parametroTallajeDimensionFacade.create(ptd);
        }
        //copiar alistamiento
        String namedQuery2 = "ParametroTallajeAlista.findByIdParametroTallajeTalla";
        ptaCopiado = parametroTallajeAlistaFacade.findByNamedQuery(namedQuery2, parametros);
        for (ParametroTallajeAlista pta : ptaCopiado) {
            pta.setIdParametroTallajeTalla(pttPegado);
            pta.setIdParametroTallajeAlista(null);
            parametroTallajeAlistaFacade.create(pta);
        }
        //copiar doblez
        String namedQuery3 = "ParametroTallajeDoblez.findByIdParametroTallajeTalla";
        ptdzCopiado = parametroTallajeDoblezFacade.findByNamedQuery(namedQuery3, parametros);
        for (ParametroTallajeDoblez ptdz : ptdzCopiado) {
            ptdz.setIdParametroTallajeTalla(pttPegado);
            ptdz.setIdParametroTallajeDoblez(null);
            parametroTallajeDoblezFacade.create(ptdz);
        }
        //copiar observaciones
        pttPegado.setObservaciones(pttCopiado.getObservaciones());
        parametroTallajeTallaFacade.edit(pttPegado);
        parametroTallajeTalla = new ParametroTallajeTalla();
        lanzarMensajeInformacion("Talla " + pttCopiado.getIdTallaEstilo().getIdTalla().getNombre() + " copiada en " + pttPegado.getIdTallaEstilo().getIdTalla().getNombre());
        //liberar memoria
        ptdCopiado = null;
        ptaCopiado = null;
        ptdzCopiado = null;
        parametroTallajeDimensionList = new ArrayList();
        parametroTallajeAlistaList = new ArrayList();
        parametroTallajeDoblezList = new ArrayList();
        PrimeFaces.current().executeScript("PF('dlgCopiar').hide();");
    }

    public void abrirDlgCopiar() {
        PrimeFaces.current().executeScript("PF('dlgCopiar').show();");
    }

    public void abrirDlgDimension() {
        parametroTallajeDimension = new ParametroTallajeDimension();
        //luego añadir para que no busque el material estilo si materialEstiloList no está vacío
        PrimeFaces.current().executeScript("PF('dlgDimension').show();");
    }

    public void abrirDlgDimensionEditar() {
        PrimeFaces.current().executeScript("PF('dlgDimension').show();");
    }

    public void abrirDlgAlista() {
        parametroTallajeAlista = new ParametroTallajeAlista();
        PrimeFaces.current().executeScript("PF('dlgAlista').show();");
    }
    
    public void abrirDlgAlistaEditar() {
        PrimeFaces.current().executeScript("PF('dlgAlistaEditar').show();");
    }
    
    public void abrirDlgTubularEditar() {
        PrimeFaces.current().executeScript("PF('dlgTubularEditar').show();");
    }

    public void abrirDlgDoblez() {
        parametroTallajeDoblez = new ParametroTallajeDoblez();
        PrimeFaces.current().executeScript("PF('dlgDoblez').show();");
    }

    public void dtTallaSeleccion() {
        String namedQuery = "ParametroTallajeDimension.findByIdParametroTallajeTalla";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idParametroTallajeTalla", parametroTallajeTalla);
        parametroTallajeDimensionList = parametroTallajeDimensionFacade.findByNamedQuery(namedQuery, parametros);
        String namedQuery2 = "ParametroTallajeAlista.findByIdParametroTallajeTalla";
        parametroTallajeAlistaList = parametroTallajeAlistaFacade.findByNamedQuery(namedQuery2, parametros);
        String namedQuery3 = "ParametroTallajeDoblez.findByIdParametroTallajeTalla";
        parametroTallajeDoblezList = parametroTallajeDoblezFacade.findByNamedQuery(namedQuery3, parametros);
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
        //buscar tejidoCirculares también
        String namedQuery2 = "ParametroTallaje.findByIdEstilo";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        parametroTallajeList = parametroTallajeFacade.findByNamedQuery(namedQuery2, parametros2);
        if (parametroTallajeList.isEmpty()) {
            parametroTallaje = new ParametroTallaje();
            parametroTallaje.setCreado(fechaActual);
            parametroTallaje.setIdCreado(usuario);
            parametroTallaje.setIdEstilo(estilo);
            parametroTallajeFacade.create(parametroTallaje);
            crearTallas();
        } else {
            parametroTallaje = parametroTallajeList.get(0);
        }
    }

    public void crearTallas() {
        String namedQuery = "TallaEstilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        tallaEstiloList = tallaEstiloFacade.findByNamedQuery(namedQuery, parametros);
        ParametroTallajeTalla ptt;
        for (TallaEstilo te : tallaEstiloList) {
            ptt = new ParametroTallajeTalla();
            ptt.setIdParametroTallaje(parametroTallaje);
            ptt.setIdTallaEstilo(te);
            ptt.setLector("Cetme 4 kls");
            parametroTallajeTallaFacade.create(ptt);
        }
    }

    public void llenarListas() {
        hiloParametroList = hiloParametroFacade.findAll();
        String namedQuery = "ParametroTallajeTalla.findByIdParametroTallaje";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idParametroTallaje", parametroTallaje);
        parametroTallajeTallaList = parametroTallajeTallaFacade.findByNamedQuery(namedQuery, parametros);

        String namedQuery2 = "HiloTubular.findByIdEstilo";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        hiloTubularList = hiloTubularFacade.findByNamedQuery(namedQuery2, parametros2);
        tubularDoblezList = tubularDoblezFacade.findAll();
    }

    public void lanzarMensajeInformacion(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", mensaje));
    }

    public ParametroTallajeDimension getPtdSeleccionado() {
        return ptdSeleccionado;
    }

    public ParametroTallajeAlista getPtaSeleccionado() {
        return ptaSeleccionado;
    }

    public void setPtaSeleccionado(ParametroTallajeAlista ptaSeleccionado) {
        this.ptaSeleccionado = ptaSeleccionado;
    }

    public ParametroTallajeDoblez getPtdoSeleccionado() {
        return ptdoSeleccionado;
    }

    public void setPtdoSeleccionado(ParametroTallajeDoblez ptdoSeleccionado) {
        this.ptdoSeleccionado = ptdoSeleccionado;
    }

    public void setPtdSeleccionado(ParametroTallajeDimension ptdSeleccionado) {
        this.ptdSeleccionado = ptdSeleccionado;
    }

    public void lanzarMensajeAdvertencia(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", mensaje));
    }

    public void lanzarMensajeError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", mensaje));
    }

    public ParametroTallaje getParametroTallaje() {
        return parametroTallaje;
    }

    public List<HiloTubular> getHiloTubularList() {
        return hiloTubularList;
    }

    public void setHiloTubularList(List<HiloTubular> hiloTubularList) {
        this.hiloTubularList = hiloTubularList;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public List<ParametroTallajeDoblez> getParametroTallajeDoblezList() {
        return parametroTallajeDoblezList;
    }

    public void setParametroTallajeDoblezList(List<ParametroTallajeDoblez> parametroTallajeDoblezList) {
        this.parametroTallajeDoblezList = parametroTallajeDoblezList;
    }

    public List<TubularDoblez> getTubularDoblezList() {
        return tubularDoblezList;
    }

    public void setTubularDoblezList(List<TubularDoblez> tubularDoblezList) {
        this.tubularDoblezList = tubularDoblezList;
    }

    public void setParametroTallaje(ParametroTallaje parametroTallaje) {
        this.parametroTallaje = parametroTallaje;
    }

    public List<ParametroTallaje> getParametroTallajeList() {
        return parametroTallajeList;
    }

    public ParametroTallajeTalla getParametroTallajeTalla() {
        return parametroTallajeTalla;
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

    public void setParametroTallajeTalla(ParametroTallajeTalla parametroTallajeTalla) {
        this.parametroTallajeTalla = parametroTallajeTalla;
    }

    public void setParametroTallajeList(List<ParametroTallaje> parametroTallajeList) {
        this.parametroTallajeList = parametroTallajeList;
    }

    public List<ParametroTallajeTalla> getParametroTallajeTallaList() {
        return parametroTallajeTallaList;
    }

    public void setParametroTallajeTallaList(List<ParametroTallajeTalla> parametroTallajeTallaList) {
        this.parametroTallajeTallaList = parametroTallajeTallaList;
    }

    public List<ParametroTallajeDimension> getParametroTallajeDimensionList() {
        return parametroTallajeDimensionList;
    }

    public void setParametroTallajeDimensionList(List<ParametroTallajeDimension> parametroTallajeDimensionList) {
        this.parametroTallajeDimensionList = parametroTallajeDimensionList;
    }

    public List<ParametroTallajeAlista> getParametroTallajeAlistaList() {
        return parametroTallajeAlistaList;
    }

    public void setParametroTallajeAlistaList(List<ParametroTallajeAlista> parametroTallajeAlistaList) {
        this.parametroTallajeAlistaList = parametroTallajeAlistaList;
    }

    public ParametroTallajeDimension getParametroTallajeDimension() {
        return parametroTallajeDimension;
    }

    public void setParametroTallajeDimension(ParametroTallajeDimension parametroTallajeDimension) {
        this.parametroTallajeDimension = parametroTallajeDimension;
    }

    public List<HiloParametro> getHiloParametroList() {
        return hiloParametroList;
    }

    public void setHiloParametroList(List<HiloParametro> hiloParametroList) {
        this.hiloParametroList = hiloParametroList;
    }

    public ParametroTallajeAlista getParametroTallajeAlista() {
        return parametroTallajeAlista;
    }

    public void setParametroTallajeAlista(ParametroTallajeAlista parametroTallajeAlista) {
        this.parametroTallajeAlista = parametroTallajeAlista;
    }

    public ParametroTallajeDoblez getParametroTallajeDoblez() {
        return parametroTallajeDoblez;
    }

    public void setParametroTallajeDoblez(ParametroTallajeDoblez parametroTallajeDoblez) {
        this.parametroTallajeDoblez = parametroTallajeDoblez;
    }

    public ParametroTallajeTalla getPttCopiado() {
        return pttCopiado;
    }

    public void setPttCopiado(ParametroTallajeTalla pttCopiado) {
        this.pttCopiado = pttCopiado;
    }

    public ParametroTallajeTalla getPttPegado() {
        return pttPegado;
    }

    public void setPttPegado(ParametroTallajeTalla pttPegado) {
        this.pttPegado = pttPegado;
    }

}
