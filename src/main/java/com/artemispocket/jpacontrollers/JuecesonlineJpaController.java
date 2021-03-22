/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.Juecesonline;
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
public class JuecesonlineJpaController implements Serializable {

    public JuecesonlineJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Juecesonline juecesonline) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(juecesonline);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Juecesonline juecesonline) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            juecesonline = em.merge(juecesonline);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = juecesonline.getId();
                if (findJuecesonline(id) == null) {
                    throw new NonexistentEntityException("The juecesonline with id " + id + " no longer exists.");
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
            Juecesonline juecesonline;
            try {
                juecesonline = em.getReference(Juecesonline.class, id);
                juecesonline.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The juecesonline with id " + id + " no longer exists.", enfe);
            }
            em.remove(juecesonline);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Juecesonline> findJuecesonlineEntities() {
        return findJuecesonlineEntities(true, -1, -1);
    }

    public List<Juecesonline> findJuecesonlineEntities(int maxResults, int firstResult) {
        return findJuecesonlineEntities(false, maxResults, firstResult);
    }

    private List<Juecesonline> findJuecesonlineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Juecesonline.class));
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

    public Juecesonline findJuecesonline(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Juecesonline.class, id);
        } finally {
            em.close();
        }
    }

    public int getJuecesonlineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Juecesonline> rt = cq.from(Juecesonline.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
