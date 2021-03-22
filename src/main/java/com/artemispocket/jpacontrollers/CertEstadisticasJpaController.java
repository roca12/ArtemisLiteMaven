/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.CertEstadisticas;
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
public class CertEstadisticasJpaController implements Serializable {

    public CertEstadisticasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CertEstadisticas certEstadisticas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(certEstadisticas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CertEstadisticas certEstadisticas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            certEstadisticas = em.merge(certEstadisticas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = certEstadisticas.getId();
                if (findCertEstadisticas(id) == null) {
                    throw new NonexistentEntityException("The certEstadisticas with id " + id + " no longer exists.");
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
            CertEstadisticas certEstadisticas;
            try {
                certEstadisticas = em.getReference(CertEstadisticas.class, id);
                certEstadisticas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The certEstadisticas with id " + id + " no longer exists.", enfe);
            }
            em.remove(certEstadisticas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CertEstadisticas> findCertEstadisticasEntities() {
        return findCertEstadisticasEntities(true, -1, -1);
    }

    public List<CertEstadisticas> findCertEstadisticasEntities(int maxResults, int firstResult) {
        return findCertEstadisticasEntities(false, maxResults, firstResult);
    }

    private List<CertEstadisticas> findCertEstadisticasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CertEstadisticas.class));
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

    public CertEstadisticas findCertEstadisticas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CertEstadisticas.class, id);
        } finally {
            em.close();
        }
    }

    public int getCertEstadisticasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CertEstadisticas> rt = cq.from(CertEstadisticas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
