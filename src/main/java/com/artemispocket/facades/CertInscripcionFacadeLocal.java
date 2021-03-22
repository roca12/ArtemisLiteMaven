/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.CertInscripcion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface CertInscripcionFacadeLocal {

    void create(CertInscripcion certInscripcion);

    void edit(CertInscripcion certInscripcion);

    void remove(CertInscripcion certInscripcion);

    CertInscripcion find(Object id);

    List<CertInscripcion> findAll();

    List<CertInscripcion> findRange(int[] range);

    int count();
    
}
