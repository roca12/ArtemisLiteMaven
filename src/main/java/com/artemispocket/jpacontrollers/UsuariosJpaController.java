/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.artemispocket.entities.Ciudades;
import com.artemispocket.entities.Paises;
import com.artemispocket.entities.Rangos;
import com.artemispocket.entities.Universidades;
import com.artemispocket.entities.AuditoriaAcceso;
import java.util.ArrayList;
import java.util.List;
import com.artemispocket.entities.Feed;
import com.artemispocket.entities.Comentarios;
import com.artemispocket.entities.Enviosuva;
import com.artemispocket.entities.Usuarios;
import com.artemispocket.jpacontrollers.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author roca12
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) {
        if (usuarios.getAuditoriaAccesoList() == null) {
            usuarios.setAuditoriaAccesoList(new ArrayList<AuditoriaAcceso>());
        }
        if (usuarios.getFeedList() == null) {
            usuarios.setFeedList(new ArrayList<Feed>());
        }
        if (usuarios.getComentariosList() == null) {
            usuarios.setComentariosList(new ArrayList<Comentarios>());
        }
        if (usuarios.getEnviosuvaList() == null) {
            usuarios.setEnviosuvaList(new ArrayList<Enviosuva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudades ciudades = usuarios.getCiudades();
            if (ciudades != null) {
                ciudades = em.getReference(ciudades.getClass(), ciudades.getIdCiudades());
                usuarios.setCiudades(ciudades);
            }
            Paises paises = usuarios.getPaises();
            if (paises != null) {
                paises = em.getReference(paises.getClass(), paises.getCodigo());
                usuarios.setPaises(paises);
            }
            Rangos rangos = usuarios.getRangos();
            if (rangos != null) {
                rangos = em.getReference(rangos.getClass(), rangos.getId());
                usuarios.setRangos(rangos);
            }
            Universidades universidades = usuarios.getUniversidades();
            if (universidades != null) {
                universidades = em.getReference(universidades.getClass(), universidades.getId());
                usuarios.setUniversidades(universidades);
            }
            List<AuditoriaAcceso> attachedAuditoriaAccesoList = new ArrayList<AuditoriaAcceso>();
            for (AuditoriaAcceso auditoriaAccesoListAuditoriaAccesoToAttach : usuarios.getAuditoriaAccesoList()) {
                auditoriaAccesoListAuditoriaAccesoToAttach = em.getReference(auditoriaAccesoListAuditoriaAccesoToAttach.getClass(), auditoriaAccesoListAuditoriaAccesoToAttach.getId());
                attachedAuditoriaAccesoList.add(auditoriaAccesoListAuditoriaAccesoToAttach);
            }
            usuarios.setAuditoriaAccesoList(attachedAuditoriaAccesoList);
            List<Feed> attachedFeedList = new ArrayList<Feed>();
            for (Feed feedListFeedToAttach : usuarios.getFeedList()) {
                feedListFeedToAttach = em.getReference(feedListFeedToAttach.getClass(), feedListFeedToAttach.getId());
                attachedFeedList.add(feedListFeedToAttach);
            }
            usuarios.setFeedList(attachedFeedList);
            List<Comentarios> attachedComentariosList = new ArrayList<Comentarios>();
            for (Comentarios comentariosListComentariosToAttach : usuarios.getComentariosList()) {
                comentariosListComentariosToAttach = em.getReference(comentariosListComentariosToAttach.getClass(), comentariosListComentariosToAttach.getId());
                attachedComentariosList.add(comentariosListComentariosToAttach);
            }
            usuarios.setComentariosList(attachedComentariosList);
            List<Enviosuva> attachedEnviosuvaList = new ArrayList<Enviosuva>();
            for (Enviosuva enviosuvaListEnviosuvaToAttach : usuarios.getEnviosuvaList()) {
                enviosuvaListEnviosuvaToAttach = em.getReference(enviosuvaListEnviosuvaToAttach.getClass(), enviosuvaListEnviosuvaToAttach.getId());
                attachedEnviosuvaList.add(enviosuvaListEnviosuvaToAttach);
            }
            usuarios.setEnviosuvaList(attachedEnviosuvaList);
            em.persist(usuarios);
            if (ciudades != null) {
                ciudades.getUsuariosList().add(usuarios);
                ciudades = em.merge(ciudades);
            }
            if (paises != null) {
                paises.getUsuariosList().add(usuarios);
                paises = em.merge(paises);
            }
            if (rangos != null) {
                rangos.getUsuariosList().add(usuarios);
                rangos = em.merge(rangos);
            }
            if (universidades != null) {
                universidades.getUsuariosList().add(usuarios);
                universidades = em.merge(universidades);
            }
            for (AuditoriaAcceso auditoriaAccesoListAuditoriaAcceso : usuarios.getAuditoriaAccesoList()) {
                Usuarios oldUsuariosOfAuditoriaAccesoListAuditoriaAcceso = auditoriaAccesoListAuditoriaAcceso.getUsuarios();
                auditoriaAccesoListAuditoriaAcceso.setUsuarios(usuarios);
                auditoriaAccesoListAuditoriaAcceso = em.merge(auditoriaAccesoListAuditoriaAcceso);
                if (oldUsuariosOfAuditoriaAccesoListAuditoriaAcceso != null) {
                    oldUsuariosOfAuditoriaAccesoListAuditoriaAcceso.getAuditoriaAccesoList().remove(auditoriaAccesoListAuditoriaAcceso);
                    oldUsuariosOfAuditoriaAccesoListAuditoriaAcceso = em.merge(oldUsuariosOfAuditoriaAccesoListAuditoriaAcceso);
                }
            }
            for (Feed feedListFeed : usuarios.getFeedList()) {
                Usuarios oldUsuariosOfFeedListFeed = feedListFeed.getUsuarios();
                feedListFeed.setUsuarios(usuarios);
                feedListFeed = em.merge(feedListFeed);
                if (oldUsuariosOfFeedListFeed != null) {
                    oldUsuariosOfFeedListFeed.getFeedList().remove(feedListFeed);
                    oldUsuariosOfFeedListFeed = em.merge(oldUsuariosOfFeedListFeed);
                }
            }
            for (Comentarios comentariosListComentarios : usuarios.getComentariosList()) {
                Usuarios oldUsuariosOfComentariosListComentarios = comentariosListComentarios.getUsuarios();
                comentariosListComentarios.setUsuarios(usuarios);
                comentariosListComentarios = em.merge(comentariosListComentarios);
                if (oldUsuariosOfComentariosListComentarios != null) {
                    oldUsuariosOfComentariosListComentarios.getComentariosList().remove(comentariosListComentarios);
                    oldUsuariosOfComentariosListComentarios = em.merge(oldUsuariosOfComentariosListComentarios);
                }
            }
            for (Enviosuva enviosuvaListEnviosuva : usuarios.getEnviosuvaList()) {
                Usuarios oldUsuariosOfEnviosuvaListEnviosuva = enviosuvaListEnviosuva.getUsuarios();
                enviosuvaListEnviosuva.setUsuarios(usuarios);
                enviosuvaListEnviosuva = em.merge(enviosuvaListEnviosuva);
                if (oldUsuariosOfEnviosuvaListEnviosuva != null) {
                    oldUsuariosOfEnviosuvaListEnviosuva.getEnviosuvaList().remove(enviosuvaListEnviosuva);
                    oldUsuariosOfEnviosuvaListEnviosuva = em.merge(oldUsuariosOfEnviosuvaListEnviosuva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getId());
            Ciudades ciudadesOld = persistentUsuarios.getCiudades();
            Ciudades ciudadesNew = usuarios.getCiudades();
            Paises paisesOld = persistentUsuarios.getPaises();
            Paises paisesNew = usuarios.getPaises();
            Rangos rangosOld = persistentUsuarios.getRangos();
            Rangos rangosNew = usuarios.getRangos();
            Universidades universidadesOld = persistentUsuarios.getUniversidades();
            Universidades universidadesNew = usuarios.getUniversidades();
            List<AuditoriaAcceso> auditoriaAccesoListOld = persistentUsuarios.getAuditoriaAccesoList();
            List<AuditoriaAcceso> auditoriaAccesoListNew = usuarios.getAuditoriaAccesoList();
            List<Feed> feedListOld = persistentUsuarios.getFeedList();
            List<Feed> feedListNew = usuarios.getFeedList();
            List<Comentarios> comentariosListOld = persistentUsuarios.getComentariosList();
            List<Comentarios> comentariosListNew = usuarios.getComentariosList();
            List<Enviosuva> enviosuvaListOld = persistentUsuarios.getEnviosuvaList();
            List<Enviosuva> enviosuvaListNew = usuarios.getEnviosuvaList();
            if (ciudadesNew != null) {
                ciudadesNew = em.getReference(ciudadesNew.getClass(), ciudadesNew.getIdCiudades());
                usuarios.setCiudades(ciudadesNew);
            }
            if (paisesNew != null) {
                paisesNew = em.getReference(paisesNew.getClass(), paisesNew.getCodigo());
                usuarios.setPaises(paisesNew);
            }
            if (rangosNew != null) {
                rangosNew = em.getReference(rangosNew.getClass(), rangosNew.getId());
                usuarios.setRangos(rangosNew);
            }
            if (universidadesNew != null) {
                universidadesNew = em.getReference(universidadesNew.getClass(), universidadesNew.getId());
                usuarios.setUniversidades(universidadesNew);
            }
            List<AuditoriaAcceso> attachedAuditoriaAccesoListNew = new ArrayList<AuditoriaAcceso>();
            for (AuditoriaAcceso auditoriaAccesoListNewAuditoriaAccesoToAttach : auditoriaAccesoListNew) {
                auditoriaAccesoListNewAuditoriaAccesoToAttach = em.getReference(auditoriaAccesoListNewAuditoriaAccesoToAttach.getClass(), auditoriaAccesoListNewAuditoriaAccesoToAttach.getId());
                attachedAuditoriaAccesoListNew.add(auditoriaAccesoListNewAuditoriaAccesoToAttach);
            }
            auditoriaAccesoListNew = attachedAuditoriaAccesoListNew;
            usuarios.setAuditoriaAccesoList(auditoriaAccesoListNew);
            List<Feed> attachedFeedListNew = new ArrayList<Feed>();
            for (Feed feedListNewFeedToAttach : feedListNew) {
                feedListNewFeedToAttach = em.getReference(feedListNewFeedToAttach.getClass(), feedListNewFeedToAttach.getId());
                attachedFeedListNew.add(feedListNewFeedToAttach);
            }
            feedListNew = attachedFeedListNew;
            usuarios.setFeedList(feedListNew);
            List<Comentarios> attachedComentariosListNew = new ArrayList<Comentarios>();
            for (Comentarios comentariosListNewComentariosToAttach : comentariosListNew) {
                comentariosListNewComentariosToAttach = em.getReference(comentariosListNewComentariosToAttach.getClass(), comentariosListNewComentariosToAttach.getId());
                attachedComentariosListNew.add(comentariosListNewComentariosToAttach);
            }
            comentariosListNew = attachedComentariosListNew;
            usuarios.setComentariosList(comentariosListNew);
            List<Enviosuva> attachedEnviosuvaListNew = new ArrayList<Enviosuva>();
            for (Enviosuva enviosuvaListNewEnviosuvaToAttach : enviosuvaListNew) {
                enviosuvaListNewEnviosuvaToAttach = em.getReference(enviosuvaListNewEnviosuvaToAttach.getClass(), enviosuvaListNewEnviosuvaToAttach.getId());
                attachedEnviosuvaListNew.add(enviosuvaListNewEnviosuvaToAttach);
            }
            enviosuvaListNew = attachedEnviosuvaListNew;
            usuarios.setEnviosuvaList(enviosuvaListNew);
            usuarios = em.merge(usuarios);
            if (ciudadesOld != null && !ciudadesOld.equals(ciudadesNew)) {
                ciudadesOld.getUsuariosList().remove(usuarios);
                ciudadesOld = em.merge(ciudadesOld);
            }
            if (ciudadesNew != null && !ciudadesNew.equals(ciudadesOld)) {
                ciudadesNew.getUsuariosList().add(usuarios);
                ciudadesNew = em.merge(ciudadesNew);
            }
            if (paisesOld != null && !paisesOld.equals(paisesNew)) {
                paisesOld.getUsuariosList().remove(usuarios);
                paisesOld = em.merge(paisesOld);
            }
            if (paisesNew != null && !paisesNew.equals(paisesOld)) {
                paisesNew.getUsuariosList().add(usuarios);
                paisesNew = em.merge(paisesNew);
            }
            if (rangosOld != null && !rangosOld.equals(rangosNew)) {
                rangosOld.getUsuariosList().remove(usuarios);
                rangosOld = em.merge(rangosOld);
            }
            if (rangosNew != null && !rangosNew.equals(rangosOld)) {
                rangosNew.getUsuariosList().add(usuarios);
                rangosNew = em.merge(rangosNew);
            }
            if (universidadesOld != null && !universidadesOld.equals(universidadesNew)) {
                universidadesOld.getUsuariosList().remove(usuarios);
                universidadesOld = em.merge(universidadesOld);
            }
            if (universidadesNew != null && !universidadesNew.equals(universidadesOld)) {
                universidadesNew.getUsuariosList().add(usuarios);
                universidadesNew = em.merge(universidadesNew);
            }
            for (AuditoriaAcceso auditoriaAccesoListOldAuditoriaAcceso : auditoriaAccesoListOld) {
                if (!auditoriaAccesoListNew.contains(auditoriaAccesoListOldAuditoriaAcceso)) {
                    auditoriaAccesoListOldAuditoriaAcceso.setUsuarios(null);
                    auditoriaAccesoListOldAuditoriaAcceso = em.merge(auditoriaAccesoListOldAuditoriaAcceso);
                }
            }
            for (AuditoriaAcceso auditoriaAccesoListNewAuditoriaAcceso : auditoriaAccesoListNew) {
                if (!auditoriaAccesoListOld.contains(auditoriaAccesoListNewAuditoriaAcceso)) {
                    Usuarios oldUsuariosOfAuditoriaAccesoListNewAuditoriaAcceso = auditoriaAccesoListNewAuditoriaAcceso.getUsuarios();
                    auditoriaAccesoListNewAuditoriaAcceso.setUsuarios(usuarios);
                    auditoriaAccesoListNewAuditoriaAcceso = em.merge(auditoriaAccesoListNewAuditoriaAcceso);
                    if (oldUsuariosOfAuditoriaAccesoListNewAuditoriaAcceso != null && !oldUsuariosOfAuditoriaAccesoListNewAuditoriaAcceso.equals(usuarios)) {
                        oldUsuariosOfAuditoriaAccesoListNewAuditoriaAcceso.getAuditoriaAccesoList().remove(auditoriaAccesoListNewAuditoriaAcceso);
                        oldUsuariosOfAuditoriaAccesoListNewAuditoriaAcceso = em.merge(oldUsuariosOfAuditoriaAccesoListNewAuditoriaAcceso);
                    }
                }
            }
            for (Feed feedListOldFeed : feedListOld) {
                if (!feedListNew.contains(feedListOldFeed)) {
                    feedListOldFeed.setUsuarios(null);
                    feedListOldFeed = em.merge(feedListOldFeed);
                }
            }
            for (Feed feedListNewFeed : feedListNew) {
                if (!feedListOld.contains(feedListNewFeed)) {
                    Usuarios oldUsuariosOfFeedListNewFeed = feedListNewFeed.getUsuarios();
                    feedListNewFeed.setUsuarios(usuarios);
                    feedListNewFeed = em.merge(feedListNewFeed);
                    if (oldUsuariosOfFeedListNewFeed != null && !oldUsuariosOfFeedListNewFeed.equals(usuarios)) {
                        oldUsuariosOfFeedListNewFeed.getFeedList().remove(feedListNewFeed);
                        oldUsuariosOfFeedListNewFeed = em.merge(oldUsuariosOfFeedListNewFeed);
                    }
                }
            }
            for (Comentarios comentariosListOldComentarios : comentariosListOld) {
                if (!comentariosListNew.contains(comentariosListOldComentarios)) {
                    comentariosListOldComentarios.setUsuarios(null);
                    comentariosListOldComentarios = em.merge(comentariosListOldComentarios);
                }
            }
            for (Comentarios comentariosListNewComentarios : comentariosListNew) {
                if (!comentariosListOld.contains(comentariosListNewComentarios)) {
                    Usuarios oldUsuariosOfComentariosListNewComentarios = comentariosListNewComentarios.getUsuarios();
                    comentariosListNewComentarios.setUsuarios(usuarios);
                    comentariosListNewComentarios = em.merge(comentariosListNewComentarios);
                    if (oldUsuariosOfComentariosListNewComentarios != null && !oldUsuariosOfComentariosListNewComentarios.equals(usuarios)) {
                        oldUsuariosOfComentariosListNewComentarios.getComentariosList().remove(comentariosListNewComentarios);
                        oldUsuariosOfComentariosListNewComentarios = em.merge(oldUsuariosOfComentariosListNewComentarios);
                    }
                }
            }
            for (Enviosuva enviosuvaListOldEnviosuva : enviosuvaListOld) {
                if (!enviosuvaListNew.contains(enviosuvaListOldEnviosuva)) {
                    enviosuvaListOldEnviosuva.setUsuarios(null);
                    enviosuvaListOldEnviosuva = em.merge(enviosuvaListOldEnviosuva);
                }
            }
            for (Enviosuva enviosuvaListNewEnviosuva : enviosuvaListNew) {
                if (!enviosuvaListOld.contains(enviosuvaListNewEnviosuva)) {
                    Usuarios oldUsuariosOfEnviosuvaListNewEnviosuva = enviosuvaListNewEnviosuva.getUsuarios();
                    enviosuvaListNewEnviosuva.setUsuarios(usuarios);
                    enviosuvaListNewEnviosuva = em.merge(enviosuvaListNewEnviosuva);
                    if (oldUsuariosOfEnviosuvaListNewEnviosuva != null && !oldUsuariosOfEnviosuvaListNewEnviosuva.equals(usuarios)) {
                        oldUsuariosOfEnviosuvaListNewEnviosuva.getEnviosuvaList().remove(enviosuvaListNewEnviosuva);
                        oldUsuariosOfEnviosuvaListNewEnviosuva = em.merge(oldUsuariosOfEnviosuvaListNewEnviosuva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getId();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            Ciudades ciudades = usuarios.getCiudades();
            if (ciudades != null) {
                ciudades.getUsuariosList().remove(usuarios);
                ciudades = em.merge(ciudades);
            }
            Paises paises = usuarios.getPaises();
            if (paises != null) {
                paises.getUsuariosList().remove(usuarios);
                paises = em.merge(paises);
            }
            Rangos rangos = usuarios.getRangos();
            if (rangos != null) {
                rangos.getUsuariosList().remove(usuarios);
                rangos = em.merge(rangos);
            }
            Universidades universidades = usuarios.getUniversidades();
            if (universidades != null) {
                universidades.getUsuariosList().remove(usuarios);
                universidades = em.merge(universidades);
            }
            List<AuditoriaAcceso> auditoriaAccesoList = usuarios.getAuditoriaAccesoList();
            for (AuditoriaAcceso auditoriaAccesoListAuditoriaAcceso : auditoriaAccesoList) {
                auditoriaAccesoListAuditoriaAcceso.setUsuarios(null);
                auditoriaAccesoListAuditoriaAcceso = em.merge(auditoriaAccesoListAuditoriaAcceso);
            }
            List<Feed> feedList = usuarios.getFeedList();
            for (Feed feedListFeed : feedList) {
                feedListFeed.setUsuarios(null);
                feedListFeed = em.merge(feedListFeed);
            }
            List<Comentarios> comentariosList = usuarios.getComentariosList();
            for (Comentarios comentariosListComentarios : comentariosList) {
                comentariosListComentarios.setUsuarios(null);
                comentariosListComentarios = em.merge(comentariosListComentarios);
            }
            List<Enviosuva> enviosuvaList = usuarios.getEnviosuvaList();
            for (Enviosuva enviosuvaListEnviosuva : enviosuvaList) {
                enviosuvaListEnviosuva.setUsuarios(null);
                enviosuvaListEnviosuva = em.merge(enviosuvaListEnviosuva);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
