/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Ejercicios;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface EjerciciosFacadeLocal {

    void create(Ejercicios ejercicios);

    void edit(Ejercicios ejercicios);

    void remove(Ejercicios ejercicios);

    Ejercicios find(Object id);

    List<Ejercicios> findAll();

    List<Ejercicios> findRange(int[] range);

    int count();
    
}
