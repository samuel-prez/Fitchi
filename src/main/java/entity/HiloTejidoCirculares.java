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
@Table(name = "hilo_tejido_circulares")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HiloTejidoCirculares.findAll", query = "SELECT h FROM HiloTejidoCirculares h"),
    @NamedQuery(name = "HiloTejidoCirculares.findByIdHiloTejidoCirculares", query = "SELECT h FROM HiloTejidoCirculares h WHERE h.idHiloTejidoCirculares = :idHiloTejidoCirculares"),
    @NamedQuery(name = "HiloTejidoCirculares.findByGuia1", query = "SELECT h FROM HiloTejidoCirculares h WHERE h.guia1 = :guia1"),
    @NamedQuery(name = "HiloTejidoCirculares.findByGuia2", query = "SELECT h FROM HiloTejidoCirculares h WHERE h.guia2 = :guia2"),
    @NamedQuery(name = "HiloTejidoCirculares.findByGuia3", query = "SELECT h FROM HiloTejidoCirculares h WHERE h.guia3 = :guia3"),
    @NamedQuery(name = "HiloTejidoCirculares.findByGuia4", query = "SELECT h FROM HiloTejidoCirculares h WHERE h.guia4 = :guia4"),
    @NamedQuery(name = "HiloTejidoCirculares.findTejidoCirculares", query = "SELECT h FROM HiloTejidoCirculares h WHERE h.idTejidoCirculares = :idTejidoCirculares"
            + " ORDER BY h.orden"),
})
public class HiloTejidoCirculares implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_HILO_TEJIDO_CIRCULARES")
    private Integer idHiloTejidoCirculares;
    @Column(name = "ORDEN")
    private Integer orden;
    @Size(max = 20)
    @Column(name = "GUIA_1")
    private String guia1;
    @Size(max = 20)
    @Column(name = "GUIA_2")
    private String guia2;
    @Size(max = 20)
    @Column(name = "GUIA_3")
    private String guia3;
    @Size(max = 20)
    @Column(name = "GUIA_4")
    private String guia4;
    @JoinColumn(name = "ID_HILO_PARAMETRO", referencedColumnName = "ID_HILO_PARAMETRO")
    @ManyToOne
    private HiloParametro idHiloParametro;
    @JoinColumn(name = "ID_ALIMENTADOR_1", referencedColumnName = "ID_MATERIAL_ESTILO")
    @ManyToOne
    private MaterialEstilo idAlimentador1;
    @JoinColumn(name = "ID_ALIMENTADOR_2", referencedColumnName = "ID_MATERIAL_ESTILO")
    @ManyToOne
    private MaterialEstilo idAlimentador2;
    @JoinColumn(name = "ID_ALIMENTADOR_3", referencedColumnName = "ID_MATERIAL_ESTILO")
    @ManyToOne
    private MaterialEstilo idAlimentador3;
    @JoinColumn(name = "ID_ALIMENTADOR_4", referencedColumnName = "ID_MATERIAL_ESTILO")
    @ManyToOne
    private MaterialEstilo idAlimentador4;
    @JoinColumn(name = "ID_TEJIDO_CIRCULARES", referencedColumnName = "ID_TEJIDO_CIRCULARES")
    @ManyToOne
    private TejidoCirculares idTejidoCirculares;

    public HiloTejidoCirculares() {
    }

    public HiloTejidoCirculares(Integer idHiloTejidoCirculares) {
        this.idHiloTejidoCirculares = idHiloTejidoCirculares;
    }

    public Integer getIdHiloTejidoCirculares() {
        return idHiloTejidoCirculares;
    }

    public void setIdHiloTejidoCirculares(Integer idHiloTejidoCirculares) {
        this.idHiloTejidoCirculares = idHiloTejidoCirculares;
    }

    public String getGuia1() {
        return guia1;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public MaterialEstilo getIdAlimentador1() {
        return idAlimentador1;
    }

    public void setIdAlimentador1(MaterialEstilo idAlimentador1) {
        this.idAlimentador1 = idAlimentador1;
    }

    public MaterialEstilo getIdAlimentador2() {
        return idAlimentador2;
    }

    public void setIdAlimentador2(MaterialEstilo idAlimentador2) {
        this.idAlimentador2 = idAlimentador2;
    }

    public MaterialEstilo getIdAlimentador3() {
        return idAlimentador3;
    }

    public void setIdAlimentador3(MaterialEstilo idAlimentador3) {
        this.idAlimentador3 = idAlimentador3;
    }

    public MaterialEstilo getIdAlimentador4() {
        return idAlimentador4;
    }

    public void setIdAlimentador4(MaterialEstilo idAlimentador4) {
        this.idAlimentador4 = idAlimentador4;
    }

    public void setGuia1(String guia1) {
        this.guia1 = guia1;
    }

    public String getGuia2() {
        return guia2;
    }

    public void setGuia2(String guia2) {
        this.guia2 = guia2;
    }

    public String getGuia3() {
        return guia3;
    }

    public void setGuia3(String guia3) {
        this.guia3 = guia3;
    }

    public String getGuia4() {
        return guia4;
    }

    public void setGuia4(String guia4) {
        this.guia4 = guia4;
    }

    public HiloParametro getIdHiloParametro() {
        return idHiloParametro;
    }

    public void setIdHiloParametro(HiloParametro idHiloParametro) {
        this.idHiloParametro = idHiloParametro;
    }

    public TejidoCirculares getIdTejidoCirculares() {
        return idTejidoCirculares;
    }

    public void setIdTejidoCirculares(TejidoCirculares idTejidoCirculares) {
        this.idTejidoCirculares = idTejidoCirculares;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHiloTejidoCirculares != null ? idHiloTejidoCirculares.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HiloTejidoCirculares)) {
            return false;
        }
        HiloTejidoCirculares other = (HiloTejidoCirculares) object;
        if ((this.idHiloTejidoCirculares == null && other.idHiloTejidoCirculares != null) || (this.idHiloTejidoCirculares != null && !this.idHiloTejidoCirculares.equals(other.idHiloTejidoCirculares))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HiloTejidoCirculares[ idHiloTejidoCirculares=" + idHiloTejidoCirculares + " ]";
    }
    
}
