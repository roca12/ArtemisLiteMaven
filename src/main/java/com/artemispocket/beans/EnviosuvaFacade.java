/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.beans;

import com.artemispocket.entities.Enviosuva;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author roca12
 */
@Stateless
public class EnviosuvaFacade extends AbstractFacade<Enviosuva> {


    @PersistenceContext(unitName = "com.roca12artemis_MiniArtemisMaven_war_1.0-SNAPSHOTPU")

    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnviosuvaFacade() {
        super(Enviosuva.class);
    }
    
}
