package com.shukalovich.database.dao;

import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.entity.ProductEntity_;
import com.shukalovich.database.entity.enam.Brand;
import jakarta.persistence.criteria.Predicate;
import org.hibernate.Session;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.hibernate.query.criteria.JpaRoot;

import java.util.ArrayList;
import java.util.List;

public final class ProductDao extends Dao<Long, ProductEntity> {
    private static final ProductDao INSTANCE = new ProductDao();

    private ProductDao() {
        super(ProductEntity.class);
    }

    public List<ProductEntity> findAll(Session session) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<ProductEntity> query = cb.createQuery(ProductEntity.class);
        JpaRoot<ProductEntity> productRoot = query.from(ProductEntity.class);
        query.select(productRoot);
        return session.createQuery(query).list();
    }

    public List<ProductEntity> findAllByBrand(Session session, Brand brand) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<ProductEntity> query = cb.createQuery(ProductEntity.class);
        JpaRoot<ProductEntity> productRoot = query.from(ProductEntity.class);
        query.select(productRoot);
        query.where(cb.equal(productRoot.get(ProductEntity_.BRAND), brand));
        return session.createQuery(query).list();
    }


    public List<ProductEntity> findByFilter(Session session, ProductFilter filter) {
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<ProductEntity> query = cb.createQuery(ProductEntity.class);
        JpaRoot<ProductEntity> productRoot = query.from(ProductEntity.class);
        query.select(productRoot);
        List<Predicate> predicates = collectPredicates(filter, cb, productRoot);

        query.where(predicates.toArray(Predicate[]::new));

        return session.createQuery(query)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .list();
    }

    private static List<Predicate> collectPredicates(ProductFilter filter,
                                                     HibernateCriteriaBuilder cb,
                                                     JpaRoot<ProductEntity> productRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getMemorySize() != null) {
            predicates.add(cb.le(productRoot.get(ProductEntity_.MEMORY_SIZE),
                    filter.getMemorySize()));
        }
        if (filter.getPrice() != null) {
            predicates.add(cb.le(productRoot.get(ProductEntity_.PRICE),
                    filter.getPrice()));
        }
        if (filter.getRam() != null) {
            predicates.add(cb.le(productRoot.get(ProductEntity_.RAM),
                    filter.getRam()));
        }
        return predicates;
    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}