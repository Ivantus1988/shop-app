package com.shukalovich.database.dto;


public record ProductFilter(Double screenSize,
                            Double price,
                            Integer ram,
                            Integer limit,
                            Integer page) {
}