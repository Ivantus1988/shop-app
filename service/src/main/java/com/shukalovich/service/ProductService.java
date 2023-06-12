package com.shukalovich.service;

import com.shukalovich.database.dao.ProductDao;
import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.hibernate.HibernateFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductService {
    private static final ProductService INSTANCE = new ProductService();
    private final ProductDao productDao = ProductDao.getInstance();

    private final HibernateFactory hibernateFactory = HibernateFactory.getInstance();

    public List<ProductEntity> findAll() {
        List<ProductEntity> products;
        try (var session = hibernateFactory.getSession()) {
            session.beginTransaction();
            products = productDao.findAll(session);
            session.getTransaction().commit();
        }
        return products;
    }

    public List<ProductEntity> findByFilter(ProductFilter filter) {
        List<ProductEntity> products;
        try (var session = hibernateFactory.getSession()) {
            session.beginTransaction();
            products = productDao.findByFilter(session, filter);
            session.getTransaction().commit();
        }
        return products;
    }


    public ProductEntity findById(Long id) {
        ProductEntity product;
        try (var session = hibernateFactory.getSession()) {
            session.beginTransaction();
            product = productDao.findById(session, id);
            session.getTransaction().commit();
        }
        return product;
    }


    public Optional<ProductEntity> update(ProductEntity product) {
        try (var session = hibernateFactory.getSession()) {
            session.beginTransaction();
            productDao.update(session, product);
            session.getTransaction().commit();
        }
        return Optional.ofNullable(product);
    }


    public Optional<ProductEntity> save(ProductEntity product) {
        Optional<ProductEntity> newProduct;
        try (var session = hibernateFactory.getSession()) {
            session.beginTransaction();
            newProduct = productDao.save(session, product);
            session.getTransaction().commit();
        }
        return newProduct;
    }

    public boolean delete(Long id) {
        try (var session = hibernateFactory.getSession()) {
            session.getTransaction();
            ProductEntity removedProduct = session.get(ProductEntity.class, id);
            if (removedProduct == null) {
                session.getTransaction().commit();
                return false;
            }
            productDao.delete(session, id);
            session.getTransaction().commit();
        }
        return true;
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }
}
