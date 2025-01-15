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
@Table(name = "proceso_confeccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoConfeccion.findAll", query = "SELECT p FROM ProcesoConfeccion p"),
    @NamedQuery(name = "ProcesoConfeccion.findByIdProcesoConfeccion", query = "SELECT p FROM ProcesoConfeccion p WHERE p.idProcesoConfeccion = :idProcesoConfeccion"),
    @NamedQuery(name = "ProcesoConfeccion.findByCantidadTalego", query = "SELECT p FROM ProcesoConfeccion p WHERE p.cantidadTalego = :cantidadTalego"),
    @NamedQuery(name = "ProcesoConfeccion.findByCantidadBulto", query = "SELECT p FROM ProcesoConfeccion p WHERE p.cantidadBulto = :cantidadBulto"),
    @NamedQuery(name = "ProcesoConfeccion.findByCreado", query = "SELECT p FROM ProcesoConfeccion p WHERE p.creado = :creado"),
    @NamedQuery(name = "ProcesoConfeccion.findByActualizado", query = "SELECT p FROM ProcesoConfeccion p WHERE p.actualizado = :actualizado"),
    @NamedQuery(name = "ProcesoConfeccion.findByIdEstilo", query = "SELECT p FROM ProcesoConfeccion p WHERE p.idEstilo = :idEstilo")})
public class ProcesoConfeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROCESO_CONFECCION")
    private Integer idProcesoConfeccion;
    @Column(name = "CANTIDAD_TALEGO")
    private Integer cantidadTalego;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Size(max = 50)
    @Column(name = "IMAGEN")
    private String imagen;
    @Column(name = "CANTIDAD_BULTO")
    private Integer cantidadBulto;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "ACTUALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;
    @OneToMany(mappedBy = "idProcesoConfeccion")
    private List<ProcesoConfeccionFase> procesoConfeccionFaseList;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;
    @JoinColumn(name = "ID_CREADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idCreado;
    @JoinColumn(name = "ID_ACTUALIZADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idActualizado;

    public ProcesoConfeccion() {
    }

    public ProcesoConfeccion(Integer idProcesoConfeccion) {
        this.idProcesoConfeccion = idProcesoConfeccion;
    }

    public Estilo getIdEstilo() {
        return idEstilo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setIdEstilo(Estilo idEstilo) {
        this.idEstilo = idEstilo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public Integer getIdProcesoConfeccion() {
        return idProcesoConfeccion;
    }

    public void setIdProcesoConfeccion(Integer idProcesoConfeccion) {
        this.idProcesoConfeccion = idProcesoConfeccion;
    }

    public Integer getCantidadTalego() {
        return cantidadTalego;
    }

    public void setCantidadTalego(Integer cantidadTalego) {
        this.cantidadTalego = cantidadTalego;
    }

    public Integer getCantidadBulto() {
        return cantidadBulto;
    }

    public void setCantidadBulto(Integer cantidadBulto) {
        this.cantidadBulto = cantidadBulto;
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

    @XmlTransient
    public List<ProcesoConfeccionFase> getProcesoConfeccionFaseList() {
        return procesoConfeccionFaseList;
    }

    public void setProcesoConfeccionFaseList(List<ProcesoConfeccionFase> procesoConfeccionFaseList) {
        this.procesoConfeccionFaseList = procesoConfeccionFaseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcesoConfeccion != null ? idProcesoConfeccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoConfeccion)) {
            return false;
        }
        ProcesoConfeccion other = (ProcesoConfeccion) object;
        if ((this.idProcesoConfeccion == null && other.idProcesoConfeccion != null) || (this.idProcesoConfeccion != null && !this.idProcesoConfeccion.equals(other.idProcesoConfeccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProcesoConfeccion[ idProcesoConfeccion=" + idProcesoConfeccion + " ]";
    }
    
}
