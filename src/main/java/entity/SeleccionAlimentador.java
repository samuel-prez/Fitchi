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
@Table(name = "seleccion_alimentador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SeleccionAlimentador.findAll", query = "SELECT s FROM SeleccionAlimentador s"),
    @NamedQuery(name = "SeleccionAlimentador.findByIdSeleccionAlimentador", query = "SELECT s FROM SeleccionAlimentador s WHERE s.idSeleccionAlimentador = :idSeleccionAlimentador"),
    @NamedQuery(name = "SeleccionAlimentador.findByNombre", query = "SELECT s FROM SeleccionAlimentador s WHERE s.nombre = :nombre")})
public class SeleccionAlimentador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SELECCION_ALIMENTADOR")
    private Integer idSeleccionAlimentador;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;


    public SeleccionAlimentador() {
    }

    public SeleccionAlimentador(Integer idSeleccionAlimentador) {
        this.idSeleccionAlimentador = idSeleccionAlimentador;
    }

    public Integer getIdSeleccionAlimentador() {
        return idSeleccionAlimentador;
    }

    public void setIdSeleccionAlimentador(Integer idSeleccionAlimentador) {
        this.idSeleccionAlimentador = idSeleccionAlimentador;
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
        hash += (idSeleccionAlimentador != null ? idSeleccionAlimentador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeleccionAlimentador)) {
            return false;
        }
        SeleccionAlimentador other = (SeleccionAlimentador) object;
        if ((this.idSeleccionAlimentador == null && other.idSeleccionAlimentador != null) || (this.idSeleccionAlimentador != null && !this.idSeleccionAlimentador.equals(other.idSeleccionAlimentador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SeleccionAlimentador[ idSeleccionAlimentador=" + idSeleccionAlimentador + " ]";
    }
    
}
