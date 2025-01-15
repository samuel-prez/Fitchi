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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samuel P.
 */
@Entity
@Table(name = "color_estilo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ColorEstilo.findAll", query = "SELECT c FROM ColorEstilo c"),
    @NamedQuery(name = "ColorEstilo.findByIdColorEstilo", query = "SELECT c FROM ColorEstilo c WHERE c.idColorEstilo = :idColorEstilo"),
    @NamedQuery(name = "ColorEstilo.findByIdEstilo", query = "SELECT c FROM ColorEstilo c WHERE c.idEstilo = :idEstilo"),})
public class ColorEstilo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_COLOR_ESTILO")
    private Integer idColorEstilo;
    @JoinColumn(name = "ID_COLOR", referencedColumnName = "ID_COLOR")
    @ManyToOne
    private Color idColor;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;

    public ColorEstilo() {
    }

    public ColorEstilo(Integer idColorEstilo) {
        this.idColorEstilo = idColorEstilo;
    }

    public Integer getIdColorEstilo() {
        return idColorEstilo;
    }

    public void setIdColorEstilo(Integer idColorEstilo) {
        this.idColorEstilo = idColorEstilo;
    }

    public Color getIdColor() {
        return idColor;
    }

    public void setIdColor(Color idColor) {
        this.idColor = idColor;
    }

    public Estilo getIdEstilo() {
        return idEstilo;
    }

    public void setIdEstilo(Estilo idEstilo) {
        this.idEstilo = idEstilo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idColorEstilo != null ? idColorEstilo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColorEstilo)) {
            return false;
        }
        ColorEstilo other = (ColorEstilo) object;
        if ((this.idColorEstilo == null && other.idColorEstilo != null) || (this.idColorEstilo != null && !this.idColorEstilo.equals(other.idColorEstilo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ColorEstilo[ idColorEstilo=" + idColorEstilo + " ]";
    }
    
}
