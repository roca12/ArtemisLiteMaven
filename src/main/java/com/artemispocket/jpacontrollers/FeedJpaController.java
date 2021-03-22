/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.Feed;
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
public class FeedJpaController implements Serializable {

    public FeedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Feed feed) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios = feed.getUsuarios();
            if (usuarios != null) {
                usuarios = em.getReference(usuarios.getClass(), usuarios.getId());
                feed.setUsuarios(usuarios);
            }
            em.persist(feed);
            if (usuarios != null) {
                usuarios.getFeedList().add(feed);
                usuarios = em.merge(usuarios);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Feed feed) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Feed persistentFeed = em.find(Feed.class, feed.getId());
            Usuarios usuariosOld = persistentFeed.getUsuarios();
            Usuarios usuariosNew = feed.getUsuarios();
            if (usuariosNew != null) {
                usuariosNew = em.getReference(usuariosNew.getClass(), usuariosNew.getId());
                feed.setUsuarios(usuariosNew);
            }
            feed = em.merge(feed);
            if (usuariosOld != null && !usuariosOld.equals(usuariosNew)) {
                usuariosOld.getFeedList().remove(feed);
                usuariosOld = em.merge(usuariosOld);
            }
            if (usuariosNew != null && !usuariosNew.equals(usuariosOld)) {
                usuariosNew.getFeedList().add(feed);
                usuariosNew = em.merge(usuariosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = feed.getId();
                if (findFeed(id) == null) {
                    throw new NonexistentEntityException("The feed with id " + id + " no longer exists.");
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
            Feed feed;
            try {
                feed = em.getReference(Feed.class, id);
                feed.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The feed with id " + id + " no longer exists.", enfe);
            }
            Usuarios usuarios = feed.getUsuarios();
            if (usuarios != null) {
                usuarios.getFeedList().remove(feed);
                usuarios = em.merge(usuarios);
            }
            em.remove(feed);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Feed> findFeedEntities() {
        return findFeedEntities(true, -1, -1);
    }

    public List<Feed> findFeedEntities(int maxResults, int firstResult) {
        return findFeedEntities(false, maxResults, firstResult);
    }

    private List<Feed> findFeedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Feed.class));
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

    public Feed findFeed(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Feed.class, id);
        } finally {
            em.close();
        }
    }

    public int getFeedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Feed> rt = cq.from(Feed.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
