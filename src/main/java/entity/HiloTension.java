/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samuel P.
 */
@Entity
@Table(name = "hilo_tension")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HiloTension.findAll", query = "SELECT h FROM HiloTension h"),
    @NamedQuery(name = "HiloTension.findByIdHiloTension", query = "SELECT h FROM HiloTension h WHERE h.idHiloTension = :idHiloTension"),
    @NamedQuery(name = "HiloTension.findByTension", query = "SELECT h FROM HiloTension h WHERE h.tension = :tension"),
    @NamedQuery(name = "HiloTension.findByIdTejidoCirculares", query = "SELECT h FROM HiloTension h WHERE h.idTejidoCirculares = :idTejidoCirculares"),
})
public class HiloTension implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HILO_TENSION")
    private Integer idHiloTension;
    @Size(max = 50)
    @Column(name = "TENSION")
    private String tension;
    @JoinColumn(name = "ID_TEJIDO_CIRCULARES", referencedColumnName = "ID_TEJIDO_CIRCULARES")
    @ManyToOne
    private TejidoCirculares idTejidoCirculares;
    @JoinColumn(name = "ID_HILO", referencedColumnName = "ID_MATERIAL_ESTILO")
    @ManyToOne
    private MaterialEstilo idHilo;

    public HiloTension() {
    }

    public HiloTension(Integer idHiloTension) {
        this.idHiloTension = idHiloTension;
    }

    public Integer getIdHiloTension() {
        return idHiloTension;
    }

    public void setIdHiloTension(Integer idHiloTension) {
        this.idHiloTension = idHiloTension;
    }

    public String getTension() {
        return tension;
    }

    public void setTension(String tension) {
        this.tension = tension;
    }

    public MaterialEstilo getIdHilo() {
        return idHilo;
    }

    public void setIdHilo(MaterialEstilo idHilo) {
        this.idHilo = idHilo;
    }

    public TejidoCirculares getIdTejidoCirculares() {
        return idTejidoCirculares;
    }

    public void setIdTejidoCirculares(TejidoCirculares idTejidoCirculares) {
        this.idTejidoCirculares = idTejidoCirculares;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHiloTension != null ? idHiloTension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HiloTension)) {
            return false;
        }
        HiloTension other = (HiloTension) object;
        if ((this.idHiloTension == null && other.idHiloTension != null) || (this.idHiloTension != null && !this.idHiloTension.equals(other.idHiloTension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HiloTension[ idHiloTension=" + idHiloTension + " ]";
    }
    
}
