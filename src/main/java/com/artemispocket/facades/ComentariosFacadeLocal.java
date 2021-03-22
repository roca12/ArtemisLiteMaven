/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Comentarios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface ComentariosFacadeLocal {

    void create(Comentarios comentarios);

    void edit(Comentarios comentarios);

    void remove(Comentarios comentarios);

    Comentarios find(Object id);

    List<Comentarios> findAll();

    List<Comentarios> findRange(int[] range);

    int count();
    
}
