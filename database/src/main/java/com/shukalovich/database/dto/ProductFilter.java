package com.shukalovich.database.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ProductFilter {

    private Short memorySize;
    private Double price;
    private Short ram;
    private Integer limit;
    private Integer page;

    public Integer getLimit() {
        return limit == null ? 6 : limit;
    }

    public Integer getOffset() {
        return page == null ? 0 : limit * (page - 1);
    }
}