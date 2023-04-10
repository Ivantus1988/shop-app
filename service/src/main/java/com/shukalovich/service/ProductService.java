package com.shukalovich.service;

import com.shukalovich.database.dao.ProductDao;
import com.shukalovich.database.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductService {
    private static final ProductService INSTANCE = new ProductService();
    private final ProductDao productDao = ProductDao.getInstance();

    public List<Product> findAll() {
        return productDao.findAll();
    }

    public Product findById(Integer id) {
        return productDao.findById(id)
                .orElse(Product.builder()
                        .model("3310")
                .build());
    }

    public static ProductService getInstance() {
        return INSTANCE;
    }
}
