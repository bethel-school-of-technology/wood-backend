package com.woodapp.controllers;

import java.util.List;
import java.util.UUID;
import javax.validation.Valid;

import com.woodapp.models.User;
import com.woodapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/user")
@RestController
public class UserController {
    
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@Valid @NonNull @RequestBody User user){
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") UUID id){
        return userService.getUserById(id)
            .orElse(null);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") UUID id, @Valid @NonNull @RequestBody User UserToUpdate){
        userService.updateUser(id, UserToUpdate);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUserById(@PathVariable("id") UUID id){
        userService.deleteUser(id);
    }
}
