package com.shukalovich.database.dao;

import com.shukalovich.database.entity.Product;
import com.shukalovich.database.entity.Role;
import com.shukalovich.database.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDao implements Dao<Integer, User>{

    private static final UserDao INSTANCE = new UserDao();
    private static final int ID = 1;
    private static final String NAME = "Bob";
    private static final String SURNAME = "Johnson";
    private static final String EMAIL = "example@gmail.com";
    private static final Role ROLE = Role.USER;

    public User getUser() {
        return User.builder()
                .id(ID)
                .name(NAME)
                .surname(SURNAME)
                .email(EMAIL)
                .role(ROLE).
                build();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public User delete(Integer id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(User product) {

    }
    @Override
    public User save(User product) {
        return null;
    }
}
