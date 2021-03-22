/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.AuditoriaAcceso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface AuditoriaAccesoFacadeLocal {

    void create(AuditoriaAcceso auditoriaAcceso);

    void edit(AuditoriaAcceso auditoriaAcceso);

    void remove(AuditoriaAcceso auditoriaAcceso);

    AuditoriaAcceso find(Object id);

    List<AuditoriaAcceso> findAll();

    List<AuditoriaAcceso> findRange(int[] range);

    int count();
    
}
