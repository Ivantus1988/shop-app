package com.shukalovich.service;

import com.shukalovich.database.dao.UserDao;
import com.shukalovich.database.entity.Role;
import com.shukalovich.database.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class UserService {

    private static final UserService INSTANCE = new UserService();
    private final UserDao userDao = UserDao.getInstance();

    public String getUser() {
        User testUser = userDao.getUser();
        if (testUser.getRole() == Role.USER) {
            return "Hi "
                   + testUser.getName()
                   + ".";
        } else {
            return "Hi admin";
        }
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
