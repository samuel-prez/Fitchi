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
@Table(name = "seleccion_parametro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeleccionParametro.findAll", query = "SELECT s FROM SeleccionParametro s"),
    @NamedQuery(name = "SeleccionParametro.findByIdSeleccionParametro", query = "SELECT s FROM SeleccionParametro s WHERE s.idSeleccionParametro = :idSeleccionParametro"),
    @NamedQuery(name = "SeleccionParametro.findByNombre", query = "SELECT s FROM SeleccionParametro s WHERE s.nombre = :nombre")})
public class SeleccionParametro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SELECCION_PARAMETRO")
    private Integer idSeleccionParametro;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idSeleccionParametro")
    private List<Seleccion> seleccionList;

    public SeleccionParametro() {
    }

    public SeleccionParametro(Integer idSeleccionParametro) {
        this.idSeleccionParametro = idSeleccionParametro;
    }

    public Integer getIdSeleccionParametro() {
        return idSeleccionParametro;
    }

    public void setIdSeleccionParametro(Integer idSeleccionParametro) {
        this.idSeleccionParametro = idSeleccionParametro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Seleccion> getSeleccionList() {
        return seleccionList;
    }

    public void setSeleccionList(List<Seleccion> seleccionList) {
        this.seleccionList = seleccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeleccionParametro != null ? idSeleccionParametro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeleccionParametro)) {
            return false;
        }
        SeleccionParametro other = (SeleccionParametro) object;
        if ((this.idSeleccionParametro == null && other.idSeleccionParametro != null) || (this.idSeleccionParametro != null && !this.idSeleccionParametro.equals(other.idSeleccionParametro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SeleccionParametro[ idSeleccionParametro=" + idSeleccionParametro + " ]";
    }
    
}
