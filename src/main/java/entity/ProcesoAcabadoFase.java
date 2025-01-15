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
@Table(name = "proceso_acabado_fase")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProcesoAcabadoFase.findAll", query = "SELECT p FROM ProcesoAcabadoFase p"),
    @NamedQuery(name = "ProcesoAcabadoFase.findByIdProcesoAcabadoFase", query = "SELECT p FROM ProcesoAcabadoFase p WHERE p.idProcesoAcabadoFase = :idProcesoAcabadoFase"),
    @NamedQuery(name = "ProcesoAcabadoFase.findByOperacion", query = "SELECT p FROM ProcesoAcabadoFase p WHERE p.operacion = :operacion"),
    @NamedQuery(name = "ProcesoAcabadoFase.findByAgujaCantidad", query = "SELECT p FROM ProcesoAcabadoFase p WHERE p.agujaCantidad = :agujaCantidad"),
    @NamedQuery(name = "ProcesoAcabadoFase.findByPuntadaPulgada", query = "SELECT p FROM ProcesoAcabadoFase p WHERE p.puntadaPulgada = :puntadaPulgada"),
    @NamedQuery(name = "ProcesoAcabadoFase.findByIdProcesoAcabado", query = "SELECT p FROM ProcesoAcabadoFase p WHERE p.idProcesoAcabado = :idProcesoAcabado"),
    @NamedQuery(name = "ProcesoAcabadoFase.findByIdEtapa", query = "SELECT p FROM ProcesoAcabadoFase p WHERE p.idProcesoAcabadoEtapa = :idEtapa"
            + " AND p.idProcesoAcabado = :idProcesoAcabado  ORDER BY p.orden")})
public class ProcesoAcabadoFase implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROCESO_ACABADO_FASE")
    private Integer idProcesoAcabadoFase;
    @Size(max = 1000)
    @Column(name = "OPERACION")
    private String operacion;
    @Size(max = 20)
    @Column(name = "AGUJA_CANTIDAD")
    private String agujaCantidad;
    @Column(name = "ORDEN")
    private Integer orden;
    @Size(max = 20)
    @Column(name = "PUNTADA_PULGADA")
    private String puntadaPulgada;
    @JoinColumn(name = "ID_PROCESO_ACABADO", referencedColumnName = "ID_PROCESO_ACABADO")
    @ManyToOne
    private ProcesoAcabado idProcesoAcabado;
    @JoinColumn(name = "ID_PROCESO_ACABADO_ETAPA", referencedColumnName = "ID_PROCESO_ACABADO_ETAPA")
    @ManyToOne
    private ProcesoAcabadoEtapa idProcesoAcabadoEtapa;
    @JoinColumn(name = "ID_AGUJA", referencedColumnName = "ID_AGUJA")
    @ManyToOne
    private Aguja idAguja;
    @JoinColumn(name = "ID_MAQUINA", referencedColumnName = "ID_MAQUINA_CONFECICON")
    @ManyToOne
    private MaquinaConfeccion idMaquina;
    @JoinColumn(name = "ID_MATERIAL", referencedColumnName = "ID_MATERIAL_ESTILO")
    @ManyToOne
    private MaterialEstilo idMaterial;
    @Column(name = "NEGRILLA")
    private Boolean negrilla;

    public ProcesoAcabadoFase() {
    }

    public ProcesoAcabadoFase(Integer idProcesoAcabadoFase) {
        this.idProcesoAcabadoFase = idProcesoAcabadoFase;
    }

    public Aguja getIdAguja() {
        return idAguja;
    }

    public Boolean getNegrilla() {
        return negrilla;
    }

    public void setNegrilla(Boolean negrilla) {
        this.negrilla = negrilla;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
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

    public MaterialEstilo getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(MaterialEstilo idMaterial) {
        this.idMaterial = idMaterial;
    }

    public Integer getIdProcesoAcabadoFase() {
        return idProcesoAcabadoFase;
    }

    public void setIdProcesoAcabadoFase(Integer idProcesoAcabadoFase) {
        this.idProcesoAcabadoFase = idProcesoAcabadoFase;
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

    public ProcesoAcabado getIdProcesoAcabado() {
        return idProcesoAcabado;
    }

    public void setIdProcesoAcabado(ProcesoAcabado idProcesoAcabado) {
        this.idProcesoAcabado = idProcesoAcabado;
    }

    public ProcesoAcabadoEtapa getIdProcesoAcabadoEtapa() {
        return idProcesoAcabadoEtapa;
    }

    public void setIdProcesoAcabadoEtapa(ProcesoAcabadoEtapa idProcesoAcabadoEtapa) {
        this.idProcesoAcabadoEtapa = idProcesoAcabadoEtapa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcesoAcabadoFase != null ? idProcesoAcabadoFase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProcesoAcabadoFase)) {
            return false;
        }
        ProcesoAcabadoFase other = (ProcesoAcabadoFase) object;
        if ((this.idProcesoAcabadoFase == null && other.idProcesoAcabadoFase != null) || (this.idProcesoAcabadoFase != null && !this.idProcesoAcabadoFase.equals(other.idProcesoAcabadoFase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ProcesoAcabadoFase[ idProcesoAcabadoFase=" + idProcesoAcabadoFase + " ]";
    }

}
