package com.shukalovich.service;

import com.shukalovich.database.dao.ProductDao;
import com.shukalovich.database.dto.ProductFilter;
import com.shukalovich.database.entity.enam.Brand;
import com.shukalovich.database.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductService {
    private static final ProductService INSTANCE = new ProductService();
    private final ProductDao productDao = ProductDao.getInstance();
    public List<Product> findAll() {
        return productDao.findAll();
    }

    public List<Product> findByFilter(ProductFilter filter) {
        return productDao.findByFilter(filter);
    }


    public Product findById(Long id) {
        return productDao.findById(id)
                .orElse(Product.builder()
                        .brand(Brand.NOKIA)
                        .model("3310")
                        .build());
    }

    public Optional<Product> update(Product product) {
        return productDao.update(product);
    }

    public Optional<Product> save(Product product) {
        return Optional.ofNullable(productDao.save(product));
    }

    public boolean delete(Long id) {return productDao.delete(id);}

    public static ProductService getInstance() {
        return INSTANCE;
    }
}
