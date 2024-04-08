package com.example.blogAPI.controllers;

import com.example.blogAPI.items.User;
import com.example.blogAPI.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> login(String username, String password){
        boolean success = userService.loginUser(username, password);
        if(success){
            return ResponseEntity.status(HttpStatus.OK).body("Successfully logged in.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to log in.");
    }

    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<User> getUser(String username){
        User user = userService.getUserByUsername(username);
        if(user != null){
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
