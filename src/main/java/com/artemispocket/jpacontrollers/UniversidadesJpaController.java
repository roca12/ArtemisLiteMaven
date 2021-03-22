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
import com.artemispocket.entities.Ciudades;
import com.artemispocket.entities.Paises;
import com.artemispocket.entities.Usuarios;
import java.util.ArrayList;
import java.util.List;
import com.artemispocket.entities.CertInscripcion;
import com.artemispocket.entities.Universidades;
import com.artemispocket.jpacontrollers.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author roca12
 */
public class UniversidadesJpaController implements Serializable {

    public UniversidadesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Universidades universidades) {
        if (universidades.getUsuariosList() == null) {
            universidades.setUsuariosList(new ArrayList<Usuarios>());
        }
        if (universidades.getCertInscripcionList() == null) {
            universidades.setCertInscripcionList(new ArrayList<CertInscripcion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ciudades ciudades = universidades.getCiudades();
            if (ciudades != null) {
                ciudades = em.getReference(ciudades.getClass(), ciudades.getIdCiudades());
                universidades.setCiudades(ciudades);
            }
            Paises paises = universidades.getPaises();
            if (paises != null) {
                paises = em.getReference(paises.getClass(), paises.getCodigo());
                universidades.setPaises(paises);
            }
            List<Usuarios> attachedUsuariosList = new ArrayList<Usuarios>();
            for (Usuarios usuariosListUsuariosToAttach : universidades.getUsuariosList()) {
                usuariosListUsuariosToAttach = em.getReference(usuariosListUsuariosToAttach.getClass(), usuariosListUsuariosToAttach.getId());
                attachedUsuariosList.add(usuariosListUsuariosToAttach);
            }
            universidades.setUsuariosList(attachedUsuariosList);
            List<CertInscripcion> attachedCertInscripcionList = new ArrayList<CertInscripcion>();
            for (CertInscripcion certInscripcionListCertInscripcionToAttach : universidades.getCertInscripcionList()) {
                certInscripcionListCertInscripcionToAttach = em.getReference(certInscripcionListCertInscripcionToAttach.getClass(), certInscripcionListCertInscripcionToAttach.getId());
                attachedCertInscripcionList.add(certInscripcionListCertInscripcionToAttach);
            }
            universidades.setCertInscripcionList(attachedCertInscripcionList);
            em.persist(universidades);
            if (ciudades != null) {
                ciudades.getUniversidadesList().add(universidades);
                ciudades = em.merge(ciudades);
            }
            if (paises != null) {
                paises.getUniversidadesList().add(universidades);
                paises = em.merge(paises);
            }
            for (Usuarios usuariosListUsuarios : universidades.getUsuariosList()) {
                Universidades oldUniversidadesOfUsuariosListUsuarios = usuariosListUsuarios.getUniversidades();
                usuariosListUsuarios.setUniversidades(universidades);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
                if (oldUniversidadesOfUsuariosListUsuarios != null) {
                    oldUniversidadesOfUsuariosListUsuarios.getUsuariosList().remove(usuariosListUsuarios);
                    oldUniversidadesOfUsuariosListUsuarios = em.merge(oldUniversidadesOfUsuariosListUsuarios);
                }
            }
            for (CertInscripcion certInscripcionListCertInscripcion : universidades.getCertInscripcionList()) {
                Universidades oldUniversidadesOfCertInscripcionListCertInscripcion = certInscripcionListCertInscripcion.getUniversidades();
                certInscripcionListCertInscripcion.setUniversidades(universidades);
                certInscripcionListCertInscripcion = em.merge(certInscripcionListCertInscripcion);
                if (oldUniversidadesOfCertInscripcionListCertInscripcion != null) {
                    oldUniversidadesOfCertInscripcionListCertInscripcion.getCertInscripcionList().remove(certInscripcionListCertInscripcion);
                    oldUniversidadesOfCertInscripcionListCertInscripcion = em.merge(oldUniversidadesOfCertInscripcionListCertInscripcion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Universidades universidades) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Universidades persistentUniversidades = em.find(Universidades.class, universidades.getId());
            Ciudades ciudadesOld = persistentUniversidades.getCiudades();
            Ciudades ciudadesNew = universidades.getCiudades();
            Paises paisesOld = persistentUniversidades.getPaises();
            Paises paisesNew = universidades.getPaises();
            List<Usuarios> usuariosListOld = persistentUniversidades.getUsuariosList();
            List<Usuarios> usuariosListNew = universidades.getUsuariosList();
            List<CertInscripcion> certInscripcionListOld = persistentUniversidades.getCertInscripcionList();
            List<CertInscripcion> certInscripcionListNew = universidades.getCertInscripcionList();
            if (ciudadesNew != null) {
                ciudadesNew = em.getReference(ciudadesNew.getClass(), ciudadesNew.getIdCiudades());
                universidades.setCiudades(ciudadesNew);
            }
            if (paisesNew != null) {
                paisesNew = em.getReference(paisesNew.getClass(), paisesNew.getCodigo());
                universidades.setPaises(paisesNew);
            }
            List<Usuarios> attachedUsuariosListNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosListNewUsuariosToAttach : usuariosListNew) {
                usuariosListNewUsuariosToAttach = em.getReference(usuariosListNewUsuariosToAttach.getClass(), usuariosListNewUsuariosToAttach.getId());
                attachedUsuariosListNew.add(usuariosListNewUsuariosToAttach);
            }
            usuariosListNew = attachedUsuariosListNew;
            universidades.setUsuariosList(usuariosListNew);
            List<CertInscripcion> attachedCertInscripcionListNew = new ArrayList<CertInscripcion>();
            for (CertInscripcion certInscripcionListNewCertInscripcionToAttach : certInscripcionListNew) {
                certInscripcionListNewCertInscripcionToAttach = em.getReference(certInscripcionListNewCertInscripcionToAttach.getClass(), certInscripcionListNewCertInscripcionToAttach.getId());
                attachedCertInscripcionListNew.add(certInscripcionListNewCertInscripcionToAttach);
            }
            certInscripcionListNew = attachedCertInscripcionListNew;
            universidades.setCertInscripcionList(certInscripcionListNew);
            universidades = em.merge(universidades);
            if (ciudadesOld != null && !ciudadesOld.equals(ciudadesNew)) {
                ciudadesOld.getUniversidadesList().remove(universidades);
                ciudadesOld = em.merge(ciudadesOld);
            }
            if (ciudadesNew != null && !ciudadesNew.equals(ciudadesOld)) {
                ciudadesNew.getUniversidadesList().add(universidades);
                ciudadesNew = em.merge(ciudadesNew);
            }
            if (paisesOld != null && !paisesOld.equals(paisesNew)) {
                paisesOld.getUniversidadesList().remove(universidades);
                paisesOld = em.merge(paisesOld);
            }
            if (paisesNew != null && !paisesNew.equals(paisesOld)) {
                paisesNew.getUniversidadesList().add(universidades);
                paisesNew = em.merge(paisesNew);
            }
            for (Usuarios usuariosListOldUsuarios : usuariosListOld) {
                if (!usuariosListNew.contains(usuariosListOldUsuarios)) {
                    usuariosListOldUsuarios.setUniversidades(null);
                    usuariosListOldUsuarios = em.merge(usuariosListOldUsuarios);
                }
            }
            for (Usuarios usuariosListNewUsuarios : usuariosListNew) {
                if (!usuariosListOld.contains(usuariosListNewUsuarios)) {
                    Universidades oldUniversidadesOfUsuariosListNewUsuarios = usuariosListNewUsuarios.getUniversidades();
                    usuariosListNewUsuarios.setUniversidades(universidades);
                    usuariosListNewUsuarios = em.merge(usuariosListNewUsuarios);
                    if (oldUniversidadesOfUsuariosListNewUsuarios != null && !oldUniversidadesOfUsuariosListNewUsuarios.equals(universidades)) {
                        oldUniversidadesOfUsuariosListNewUsuarios.getUsuariosList().remove(usuariosListNewUsuarios);
                        oldUniversidadesOfUsuariosListNewUsuarios = em.merge(oldUniversidadesOfUsuariosListNewUsuarios);
                    }
                }
            }
            for (CertInscripcion certInscripcionListOldCertInscripcion : certInscripcionListOld) {
                if (!certInscripcionListNew.contains(certInscripcionListOldCertInscripcion)) {
                    certInscripcionListOldCertInscripcion.setUniversidades(null);
                    certInscripcionListOldCertInscripcion = em.merge(certInscripcionListOldCertInscripcion);
                }
            }
            for (CertInscripcion certInscripcionListNewCertInscripcion : certInscripcionListNew) {
                if (!certInscripcionListOld.contains(certInscripcionListNewCertInscripcion)) {
                    Universidades oldUniversidadesOfCertInscripcionListNewCertInscripcion = certInscripcionListNewCertInscripcion.getUniversidades();
                    certInscripcionListNewCertInscripcion.setUniversidades(universidades);
                    certInscripcionListNewCertInscripcion = em.merge(certInscripcionListNewCertInscripcion);
                    if (oldUniversidadesOfCertInscripcionListNewCertInscripcion != null && !oldUniversidadesOfCertInscripcionListNewCertInscripcion.equals(universidades)) {
                        oldUniversidadesOfCertInscripcionListNewCertInscripcion.getCertInscripcionList().remove(certInscripcionListNewCertInscripcion);
                        oldUniversidadesOfCertInscripcionListNewCertInscripcion = em.merge(oldUniversidadesOfCertInscripcionListNewCertInscripcion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = universidades.getId();
                if (findUniversidades(id) == null) {
                    throw new NonexistentEntityException("The universidades with id " + id + " no longer exists.");
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
            Universidades universidades;
            try {
                universidades = em.getReference(Universidades.class, id);
                universidades.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The universidades with id " + id + " no longer exists.", enfe);
            }
            Ciudades ciudades = universidades.getCiudades();
            if (ciudades != null) {
                ciudades.getUniversidadesList().remove(universidades);
                ciudades = em.merge(ciudades);
            }
            Paises paises = universidades.getPaises();
            if (paises != null) {
                paises.getUniversidadesList().remove(universidades);
                paises = em.merge(paises);
            }
            List<Usuarios> usuariosList = universidades.getUsuariosList();
            for (Usuarios usuariosListUsuarios : usuariosList) {
                usuariosListUsuarios.setUniversidades(null);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
            }
            List<CertInscripcion> certInscripcionList = universidades.getCertInscripcionList();
            for (CertInscripcion certInscripcionListCertInscripcion : certInscripcionList) {
                certInscripcionListCertInscripcion.setUniversidades(null);
                certInscripcionListCertInscripcion = em.merge(certInscripcionListCertInscripcion);
            }
            em.remove(universidades);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Universidades> findUniversidadesEntities() {
        return findUniversidadesEntities(true, -1, -1);
    }

    public List<Universidades> findUniversidadesEntities(int maxResults, int firstResult) {
        return findUniversidadesEntities(false, maxResults, firstResult);
    }

    private List<Universidades> findUniversidadesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Universidades.class));
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

    public Universidades findUniversidades(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Universidades.class, id);
        } finally {
            em.close();
        }
    }

    public int getUniversidadesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Universidades> rt = cq.from(Universidades.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
