package com.shukalovich.database.entity;

import com.shukalovich.database.entity.enam.Gender;
import com.shukalovich.database.entity.enam.Role;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(of = "id")
public class User {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Gender gender;
    private Role role;
}