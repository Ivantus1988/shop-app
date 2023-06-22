package com.shukalovich.service;

import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.ProductEntity;
import com.shukalovich.database.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductEntity> findAll() {
        return productRepository.findAll();
    }

    public List<ProductEntity> findByFilter(ProductFilter filter) {
        return productRepository.findByFilter(filter);
    }

    public ProductEntity findById(Long id) {
        return productRepository.findById(id)
                .orElse(ProductEntity.builder().build());
    }


    public Optional<ProductEntity> update(ProductEntity product) {
        return Optional.of(productRepository.save(product));
    }

    public Optional<ProductEntity> save(ProductEntity product) {
        return Optional.of(productRepository.save(product));
    }

    public boolean delete(Long id) {
        Optional<ProductEntity> removedProduct = productRepository.findById(id);
        removedProduct.get();
        productRepository.delete(removedProduct.get());
        return true;
    }
}
