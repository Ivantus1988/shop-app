package com.shukalovich.database.repository;

import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.entity.ProductEntity_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryExtensionImpl implements ProductRepositoryExtension {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<ProductEntity> findByFilter(ProductFilter filter) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> productRoot = query.from(ProductEntity.class);
        query.select(productRoot);
        List<Predicate> predicates = collectPredicates(filter, cb, productRoot);

        query.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(query)
                .setMaxResults(filter.getLimit())
                .setFirstResult(filter.getOffset())
                .getResultList();
    }

    private static List<Predicate> collectPredicates(ProductFilter filter,
                                                     CriteriaBuilder cb,
                                                     Root<ProductEntity> productRoot) {
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
}
