/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Juecesonline;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface JuecesonlineFacadeLocal {

    void create(Juecesonline juecesonline);

    void edit(Juecesonline juecesonline);

    void remove(Juecesonline juecesonline);

    Juecesonline find(Object id);

    List<Juecesonline> findAll();

    List<Juecesonline> findRange(int[] range);

    int count();
    
}
