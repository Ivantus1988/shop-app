package com.shukalovich.database.dao;

import com.shukalovich.database.DummyDatabase;
import com.shukalovich.database.entity.Brand;
import com.shukalovich.database.entity.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ProductDao implements Dao<Integer, Product>{
    private static final ProductDao INSTANCE = new ProductDao();
    private final DummyDatabase db = DummyDatabase.getInstance();

    public static ProductDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Product delete(Integer id) {
        return Optional.ofNullable(db.getProducts().remove(id))
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<> (db.getProducts().values());
    }

    @Override
    public Optional<Product> findById(Integer id) {
        return Optional.ofNullable(db.getProducts().get(id));
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public Product save(Product product) {
        return db.getProducts().put(product.getId(), product);
    }
}
