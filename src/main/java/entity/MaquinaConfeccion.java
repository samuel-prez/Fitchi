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
@Table(name = "maquina_confeccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaquinaConfeccion.findAll", query = "SELECT m FROM MaquinaConfeccion m"),
    @NamedQuery(name = "MaquinaConfeccion.findByIdMaquinaConfecicon", query = "SELECT m FROM MaquinaConfeccion m WHERE m.idMaquinaConfecicon = :idMaquinaConfecicon"),
    @NamedQuery(name = "MaquinaConfeccion.findByNombre", query = "SELECT m FROM MaquinaConfeccion m WHERE m.nombre = :nombre")})
public class MaquinaConfeccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MAQUINA_CONFECICON")
    private Integer idMaquinaConfecicon;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idMaquina")
    private List<ProcesoConfeccionFase> procesoConfeccionFaseList;

    public MaquinaConfeccion() {
    }

    public MaquinaConfeccion(Integer idMaquinaConfecicon) {
        this.idMaquinaConfecicon = idMaquinaConfecicon;
    }

    public Integer getIdMaquinaConfecicon() {
        return idMaquinaConfecicon;
    }

    public void setIdMaquinaConfecicon(Integer idMaquinaConfecicon) {
        this.idMaquinaConfecicon = idMaquinaConfecicon;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idMaquinaConfecicon != null ? idMaquinaConfecicon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaquinaConfeccion)) {
            return false;
        }
        MaquinaConfeccion other = (MaquinaConfeccion) object;
        if ((this.idMaquinaConfecicon == null && other.idMaquinaConfecicon != null) || (this.idMaquinaConfecicon != null && !this.idMaquinaConfecicon.equals(other.idMaquinaConfecicon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MaquinaConfeccion[ idMaquinaConfecicon=" + idMaquinaConfecicon + " ]";
    }
    
}
