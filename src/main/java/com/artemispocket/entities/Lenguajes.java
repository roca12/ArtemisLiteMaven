/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "lenguajes")
@NamedQueries({
    @NamedQuery(name = "Lenguajes.findAll", query = "SELECT l FROM Lenguajes l"),
    @NamedQuery(name = "Lenguajes.findById", query = "SELECT l FROM Lenguajes l WHERE l.id = :id"),
    @NamedQuery(name = "Lenguajes.findByNombre", query = "SELECT l FROM Lenguajes l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "Lenguajes.findByExtension", query = "SELECT l FROM Lenguajes l WHERE l.extension = :extension"),
    @NamedQuery(name = "Lenguajes.findByUrldocs", query = "SELECT l FROM Lenguajes l WHERE l.urldocs = :urldocs"),
    @NamedQuery(name = "Lenguajes.findByUrllogo", query = "SELECT l FROM Lenguajes l WHERE l.urllogo = :urllogo"),
    @NamedQuery(name = "Lenguajes.findByNumerofavoritos", query = "SELECT l FROM Lenguajes l WHERE l.numerofavoritos = :numerofavoritos"),
    @NamedQuery(name = "Lenguajes.findByFechaCreacion", query = "SELECT l FROM Lenguajes l WHERE l.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Lenguajes.findByFechaModificacion", query = "SELECT l FROM Lenguajes l WHERE l.fechaModificacion = :fechaModificacion")})
public class Lenguajes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "extension")
    private String extension;
    @Size(max = 200)
    @Column(name = "urldocs")
    private String urldocs;
    @Size(max = 200)
    @Column(name = "urllogo")
    private String urllogo;
    @Column(name = "numerofavoritos")
    private Integer numerofavoritos;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Size(max = 45)
    @Column(name = "fecha_modificacion")
    private String fechaModificacion;
    @OneToMany(mappedBy = "lenguajes")
    private List<Enviosuva> enviosuvaList;

    public Lenguajes() {
    }

    public Lenguajes(Integer id) {
        this.id = id;
    }

    public Lenguajes(Integer id, String nombre, String extension) {
        this.id = id;
        this.nombre = nombre;
        this.extension = extension;
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getUrldocs() {
        return urldocs;
    }

    public void setUrldocs(String urldocs) {
        this.urldocs = urldocs;
    }

    public String getUrllogo() {
        return urllogo;
    }

    public void setUrllogo(String urllogo) {
        this.urllogo = urllogo;
    }

    public Integer getNumerofavoritos() {
        return numerofavoritos;
    }

    public void setNumerofavoritos(Integer numerofavoritos) {
        this.numerofavoritos = numerofavoritos;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<Enviosuva> getEnviosuvaList() {
        return enviosuvaList;
    }

    public void setEnviosuvaList(List<Enviosuva> enviosuvaList) {
        this.enviosuvaList = enviosuvaList;
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
        if (!(object instanceof Lenguajes)) {
            return false;
        }
        Lenguajes other = (Lenguajes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.Lenguajes[ id=" + id + " ]";
    }
    
}
