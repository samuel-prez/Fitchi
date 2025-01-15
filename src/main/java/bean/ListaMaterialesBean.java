package bean;

import entity.Estilo;
import entity.Usuario;
import entity.Material;
import entity.MaterialEstilo;
import entity.Area;
import facade.EstiloFacade;
import facade.MaterialFacade;
import facade.MaterialEstiloFacade;
import facade.AreaFacade;
import facade.ProveedorFacade;
import facade.UsuarioFacade;
import jasper.utilidades.JasperUtilidadesBeanLocal;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.ReorderEvent;

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class ListaMaterialesBean implements Serializable {

    @EJB
    private EstiloFacade estiloFacade;
    @EJB
    private MaterialFacade materialFacade;
    @EJB
    private MaterialEstiloFacade materialEstiloFacade;
    @EJB
    private AreaFacade areaFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private JasperUtilidadesBeanLocal jasperUtilidadesBean;

    private Estilo estilo;
    private String estiloRecibido;
    private List<MaterialEstilo> materialEstiloTejidoList;
    private List<MaterialEstilo> materialEstiloConfeccionList;
    private List<MaterialEstilo> materialEstiloTintoreriaList;
    private List<MaterialEstilo> materialEstiloAcabadosList;
    private List<Material> materialListTejido;
    private List<Material> materialListConfeccion;
    private List<Material> materialListTintoreria;
    private List<Material> materialListAcabados;
    private MaterialEstilo materialEstiloTejido;
    private MaterialEstilo materialEstiloConfeccion;
    private MaterialEstilo materialEstiloTintoreria;
    private MaterialEstilo materialEstiloAcabados;
    private MaterialEstilo materialEstiloSeleccionado;
    private Area area;
    private boolean dsbEdicion;
    private boolean dsbImpresion;
    private final int idRolConsulta = 3;
    private final int idRolFull = 2;
    private final int idRolCalidad = 4;
    private Usuario usuario;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        estiloRecibido = params.get("estiloRecibido");
        traerUsuario();
        buscarEstilo();
        llenarListas();
        validarPermisos();
    }
    
    public void onRowReorderTejido(ReorderEvent event) {
        int cont = 0;
        for (MaterialEstilo pr : materialEstiloTejidoList) {
            pr.setOrden(cont);
            cont++;
            materialEstiloFacade.edit(pr);
        }
    }
    
    public void onRowReorderConfeccion(ReorderEvent event) {
        int cont = 0;
        for (MaterialEstilo pr : materialEstiloConfeccionList) {
            pr.setOrden(cont);
            cont++;
            materialEstiloFacade.edit(pr);
        }
    }
    
    public void onRowReorderTintoreria(ReorderEvent event) {
        int cont = 0;
        for (MaterialEstilo pr : materialEstiloTintoreriaList) {
            pr.setOrden(cont);
            cont++;
            materialEstiloFacade.edit(pr);
        }
    }
    
    public void onRowReorderAcabados(ReorderEvent event) {
        int cont = 0;
        for (MaterialEstilo pr : materialEstiloAcabadosList) {
            pr.setOrden(cont);
            cont++;
            materialEstiloFacade.edit(pr);
        }
    }

    public String redirect(String opc) {
        return opc + "?faces-redirect=true&amp;estiloRecibido=" + estiloRecibido;
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
        //llenar lista material tejido
        String[] nombresAreas = {"TEJIDO", "CONFECCION", "TINTORERIA", "ACABADOS"};

        String namedQuery = "MaterialEstilo.findByIdEstiloAndArea";
        List<List<MaterialEstilo>> materialEstiloLists = new ArrayList<>();

        for (String nombreArea : nombresAreas) {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idEstilo", estilo);
            parametros.put("nombre", nombreArea);

            List<MaterialEstilo> materialEstiloList = materialEstiloFacade.findByNamedQuery(namedQuery, parametros);
            materialEstiloLists.add(materialEstiloList);
        }
        materialEstiloTejidoList = materialEstiloLists.get(0);
        materialEstiloConfeccionList = materialEstiloLists.get(1);
        materialEstiloTintoreriaList = materialEstiloLists.get(2);
        materialEstiloAcabadosList = materialEstiloLists.get(3);

        String namedQuery2 = "Material.findByArea";
        List<List<Material>> materialLists = new ArrayList<>();
        for (String nombreArea : nombresAreas) {
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("nombre", nombreArea);
            List<Material> materialList = materialFacade.findByNamedQuery(namedQuery2, parametros);
            materialLists.add(materialList);
        }
        materialListTejido = materialLists.get(0);
        materialListConfeccion = materialLists.get(1);
        materialListTintoreria = materialLists.get(2);
        materialListAcabados = materialLists.get(3);

        materialEstiloTejido = new MaterialEstilo();
        materialEstiloConfeccion = new MaterialEstilo();
        materialEstiloTintoreria = new MaterialEstilo();
        materialEstiloAcabados = new MaterialEstilo();
        materialEstiloSeleccionado = new MaterialEstilo();

    }

    public void añadirMaterialTejido() {
        materialEstiloTejido.setIdEstilo(estilo);
        materialEstiloTejido.setIdCreado(usuario);
        String namedQuery = "Area.findByNombre";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", "TEJIDO");
        area = areaFacade.getObject(namedQuery, parametros);
        materialEstiloTejido.setIdArea(area);
        materialEstiloTejido.setCreado(Calendar.getInstance().getTime());
        materialEstiloTejido.setOrden(materialEstiloTejidoList.size());
        materialEstiloFacade.create(materialEstiloTejido);
        materialEstiloTejido = new MaterialEstilo();
        //llenar de nuevo la lista
        String namedQuery2 = "MaterialEstilo.findByIdEstiloAndArea";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        parametros2.put("nombre", "TEJIDO");
        materialEstiloTejidoList = materialEstiloFacade.findByNamedQuery(namedQuery2, parametros2);
    }

    public void añadirMaterialConfeccion() {
        materialEstiloConfeccion.setIdEstilo(estilo);
        materialEstiloConfeccion.setIdCreado(usuario);
        String namedQuery = "Area.findByNombre";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", "CONFECCION");
        area = areaFacade.getObject(namedQuery, parametros);
        materialEstiloConfeccion.setIdArea(area);
        materialEstiloConfeccion.setCreado(Calendar.getInstance().getTime());
        materialEstiloConfeccion.setOrden(materialEstiloConfeccionList.size());
        materialEstiloFacade.create(materialEstiloConfeccion);
        materialEstiloConfeccion = new MaterialEstilo();
        //llenar de nuevo la lista
        String namedQuery2 = "MaterialEstilo.findByIdEstiloAndArea";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        parametros2.put("nombre", "CONFECCION");
        materialEstiloConfeccionList = materialEstiloFacade.findByNamedQuery(namedQuery2, parametros2);
    }

    public void añadirMaterialTintoreria() {
        materialEstiloTintoreria.setIdEstilo(estilo);
        materialEstiloTintoreria.setIdCreado(usuario);
        String namedQuery = "Area.findByNombre";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", "TINTORERIA");
        area = areaFacade.getObject(namedQuery, parametros);
        materialEstiloTintoreria.setIdArea(area);
        materialEstiloTintoreria.setCreado(Calendar.getInstance().getTime());
        materialEstiloTintoreria.setOrden(materialEstiloTintoreriaList.size());
        materialEstiloFacade.create(materialEstiloTintoreria);
        materialEstiloTintoreria = new MaterialEstilo();
        //llenar de nuevo la lista
        String namedQuery2 = "MaterialEstilo.findByIdEstiloAndArea";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        parametros2.put("nombre", "TINTORERIA");
        materialEstiloTintoreriaList = materialEstiloFacade.findByNamedQuery(namedQuery2, parametros2);
    }

    public void añadirMaterialAcabados() {
        materialEstiloAcabados.setIdEstilo(estilo);
        materialEstiloAcabados.setIdCreado(usuario);
        String namedQuery = "Area.findByNombre";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("nombre", "ACABADOS");
        area = areaFacade.getObject(namedQuery, parametros);
        materialEstiloAcabados.setIdArea(area);
        materialEstiloAcabados.setCreado(Calendar.getInstance().getTime());
        materialEstiloAcabados.setOrden(materialEstiloAcabadosList.size());
        materialEstiloFacade.create(materialEstiloAcabados);
        materialEstiloAcabados = new MaterialEstilo();
        //llenar de nuevo la lista
        String namedQuery2 = "MaterialEstilo.findByIdEstiloAndArea";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        parametros2.put("nombre", "ACABADOS");
        materialEstiloAcabadosList = materialEstiloFacade.findByNamedQuery(namedQuery2, parametros2);
    }

    public void imprimirReporte() {
        String rutaWebPages = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String rutaCarpetaReportes = rutaWebPages + File.separator + "reports" + File.separator;
        String rutaReportePaqueteTecnico = rutaCarpetaReportes + File.separator + "lista_materiales.jrxml";
        Map<String, Object> parametros1 = new HashMap<>();
        parametros1.put("ID_ESTILO", estilo.getIdEstilo());
        parametros1.put("SUBREPORT_DIR", rutaCarpetaReportes);
        String nombrePDFGenerado = "lista_materiales_" + estilo.getEstilo() + ".pdf";
        jasperUtilidadesBean.descargarReporte(rutaReportePaqueteTecnico, parametros1, nombrePDFGenerado);

    }

    public void borrarMaterialEstilo(String opc) {
        materialEstiloFacade.remove(materialEstiloSeleccionado);
        //llena la lista que se actualizó con el borrado
        String namedQuery = "MaterialEstilo.findByIdEstiloAndArea";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        switch (opc) {
            case "TEJIDO":
                parametros.put("nombre", "TEJIDO");
                materialEstiloTejidoList = materialEstiloFacade.findByNamedQuery(namedQuery, parametros);
                break;
            case "CONFECCION":
                parametros.put("nombre", "CONFECCION");
                materialEstiloConfeccionList = materialEstiloFacade.findByNamedQuery(namedQuery, parametros);
                break;
            case "TINTORERIA":
                parametros.put("nombre", "TINTORERIA");
                materialEstiloTintoreriaList = materialEstiloFacade.findByNamedQuery(namedQuery, parametros);
                break;
            case "ACABADOS":
                parametros.put("nombre", "ACABADOS");
                materialEstiloAcabadosList = materialEstiloFacade.findByNamedQuery(namedQuery, parametros);
                break;
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

    public void setEstiloRecibido(String estiloRecibido) {
        this.estiloRecibido = estiloRecibido;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public List<MaterialEstilo> getMaterialEstiloTejidoList() {
        return materialEstiloTejidoList;
    }

    public void setMaterialEstiloTejidoList(List<MaterialEstilo> materialEstiloTejidoList) {
        this.materialEstiloTejidoList = materialEstiloTejidoList;
    }

    public List<MaterialEstilo> getMaterialEstiloConfeccionList() {
        return materialEstiloConfeccionList;
    }

    public void setMaterialEstiloConfeccionList(List<MaterialEstilo> materialEstiloConfeccionList) {
        this.materialEstiloConfeccionList = materialEstiloConfeccionList;
    }

    public List<MaterialEstilo> getMaterialEstiloTintoreriaList() {
        return materialEstiloTintoreriaList;
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

    public void setMaterialEstiloTintoreriaList(List<MaterialEstilo> materialEstiloTintoreriaList) {
        this.materialEstiloTintoreriaList = materialEstiloTintoreriaList;
    }

    public List<MaterialEstilo> getMaterialEstiloAcabadosList() {
        return materialEstiloAcabadosList;
    }

    public void setMaterialEstiloAcabadosList(List<MaterialEstilo> materialEstiloAcabadosList) {
        this.materialEstiloAcabadosList = materialEstiloAcabadosList;
    }

    public MaterialEstilo getMaterialEstiloTejido() {
        return materialEstiloTejido;
    }

    public void setMaterialEstiloTejido(MaterialEstilo materialEstiloTejido) {
        this.materialEstiloTejido = materialEstiloTejido;
    }

    public MaterialEstilo getMaterialEstiloConfeccion() {
        return materialEstiloConfeccion;
    }

    public void setMaterialEstiloConfeccion(MaterialEstilo materialEstiloConfeccion) {
        this.materialEstiloConfeccion = materialEstiloConfeccion;
    }

    public MaterialEstilo getMaterialEstiloTintoreria() {
        return materialEstiloTintoreria;
    }

    public void setMaterialEstiloTintoreria(MaterialEstilo materialEstiloTintoreria) {
        this.materialEstiloTintoreria = materialEstiloTintoreria;
    }

    public MaterialEstilo getMaterialEstiloAcabados() {
        return materialEstiloAcabados;
    }

    public void setMaterialEstiloAcabados(MaterialEstilo materialEstiloAcabados) {
        this.materialEstiloAcabados = materialEstiloAcabados;
    }

    public MaterialEstilo getMaterialEstiloSeleccionado() {
        return materialEstiloSeleccionado;
    }

    public void setMaterialEstiloSeleccionado(MaterialEstilo materialEstiloSeleccionado) {
        this.materialEstiloSeleccionado = materialEstiloSeleccionado;
    }

    public List<Material> getMaterialListTejido() {
        return materialListTejido;
    }

    public void setMaterialListTejido(List<Material> materialListTejido) {
        this.materialListTejido = materialListTejido;
    }

    public List<Material> getMaterialListConfeccion() {
        return materialListConfeccion;
    }

    public void setMaterialListConfeccion(List<Material> materialListConfeccion) {
        this.materialListConfeccion = materialListConfeccion;
    }

    public List<Material> getMaterialListTintoreria() {
        return materialListTintoreria;
    }

    public void setMaterialListTintoreria(List<Material> materialListTintoreria) {
        this.materialListTintoreria = materialListTintoreria;
    }

    public List<Material> getMaterialListAcabados() {
        return materialListAcabados;
    }

    public void setMaterialListAcabados(List<Material> materialListAcabados) {
        this.materialListAcabados = materialListAcabados;
    }

}
