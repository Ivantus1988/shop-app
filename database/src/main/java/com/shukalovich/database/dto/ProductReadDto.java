package com.shukalovich.database.dto;

import com.shukalovich.database.entity.enam.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductReadDto {
    private Long id;
    private Brand brand;
    private String model;
    private Double screenSize;
    private String screenResolution;
    private Short ram;
    private Short memorySize;
    private Double price;
    private String description;
}