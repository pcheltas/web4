package com.pcheltas.web4.controller;


import com.pcheltas.web4.model.User;
import com.pcheltas.web4.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<?> register(User user) {
        userService.addUser(user);

        return new ResponseEntity<>("registered successfully", HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(User user) {
        userService.checkUser(user);

        return new ResponseEntity<>("logged successfully", HttpStatus.ACCEPTED);
    }
}
