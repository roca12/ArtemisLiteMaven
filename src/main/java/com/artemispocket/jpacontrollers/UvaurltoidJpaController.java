/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.Uvaurltoid;
import com.artemispocket.jpacontrollers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author roca12
 */
public class UvaurltoidJpaController implements Serializable {

    public UvaurltoidJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Uvaurltoid uvaurltoid) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(uvaurltoid);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Uvaurltoid uvaurltoid) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            uvaurltoid = em.merge(uvaurltoid);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = uvaurltoid.getId();
                if (findUvaurltoid(id) == null) {
                    throw new NonexistentEntityException("The uvaurltoid with id " + id + " no longer exists.");
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
            Uvaurltoid uvaurltoid;
            try {
                uvaurltoid = em.getReference(Uvaurltoid.class, id);
                uvaurltoid.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The uvaurltoid with id " + id + " no longer exists.", enfe);
            }
            em.remove(uvaurltoid);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Uvaurltoid> findUvaurltoidEntities() {
        return findUvaurltoidEntities(true, -1, -1);
    }

    public List<Uvaurltoid> findUvaurltoidEntities(int maxResults, int firstResult) {
        return findUvaurltoidEntities(false, maxResults, firstResult);
    }

    private List<Uvaurltoid> findUvaurltoidEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Uvaurltoid.class));
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

    public Uvaurltoid findUvaurltoid(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Uvaurltoid.class, id);
        } finally {
            em.close();
        }
    }

    public int getUvaurltoidCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Uvaurltoid> rt = cq.from(Uvaurltoid.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
