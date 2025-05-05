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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Samuel P.
 */
@Entity
@Table(name = "seleccion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seleccion.findAll", query = "SELECT s FROM Seleccion s"),
    @NamedQuery(name = "Seleccion.findByIdSeleccion", query = "SELECT s FROM Seleccion s WHERE s.idSeleccion = :idSeleccion"),
    @NamedQuery(name = "Seleccion.findByCreado", query = "SELECT s FROM Seleccion s WHERE s.creado = :creado"),
    @NamedQuery(name = "Seleccion.findByActualizado", query = "SELECT s FROM Seleccion s WHERE s.actualizado = :actualizado"),
    @NamedQuery(name = "Seleccion.findByIdTejidoCirculares", query = "SELECT s FROM Seleccion s WHERE s.idTejidoCirculares = :idTejidoCirculares"),
})
public class Seleccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SELECCION")
    private Integer idSeleccion;
    @Column(name = "CREADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    @Column(name = "ACTUALIZADO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizado;
    @JoinColumn(name = "ID_SELECCION_ALIMENTADOR_1", referencedColumnName = "ID_SELECCION_ALIMENTADOR")
    @ManyToOne
    private SeleccionAlimentador idSeleccionAlimentador1;
    @JoinColumn(name = "ID_SELECCION_ALIMENTADOR_2", referencedColumnName = "ID_SELECCION_ALIMENTADOR")
    @ManyToOne
    private SeleccionAlimentador idSeleccionAlimentador2;
    @JoinColumn(name = "ID_SELECCION_ALIMENTADOR_3", referencedColumnName = "ID_SELECCION_ALIMENTADOR")
    @ManyToOne
    private SeleccionAlimentador idSeleccionAlimentador3;
    @JoinColumn(name = "ID_SELECCION_ALIMENTADOR_4", referencedColumnName = "ID_SELECCION_ALIMENTADOR")
    @ManyToOne
    private SeleccionAlimentador idSeleccionAlimentador4;
    @JoinColumn(name = "ID_SELECCION_PARAMETRO", referencedColumnName = "ID_SELECCION_PARAMETRO")
    @ManyToOne
    private SeleccionParametro idSeleccionParametro;
    @JoinColumn(name = "ID_CREADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idCreado;
    @JoinColumn(name = "ID_ACTUALIZADO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuario idActualizado;
    @JoinColumn(name = "ID_TEJIDO_CIRCULARES", referencedColumnName = "ID_TEJIDO_CIRCULARES")
    @ManyToOne
    private TejidoCirculares idTejidoCirculares;

    public Seleccion() {
    }

    public Seleccion(Integer idSeleccion) {
        this.idSeleccion = idSeleccion;
    }

    public Integer getIdSeleccion() {
        return idSeleccion;
    }

    public void setIdSeleccion(Integer idSeleccion) {
        this.idSeleccion = idSeleccion;
    }

    public Date getCreado() {
        return creado;
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

    public TejidoCirculares getIdTejidoCirculares() {
        return idTejidoCirculares;
    }

    public void setIdTejidoCirculares(TejidoCirculares idTejidoCirculares) {
        this.idTejidoCirculares = idTejidoCirculares;
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

    public SeleccionAlimentador getIdSeleccionAlimentador1() {
        return idSeleccionAlimentador1;
    }

    public void setIdSeleccionAlimentador1(SeleccionAlimentador idSeleccionAlimentador1) {
        this.idSeleccionAlimentador1 = idSeleccionAlimentador1;
    }

    public SeleccionAlimentador getIdSeleccionAlimentador2() {
        return idSeleccionAlimentador2;
    }

    public void setIdSeleccionAlimentador2(SeleccionAlimentador idSeleccionAlimentador2) {
        this.idSeleccionAlimentador2 = idSeleccionAlimentador2;
    }

    public SeleccionAlimentador getIdSeleccionAlimentador3() {
        return idSeleccionAlimentador3;
    }

    public void setIdSeleccionAlimentador3(SeleccionAlimentador idSeleccionAlimentador3) {
        this.idSeleccionAlimentador3 = idSeleccionAlimentador3;
    }

    public SeleccionAlimentador getIdSeleccionAlimentador4() {
        return idSeleccionAlimentador4;
    }

    public void setIdSeleccionAlimentador4(SeleccionAlimentador idSeleccionAlimentador4) {
        this.idSeleccionAlimentador4 = idSeleccionAlimentador4;
    }


    public SeleccionParametro getIdSeleccionParametro() {
        return idSeleccionParametro;
    }

    public void setIdSeleccionParametro(SeleccionParametro idSeleccionParametro) {
        this.idSeleccionParametro = idSeleccionParametro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSeleccion != null ? idSeleccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seleccion)) {
            return false;
        }
        Seleccion other = (Seleccion) object;
        if ((this.idSeleccion == null && other.idSeleccion != null) || (this.idSeleccion != null && !this.idSeleccion.equals(other.idSeleccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Seleccion[ idSeleccion=" + idSeleccion + " ]";
    }
    
}
