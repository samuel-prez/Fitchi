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
@Table(name = "parametro_tallaje_doblez")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroTallajeDoblez.findAll", query = "SELECT p FROM ParametroTallajeDoblez p"),
    @NamedQuery(name = "ParametroTallajeDoblez.findByIdParametroTallajeDoblez", query = "SELECT p FROM ParametroTallajeDoblez p WHERE p.idParametroTallajeDoblez = :idParametroTallajeDoblez"),
    @NamedQuery(name = "ParametroTallajeDoblez.findByMedida", query = "SELECT p FROM ParametroTallajeDoblez p WHERE p.medida = :medida"),
    @NamedQuery(name = "ParametroTallajeDoblez.findByObservaciones", query = "SELECT p FROM ParametroTallajeDoblez p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "ParametroTallajeDoblez.findByIdEstilo", query = "SELECT p FROM ParametroTallajeDoblez p WHERE p.idParametroTallajeTalla.idParametroTallaje.idEstilo = :idEstilo"),
    @NamedQuery(name = "ParametroTallajeDoblez.findByIdParametroTallajeTalla", query = "SELECT p FROM ParametroTallajeDoblez p WHERE p.idParametroTallajeTalla = :idParametroTallajeTalla")})
public class ParametroTallajeDoblez implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PARAMETRO_TALLAJE_DOBLEZ")
    private Integer idParametroTallajeDoblez;
    @Size(max = 20)
    @Column(name = "MEDIDA")
    private String medida;
    @Size(max = 200)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ID_TUBULAR_DOBLEZ", referencedColumnName = "ID_TUBULAR_DOBLEZ")
    @ManyToOne
    private TubularDoblez idTubularDoblez;
    @JoinColumn(name = "ID_PARAMETRO_TALLAJE_TALLA", referencedColumnName = "ID_PARAMETRO_TALLAJE_TALLA")
    @ManyToOne
    private ParametroTallajeTalla idParametroTallajeTalla;

    public ParametroTallajeDoblez() {
    }

    public ParametroTallajeTalla getIdParametroTallajeTalla() {
        return idParametroTallajeTalla;
    }

    public void setIdParametroTallajeTalla(ParametroTallajeTalla idParametroTallajeTalla) {
        this.idParametroTallajeTalla = idParametroTallajeTalla;
    }

    public ParametroTallajeDoblez(Integer idParametroTallajeDoblez) {
        this.idParametroTallajeDoblez = idParametroTallajeDoblez;
    }

    public Integer getIdParametroTallajeDoblez() {
        return idParametroTallajeDoblez;
    }

    public void setIdParametroTallajeDoblez(Integer idParametroTallajeDoblez) {
        this.idParametroTallajeDoblez = idParametroTallajeDoblez;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TubularDoblez getIdTubularDoblez() {
        return idTubularDoblez;
    }

    public void setIdTubularDoblez(TubularDoblez idTubularDoblez) {
        this.idTubularDoblez = idTubularDoblez;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametroTallajeDoblez != null ? idParametroTallajeDoblez.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroTallajeDoblez)) {
            return false;
        }
        ParametroTallajeDoblez other = (ParametroTallajeDoblez) object;
        if ((this.idParametroTallajeDoblez == null && other.idParametroTallajeDoblez != null) || (this.idParametroTallajeDoblez != null && !this.idParametroTallajeDoblez.equals(other.idParametroTallajeDoblez))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParametroTallajeDoblez[ idParametroTallajeDoblez=" + idParametroTallajeDoblez + " ]";
    }

}
