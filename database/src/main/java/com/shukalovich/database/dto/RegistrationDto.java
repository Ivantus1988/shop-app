package com.shukalovich.database.dto;

import com.shukalovich.database.entity.enam.Gender;
import com.shukalovich.database.entity.enam.Role;

public record RegistrationDto (
        String name,
        String surname,
        String email,
        String password,
        Gender gender,
        Role role,
        String city,
        String street,
        String building,
        String flat
) {
}