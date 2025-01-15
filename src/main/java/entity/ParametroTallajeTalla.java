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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "parametro_tallaje_talla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroTallajeTalla.findAll", query = "SELECT p FROM ParametroTallajeTalla p"),
    @NamedQuery(name = "ParametroTallajeTalla.findByIdParametroTallajeTalla", query = "SELECT p FROM ParametroTallajeTalla p WHERE p.idParametroTallajeTalla = :idParametroTallajeTalla"),
    @NamedQuery(name = "ParametroTallajeTalla.findByLector", query = "SELECT p FROM ParametroTallajeTalla p WHERE p.lector = :lector"),
    @NamedQuery(name = "ParametroTallajeTalla.findByObservaciones", query = "SELECT p FROM ParametroTallajeTalla p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "ParametroTallajeTalla.findByIdParametroTallaje", query = "SELECT p FROM ParametroTallajeTalla p WHERE p.idParametroTallaje = :idParametroTallaje"),

})
public class ParametroTallajeTalla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PARAMETRO_TALLAJE_TALLA")
    private Integer idParametroTallajeTalla;
    @Size(max = 50)
    @Column(name = "LECTOR")
    private String lector;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @JoinColumn(name = "ID_PARAMETRO_TALLAJE", referencedColumnName = "ID_PARAMETROS_TALLAJE")
    @ManyToOne
    private ParametroTallaje idParametroTallaje;
    @OneToMany(mappedBy = "idParametroTallajeTalla")
    private List<ParametroTallajeDimension> parametroTallajeDimensionList;
    @OneToMany(mappedBy = "idParametroTallajeTalla")
    private List<ParametroTallajeAlista> parametroTallajeAlistaList;
    @JoinColumn(name = "ID_TALLA_ESTILO", referencedColumnName = "ID_TALLA_ESTILO")
    @ManyToOne
    private TallaEstilo idTallaEstilo;

    public ParametroTallajeTalla() {
    }

    public ParametroTallajeTalla(Integer idParametroTallajeTalla) {
        this.idParametroTallajeTalla = idParametroTallajeTalla;
    }

    public Integer getIdParametroTallajeTalla() {
        return idParametroTallajeTalla;
    }

    public TallaEstilo getIdTallaEstilo() {
        return idTallaEstilo;
    }

    public void setIdTallaEstilo(TallaEstilo idTallaEstilo) {
        this.idTallaEstilo = idTallaEstilo;
    }

    public void setIdParametroTallajeTalla(Integer idParametroTallajeTalla) {
        this.idParametroTallajeTalla = idParametroTallajeTalla;
    }

    public String getLector() {
        return lector;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public ParametroTallaje getIdParametroTallaje() {
        return idParametroTallaje;
    }

    public void setIdParametroTallaje(ParametroTallaje idParametroTallaje) {
        this.idParametroTallaje = idParametroTallaje;
    }

    @XmlTransient
    public List<ParametroTallajeDimension> getParametroTallajeDimensionList() {
        return parametroTallajeDimensionList;
    }

    public void setParametroTallajeDimensionList(List<ParametroTallajeDimension> parametroTallajeDimensionList) {
        this.parametroTallajeDimensionList = parametroTallajeDimensionList;
    }

    @XmlTransient
    public List<ParametroTallajeAlista> getParametroTallajeAlistaList() {
        return parametroTallajeAlistaList;
    }

    public void setParametroTallajeAlistaList(List<ParametroTallajeAlista> parametroTallajeAlistaList) {
        this.parametroTallajeAlistaList = parametroTallajeAlistaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametroTallajeTalla != null ? idParametroTallajeTalla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroTallajeTalla)) {
            return false;
        }
        ParametroTallajeTalla other = (ParametroTallajeTalla) object;
        if ((this.idParametroTallajeTalla == null && other.idParametroTallajeTalla != null) || (this.idParametroTallajeTalla != null && !this.idParametroTallajeTalla.equals(other.idParametroTallajeTalla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParametroTallajeTalla[ idParametroTallajeTalla=" + idParametroTallajeTalla + " ]";
    }
    
}
