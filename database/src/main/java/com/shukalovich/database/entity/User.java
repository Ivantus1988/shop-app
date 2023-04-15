package com.shukalovich.database.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String gender;
}