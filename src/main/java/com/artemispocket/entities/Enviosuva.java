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
import javax.persistence.Lob;
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
@Table(name = "enviosuva")
@NamedQueries({
    @NamedQuery(name = "Enviosuva.findAll", query = "SELECT e FROM Enviosuva e"),
    @NamedQuery(name = "Enviosuva.findById", query = "SELECT e FROM Enviosuva e WHERE e.id = :id"),
    @NamedQuery(name = "Enviosuva.findByEjercicio", query = "SELECT e FROM Enviosuva e WHERE e.ejercicio = :ejercicio"),
    @NamedQuery(name = "Enviosuva.findBySubmissionid", query = "SELECT e FROM Enviosuva e WHERE e.submissionid = :submissionid"),
    @NamedQuery(name = "Enviosuva.findByFechaCreacion", query = "SELECT e FROM Enviosuva e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Enviosuva.findByFechaModificacion", query = "SELECT e FROM Enviosuva e WHERE e.fechaModificacion = :fechaModificacion")})
public class Enviosuva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "ejercicio")
    private String ejercicio;
    @Lob
    @Size(max = 65535)
    @Column(name = "codigo")
    private String codigo;
    @Size(max = 20)
    @Column(name = "submissionid")
    private String submissionid;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @JoinColumns({
        @JoinColumn(name = "lenguajeid", referencedColumnName = "id"),
        @JoinColumn(name = "lenguajeid", referencedColumnName = "id")})
    @ManyToOne
    private Lenguajes lenguajes;
    @JoinColumns({
        @JoinColumn(name = "userid", referencedColumnName = "id"),
        @JoinColumn(name = "userid", referencedColumnName = "id")})
    @ManyToOne
    private Usuarios usuarios;

    public Enviosuva() {
    }

    public Enviosuva(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSubmissionid() {
        return submissionid;
    }

    public void setSubmissionid(String submissionid) {
        this.submissionid = submissionid;
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

    public Lenguajes getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(Lenguajes lenguajes) {
        this.lenguajes = lenguajes;
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
        if (!(object instanceof Enviosuva)) {
            return false;
        }
        Enviosuva other = (Enviosuva) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.Enviosuva[ id=" + id + " ]";
    }
    
}
