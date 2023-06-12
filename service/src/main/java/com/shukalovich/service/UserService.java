package com.shukalovich.service;

import com.shukalovich.database.dao.UserDao;
import com.shukalovich.database.entity.UserEntity;
import com.shukalovich.database.hibernate.HibernateFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();
    private final HibernateFactory hibernateFactory = HibernateFactory.getInstance();

    public Optional<UserEntity> findByEmailAndPass(String email, String password) {
        Optional<UserEntity> byEmailAndPass;
        try (var session = hibernateFactory.getSession()) {
            session.beginTransaction();
            byEmailAndPass = userDao.findByEmailAndPass(session, email, password);
            session.getTransaction().commit();
        }
        return byEmailAndPass;
    }

    public Optional<UserEntity> save(UserEntity user) {
        Optional<UserEntity> newUser;
        try (var session = hibernateFactory.getSession()) {
            session.beginTransaction();
            newUser = userDao.save(session, user);
            session.getTransaction().commit();
        }
        return newUser;
    }

    public List<UserEntity> findAll() {
        List<UserEntity> users;
        try (var session = hibernateFactory.getSession()) {
            session.beginTransaction();
            users = userDao.findAll(session);
            session.getTransaction().commit();
        }
        return users;
    }

    public Optional<UserEntity> update(UserEntity user) {
        try (var session = hibernateFactory.getSession()) {
            session.beginTransaction();
            userDao.update(session, user);
            session.getTransaction().commit();
        }
        return Optional.ofNullable(user);
    }

    public boolean delete(Long id) {
        try (var session = hibernateFactory.getSession()) {
            session.getTransaction();
            UserEntity removedUser = session.get(UserEntity.class, id);
            if (removedUser == null) {
                session.getTransaction().commit();
                return false;
            }
            userDao.delete(session, id);
            session.getTransaction().commit();
        }
        return true;
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
