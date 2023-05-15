package com.shukalovich.database.dao;

import com.shukalovich.database.connection.ConnectionPool;
import com.shukalovich.database.entity.User;
import com.shukalovich.database.entity.enam.Gender;
import com.shukalovich.database.entity.enam.Role;
import com.shukalovich.database.exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDao implements Dao<Long, User> {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE_SQL = """
            INSERT INTO users
                (name,
                surname,
                email,
                password,
                role,
                gender)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String FIND_ALL_SQL = """
            SELECT id,
            name,
            surname,
            email,
            password,
            gender,
            role
            FROM users
            """;

    private static final String FIND_PASSWORD_AND_EMAIL = """
            SELECT id,
                   name,
                   surname,
                   email,
                   password,
                   gender,
                   role
            FROM users
            WHERE email = ?
              AND password = ?
                   """;

    private static final String DELETE_SQL = """
            DELETE 
            FROM users
            WHERE id = ?         
            """;

    private static final String FIND_BY_ID_SQL = """
            SELECT id,
            name,
            surname,
            email,
            password,
            gender,
            role
            FROM users
            WHERE id = ?
            """;

    private static final String UPDATE_SQL = """
            UPDATE users
            SET name = ?,
            surname = ?,
            email = ?,
            password = ?,
            gender = ?, 
            role = ?
            WHERE id = ?
            """;


    public Optional<User> findByEmailAndPass(String email, String password) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(FIND_PASSWORD_AND_EMAIL)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            var resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? Optional.of(buildUser(resultSet))
                    : Optional.empty();
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public boolean delete(Long id) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(DELETE_SQL)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (var connection = ConnectionPool.get();
             var statement = connection.createStatement()) {
            var resultSet = statement.executeQuery(FIND_ALL_SQL);
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
        return users;
    }

    @Override
    public Optional<User> findById(Long id) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            User user = null;
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
    }

    @Override
    public Optional<User> update(User user) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(UPDATE_SQL)) {
            setPrepareStatement(user, preparedStatement);
            preparedStatement.setLong(5, user.getId());
            preparedStatement.executeUpdate();
            return Optional.of(user);
        } catch (SQLException throwable) {
            throw new RuntimeException(throwable);
        }
    }

    @Override
    public User save(User user) {
        try (var connection = ConnectionPool.get();
             var preparedStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            setPrepareStatement(user, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException throwable) {
            throw new DaoException(throwable);
        }
        return user;
    }

    private static void setPrepareStatement(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, String.valueOf(user.getRole()));
        preparedStatement.setString(6, String.valueOf(user.getGender()));
    }

    private static User buildUser(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .email(resultSet.getString("email"))
                .password(resultSet.getString("password"))
                .gender(Gender.valueOf(resultSet.getString("gender")))
                .role(Role.valueOf(resultSet.getString("role")))
                .build();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
