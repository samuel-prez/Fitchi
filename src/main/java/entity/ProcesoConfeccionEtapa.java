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
@Table(name = "proceso_confeccion_etapa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoConfeccionEtapa.findAll", query = "SELECT p FROM ProcesoConfeccionEtapa p"),
    @NamedQuery(name = "ProcesoConfeccionEtapa.findByIdProcesoConfeccionEtapa", query = "SELECT p FROM ProcesoConfeccionEtapa p WHERE p.idProcesoConfeccionEtapa = :idProcesoConfeccionEtapa"),
    @NamedQuery(name = "ProcesoConfeccionEtapa.findByNombre", query = "SELECT p FROM ProcesoConfeccionEtapa p WHERE p.nombre = :nombre")})
public class ProcesoConfeccionEtapa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROCESO_CONFECCION_ETAPA")
    private Integer idProcesoConfeccionEtapa;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idProcesoConfeccionEtapa")
    private List<ProcesoConfeccionFase> procesoConfeccionFaseList;

    public ProcesoConfeccionEtapa() {
    }

    public ProcesoConfeccionEtapa(Integer idProcesoConfeccionEtapa) {
        this.idProcesoConfeccionEtapa = idProcesoConfeccionEtapa;
    }

    public Integer getIdProcesoConfeccionEtapa() {
        return idProcesoConfeccionEtapa;
    }

    public void setIdProcesoConfeccionEtapa(Integer idProcesoConfeccionEtapa) {
        this.idProcesoConfeccionEtapa = idProcesoConfeccionEtapa;
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
        hash += (idProcesoConfeccionEtapa != null ? idProcesoConfeccionEtapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoConfeccionEtapa)) {
            return false;
        }
        ProcesoConfeccionEtapa other = (ProcesoConfeccionEtapa) object;
        if ((this.idProcesoConfeccionEtapa == null && other.idProcesoConfeccionEtapa != null) || (this.idProcesoConfeccionEtapa != null && !this.idProcesoConfeccionEtapa.equals(other.idProcesoConfeccionEtapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProcesoConfeccionEtapa[ idProcesoConfeccionEtapa=" + idProcesoConfeccionEtapa + " ]";
    }
    
}
