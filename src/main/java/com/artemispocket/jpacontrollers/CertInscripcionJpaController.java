/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.CertInscripcion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.artemispocket.entities.Universidades;
import com.artemispocket.jpacontrollers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author roca12
 */
public class CertInscripcionJpaController implements Serializable {

    public CertInscripcionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CertInscripcion certInscripcion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Universidades universidades = certInscripcion.getUniversidades();
            if (universidades != null) {
                universidades = em.getReference(universidades.getClass(), universidades.getId());
                certInscripcion.setUniversidades(universidades);
            }
            em.persist(certInscripcion);
            if (universidades != null) {
                universidades.getCertInscripcionList().add(certInscripcion);
                universidades = em.merge(universidades);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CertInscripcion certInscripcion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CertInscripcion persistentCertInscripcion = em.find(CertInscripcion.class, certInscripcion.getId());
            Universidades universidadesOld = persistentCertInscripcion.getUniversidades();
            Universidades universidadesNew = certInscripcion.getUniversidades();
            if (universidadesNew != null) {
                universidadesNew = em.getReference(universidadesNew.getClass(), universidadesNew.getId());
                certInscripcion.setUniversidades(universidadesNew);
            }
            certInscripcion = em.merge(certInscripcion);
            if (universidadesOld != null && !universidadesOld.equals(universidadesNew)) {
                universidadesOld.getCertInscripcionList().remove(certInscripcion);
                universidadesOld = em.merge(universidadesOld);
            }
            if (universidadesNew != null && !universidadesNew.equals(universidadesOld)) {
                universidadesNew.getCertInscripcionList().add(certInscripcion);
                universidadesNew = em.merge(universidadesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = certInscripcion.getId();
                if (findCertInscripcion(id) == null) {
                    throw new NonexistentEntityException("The certInscripcion with id " + id + " no longer exists.");
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
            CertInscripcion certInscripcion;
            try {
                certInscripcion = em.getReference(CertInscripcion.class, id);
                certInscripcion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The certInscripcion with id " + id + " no longer exists.", enfe);
            }
            Universidades universidades = certInscripcion.getUniversidades();
            if (universidades != null) {
                universidades.getCertInscripcionList().remove(certInscripcion);
                universidades = em.merge(universidades);
            }
            em.remove(certInscripcion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CertInscripcion> findCertInscripcionEntities() {
        return findCertInscripcionEntities(true, -1, -1);
    }

    public List<CertInscripcion> findCertInscripcionEntities(int maxResults, int firstResult) {
        return findCertInscripcionEntities(false, maxResults, firstResult);
    }

    private List<CertInscripcion> findCertInscripcionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CertInscripcion.class));
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

    public CertInscripcion findCertInscripcion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CertInscripcion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCertInscripcionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CertInscripcion> rt = cq.from(CertInscripcion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
