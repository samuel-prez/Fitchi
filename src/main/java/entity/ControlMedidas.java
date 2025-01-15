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
@Table(name = "control_medidas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlMedidas.findAll", query = "SELECT c FROM ControlMedidas c"),
    @NamedQuery(name = "ControlMedidas.findByIdControlMedidas", query = "SELECT c FROM ControlMedidas c WHERE c.idControlMedidas = :idControlMedidas"),
    @NamedQuery(name = "ControlMedidas.findByCreado", query = "SELECT c FROM ControlMedidas c WHERE c.creado = :creado"),
    @NamedQuery(name = "ControlMedidas.findByActualizado", query = "SELECT c FROM ControlMedidas c WHERE c.actualizado = :actualizado"),
    @NamedQuery(name = "ControlMedidas.findByObservaciones", query = "SELECT c FROM ControlMedidas c WHERE c.observaciones = :observaciones"),
    @NamedQuery(name = "ControlMedidas.findByIdEstilo", query = "SELECT c FROM ControlMedidas c WHERE c.idEstilo = :idEstilo")
})
public class ControlMedidas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONTROL_MEDIDAS")
    private Integer idControlMedidas;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "ACTUALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Size(max = 50)
    @Column(name = "IMAGEN")
    private String imagen;
    @OneToMany(mappedBy = "idControlMedidas")
    private List<ControlMedidasTalla> controlMedidasTallaList;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;
    @JoinColumn(name = "ID_CREADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idCreado;
    @JoinColumn(name = "ID_ACTUALIZADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idActualizado;

    public ControlMedidas() {
    }

    public ControlMedidas(Integer idControlMedidas) {
        this.idControlMedidas = idControlMedidas;
    }

    public Integer getIdControlMedidas() {
        return idControlMedidas;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setIdControlMedidas(Integer idControlMedidas) {
        this.idControlMedidas = idControlMedidas;
    }

    public Date getCreado() {
        return creado;
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

    @XmlTransient
    public List<ControlMedidasTalla> getControlMedidasTallaList() {
        return controlMedidasTallaList;
    }

    public void setControlMedidasTallaList(List<ControlMedidasTalla> controlMedidasTallaList) {
        this.controlMedidasTallaList = controlMedidasTallaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControlMedidas != null ? idControlMedidas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlMedidas)) {
            return false;
        }
        ControlMedidas other = (ControlMedidas) object;
        if ((this.idControlMedidas == null && other.idControlMedidas != null) || (this.idControlMedidas != null && !this.idControlMedidas.equals(other.idControlMedidas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ControlMedidas[ idControlMedidas=" + idControlMedidas + " ]";
    }
    
}
