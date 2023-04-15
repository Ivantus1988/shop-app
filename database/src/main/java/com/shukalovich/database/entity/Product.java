package com.shukalovich.database.entity;

import lombok.Builder;
import lombok.Data;
@Builder
@Data

public class Product {
    private Long id;
    private Brand brand;
    private String model;
    private double screenSize;
    private String screenResolution;
    private int ram;
    private int memorySize;
    private double price;
    private String description;
}
