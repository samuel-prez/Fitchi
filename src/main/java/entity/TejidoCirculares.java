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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Samuel P.
 */
@Entity
@Table(name = "tejido_circulares")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TejidoCirculares.findAll", query = "SELECT t FROM TejidoCirculares t"),
    @NamedQuery(name = "TejidoCirculares.findByIdTejidoCirculares", query = "SELECT t FROM TejidoCirculares t WHERE t.idTejidoCirculares = :idTejidoCirculares"),
    @NamedQuery(name = "TejidoCirculares.findByCantidad", query = "SELECT t FROM TejidoCirculares t WHERE t.cantidad = :cantidad"),
    @NamedQuery(name = "TejidoCirculares.findByTemperatura", query = "SELECT t FROM TejidoCirculares t WHERE t.temperatura = :temperatura"),
    @NamedQuery(name = "TejidoCirculares.findByObservaciones", query = "SELECT t FROM TejidoCirculares t WHERE t.observaciones = :observaciones"),
    @NamedQuery(name = "TejidoCirculares.findByCreado", query = "SELECT t FROM TejidoCirculares t WHERE t.creado = :creado"),
    @NamedQuery(name = "TejidoCirculares.findByActualizado", query = "SELECT t FROM TejidoCirculares t WHERE t.actualizado = :actualizado"),
    @NamedQuery(name = "TejidoCirculares.findByIdEstilo", query = "SELECT t FROM TejidoCirculares t WHERE t.idEstilo = :idEstilo")
})
public class TejidoCirculares implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TEJIDO_CIRCULARES")
    private Integer idTejidoCirculares;
    @Size(max = 50)
    @Column(name = "CANTIDAD")
    private String cantidad;
    @Column(name = "TEMPERATURA")
    private Integer temperatura;
    @Size(max = 500)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "ACTUALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;
    @OneToMany(mappedBy = "idTejidoCirculares")
    private List<HiloTejidoCirculares> hiloTejidoCircularesList;
    @OneToMany(mappedBy = "idTejidoCirculares")
    private List<HiloTension> hiloTensionList;
    @JoinColumn(name = "ID_EMBALAJE", referencedColumnName = "ID_EMBALAJE")
    @ManyToOne
    private Embalaje idEmbalaje;
    @JoinColumn(name = "ID_MAQUINA", referencedColumnName = "ID_MAQUINA")
    @ManyToOne
    private Maquina idMaquina;
    @JoinColumn(name = "ID_PREFIJADO_OBS", referencedColumnName = "ID_PREFIJADO_OBS")
    @ManyToOne
    private PrefijadoObs idPrefijadoObs;
    @OneToMany(mappedBy = "idTejidoCirculares")
    private List<HiloTubular> hiloTubularList;
    @JoinColumn(name = "ID_ESTILO", referencedColumnName = "ID_ESTILO")
    @ManyToOne
    private Estilo idEstilo;
    @JoinColumn(name = "ID_CREADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idCreado;
    @JoinColumn(name = "ID_ACTUALIZADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idActualizado;

    public TejidoCirculares() {
    }

    public TejidoCirculares(Integer idTejidoCirculares) {
        this.idTejidoCirculares = idTejidoCirculares;
    }

    public Integer getIdTejidoCirculares() {
        return idTejidoCirculares;
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

    public void setIdTejidoCirculares(Integer idTejidoCirculares) {
        this.idTejidoCirculares = idTejidoCirculares;
    }

    public String getCantidad() {
        return cantidad;
    }

    public Estilo getIdEstilo() {
        return idEstilo;
    }

    public void setIdEstilo(Estilo idEstilo) {
        this.idEstilo = idEstilo;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
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

    @XmlTransient
    public List<HiloTejidoCirculares> getHiloTejidoCircularesList() {
        return hiloTejidoCircularesList;
    }

    public void setHiloTejidoCircularesList(List<HiloTejidoCirculares> hiloTejidoCircularesList) {
        this.hiloTejidoCircularesList = hiloTejidoCircularesList;
    }

    @XmlTransient
    public List<HiloTension> getHiloTensionList() {
        return hiloTensionList;
    }

    public void setHiloTensionList(List<HiloTension> hiloTensionList) {
        this.hiloTensionList = hiloTensionList;
    }

    public Embalaje getIdEmbalaje() {
        return idEmbalaje;
    }

    public void setIdEmbalaje(Embalaje idEmbalaje) {
        this.idEmbalaje = idEmbalaje;
    }

    public Maquina getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Maquina idMaquina) {
        this.idMaquina = idMaquina;
    }

    public PrefijadoObs getIdPrefijadoObs() {
        return idPrefijadoObs;
    }

    public void setIdPrefijadoObs(PrefijadoObs idPrefijadoObs) {
        this.idPrefijadoObs = idPrefijadoObs;
    }

    @XmlTransient
    public List<HiloTubular> getHiloTubularList() {
        return hiloTubularList;
    }

    public void setHiloTubularList(List<HiloTubular> hiloTubularList) {
        this.hiloTubularList = hiloTubularList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTejidoCirculares != null ? idTejidoCirculares.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TejidoCirculares)) {
            return false;
        }
        TejidoCirculares other = (TejidoCirculares) object;
        if ((this.idTejidoCirculares == null && other.idTejidoCirculares != null) || (this.idTejidoCirculares != null && !this.idTejidoCirculares.equals(other.idTejidoCirculares))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.TejidoCirculares[ idTejidoCirculares=" + idTejidoCirculares + " ]";
    }
    
}
