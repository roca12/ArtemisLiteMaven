/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.Comentarios;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.artemispocket.entities.Usuarios;
import com.artemispocket.jpacontrollers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author roca12
 */
public class ComentariosJpaController implements Serializable {

    public ComentariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Comentarios comentarios) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios = comentarios.getUsuarios();
            if (usuarios != null) {
                usuarios = em.getReference(usuarios.getClass(), usuarios.getId());
                comentarios.setUsuarios(usuarios);
            }
            em.persist(comentarios);
            if (usuarios != null) {
                usuarios.getComentariosList().add(comentarios);
                usuarios = em.merge(usuarios);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Comentarios comentarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Comentarios persistentComentarios = em.find(Comentarios.class, comentarios.getId());
            Usuarios usuariosOld = persistentComentarios.getUsuarios();
            Usuarios usuariosNew = comentarios.getUsuarios();
            if (usuariosNew != null) {
                usuariosNew = em.getReference(usuariosNew.getClass(), usuariosNew.getId());
                comentarios.setUsuarios(usuariosNew);
            }
            comentarios = em.merge(comentarios);
            if (usuariosOld != null && !usuariosOld.equals(usuariosNew)) {
                usuariosOld.getComentariosList().remove(comentarios);
                usuariosOld = em.merge(usuariosOld);
            }
            if (usuariosNew != null && !usuariosNew.equals(usuariosOld)) {
                usuariosNew.getComentariosList().add(comentarios);
                usuariosNew = em.merge(usuariosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = comentarios.getId();
                if (findComentarios(id) == null) {
                    throw new NonexistentEntityException("The comentarios with id " + id + " no longer exists.");
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
            Comentarios comentarios;
            try {
                comentarios = em.getReference(Comentarios.class, id);
                comentarios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The comentarios with id " + id + " no longer exists.", enfe);
            }
            Usuarios usuarios = comentarios.getUsuarios();
            if (usuarios != null) {
                usuarios.getComentariosList().remove(comentarios);
                usuarios = em.merge(usuarios);
            }
            em.remove(comentarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Comentarios> findComentariosEntities() {
        return findComentariosEntities(true, -1, -1);
    }

    public List<Comentarios> findComentariosEntities(int maxResults, int firstResult) {
        return findComentariosEntities(false, maxResults, firstResult);
    }

    private List<Comentarios> findComentariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Comentarios.class));
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

    public Comentarios findComentarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Comentarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getComentariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Comentarios> rt = cq.from(Comentarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
