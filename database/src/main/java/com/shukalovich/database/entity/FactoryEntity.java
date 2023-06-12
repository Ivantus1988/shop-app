package com.shukalovich.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "products")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "products")
@Entity
@Table(name = "factory")
public class FactoryEntity implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "location", length = 32, nullable = false, unique = true)
    private String location;

    @Column(name = "contact", length = 32, nullable = false, unique = true)
    private String contact;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "product_factory",
            joinColumns = {
                    @JoinColumn(name = "factory_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id")
            })
    private List<ProductEntity> products = new ArrayList<>();
}
