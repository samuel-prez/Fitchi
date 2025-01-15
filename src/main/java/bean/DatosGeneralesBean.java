/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bean;

import entity.Estilo;
import entity.Segmento;
import entity.Categoria;
import entity.Color;
import entity.ColorEstilo;
import entity.LineaProducto;
import entity.Marca;
import entity.MercadoObjetivo;
import entity.ParametroPrenda;
import entity.ParametroPrendaEstilo;
import entity.PartidaArancelaria;
import entity.Talla;
import entity.TallaEstilo;
import entity.TallaEtiqueta;
import entity.Usuario;
import entity.Composicion;
import entity.GuiaTallas;
import facade.CategoriaFacade;
import facade.ColorFacade;
import facade.ColorEstiloFacade;
import facade.LineaProductoFacade;
import facade.MarcaFacade;
import facade.MercadoObjetivoFacade;
import facade.ParametroPrendaFacade;
import facade.ParametroPrendaEstiloFacade;
import facade.PartidaArancelariaFacade;
import facade.TallaFacade;
import facade.TallaEstiloFacade;
import facade.TallaEtiquetaFacade;
import facade.EstiloFacade;
import facade.SegmentoFacade;
import facade.UsuarioFacade;
import facade.ComposicionFacade;
import facade.GuiaTallasFacade;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.ReorderEvent;
import org.primefaces.model.file.UploadedFile;
import utility.Archivos;
import jasper.utilidades.JasperUtilidadesBeanLocal;
import java.util.Calendar;

/**
 *
 * @author Samuel P.
 */
@Named
@ViewScoped
public class DatosGeneralesBean implements Serializable {

    @EJB
    private EstiloFacade estiloFacade;
    @EJB
    private SegmentoFacade segmentoFacade;
    @EJB
    private LineaProductoFacade lineaProductoFacade;
    @EJB
    private CategoriaFacade categoriaFacade;
    @EJB
    private ColorEstiloFacade colorEstiloFacade;
    @EJB
    private ColorFacade colorFacade;
    @EJB
    private MarcaFacade marcaFacade;
    @EJB
    private MercadoObjetivoFacade mercadoObejtivoFacade;
    @EJB
    private ParametroPrendaFacade parametroPrendaFacade;
    @EJB
    private ParametroPrendaEstiloFacade parametroPrendaEstiloFacade;
    @EJB
    private PartidaArancelariaFacade partidaArancelariaFacade;
    @EJB
    private TallaFacade tallaFacade;
    @EJB
    private TallaEstiloFacade tallaEstiloFacade;
    @EJB
    private TallaEtiquetaFacade tallaEtiquetaFacade;
    @EJB
    private JasperUtilidadesBeanLocal jasperUtilidadesBean;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private ComposicionFacade composicionFacade;
    @EJB
    private GuiaTallasFacade guiaTallasFacade;

    private Estilo estilo;
    private String estiloRecibido;
    private List<Segmento> segmentoList;
    private List<LineaProducto> lineaProductoList;
    private List<Categoria> categoriaList;
    private List<ColorEstilo> colorEstiloList;
    private List<Color> colorList;
    private List<Marca> marcaList;
    private List<MercadoObjetivo> mercadoObjetivoList;
    private List<ParametroPrenda> parametroPrendaList;
    private List<ParametroPrendaEstilo> parametroPrendaEstiloList;
    private List<PartidaArancelaria> partidaArancelariaList;
    private List<Talla> tallaList;
    private List<TallaEstilo> tallaEstiloList;
    private List<TallaEtiqueta> tallaEtiquetaList;
    private List<Composicion> composicionList;
    private List<GuiaTallas> guiaTallasList;
    private ColorEstilo colorEstilo;
    private TallaEstilo tallaEstilo;
    private ParametroPrendaEstilo parametroPrendaEstilo;
    private final String ruta = "C:\\Cargues\\Estilos";
    private Usuario usuario;
    private boolean dsbEdicion;
    private boolean dsbImpresion;
    private final int idRolConsulta = 3;
    private final int idRolFull = 2;
    private final int idRolCalidad = 4;
    private ColorEstilo colorSeleccionado;
    private TallaEstilo tallaSeleccionada;
    private ParametroPrendaEstilo parametroSeleccionado;

    @PostConstruct
    public void init() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        estiloRecibido = params.get("estiloRecibido");
        traerUsuario();
        buscarEstilo();
        llenarListas();
        validarPermisos();
    }

    public String redirect() {
        return "lista_materiales?faces-redirect=true&amp;estiloRecibido=" + estiloRecibido;
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

    //añadir clima, postura, lavado y guia tallas. crear ventana "Creación tablas primarias"
    public void traerUsuario() {
        String nombreUsuario = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombreUsuario").toString();
        String namedQuery = "Usuario.findByUsuario";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuario", nombreUsuario);
        usuario = usuarioFacade.findByNamedQuery(namedQuery, parametros).get(0);
    }

    public void llenarListas() {
        segmentoList = segmentoFacade.findAll();
        lineaProductoList = lineaProductoFacade.findAll();
        categoriaList = categoriaFacade.findAll();
        marcaList = marcaFacade.findAll();
        mercadoObjetivoList = mercadoObejtivoFacade.findAll();
        tallaEtiquetaList = tallaEtiquetaFacade.findAll();
        colorEstiloList = new ArrayList<>();
        String namedQuery = "ColorEstilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        colorEstiloList = colorEstiloFacade.findByNamedQuery(namedQuery, parametros);
        String namedQuery2 = "TallaEstilo.findByIdEstilo";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        tallaEstiloList = tallaEstiloFacade.findByNamedQuery(namedQuery2, parametros2);
        String namedQuery3 = "ParametroPrendaEstilo.findByIdEstilo";
        Map<String, Object> parametros3 = new HashMap<>();
        parametros3.put("idEstilo", estilo);
        parametroPrendaEstiloList = parametroPrendaEstiloFacade.findByNamedQuery(namedQuery3, parametros3);
        partidaArancelariaList = partidaArancelariaFacade.findAll();
        composicionList = composicionFacade.findAll();
        guiaTallasList = guiaTallasFacade.findAll();
        colorSeleccionado = new ColorEstilo();
    }

    public void borrarColor() {
        colorEstiloFacade.remove(colorSeleccionado);
        String namedQuery = "ColorEstilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", estilo);
        colorEstiloList = colorEstiloFacade.findByNamedQuery(namedQuery, parametros);
    }

    public void borrarTalla() {
        tallaEstiloFacade.remove(tallaSeleccionada);
        String namedQuery2 = "TallaEstilo.findByIdEstilo";
        Map<String, Object> parametros2 = new HashMap<>();
        parametros2.put("idEstilo", estilo);
        tallaEstiloList = tallaEstiloFacade.findByNamedQuery(namedQuery2, parametros2);
    }

    public void borrarParametro() {
        parametroPrendaEstiloFacade.remove(parametroSeleccionado);
        String namedQuery3 = "ParametroPrendaEstilo.findByIdEstilo";
        Map<String, Object> parametros3 = new HashMap<>();
        parametros3.put("idEstilo", estilo);
        parametroPrendaEstiloList = parametroPrendaEstiloFacade.findByNamedQuery(namedQuery3, parametros3);
    }

    public void buscarEstilo() {
        String namedQuery = "Estilo.findByIdEstilo";
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("idEstilo", Integer.valueOf(estiloRecibido));
        estilo = estiloFacade.findByNamedQuery(namedQuery, parametros).get(0);

    }

    public void abrirDlgColor() {
        //color = new Color(); borrar si no sale error
        colorList = colorFacade.findAll();
        colorEstilo = new ColorEstilo();
        PrimeFaces.current().executeScript("PF('dlgColor').show();");
    }

    public void abrirDlgTalla() {
        tallaList = tallaFacade.findAll();
        tallaEstilo = new TallaEstilo();
        PrimeFaces.current().executeScript("PF('dlgTalla').show();");
    }
    
    public void abrirDlgTallaEditar() {
        tallaList = tallaFacade.findAll();
        PrimeFaces.current().executeScript("PF('dlgTallaEditar').show();");
    }

    public void abrirDlgParametro() {
        parametroPrendaList = parametroPrendaFacade.findAll();
        parametroPrendaEstilo = new ParametroPrendaEstilo();
        PrimeFaces.current().executeScript("PF('dlgParametro').show();");
    }
    
    public void abrirDlgEditarParametro() {
        parametroPrendaList = parametroPrendaFacade.findAll();
        PrimeFaces.current().executeScript("PF('dlgEditarParametro').show();");
    }

    public void guardarColor() {
        colorEstilo.setIdEstilo(estilo);
        colorEstiloFacade.create(colorEstilo);
        colorEstiloList.add(colorEstilo);
        PrimeFaces.current().executeScript("PF('dlgColor').hide();");
    }

    public void guardarTalla() {
        tallaEstilo.setIdEstilo(estilo);
        tallaEstiloFacade.create(tallaEstilo);
        tallaEstiloList.add(tallaEstilo);
        PrimeFaces.current().executeScript("PF('dlgTalla').hide();");
    }
    
    public void editarTalla() {
        tallaEstiloFacade.edit(tallaEstilo);
        PrimeFaces.current().executeScript("PF('dlgTallaEditar').hide();");
        lanzarMensajeInformacion("Talla editada");
    }

    public void guardarParametro() {
        parametroPrendaEstilo.setIdEstilo(estilo);
        parametroPrendaEstilo.setOrden(parametroPrendaEstiloList.size());
        parametroPrendaEstiloFacade.create(parametroPrendaEstilo);
        parametroPrendaEstiloList.add(parametroPrendaEstilo);
        PrimeFaces.current().executeScript("PF('dlgParametro').hide();");
        lanzarMensajeInformacion("Parámetro editado");
    }

    public void editarParametro() {
        parametroPrendaEstiloFacade.edit(parametroPrendaEstilo);
        PrimeFaces.current().executeScript("PF('dlgParametroEditar').hide();");
        lanzarMensajeInformacion("Parámetro editado");
    }

    public void onRowReorder(ReorderEvent event) {
        int cont = 0;
        for (ParametroPrendaEstilo pr : parametroPrendaEstiloList) {
            pr.setOrden(cont);
            cont++;
            parametroPrendaEstiloFacade.edit(pr);
        }
    }

    public void handleFileUpload(FileUploadEvent event) throws IOException {
        UploadedFile archivoASubir = event.getFile();
        String nombreArchivo = "Estilo " + estilo.getEstilo();
        String extension = Archivos.getExtension(archivoASubir.getFileName());
        byte[] contenidoArchivo = archivoASubir.getContent();
        File archivoSubido = Archivos.subirArchivo(ruta, extension,
                contenidoArchivo, nombreArchivo, true);
        estilo.setImagen(archivoSubido.getName());
        estiloFacade.edit(estilo);
    }

    public void imprimirReporte() {
        String rutaWebPages = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        String rutaCarpetaReportes = rutaWebPages + File.separator + "reports" + File.separator;
        String rutaReportePaqueteTecnico = rutaCarpetaReportes + File.separator + "datos_generales.jrxml";
        Map<String, Object> parametros1 = new HashMap<>();
        parametros1.put("ID_ESTILO", estilo.getIdEstilo());
        parametros1.put("SUBREPORT_DIR", rutaCarpetaReportes);
        String nombrePDFGenerado = "datos_generales_" + estilo.getEstilo() + ".pdf";
        jasperUtilidadesBean.descargarReporte(rutaReportePaqueteTecnico, parametros1, nombrePDFGenerado);
    }

    public void guardarReporte() {
        String user = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("nombreUsuario").toString();
        estilo.setIdActualizado(usuario);
        estilo.setActualizado(Calendar.getInstance().getTime());
        estiloFacade.edit(estilo);
        lanzarMensajeInformacion("Estilo guardado");
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

    public TallaEstilo getTallaSeleccionada() {
        return tallaSeleccionada;
    }

    public void setTallaSeleccionada(TallaEstilo tallaSeleccionada) {
        this.tallaSeleccionada = tallaSeleccionada;
    }

    public Estilo getEstilo() {
        return estilo;
    }

    public ColorEstilo getColorEstilo() {
        return colorEstilo;
    }

    public void setColorEstilo(ColorEstilo colorEstilo) {
        this.colorEstilo = colorEstilo;
    }

    public List<Composicion> getComposicionList() {
        return composicionList;
    }

    public void setComposicionList(List<Composicion> composicionList) {
        this.composicionList = composicionList;
    }

    public ParametroPrendaEstilo getParametroSeleccionado() {
        return parametroSeleccionado;
    }

    public void setParametroSeleccionado(ParametroPrendaEstilo parametroSeleccionado) {
        this.parametroSeleccionado = parametroSeleccionado;
    }

    public String getRuta() {
        return ruta;
    }

    public List<GuiaTallas> getGuiaTallasList() {
        return guiaTallasList;
    }

    public void setGuiaTallasList(List<GuiaTallas> guiaTallasList) {
        this.guiaTallasList = guiaTallasList;
    }

    public void setEstilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public String getEstiloRecibido() {
        return estiloRecibido;
    }

    public TallaEstilo getTallaEstilo() {
        return tallaEstilo;
    }

    public void setTallaEstilo(TallaEstilo tallaEstilo) {
        this.tallaEstilo = tallaEstilo;
    }

    public void setEstiloRecibido(String estiloRecibido) {
        this.estiloRecibido = estiloRecibido;
    }

    public List<Segmento> getSegmentoList() {
        return segmentoList;
    }

    public ColorEstilo getColorSeleccionado() {
        return colorSeleccionado;
    }

    public void setColorSeleccionado(ColorEstilo colorSeleccionado) {
        this.colorSeleccionado = colorSeleccionado;
    }

    public void setSegmentoList(List<Segmento> segmentoList) {
        this.segmentoList = segmentoList;
    }

    public List<LineaProducto> getLineaProductoList() {
        return lineaProductoList;
    }

    public void setLineaProductoList(List<LineaProducto> lineaProductoList) {
        this.lineaProductoList = lineaProductoList;
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public List<ColorEstilo> getColorEstiloList() {
        return colorEstiloList;
    }

    public void setColorEstiloList(List<ColorEstilo> colorEstiloList) {
        this.colorEstiloList = colorEstiloList;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    public void setColorList(List<Color> colorList) {
        this.colorList = colorList;
    }

    public List<Marca> getMarcaList() {
        return marcaList;
    }

    public void setMarcaList(List<Marca> marcaList) {
        this.marcaList = marcaList;
    }

    public List<MercadoObjetivo> getMercadoObjetivoList() {
        return mercadoObjetivoList;
    }

    public void setMercadoObjetivoList(List<MercadoObjetivo> mercadoObjetivoList) {
        this.mercadoObjetivoList = mercadoObjetivoList;
    }

    public List<ParametroPrenda> getParametroPrendaList() {
        return parametroPrendaList;
    }

    public void setParametroPrendaList(List<ParametroPrenda> parametroPrendaList) {
        this.parametroPrendaList = parametroPrendaList;
    }

    public List<ParametroPrendaEstilo> getParametroPrendaEstiloList() {
        return parametroPrendaEstiloList;
    }

    public void setParametroPrendaEstiloList(List<ParametroPrendaEstilo> parametroPrendaEstiloList) {
        this.parametroPrendaEstiloList = parametroPrendaEstiloList;
    }

    public List<PartidaArancelaria> getPartidaArancelariaList() {
        return partidaArancelariaList;
    }

    public void setPartidaArancelariaList(List<PartidaArancelaria> partidaArancelariaList) {
        this.partidaArancelariaList = partidaArancelariaList;
    }

    public List<Talla> getTallaList() {
        return tallaList;
    }

    public void setTallaList(List<Talla> tallaList) {
        this.tallaList = tallaList;
    }

    public List<TallaEstilo> getTallaEstiloList() {
        return tallaEstiloList;
    }

    public void setTallaEstiloList(List<TallaEstilo> tallaEstiloList) {
        this.tallaEstiloList = tallaEstiloList;
    }

    public List<TallaEtiqueta> getTallaEtiquetaList() {
        return tallaEtiquetaList;
    }

    public ParametroPrendaEstilo getParametroPrendaEstilo() {
        return parametroPrendaEstilo;
    }

    public void setParametroPrendaEstilo(ParametroPrendaEstilo parametroPrendaEstilo) {
        this.parametroPrendaEstilo = parametroPrendaEstilo;
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

    public void setTallaEtiquetaList(List<TallaEtiqueta> tallaEtiquetaList) {
        this.tallaEtiquetaList = tallaEtiquetaList;
    }

}
