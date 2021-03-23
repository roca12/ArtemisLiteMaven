/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Temario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface TemarioFacadeLocal {

    void create(Temario temario);

    void edit(Temario temario);

    void remove(Temario temario);

    Temario find(Object id);

    List<Temario> findAll();

    List<Temario> findRange(int[] range);

    int count();
    
}
