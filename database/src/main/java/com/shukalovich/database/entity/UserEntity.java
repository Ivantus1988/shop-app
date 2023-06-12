package com.shukalovich.database.entity;

import com.shukalovich.database.entity.enam.Gender;
import com.shukalovich.database.entity.enam.Role;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id", callSuper = true)
@Entity
@Table(name = "users")
public class UserEntity extends CreatableEntity implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "surname", nullable = false, length = 32)
    private String surname;

    @Column(name = "email", unique = true, nullable = false, length = 32)
    private String email;

    @Column(name = "password", nullable = false, length = 32)
    private String password;

    @Column(name = "gender", length = 32)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "role", length = 32)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Embedded
    private Address address;

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderEntity> orders = new ArrayList<>();


    public void addOrder(OrderEntity order) {
        this.orders.add(order);
        order.setUser(this);
    }

}