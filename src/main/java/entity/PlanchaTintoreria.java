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
@Table(name = "plancha_tintoreria")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanchaTintoreria.findAll", query = "SELECT p FROM PlanchaTintoreria p"),
    @NamedQuery(name = "PlanchaTintoreria.findByIdPlanchaTintoreria", query = "SELECT p FROM PlanchaTintoreria p WHERE p.idPlanchaTintoreria = :idPlanchaTintoreria"),
    @NamedQuery(name = "PlanchaTintoreria.findByTipoPlancha", query = "SELECT p FROM PlanchaTintoreria p WHERE p.tipoPlancha = :tipoPlancha"),
    @NamedQuery(name = "PlanchaTintoreria.findByTipoHorma", query = "SELECT p FROM PlanchaTintoreria p WHERE p.tipoHorma = :tipoHorma"),
    @NamedQuery(name = "PlanchaTintoreria.findByPresion", query = "SELECT p FROM PlanchaTintoreria p WHERE p.presion = :presion"),
    @NamedQuery(name = "PlanchaTintoreria.findByTemperaturaPlancha", query = "SELECT p FROM PlanchaTintoreria p WHERE p.temperaturaPlancha = :temperaturaPlancha"),
    @NamedQuery(name = "PlanchaTintoreria.findByTiempoHormado", query = "SELECT p FROM PlanchaTintoreria p WHERE p.tiempoHormado = :tiempoHormado"),
    @NamedQuery(name = "PlanchaTintoreria.findByTipoSecado", query = "SELECT p FROM PlanchaTintoreria p WHERE p.tipoSecado = :tipoSecado"),
    @NamedQuery(name = "PlanchaTintoreria.findByTiempoSecado", query = "SELECT p FROM PlanchaTintoreria p WHERE p.tiempoSecado = :tiempoSecado"),
    @NamedQuery(name = "PlanchaTintoreria.findByTemperatura", query = "SELECT p FROM PlanchaTintoreria p WHERE p.temperatura = :temperatura"),
    @NamedQuery(name = "PlanchaTintoreria.findByObservaciones", query = "SELECT p FROM PlanchaTintoreria p WHERE p.observaciones = :observaciones"),
    @NamedQuery(name = "PlanchaTintoreria.findByCreado", query = "SELECT p FROM PlanchaTintoreria p WHERE p.creado = :creado"),
    @NamedQuery(name = "PlanchaTintoreria.findByActualizado", query = "SELECT p FROM PlanchaTintoreria p WHERE p.actualizado = :actualizado"),
    @NamedQuery(name = "PlanchaTintoreria.findByIdEstilo", query = "SELECT p FROM PlanchaTintoreria p WHERE p.idEstilo = :idEstilo"),
})
public class PlanchaTintoreria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PLANCHA_TINTORERIA")
    private Integer idPlanchaTintoreria;
    @Size(max = 100)
    @Column(name = "TIPO_PLANCHA")
    private String tipoPlancha;
    @Size(max = 100)
    @Column(name = "TIPO_HORMA")
    private String tipoHorma;
    @Size(max = 30)
    @Column(name = "PRESION")
    private String presion;
    @Size(max = 30)
    @Column(name = "TEMPERATURA_PLANCHA")
    private String temperaturaPlancha;
    @Size(max = 30)
    @Column(name = "VELOCIDAD")
    private String velocidad;
    @Size(max = 30)
    @Column(name = "TIEMPO_HORMADO")
    private String tiempoHormado;
    @Size(max = 100)
    @Column(name = "TIPO_SECADO")
    private String tipoSecado;
    @Size(max = 100)
    @Column(name = "TIEMPO_SECADO")
    private String tiempoSecado;
    @Size(max = 100)
    @Column(name = "TEMPERATURA")
    private String temperatura;
    @Size(max = 1000)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "ACTUALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;
    @JoinColumn(name = "ID_CREADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idCreado;
    @JoinColumn(name = "ID_ACTUALIZADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idActualizado;

    public PlanchaTintoreria() {
    }

    public PlanchaTintoreria(Integer idPlanchaTintoreria) {
        this.idPlanchaTintoreria = idPlanchaTintoreria;
    }

    public Estilo getIdEstilo() {
        return idEstilo;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
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

    public Integer getIdPlanchaTintoreria() {
        return idPlanchaTintoreria;
    }

    public void setIdPlanchaTintoreria(Integer idPlanchaTintoreria) {
        this.idPlanchaTintoreria = idPlanchaTintoreria;
    }

    public String getTipoPlancha() {
        return tipoPlancha;
    }

    public void setTipoPlancha(String tipoPlancha) {
        this.tipoPlancha = tipoPlancha;
    }

    public String getTipoHorma() {
        return tipoHorma;
    }

    public void setTipoHorma(String tipoHorma) {
        this.tipoHorma = tipoHorma;
    }

    public String getPresion() {
        return presion;
    }

    public void setPresion(String presion) {
        this.presion = presion;
    }

    public String getTemperaturaPlancha() {
        return temperaturaPlancha;
    }

    public void setTemperaturaPlancha(String temperaturaPlancha) {
        this.temperaturaPlancha = temperaturaPlancha;
    }

    public String getTiempoHormado() {
        return tiempoHormado;
    }

    public void setTiempoHormado(String tiempoHormado) {
        this.tiempoHormado = tiempoHormado;
    }

    public String getTipoSecado() {
        return tipoSecado;
    }

    public void setTipoSecado(String tipoSecado) {
        this.tipoSecado = tipoSecado;
    }

    public String getTiempoSecado() {
        return tiempoSecado;
    }

    public void setTiempoSecado(String tiempoSecado) {
        this.tiempoSecado = tiempoSecado;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getCreado() {
        return creado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanchaTintoreria != null ? idPlanchaTintoreria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanchaTintoreria)) {
            return false;
        }
        PlanchaTintoreria other = (PlanchaTintoreria) object;
        if ((this.idPlanchaTintoreria == null && other.idPlanchaTintoreria != null) || (this.idPlanchaTintoreria != null && !this.idPlanchaTintoreria.equals(other.idPlanchaTintoreria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.PlanchaTintoreria[ idPlanchaTintoreria=" + idPlanchaTintoreria + " ]";
    }
    
}
