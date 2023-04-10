package com.shukalovich.database;


import com.shukalovich.database.entity.Brand;
import com.shukalovich.database.entity.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DummyDatabase {

    private static final DummyDatabase INSTANCE = new DummyDatabase();

    private final Map<Integer, Product> products = new HashMap<>() {{
        put(2, Product.builder()
                .model("A52")
                .id(2)
                .brand(Brand.SAMSUNG)
                .price(1233)
                .build());
        put(3, Product.builder()
                .id(3)
                .model("X9a")
                .brand(Brand.HONOR)
                .price(915.34)
                .build());
    }};

    public static DummyDatabase getInstance() {
        return INSTANCE;
    }
}
