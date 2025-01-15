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
@Table(name = "mercado_objetivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MercadoObjetivo.findAll", query = "SELECT m FROM MercadoObjetivo m"),
    @NamedQuery(name = "MercadoObjetivo.findByIdMercadoObjetivo", query = "SELECT m FROM MercadoObjetivo m WHERE m.idMercadoObjetivo = :idMercadoObjetivo"),
    @NamedQuery(name = "MercadoObjetivo.findByNombre", query = "SELECT m FROM MercadoObjetivo m WHERE m.nombre = :nombre")})
public class MercadoObjetivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MERCADO_OBJETIVO")
    private Integer idMercadoObjetivo;
    @Size(max = 50)
    @Column(name = "NOMBRE")
    private String nombre;

    public MercadoObjetivo() {
    }

    public MercadoObjetivo(Integer idMercadoObjetivo) {
        this.idMercadoObjetivo = idMercadoObjetivo;
    }

    public Integer getIdMercadoObjetivo() {
        return idMercadoObjetivo;
    }

    public void setIdMercadoObjetivo(Integer idMercadoObjetivo) {
        this.idMercadoObjetivo = idMercadoObjetivo;
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
        hash += (idMercadoObjetivo != null ? idMercadoObjetivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MercadoObjetivo)) {
            return false;
        }
        MercadoObjetivo other = (MercadoObjetivo) object;
        if ((this.idMercadoObjetivo == null && other.idMercadoObjetivo != null) || (this.idMercadoObjetivo != null && !this.idMercadoObjetivo.equals(other.idMercadoObjetivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MercadoObjetivo[ idMercadoObjetivo=" + idMercadoObjetivo + " ]";
    }
    
}
