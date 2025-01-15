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
@Table(name = "proceso_acabado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoAcabado.findAll", query = "SELECT p FROM ProcesoAcabado p"),
    @NamedQuery(name = "ProcesoAcabado.findByIdProcesoAcabado", query = "SELECT p FROM ProcesoAcabado p WHERE p.idProcesoAcabado = :idProcesoAcabado"),
    @NamedQuery(name = "ProcesoAcabado.findByCreado", query = "SELECT p FROM ProcesoAcabado p WHERE p.creado = :creado"),
    @NamedQuery(name = "ProcesoAcabado.findByActualizado", query = "SELECT p FROM ProcesoAcabado p WHERE p.actualizado = :actualizado"),
    @NamedQuery(name = "ProcesoAcabado.findByObservaciones", query = "SELECT p FROM ProcesoAcabado p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "ProcesoAcabado.findByImagen", query = "SELECT p FROM ProcesoAcabado p WHERE p.imagen = :imagen"),
    @NamedQuery(name = "ProcesoAcabado.findByIdEstilo", query = "SELECT p FROM ProcesoAcabado p WHERE p.idEstilo = :idEstilo")})
public class ProcesoAcabado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROCESO_ACABADO")
    private Integer idProcesoAcabado;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "ACTUALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Size(max = 100)
    @Column(name = "IMAGEN")
    private String imagen;
    @OneToMany(mappedBy = "idProcesoAcabado")
    private List<ProcesoAcabadoFase> procesoAcabadoFaseList;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;
    @JoinColumn(name = "ID_CREADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idCreado;
    @JoinColumn(name = "ID_ACTUALIZADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idActualizado;

    public ProcesoAcabado() {
    }

    public ProcesoAcabado(Integer idProcesoAcabado) {
        this.idProcesoAcabado = idProcesoAcabado;
    }

    public Integer getIdProcesoAcabado() {
        return idProcesoAcabado;
    }

    public void setIdProcesoAcabado(Integer idProcesoAcabado) {
        this.idProcesoAcabado = idProcesoAcabado;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @XmlTransient
    public List<ProcesoAcabadoFase> getProcesoAcabadoFaseList() {
        return procesoAcabadoFaseList;
    }

    public void setProcesoAcabadoFaseList(List<ProcesoAcabadoFase> procesoAcabadoFaseList) {
        this.procesoAcabadoFaseList = procesoAcabadoFaseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcesoAcabado != null ? idProcesoAcabado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoAcabado)) {
            return false;
        }
        ProcesoAcabado other = (ProcesoAcabado) object;
        if ((this.idProcesoAcabado == null && other.idProcesoAcabado != null) || (this.idProcesoAcabado != null && !this.idProcesoAcabado.equals(other.idProcesoAcabado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProcesoAcabado[ idProcesoAcabado=" + idProcesoAcabado + " ]";
    }

}
