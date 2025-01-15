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
@Table(name = "talla_etiqueta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TallaEtiqueta.findAll", query = "SELECT t FROM TallaEtiqueta t"),
    @NamedQuery(name = "TallaEtiqueta.findByIdTallaEtiqueta", query = "SELECT t FROM TallaEtiqueta t WHERE t.idTallaEtiqueta = :idTallaEtiqueta"),
    @NamedQuery(name = "TallaEtiqueta.findByNombre", query = "SELECT t FROM TallaEtiqueta t WHERE t.nombre = :nombre")})
public class TallaEtiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TALLA_ETIQUETA")
    private Integer idTallaEtiqueta;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "idTallaEtiqueta")
    private List<Estilo> estiloList;

    public TallaEtiqueta() {
    }

    public TallaEtiqueta(Integer idTallaEtiqueta) {
        this.idTallaEtiqueta = idTallaEtiqueta;
    }

    public Integer getIdTallaEtiqueta() {
        return idTallaEtiqueta;
    }

    public void setIdTallaEtiqueta(Integer idTallaEtiqueta) {
        this.idTallaEtiqueta = idTallaEtiqueta;
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
        hash += (idTallaEtiqueta != null ? idTallaEtiqueta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TallaEtiqueta)) {
            return false;
        }
        TallaEtiqueta other = (TallaEtiqueta) object;
        if ((this.idTallaEtiqueta == null && other.idTallaEtiqueta != null) || (this.idTallaEtiqueta != null && !this.idTallaEtiqueta.equals(other.idTallaEtiqueta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TallaEtiqueta[ idTallaEtiqueta=" + idTallaEtiqueta + " ]";
    }
    
}
