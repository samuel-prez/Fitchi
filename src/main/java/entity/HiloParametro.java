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
@Table(name = "hilo_parametro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HiloParametro.findAll", query = "SELECT h FROM HiloParametro h"),
    @NamedQuery(name = "HiloParametro.findByIdHiloParametro", query = "SELECT h FROM HiloParametro h WHERE h.idHiloParametro = :idHiloParametro"),
    @NamedQuery(name = "HiloParametro.findByNombre", query = "SELECT h FROM HiloParametro h WHERE h.nombre = :nombre")})
public class HiloParametro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HILO_PARAMETRO")
    private Integer idHiloParametro;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idHiloParametro")
    private List<HiloTejidoCirculares> hiloTejidoCircularesList;

    public HiloParametro() {
    }

    public HiloParametro(Integer idHiloParametro) {
        this.idHiloParametro = idHiloParametro;
    }

    public Integer getIdHiloParametro() {
        return idHiloParametro;
    }

    public void setIdHiloParametro(Integer idHiloParametro) {
        this.idHiloParametro = idHiloParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<HiloTejidoCirculares> getHiloTejidoCircularesList() {
        return hiloTejidoCircularesList;
    }

    public void setHiloTejidoCircularesList(List<HiloTejidoCirculares> hiloTejidoCircularesList) {
        this.hiloTejidoCircularesList = hiloTejidoCircularesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHiloParametro != null ? idHiloParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HiloParametro)) {
            return false;
        }
        HiloParametro other = (HiloParametro) object;
        if ((this.idHiloParametro == null && other.idHiloParametro != null) || (this.idHiloParametro != null && !this.idHiloParametro.equals(other.idHiloParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HiloParametro[ idHiloParametro=" + idHiloParametro + " ]";
    }
    
}
