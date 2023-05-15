package com.shukalovich.database.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<K, E> {

    boolean delete(K id);

    List<E> findAll();

    Optional<E> findById(K id);

    Optional<E> update(E e);

    E save(E product);

}