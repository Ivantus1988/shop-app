package com.shukalovich.database.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data

public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private Role role;
}