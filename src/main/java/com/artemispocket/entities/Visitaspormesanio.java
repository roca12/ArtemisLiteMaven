/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author roca12
 */
@Entity
@Table(name = "visitaspormesanio")
@NamedQueries({
    @NamedQuery(name = "Visitaspormesanio.findAll", query = "SELECT v FROM Visitaspormesanio v"),
    @NamedQuery(name = "Visitaspormesanio.findById", query = "SELECT v FROM Visitaspormesanio v WHERE v.id = :id"),
    @NamedQuery(name = "Visitaspormesanio.findByAnio", query = "SELECT v FROM Visitaspormesanio v WHERE v.anio = :anio"),
    @NamedQuery(name = "Visitaspormesanio.findByEnero", query = "SELECT v FROM Visitaspormesanio v WHERE v.enero = :enero"),
    @NamedQuery(name = "Visitaspormesanio.findByFebrero", query = "SELECT v FROM Visitaspormesanio v WHERE v.febrero = :febrero"),
    @NamedQuery(name = "Visitaspormesanio.findByMarzo", query = "SELECT v FROM Visitaspormesanio v WHERE v.marzo = :marzo"),
    @NamedQuery(name = "Visitaspormesanio.findByAbril", query = "SELECT v FROM Visitaspormesanio v WHERE v.abril = :abril"),
    @NamedQuery(name = "Visitaspormesanio.findByMayo", query = "SELECT v FROM Visitaspormesanio v WHERE v.mayo = :mayo"),
    @NamedQuery(name = "Visitaspormesanio.findByJunio", query = "SELECT v FROM Visitaspormesanio v WHERE v.junio = :junio"),
    @NamedQuery(name = "Visitaspormesanio.findByJulio", query = "SELECT v FROM Visitaspormesanio v WHERE v.julio = :julio"),
    @NamedQuery(name = "Visitaspormesanio.findByAgosto", query = "SELECT v FROM Visitaspormesanio v WHERE v.agosto = :agosto"),
    @NamedQuery(name = "Visitaspormesanio.findBySeptiembre", query = "SELECT v FROM Visitaspormesanio v WHERE v.septiembre = :septiembre"),
    @NamedQuery(name = "Visitaspormesanio.findByOctubre", query = "SELECT v FROM Visitaspormesanio v WHERE v.octubre = :octubre"),
    @NamedQuery(name = "Visitaspormesanio.findByNoviembre", query = "SELECT v FROM Visitaspormesanio v WHERE v.noviembre = :noviembre"),
    @NamedQuery(name = "Visitaspormesanio.findByDiciembre", query = "SELECT v FROM Visitaspormesanio v WHERE v.diciembre = :diciembre"),
    @NamedQuery(name = "Visitaspormesanio.findByFechaCreacion", query = "SELECT v FROM Visitaspormesanio v WHERE v.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Visitaspormesanio.findByFechaModificacion", query = "SELECT v FROM Visitaspormesanio v WHERE v.fechaModificacion = :fechaModificacion")})
public class Visitaspormesanio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "anio")
    private int anio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "enero")
    private int enero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "febrero")
    private int febrero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "marzo")
    private int marzo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "abril")
    private int abril;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mayo")
    private int mayo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "junio")
    private int junio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "julio")
    private int julio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "agosto")
    private int agosto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "septiembre")
    private int septiembre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "octubre")
    private int octubre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "noviembre")
    private int noviembre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "diciembre")
    private int diciembre;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    public Visitaspormesanio() {
    }

    public Visitaspormesanio(Integer id) {
        this.id = id;
    }

    public Visitaspormesanio(Integer id, int anio, int enero, int febrero, int marzo, int abril, int mayo, int junio, int julio, int agosto, int septiembre, int octubre, int noviembre, int diciembre) {
        this.id = id;
        this.anio = anio;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.abril = abril;
        this.mayo = mayo;
        this.junio = junio;
        this.julio = julio;
        this.agosto = agosto;
        this.septiembre = septiembre;
        this.octubre = octubre;
        this.noviembre = noviembre;
        this.diciembre = diciembre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getEnero() {
        return enero;
    }

    public void setEnero(int enero) {
        this.enero = enero;
    }

    public int getFebrero() {
        return febrero;
    }

    public void setFebrero(int febrero) {
        this.febrero = febrero;
    }

    public int getMarzo() {
        return marzo;
    }

    public void setMarzo(int marzo) {
        this.marzo = marzo;
    }

    public int getAbril() {
        return abril;
    }

    public void setAbril(int abril) {
        this.abril = abril;
    }

    public int getMayo() {
        return mayo;
    }

    public void setMayo(int mayo) {
        this.mayo = mayo;
    }

    public int getJunio() {
        return junio;
    }

    public void setJunio(int junio) {
        this.junio = junio;
    }

    public int getJulio() {
        return julio;
    }

    public void setJulio(int julio) {
        this.julio = julio;
    }

    public int getAgosto() {
        return agosto;
    }

    public void setAgosto(int agosto) {
        this.agosto = agosto;
    }

    public int getSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(int septiembre) {
        this.septiembre = septiembre;
    }

    public int getOctubre() {
        return octubre;
    }

    public void setOctubre(int octubre) {
        this.octubre = octubre;
    }

    public int getNoviembre() {
        return noviembre;
    }

    public void setNoviembre(int noviembre) {
        this.noviembre = noviembre;
    }

    public int getDiciembre() {
        return diciembre;
    }

    public void setDiciembre(int diciembre) {
        this.diciembre = diciembre;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Visitaspormesanio)) {
            return false;
        }
        Visitaspormesanio other = (Visitaspormesanio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.Visitaspormesanio[ id=" + id + " ]";
    }
    
}
