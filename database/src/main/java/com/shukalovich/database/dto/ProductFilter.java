package com.shukalovich.database.dto;

import com.shukalovich.database.entity.enam.Brand;

public record ProductFilter(Brand brand,
                            double price,
                            int ram,
                            int limit,
                            int page) {
}