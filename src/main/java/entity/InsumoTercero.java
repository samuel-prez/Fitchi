/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samuel P.
 */
@Entity
@Table(name = "insumo_tercero")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InsumoTercero.findAll", query = "SELECT i FROM InsumoTercero i"),
    @NamedQuery(name = "InsumoTercero.findByIdInsumoTercero", query = "SELECT i FROM InsumoTercero i WHERE i.idInsumoTercero = :idInsumoTercero"),
    @NamedQuery(name = "InsumoTercero.findByTalla", query = "SELECT i FROM InsumoTercero i WHERE i.talla = :talla"),
    @NamedQuery(name = "InsumoTercero.findByRendimiento", query = "SELECT i FROM InsumoTercero i WHERE i.rendimiento = :rendimiento"),
    @NamedQuery(name = "InsumoTercero.findByAnchoUtil", query = "SELECT i FROM InsumoTercero i WHERE i.anchoUtil = :anchoUtil"),
    @NamedQuery(name = "InsumoTercero.findByConsumoPorPrenda", query = "SELECT i FROM InsumoTercero i WHERE i.consumoPorPrenda = :consumoPorPrenda"),
    @NamedQuery(name = "InsumoTercero.findByAprovechamiento", query = "SELECT i FROM InsumoTercero i WHERE i.aprovechamiento = :aprovechamiento"),
    @NamedQuery(name = "InsumoTercero.findByTrazo", query = "SELECT i FROM InsumoTercero i WHERE i.trazo = :trazo"),
    @NamedQuery(name = "InsumoTercero.findByImagen", query = "SELECT i FROM InsumoTercero i WHERE i.imagen = :imagen"),
    @NamedQuery(name = "InsumoTercero.findByLargoTrazo", query = "SELECT i FROM InsumoTercero i WHERE i.largoTrazo = :largoTrazo"),
    @NamedQuery(name = "InsumoTercero.findByCreado", query = "SELECT i FROM InsumoTercero i WHERE i.creado = :creado"),
    @NamedQuery(name = "InsumoTercero.findByActualizado", query = "SELECT i FROM InsumoTercero i WHERE i.actualizado = :actualizado"),
    @NamedQuery(name = "InsumoTercero.findByIdEstilo", query = "SELECT i FROM InsumoTercero i WHERE i.idEstilo = :idEstilo"),
})
public class InsumoTercero implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INSUMO_TERCERO")
    private Integer idInsumoTercero;
    @Size(max = 30)
    @Column(name = "TALLA")
    private String talla;
    @Size(max = 30)
    @Column(name = "RENDIMIENTO")
    private String rendimiento;
    @Size(max = 30)
    @Column(name = "ANCHO_UTIL")
    private String anchoUtil;
    @Size(max = 30)
    @Column(name = "CONSUMO_POR_PRENDA")
    private String consumoPorPrenda;
    @Size(max = 30)
    @Column(name = "APROVECHAMIENTO")
    private String aprovechamiento;
    @Size(max = 50)
    @Column(name = "TRAZO")
    private String trazo;
    @Size(max = 100)
    @Column(name = "IMAGEN")
    private String imagen;
    @Size(max = 30)
    @Column(name = "LARGO_TRAZO")
    private String largoTrazo;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "ACTUALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;
    @JoinColumn(name = "ID_CREADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idCreado;
    @JoinColumn(name = "ID_ACTUALIZADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idActualizado;
    @JoinColumn(name = "ID_MATERIAL_ESTILO", referencedColumnName = "ID_MATERIAL_ESTILO")
    @ManyToOne
    private MaterialEstilo idMaterialEstilo;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES")
    private String observaciones;

    public InsumoTercero() {
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public InsumoTercero(Integer idInsumoTercero) {
        this.idInsumoTercero = idInsumoTercero;
    }

    public Integer getIdInsumoTercero() {
        return idInsumoTercero;
    }

    public Estilo getIdEstilo() {
        return idEstilo;
    }

    public void setIdEstilo(Estilo idEstilo) {
        this.idEstilo = idEstilo;
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

    public MaterialEstilo getIdMaterialEstilo() {
        return idMaterialEstilo;
    }

    public void setIdMaterialEstilo(MaterialEstilo idMaterialEstilo) {
        this.idMaterialEstilo = idMaterialEstilo;
    }

    public void setIdInsumoTercero(Integer idInsumoTercero) {
        this.idInsumoTercero = idInsumoTercero;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    public String getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(String rendimiento) {
        this.rendimiento = rendimiento;
    }

    public String getAnchoUtil() {
        return anchoUtil;
    }

    public void setAnchoUtil(String anchoUtil) {
        this.anchoUtil = anchoUtil;
    }

    public String getConsumoPorPrenda() {
        return consumoPorPrenda;
    }

    public void setConsumoPorPrenda(String consumoPorPrenda) {
        this.consumoPorPrenda = consumoPorPrenda;
    }

    public String getAprovechamiento() {
        return aprovechamiento;
    }

    public void setAprovechamiento(String aprovechamiento) {
        this.aprovechamiento = aprovechamiento;
    }

    public String getTrazo() {
        return trazo;
    }

    public void setTrazo(String trazo) {
        this.trazo = trazo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getLargoTrazo() {
        return largoTrazo;
    }

    public void setLargoTrazo(String largoTrazo) {
        this.largoTrazo = largoTrazo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInsumoTercero != null ? idInsumoTercero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumoTercero)) {
            return false;
        }
        InsumoTercero other = (InsumoTercero) object;
        if ((this.idInsumoTercero == null && other.idInsumoTercero != null) || (this.idInsumoTercero != null && !this.idInsumoTercero.equals(other.idInsumoTercero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.InsumoTercero[ idInsumoTercero=" + idInsumoTercero + " ]";
    }
    
}
