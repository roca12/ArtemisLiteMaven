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
@Table(name = "cert_estadisticas")
@NamedQueries({
    @NamedQuery(name = "CertEstadisticas.findAll", query = "SELECT c FROM CertEstadisticas c"),
    @NamedQuery(name = "CertEstadisticas.findById", query = "SELECT c FROM CertEstadisticas c WHERE c.id = :id"),
    @NamedQuery(name = "CertEstadisticas.findByEjerciciosCorrectos", query = "SELECT c FROM CertEstadisticas c WHERE c.ejerciciosCorrectos = :ejerciciosCorrectos"),
    @NamedQuery(name = "CertEstadisticas.findByNombres", query = "SELECT c FROM CertEstadisticas c WHERE c.nombres = :nombres"),
    @NamedQuery(name = "CertEstadisticas.findByApellidos", query = "SELECT c FROM CertEstadisticas c WHERE c.apellidos = :apellidos"),
    @NamedQuery(name = "CertEstadisticas.findByFecha", query = "SELECT c FROM CertEstadisticas c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CertEstadisticas.findByFechaCreacion", query = "SELECT c FROM CertEstadisticas c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "CertEstadisticas.findByFechaModificacion", query = "SELECT c FROM CertEstadisticas c WHERE c.fechaModificacion = :fechaModificacion")})
public class CertEstadisticas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "ejercicios_correctos")
    private Integer ejerciciosCorrectos;
    @Size(max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Size(max = 45)
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    public CertEstadisticas() {
    }

    public CertEstadisticas(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEjerciciosCorrectos() {
        return ejerciciosCorrectos;
    }

    public void setEjerciciosCorrectos(Integer ejerciciosCorrectos) {
        this.ejerciciosCorrectos = ejerciciosCorrectos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof CertEstadisticas)) {
            return false;
        }
        CertEstadisticas other = (CertEstadisticas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.CertEstadisticas[ id=" + id + " ]";
    }
    
}
