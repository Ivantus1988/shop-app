package com.shukalovich.database.dao;

import com.shukalovich.database.DummyDatabase;
import com.shukalovich.database.entity.User;
import static org.junit.Assert.*;

public class UserDaoTest {
    private final DummyDatabase db = DummyDatabase.getInstance();
    private static final long ID_USER_1 = 1;
    @org.junit.Test
    public void getUser() {
        User user = db.getUsers().get(ID_USER_1);
        assertEquals(User.builder()
                .id(ID_USER_1)
                .name("Ivan")
                .surname("Ivanov")
                .email("ivanov_ivan@gmail.com")
                .password("ivanov2000")
                .gender("Male")
                .build(), user);
    }
}