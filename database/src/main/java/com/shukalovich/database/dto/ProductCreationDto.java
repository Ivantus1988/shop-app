package com.shukalovich.database.dto;

import com.shukalovich.database.entity.enam.Brand;

public record ProductCreationDto(Brand brand,
                                 String model,
                                 Double screenSize,
                                 String screenResolution,
                                 Short ram,
                                 Short memorySize,
                                 Double price,
                                 String description) {
}
