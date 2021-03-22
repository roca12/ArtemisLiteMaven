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
@Table(name = "cert_inscripcion")
@NamedQueries({
    @NamedQuery(name = "CertInscripcion.findAll", query = "SELECT c FROM CertInscripcion c"),
    @NamedQuery(name = "CertInscripcion.findById", query = "SELECT c FROM CertInscripcion c WHERE c.id = :id"),
    @NamedQuery(name = "CertInscripcion.findByNombres", query = "SELECT c FROM CertInscripcion c WHERE c.nombres = :nombres"),
    @NamedQuery(name = "CertInscripcion.findByApellidos", query = "SELECT c FROM CertInscripcion c WHERE c.apellidos = :apellidos"),
    @NamedQuery(name = "CertInscripcion.findByFecha", query = "SELECT c FROM CertInscripcion c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "CertInscripcion.findByFechaCreacion", query = "SELECT c FROM CertInscripcion c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "CertInscripcion.findByFechaModificacion", query = "SELECT c FROM CertInscripcion c WHERE c.fechaModificacion = :fechaModificacion")})
public class CertInscripcion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumns({
        @JoinColumn(name = "institucion", referencedColumnName = "id"),
        @JoinColumn(name = "institucion", referencedColumnName = "id")})
    @ManyToOne
    private Universidades universidades;

    public CertInscripcion() {
    }

    public CertInscripcion(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
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

    public Universidades getUniversidades() {
        return universidades;
    }

    public void setUniversidades(Universidades universidades) {
        this.universidades = universidades;
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
        if (!(object instanceof CertInscripcion)) {
            return false;
        }
        CertInscripcion other = (CertInscripcion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.CertInscripcion[ id=" + id + " ]";
    }
    
}
