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
@Table(name = "composicion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Composicion.findAll", query = "SELECT c FROM Composicion c"),
    @NamedQuery(name = "Composicion.findByIdComposicion", query = "SELECT c FROM Composicion c WHERE c.idComposicion = :idComposicion"),
    @NamedQuery(name = "Composicion.findByNombre", query = "SELECT c FROM Composicion c WHERE c.nombre = :nombre")})
public class Composicion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COMPOSICION")
    private Integer idComposicion;
    @Size(max = 150)
    @Column(name = "NOMBRE")
    private String nombre;

    public Composicion() {
    }

    public Composicion(Integer idComposicion) {
        this.idComposicion = idComposicion;
    }

    public Integer getIdComposicion() {
        return idComposicion;
    }

    public void setIdComposicion(Integer idComposicion) {
        this.idComposicion = idComposicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idComposicion != null ? idComposicion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Composicion)) {
            return false;
        }
        Composicion other = (Composicion) object;
        if ((this.idComposicion == null && other.idComposicion != null) || (this.idComposicion != null && !this.idComposicion.equals(other.idComposicion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Composicion[ idComposicion=" + idComposicion + " ]";
    }
    
}
