package com.shukalovich.database.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class Product {
    private int id;
    private Brand brand;
    private String model;
    private double price;
}
