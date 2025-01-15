/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import entity.ColorEstilo;
import entity.Estilo;
import entity.Usuario;
import entity.PlanchaTintoreria;
import facade.ColorEstiloFacade;
import facade.EstiloFacade;
import facade.UsuarioFacade;
import facade.PlanchaTintoreriaFacade;
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

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class PlanchaTintoreriaBean implements Serializable {

    @EJB
    private EstiloFacade estiloFacade;
    @EJB
    private JasperUtilidadesBeanLocal jasperUtilidadesBean;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ColorEstiloFacade colorEstiloFacade;
    @EJB
    private PlanchaTintoreriaFacade planchaTintoreriaFacade;
    
    private Estilo estilo;
    private String estiloRecibido;
    private Usuario usuario;
    private PlanchaTintoreria planchaTintoreria;
    private List<ColorEstilo> colorEstiloList;
    private List<PlanchaTintoreria> planchaTintoreriaList;
    private List<String> tipoPlanchaList;
    private boolean dsbEdicion;
    private boolean dsbImpresion;
    private final int idRolConsulta = 3;
    private final int idRolFull = 2;
    private final int idRolCalidad = 4;
    private Date fechaActual;
    
    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        estiloRecibido = params.get("estiloRecibido");
        fechaActual = Calendar.getInstance().getTime();
        traerUsuario();
        buscarEstilo();
        buscarPlancha();
        buscarColores();
        llenarListas();
        validarPermisos();
    }
    
    private void llenarListas() {
        tipoPlanchaList = new ArrayList();
        tipoPlanchaList.add("ELÉCTRICA");
        tipoPlanchaList.add("TAKATORY");
    }
    
    public void guardarReporte() {
        planchaTintoreria.setIdActualizado(usuario);
        planchaTintoreria.setActualizado(Calendar.getInstance().getTime());
        planchaTintoreriaFacade.edit(planchaTintoreria);
        lanzarMensajeInformacion("Datos guardados");
    }
    
    public void buscarPlancha() {
        String namedQuery = "PlanchaTintoreria.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        planchaTintoreriaList = planchaTintoreriaFacade.findByNamedQuery(namedQuery, parametros);
        if (planchaTintoreriaList.isEmpty()) {
            planchaTintoreria = new PlanchaTintoreria();
            planchaTintoreria.setCreado(fechaActual);
            planchaTintoreria.setIdCreado(usuario);
            planchaTintoreria.setIdEstilo(estilo);
            planchaTintoreria.setTipoHorma("No aplica");
            planchaTintoreria.setTipoPlancha("Takatory");
            planchaTintoreria.setTipoSecado("No aplica");
            planchaTintoreria.setTiempoSecado("9 segundos");
            planchaTintoreria.setTiempoHormado("9 segundos");
            planchaTintoreria.setTemperaturaPlancha("100 grados");
            planchaTintoreria.setPresion("1.4");
            planchaTintoreria.setObservaciones("Se maneja con el criterio del proceso de acabados.");
            
            planchaTintoreriaFacade.create(planchaTintoreria);
        } else {
            planchaTintoreria = planchaTintoreriaList.get(0);
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
    
    public void buscarColores() {
        String namedQuery = "ColorEstilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        colorEstiloList = colorEstiloFacade.findByNamedQuery(namedQuery, parametros);
    }
    
    public void buscarEstilo() {
        String namedQuery = "Estilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", Integer.valueOf(estiloRecibido));
        estilo = estiloFacade.findByNamedQuery(namedQuery, parametros).get(0);
    }
    
    public void imprimirReporte() {
        String rutaWebPages = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String rutaCarpetaReportes = rutaWebPages + File.separator + "reports" + File.separator;
        String rutaReportePaqueteTecnico = rutaCarpetaReportes + File.separator + "plancha_tintoreria.jrxml";
        Map<String, Object> parametros1 = new HashMap<>();
        parametros1.put("ID_ESTILO", estilo.getIdEstilo());
        parametros1.put("SUBREPORT_DIR", rutaCarpetaReportes);
        String nombrePDFGenerado = "plancha_tintoreria_" + estilo.getEstilo() + ".pdf";
        jasperUtilidadesBean.descargarReporte(rutaReportePaqueteTecnico, parametros1, nombrePDFGenerado);
    }
    
    public void terminarFicha() {
        estilo.setEstado("Terminada");
        estiloFacade.edit(estilo);
        lanzarMensajeInformacion("Esta ficha ha terminado su proceso.");
    }
    
    public void fichaEnProceso() {
        estilo.setEstado("En proceso");
        estiloFacade.edit(estilo);
        lanzarMensajeInformacion("Ficha en proceso nuevamente.");
    }
    
    public void lanzarMensajeInformacion(String mensaje) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informacion", mensaje));
    }
    
    public List<ColorEstilo> getColorEstiloList() {
        return colorEstiloList;
    }
    
    public boolean isDsbEdicion() {
        return dsbEdicion;
    }
    
    public List<PlanchaTintoreria> getPlanchaTintoreriaList() {
        return planchaTintoreriaList;
    }
    
    public void setPlanchaTintoreriaList(List<PlanchaTintoreria> planchaTintoreriaList) {
        this.planchaTintoreriaList = planchaTintoreriaList;
    }
    
    public void setDsbEdicion(boolean dsbEdicion) {
        this.dsbEdicion = dsbEdicion;
    }
    
    public boolean isDsbImpresion() {
        return dsbImpresion;
    }
    
    public PlanchaTintoreria getPlanchaTintoreria() {
        return planchaTintoreria;
    }
    
    public void setPlanchaTintoreria(PlanchaTintoreria planchaTintoreria) {
        this.planchaTintoreria = planchaTintoreria;
    }
    
    public void setDsbImpresion(boolean dsbImpresion) {
        this.dsbImpresion = dsbImpresion;
    }
    
    public void setColorEstiloList(List<ColorEstilo> colorEstiloList) {
        this.colorEstiloList = colorEstiloList;
    }
    
    public Estilo getEstilo() {
        return estilo;
    }
    
    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }
    
    public String getEstiloRecibido() {
        return estiloRecibido;
    }
    
    public void setEstiloRecibido(String estiloRecibido) {
        this.estiloRecibido = estiloRecibido;
    }
    
    public List<String> getTipoPlanchaList() {
        return tipoPlanchaList;
    }
    
    public void setTipoPlanchaList(List<String> tipoPlanchaList) {
        this.tipoPlanchaList = tipoPlanchaList;
    }
    
}
