/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.Rangos;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.artemispocket.entities.Usuarios;
import com.artemispocket.jpacontrollers.exceptions.IllegalOrphanException;
import com.artemispocket.jpacontrollers.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author roca12
 */
public class RangosJpaController implements Serializable {

    public RangosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rangos rangos) {
        if (rangos.getUsuariosList() == null) {
            rangos.setUsuariosList(new ArrayList<Usuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuarios> attachedUsuariosList = new ArrayList<Usuarios>();
            for (Usuarios usuariosListUsuariosToAttach : rangos.getUsuariosList()) {
                usuariosListUsuariosToAttach = em.getReference(usuariosListUsuariosToAttach.getClass(), usuariosListUsuariosToAttach.getId());
                attachedUsuariosList.add(usuariosListUsuariosToAttach);
            }
            rangos.setUsuariosList(attachedUsuariosList);
            em.persist(rangos);
            for (Usuarios usuariosListUsuarios : rangos.getUsuariosList()) {
                Rangos oldRangosOfUsuariosListUsuarios = usuariosListUsuarios.getRangos();
                usuariosListUsuarios.setRangos(rangos);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
                if (oldRangosOfUsuariosListUsuarios != null) {
                    oldRangosOfUsuariosListUsuarios.getUsuariosList().remove(usuariosListUsuarios);
                    oldRangosOfUsuariosListUsuarios = em.merge(oldRangosOfUsuariosListUsuarios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rangos rangos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rangos persistentRangos = em.find(Rangos.class, rangos.getId());
            List<Usuarios> usuariosListOld = persistentRangos.getUsuariosList();
            List<Usuarios> usuariosListNew = rangos.getUsuariosList();
            List<String> illegalOrphanMessages = null;
            for (Usuarios usuariosListOldUsuarios : usuariosListOld) {
                if (!usuariosListNew.contains(usuariosListOldUsuarios)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuarios " + usuariosListOldUsuarios + " since its rangos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Usuarios> attachedUsuariosListNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosListNewUsuariosToAttach : usuariosListNew) {
                usuariosListNewUsuariosToAttach = em.getReference(usuariosListNewUsuariosToAttach.getClass(), usuariosListNewUsuariosToAttach.getId());
                attachedUsuariosListNew.add(usuariosListNewUsuariosToAttach);
            }
            usuariosListNew = attachedUsuariosListNew;
            rangos.setUsuariosList(usuariosListNew);
            rangos = em.merge(rangos);
            for (Usuarios usuariosListNewUsuarios : usuariosListNew) {
                if (!usuariosListOld.contains(usuariosListNewUsuarios)) {
                    Rangos oldRangosOfUsuariosListNewUsuarios = usuariosListNewUsuarios.getRangos();
                    usuariosListNewUsuarios.setRangos(rangos);
                    usuariosListNewUsuarios = em.merge(usuariosListNewUsuarios);
                    if (oldRangosOfUsuariosListNewUsuarios != null && !oldRangosOfUsuariosListNewUsuarios.equals(rangos)) {
                        oldRangosOfUsuariosListNewUsuarios.getUsuariosList().remove(usuariosListNewUsuarios);
                        oldRangosOfUsuariosListNewUsuarios = em.merge(oldRangosOfUsuariosListNewUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rangos.getId();
                if (findRangos(id) == null) {
                    throw new NonexistentEntityException("The rangos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rangos rangos;
            try {
                rangos = em.getReference(Rangos.class, id);
                rangos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rangos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Usuarios> usuariosListOrphanCheck = rangos.getUsuariosList();
            for (Usuarios usuariosListOrphanCheckUsuarios : usuariosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Rangos (" + rangos + ") cannot be destroyed since the Usuarios " + usuariosListOrphanCheckUsuarios + " in its usuariosList field has a non-nullable rangos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(rangos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rangos> findRangosEntities() {
        return findRangosEntities(true, -1, -1);
    }

    public List<Rangos> findRangosEntities(int maxResults, int firstResult) {
        return findRangosEntities(false, maxResults, firstResult);
    }

    private List<Rangos> findRangosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rangos.class));
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

    public Rangos findRangos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rangos.class, id);
        } finally {
            em.close();
        }
    }

    public int getRangosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rangos> rt = cq.from(Rangos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
