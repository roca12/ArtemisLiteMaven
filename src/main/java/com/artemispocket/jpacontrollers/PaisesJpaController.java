/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.jpacontrollers;

import com.artemispocket.entities.Paises;
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
import com.artemispocket.jpacontrollers.exceptions.PreexistingEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author roca12
 */
public class PaisesJpaController implements Serializable {

    public PaisesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Paises paises) throws PreexistingEntityException, Exception {
        if (paises.getUniversidadesList() == null) {
            paises.setUniversidadesList(new ArrayList<Universidades>());
        }
        if (paises.getUsuariosList() == null) {
            paises.setUsuariosList(new ArrayList<Usuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Universidades> attachedUniversidadesList = new ArrayList<Universidades>();
            for (Universidades universidadesListUniversidadesToAttach : paises.getUniversidadesList()) {
                universidadesListUniversidadesToAttach = em.getReference(universidadesListUniversidadesToAttach.getClass(), universidadesListUniversidadesToAttach.getId());
                attachedUniversidadesList.add(universidadesListUniversidadesToAttach);
            }
            paises.setUniversidadesList(attachedUniversidadesList);
            List<Usuarios> attachedUsuariosList = new ArrayList<Usuarios>();
            for (Usuarios usuariosListUsuariosToAttach : paises.getUsuariosList()) {
                usuariosListUsuariosToAttach = em.getReference(usuariosListUsuariosToAttach.getClass(), usuariosListUsuariosToAttach.getId());
                attachedUsuariosList.add(usuariosListUsuariosToAttach);
            }
            paises.setUsuariosList(attachedUsuariosList);
            em.persist(paises);
            for (Universidades universidadesListUniversidades : paises.getUniversidadesList()) {
                Paises oldPaisesOfUniversidadesListUniversidades = universidadesListUniversidades.getPaises();
                universidadesListUniversidades.setPaises(paises);
                universidadesListUniversidades = em.merge(universidadesListUniversidades);
                if (oldPaisesOfUniversidadesListUniversidades != null) {
                    oldPaisesOfUniversidadesListUniversidades.getUniversidadesList().remove(universidadesListUniversidades);
                    oldPaisesOfUniversidadesListUniversidades = em.merge(oldPaisesOfUniversidadesListUniversidades);
                }
            }
            for (Usuarios usuariosListUsuarios : paises.getUsuariosList()) {
                Paises oldPaisesOfUsuariosListUsuarios = usuariosListUsuarios.getPaises();
                usuariosListUsuarios.setPaises(paises);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
                if (oldPaisesOfUsuariosListUsuarios != null) {
                    oldPaisesOfUsuariosListUsuarios.getUsuariosList().remove(usuariosListUsuarios);
                    oldPaisesOfUsuariosListUsuarios = em.merge(oldPaisesOfUsuariosListUsuarios);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPaises(paises.getCodigo()) != null) {
                throw new PreexistingEntityException("Paises " + paises + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paises paises) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paises persistentPaises = em.find(Paises.class, paises.getCodigo());
            List<Universidades> universidadesListOld = persistentPaises.getUniversidadesList();
            List<Universidades> universidadesListNew = paises.getUniversidadesList();
            List<Usuarios> usuariosListOld = persistentPaises.getUsuariosList();
            List<Usuarios> usuariosListNew = paises.getUsuariosList();
            List<Universidades> attachedUniversidadesListNew = new ArrayList<Universidades>();
            for (Universidades universidadesListNewUniversidadesToAttach : universidadesListNew) {
                universidadesListNewUniversidadesToAttach = em.getReference(universidadesListNewUniversidadesToAttach.getClass(), universidadesListNewUniversidadesToAttach.getId());
                attachedUniversidadesListNew.add(universidadesListNewUniversidadesToAttach);
            }
            universidadesListNew = attachedUniversidadesListNew;
            paises.setUniversidadesList(universidadesListNew);
            List<Usuarios> attachedUsuariosListNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosListNewUsuariosToAttach : usuariosListNew) {
                usuariosListNewUsuariosToAttach = em.getReference(usuariosListNewUsuariosToAttach.getClass(), usuariosListNewUsuariosToAttach.getId());
                attachedUsuariosListNew.add(usuariosListNewUsuariosToAttach);
            }
            usuariosListNew = attachedUsuariosListNew;
            paises.setUsuariosList(usuariosListNew);
            paises = em.merge(paises);
            for (Universidades universidadesListOldUniversidades : universidadesListOld) {
                if (!universidadesListNew.contains(universidadesListOldUniversidades)) {
                    universidadesListOldUniversidades.setPaises(null);
                    universidadesListOldUniversidades = em.merge(universidadesListOldUniversidades);
                }
            }
            for (Universidades universidadesListNewUniversidades : universidadesListNew) {
                if (!universidadesListOld.contains(universidadesListNewUniversidades)) {
                    Paises oldPaisesOfUniversidadesListNewUniversidades = universidadesListNewUniversidades.getPaises();
                    universidadesListNewUniversidades.setPaises(paises);
                    universidadesListNewUniversidades = em.merge(universidadesListNewUniversidades);
                    if (oldPaisesOfUniversidadesListNewUniversidades != null && !oldPaisesOfUniversidadesListNewUniversidades.equals(paises)) {
                        oldPaisesOfUniversidadesListNewUniversidades.getUniversidadesList().remove(universidadesListNewUniversidades);
                        oldPaisesOfUniversidadesListNewUniversidades = em.merge(oldPaisesOfUniversidadesListNewUniversidades);
                    }
                }
            }
            for (Usuarios usuariosListOldUsuarios : usuariosListOld) {
                if (!usuariosListNew.contains(usuariosListOldUsuarios)) {
                    usuariosListOldUsuarios.setPaises(null);
                    usuariosListOldUsuarios = em.merge(usuariosListOldUsuarios);
                }
            }
            for (Usuarios usuariosListNewUsuarios : usuariosListNew) {
                if (!usuariosListOld.contains(usuariosListNewUsuarios)) {
                    Paises oldPaisesOfUsuariosListNewUsuarios = usuariosListNewUsuarios.getPaises();
                    usuariosListNewUsuarios.setPaises(paises);
                    usuariosListNewUsuarios = em.merge(usuariosListNewUsuarios);
                    if (oldPaisesOfUsuariosListNewUsuarios != null && !oldPaisesOfUsuariosListNewUsuarios.equals(paises)) {
                        oldPaisesOfUsuariosListNewUsuarios.getUsuariosList().remove(usuariosListNewUsuarios);
                        oldPaisesOfUsuariosListNewUsuarios = em.merge(oldPaisesOfUsuariosListNewUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = paises.getCodigo();
                if (findPaises(id) == null) {
                    throw new NonexistentEntityException("The paises with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paises paises;
            try {
                paises = em.getReference(Paises.class, id);
                paises.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paises with id " + id + " no longer exists.", enfe);
            }
            List<Universidades> universidadesList = paises.getUniversidadesList();
            for (Universidades universidadesListUniversidades : universidadesList) {
                universidadesListUniversidades.setPaises(null);
                universidadesListUniversidades = em.merge(universidadesListUniversidades);
            }
            List<Usuarios> usuariosList = paises.getUsuariosList();
            for (Usuarios usuariosListUsuarios : usuariosList) {
                usuariosListUsuarios.setPaises(null);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
            }
            em.remove(paises);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paises> findPaisesEntities() {
        return findPaisesEntities(true, -1, -1);
    }

    public List<Paises> findPaisesEntities(int maxResults, int firstResult) {
        return findPaisesEntities(false, maxResults, firstResult);
    }

    private List<Paises> findPaisesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paises.class));
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

    public Paises findPaises(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paises.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaisesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paises> rt = cq.from(Paises.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
