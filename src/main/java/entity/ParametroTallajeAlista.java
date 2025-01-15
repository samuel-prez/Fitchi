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
@Table(name = "parametro_tallaje_alista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroTallajeAlista.findAll", query = "SELECT p FROM ParametroTallajeAlista p"),
    @NamedQuery(name = "ParametroTallajeAlista.findByIdParametroTallajeAlista", query = "SELECT p FROM ParametroTallajeAlista p WHERE p.idParametroTallajeAlista = :idParametroTallajeAlista"),
    @NamedQuery(name = "ParametroTallajeAlista.findByCrudo", query = "SELECT p FROM ParametroTallajeAlista p WHERE p.crudo = :crudo"),
    @NamedQuery(name = "ParametroTallajeAlista.findByPrefijado", query = "SELECT p FROM ParametroTallajeAlista p WHERE p.prefijado = :prefijado"),
    @NamedQuery(name = "ParametroTallajeAlista.findByIdParametroTallajeTalla", query = "SELECT p FROM ParametroTallajeAlista p "
            + "WHERE p.idParametroTallajeTalla = :idParametroTallajeTalla ORDER BY p.orden"),
})
public class ParametroTallajeAlista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PARAMETRO_TALLAJE_ALISTA")
    private Integer idParametroTallajeAlista;
    @Size(max = 20)
    @Column(name = "CRUDO")
    private String crudo;
    @Size(max = 20)
    @Column(name = "PREFIJADO")
    private String prefijado;
    @JoinColumn(name = "ID_PARAMETRO_TALLAJE_TALLA", referencedColumnName = "ID_PARAMETRO_TALLAJE_TALLA")
    @ManyToOne
    private ParametroTallajeTalla idParametroTallajeTalla;
    @JoinColumn(name = "ID_HILO_PARAMETRO", referencedColumnName = "ID_HILO_PARAMETRO")
    @ManyToOne
    private HiloParametro idHiloParametro;
    @Column(name = "ORDEN")
    private Integer orden;

    public ParametroTallajeAlista() {
    }

    public ParametroTallajeAlista(Integer idParametroTallajeAlista) {
        this.idParametroTallajeAlista = idParametroTallajeAlista;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getIdParametroTallajeAlista() {
        return idParametroTallajeAlista;
    }

    public void setIdParametroTallajeAlista(Integer idParametroTallajeAlista) {
        this.idParametroTallajeAlista = idParametroTallajeAlista;
    }

    public String getCrudo() {
        return crudo;
    }

    public void setCrudo(String crudo) {
        this.crudo = crudo;
    }

    public HiloParametro getIdHiloParametro() {
        return idHiloParametro;
    }

    public void setIdHiloParametro(HiloParametro idHiloParametro) {
        this.idHiloParametro = idHiloParametro;
    }

    public String getPrefijado() {
        return prefijado;
    }

    public void setPrefijado(String prefijado) {
        this.prefijado = prefijado;
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
        hash += (idParametroTallajeAlista != null ? idParametroTallajeAlista.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroTallajeAlista)) {
            return false;
        }
        ParametroTallajeAlista other = (ParametroTallajeAlista) object;
        if ((this.idParametroTallajeAlista == null && other.idParametroTallajeAlista != null) || (this.idParametroTallajeAlista != null && !this.idParametroTallajeAlista.equals(other.idParametroTallajeAlista))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParametroTallajeAlista[ idParametroTallajeAlista=" + idParametroTallajeAlista + " ]";
    }
    
}
