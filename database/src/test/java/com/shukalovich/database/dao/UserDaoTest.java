package com.shukalovich.database.dao;

import com.shukalovich.database.entity.Address;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.entity.UserEntity;
import com.shukalovich.database.hibernate.HibernateFactory;
import lombok.Cleanup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.shukalovich.database.TestDataImporter.*;
import static com.shukalovich.database.entity.enam.Gender.MALE;
import static com.shukalovich.database.entity.enam.Role.USER;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDaoTest {
    private static final UserDao userDao = UserDao.getInstance();
    private static final HibernateFactory hibernateFactory = HibernateFactory.getInstance();

    @BeforeAll
    static void beforeAll() {
        @Cleanup var session = hibernateFactory.getSession();
        session.beginTransaction();
        importTestData(session);
        session.getTransaction().commit();
    }

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllUsersReturned() {
        @Cleanup var session = hibernateFactory.getSession();
        String[] actual = userDao.findAll(session)
                .stream()
                .map(UserEntity::getName)
                .toArray(String[]::new);
        String[] expected = List.of("Ivan", "Petr")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
    void whenFindAllByUser_ThenAllTheProductsOfUsersReturned() {
        @Cleanup var session = hibernateFactory.getSession();
        ProductEntity apple = session.get(ProductEntity.class, 1L);
        String[] actual = userDao.findAllByProduct(session, apple)
                .stream()
                .map(UserEntity::getName)
                .toArray(String[]::new);
        String[] expected = List.of("Ivan", "Petr")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(3)
    void whenCreatedInvokedWithUser_ThenUserSaved() {
        Address testAddress = Address.builder()
                .city("Minsk")
                .street("Pushkina")
                .building("10")
                .flat("12")
                .build();
        UserEntity testUser = UserEntity.builder()
                .name("Vasilii")
                .surname("Vasiliev")
                .email("vasilii@gmail.com")
                .role(USER)
                .gender(MALE)
                .password("1234")
                .address(testAddress)
                .build();

        @Cleanup var session = hibernateFactory.getSession();
        session.beginTransaction();
        userDao.save(session, testUser);
        session.getTransaction().commit();
        List<String> users = userDao.findAll(session)
                .stream()
                .map(UserEntity::getEmail)
                .toList();

        assertTrue(users.contains(testUser.getEmail()));
        session.beginTransaction();
        userDao.delete(session, testUser.getId());
        session.getTransaction().commit();
    }

    @Test
    @Order(4)
    void whenFindById_ThenReturnedValidUser() {
        @Cleanup var session = hibernateFactory.getSession();
        UserEntity actual = userDao.findById(session, 1l);
        assertNotNull(actual);
        assertEquals("Ivan", actual.getName());
    }
}