/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Enviosuva;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface EnviosuvaFacadeLocal {

    void create(Enviosuva enviosuva);

    void edit(Enviosuva enviosuva);

    void remove(Enviosuva enviosuva);

    Enviosuva find(Object id);

    List<Enviosuva> findAll();

    List<Enviosuva> findRange(int[] range);

    int count();
    
}
