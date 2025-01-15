package bean;

import entity.Area;
import entity.Estilo;
import entity.Material;
import entity.Proveedor;
import entity.Unidad;
import facade.AreaFacade;
import facade.MaterialFacade;
import facade.ProveedorFacade;
import facade.UnidadFacade;
import facade.UsuarioFacade;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class CrearMaterialBean implements Serializable{
    
    @EJB
    private MaterialFacade materialFacade;
    @EJB
    private ProveedorFacade proveedorFacade;
    @EJB
    private UnidadFacade unidadFacade;
    @EJB
    private AreaFacade areaFacade;
    
    private Material material;
    private Proveedor proveedor;
    private Unidad unidad;
    private List<Material> materialList;
    private List<Proveedor> proveedorList;
    private List<Unidad> unidadList;
    private List<Area> areaList;
    
    @PostConstruct
    public void init() {
        material = new Material();
        llenarListas();
    }
    
    public void abrirCrearMaterial() {
        material = new Material();
        PrimeFaces.current().executeScript("PF('dlgCrearMaterial').show();");
    }
    
    public void abrirCrearProveedor() {
        proveedor = new Proveedor();
        PrimeFaces.current().executeScript("PF('dlgCrearProveedor').show();");
    }
    
    private void llenarListas() {
        materialList = materialFacade.findAll();
        proveedorList = proveedorFacade.findAll();
        unidadList = unidadFacade.findAll();
        areaList = areaFacade.findAll();
    }
    
    public void borrarMaterial() {
        materialFacade.remove(material);
        info("material borrado");
    }
    
    public void crearMaterial() {
        materialFacade.create(material);
        materialList.add(material);
        info("Material creado");
    }
    
    public void crearProveedor() {
        proveedorFacade.create(proveedor);
        info("Proveedor creado");
    }
    
    public void crearUnidad() {
        unidadFacade.create(unidad);
        info("Unidad de medida creada");
    }
    
    public void info(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Informacion", mensaje));
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Unidad getUnidad() {
        return unidad;
    }

    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }

    public List<Material> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<Material> materialList) {
        this.materialList = materialList;
    }

    public List<Proveedor> getProveedorList() {
        return proveedorList;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public void setProveedorList(List<Proveedor> proveedorList) {
        this.proveedorList = proveedorList;
    }

    public List<Unidad> getUnidadList() {
        return unidadList;
    }

    public void setUnidadList(List<Unidad> unidadList) {
        this.unidadList = unidadList;
    }

    
    
    
}
