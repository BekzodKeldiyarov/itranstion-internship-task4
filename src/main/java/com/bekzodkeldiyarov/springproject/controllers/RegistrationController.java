package com.bekzodkeldiyarov.springproject.controllers;

import com.bekzodkeldiyarov.springproject.entity.Role;
import com.bekzodkeldiyarov.springproject.entity.User;
import com.bekzodkeldiyarov.springproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.Collections;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String getRegisterPage() {
        System.out.println("Inside register page");
        return "register";
    }

    @PostMapping("/register")
    public String addUser(User user) {
        System.out.println("Inside post mapping");
        User userFromDB = userService.findUserByUsername(user.getUsername());
        if (userFromDB != null) {
            System.out.println("User exists");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setRegisterDate(LocalDate.now());
        userService.saveUser(user);
        return "redirect:/login";
    }
}
