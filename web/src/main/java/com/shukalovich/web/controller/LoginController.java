package com.shukalovich.web.controller;

import com.shukalovich.service.UserService;
import com.shukalovich.database.dto.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import static com.shukalovich.web.util.PagesUtil.LOGIN;

@Controller
@RequestMapping(LOGIN)
@SessionAttributes({"user"})
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping()
    public String getLoginPage() {
        return "login";
    }

    @PostMapping
    public String login(LoginDto login, Model model) {
        return userService.findByEmailAndPass(login)
                .map(user -> {
                    model.addAttribute("user", user);
                    return "redirect:/product";
                })
                .orElse("redirect:/login?error=true");
    }
}