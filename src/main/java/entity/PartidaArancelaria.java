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
@Table(name = "partida_arancelaria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PartidaArancelaria.findAll", query = "SELECT p FROM PartidaArancelaria p"),
    @NamedQuery(name = "PartidaArancelaria.findByIdPartidaArancelaria", query = "SELECT p FROM PartidaArancelaria p WHERE p.idPartidaArancelaria = :idPartidaArancelaria"),
    @NamedQuery(name = "PartidaArancelaria.findByDescripcion", query = "SELECT p FROM PartidaArancelaria p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "PartidaArancelaria.findByCodigo", query = "SELECT p FROM PartidaArancelaria p WHERE p.codigo = :codigo")})
public class PartidaArancelaria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PARTIDA_ARANCELARIA")
    private Integer idPartidaArancelaria;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 50)
    @Column(name = "CODIGO")
    private String codigo;
    @OneToMany(mappedBy = "idPartidaArancelaria")
    private List<Estilo> estiloList;

    public PartidaArancelaria() {
    }

    public PartidaArancelaria(Integer idPartidaArancelaria) {
        this.idPartidaArancelaria = idPartidaArancelaria;
    }

    public Integer getIdPartidaArancelaria() {
        return idPartidaArancelaria;
    }

    public void setIdPartidaArancelaria(Integer idPartidaArancelaria) {
        this.idPartidaArancelaria = idPartidaArancelaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
        hash += (idPartidaArancelaria != null ? idPartidaArancelaria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PartidaArancelaria)) {
            return false;
        }
        PartidaArancelaria other = (PartidaArancelaria) object;
        if ((this.idPartidaArancelaria == null && other.idPartidaArancelaria != null) || (this.idPartidaArancelaria != null && !this.idPartidaArancelaria.equals(other.idPartidaArancelaria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PartidaArancelaria[ idPartidaArancelaria=" + idPartidaArancelaria + " ]";
    }
    
}
