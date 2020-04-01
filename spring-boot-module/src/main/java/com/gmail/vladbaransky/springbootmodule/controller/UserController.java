package com.gmail.vladbaransky.springbootmodule.controller;

import com.gmail.vladbaransky.servicemodule.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getLoginPage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/roles")
    public String getPageRole() {
        String namePage = userService.getPageByRole();
        return "forward:" + namePage;
    }
}
