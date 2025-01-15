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
@Table(name = "control_medidas_talla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlMedidasTalla.findAll", query = "SELECT c FROM ControlMedidasTalla c"),
    @NamedQuery(name = "ControlMedidasTalla.findByIdControlMedidasTalla", query = "SELECT c FROM ControlMedidasTalla c WHERE c.idControlMedidasTalla = :idControlMedidasTalla"),
    @NamedQuery(name = "ControlMedidasTalla.findByConfeccion", query = "SELECT c FROM ControlMedidasTalla c WHERE c.confeccion = :confeccion"),
    @NamedQuery(name = "ControlMedidasTalla.findByConfeccionTolerancia", query = "SELECT c FROM ControlMedidasTalla c WHERE c.confeccionTolerancia = :confeccionTolerancia"),
    @NamedQuery(name = "ControlMedidasTalla.findByAcabado", query = "SELECT c FROM ControlMedidasTalla c WHERE c.acabado = :acabado"),
    @NamedQuery(name = "ControlMedidasTalla.findByAcabadoTolerancia", query = "SELECT c FROM ControlMedidasTalla c WHERE c.acabadoTolerancia = :acabadoTolerancia"),
    @NamedQuery(name = "ControlMedidasTalla.findByOrden", query = "SELECT c FROM ControlMedidasTalla c WHERE c.orden = :orden"),
    @NamedQuery(name = "ControlMedidasTalla.findByTalla", query = "SELECT c FROM ControlMedidasTalla c WHERE c.idTallaEstilo = :idTallaEstilo order by c.orden"),
    @NamedQuery(name = "ControlMedidasTalla.findByIdControlMedidas", query = "SELECT c FROM ControlMedidasTalla c WHERE c.idControlMedidas = :idControlMedidas"),
})
public class ControlMedidasTalla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_CONTROL_MEDIDAS_TALLA")
    private Integer idControlMedidasTalla;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CONFECCION")
    private Double confeccion;
    @Size(max = 10)
    @Column(name = "CONFECCION_TOLERANCIA")
    private String confeccionTolerancia;
    @Column(name = "ACABADO")
    private Double acabado;
    @Size(max = 10)
    @Column(name = "ACABADO_TOLERANCIA")
    private String acabadoTolerancia;
    @Column(name = "ORDEN")
    private Integer orden;
    @JoinColumn(name = "ID_CONTROL_MEDIDAS", referencedColumnName = "ID_CONTROL_MEDIDAS")
    @ManyToOne
    private ControlMedidas idControlMedidas;
    @JoinColumn(name = "ID_CONTROL_MEDIDAS_PARAMETRO", referencedColumnName = "ID_CONTROL_MEDIDAS_PARAMETRO")
    @ManyToOne
    private ControlMedidasParametro idControlMedidasParametro;
    @JoinColumn(name = "ID_TALLA_ESTILO", referencedColumnName = "ID_TALLA_ESTILO")
    @ManyToOne
    private TallaEstilo idTallaEstilo;

    public ControlMedidasTalla() {
    }

    public ControlMedidasTalla(Integer idControlMedidasTalla) {
        this.idControlMedidasTalla = idControlMedidasTalla;
    }

    public Integer getIdControlMedidasTalla() {
        return idControlMedidasTalla;
    }

    public TallaEstilo getIdTallaEstilo() {
        return idTallaEstilo;
    }

    public void setIdTallaEstilo(TallaEstilo idTallaEstilo) {
        this.idTallaEstilo = idTallaEstilo;
    }

    public void setIdControlMedidasTalla(Integer idControlMedidasTalla) {
        this.idControlMedidasTalla = idControlMedidasTalla;
    }

    public Double getConfeccion() {
        return confeccion;
    }

    public void setConfeccion(Double confeccion) {
        this.confeccion = confeccion;
    }

    public String getConfeccionTolerancia() {
        return confeccionTolerancia;
    }

    public void setConfeccionTolerancia(String confeccionTolerancia) {
        this.confeccionTolerancia = confeccionTolerancia;
    }

    public Double getAcabado() {
        return acabado;
    }

    public void setAcabado(Double acabado) {
        this.acabado = acabado;
    }

    public String getAcabadoTolerancia() {
        return acabadoTolerancia;
    }

    public void setAcabadoTolerancia(String acabadoTolerancia) {
        this.acabadoTolerancia = acabadoTolerancia;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public ControlMedidas getIdControlMedidas() {
        return idControlMedidas;
    }

    public void setIdControlMedidas(ControlMedidas idControlMedidas) {
        this.idControlMedidas = idControlMedidas;
    }

    public ControlMedidasParametro getIdControlMedidasParametro() {
        return idControlMedidasParametro;
    }

    public void setIdControlMedidasParametro(ControlMedidasParametro idControlMedidasParametro) {
        this.idControlMedidasParametro = idControlMedidasParametro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControlMedidasTalla != null ? idControlMedidasTalla.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlMedidasTalla)) {
            return false;
        }
        ControlMedidasTalla other = (ControlMedidasTalla) object;
        if ((this.idControlMedidasTalla == null && other.idControlMedidasTalla != null) || (this.idControlMedidasTalla != null && !this.idControlMedidasTalla.equals(other.idControlMedidasTalla))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ControlMedidasTalla[ idControlMedidasTalla=" + idControlMedidasTalla + " ]";
    }
    
}
