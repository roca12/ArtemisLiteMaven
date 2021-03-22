/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artemispocket.facades;

import com.artemispocket.entities.Feed;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author roca12
 */
@Local
public interface FeedFacadeLocal {

    void create(Feed feed);

    void edit(Feed feed);

    void remove(Feed feed);

    Feed find(Object id);

    List<Feed> findAll();

    List<Feed> findRange(int[] range);

    int count();
    
}
