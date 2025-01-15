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
@Table(name = "parametro_prenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroPrenda.findAll", query = "SELECT p FROM ParametroPrenda p"),
    @NamedQuery(name = "ParametroPrenda.findByIdParametroPrenda", query = "SELECT p FROM ParametroPrenda p WHERE p.idParametroPrenda = :idParametroPrenda"),
    @NamedQuery(name = "ParametroPrenda.findByNombre", query = "SELECT p FROM ParametroPrenda p WHERE p.nombre = :nombre")})
public class ParametroPrenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PARAMETRO_PRENDA")
    private Integer idParametroPrenda;
    @Size(max = 80)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idParametroPrenda")
    private List<ParametroPrendaEstilo> parametroPrendaEstiloList;

    public ParametroPrenda() {
    }

    public ParametroPrenda(Integer idParametroPrenda) {
        this.idParametroPrenda = idParametroPrenda;
    }

    public Integer getIdParametroPrenda() {
        return idParametroPrenda;
    }

    public void setIdParametroPrenda(Integer idParametroPrenda) {
        this.idParametroPrenda = idParametroPrenda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<ParametroPrendaEstilo> getParametroPrendaEstiloList() {
        return parametroPrendaEstiloList;
    }

    public void setParametroPrendaEstiloList(List<ParametroPrendaEstilo> parametroPrendaEstiloList) {
        this.parametroPrendaEstiloList = parametroPrendaEstiloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametroPrenda != null ? idParametroPrenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroPrenda)) {
            return false;
        }
        ParametroPrenda other = (ParametroPrenda) object;
        if ((this.idParametroPrenda == null && other.idParametroPrenda != null) || (this.idParametroPrenda != null && !this.idParametroPrenda.equals(other.idParametroPrenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParametroPrenda[ idParametroPrenda=" + idParametroPrenda + " ]";
    }
    
}
