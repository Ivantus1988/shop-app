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
public class Screen {

    @Column(name = "screen_size")
    private Double screenSize;

    @Column(name = "screen_resolution", length = 32)
    private String screenResolution;
}
