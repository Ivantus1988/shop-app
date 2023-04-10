package com.shukalovich.database.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data

public class Product {
    private int id;
    private Brand brand;
    private String model;
    private double price;
}
