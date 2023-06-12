package com.shukalovich.database.dao;

import com.shukalovich.database.entity.OrderEntity_;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaJoin;
import org.hibernate.query.criteria.JpaRoot;

import java.util.List;
import java.util.Optional;

public final class UserDao extends Dao<Long, UserEntity> {

    private static final UserDao INSTANCE = new UserDao();

    private UserDao() {
        super(UserEntity.class);
    }

    public Optional<UserEntity> findByEmailAndPass(Session session, String email, String password) {
        return session
                .createQuery("SELECT user FROM UserEntity user WHERE email = :email AND password = :password",
                        UserEntity.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .uniqueResultOptional();
    }

    public List<UserEntity> findAllByProduct(Session session, ProductEntity product) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<UserEntity> query = cb.createQuery(UserEntity.class);
        JpaRoot<UserEntity> userRoot = query.from(UserEntity.class);
        query.select(userRoot);
        JpaJoin<Object, Object> orders = userRoot.join("orders");
        query.where(cb.equal(orders.get(OrderEntity_.PRODUCT), product));
        return session.createQuery(query).list();
    }

    public List<UserEntity> findAll(Session session) {
        return session.createQuery("SELECT user FROM UserEntity user", UserEntity.class).list();
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
