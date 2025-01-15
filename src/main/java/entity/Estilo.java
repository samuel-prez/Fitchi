/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Samuel P.
 */
@Entity
@Table(name = "estilo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estilo.findAll", query = "SELECT e FROM Estilo e"),
    @NamedQuery(name = "Estilo.findByIdEstilo", query = "SELECT e FROM Estilo e WHERE e.idEstilo = :idEstilo"),
    @NamedQuery(name = "Estilo.findByEstilo", query = "SELECT e FROM Estilo e WHERE e.estilo = :estilo"),
    @NamedQuery(name = "Estilo.findByReferencias", query = "SELECT e FROM Estilo e WHERE e.referencias = :referencias"),
    @NamedQuery(name = "Estilo.findByDescripcion", query = "SELECT e FROM Estilo e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "Estilo.findByImagen", query = "SELECT e FROM Estilo e WHERE e.imagen = :imagen"),
    @NamedQuery(name = "Estilo.findByObservaciones", query = "SELECT e FROM Estilo e WHERE e.observaciones = :observaciones"),
    @NamedQuery(name = "Estilo.findByReferenciaCliente", query = "SELECT e FROM Estilo e WHERE e.referenciaCliente = :referenciaCliente"),
    @NamedQuery(name = "Estilo.findByPorcentajeCalidad", query = "SELECT e FROM Estilo e WHERE e.porcentajeCalidad = :porcentajeCalidad"),
    @NamedQuery(name = "Estilo.findByColeccion", query = "SELECT e FROM Estilo e WHERE e.coleccion = :coleccion"),
    @NamedQuery(name = "Estilo.findByCreado", query = "SELECT e FROM Estilo e WHERE e.creado = :creado"),
    @NamedQuery(name = "Estilo.findByActualizado", query = "SELECT e FROM Estilo e WHERE e.actualizado = :actualizado"),
    @NamedQuery(name = "Estilo.findByIdMercadoObjetivo", query = "SELECT e FROM Estilo e WHERE e.idMercadoObjetivo = :idMercadoObjetivo"),
    @NamedQuery(name = "Estilo.findByActiva", query = "SELECT e FROM Estilo e WHERE e.activa = :activa")})
public class Estilo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ESTILO")
    private Integer idEstilo;
    @Size(max = 30)
    @Column(name = "ESTILO")
    private String estilo;
    @Size(max = 20)
    @Column(name = "PESO")
    private String peso;
    @Size(max = 30)
    @Column(name = "REFERENCIAS")
    private String referencias;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "IMAGEN")
    private String imagen;
    @Size(max = 30)
    @Column(name = "ESTADO")
    private String estado;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Size(max = 100)
    @Column(name = "REFERENCIA_CLIENTE")
    private String referenciaCliente;
    @Size(max = 1000)
    @Column(name = "POSTURA")
    private String postura;
    @Size(max = 1000)
    @Column(name = "LAVADO")
    private String lavado;
    @Size(max = 200)
    @Column(name = "CLIMA")
    private String clima;
    @Column(name = "PORCENTAJE_CALIDAD")
    private Integer porcentajeCalidad;
    @Size(max = 100)
    @Column(name = "COLECCION")
    private String coleccion;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "ACTUALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;
    @JoinColumn(name = "ID_MERCADO_OBJETIVO", referencedColumnName = "ID_MERCADO_OBJETIVO")
    private MercadoObjetivo idMercadoObjetivo;
    @Column(name = "ACTIVA")
    private Boolean activa;
    @JoinColumn(name = "ID_CATEGORIA", referencedColumnName = "ID_CATEGORIA")
    @ManyToOne
    private Categoria idCategoria;
    @JoinColumn(name = "ID_LINEA_PRODUCTO", referencedColumnName = "ID_LINEA_PRODUCTO")
    @ManyToOne
    private LineaProducto idLineaProducto;
    @JoinColumn(name = "ID_MARCA", referencedColumnName = "ID_MARCA")
    @ManyToOne
    private Marca idMarca;
    @JoinColumn(name = "ID_PARTIDA_ARANCELARIA", referencedColumnName = "ID_PARTIDA_ARANCELARIA")
    @ManyToOne
    private PartidaArancelaria idPartidaArancelaria;
    @JoinColumn(name = "ID_SEGMENTO", referencedColumnName = "ID_SEGMENTO")
    @ManyToOne
    private Segmento idSegmento;
    @JoinColumn(name = "ID_TALLA_ETIQUETA", referencedColumnName = "ID_TALLA_ETIQUETA")
    @ManyToOne
    private TallaEtiqueta idTallaEtiqueta;
    @JoinColumn(name = "ID_CREADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idCreado;
    @JoinColumn(name = "ID_ACTUALIZADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idActualizado;
    @JoinColumn(name = "ID_GUIA_TALLAS", referencedColumnName = "ID_GUIA_TALLAS")
    @ManyToOne
    private GuiaTallas idGuiaTallas;
    @JoinColumn(name = "ID_COMPOSICION", referencedColumnName = "ID_COMPOSICION")
    @ManyToOne
    private Composicion idComposicion;
    @OneToMany(mappedBy = "idEstilo")
    private List<ParametroPrendaEstilo> parametroPrendaEstiloList;
    @OneToMany(mappedBy = "idEstilo")
    private List<TallaEstilo> tallaEstiloList;
    @OneToMany(mappedBy = "idEstilo")
    private List<ColorEstilo> colorEstiloList;
    @Size(max = 200)
    @Column(name = "MATERIAL_SECUNDARIO")
    private String materialSecundario;
    @Size(max = 200)
    @Column(name = "COMPOSICION_SECUNDARIA")
    private String composicionSecundaria;

    public Estilo() {
    }

    public Estilo(Integer idEstilo) {
        this.idEstilo = idEstilo;
    }

    public String getMaterialSecundario() {
        return materialSecundario;
    }

    public void setMaterialSecundario(String materialSecundario) {
        this.materialSecundario = materialSecundario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdEstilo() {
        return idEstilo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getComposicionSecundaria() {
        return composicionSecundaria;
    }

    public String getPostura() {
        return postura;
    }

    public void setPostura(String postura) {
        this.postura = postura;
    }

    public String getLavado() {
        return lavado;
    }

    public void setLavado(String lavado) {
        this.lavado = lavado;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public GuiaTallas getIdGuiaTallas() {
        return idGuiaTallas;
    }

    public void setIdGuiaTallas(GuiaTallas idGuiaTallas) {
        this.idGuiaTallas = idGuiaTallas;
    }

    public void setComposicionSecundaria(String composicionSecundaria) {
        this.composicionSecundaria = composicionSecundaria;
    }

    public void setIdEstilo(Integer idEstilo) {
        this.idEstilo = idEstilo;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public String getReferencias() {
        return referencias;
    }

    public void setReferencias(String referencias) {
        this.referencias = referencias;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Composicion getIdComposicion() {
        return idComposicion;
    }

    public void setIdComposicion(Composicion idComposicion) {
        this.idComposicion = idComposicion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getReferenciaCliente() {
        return referenciaCliente;
    }

    public void setReferenciaCliente(String referenciaCliente) {
        this.referenciaCliente = referenciaCliente;
    }

    public Integer getPorcentajeCalidad() {
        return porcentajeCalidad;
    }

    public void setPorcentajeCalidad(Integer porcentajeCalidad) {
        this.porcentajeCalidad = porcentajeCalidad;
    }

    public String getColeccion() {
        return coleccion;
    }

    public void setColeccion(String coleccion) {
        this.coleccion = coleccion;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getActualizado() {
        return actualizado;
    }

    public void setActualizado(Date actualizado) {
        this.actualizado = actualizado;
    }

    public MercadoObjetivo getIdMercadoObjetivo() {
        return idMercadoObjetivo;
    }

    public void setIdMercadoObjetivo(MercadoObjetivo idMercadoObjetivo) {
        this.idMercadoObjetivo = idMercadoObjetivo;
    }

    public Boolean getActiva() {
        return activa;
    }

    public void setActiva(Boolean activa) {
        this.activa = activa;
    }


    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public LineaProducto getIdLineaProducto() {
        return idLineaProducto;
    }

    public void setIdLineaProducto(LineaProducto idLineaProducto) {
        this.idLineaProducto = idLineaProducto;
    }

    public Marca getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Marca idMarca) {
        this.idMarca = idMarca;
    }

    public PartidaArancelaria getIdPartidaArancelaria() {
        return idPartidaArancelaria;
    }

    public void setIdPartidaArancelaria(PartidaArancelaria idPartidaArancelaria) {
        this.idPartidaArancelaria = idPartidaArancelaria;
    }

    public Segmento getIdSegmento() {
        return idSegmento;
    }

    public void setIdSegmento(Segmento idSegmento) {
        this.idSegmento = idSegmento;
    }

    public TallaEtiqueta getIdTallaEtiqueta() {
        return idTallaEtiqueta;
    }

    public void setIdTallaEtiqueta(TallaEtiqueta idTallaEtiqueta) {
        this.idTallaEtiqueta = idTallaEtiqueta;
    }

    public Usuario getIdCreado() {
        return idCreado;
    }

    public void setIdCreado(Usuario idCreado) {
        this.idCreado = idCreado;
    }

    public Usuario getIdActualizado() {
        return idActualizado;
    }

    public void setIdActualizado(Usuario idActualizado) {
        this.idActualizado = idActualizado;
    }

    @XmlTransient
    public List<ParametroPrendaEstilo> getParametroPrendaEstiloList() {
        return parametroPrendaEstiloList;
    }

    public void setParametroPrendaEstiloList(List<ParametroPrendaEstilo> parametroPrendaEstiloList) {
        this.parametroPrendaEstiloList = parametroPrendaEstiloList;
    }

    @XmlTransient
    public List<TallaEstilo> getTallaEstiloList() {
        return tallaEstiloList;
    }

    public void setTallaEstiloList(List<TallaEstilo> tallaEstiloList) {
        this.tallaEstiloList = tallaEstiloList;
    }

    @XmlTransient
    public List<ColorEstilo> getColorEstiloList() {
        return colorEstiloList;
    }

    public void setColorEstiloList(List<ColorEstilo> colorEstiloList) {
        this.colorEstiloList = colorEstiloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstilo != null ? idEstilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estilo)) {
            return false;
        }
        Estilo other = (Estilo) object;
        if ((this.idEstilo == null && other.idEstilo != null) || (this.idEstilo != null && !this.idEstilo.equals(other.idEstilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Estilo[ idEstilo=" + idEstilo + " ]";
    }
    
}
