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
@Table(name = "parametro_prenda_estilo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroPrendaEstilo.findAll", query = "SELECT p FROM ParametroPrendaEstilo p"),
    @NamedQuery(name = "ParametroPrendaEstilo.findByIdParametroPrendaEstilo", query = "SELECT p FROM ParametroPrendaEstilo p WHERE p.idParametroPrendaEstilo = :idParametroPrendaEstilo"),
    @NamedQuery(name = "ParametroPrendaEstilo.findByDescripcion", query = "SELECT p FROM ParametroPrendaEstilo p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "ParametroPrendaEstilo.findByIdEstilo", query = "SELECT p FROM ParametroPrendaEstilo p WHERE p.idEstilo = :idEstilo ORDER BY p.orden"),
    @NamedQuery(name = "ParametroPrendaEstilo.findByOrden", query = "SELECT p FROM ParametroPrendaEstilo p WHERE p.orden = :orden")})
public class ParametroPrendaEstilo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PARAMETRO_PRENDA_ESTILO")
    private Integer idParametroPrendaEstilo;
    @Size(max = 500)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Column(name = "ORDEN")
    private Integer orden;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;
    @JoinColumn(name = "ID_PARAMETRO_PRENDA", referencedColumnName = "ID_PARAMETRO_PRENDA")
    @ManyToOne
    private ParametroPrenda idParametroPrenda;

    public ParametroPrendaEstilo() {
    }

    public ParametroPrendaEstilo(Integer idParametroPrendaEstilo) {
        this.idParametroPrendaEstilo = idParametroPrendaEstilo;
    }

    public Integer getIdParametroPrendaEstilo() {
        return idParametroPrendaEstilo;
    }

    public void setIdParametroPrendaEstilo(Integer idParametroPrendaEstilo) {
        this.idParametroPrendaEstilo = idParametroPrendaEstilo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Estilo getIdEstilo() {
        return idEstilo;
    }

    public void setIdEstilo(Estilo idEstilo) {
        this.idEstilo = idEstilo;
    }

    public ParametroPrenda getIdParametroPrenda() {
        return idParametroPrenda;
    }

    public void setIdParametroPrenda(ParametroPrenda idParametroPrenda) {
        this.idParametroPrenda = idParametroPrenda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametroPrendaEstilo != null ? idParametroPrendaEstilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroPrendaEstilo)) {
            return false;
        }
        ParametroPrendaEstilo other = (ParametroPrendaEstilo) object;
        if ((this.idParametroPrendaEstilo == null && other.idParametroPrendaEstilo != null) || (this.idParametroPrendaEstilo != null && !this.idParametroPrendaEstilo.equals(other.idParametroPrendaEstilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParametroPrendaEstilo[ idParametroPrendaEstilo=" + idParametroPrendaEstilo + " ]";
    }
    
}
