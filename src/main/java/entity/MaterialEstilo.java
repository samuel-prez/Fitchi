/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samuel P.
 */
@Entity
@Table(name = "material_estilo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaterialEstilo.findAll", query = "SELECT m FROM MaterialEstilo m"),
    @NamedQuery(name = "MaterialEstilo.findByIdMaterialEstilo", query = "SELECT m FROM MaterialEstilo m WHERE m.idMaterialEstilo = :idMaterialEstilo"),
    @NamedQuery(name = "MaterialEstilo.findByIdEstilo", query = "SELECT m FROM MaterialEstilo m WHERE m.idEstilo = :idEstilo"),
    @NamedQuery(name = "MaterialEstilo.findByIdEstiloAndArea", query = "SELECT m FROM MaterialEstilo m WHERE m.idEstilo = :idEstilo AND m.idArea.nombre = :nombre ORDER BY m.orden"),
    @NamedQuery(name = "MaterialEstilo.findByObservaciones", query = "SELECT m FROM MaterialEstilo m WHERE m.observaciones = :observaciones"),
    @NamedQuery(name = "MaterialEstilo.findByCreado", query = "SELECT m FROM MaterialEstilo m WHERE m.creado = :creado"),
    @NamedQuery(name = "MaterialEstilo.findByIdEstiloAndIdArea", query = "SELECT m FROM MaterialEstilo m WHERE m.idEstilo = :idEstilo AND m.idArea.idArea = :idArea ORDER BY m.orden"),
    @NamedQuery(name = "MaterialEstilo.findByIdEstiloAndIdMaterial", query = "SELECT m FROM MaterialEstilo m WHERE m.idEstilo = :idEstilo AND m.idMaterial = :idMaterial"),
})
public class MaterialEstilo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_MATERIAL_ESTILO")
    private Integer idMaterialEstilo;
    @Size(max = 200)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @JoinColumn(name = "ID_AREA", referencedColumnName = "ID_AREA")
    @ManyToOne
    private Area idArea;
    @JoinColumn(name = "ID_MATERIAL", referencedColumnName = "ID_MATERIAL")
    @ManyToOne
    private Material idMaterial;
    @JoinColumn(name = "ID_CREADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idCreado;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;
    @Column(name = "ORDEN")
    private Integer orden;

    public MaterialEstilo() {
    }

    public MaterialEstilo(Integer idMaterialEstilo) {
        this.idMaterialEstilo = idMaterialEstilo;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getIdMaterialEstilo() {
        return idMaterialEstilo;
    }

    public void setIdMaterialEstilo(Integer idMaterialEstilo) {
        this.idMaterialEstilo = idMaterialEstilo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getIdCreado() {
        return idCreado;
    }

    public void setIdCreado(Usuario idCreado) {
        this.idCreado = idCreado;
    }

    public Estilo getIdEstilo() {
        return idEstilo;
    }

    public void setIdEstilo(Estilo idEstilo) {
        this.idEstilo = idEstilo;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    public Material getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Material idMaterial) {
        this.idMaterial = idMaterial;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaterialEstilo != null ? idMaterialEstilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialEstilo)) {
            return false;
        }
        MaterialEstilo other = (MaterialEstilo) object;
        if ((this.idMaterialEstilo == null && other.idMaterialEstilo != null) || (this.idMaterialEstilo != null && !this.idMaterialEstilo.equals(other.idMaterialEstilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.MaterialEstilo[ idMaterialEstilo=" + idMaterialEstilo + " ]";
    }
    
}
