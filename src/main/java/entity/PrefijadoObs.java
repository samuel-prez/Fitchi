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
@Table(name = "prefijado_obs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PrefijadoObs.findAll", query = "SELECT p FROM PrefijadoObs p"),
    @NamedQuery(name = "PrefijadoObs.findByIdPrefijadoObs", query = "SELECT p FROM PrefijadoObs p WHERE p.idPrefijadoObs = :idPrefijadoObs"),
    @NamedQuery(name = "PrefijadoObs.findByNombre", query = "SELECT p FROM PrefijadoObs p WHERE p.nombre = :nombre")})
public class PrefijadoObs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PREFIJADO_OBS")
    private Integer idPrefijadoObs;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idPrefijadoObs")
    private List<TejidoCirculares> tejidoCircularesList;

    public PrefijadoObs() {
    }

    public PrefijadoObs(Integer idPrefijadoObs) {
        this.idPrefijadoObs = idPrefijadoObs;
    }

    public Integer getIdPrefijadoObs() {
        return idPrefijadoObs;
    }

    public void setIdPrefijadoObs(Integer idPrefijadoObs) {
        this.idPrefijadoObs = idPrefijadoObs;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<TejidoCirculares> getTejidoCircularesList() {
        return tejidoCircularesList;
    }

    public void setTejidoCircularesList(List<TejidoCirculares> tejidoCircularesList) {
        this.tejidoCircularesList = tejidoCircularesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrefijadoObs != null ? idPrefijadoObs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PrefijadoObs)) {
            return false;
        }
        PrefijadoObs other = (PrefijadoObs) object;
        if ((this.idPrefijadoObs == null && other.idPrefijadoObs != null) || (this.idPrefijadoObs != null && !this.idPrefijadoObs.equals(other.idPrefijadoObs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PrefijadoObs[ idPrefijadoObs=" + idPrefijadoObs + " ]";
    }
    
}
