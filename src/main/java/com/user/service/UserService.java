package com.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.model.User;
import com.user.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    
    public Optional<User> getUserByName(String username){
        return userRepository.findById(username);
    }
    
    public User createUser(User user) {
        System.out.println("Creating user: " + user);
        if (user.getUsername() == null) {
            throw new RuntimeException("Username must be set before saving the user.");
        }
        return userRepository.save(user);
    }
    
    public User updateUser(String username, User userDetails) {
        User user = userRepository.findById(username).orElseThrow(() -> 
            new RuntimeException("User not found with username: " + username));
        user.setPassword(userDetails.getPassword());
        user.setActive(userDetails.isActive());
        
        return userRepository.save(user);
    }
    
    public void deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
        } else {
            throw new RuntimeException("User not found with username: " + username);
        }
    }
}