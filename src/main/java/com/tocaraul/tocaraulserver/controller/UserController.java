package com.tocaraul.tocaraulserver.controller;

import com.tocaraul.tocaraulserver.dto.MeResponseDto;
import com.tocaraul.tocaraulserver.entity.User;
import com.tocaraul.tocaraulserver.service.ProfileService;
import com.tocaraul.tocaraulserver.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final ProfileService profileService;

    public UserController(UserService userService, ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }

    @GetMapping(value = "/{userId}")
    public User getUser(@PathVariable int userId) {
        return this.userService.findById(userId);
    }

    @GetMapping("/me")
    public MeResponseDto me(Authentication authentication) {

        User user = (User) authentication.getPrincipal();

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Nao autenticado");
        }

        return profileService.buildMeResponse(user);
    }

    @PostMapping(value = "/register")
    public User registerUser(@RequestBody User user) {
        this.userService.save(user);
        return user;
    }
}