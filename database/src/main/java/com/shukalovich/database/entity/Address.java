package com.shukalovich.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Address {

    @Column(name = "city", length = 32)
    private String city;

    @Column(name = "street", length = 32)
    private String street;

    @Column(name = "building", length = 32)
    private String building;

    @Column(name = "flat", length = 32)
    private String flat;
}