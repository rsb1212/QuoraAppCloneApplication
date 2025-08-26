package com.example.QuoraAppCloneApplication.services;

import com.example.QuoraAppCloneApplication.dtos.UserDTO;
import com.example.QuoraAppCloneApplication.models.Tag;
import com.example.QuoraAppCloneApplication.models.User;
import com.example.QuoraAppCloneApplication.repositories.TagRepository;
import com.example.QuoraAppCloneApplication.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    private UserRepository userRepository;
    private TagRepository tagRepository;

    UserServices(UserRepository userRepository, TagRepository tagRepository){
        this.userRepository=userRepository;
        this.tagRepository=tagRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return  userRepository.findById(id);
    }
    public User createUser(UserDTO userDTO){
        User user=new User();
        user.setUsername(userDTO.getUsername());
        user.setUsername(userDTO.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

    public void followTag(Long userId,Long tagId){
        User user=userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        Tag tag=tagRepository.findById(tagId).orElseThrow(()->new RuntimeException("Tag not fount"));
        user.getFollowedTags().add(tag);
        userRepository.save(user);
    }
}
