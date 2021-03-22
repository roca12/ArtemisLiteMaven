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
@Table(name = "ejercicios")
@NamedQueries({
    @NamedQuery(name = "Ejercicios.findAll", query = "SELECT e FROM Ejercicios e"),
    @NamedQuery(name = "Ejercicios.findById", query = "SELECT e FROM Ejercicios e WHERE e.id = :id"),
    @NamedQuery(name = "Ejercicios.findByTitulo", query = "SELECT e FROM Ejercicios e WHERE e.titulo = :titulo"),
    @NamedQuery(name = "Ejercicios.findByJuez", query = "SELECT e FROM Ejercicios e WHERE e.juez = :juez"),
    @NamedQuery(name = "Ejercicios.findByAlias", query = "SELECT e FROM Ejercicios e WHERE e.alias = :alias"),
    @NamedQuery(name = "Ejercicios.findByDificultad", query = "SELECT e FROM Ejercicios e WHERE e.dificultad = :dificultad"),
    @NamedQuery(name = "Ejercicios.findByTema1", query = "SELECT e FROM Ejercicios e WHERE e.tema1 = :tema1"),
    @NamedQuery(name = "Ejercicios.findByTema2", query = "SELECT e FROM Ejercicios e WHERE e.tema2 = :tema2"),
    @NamedQuery(name = "Ejercicios.findByTema3", query = "SELECT e FROM Ejercicios e WHERE e.tema3 = :tema3"),
    @NamedQuery(name = "Ejercicios.findByTema4", query = "SELECT e FROM Ejercicios e WHERE e.tema4 = :tema4"),
    @NamedQuery(name = "Ejercicios.findByUrl", query = "SELECT e FROM Ejercicios e WHERE e.url = :url"),
    @NamedQuery(name = "Ejercicios.findByFechaCreacion", query = "SELECT e FROM Ejercicios e WHERE e.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Ejercicios.findByFechaModificacion", query = "SELECT e FROM Ejercicios e WHERE e.fechaModificacion = :fechaModificacion")})
public class Ejercicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "titulo")
    private String titulo;
    @Size(max = 25)
    @Column(name = "juez")
    private String juez;
    @Size(max = 45)
    @Column(name = "alias")
    private String alias;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dificultad")
    private int dificultad;
    @Size(max = 45)
    @Column(name = "tema_1")
    private String tema1;
    @Size(max = 45)
    @Column(name = "tema_2")
    private String tema2;
    @Size(max = 45)
    @Column(name = "tema_3")
    private String tema3;
    @Size(max = 45)
    @Column(name = "tema_4")
    private String tema4;
    @Size(max = 255)
    @Column(name = "url")
    private String url;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    public Ejercicios() {
    }

    public Ejercicios(Integer id) {
        this.id = id;
    }

    public Ejercicios(Integer id, int dificultad) {
        this.id = id;
        this.dificultad = dificultad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getJuez() {
        return juez;
    }

    public void setJuez(String juez) {
        this.juez = juez;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public String getTema1() {
        return tema1;
    }

    public void setTema1(String tema1) {
        this.tema1 = tema1;
    }

    public String getTema2() {
        return tema2;
    }

    public void setTema2(String tema2) {
        this.tema2 = tema2;
    }

    public String getTema3() {
        return tema3;
    }

    public void setTema3(String tema3) {
        this.tema3 = tema3;
    }

    public String getTema4() {
        return tema4;
    }

    public void setTema4(String tema4) {
        this.tema4 = tema4;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        if (!(object instanceof Ejercicios)) {
            return false;
        }
        Ejercicios other = (Ejercicios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.Ejercicios[ id=" + id + " ]";
    }
    
}
