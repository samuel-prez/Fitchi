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
@Table(name = "guia_tallas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GuiaTallas.findAll", query = "SELECT g FROM GuiaTallas g"),
    @NamedQuery(name = "GuiaTallas.findByIdGuiaTallas", query = "SELECT g FROM GuiaTallas g WHERE g.idGuiaTallas = :idGuiaTallas"),
    @NamedQuery(name = "GuiaTallas.findByNombre", query = "SELECT g FROM GuiaTallas g WHERE g.nombre = :nombre"),
    @NamedQuery(name = "GuiaTallas.findByImagen", query = "SELECT g FROM GuiaTallas g WHERE g.imagen = :imagen")})
public class GuiaTallas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_GUIA_TALLAS")
    private Integer idGuiaTallas;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 200)
    @Column(name = "IMAGEN")
    private String imagen;

    public GuiaTallas() {
    }

    public GuiaTallas(Integer idGuiaTallas) {
        this.idGuiaTallas = idGuiaTallas;
    }

    public Integer getIdGuiaTallas() {
        return idGuiaTallas;
    }

    public void setIdGuiaTallas(Integer idGuiaTallas) {
        this.idGuiaTallas = idGuiaTallas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGuiaTallas != null ? idGuiaTallas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GuiaTallas)) {
            return false;
        }
        GuiaTallas other = (GuiaTallas) object;
        if ((this.idGuiaTallas == null && other.idGuiaTallas != null) || (this.idGuiaTallas != null && !this.idGuiaTallas.equals(other.idGuiaTallas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.GuiaTallas[ idGuiaTallas=" + idGuiaTallas + " ]";
    }
    
}
