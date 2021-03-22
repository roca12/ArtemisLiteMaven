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
@Table(name = "ciudades")
@NamedQueries({
    @NamedQuery(name = "Ciudades.findAll", query = "SELECT c FROM Ciudades c"),
    @NamedQuery(name = "Ciudades.findByIdCiudades", query = "SELECT c FROM Ciudades c WHERE c.idCiudades = :idCiudades"),
    @NamedQuery(name = "Ciudades.findByPaisesCodigo", query = "SELECT c FROM Ciudades c WHERE c.paisesCodigo = :paisesCodigo"),
    @NamedQuery(name = "Ciudades.findByCiudad", query = "SELECT c FROM Ciudades c WHERE c.ciudad = :ciudad"),
    @NamedQuery(name = "Ciudades.findByFechaCreacion", query = "SELECT c FROM Ciudades c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Ciudades.findByFechaModificacion", query = "SELECT c FROM Ciudades c WHERE c.fechaModificacion = :fechaModificacion")})
public class Ciudades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCiudades")
    private Integer idCiudades;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "Paises_Codigo")
    private String paisesCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Ciudad")
    private String ciudad;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "ciudades")
    private List<Universidades> universidadesList;
    @OneToMany(mappedBy = "ciudades")
    private List<Usuarios> usuariosList;

    public Ciudades() {
    }

    public Ciudades(Integer idCiudades) {
        this.idCiudades = idCiudades;
    }

    public Ciudades(Integer idCiudades, String paisesCodigo, String ciudad) {
        this.idCiudades = idCiudades;
        this.paisesCodigo = paisesCodigo;
        this.ciudad = ciudad;
    }

    public Integer getIdCiudades() {
        return idCiudades;
    }

    public void setIdCiudades(Integer idCiudades) {
        this.idCiudades = idCiudades;
    }

    public String getPaisesCodigo() {
        return paisesCodigo;
    }

    public void setPaisesCodigo(String paisesCodigo) {
        this.paisesCodigo = paisesCodigo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
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

    public List<Universidades> getUniversidadesList() {
        return universidadesList;
    }

    public void setUniversidadesList(List<Universidades> universidadesList) {
        this.universidadesList = universidadesList;
    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCiudades != null ? idCiudades.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ciudades)) {
            return false;
        }
        Ciudades other = (Ciudades) object;
        if ((this.idCiudades == null && other.idCiudades != null) || (this.idCiudades != null && !this.idCiudades.equals(other.idCiudades))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.Ciudades[ idCiudades=" + idCiudades + " ]";
    }
    
}
