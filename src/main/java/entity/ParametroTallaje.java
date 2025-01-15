/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Samuel P.
 */
@Entity
@Table(name = "parametro_tallaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ParametroTallaje.findAll", query = "SELECT p FROM ParametroTallaje p"),
    @NamedQuery(name = "ParametroTallaje.findByIdParametrosTallaje", query = "SELECT p FROM ParametroTallaje p WHERE p.idParametrosTallaje = :idParametrosTallaje"),
    @NamedQuery(name = "ParametroTallaje.findByCreado", query = "SELECT p FROM ParametroTallaje p WHERE p.creado = :creado"),
    @NamedQuery(name = "ParametroTallaje.findByActualizado", query = "SELECT p FROM ParametroTallaje p WHERE p.actualizado = :actualizado"),
    @NamedQuery(name = "ParametroTallaje.findByIdEstilo", query = "SELECT p FROM ParametroTallaje p WHERE p.idEstilo = :idEstilo")})
public class ParametroTallaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PARAMETROS_TALLAJE")
    private Integer idParametrosTallaje;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "ACTUALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;
    @OneToMany(mappedBy = "idParametroTallaje")
    private List<ParametroTallajeTalla> parametroTallajeTallaList;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;
    @JoinColumn(name = "ID_CREADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idCreado;
    @JoinColumn(name = "ID_ACTUALIZADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idActualizado;

    public ParametroTallaje() {
    }

    public ParametroTallaje(Integer idParametrosTallaje) {
        this.idParametrosTallaje = idParametrosTallaje;
    }

    public Integer getIdParametrosTallaje() {
        return idParametrosTallaje;
    }

    public void setIdParametrosTallaje(Integer idParametrosTallaje) {
        this.idParametrosTallaje = idParametrosTallaje;
    }

    public Date getCreado() {
        return creado;
    }

    public Estilo getIdEstilo() {
        return idEstilo;
    }

    public void setIdEstilo(Estilo idEstilo) {
        this.idEstilo = idEstilo;
    }

    public Usuario getIdCreado() {
        return idCreado;
    }

    public void setIdCreado(Usuario idCreado) {
        this.idCreado = idCreado;
    }

    public Usuario getIdActualizado() {
        return idActualizado;
    }

    public void setIdActualizado(Usuario idActualizado) {
        this.idActualizado = idActualizado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getActualizado() {
        return actualizado;
    }

    public void setActualizado(Date actualizado) {
        this.actualizado = actualizado;
    }

    @XmlTransient
    public List<ParametroTallajeTalla> getParametroTallajeTallaList() {
        return parametroTallajeTallaList;
    }

    public void setParametroTallajeTallaList(List<ParametroTallajeTalla> parametroTallajeTallaList) {
        this.parametroTallajeTallaList = parametroTallajeTallaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idParametrosTallaje != null ? idParametrosTallaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ParametroTallaje)) {
            return false;
        }
        ParametroTallaje other = (ParametroTallaje) object;
        if ((this.idParametrosTallaje == null && other.idParametrosTallaje != null) || (this.idParametrosTallaje != null && !this.idParametrosTallaje.equals(other.idParametrosTallaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ParametroTallaje[ idParametrosTallaje=" + idParametrosTallaje + " ]";
    }
    
}
