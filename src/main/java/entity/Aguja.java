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
@Table(name = "aguja")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Aguja.findAll", query = "SELECT a FROM Aguja a"),
    @NamedQuery(name = "Aguja.findByIdAguja", query = "SELECT a FROM Aguja a WHERE a.idAguja = :idAguja"),
    @NamedQuery(name = "Aguja.findByNombre", query = "SELECT a FROM Aguja a WHERE a.nombre = :nombre")})
public class Aguja implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_AGUJA")
    private Integer idAguja;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idAguja")
    private List<ProcesoConfeccionFase> procesoConfeccionFaseList;

    public Aguja() {
    }

    public Aguja(Integer idAguja) {
        this.idAguja = idAguja;
    }

    public Integer getIdAguja() {
        return idAguja;
    }

    public void setIdAguja(Integer idAguja) {
        this.idAguja = idAguja;
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
        hash += (idAguja != null ? idAguja.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aguja)) {
            return false;
        }
        Aguja other = (Aguja) object;
        if ((this.idAguja == null && other.idAguja != null) || (this.idAguja != null && !this.idAguja.equals(other.idAguja))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Aguja[ idAguja=" + idAguja + " ]";
    }
    
}
