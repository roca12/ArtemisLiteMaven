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
import javax.validation.constraints.Size;

/**
 *
 * @author roca12
 */
@Entity
@Table(name = "uvaurltoid")
@NamedQueries({
    @NamedQuery(name = "Uvaurltoid.findAll", query = "SELECT u FROM Uvaurltoid u"),
    @NamedQuery(name = "Uvaurltoid.findById", query = "SELECT u FROM Uvaurltoid u WHERE u.id = :id"),
    @NamedQuery(name = "Uvaurltoid.findByUrlid", query = "SELECT u FROM Uvaurltoid u WHERE u.urlid = :urlid"),
    @NamedQuery(name = "Uvaurltoid.findByProblemid", query = "SELECT u FROM Uvaurltoid u WHERE u.problemid = :problemid"),
    @NamedQuery(name = "Uvaurltoid.findByProblemname", query = "SELECT u FROM Uvaurltoid u WHERE u.problemname = :problemname"),
    @NamedQuery(name = "Uvaurltoid.findByFechaCreacion", query = "SELECT u FROM Uvaurltoid u WHERE u.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Uvaurltoid.findByFechaModificacion", query = "SELECT u FROM Uvaurltoid u WHERE u.fechaModificacion = :fechaModificacion")})
public class Uvaurltoid implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 5)
    @Column(name = "urlid")
    private String urlid;
    @Size(max = 5)
    @Column(name = "problemid")
    private String problemid;
    @Size(max = 255)
    @Column(name = "problemname")
    private String problemname;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    public Uvaurltoid() {
    }

    public Uvaurltoid(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlid() {
        return urlid;
    }

    public void setUrlid(String urlid) {
        this.urlid = urlid;
    }

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getProblemname() {
        return problemname;
    }

    public void setProblemname(String problemname) {
        this.problemname = problemname;
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
        if (!(object instanceof Uvaurltoid)) {
            return false;
        }
        Uvaurltoid other = (Uvaurltoid) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.Uvaurltoid[ id=" + id + " ]";
    }
    
}
