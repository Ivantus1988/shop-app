package com.shukalovich.database.dao;

import com.shukalovich.database.entity.Role;
import com.shukalovich.database.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {
    private static final int ID = 1;

    @org.junit.Test
    public void getUser() {
        UserDao userDao = UserDao.getInstance();
        assertEquals(User.builder()
                .id(ID)
                .name("Bob")
                .surname("Johnson")
                .email("example@gmail.com")
                .role(Role.USER)
                .build(), userDao.getUser());
    }
}