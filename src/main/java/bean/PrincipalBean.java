/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import entity.Usuario;
import facade.EstiloFacade;
import entity.Estilo;
import facade.UsuarioFacade;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class PrincipalBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private EstiloFacade estiloFacade;

    private Usuario usuario;
    private List<Estilo> listEstilo;
    private Estilo estilo;
    private Estilo estiloNuevo;
    private boolean dsbConsulta;
    private final int idRolConsulta = 3;
    private final int idRolFull = 2;
    private final int idRolCalidad = 4;
    private String estiloRecibido;

    @PostConstruct
    public void init() {
        listEstilo = estiloFacade.findActivos();
        estilo = new Estilo();
        traerUsuario();
        dsbConsulta = false;
        //validarVolver();
    }
    
    public void validarVolver() {//si se usó el botón "volver" validará el estilo recibido
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        estiloRecibido = params.get("estiloRecibido");
        String namedQuery = "Estilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", Integer.valueOf(estiloRecibido));
        estilo = estiloFacade.findByNamedQuery(namedQuery, parametros).get(0);
    }

    public void dtEstiloSeleccion() {

        if (!estilo.getEstado().equals("Terminada")) {
            switch (usuario.getIdRol().getIdRol()) {
                case idRolConsulta:
                    dsbConsulta = true;
                    break;
                case idRolFull:
                    dsbConsulta = false;
                    break;
                case idRolCalidad:
                    dsbConsulta = true;
                    break;
                default:
                    dsbConsulta = false;
                    break;
            }
        } else {
            dsbConsulta = false;
        }
    }

    public String redirect(String opc) {
        if (estilo != null) {
            return opc + "?faces-redirect=true&amp;estiloRecibido=" + estilo.getIdEstilo();
        } else {
            lanzarMensajeAdvertencia("Debe escoger una ficha");
            return "";
        }
    }

    public void abrirDlgCrearFicha() {
        System.out.println();
        estiloNuevo = new Estilo();
        PrimeFaces.current().executeScript("PF('dlgCrearFicha').show();");
    }

    //copiar usuario de datosgenerales
    public void crearFicha() {

        estiloNuevo.setCreado(Calendar.getInstance().getTime());
        estiloNuevo.setActiva(true);
        estiloNuevo.setIdCreado(usuario);
        estiloNuevo.setEstado("En proceso");
        estiloFacade.create(estiloNuevo);
        lanzarMensajeInformacion("Ficha " + estiloNuevo.getEstilo() + " creada");
        listEstilo = estiloFacade.findActivos();
        PrimeFaces.current().executeScript("PF('dlgCrearFicha').hide();");

    }

    public void traerUsuario() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombreUsuario").toString();
        String namedQuery = "Usuario.findByUsuario";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", nombreUsuario);
        usuario = usuarioFacade.findByNamedQuery(namedQuery, parametros).get(0);
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

    public Estilo getEstiloNuevo() {
        return estiloNuevo;
    }

    public void setEstiloNuevo(Estilo estiloNuevo) {
        this.estiloNuevo = estiloNuevo;
    }

    public boolean isDsbConsulta() {
        return dsbConsulta;
    }

    public void setDsbConsulta(boolean dsbConsulta) {
        this.dsbConsulta = dsbConsulta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
