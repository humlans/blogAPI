package com.example.blogAPI.controllers;

import com.example.blogAPI.items.BlogPost;
import com.example.blogAPI.items.User;
import com.example.blogAPI.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    // Post mapping to make a user login.
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<String> login(String username, String password){
        // Check if loginUser successes or not.
        boolean success = userService.loginUser(username, password);
        if(success){
            // Post request succeeded.
            return ResponseEntity.status(HttpStatus.OK).body("Successfully logged in.");
        }
        // Post request failed.
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to log in.");
    }

    // Get mapping to get User object by username.
    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<User> getUser(String username){
        // Get User object by id with getUserByUsername.
        User user = userService.getUserByUsername(username);
        if(user != null){
            // Get request succeeded.
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        // Get request didn't find User object with given userId.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    // Get mapping to get a User object with id.
    @CrossOrigin
    @GetMapping("/byId")
    public ResponseEntity<User> getUserById(int id){
        // Get User object by id with getUserByUserId.
        User user = userService.getUserByUserId(id);
        if(user != null){
            // Get request succeeded.
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        // Get request didn't find User object with given id.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
