/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.Ciudades;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.artemispocket.entities.Universidades;
import java.util.ArrayList;
import java.util.List;
import com.artemispocket.entities.Usuarios;
import com.artemispocket.jpacontrollers.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author roca12
 */
public class CiudadesJpaController implements Serializable {

    public CiudadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ciudades ciudades) {
        if (ciudades.getUniversidadesList() == null) {
            ciudades.setUniversidadesList(new ArrayList<Universidades>());
        }
        if (ciudades.getUsuariosList() == null) {
            ciudades.setUsuariosList(new ArrayList<Usuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Universidades> attachedUniversidadesList = new ArrayList<Universidades>();
            for (Universidades universidadesListUniversidadesToAttach : ciudades.getUniversidadesList()) {
                universidadesListUniversidadesToAttach = em.getReference(universidadesListUniversidadesToAttach.getClass(), universidadesListUniversidadesToAttach.getId());
                attachedUniversidadesList.add(universidadesListUniversidadesToAttach);
            }
            ciudades.setUniversidadesList(attachedUniversidadesList);
            List<Usuarios> attachedUsuariosList = new ArrayList<Usuarios>();
            for (Usuarios usuariosListUsuariosToAttach : ciudades.getUsuariosList()) {
                usuariosListUsuariosToAttach = em.getReference(usuariosListUsuariosToAttach.getClass(), usuariosListUsuariosToAttach.getId());
                attachedUsuariosList.add(usuariosListUsuariosToAttach);
            }
            ciudades.setUsuariosList(attachedUsuariosList);
            em.persist(ciudades);
            for (Universidades universidadesListUniversidades : ciudades.getUniversidadesList()) {
                Ciudades oldCiudadesOfUniversidadesListUniversidades = universidadesListUniversidades.getCiudades();
                universidadesListUniversidades.setCiudades(ciudades);
                universidadesListUniversidades = em.merge(universidadesListUniversidades);
                if (oldCiudadesOfUniversidadesListUniversidades != null) {
                    oldCiudadesOfUniversidadesListUniversidades.getUniversidadesList().remove(universidadesListUniversidades);
                    oldCiudadesOfUniversidadesListUniversidades = em.merge(oldCiudadesOfUniversidadesListUniversidades);
                }
            }
            for (Usuarios usuariosListUsuarios : ciudades.getUsuariosList()) {
                Ciudades oldCiudadesOfUsuariosListUsuarios = usuariosListUsuarios.getCiudades();
                usuariosListUsuarios.setCiudades(ciudades);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
                if (oldCiudadesOfUsuariosListUsuarios != null) {
                    oldCiudadesOfUsuariosListUsuarios.getUsuariosList().remove(usuariosListUsuarios);
                    oldCiudadesOfUsuariosListUsuarios = em.merge(oldCiudadesOfUsuariosListUsuarios);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ciudades ciudades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudades persistentCiudades = em.find(Ciudades.class, ciudades.getIdCiudades());
            List<Universidades> universidadesListOld = persistentCiudades.getUniversidadesList();
            List<Universidades> universidadesListNew = ciudades.getUniversidadesList();
            List<Usuarios> usuariosListOld = persistentCiudades.getUsuariosList();
            List<Usuarios> usuariosListNew = ciudades.getUsuariosList();
            List<Universidades> attachedUniversidadesListNew = new ArrayList<Universidades>();
            for (Universidades universidadesListNewUniversidadesToAttach : universidadesListNew) {
                universidadesListNewUniversidadesToAttach = em.getReference(universidadesListNewUniversidadesToAttach.getClass(), universidadesListNewUniversidadesToAttach.getId());
                attachedUniversidadesListNew.add(universidadesListNewUniversidadesToAttach);
            }
            universidadesListNew = attachedUniversidadesListNew;
            ciudades.setUniversidadesList(universidadesListNew);
            List<Usuarios> attachedUsuariosListNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosListNewUsuariosToAttach : usuariosListNew) {
                usuariosListNewUsuariosToAttach = em.getReference(usuariosListNewUsuariosToAttach.getClass(), usuariosListNewUsuariosToAttach.getId());
                attachedUsuariosListNew.add(usuariosListNewUsuariosToAttach);
            }
            usuariosListNew = attachedUsuariosListNew;
            ciudades.setUsuariosList(usuariosListNew);
            ciudades = em.merge(ciudades);
            for (Universidades universidadesListOldUniversidades : universidadesListOld) {
                if (!universidadesListNew.contains(universidadesListOldUniversidades)) {
                    universidadesListOldUniversidades.setCiudades(null);
                    universidadesListOldUniversidades = em.merge(universidadesListOldUniversidades);
                }
            }
            for (Universidades universidadesListNewUniversidades : universidadesListNew) {
                if (!universidadesListOld.contains(universidadesListNewUniversidades)) {
                    Ciudades oldCiudadesOfUniversidadesListNewUniversidades = universidadesListNewUniversidades.getCiudades();
                    universidadesListNewUniversidades.setCiudades(ciudades);
                    universidadesListNewUniversidades = em.merge(universidadesListNewUniversidades);
                    if (oldCiudadesOfUniversidadesListNewUniversidades != null && !oldCiudadesOfUniversidadesListNewUniversidades.equals(ciudades)) {
                        oldCiudadesOfUniversidadesListNewUniversidades.getUniversidadesList().remove(universidadesListNewUniversidades);
                        oldCiudadesOfUniversidadesListNewUniversidades = em.merge(oldCiudadesOfUniversidadesListNewUniversidades);
                    }
                }
            }
            for (Usuarios usuariosListOldUsuarios : usuariosListOld) {
                if (!usuariosListNew.contains(usuariosListOldUsuarios)) {
                    usuariosListOldUsuarios.setCiudades(null);
                    usuariosListOldUsuarios = em.merge(usuariosListOldUsuarios);
                }
            }
            for (Usuarios usuariosListNewUsuarios : usuariosListNew) {
                if (!usuariosListOld.contains(usuariosListNewUsuarios)) {
                    Ciudades oldCiudadesOfUsuariosListNewUsuarios = usuariosListNewUsuarios.getCiudades();
                    usuariosListNewUsuarios.setCiudades(ciudades);
                    usuariosListNewUsuarios = em.merge(usuariosListNewUsuarios);
                    if (oldCiudadesOfUsuariosListNewUsuarios != null && !oldCiudadesOfUsuariosListNewUsuarios.equals(ciudades)) {
                        oldCiudadesOfUsuariosListNewUsuarios.getUsuariosList().remove(usuariosListNewUsuarios);
                        oldCiudadesOfUsuariosListNewUsuarios = em.merge(oldCiudadesOfUsuariosListNewUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ciudades.getIdCiudades();
                if (findCiudades(id) == null) {
                    throw new NonexistentEntityException("The ciudades with id " + id + " no longer exists.");
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
            Ciudades ciudades;
            try {
                ciudades = em.getReference(Ciudades.class, id);
                ciudades.getIdCiudades();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ciudades with id " + id + " no longer exists.", enfe);
            }
            List<Universidades> universidadesList = ciudades.getUniversidadesList();
            for (Universidades universidadesListUniversidades : universidadesList) {
                universidadesListUniversidades.setCiudades(null);
                universidadesListUniversidades = em.merge(universidadesListUniversidades);
            }
            List<Usuarios> usuariosList = ciudades.getUsuariosList();
            for (Usuarios usuariosListUsuarios : usuariosList) {
                usuariosListUsuarios.setCiudades(null);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
            }
            em.remove(ciudades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ciudades> findCiudadesEntities() {
        return findCiudadesEntities(true, -1, -1);
    }

    public List<Ciudades> findCiudadesEntities(int maxResults, int firstResult) {
        return findCiudadesEntities(false, maxResults, firstResult);
    }

    private List<Ciudades> findCiudadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudades.class));
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

    public Ciudades findCiudades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ciudades.class, id);
        } finally {
            em.close();
        }
    }

    public int getCiudadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudades> rt = cq.from(Ciudades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
