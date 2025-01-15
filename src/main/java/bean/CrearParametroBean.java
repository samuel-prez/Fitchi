/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import entity.ParametroPrenda;
import entity.HiloParametro;
import entity.Color;
import entity.ControlMedidasParametro;
import entity.TubularDoblez;
import facade.ParametroPrendaFacade;
import facade.HiloParametroFacade;
import facade.ColorFacade;
import facade.ControlMedidasParametroFacade;
import facade.TubularDoblezFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class CrearParametroBean implements Serializable {
    
    @EJB
    private ParametroPrendaFacade parametroPrendaFacade;
    @EJB
    private HiloParametroFacade hiloParametroFacade;
    @EJB
    private ColorFacade colorFacade;
    @EJB
    private ControlMedidasParametroFacade controlMedidasParametroFacade;
    @EJB
    private TubularDoblezFacade tubularDoblezFacade;
    
    
    private Color color;
    private ParametroPrenda parametroPrenda;
    private HiloParametro hiloParametro;
    private ControlMedidasParametro controlMedidasParametro;
    private TubularDoblez tubularDoblez;
    private List<Color> colorList;
    private List<ParametroPrenda> parametroPrendaList;
    private List<HiloParametro> hiloParametroList;
    private List<ControlMedidasParametro> controlMedidasParametroList;
    private List<TubularDoblez> tubularDoblezList;
    

    @PostConstruct
    public void init() {
        llenarListas();
    }
    
    public void crearParametroPrenda() {
        parametroPrendaFacade.create(parametroPrenda);
        parametroPrendaList.add(parametroPrenda);
        info("Parámetro prenda creado");
    }
    
    public void crearHiloParametro() {
        hiloParametroFacade.create(hiloParametro);
        hiloParametroList.add(hiloParametro);
        info("Parámetro prenda creado");
    }
    
    public void crearControlMedidasParametro() {
        controlMedidasParametroFacade.create(controlMedidasParametro);
        controlMedidasParametroList.add(controlMedidasParametro);
        info("Control medidas parámetro creado");
    }
    
    public void crearColor() {
        colorFacade.create(color);
        colorList.add(color);
        info("Color creado");
    }
    
    public void crearTubularDoblez() {
        tubularDoblezFacade.create(tubularDoblez);
        tubularDoblezList.add(tubularDoblez);
        info("Tubular doblez creado");
    }
    
    public void abrirCrearPP() {
        parametroPrenda = new ParametroPrenda();
        PrimeFaces.current().executeScript("PF('dlgCrearPP').show();");
    }
    
    public void abrirCrearHP() {
        hiloParametro = new HiloParametro();
        PrimeFaces.current().executeScript("PF('dlgCrearHP').show();");
    }
    
    public void abrirCrearCMP() {
        controlMedidasParametro = new ControlMedidasParametro();
        PrimeFaces.current().executeScript("PF('dlgCrearCMP').show();");
    }
    
    public void abrirCrearColor() {
        color = new Color();
        PrimeFaces.current().executeScript("PF('dlgCrearColor').show();");
    }
    
    public void abrirCrearTD() {
        tubularDoblez = new TubularDoblez();
        PrimeFaces.current().executeScript("PF('dlgCrearTD').show();");
    }
    
    public void llenarListas() {
        colorList = colorFacade.findAll();
        parametroPrendaList = parametroPrendaFacade.findAll();
        hiloParametroList = hiloParametroFacade.findAll();
        controlMedidasParametroList = controlMedidasParametroFacade.findAll();
        tubularDoblezList = tubularDoblezFacade.findAll();
    }
    
    public void info(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Informacion", mensaje));
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ParametroPrenda getParametroPrenda() {
        return parametroPrenda;
    }

    public TubularDoblez getTubularDoblez() {
        return tubularDoblez;
    }

    public void setTubularDoblez(TubularDoblez tubularDoblez) {
        this.tubularDoblez = tubularDoblez;
    }

    public List<TubularDoblez> getTubularDoblezList() {
        return tubularDoblezList;
    }

    public void setTubularDoblezList(List<TubularDoblez> tubularDoblezList) {
        this.tubularDoblezList = tubularDoblezList;
    }

    public void setParametroPrenda(ParametroPrenda parametroPrenda) {
        this.parametroPrenda = parametroPrenda;
    }

    public HiloParametro getHiloParametro() {
        return hiloParametro;
    }

    public void setHiloParametro(HiloParametro hiloParametro) {
        this.hiloParametro = hiloParametro;
    }

    public ControlMedidasParametro getControlMedidasParametro() {
        return controlMedidasParametro;
    }

    public List<ControlMedidasParametro> getControlMedidasParametroList() {
        return controlMedidasParametroList;
    }

    public void setControlMedidasParametroList(List<ControlMedidasParametro> controlMedidasParametroList) {
        this.controlMedidasParametroList = controlMedidasParametroList;
    }

    public void setControlMedidasParametro(ControlMedidasParametro controlMedidasParametro) {
        this.controlMedidasParametro = controlMedidasParametro;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
    }

    public List<ParametroPrenda> getParametroPrendaList() {
        return parametroPrendaList;
    }

    public void setParametroPrendaList(List<ParametroPrenda> parametroPrendaList) {
        this.parametroPrendaList = parametroPrendaList;
    }

    public List<HiloParametro> getHiloParametroList() {
        return hiloParametroList;
    }

    public void setHiloParametroList(List<HiloParametro> hiloParametroList) {
        this.hiloParametroList = hiloParametroList;
    }

    
    
}
