/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Universidades;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface UniversidadesFacadeLocal {

    void create(Universidades universidades);

    void edit(Universidades universidades);

    void remove(Universidades universidades);

    Universidades find(Object id);

    List<Universidades> findAll();

    List<Universidades> findRange(int[] range);

    int count();
    
}
