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
import com.artemispocket.entities.Enviosuva;
import com.artemispocket.entities.Lenguajes;
import com.artemispocket.jpacontrollers.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author roca12
 */
public class LenguajesJpaController implements Serializable {

    public LenguajesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Lenguajes lenguajes) {
        if (lenguajes.getEnviosuvaList() == null) {
            lenguajes.setEnviosuvaList(new ArrayList<Enviosuva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Enviosuva> attachedEnviosuvaList = new ArrayList<Enviosuva>();
            for (Enviosuva enviosuvaListEnviosuvaToAttach : lenguajes.getEnviosuvaList()) {
                enviosuvaListEnviosuvaToAttach = em.getReference(enviosuvaListEnviosuvaToAttach.getClass(), enviosuvaListEnviosuvaToAttach.getId());
                attachedEnviosuvaList.add(enviosuvaListEnviosuvaToAttach);
            }
            lenguajes.setEnviosuvaList(attachedEnviosuvaList);
            em.persist(lenguajes);
            for (Enviosuva enviosuvaListEnviosuva : lenguajes.getEnviosuvaList()) {
                Lenguajes oldLenguajesOfEnviosuvaListEnviosuva = enviosuvaListEnviosuva.getLenguajes();
                enviosuvaListEnviosuva.setLenguajes(lenguajes);
                enviosuvaListEnviosuva = em.merge(enviosuvaListEnviosuva);
                if (oldLenguajesOfEnviosuvaListEnviosuva != null) {
                    oldLenguajesOfEnviosuvaListEnviosuva.getEnviosuvaList().remove(enviosuvaListEnviosuva);
                    oldLenguajesOfEnviosuvaListEnviosuva = em.merge(oldLenguajesOfEnviosuvaListEnviosuva);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Lenguajes lenguajes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lenguajes persistentLenguajes = em.find(Lenguajes.class, lenguajes.getId());
            List<Enviosuva> enviosuvaListOld = persistentLenguajes.getEnviosuvaList();
            List<Enviosuva> enviosuvaListNew = lenguajes.getEnviosuvaList();
            List<Enviosuva> attachedEnviosuvaListNew = new ArrayList<Enviosuva>();
            for (Enviosuva enviosuvaListNewEnviosuvaToAttach : enviosuvaListNew) {
                enviosuvaListNewEnviosuvaToAttach = em.getReference(enviosuvaListNewEnviosuvaToAttach.getClass(), enviosuvaListNewEnviosuvaToAttach.getId());
                attachedEnviosuvaListNew.add(enviosuvaListNewEnviosuvaToAttach);
            }
            enviosuvaListNew = attachedEnviosuvaListNew;
            lenguajes.setEnviosuvaList(enviosuvaListNew);
            lenguajes = em.merge(lenguajes);
            for (Enviosuva enviosuvaListOldEnviosuva : enviosuvaListOld) {
                if (!enviosuvaListNew.contains(enviosuvaListOldEnviosuva)) {
                    enviosuvaListOldEnviosuva.setLenguajes(null);
                    enviosuvaListOldEnviosuva = em.merge(enviosuvaListOldEnviosuva);
                }
            }
            for (Enviosuva enviosuvaListNewEnviosuva : enviosuvaListNew) {
                if (!enviosuvaListOld.contains(enviosuvaListNewEnviosuva)) {
                    Lenguajes oldLenguajesOfEnviosuvaListNewEnviosuva = enviosuvaListNewEnviosuva.getLenguajes();
                    enviosuvaListNewEnviosuva.setLenguajes(lenguajes);
                    enviosuvaListNewEnviosuva = em.merge(enviosuvaListNewEnviosuva);
                    if (oldLenguajesOfEnviosuvaListNewEnviosuva != null && !oldLenguajesOfEnviosuvaListNewEnviosuva.equals(lenguajes)) {
                        oldLenguajesOfEnviosuvaListNewEnviosuva.getEnviosuvaList().remove(enviosuvaListNewEnviosuva);
                        oldLenguajesOfEnviosuvaListNewEnviosuva = em.merge(oldLenguajesOfEnviosuvaListNewEnviosuva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = lenguajes.getId();
                if (findLenguajes(id) == null) {
                    throw new NonexistentEntityException("The lenguajes with id " + id + " no longer exists.");
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
            Lenguajes lenguajes;
            try {
                lenguajes = em.getReference(Lenguajes.class, id);
                lenguajes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lenguajes with id " + id + " no longer exists.", enfe);
            }
            List<Enviosuva> enviosuvaList = lenguajes.getEnviosuvaList();
            for (Enviosuva enviosuvaListEnviosuva : enviosuvaList) {
                enviosuvaListEnviosuva.setLenguajes(null);
                enviosuvaListEnviosuva = em.merge(enviosuvaListEnviosuva);
            }
            em.remove(lenguajes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Lenguajes> findLenguajesEntities() {
        return findLenguajesEntities(true, -1, -1);
    }

    public List<Lenguajes> findLenguajesEntities(int maxResults, int firstResult) {
        return findLenguajesEntities(false, maxResults, firstResult);
    }

    private List<Lenguajes> findLenguajesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Lenguajes.class));
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

    public Lenguajes findLenguajes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Lenguajes.class, id);
        } finally {
            em.close();
        }
    }

    public int getLenguajesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Lenguajes> rt = cq.from(Lenguajes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
