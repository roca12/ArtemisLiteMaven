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
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "temario")
@NamedQueries({
    @NamedQuery(name = "Temario.findAll", query = "SELECT t FROM Temario t"),
    @NamedQuery(name = "Temario.findById", query = "SELECT t FROM Temario t WHERE t.id = :id"),
    @NamedQuery(name = "Temario.findBySupergrupo", query = "SELECT t FROM Temario t WHERE t.supergrupo = :supergrupo"),
    @NamedQuery(name = "Temario.findByTema", query = "SELECT t FROM Temario t WHERE t.tema = :tema"),
    @NamedQuery(name = "Temario.findByComplejidadTiempo", query = "SELECT t FROM Temario t WHERE t.complejidadTiempo = :complejidadTiempo"),
    @NamedQuery(name = "Temario.findByOrden", query = "SELECT t FROM Temario t WHERE t.orden = :orden"),
    @NamedQuery(name = "Temario.findBySuborden", query = "SELECT t FROM Temario t WHERE t.suborden = :suborden"),
    @NamedQuery(name = "Temario.findByFechaCreacion", query = "SELECT t FROM Temario t WHERE t.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Temario.findByFechaModificacion", query = "SELECT t FROM Temario t WHERE t.fechaModificacion = :fechaModificacion")})
public class Temario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "supergrupo")
    private String supergrupo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tema")
    private String tema;
    @Lob
    @Size(max = 65535)
    @Column(name = "texto")
    private String texto;
    @Size(max = 255)
    @Column(name = "complejidad_tiempo")
    private String complejidadTiempo;
    @Lob
    @Size(max = 65535)
    @Column(name = "java")
    private String java;
    @Lob
    @Size(max = 65535)
    @Column(name = "cpp")
    private String cpp;
    @Lob
    @Size(max = 65535)
    @Column(name = "py")
    private String py;
    @Column(name = "orden")
    private Integer orden;
    @Column(name = "suborden")
    private Integer suborden;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    public Temario() {
    }

    public Temario(Integer id) {
        this.id = id;
    }

    public Temario(Integer id, String supergrupo, String tema) {
        this.id = id;
        this.supergrupo = supergrupo;
        this.tema = tema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSupergrupo() {
        return supergrupo;
    }

    public void setSupergrupo(String supergrupo) {
        this.supergrupo = supergrupo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getComplejidadTiempo() {
        return complejidadTiempo;
    }

    public void setComplejidadTiempo(String complejidadTiempo) {
        this.complejidadTiempo = complejidadTiempo;
    }

    public String getJava() {
        return java;
    }

    public void setJava(String java) {
        this.java = java;
    }

    public String getCpp() {
        return cpp;
    }

    public void setCpp(String cpp) {
        this.cpp = cpp;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public Integer getSuborden() {
        return suborden;
    }

    public void setSuborden(Integer suborden) {
        this.suborden = suborden;
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
        if (!(object instanceof Temario)) {
            return false;
        }
        Temario other = (Temario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.Temario[ id=" + id + " ]";
    }
    
}
