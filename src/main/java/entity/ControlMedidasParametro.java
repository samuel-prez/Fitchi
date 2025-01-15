/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Samuel P.
 */
@Entity
@Table(name = "control_medidas_parametro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlMedidasParametro.findAll", query = "SELECT c FROM ControlMedidasParametro c"),
    @NamedQuery(name = "ControlMedidasParametro.findByIdControlMedidasParametro", query = "SELECT c FROM ControlMedidasParametro c WHERE c.idControlMedidasParametro = :idControlMedidasParametro"),
    @NamedQuery(name = "ControlMedidasParametro.findByNombre", query = "SELECT c FROM ControlMedidasParametro c WHERE c.nombre = :nombre")})
public class ControlMedidasParametro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONTROL_MEDIDAS_PARAMETRO")
    private Integer idControlMedidasParametro;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idControlMedidasParametro")
    private List<ControlMedidasTalla> controlMedidasTallaList;

    public ControlMedidasParametro() {
    }

    public ControlMedidasParametro(Integer idControlMedidasParametro) {
        this.idControlMedidasParametro = idControlMedidasParametro;
    }

    public Integer getIdControlMedidasParametro() {
        return idControlMedidasParametro;
    }

    public void setIdControlMedidasParametro(Integer idControlMedidasParametro) {
        this.idControlMedidasParametro = idControlMedidasParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idControlMedidasParametro != null ? idControlMedidasParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlMedidasParametro)) {
            return false;
        }
        ControlMedidasParametro other = (ControlMedidasParametro) object;
        if ((this.idControlMedidasParametro == null && other.idControlMedidasParametro != null) || (this.idControlMedidasParametro != null && !this.idControlMedidasParametro.equals(other.idControlMedidasParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ControlMedidasParametro[ idControlMedidasParametro=" + idControlMedidasParametro + " ]";
    }
    
}
