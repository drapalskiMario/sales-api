package com.dev.restfullapi.rest.controller;

import com.dev.restfullapi.rest.dto.input.AuthDTO;
import com.dev.restfullapi.rest.dto.output.TokenDTO;
import com.dev.restfullapi.rest.dto.input.UserDTO;
import com.dev.restfullapi.rest.jwt.JwtService;
import com.dev.restfullapi.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userService;
    private final JwtService jwtService;

    public UserController(UserServiceImpl userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid UserDTO userDTO) {
        this.userService.create(userDTO);
    }

    @PostMapping("/auth")
    public TokenDTO auth(@RequestBody @Valid AuthDTO authDTO) {
        var userDetails = this.userService.authUser(authDTO);
        var token = this.jwtService.generateToken(userDetails);
        return new TokenDTO(userDetails.getUsername(), token);
    }
}
