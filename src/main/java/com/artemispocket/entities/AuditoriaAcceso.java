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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
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
@Table(name = "auditoria_acceso")
@NamedQueries({
    @NamedQuery(name = "AuditoriaAcceso.findAll", query = "SELECT a FROM AuditoriaAcceso a"),
    @NamedQuery(name = "AuditoriaAcceso.findById", query = "SELECT a FROM AuditoriaAcceso a WHERE a.id = :id"),
    @NamedQuery(name = "AuditoriaAcceso.findByFecha", query = "SELECT a FROM AuditoriaAcceso a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "AuditoriaAcceso.findByIp", query = "SELECT a FROM AuditoriaAcceso a WHERE a.ip = :ip"),
    @NamedQuery(name = "AuditoriaAcceso.findByCorrecto", query = "SELECT a FROM AuditoriaAcceso a WHERE a.correcto = :correcto"),
    @NamedQuery(name = "AuditoriaAcceso.findByFechaCreacion", query = "SELECT a FROM AuditoriaAcceso a WHERE a.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "AuditoriaAcceso.findByFechaModificacion", query = "SELECT a FROM AuditoriaAcceso a WHERE a.fechaModificacion = :fechaModificacion")})
public class AuditoriaAcceso implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 20)
    @Column(name = "ip")
    private String ip;
    @Column(name = "correcto")
    private Short correcto;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumns({
        @JoinColumn(name = "usuario", referencedColumnName = "id"),
        @JoinColumn(name = "usuario", referencedColumnName = "id")})
    @ManyToOne
    private Usuarios usuarios;

    public AuditoriaAcceso() {
    }

    public AuditoriaAcceso(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Short getCorrecto() {
        return correcto;
    }

    public void setCorrecto(Short correcto) {
        this.correcto = correcto;
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

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
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
        if (!(object instanceof AuditoriaAcceso)) {
            return false;
        }
        AuditoriaAcceso other = (AuditoriaAcceso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.AuditoriaAcceso[ id=" + id + " ]";
    }
    
}
