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
@Table(name = "embalaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Embalaje.findAll", query = "SELECT e FROM Embalaje e"),
    @NamedQuery(name = "Embalaje.findByIdEmbalaje", query = "SELECT e FROM Embalaje e WHERE e.idEmbalaje = :idEmbalaje"),
    @NamedQuery(name = "Embalaje.findByNombre", query = "SELECT e FROM Embalaje e WHERE e.nombre = :nombre")})
public class Embalaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_EMBALAJE")
    private Integer idEmbalaje;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idEmbalaje")
    private List<TejidoCirculares> tejidoCircularesList;

    public Embalaje() {
    }

    public Embalaje(Integer idEmbalaje) {
        this.idEmbalaje = idEmbalaje;
    }

    public Integer getIdEmbalaje() {
        return idEmbalaje;
    }

    public void setIdEmbalaje(Integer idEmbalaje) {
        this.idEmbalaje = idEmbalaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<TejidoCirculares> getTejidoCircularesList() {
        return tejidoCircularesList;
    }

    public void setTejidoCircularesList(List<TejidoCirculares> tejidoCircularesList) {
        this.tejidoCircularesList = tejidoCircularesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmbalaje != null ? idEmbalaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Embalaje)) {
            return false;
        }
        Embalaje other = (Embalaje) object;
        if ((this.idEmbalaje == null && other.idEmbalaje != null) || (this.idEmbalaje != null && !this.idEmbalaje.equals(other.idEmbalaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Embalaje[ idEmbalaje=" + idEmbalaje + " ]";
    }
    
}
