package com.shukalovich.database.dao;

import com.shukalovich.database.PlugDatabase;
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
    private final PlugDatabase db = PlugDatabase.getInstance();

    private static final int ID = 1;
    private static final String MODEL = "S22";
    private static final double PRICE = 2000.2;
    private static final Brand BRAND = Brand.SAMSUNG;

    public Product getProduct() {
        return Product.builder()
                .id(ID)
                .model(MODEL)
                .price(PRICE)
                .brand(BRAND).
                build();
    }

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
