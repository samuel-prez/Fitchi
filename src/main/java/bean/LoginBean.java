/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import entity.Usuario;
import entity.Rol;
import facade.UsuarioFacade;
import facade.RolFacade;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private RolFacade rolFacade;

    private Usuario usuario;
    private List<Usuario> listUsuario;
    private String user;
    private String pass;

    @PostConstruct
    public void init() {
        listUsuario = usuarioFacade.findAll();
        user = "";
        pass = "";
    }

    public void loguear() throws IOException {
        long count;
        String namedQuery = "Usuario.countByIdUsuario";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario",user);
        parametros.put("contrasena", pass);
        count = usuarioFacade.findByNamedQuerySingleResultLong(namedQuery, parametros);
        if(count > 0){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("nombreUsuario", user);
            FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
        } else {
            lanzarMensajeError("Validar datos");
        }
        System.out.println(user + " ha ingresado " + Calendar.getInstance().getTime());
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

    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListUsuario() {
        return listUsuario;
    }

    public void setListUsuario(List<Usuario> listUsuario) {
        this.listUsuario = listUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

}
