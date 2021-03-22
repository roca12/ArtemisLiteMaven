/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.Visitaspormesanio;
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
public class VisitaspormesanioJpaController implements Serializable {

    public VisitaspormesanioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Visitaspormesanio visitaspormesanio) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(visitaspormesanio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Visitaspormesanio visitaspormesanio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            visitaspormesanio = em.merge(visitaspormesanio);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = visitaspormesanio.getId();
                if (findVisitaspormesanio(id) == null) {
                    throw new NonexistentEntityException("The visitaspormesanio with id " + id + " no longer exists.");
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
            Visitaspormesanio visitaspormesanio;
            try {
                visitaspormesanio = em.getReference(Visitaspormesanio.class, id);
                visitaspormesanio.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The visitaspormesanio with id " + id + " no longer exists.", enfe);
            }
            em.remove(visitaspormesanio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Visitaspormesanio> findVisitaspormesanioEntities() {
        return findVisitaspormesanioEntities(true, -1, -1);
    }

    public List<Visitaspormesanio> findVisitaspormesanioEntities(int maxResults, int firstResult) {
        return findVisitaspormesanioEntities(false, maxResults, firstResult);
    }

    private List<Visitaspormesanio> findVisitaspormesanioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Visitaspormesanio.class));
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

    public Visitaspormesanio findVisitaspormesanio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Visitaspormesanio.class, id);
        } finally {
            em.close();
        }
    }

    public int getVisitaspormesanioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Visitaspormesanio> rt = cq.from(Visitaspormesanio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
