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
@Table(name = "parametro_tallaje_dimension")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroTallajeDimension.findAll", query = "SELECT p FROM ParametroTallajeDimension p"),
    @NamedQuery(name = "ParametroTallajeDimension.findByIdParametroTallajeDimension", query = "SELECT p FROM ParametroTallajeDimension p WHERE p.idParametroTallajeDimension = :idParametroTallajeDimension"),
    @NamedQuery(name = "ParametroTallajeDimension.findByAncho", query = "SELECT p FROM ParametroTallajeDimension p WHERE p.ancho = :ancho"),
    @NamedQuery(name = "ParametroTallajeDimension.findByAnchoTcia", query = "SELECT p FROM ParametroTallajeDimension p WHERE p.anchoTcia = :anchoTcia"),
    @NamedQuery(name = "ParametroTallajeDimension.findByLargo", query = "SELECT p FROM ParametroTallajeDimension p WHERE p.largo = :largo"),
    @NamedQuery(name = "ParametroTallajeDimension.findByLargoTcia", query = "SELECT p FROM ParametroTallajeDimension p WHERE p.largoTcia = :largoTcia"),
    @NamedQuery(name = "ParametroTallajeDimension.findByAnchoP", query = "SELECT p FROM ParametroTallajeDimension p WHERE p.anchoP = :anchoP"),
    @NamedQuery(name = "ParametroTallajeDimension.findByAnchoTciaP", query = "SELECT p FROM ParametroTallajeDimension p WHERE p.anchoTciaP = :anchoTciaP"),
    @NamedQuery(name = "ParametroTallajeDimension.findByLargoP", query = "SELECT p FROM ParametroTallajeDimension p WHERE p.largoP = :largoP"),
    @NamedQuery(name = "ParametroTallajeDimension.findByLargoTciaP", query = "SELECT p FROM ParametroTallajeDimension p WHERE p.largoTciaP = :largoTciaP"),
    @NamedQuery(name = "ParametroTallajeDimension.findByIdParametroTallajeTalla", query = "SELECT p FROM ParametroTallajeDimension p WHERE p.idParametroTallajeTalla = :idParametroTallajeTalla"),
})
public class ParametroTallajeDimension implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PARAMETRO_TALLAJE_DIMENSION")
    private Integer idParametroTallajeDimension;
    @Size(max = 10)
    @Column(name = "ANCHO")
    private String ancho;
    @Size(max = 10)
    @Column(name = "ANCHO_TCIA")
    private String anchoTcia;
    @Size(max = 10)
    @Column(name = "LARGO")
    private String largo;
    @Size(max = 10)
    @Column(name = "LARGO_TCIA")
    private String largoTcia;
    @Size(max = 10)
    @Column(name = "ANCHO_P")
    private String anchoP;
    @Size(max = 10)
    @Column(name = "ANCHO_TCIA_P")
    private String anchoTciaP;
    @Size(max = 10)
    @Column(name = "LARGO_P")
    private String largoP;
    @Size(max = 10)
    @Column(name = "LARGO_TCIA_P")
    private String largoTciaP;
    @JoinColumn(name = "ID_PARAMETRO_TALLAJE_TALLA", referencedColumnName = "ID_PARAMETRO_TALLAJE_TALLA")
    @ManyToOne
    private ParametroTallajeTalla idParametroTallajeTalla;
    @JoinColumn(name = "ID_HILO_PARAMETRO", referencedColumnName = "ID_HILO_PARAMETRO")
    @ManyToOne
    private HiloParametro idHiloParametro;
    @JoinColumn(name = "ID_HILO_TUBULAR", referencedColumnName = "ID_HILO_TUBULAR")
    @ManyToOne
    private HiloTubular idHiloTubular;

    public ParametroTallajeDimension() {
    }

    public ParametroTallajeDimension(Integer idParametroTallajeDimension) {
        this.idParametroTallajeDimension = idParametroTallajeDimension;
    }

    public HiloParametro getIdHiloParametro() {
        return idHiloParametro;
    }

    public void setIdHiloParametro(HiloParametro idHiloParametro) {
        this.idHiloParametro = idHiloParametro;
    }

    public HiloTubular getIdHiloTubular() {
        return idHiloTubular;
    }

    public void setIdHiloTubular(HiloTubular idHiloTubular) {
        this.idHiloTubular = idHiloTubular;
    }

    public Integer getIdParametroTallajeDimension() {
        return idParametroTallajeDimension;
    }

    public void setIdParametroTallajeDimension(Integer idParametroTallajeDimension) {
        this.idParametroTallajeDimension = idParametroTallajeDimension;
    }

    public String getAncho() {
        return ancho;
    }

    public void setAncho(String ancho) {
        this.ancho = ancho;
    }

    public String getAnchoTcia() {
        return anchoTcia;
    }

    public void setAnchoTcia(String anchoTcia) {
        this.anchoTcia = anchoTcia;
    }

    public String getLargo() {
        return largo;
    }

    public void setLargo(String largo) {
        this.largo = largo;
    }

    public String getLargoTcia() {
        return largoTcia;
    }

    public void setLargoTcia(String largoTcia) {
        this.largoTcia = largoTcia;
    }

    public String getAnchoP() {
        return anchoP;
    }

    public void setAnchoP(String anchoP) {
        this.anchoP = anchoP;
    }

    public String getAnchoTciaP() {
        return anchoTciaP;
    }

    public void setAnchoTciaP(String anchoTciaP) {
        this.anchoTciaP = anchoTciaP;
    }

    public String getLargoP() {
        return largoP;
    }

    public void setLargoP(String largoP) {
        this.largoP = largoP;
    }

    public String getLargoTciaP() {
        return largoTciaP;
    }

    public void setLargoTciaP(String largoTciaP) {
        this.largoTciaP = largoTciaP;
    }

    public ParametroTallajeTalla getIdParametroTallajeTalla() {
        return idParametroTallajeTalla;
    }

    public void setIdParametroTallajeTalla(ParametroTallajeTalla idParametroTallajeTalla) {
        this.idParametroTallajeTalla = idParametroTallajeTalla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametroTallajeDimension != null ? idParametroTallajeDimension.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroTallajeDimension)) {
            return false;
        }
        ParametroTallajeDimension other = (ParametroTallajeDimension) object;
        if ((this.idParametroTallajeDimension == null && other.idParametroTallajeDimension != null) || (this.idParametroTallajeDimension != null && !this.idParametroTallajeDimension.equals(other.idParametroTallajeDimension))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParametroTallajeDimension[ idParametroTallajeDimension=" + idParametroTallajeDimension + " ]";
    }
    
}
