/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Lenguajes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface LenguajesFacadeLocal {

    void create(Lenguajes lenguajes);

    void edit(Lenguajes lenguajes);

    void remove(Lenguajes lenguajes);

    Lenguajes find(Object id);

    List<Lenguajes> findAll();

    List<Lenguajes> findRange(int[] range);

    int count();
    
}
