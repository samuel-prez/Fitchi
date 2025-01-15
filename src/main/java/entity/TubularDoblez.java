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
@Table(name = "tubular_doblez")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TubularDoblez.findAll", query = "SELECT t FROM TubularDoblez t"),
    @NamedQuery(name = "TubularDoblez.findByIdTubularDoblez", query = "SELECT t FROM TubularDoblez t WHERE t.idTubularDoblez = :idTubularDoblez"),
    @NamedQuery(name = "TubularDoblez.findByNombre", query = "SELECT t FROM TubularDoblez t WHERE t.nombre = :nombre")})
public class TubularDoblez implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TUBULAR_DOBLEZ")
    private Integer idTubularDoblez;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idTubularDoblez")
    private List<ParametroTallajeDoblez> parametroTallajeDoblezList;

    public TubularDoblez() {
    }

    public TubularDoblez(Integer idTubularDoblez) {
        this.idTubularDoblez = idTubularDoblez;
    }

    public Integer getIdTubularDoblez() {
        return idTubularDoblez;
    }

    public void setIdTubularDoblez(Integer idTubularDoblez) {
        this.idTubularDoblez = idTubularDoblez;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ParametroTallajeDoblez> getParametroTallajeDoblezList() {
        return parametroTallajeDoblezList;
    }

    public void setParametroTallajeDoblezList(List<ParametroTallajeDoblez> parametroTallajeDoblezList) {
        this.parametroTallajeDoblezList = parametroTallajeDoblezList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTubularDoblez != null ? idTubularDoblez.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TubularDoblez)) {
            return false;
        }
        TubularDoblez other = (TubularDoblez) object;
        if ((this.idTubularDoblez == null && other.idTubularDoblez != null) || (this.idTubularDoblez != null && !this.idTubularDoblez.equals(other.idTubularDoblez))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TubularDoblez[ idTubularDoblez=" + idTubularDoblez + " ]";
    }
    
}
