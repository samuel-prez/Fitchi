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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samuel P.
 */
@Entity
@Table(name = "proceso_confeccion_fase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoConfeccionFase.findAll", query = "SELECT p FROM ProcesoConfeccionFase p"),
    @NamedQuery(name = "ProcesoConfeccionFase.findByIdProcesoConfeccionFase", query = "SELECT p FROM ProcesoConfeccionFase p WHERE p.idProcesoConfeccionFase = :idProcesoConfeccionFase"),
    @NamedQuery(name = "ProcesoConfeccionFase.findByOperacion", query = "SELECT p FROM ProcesoConfeccionFase p WHERE p.operacion = :operacion"),
    @NamedQuery(name = "ProcesoConfeccionFase.findByAgujaCantidad", query = "SELECT p FROM ProcesoConfeccionFase p WHERE p.agujaCantidad = :agujaCantidad"),
    @NamedQuery(name = "ProcesoConfeccionFase.findByPuntadaPulgada", query = "SELECT p FROM ProcesoConfeccionFase p WHERE p.puntadaPulgada = :puntadaPulgada"),
    @NamedQuery(name = "ProcesoConfeccionFase.findByIdProcesoConfeccion", query = "SELECT p FROM ProcesoConfeccionFase p WHERE p.idProcesoConfeccion = :idProcesoConfeccion"),
    @NamedQuery(name = "ProcesoConfeccionFase.findByIdEtapa", query = "SELECT p FROM ProcesoConfeccionFase p WHERE p.idProcesoConfeccionEtapa = :idEtapa "
            + "AND p.idProcesoConfeccion = :idProcesoConfeccion ORDER BY p.orden"),
})
public class ProcesoConfeccionFase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROCESO_CONFECCION_FASE")
    private Integer idProcesoConfeccionFase;
    @Size(max = 1000)
    @Column(name = "OPERACION")
    private String operacion;
    @Size(max = 20)
    @Column(name = "AGUJA_CANTIDAD")
    private String agujaCantidad;
    @Size(max = 20)
    @Column(name = "PUNTADA_PULGADA")
    private String puntadaPulgada;
    @Column(name = "ORDEN")
    private Integer orden;
    @JoinColumn(name = "ID_AGUJA", referencedColumnName = "ID_AGUJA")
    @ManyToOne
    private Aguja idAguja;
    @JoinColumn(name = "ID_MAQUINA", referencedColumnName = "ID_MAQUINA_CONFECICON")
    @ManyToOne
    private MaquinaConfeccion idMaquina;
    @JoinColumn(name = "ID_PROCESO_CONFECCION", referencedColumnName = "ID_PROCESO_CONFECCION")
    @ManyToOne
    private ProcesoConfeccion idProcesoConfeccion;
    @JoinColumn(name = "ID_PROCESO_CONFECCION_ETAPA", referencedColumnName = "ID_PROCESO_CONFECCION_ETAPA")
    @ManyToOne
    private ProcesoConfeccionEtapa idProcesoConfeccionEtapa;
    @JoinColumn(name = "ID_MATERIAL", referencedColumnName = "ID_MATERIAL_ESTILO")
    @ManyToOne
    private MaterialEstilo idMaterial;
    @Column(name = "NEGRILLA")
    private Boolean negrilla;

    public ProcesoConfeccionFase() {
    }

    public ProcesoConfeccionFase(Integer idProcesoConfeccionFase) {
        this.idProcesoConfeccionFase = idProcesoConfeccionFase;
    }

    public Integer getIdProcesoConfeccionFase() {
        return idProcesoConfeccionFase;
    }

    public void setIdProcesoConfeccionFase(Integer idProcesoConfeccionFase) {
        this.idProcesoConfeccionFase = idProcesoConfeccionFase;
    }

    public Boolean getNegrilla() {
        return negrilla;
    }

    public void setNegrilla(Boolean negrilla) {
        this.negrilla = negrilla;
    }

    public MaterialEstilo getIdMaterial() {
        return idMaterial;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public void setIdMaterial(MaterialEstilo idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getAgujaCantidad() {
        return agujaCantidad;
    }

    public void setAgujaCantidad(String agujaCantidad) {
        this.agujaCantidad = agujaCantidad;
    }

    public String getPuntadaPulgada() {
        return puntadaPulgada;
    }

    public void setPuntadaPulgada(String puntadaPulgada) {
        this.puntadaPulgada = puntadaPulgada;
    }

    public Aguja getIdAguja() {
        return idAguja;
    }

    public void setIdAguja(Aguja idAguja) {
        this.idAguja = idAguja;
    }

    public MaquinaConfeccion getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(MaquinaConfeccion idMaquina) {
        this.idMaquina = idMaquina;
    }

    public ProcesoConfeccion getIdProcesoConfeccion() {
        return idProcesoConfeccion;
    }

    public void setIdProcesoConfeccion(ProcesoConfeccion idProcesoConfeccion) {
        this.idProcesoConfeccion = idProcesoConfeccion;
    }

    public ProcesoConfeccionEtapa getIdProcesoConfeccionEtapa() {
        return idProcesoConfeccionEtapa;
    }

    public void setIdProcesoConfeccionEtapa(ProcesoConfeccionEtapa idProcesoConfeccionEtapa) {
        this.idProcesoConfeccionEtapa = idProcesoConfeccionEtapa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcesoConfeccionFase != null ? idProcesoConfeccionFase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoConfeccionFase)) {
            return false;
        }
        ProcesoConfeccionFase other = (ProcesoConfeccionFase) object;
        if ((this.idProcesoConfeccionFase == null && other.idProcesoConfeccionFase != null) || (this.idProcesoConfeccionFase != null && !this.idProcesoConfeccionFase.equals(other.idProcesoConfeccionFase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProcesoConfeccionFase[ idProcesoConfeccionFase=" + idProcesoConfeccionFase + " ]";
    }
    
}
