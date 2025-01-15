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
@Table(name = "talla_estilo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TallaEstilo.findAll", query = "SELECT t FROM TallaEstilo t"),
    @NamedQuery(name = "TallaEstilo.findByIdTallaEstilo", query = "SELECT t FROM TallaEstilo t WHERE t.idTallaEstilo = :idTallaEstilo"),
    @NamedQuery(name = "TallaEstilo.findByIdEstilo", query = "SELECT t FROM TallaEstilo t WHERE t.idEstilo = :idEstilo"),
    @NamedQuery(name = "TallaEstilo.findByIdentificacionTallaTejido", query = "SELECT t FROM TallaEstilo t WHERE t.identificacionTallaTejido = :identificacionTallaTejido"),
    @NamedQuery(name = "TallaEstilo.findByObservacionesTallaje", query = "SELECT t FROM TallaEstilo t WHERE t.observacionesTallaje = :observacionesTallaje"),
    @NamedQuery(name = "TallaEstilo.findByIdEstiloAndTalla", query = "SELECT t FROM TallaEstilo t WHERE t.idEstilo = :idEstilo AND t.idTalla = :idTalla"),
})
public class TallaEstilo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TALLA_ESTILO")
    private Integer idTallaEstilo;
    @Size(max = 500)
    @Column(name = "IDENTIFICACION_TALLA_TEJIDO")
    private String identificacionTallaTejido;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES_TALLAJE")
    private String observacionesTallaje;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;
    @JoinColumn(name = "ID_TALLA", referencedColumnName = "ID_TALLA")
    @ManyToOne
    private Talla idTalla;

    public TallaEstilo() {
    }

    public TallaEstilo(Integer idTallaEstilo) {
        this.idTallaEstilo = idTallaEstilo;
    }

    public Integer getIdTallaEstilo() {
        return idTallaEstilo;
    }

    public void setIdTallaEstilo(Integer idTallaEstilo) {
        this.idTallaEstilo = idTallaEstilo;
    }

    public String getIdentificacionTallaTejido() {
        return identificacionTallaTejido;
    }

    public void setIdentificacionTallaTejido(String identificacionTallaTejido) {
        this.identificacionTallaTejido = identificacionTallaTejido;
    }

    public String getObservacionesTallaje() {
        return observacionesTallaje;
    }

    public void setObservacionesTallaje(String observacionesTallaje) {
        this.observacionesTallaje = observacionesTallaje;
    }

    public Estilo getIdEstilo() {
        return idEstilo;
    }

    public void setIdEstilo(Estilo idEstilo) {
        this.idEstilo = idEstilo;
    }

    public Talla getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(Talla idTalla) {
        this.idTalla = idTalla;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTallaEstilo != null ? idTallaEstilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TallaEstilo)) {
            return false;
        }
        TallaEstilo other = (TallaEstilo) object;
        if ((this.idTallaEstilo == null && other.idTallaEstilo != null) || (this.idTallaEstilo != null && !this.idTallaEstilo.equals(other.idTallaEstilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TallaEstilo[ idTallaEstilo=" + idTallaEstilo + " ]";
    }
    
}
