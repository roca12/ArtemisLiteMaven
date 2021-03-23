/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Visitaspormesanio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface VisitaspormesanioFacadeLocal {

    void create(Visitaspormesanio visitaspormesanio);

    void edit(Visitaspormesanio visitaspormesanio);

    void remove(Visitaspormesanio visitaspormesanio);

    Visitaspormesanio find(Object id);

    List<Visitaspormesanio> findAll();

    List<Visitaspormesanio> findRange(int[] range);

    int count();
    
}
