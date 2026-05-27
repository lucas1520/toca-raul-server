package com.tocaraul.tocaraulserver.controller;

import com.tocaraul.tocaraulserver.dto.LoginDto;
import com.tocaraul.tocaraulserver.dto.RegisterDto;
import com.tocaraul.tocaraulserver.model.User;
import com.tocaraul.tocaraulserver.service.TokenService;
import com.tocaraul.tocaraulserver.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final String LOGIN_ERROR_DEFAULT_MESSAGE = "Dados incorretos";

    private static final String EMAIL_ALREADY_IN_USE = "Email já em uso";

    private static final String REGISTER_SUCCESSFUL = "Conta criada com sucesso";

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    AuthController(UserService userService, PasswordEncoder passwordEncoder, TokenService tokenService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        User user = this.userService.getByEmail(loginDto.getEmail());

        if (user == null) {
            return ResponseEntity.status(401).body(LOGIN_ERROR_DEFAULT_MESSAGE);
        }

        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassHash())) {
            String token = this.tokenService.generateToken(user);

            Map<String, String > response = new HashMap<>();
            response.put("token", token);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body(LOGIN_ERROR_DEFAULT_MESSAGE);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        if (this.userService.getByEmail(registerDto.email()) != null) {
            return ResponseEntity.badRequest().body(EMAIL_ALREADY_IN_USE);
        }

        String encryptedPassword = this.passwordEncoder.encode(registerDto.password());

        User newUser = new User();
        newUser.setFirstName(registerDto.firstName());
        newUser.setLastName(registerDto.lastName());
        newUser.setEmail(registerDto.email());
        newUser.setPassHash(encryptedPassword);
        newUser.setType(registerDto.type().toUpperCase());

        this.userService.save(newUser);

        return ResponseEntity.ok(REGISTER_SUCCESSFUL);
    }
}
