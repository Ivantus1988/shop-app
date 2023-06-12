package com.shukalovich.database.entity;

import com.shukalovich.database.entity.enam.Brand;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "product")
public class ProductEntity implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand", nullable = false, length = 32)
    private Brand brand;

    @Column(name = "model", nullable = false, length = 32)
    private String model;

    @Embedded
    private Screen screen;

    @Column(name = "ram")
    private Short ram;

    @Column(name = "memory_size")
    private Short memorySize;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderEntity> orders = new ArrayList<>();

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProducerEntity producer;

    @Builder.Default
    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER)
     private List<FactoryEntity> factory = new ArrayList<>();

    public void addOrder(OrderEntity order) {
        this.orders.add(order);
        order.setProduct(this);
    }

    public void setProducer(ProducerEntity producer) {
        this.producer = producer;
        producer.setProduct(this);
    }
}
