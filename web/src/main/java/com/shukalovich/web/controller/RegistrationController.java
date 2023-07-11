package com.shukalovich.web.controller;

import com.shukalovich.database.dto.RegistrationDto;
import com.shukalovich.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.shukalovich.web.util.PagesUtil.REGISTRATION;

@Controller
@RequestMapping(REGISTRATION)
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String getRegistrationPage() {
        return "registration";
    }


    @PostMapping
    public String register(RegistrationDto registration) {
        return userService.createUser(registration)
                .map(user -> "redirect:/login")
                .orElse("redirect:/registration?error");

    }
}