package com.shukalovich.database.entity;

import com.shukalovich.database.entity.enam.Brand;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(of = "id")
public class Product {
    private Long id;
    private Brand brand;
    private String model;
    private Double screenSize;
    private String screenResolution;
    private Integer ram;
    private Integer memorySize;
    private Double price;
    private String description;
}
