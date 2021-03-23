/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Uvaurltoid;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface UvaurltoidFacadeLocal {

    void create(Uvaurltoid uvaurltoid);

    void edit(Uvaurltoid uvaurltoid);

    void remove(Uvaurltoid uvaurltoid);

    Uvaurltoid find(Object id);

    List<Uvaurltoid> findAll();

    List<Uvaurltoid> findRange(int[] range);

    int count();
    
}
