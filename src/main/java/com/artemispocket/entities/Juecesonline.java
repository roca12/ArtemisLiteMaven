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
import javax.validation.constraints.Size;

/**
 *
 * @author roca12
 */
@Entity
@Table(name = "juecesonline")
@NamedQueries({
    @NamedQuery(name = "Juecesonline.findAll", query = "SELECT j FROM Juecesonline j"),
    @NamedQuery(name = "Juecesonline.findById", query = "SELECT j FROM Juecesonline j WHERE j.id = :id"),
    @NamedQuery(name = "Juecesonline.findByNombre", query = "SELECT j FROM Juecesonline j WHERE j.nombre = :nombre"),
    @NamedQuery(name = "Juecesonline.findByAcronimo", query = "SELECT j FROM Juecesonline j WHERE j.acronimo = :acronimo"),
    @NamedQuery(name = "Juecesonline.findByUrlpagina", query = "SELECT j FROM Juecesonline j WHERE j.urlpagina = :urlpagina"),
    @NamedQuery(name = "Juecesonline.findByUrllogo", query = "SELECT j FROM Juecesonline j WHERE j.urllogo = :urllogo"),
    @NamedQuery(name = "Juecesonline.findByFechaCreacion", query = "SELECT j FROM Juecesonline j WHERE j.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Juecesonline.findByFechaModificacion", query = "SELECT j FROM Juecesonline j WHERE j.fechaModificacion = :fechaModificacion")})
public class Juecesonline implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "acronimo")
    private String acronimo;
    @Size(max = 100)
    @Column(name = "urlpagina")
    private String urlpagina;
    @Size(max = 100)
    @Column(name = "urllogo")
    private String urllogo;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    public Juecesonline() {
    }

    public Juecesonline(Integer id) {
        this.id = id;
    }

    public Juecesonline(Integer id, String nombre, String acronimo) {
        this.id = id;
        this.nombre = nombre;
        this.acronimo = acronimo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getUrlpagina() {
        return urlpagina;
    }

    public void setUrlpagina(String urlpagina) {
        this.urlpagina = urlpagina;
    }

    public String getUrllogo() {
        return urllogo;
    }

    public void setUrllogo(String urllogo) {
        this.urllogo = urllogo;
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
        if (!(object instanceof Juecesonline)) {
            return false;
        }
        Juecesonline other = (Juecesonline) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.Juecesonline[ id=" + id + " ]";
    }
    
}
