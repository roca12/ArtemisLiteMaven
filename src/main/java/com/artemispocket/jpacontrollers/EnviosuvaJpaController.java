/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.Enviosuva;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.artemispocket.entities.Lenguajes;
import com.artemispocket.entities.Usuarios;
import com.artemispocket.jpacontrollers.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author roca12
 */
public class EnviosuvaJpaController implements Serializable {

    public EnviosuvaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Enviosuva enviosuva) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Lenguajes lenguajes = enviosuva.getLenguajes();
            if (lenguajes != null) {
                lenguajes = em.getReference(lenguajes.getClass(), lenguajes.getId());
                enviosuva.setLenguajes(lenguajes);
            }
            Usuarios usuarios = enviosuva.getUsuarios();
            if (usuarios != null) {
                usuarios = em.getReference(usuarios.getClass(), usuarios.getId());
                enviosuva.setUsuarios(usuarios);
            }
            em.persist(enviosuva);
            if (lenguajes != null) {
                lenguajes.getEnviosuvaList().add(enviosuva);
                lenguajes = em.merge(lenguajes);
            }
            if (usuarios != null) {
                usuarios.getEnviosuvaList().add(enviosuva);
                usuarios = em.merge(usuarios);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Enviosuva enviosuva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Enviosuva persistentEnviosuva = em.find(Enviosuva.class, enviosuva.getId());
            Lenguajes lenguajesOld = persistentEnviosuva.getLenguajes();
            Lenguajes lenguajesNew = enviosuva.getLenguajes();
            Usuarios usuariosOld = persistentEnviosuva.getUsuarios();
            Usuarios usuariosNew = enviosuva.getUsuarios();
            if (lenguajesNew != null) {
                lenguajesNew = em.getReference(lenguajesNew.getClass(), lenguajesNew.getId());
                enviosuva.setLenguajes(lenguajesNew);
            }
            if (usuariosNew != null) {
                usuariosNew = em.getReference(usuariosNew.getClass(), usuariosNew.getId());
                enviosuva.setUsuarios(usuariosNew);
            }
            enviosuva = em.merge(enviosuva);
            if (lenguajesOld != null && !lenguajesOld.equals(lenguajesNew)) {
                lenguajesOld.getEnviosuvaList().remove(enviosuva);
                lenguajesOld = em.merge(lenguajesOld);
            }
            if (lenguajesNew != null && !lenguajesNew.equals(lenguajesOld)) {
                lenguajesNew.getEnviosuvaList().add(enviosuva);
                lenguajesNew = em.merge(lenguajesNew);
            }
            if (usuariosOld != null && !usuariosOld.equals(usuariosNew)) {
                usuariosOld.getEnviosuvaList().remove(enviosuva);
                usuariosOld = em.merge(usuariosOld);
            }
            if (usuariosNew != null && !usuariosNew.equals(usuariosOld)) {
                usuariosNew.getEnviosuvaList().add(enviosuva);
                usuariosNew = em.merge(usuariosNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = enviosuva.getId();
                if (findEnviosuva(id) == null) {
                    throw new NonexistentEntityException("The enviosuva with id " + id + " no longer exists.");
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
            Enviosuva enviosuva;
            try {
                enviosuva = em.getReference(Enviosuva.class, id);
                enviosuva.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The enviosuva with id " + id + " no longer exists.", enfe);
            }
            Lenguajes lenguajes = enviosuva.getLenguajes();
            if (lenguajes != null) {
                lenguajes.getEnviosuvaList().remove(enviosuva);
                lenguajes = em.merge(lenguajes);
            }
            Usuarios usuarios = enviosuva.getUsuarios();
            if (usuarios != null) {
                usuarios.getEnviosuvaList().remove(enviosuva);
                usuarios = em.merge(usuarios);
            }
            em.remove(enviosuva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Enviosuva> findEnviosuvaEntities() {
        return findEnviosuvaEntities(true, -1, -1);
    }

    public List<Enviosuva> findEnviosuvaEntities(int maxResults, int firstResult) {
        return findEnviosuvaEntities(false, maxResults, firstResult);
    }

    private List<Enviosuva> findEnviosuvaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Enviosuva.class));
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

    public Enviosuva findEnviosuva(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Enviosuva.class, id);
        } finally {
            em.close();
        }
    }

    public int getEnviosuvaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Enviosuva> rt = cq.from(Enviosuva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
