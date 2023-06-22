package com.shukalovich.database.repository;

import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepositoryExtension {

    List<ProductEntity> findByFilter(ProductFilter filter);
}
