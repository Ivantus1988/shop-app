package com.shukalovich.database.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    E delete(K id);

    List<E> findAll();

    Optional<E> findById(K id);

    void update(E product);

    E save(E product);

}