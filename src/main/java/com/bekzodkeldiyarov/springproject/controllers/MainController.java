package com.bekzodkeldiyarov.springproject.controllers;

import com.bekzodkeldiyarov.springproject.entity.User;
import com.bekzodkeldiyarov.springproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {
    UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "index";
    }

    @PostMapping(value = "/handle-main-form", params = "action=unblock")
    public String actionUnblock(@RequestParam(required = false) Long[] ids) {
        List<User> users = userService.findByIds(ids);
        users.forEach(user -> user.setActive(true));
        userService.saveUsers(users);
        userService.refreshUserSession();
        return "redirect:./";
    }

    @PostMapping(value = "/handle-main-form", params = "action=block")
    public String actionBlock(@RequestParam(required = false) Long[] ids) {
        List<User> users = userService.findByIds(ids);
        users.forEach(user -> user.setActive(false));
        userService.saveUsers(users);
        userService.refreshUserSession();
        return "redirect:./";
    }

    @PostMapping(value = "/handle-main-form", params = "action=delete")
    public String actionDelete(@RequestParam(required = false) Long[] ids) {
        List<User> users = userService.findByIds(ids);
        userService.deleteUsers(users);
        userService.refreshUserSession();
        return "redirect:./";
    }
}
