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
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "usuarios")
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findById", query = "SELECT u FROM Usuarios u WHERE u.id = :id"),
    @NamedQuery(name = "Usuarios.findByUsername", query = "SELECT u FROM Usuarios u WHERE u.username = :username"),
    @NamedQuery(name = "Usuarios.findByPassword", query = "SELECT u FROM Usuarios u WHERE u.password = :password"),
    @NamedQuery(name = "Usuarios.findByNombres", query = "SELECT u FROM Usuarios u WHERE u.nombres = :nombres"),
    @NamedQuery(name = "Usuarios.findByApellidos", query = "SELECT u FROM Usuarios u WHERE u.apellidos = :apellidos"),
    @NamedQuery(name = "Usuarios.findByCorreo", query = "SELECT u FROM Usuarios u WHERE u.correo = :correo"),
    @NamedQuery(name = "Usuarios.findBySemestre", query = "SELECT u FROM Usuarios u WHERE u.semestre = :semestre"),
    @NamedQuery(name = "Usuarios.findByCarrera", query = "SELECT u FROM Usuarios u WHERE u.carrera = :carrera"),
    @NamedQuery(name = "Usuarios.findByUvauser", query = "SELECT u FROM Usuarios u WHERE u.uvauser = :uvauser"),
    @NamedQuery(name = "Usuarios.findByCodechefuser", query = "SELECT u FROM Usuarios u WHERE u.codechefuser = :codechefuser"),
    @NamedQuery(name = "Usuarios.findByCodeforcesuser", query = "SELECT u FROM Usuarios u WHERE u.codeforcesuser = :codeforcesuser"),
    @NamedQuery(name = "Usuarios.findBySpojuser", query = "SELECT u FROM Usuarios u WHERE u.spojuser = :spojuser"),
    @NamedQuery(name = "Usuarios.findByLenguajepeferido", query = "SELECT u FROM Usuarios u WHERE u.lenguajepeferido = :lenguajepeferido"),
    @NamedQuery(name = "Usuarios.findByFechaCreacion", query = "SELECT u FROM Usuarios u WHERE u.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Usuarios.findByFechaModificacion", query = "SELECT u FROM Usuarios u WHERE u.fechaModificacion = :fechaModificacion")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellidos")
    private String apellidos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "correo")
    private String correo;
    @Column(name = "semestre")
    private Integer semestre;
    @Size(max = 60)
    @Column(name = "carrera")
    private String carrera;
    @Size(max = 45)
    @Column(name = "uvauser")
    private String uvauser;
    @Size(max = 45)
    @Column(name = "codechefuser")
    private String codechefuser;
    @Size(max = 45)
    @Column(name = "codeforcesuser")
    private String codeforcesuser;
    @Size(max = 45)
    @Column(name = "spojuser")
    private String spojuser;
    @Lob
    @Size(max = 65535)
    @Column(name = "biografia")
    private String biografia;
    @Size(max = 45)
    @Column(name = "lenguajepeferido")
    private String lenguajepeferido;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;
    @OneToMany(mappedBy = "usuarios")
    private List<AuditoriaAcceso> auditoriaAccesoList;
    @JoinColumns({
        @JoinColumn(name = "ciudad", referencedColumnName = "idCiudades"),
        @JoinColumn(name = "ciudad", referencedColumnName = "idCiudades")})
    @ManyToOne
    private Ciudades ciudades;
    @JoinColumns({
        @JoinColumn(name = "pais", referencedColumnName = "Codigo"),
        @JoinColumn(name = "pais", referencedColumnName = "Codigo")})
    @ManyToOne
    private Paises paises;
    @JoinColumns({
        @JoinColumn(name = "rango", referencedColumnName = "id"),
        @JoinColumn(name = "rango", referencedColumnName = "id")})
    @ManyToOne(optional = false)
    private Rangos rangos;
    @JoinColumns({
        @JoinColumn(name = "universidad", referencedColumnName = "id"),
        @JoinColumn(name = "universidad", referencedColumnName = "id")})
    @ManyToOne
    private Universidades universidades;
    @OneToMany(mappedBy = "usuarios")
    private List<Feed> feedList;
    @OneToMany(mappedBy = "usuarios")
    private List<Comentarios> comentariosList;
    @OneToMany(mappedBy = "usuarios")
    private List<Enviosuva> enviosuvaList;

    public Usuarios() {
    }

    public Usuarios(Integer id) {
        this.id = id;
    }

    public Usuarios(Integer id, String username, String password, String nombres, String apellidos, String correo) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public String getUvauser() {
        return uvauser;
    }

    public void setUvauser(String uvauser) {
        this.uvauser = uvauser;
    }

    public String getCodechefuser() {
        return codechefuser;
    }

    public void setCodechefuser(String codechefuser) {
        this.codechefuser = codechefuser;
    }

    public String getCodeforcesuser() {
        return codeforcesuser;
    }

    public void setCodeforcesuser(String codeforcesuser) {
        this.codeforcesuser = codeforcesuser;
    }

    public String getSpojuser() {
        return spojuser;
    }

    public void setSpojuser(String spojuser) {
        this.spojuser = spojuser;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getLenguajepeferido() {
        return lenguajepeferido;
    }

    public void setLenguajepeferido(String lenguajepeferido) {
        this.lenguajepeferido = lenguajepeferido;
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

    public List<AuditoriaAcceso> getAuditoriaAccesoList() {
        return auditoriaAccesoList;
    }

    public void setAuditoriaAccesoList(List<AuditoriaAcceso> auditoriaAccesoList) {
        this.auditoriaAccesoList = auditoriaAccesoList;
    }

    public Ciudades getCiudades() {
        return ciudades;
    }

    public void setCiudades(Ciudades ciudades) {
        this.ciudades = ciudades;
    }

    public Paises getPaises() {
        return paises;
    }

    public void setPaises(Paises paises) {
        this.paises = paises;
    }

    public Rangos getRangos() {
        return rangos;
    }

    public void setRangos(Rangos rangos) {
        this.rangos = rangos;
    }

    public Universidades getUniversidades() {
        return universidades;
    }

    public void setUniversidades(Universidades universidades) {
        this.universidades = universidades;
    }

    public List<Feed> getFeedList() {
        return feedList;
    }

    public void setFeedList(List<Feed> feedList) {
        this.feedList = feedList;
    }

    public List<Comentarios> getComentariosList() {
        return comentariosList;
    }

    public void setComentariosList(List<Comentarios> comentariosList) {
        this.comentariosList = comentariosList;
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
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.artemispocket.entities.Usuarios[ id=" + id + " ]";
    }
    
}
