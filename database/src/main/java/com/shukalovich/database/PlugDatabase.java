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
public class PlugDatabase {

    private static final PlugDatabase INSTANCE = new PlugDatabase();

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

    public static PlugDatabase getInstance() {
        return INSTANCE;
    }
}
