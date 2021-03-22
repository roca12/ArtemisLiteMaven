/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Rangos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface RangosFacadeLocal {

    void create(Rangos rangos);

    void edit(Rangos rangos);

    void remove(Rangos rangos);

    Rangos find(Object id);

    List<Rangos> findAll();

    List<Rangos> findRange(int[] range);

    int count();
    
}
