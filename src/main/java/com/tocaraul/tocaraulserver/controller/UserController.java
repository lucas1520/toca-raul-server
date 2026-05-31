package com.tocaraul.tocaraulserver.controller;

import com.tocaraul.tocaraulserver.entity.User;
import com.tocaraul.tocaraulserver.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable int userId) {
        return this.userService.findById(userId);
    }

    @PostMapping(value = "/register")
    public User registerUser(@RequestBody User user) {
        this.userService.save(user);
        return user;
    }
}