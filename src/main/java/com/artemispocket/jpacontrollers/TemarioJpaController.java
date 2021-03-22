/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.Temario;
import com.artemispocket.jpacontrollers.exceptions.NonexistentEntityException;
import com.artemispocket.jpacontrollers.exceptions.PreexistingEntityException;
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
public class TemarioJpaController implements Serializable {

    public TemarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Temario temario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(temario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTemario(temario.getId()) != null) {
                throw new PreexistingEntityException("Temario " + temario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Temario temario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            temario = em.merge(temario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = temario.getId();
                if (findTemario(id) == null) {
                    throw new NonexistentEntityException("The temario with id " + id + " no longer exists.");
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
            Temario temario;
            try {
                temario = em.getReference(Temario.class, id);
                temario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The temario with id " + id + " no longer exists.", enfe);
            }
            em.remove(temario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Temario> findTemarioEntities() {
        return findTemarioEntities(true, -1, -1);
    }

    public List<Temario> findTemarioEntities(int maxResults, int firstResult) {
        return findTemarioEntities(false, maxResults, firstResult);
    }

    private List<Temario> findTemarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Temario.class));
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

    public Temario findTemario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Temario.class, id);
        } finally {
            em.close();
        }
    }

    public int getTemarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Temario> rt = cq.from(Temario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
