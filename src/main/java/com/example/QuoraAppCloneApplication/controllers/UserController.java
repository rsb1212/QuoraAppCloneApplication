package com.example.QuoraAppCloneApplication.controllers;

import com.example.QuoraAppCloneApplication.dtos.UserDTO;
import com.example.QuoraAppCloneApplication.models.User;
import com.example.QuoraAppCloneApplication.services.UserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private UserServices userServices;
    public UserController (UserServices userServices){
        this.userServices=userServices;
    }

    @GetMapping
    public List<User> getUsers(){
        return userServices.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user=userServices.getUserById(id);
       return user.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(UserDTO userDTO){
        return  userServices.createUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userServices.deleteUser(id);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/followTag/{tatId}")
    public ResponseEntity<Void> followTag(@PathVariable Long userId,@PathVariable Long tagId){
        userServices.followTag(userId,tagId);
        return ResponseEntity.noContent().build();
    }


}
