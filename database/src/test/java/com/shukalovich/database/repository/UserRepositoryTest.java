package com.shukalovich.database.repository;

import com.shukalovich.database.config.DatabaseConfig;
import com.shukalovich.database.entity.Address;
import com.shukalovich.database.entity.UserEntity;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.shukalovich.database.entity.enam.Gender.MALE;
import static com.shukalovich.database.entity.enam.Role.USER;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DatabaseConfig.class})
@Sql(value = "classpath:test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:purge-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    void whenFindAllInvoked_ThenAllUsersReturned() {
        String[] actual = userRepository.findAll()
                .stream()
                .map(UserEntity::getName)
                .toArray(String[]::new);
        String[] expected = List.of("Ivan", "Petr")
                .toArray(String[]::new);
        assertArrayEquals(expected, actual);
    }

    @Test
    @Order(2)
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

        userRepository.save(testUser);

        List<String> users = userRepository.findAll()
                .stream()
                .map(UserEntity::getEmail)
                .toList();

        assertTrue(users.contains(testUser.getEmail()));
        userRepository.delete(testUser);
    }

    @Test
    @Order(3)
    void whenFindById_ThenReturnedValidUser() {
        Optional<UserEntity> actual = userRepository.findByName("Ivan");
        assertNotNull(actual);
        assertEquals("Ivan", actual.get().getName());
    }
}