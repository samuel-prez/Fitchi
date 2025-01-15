/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import entity.ColorEstilo;
import entity.ControlMedidas;
import entity.ControlMedidasTalla;
import entity.Estilo;
import entity.HiloTejidoCirculares;
import entity.HiloTension;
import entity.HiloTubular;
import entity.InsumoTercero;
import entity.MaterialEstilo;
import entity.ParametroPrendaEstilo;
import entity.ParametroTallaje;
import entity.ParametroTallajeAlista;
import entity.ParametroTallajeDimension;
import entity.ParametroTallajeDoblez;
import entity.ParametroTallajeTalla;
import entity.ProcesoAcabado;
import entity.ProcesoAcabadoFase;
import entity.ProcesoConfeccion;
import entity.ProcesoConfeccionFase;
import entity.TallaEstilo;
import entity.TejidoCirculares;
import entity.Usuario;
import facade.ColorEstiloFacade;
import facade.ControlMedidasFacade;
import facade.ControlMedidasTallaFacade;
import facade.EstiloFacade;
import facade.HiloTejidoCircularesFacade;
import facade.HiloTensionFacade;
import facade.HiloTubularFacade;
import facade.InsumoTerceroFacade;
import facade.MaterialEstiloFacade;
import facade.ParametroPrendaEstiloFacade;
import facade.ParametroTallajeAlistaFacade;
import facade.ParametroTallajeDimensionFacade;
import facade.ParametroTallajeDoblezFacade;
import facade.ParametroTallajeFacade;
import facade.ParametroTallajeTallaFacade;
import facade.ProcesoAcabadoFacade;
import facade.ProcesoAcabadoFaseFacade;
import facade.ProcesoConfeccionFacade;
import facade.ProcesoConfeccionFaseFacade;
import facade.TallaEstiloFacade;
import facade.TejidoCircularesFacade;
import facade.UsuarioFacade;
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

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class CopiarFichaBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private EstiloFacade estiloFacade;
    @EJB
    private ColorEstiloFacade colorEstiloFacade;
    @EJB
    private TallaEstiloFacade tallaEstiloFacade;
    @EJB
    private ParametroPrendaEstiloFacade parametroPrendaEstiloFacade;
    @EJB
    private MaterialEstiloFacade materialEstiloFacade;
    @EJB
    private TejidoCircularesFacade tejidoCircularesFacade;
    @EJB
    private HiloTejidoCircularesFacade hiloTejidoCircularesFacade;
    @EJB
    private HiloTensionFacade hiloTensionFacade;
    @EJB
    private HiloTubularFacade hiloTubularFacade;
    @EJB
    private ParametroTallajeFacade parametroTallajeFacade;
    @EJB
    private ParametroTallajeTallaFacade parametroTallajeTallaFacade;
    @EJB
    private ParametroTallajeDimensionFacade parametroTallajeDimensionFacade;
    @EJB
    private ParametroTallajeAlistaFacade parametroTallajeAlistaFacade;
    @EJB
    private ParametroTallajeDoblezFacade parametroTallajeDoblezFacade;
    @EJB
    private ProcesoConfeccionFacade procesoConfeccionFacade;
    @EJB
    private ProcesoConfeccionFaseFacade procesoConfeccionFaseFacade;
    @EJB
    private ProcesoAcabadoFacade procesoAcabadoFacade;
    @EJB
    private ProcesoAcabadoFaseFacade procesoAcabadoFaseFacade;
    @EJB
    private InsumoTerceroFacade insumoTerceroFacade;
    @EJB
    private ControlMedidasFacade controlMedidasFacade;
    @EJB
    private ControlMedidasTallaFacade controlMedidasTallaFacade;

    private Usuario usuario;
    private List<Estilo> listEstilo;
    private List<ColorEstilo> colorEstiloList;
    private List<TallaEstilo> tallaEstiloList;
    private List<ParametroPrendaEstilo> parametroPrendaEstiloList;
    private List<MaterialEstilo> materialEstiloList;
    private List<TejidoCirculares> tejidoCircularesList;
    private List<HiloTejidoCirculares> hiloTejidoCircularesList;
    private List<HiloTension> hiloTensionList;
    private List<HiloTubular> hiloTubularList;
    private List<ParametroTallaje> parametroTallajeList;
    private List<ParametroTallajeTalla> parametroTallajeTallaList;
    private List<ParametroTallajeDimension> parametroTallajeDimensionList;
    private List<ParametroTallajeAlista> parametroTallajeAlistaList;
    private List<ParametroTallajeDoblez> parametroTallajeDoblezList;
    private List<ProcesoConfeccion> procesoConfeccionList;
    private List<ProcesoConfeccionFase> procesoConfeccionFaseList;
    private List<ProcesoAcabado> procesoAcabadoList;
    private List<ProcesoAcabadoFase> procesoAcabadoFaseList;
    private List<InsumoTercero> insumoTerceroList;
    private List<ControlMedidas> controlMedidasList;
    private List<ControlMedidasTalla> controlMedidasTallaList;
    private Estilo estilo;
    private Estilo estiloNuevo;
    private boolean dsbBtn;
    private Date fechaActual;
    private Map<String, Object> parametros;

    @PostConstruct
    public void init() {
        listEstilo = estiloFacade.findActivos();
        estilo = new Estilo();
        traerUsuario();
        dsbBtn = true;
    }

    public void copiarFicha() {
        fechaActual = Calendar.getInstance().getTime();
        parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        copiarDatosGenerales();
        copiarListaMateriales();
        copiarTejidoCirculares();
        copiarParametroTallaje();
        copiarProcesosConfeccion();
        copiarProcesoAcabado();
        copiarInsumoTerceros();
        copiarControlMedidas();
        copiarPlanchaTintoreria();

        lanzarMensajeInformacion("Ficha " + estilo.getEstilo() + " copiada");
        listEstilo = estiloFacade.findActivos();
        PrimeFaces.current().executeScript("PF('dlgCrearFicha').hide();");
    }
    
    public void copiarPlanchaTintoreria() {
        
    }

    public void copiarControlMedidas() {
        String namedQuery = "ControlMedidas.findByIdEstilo";
        controlMedidasList = controlMedidasFacade.findByNamedQuery(namedQuery, parametros);
        if (!controlMedidasList.isEmpty()) {
            ControlMedidas controlMedidasNuevo = new ControlMedidas();
            ControlMedidas controlMedidasAnterior = controlMedidasList.get(0);
            controlMedidasNuevo.setActualizado(null);
            controlMedidasNuevo.setCreado(fechaActual);
            controlMedidasNuevo.setIdActualizado(null);
            controlMedidasNuevo.setIdControlMedidas(null);
            controlMedidasNuevo.setIdEstilo(estiloNuevo);
            controlMedidasNuevo.setIdCreado(usuario);
            controlMedidasNuevo.setImagen(controlMedidasAnterior.getImagen());
            controlMedidasNuevo.setObservaciones(controlMedidasAnterior.getObservaciones());
            controlMedidasFacade.create(controlMedidasNuevo);
            namedQuery = "TallaEstilo.findByIdEstilo";
            Map<String, Object> parametros3 = new HashMap<>();
            parametros3.put("idEstilo", estilo);
            tallaEstiloList = tallaEstiloFacade.findByNamedQuery(namedQuery, parametros3);
            if (!tallaEstiloList.isEmpty()) {
                for (TallaEstilo te : tallaEstiloList) {
                    namedQuery = "ControlMedidasTalla.findByTalla";
                    Map<String, Object> parametros2 = new HashMap<>();
                    parametros2.put("idTallaEstilo", te);
                    //tenemos un problemita y es euqe controlMedidasTalla guarda la tallaEstilo
                    controlMedidasTallaList = controlMedidasTallaFacade.findByNamedQuery(namedQuery, parametros2);
                    if (!controlMedidasTallaList.isEmpty()) {
                        for (ControlMedidasTalla cmt : controlMedidasTallaList) {
                            cmt.setIdControlMedidas(controlMedidasNuevo);
                            cmt.setIdControlMedidasTalla(null);
                            String namedQuery2 = "TallaEstilo.findByIdEstiloAndTalla";
                            Map<String, Object> parametros4 = new HashMap<>();
                            parametros4.put("idEstilo", estiloNuevo);
                            parametros4.put("idTalla", te.getIdTalla());
                            List<TallaEstilo> tallaEstiloListTemp = tallaEstiloFacade.findByNamedQuery(namedQuery2, parametros4);
                            cmt.setIdTallaEstilo(tallaEstiloListTemp.get(0));
                            controlMedidasTallaFacade.create(cmt);
                        }
                    }
                }
            }
        }
        System.out.println("control medidas copiado");
    }

    public void copiarInsumoTerceros() {
        String namedQuery = "InsumoTercero.findByIdEstilo";
        insumoTerceroList = insumoTerceroFacade.findByNamedQuery(namedQuery, parametros);
        if (!insumoTerceroList.isEmpty()) {
            for (InsumoTercero insumoTerceroNuevo : insumoTerceroList) {
                insumoTerceroNuevo.setIdCreado(usuario);
                insumoTerceroNuevo.setActualizado(null);
                insumoTerceroNuevo.setIdActualizado(null);
                insumoTerceroNuevo.setIdEstilo(estiloNuevo);
                insumoTerceroNuevo.setIdInsumoTercero(null);
                insumoTerceroNuevo.setCreado(fechaActual);
                insumoTerceroFacade.create(insumoTerceroNuevo);
            }
        }
        System.out.println("insumo tercero copiado");
    }

    public void copiarProcesoAcabado() {
        String namedQuery = "ProcesoAcabado.findByIdEstilo";
        procesoAcabadoList = procesoAcabadoFacade.findByNamedQuery(namedQuery, parametros);
        if (!procesoAcabadoList.isEmpty()) {
            ProcesoAcabado procesoAcabadoNuevo = new ProcesoAcabado();
            ProcesoAcabado procesoAcabadoAnterior = procesoAcabadoList.get(0);
            procesoAcabadoNuevo.setActualizado(null);
            procesoAcabadoNuevo.setCreado(fechaActual);
            procesoAcabadoNuevo.setIdActualizado(null);
            procesoAcabadoNuevo.setIdEstilo(estiloNuevo);
            procesoAcabadoNuevo.setIdCreado(usuario);
            procesoAcabadoNuevo.setIdProcesoAcabado(null);
            procesoAcabadoNuevo.setObservaciones(procesoAcabadoAnterior.getObservaciones());
            procesoAcabadoNuevo.setImagen(procesoAcabadoAnterior.getImagen());
            procesoAcabadoFacade.create(procesoAcabadoNuevo);
            namedQuery = "ProcesoAcabadoFase.findByIdProcesoAcabado";
            Map<String, Object> parametros2 = new HashMap<>();
            parametros2.put("idProcesoAcabado", procesoAcabadoAnterior);
            procesoAcabadoFaseList = procesoAcabadoFaseFacade.findByNamedQuery(namedQuery, parametros2);
            if (!procesoAcabadoFaseList.isEmpty()) {
                for (ProcesoAcabadoFase paf : procesoAcabadoFaseList) {
                    if (paf.getIdMaterial() != null) {
                        String namedQuery2 = "MaterialEstilo.findByIdEstiloAndIdMaterial";
                        Map<String, Object> parametros4 = new HashMap<>();
                        parametros4.put("idEstilo", estiloNuevo);
                        parametros4.put("idMaterial", paf.getIdMaterial().getIdMaterial());
                        List<MaterialEstilo> materialEstiloListTemp = materialEstiloFacade.findByNamedQuery(namedQuery2, parametros4);
                        if (!materialEstiloListTemp.isEmpty()) {
                            paf.setIdMaterial(materialEstiloListTemp.get(0));
                        }
                    }
                    paf.setIdProcesoAcabadoFase(null);
                    paf.setIdProcesoAcabado(procesoAcabadoNuevo);
                    procesoAcabadoFaseFacade.create(paf);
                }
            }
        }
        System.out.println("proceso acabado copiado");
    }

    public void copiarProcesosConfeccion() {
        String namedQuery = "ProcesoConfeccion.findByIdEstilo";
        procesoConfeccionList = procesoConfeccionFacade.findByNamedQuery(namedQuery, parametros);
        if (!procesoConfeccionList.isEmpty()) {
            ProcesoConfeccion procesoConfeccionNuevo = new ProcesoConfeccion();
            ProcesoConfeccion procesoConfeccionAnterior = procesoConfeccionList.get(0);
            procesoConfeccionNuevo.setIdCreado(usuario);
            procesoConfeccionNuevo.setCreado(fechaActual);
            procesoConfeccionNuevo.setIdActualizado(null);
            procesoConfeccionNuevo.setActualizado(null);
            procesoConfeccionNuevo.setIdProcesoConfeccion(null);
            procesoConfeccionNuevo.setIdEstilo(estiloNuevo);
            procesoConfeccionNuevo.setObservaciones(procesoConfeccionAnterior.getObservaciones());
            procesoConfeccionNuevo.setImagen(procesoConfeccionAnterior.getImagen());
            procesoConfeccionNuevo.setCantidadBulto(procesoConfeccionAnterior.getCantidadBulto());
            procesoConfeccionNuevo.setCantidadTalego(procesoConfeccionAnterior.getCantidadTalego());
            procesoConfeccionFacade.create(procesoConfeccionNuevo);
            namedQuery = "ProcesoConfeccionFase.findByIdProcesoConfeccion";
            Map<String, Object> parametros2 = new HashMap<>();
            parametros2.put("idProcesoConfeccion", procesoConfeccionAnterior);
            procesoConfeccionFaseList = procesoConfeccionFaseFacade.findByNamedQuery(namedQuery, parametros2);
            if (!procesoConfeccionFaseList.isEmpty()) {
                for (ProcesoConfeccionFase pcf : procesoConfeccionFaseList) {
                    if (pcf.getIdMaterial() != null) {
                        String namedQuery2 = "MaterialEstilo.findByIdEstiloAndIdMaterial";
                        Map<String, Object> parametros4 = new HashMap<>();
                        parametros4.put("idEstilo", estiloNuevo);
                        parametros4.put("idMaterial", pcf.getIdMaterial().getIdMaterial());
                        List<MaterialEstilo> materialEstiloListTemp = materialEstiloFacade.findByNamedQuery(namedQuery2, parametros4);
                        pcf.setIdMaterial(materialEstiloListTemp.get(0));
                    }
                    pcf.setIdProcesoConfeccionFase(null);
                    pcf.setIdProcesoConfeccion(procesoConfeccionNuevo);
                    procesoConfeccionFaseFacade.create(pcf);
                }
            }
        }
        System.out.println("proceso confeccion copiado");
    }

    public void copiarParametroTallaje() {
        String namedQuery = "ParametroTallaje.findByIdEstilo";
        parametroTallajeList = parametroTallajeFacade.findByNamedQuery(namedQuery, parametros);
        if (!parametroTallajeList.isEmpty()) {
            ParametroTallaje parametroTallajeNuevo = new ParametroTallaje();
            ParametroTallaje parametroTallajeAnterior = parametroTallajeList.get(0);
            //parametroTallajeNuevo = parametroTallajeList.get(0);
            parametroTallajeNuevo.setIdParametrosTallaje(null);
            parametroTallajeNuevo.setCreado(fechaActual);
            parametroTallajeNuevo.setIdCreado(usuario);
            parametroTallajeNuevo.setActualizado(null);
            parametroTallajeNuevo.setIdActualizado(null);
            parametroTallajeNuevo.setIdEstilo(estiloNuevo);
            parametroTallajeFacade.create(parametroTallajeNuevo);
            namedQuery = "ParametroTallajeTalla.findByIdParametroTallaje";
            Map<String, Object> parametros2 = new HashMap<>();
            parametros2.put("idParametroTallaje", parametroTallajeAnterior);
            parametroTallajeTallaList = parametroTallajeTallaFacade.findByNamedQuery(namedQuery, parametros2);
            for (ParametroTallajeTalla ptt : parametroTallajeTallaList) {
                namedQuery = "ParametroTallajeDimension.findByIdParametroTallajeTalla";
                Map<String, Object> parametros3 = new HashMap<>();
                parametros3.put("idParametroTallajeTalla", ptt);
                parametroTallajeDimensionList = parametroTallajeDimensionFacade.findByNamedQuery(namedQuery, parametros3);
                namedQuery = "ParametroTallajeAlista.findByIdParametroTallajeTalla";
                parametroTallajeAlistaList = parametroTallajeAlistaFacade.findByNamedQuery(namedQuery, parametros3);
                namedQuery = "ParametroTallajeDoblez.findByIdParametroTallajeTalla";
                parametroTallajeDoblezList = parametroTallajeDoblezFacade.findByNamedQuery(namedQuery, parametros3);
                ptt.setIdParametroTallajeTalla(null);
                ptt.setIdParametroTallaje(parametroTallajeNuevo);
                String namedQuery2 = "TallaEstilo.findByIdEstiloAndTalla";
                Map<String, Object> parametros4 = new HashMap<>();
                parametros4.put("idEstilo", estiloNuevo);
                parametros4.put("idTalla", ptt.getIdTallaEstilo().getIdTalla());
                List<TallaEstilo> tallaEstiloListTemp = tallaEstiloFacade.findByNamedQuery(namedQuery2, parametros4);
                ptt.setIdTallaEstilo(tallaEstiloListTemp.get(0));
                parametroTallajeTallaFacade.create(ptt);
                if (!parametroTallajeDimensionList.isEmpty()) {
                    for (ParametroTallajeDimension ptd : parametroTallajeDimensionList) {
                        ptd.setIdParametroTallajeDimension(null);
                        ptd.setIdParametroTallajeTalla(ptt);
                        parametroTallajeDimensionFacade.create(ptd);
                    }
                }
                if (!parametroTallajeAlistaList.isEmpty()) {
                    for (ParametroTallajeAlista pta : parametroTallajeAlistaList) {
                        pta.setIdParametroTallajeAlista(null);
                        pta.setIdParametroTallajeTalla(ptt);
                        parametroTallajeAlistaFacade.create(pta);
                    }
                }
                if (!parametroTallajeDoblezList.isEmpty()) {
                    for (ParametroTallajeDoblez ptdz : parametroTallajeDoblezList) {
                        ptdz.setIdParametroTallajeDoblez(null);
                        ptdz.setIdParametroTallajeTalla(ptt);
                        parametroTallajeDoblezFacade.create(ptdz);
                    }
                }
            }
        }
        System.out.println("parámetros tallaje copiado");
    }

    public void copiarTejidoCirculares() {
        String namedQuery = "TejidoCirculares.findByIdEstilo";
        tejidoCircularesList = tejidoCircularesFacade.findByNamedQuery(namedQuery, parametros);
        if (!tejidoCircularesList.isEmpty()) {
            TejidoCirculares tejidoCircularesNuevo = new TejidoCirculares();
            TejidoCirculares tejidoCircularesAnterior = tejidoCircularesList.get(0);
            tejidoCircularesNuevo.setIdMaquina(tejidoCircularesAnterior.getIdMaquina());
            tejidoCircularesNuevo.setIdEmbalaje(tejidoCircularesAnterior.getIdEmbalaje());
            tejidoCircularesNuevo.setIdPrefijadoObs(tejidoCircularesAnterior.getIdPrefijadoObs());
            tejidoCircularesNuevo.setCantidad(tejidoCircularesAnterior.getCantidad());
            tejidoCircularesNuevo.setTemperatura(tejidoCircularesAnterior.getTemperatura());
            tejidoCircularesNuevo.setObservaciones(tejidoCircularesAnterior.getObservaciones());
            //tejidoCircularesNuevo = tejidoCircularesList.get(0);
            tejidoCircularesNuevo.setIdTejidoCirculares(null);
            tejidoCircularesNuevo.setCreado(fechaActual);
            tejidoCircularesNuevo.setIdCreado(usuario);
            tejidoCircularesNuevo.setIdEstilo(estiloNuevo);
            tejidoCircularesFacade.create(tejidoCircularesNuevo);
            namedQuery = "HiloTejidoCirculares.findTejidoCirculares";
            Map<String, Object> parametros2 = new HashMap<>();
            parametros2.put("idTejidoCirculares", tejidoCircularesList.get(0));
            hiloTejidoCircularesList = hiloTejidoCircularesFacade.findByNamedQuery(namedQuery, parametros2);
            for (HiloTejidoCirculares htc : hiloTejidoCircularesList) {
                if (htc.getIdAlimentador1() != null) {
                    String namedQuery2 = "MaterialEstilo.findByIdEstiloAndIdMaterial";
                    Map<String, Object> parametros4 = new HashMap<>();
                    parametros4.put("idEstilo", estiloNuevo);
                    parametros4.put("idMaterial", htc.getIdAlimentador1().getIdMaterial());
                    List<MaterialEstilo> materialEstiloListTemp = materialEstiloFacade.findByNamedQuery(namedQuery2, parametros4);
                    htc.setIdAlimentador1(materialEstiloListTemp.get(0));
                }
                if (htc.getIdAlimentador2() != null) {
                    String namedQuery2 = "MaterialEstilo.findByIdEstiloAndIdMaterial";
                    Map<String, Object> parametros4 = new HashMap<>();
                    parametros4.put("idEstilo", estiloNuevo);
                    parametros4.put("idMaterial", htc.getIdAlimentador2().getIdMaterial());
                    List<MaterialEstilo> materialEstiloListTemp = materialEstiloFacade.findByNamedQuery(namedQuery2, parametros4);
                    htc.setIdAlimentador2(materialEstiloListTemp.get(0));
                }
                if (htc.getIdAlimentador3() != null) {
                    String namedQuery2 = "MaterialEstilo.findByIdEstiloAndIdMaterial";
                    Map<String, Object> parametros4 = new HashMap<>();
                    parametros4.put("idEstilo", estiloNuevo);
                    parametros4.put("idMaterial", htc.getIdAlimentador3().getIdMaterial());
                    List<MaterialEstilo> materialEstiloListTemp = materialEstiloFacade.findByNamedQuery(namedQuery2, parametros4);
                    htc.setIdAlimentador3(materialEstiloListTemp.get(0));
                }
                if (htc.getIdAlimentador4() != null) {
                    String namedQuery2 = "MaterialEstilo.findByIdEstiloAndIdMaterial";
                    Map<String, Object> parametros4 = new HashMap<>();
                    parametros4.put("idEstilo", estiloNuevo);
                    parametros4.put("idMaterial", htc.getIdAlimentador4().getIdMaterial());
                    List<MaterialEstilo> materialEstiloListTemp = materialEstiloFacade.findByNamedQuery(namedQuery2, parametros4);
                    htc.setIdAlimentador4(materialEstiloListTemp.get(0));
                }
                htc.setIdTejidoCirculares(tejidoCircularesNuevo);
                htc.setIdHiloTejidoCirculares(null);
                hiloTejidoCircularesFacade.create(htc);
            }
            namedQuery = "HiloTension.findByIdTejidoCirculares";
            hiloTensionList = hiloTensionFacade.findByNamedQuery(namedQuery, parametros2);
            for (HiloTension ht : hiloTensionList) {
                ht.setIdTejidoCirculares(tejidoCircularesNuevo);
                ht.setIdHiloTension(null);
                hiloTensionFacade.create(ht);
            }
            namedQuery = "HiloTubular.findByIdTejidoCirculares";
            hiloTubularList = hiloTubularFacade.findByNamedQuery(namedQuery, parametros2);
            for (HiloTubular htu : hiloTubularList) {
                htu.setIdTejidoCirculares(tejidoCircularesNuevo);
                htu.setIdHiloTubular(null);
                hiloTubularFacade.create(htu);
            }
        }
        System.out.println("tejido circulares copiado");
    }

    public void copiarListaMateriales() {
        String namedQuery = "MaterialEstilo.findByIdEstilo";
        materialEstiloList = materialEstiloFacade.findByNamedQuery(namedQuery, parametros);
        for (MaterialEstilo me : materialEstiloList) {
            me.setCreado(fechaActual);
            me.setIdMaterialEstilo(null);
            me.setIdEstilo(estiloNuevo);
            materialEstiloFacade.create(me);
        }
        System.out.println("lista materiales copiado");
    }

    public void copiarDatosGenerales() {
        estiloNuevo.setDescripcion(estilo.getDescripcion());
        estiloNuevo.setIdSegmento(estilo.getIdSegmento());
        estiloNuevo.setIdLineaProducto(estilo.getIdLineaProducto());
        estiloNuevo.setIdCategoria(estilo.getIdCategoria());
        estiloNuevo.setIdComposicion(estilo.getIdComposicion());
        estiloNuevo.setMaterialSecundario(estilo.getMaterialSecundario());
        estiloNuevo.setComposicionSecundaria(estilo.getComposicionSecundaria());
        estiloNuevo.setIdMarca(estilo.getIdMarca());
        estiloNuevo.setReferenciaCliente(estilo.getReferenciaCliente());
        estiloNuevo.setIdMercadoObjetivo(estilo.getIdMercadoObjetivo());
        estiloNuevo.setColeccion(estilo.getColeccion());
        estiloNuevo.setIdTallaEtiqueta(estilo.getIdTallaEtiqueta());
        estiloNuevo.setObservaciones(estilo.getObservaciones());
        estiloNuevo.setIdPartidaArancelaria(estilo.getIdPartidaArancelaria());
        estiloNuevo.setPeso(estilo.getPeso());
        estiloNuevo.setIdGuiaTallas(estilo.getIdGuiaTallas());
        estiloNuevo.setClima(estilo.getClima());
        estiloNuevo.setPostura(estilo.getPostura());
        estiloNuevo.setLavado(estilo.getLavado());
        estiloNuevo.setImagen(estilo.getImagen());
        estiloNuevo.setCreado(fechaActual);
        estiloNuevo.setIdCreado(usuario);
        estiloNuevo.setActualizado(fechaActual);
        estiloNuevo.setIdActualizado(usuario);
        estiloNuevo.setEstado("En proceso");
        estiloNuevo.setIdEstilo(null);
        estiloNuevo.setActiva(true);
        //estiloNuevo.setImagen(null); //PREGUNTAR ESTA PARTE
        estiloFacade.create(estiloNuevo);

        //copiar colores
        String namedQuery = "ColorEstilo.findByIdEstilo";
        colorEstiloList = colorEstiloFacade.findByNamedQuery(namedQuery, parametros);
        for (ColorEstilo ce : colorEstiloList) {
            ce.setIdColorEstilo(null);
            ce.setIdEstilo(estiloNuevo);
            colorEstiloFacade.create(ce);
        }
        //copiar tallas
        namedQuery = "TallaEstilo.findByIdEstilo";
        tallaEstiloList = tallaEstiloFacade.findByNamedQuery(namedQuery, parametros);
        for (TallaEstilo te : tallaEstiloList) {
            te.setIdTallaEstilo(null);
            te.setIdEstilo(estiloNuevo);
            tallaEstiloFacade.create(te);
        }
        //copiar partes del producto
        namedQuery = "ParametroPrendaEstilo.findByIdEstilo";
        parametroPrendaEstiloList = parametroPrendaEstiloFacade.findByNamedQuery(namedQuery, parametros);
        for (ParametroPrendaEstilo ppe : parametroPrendaEstiloList) {
            ppe.setIdParametroPrendaEstilo(null);
            ppe.setIdEstilo(estiloNuevo);
            parametroPrendaEstiloFacade.create(ppe);
        }
        System.out.println("datos generales copiado");
    }

    public void abrirDlgCrearFicha() {
        estiloNuevo = new Estilo();
        estiloNuevo.setReferencias(estilo.getReferencias());
        estiloNuevo.setEstilo(estilo.getEstilo());
        PrimeFaces.current().executeScript("PF('dlgCrearFicha').show();");
    }

    public void traerUsuario() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombreUsuario").toString();
        String namedQuery = "Usuario.findByUsuario";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("usuario", nombreUsuario);
        usuario = usuarioFacade.findByNamedQuery(namedQuery, parametros2).get(0);
    }

    public void lanzarMensajeInformacion(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_INFO, "Informacion", mensaje));
    }

    public void lanzarMensajeAdvertencia(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_WARN, "Advertencia", mensaje));
    }

    public void lanzarMensajeError(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Error", mensaje));
    }

    public Estilo getEstiloNuevo() {
        return estiloNuevo;
    }

    public boolean isDsbBtn() {
        return dsbBtn;
    }

    public void setDsbBtn(boolean dsbBtn) {
        this.dsbBtn = dsbBtn;
    }

    public void setEstiloNuevo(Estilo estiloNuevo) {
        this.estiloNuevo = estiloNuevo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Estilo> getListEstilo() {
        return listEstilo;
    }

    public void setListEstilo(List<Estilo> listEstilo) {
        this.listEstilo = listEstilo;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

}
