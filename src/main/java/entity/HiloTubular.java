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
@Table(name = "hilo_tubular")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HiloTubular.findAll", query = "SELECT h FROM HiloTubular h"),
    @NamedQuery(name = "HiloTubular.findByIdHiloTubular", query = "SELECT h FROM HiloTubular h WHERE h.idHiloTubular = :idHiloTubular"),
    @NamedQuery(name = "HiloTubular.findByTubo", query = "SELECT h FROM HiloTubular h WHERE h.tubo = :tubo"),
    @NamedQuery(name = "HiloTubular.findByDescripcion", query = "SELECT h FROM HiloTubular h WHERE h.descripcion = :descripcion"),
    @NamedQuery(name = "HiloTubular.findByCantidad", query = "SELECT h FROM HiloTubular h WHERE h.cantidad = :cantidad"),
    @NamedQuery(name = "HiloTubular.findByIdTejidoCirculares", query = "SELECT h FROM HiloTubular h WHERE h.idTejidoCirculares = :idTejidoCirculares"),
    @NamedQuery(name = "HiloTubular.findByIdEstilo", query = "SELECT h FROM HiloTubular h WHERE h.idTejidoCirculares.idEstilo = :idEstilo"),
})
public class HiloTubular implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HILO_TUBULAR")
    private Integer idHiloTubular;
    @Size(max = 100)
    @Column(name = "TUBO")
    private String tubo;
    @Size(max = 100)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CANTIDAD")
    private Double cantidad;
    @JoinColumn(name = "ID_TEJIDO_CIRCULARES", referencedColumnName = "ID_TEJIDO_CIRCULARES")
    @ManyToOne
    private TejidoCirculares idTejidoCirculares;

    public HiloTubular() {
    }

    public HiloTubular(Integer idHiloTubular) {
        this.idHiloTubular = idHiloTubular;
    }

    public Integer getIdHiloTubular() {
        return idHiloTubular;
    }

    public void setIdHiloTubular(Integer idHiloTubular) {
        this.idHiloTubular = idHiloTubular;
    }

    public String getTubo() {
        return tubo;
    }

    public void setTubo(String tubo) {
        this.tubo = tubo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
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
        hash += (idHiloTubular != null ? idHiloTubular.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HiloTubular)) {
            return false;
        }
        HiloTubular other = (HiloTubular) object;
        if ((this.idHiloTubular == null && other.idHiloTubular != null) || (this.idHiloTubular != null && !this.idHiloTubular.equals(other.idHiloTubular))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HiloTubular[ idHiloTubular=" + idHiloTubular + " ]";
    }
    
}
