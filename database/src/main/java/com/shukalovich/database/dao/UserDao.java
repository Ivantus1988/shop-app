package com.shukalovich.database.dao;

import com.shukalovich.database.DummyDatabase;
import com.shukalovich.database.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDao implements Dao<Long, User> {

    private final DummyDatabase db = DummyDatabase.getInstance();

    private static final UserDao INSTANCE = new UserDao();


    public Optional<User> findByEmailAndPass(String email, String password) {
        return db.getUsers().values().stream()
                .filter(user -> user.getEmail().equals(email))
                .filter(user -> user.getPassword().equals(password))
                .findAny();
    }

    @Override
    public User delete(Long id) {
        return Optional.ofNullable(db.getUsers().remove(id))
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(db.getUsers().values());
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(db.getUsers().get(id));
    }

    @Override
    public void update(User product) {
    }

    @Override
    public User save(User user) {
        Long dummyID = db.getUsers().keySet().size() + 1L;
        user.setId(dummyID);
        return db.getUsers().put(dummyID, user);
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
