package com.shukalovich.database.dao;

import com.shukalovich.database.entity.BaseEntity;
import lombok.AllArgsConstructor;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.Optional;

@AllArgsConstructor
public abstract class Dao<K extends Serializable, E extends BaseEntity> {

    private Class<E> clazz;

    public E findById(Session session, K id) {
        return session.get(clazz, id);
    }

    public Optional<E> save(Session session, E entity) {
        session.persist(entity);
        return Optional.ofNullable(entity);
    }

    public Optional<E> update(Session session, E entity) {
        session.merge(entity);
        return Optional.ofNullable(entity);
    }

    public boolean delete(Session session, K id) {
        return Optional.ofNullable(session.get(clazz, id))
                .map(entity -> {
                    session.remove(entity);
                    return true;
                })
                .orElse(false);
    }
}