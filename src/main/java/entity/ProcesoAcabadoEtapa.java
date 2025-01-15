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
@Table(name = "proceso_acabado_etapa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoAcabadoEtapa.findAll", query = "SELECT p FROM ProcesoAcabadoEtapa p"),
    @NamedQuery(name = "ProcesoAcabadoEtapa.findByIdProcesoAcabadoEtapa", query = "SELECT p FROM ProcesoAcabadoEtapa p WHERE p.idProcesoAcabadoEtapa = :idProcesoAcabadoEtapa"),
    @NamedQuery(name = "ProcesoAcabadoEtapa.findByNombre", query = "SELECT p FROM ProcesoAcabadoEtapa p WHERE p.nombre = :nombre")})
public class ProcesoAcabadoEtapa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROCESO_ACABADO_ETAPA")
    private Integer idProcesoAcabadoEtapa;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idProcesoAcabadoEtapa")
    private List<ProcesoAcabadoFase> procesoAcabadoFaseList;

    public ProcesoAcabadoEtapa() {
    }

    public ProcesoAcabadoEtapa(Integer idProcesoAcabadoEtapa) {
        this.idProcesoAcabadoEtapa = idProcesoAcabadoEtapa;
    }

    public Integer getIdProcesoAcabadoEtapa() {
        return idProcesoAcabadoEtapa;
    }

    public void setIdProcesoAcabadoEtapa(Integer idProcesoAcabadoEtapa) {
        this.idProcesoAcabadoEtapa = idProcesoAcabadoEtapa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        hash += (idProcesoAcabadoEtapa != null ? idProcesoAcabadoEtapa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoAcabadoEtapa)) {
            return false;
        }
        ProcesoAcabadoEtapa other = (ProcesoAcabadoEtapa) object;
        if ((this.idProcesoAcabadoEtapa == null && other.idProcesoAcabadoEtapa != null) || (this.idProcesoAcabadoEtapa != null && !this.idProcesoAcabadoEtapa.equals(other.idProcesoAcabadoEtapa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProcesoAcabadoEtapa[ idProcesoAcabadoEtapa=" + idProcesoAcabadoEtapa + " ]";
    }
    
}
