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
@Table(name = "linea_producto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LineaProducto.findAll", query = "SELECT l FROM LineaProducto l"),
    @NamedQuery(name = "LineaProducto.findByIdLineaProducto", query = "SELECT l FROM LineaProducto l WHERE l.idLineaProducto = :idLineaProducto"),
    @NamedQuery(name = "LineaProducto.findByNombre", query = "SELECT l FROM LineaProducto l WHERE l.nombre = :nombre")})
public class LineaProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_LINEA_PRODUCTO")
    private Integer idLineaProducto;
    @Size(max = 30)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idLineaProducto")
    private List<Estilo> estiloList;

    public LineaProducto() {
    }

    public LineaProducto(Integer idLineaProducto) {
        this.idLineaProducto = idLineaProducto;
    }

    public Integer getIdLineaProducto() {
        return idLineaProducto;
    }

    public void setIdLineaProducto(Integer idLineaProducto) {
        this.idLineaProducto = idLineaProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Estilo> getEstiloList() {
        return estiloList;
    }

    public void setEstiloList(List<Estilo> estiloList) {
        this.estiloList = estiloList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLineaProducto != null ? idLineaProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LineaProducto)) {
            return false;
        }
        LineaProducto other = (LineaProducto) object;
        if ((this.idLineaProducto == null && other.idLineaProducto != null) || (this.idLineaProducto != null && !this.idLineaProducto.equals(other.idLineaProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.LineaProducto[ idLineaProducto=" + idLineaProducto + " ]";
    }
    
}
