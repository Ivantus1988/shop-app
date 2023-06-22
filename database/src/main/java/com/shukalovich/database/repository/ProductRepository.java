package com.shukalovich.database.repository;

import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.entity.enam.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryExtension {

    List<ProductEntity> findAllByBrand(Brand brand);

    Optional<ProductEntity> findByModel(String model);
}
