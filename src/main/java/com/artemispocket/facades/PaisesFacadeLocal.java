/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Paises;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface PaisesFacadeLocal {

    void create(Paises paises);

    void edit(Paises paises);

    void remove(Paises paises);

    Paises find(Object id);

    List<Paises> findAll();

    List<Paises> findRange(int[] range);

    int count();
    
}
