package com.fullStack.Backend.controller;

import com.fullStack.Backend.exception.UserNotFoundException;
import com.fullStack.Backend.model.User;
import com.fullStack.Backend.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("https://login-portal-rouge.vercel.app/")
public class userController {

   @Autowired
    private userRepository userRepository;

   @PostMapping("/user")
    User newUser (@RequestBody User newUser){
       return userRepository.save(newUser);
   }

   @GetMapping("/user")
    List<User> getAllUsers(){
       return userRepository.findAll();
       //this find all method is provided by the jpa
   }

   @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
       return userRepository.findById(id)
               .orElseThrow(()->new UserNotFoundException(id));
   }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser,@PathVariable Long id){
       return userRepository.findById(id)
               .map(user -> {
                   user.setName(newUser.getName());
                   user.setUsername(newUser.getUsername());
                   user.setEmail(newUser.getEmail());
                   return userRepository.save(user);
               }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    String deleteUser (@PathVariable Long id){
       if(!userRepository.existsById(id)){
           throw  new UserNotFoundException(id);
       }
       userRepository.deleteById(id);
       return "User with id"+ id+"has been deleted";
    }
}
