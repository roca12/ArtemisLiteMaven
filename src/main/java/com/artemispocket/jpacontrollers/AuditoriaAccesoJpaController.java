/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.AuditoriaAcceso;
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
public class AuditoriaAccesoJpaController implements Serializable {

    public AuditoriaAccesoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AuditoriaAcceso auditoriaAcceso) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios = auditoriaAcceso.getUsuarios();
            if (usuarios != null) {
                usuarios = em.getReference(usuarios.getClass(), usuarios.getId());
                auditoriaAcceso.setUsuarios(usuarios);
            }
            em.persist(auditoriaAcceso);
            if (usuarios != null) {
                usuarios.getAuditoriaAccesoList().add(auditoriaAcceso);
                usuarios = em.merge(usuarios);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AuditoriaAcceso auditoriaAcceso) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AuditoriaAcceso persistentAuditoriaAcceso = em.find(AuditoriaAcceso.class, auditoriaAcceso.getId());
            Usuarios usuariosOld = persistentAuditoriaAcceso.getUsuarios();
            Usuarios usuariosNew = auditoriaAcceso.getUsuarios();
            if (usuariosNew != null) {
                usuariosNew = em.getReference(usuariosNew.getClass(), usuariosNew.getId());
                auditoriaAcceso.setUsuarios(usuariosNew);
            }
            auditoriaAcceso = em.merge(auditoriaAcceso);
            if (usuariosOld != null && !usuariosOld.equals(usuariosNew)) {
                usuariosOld.getAuditoriaAccesoList().remove(auditoriaAcceso);
                usuariosOld = em.merge(usuariosOld);
            }
            if (usuariosNew != null && !usuariosNew.equals(usuariosOld)) {
                usuariosNew.getAuditoriaAccesoList().add(auditoriaAcceso);
                usuariosNew = em.merge(usuariosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = auditoriaAcceso.getId();
                if (findAuditoriaAcceso(id) == null) {
                    throw new NonexistentEntityException("The auditoriaAcceso with id " + id + " no longer exists.");
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
            AuditoriaAcceso auditoriaAcceso;
            try {
                auditoriaAcceso = em.getReference(AuditoriaAcceso.class, id);
                auditoriaAcceso.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The auditoriaAcceso with id " + id + " no longer exists.", enfe);
            }
            Usuarios usuarios = auditoriaAcceso.getUsuarios();
            if (usuarios != null) {
                usuarios.getAuditoriaAccesoList().remove(auditoriaAcceso);
                usuarios = em.merge(usuarios);
            }
            em.remove(auditoriaAcceso);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AuditoriaAcceso> findAuditoriaAccesoEntities() {
        return findAuditoriaAccesoEntities(true, -1, -1);
    }

    public List<AuditoriaAcceso> findAuditoriaAccesoEntities(int maxResults, int firstResult) {
        return findAuditoriaAccesoEntities(false, maxResults, firstResult);
    }

    private List<AuditoriaAcceso> findAuditoriaAccesoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AuditoriaAcceso.class));
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

    public AuditoriaAcceso findAuditoriaAcceso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AuditoriaAcceso.class, id);
        } finally {
            em.close();
        }
    }

    public int getAuditoriaAccesoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AuditoriaAcceso> rt = cq.from(AuditoriaAcceso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
