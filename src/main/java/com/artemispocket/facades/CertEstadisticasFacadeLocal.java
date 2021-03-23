/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.CertEstadisticas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface CertEstadisticasFacadeLocal {

    void create(CertEstadisticas certEstadisticas);

    void edit(CertEstadisticas certEstadisticas);

    void remove(CertEstadisticas certEstadisticas);

    CertEstadisticas find(Object id);

    List<CertEstadisticas> findAll();

    List<CertEstadisticas> findRange(int[] range);

    int count();
    
}
