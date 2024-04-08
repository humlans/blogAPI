package com.example.blogAPI.services;

import com.example.blogAPI.items.User;
import com.example.blogAPI.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository repo;
    public UserService(UserRepository userRepository){
        repo = userRepository;
    }

    public boolean loginUser(String username, String password){
        User user = repo.findByUserName(username);
        if(user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    public User getUserByUsername(String username){
        if(repo.existsByUserName(username)){
            return repo.findByUserName(username);
        }
        return null;
    }
}
