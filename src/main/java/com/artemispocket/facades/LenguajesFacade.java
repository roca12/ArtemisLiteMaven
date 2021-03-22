/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Lenguajes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author roca12
 */
@Stateless
public class LenguajesFacade extends AbstractFacade<Lenguajes> implements LenguajesFacadeLocal {

    @PersistenceContext(unitName = "com.roca12artemis.MiniArtemisPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LenguajesFacade() {
        super(Lenguajes.class);
    }
    
}
