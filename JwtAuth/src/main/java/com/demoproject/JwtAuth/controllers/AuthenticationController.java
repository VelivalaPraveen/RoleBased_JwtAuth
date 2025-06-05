package com.demoproject.JwtAuth.controllers;

import com.demoproject.JwtAuth.dtos.LoginUserDto;
import com.demoproject.JwtAuth.dtos.RegisterUserDto;
import com.demoproject.JwtAuth.entities.User;
import com.demoproject.JwtAuth.services.AuthenticationService;
import com.demoproject.JwtAuth.services.Jwtservice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import org.springframework.JwtAuth.controllers.LoginReponse;
@RequestMapping("/auth")
@RestController
@CrossOrigin
public class AuthenticationController {
    private final Jwtservice jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(Jwtservice jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());


        return ResponseEntity.ok(loginResponse);
    }
}