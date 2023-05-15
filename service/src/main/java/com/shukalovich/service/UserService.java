package com.shukalovich.service;

import com.shukalovich.database.dao.UserDao;
import com.shukalovich.database.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    public Optional<User> findByEmailAndPass(String email, String password) {
        return userDao.findByEmailAndPass(email, password);
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public List<User> findAll() {return userDao.findAll(); }

    public Optional<User> update(User user) {return userDao.update(user); }

    public boolean delete(Long id) {return userDao.delete(id); }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
