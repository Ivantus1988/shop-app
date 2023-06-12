package com.shukalovich.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "producer")
public class ProducerEntity implements BaseEntity<Long> {

    @Id
    @Column(name = "product_id")
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Column(name = "number_of_the_contract", nullable = false, unique = true)
    private String numberOfTheContract;
}