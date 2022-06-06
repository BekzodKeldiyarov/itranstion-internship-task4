package com.bekzodkeldiyarov.springproject.controllers;

import com.bekzodkeldiyarov.springproject.entity.User;
import com.bekzodkeldiyarov.springproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        if (userFromDB != null) {
            return "registration";
        }
        user.setActive(true);
        user.setRegisterDate(LocalDate.now());
        user.setLastLoginDate(LocalDateTime.now());
        userService.saveUser(user);
        return "redirect:/login";
    }
}
