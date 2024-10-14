package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Users;
import com.adrian.champlonshipfootball.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<Users> getUsers() {
        return userService.findAllUsers();
    }
    @GetMapping("/users/{id}")
    public Users getUserById(@PathVariable Long id) {
        return userService.findUserById(id);
    }
    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable  Long id, @RequestBody Users user) {
        return userService.update(id, user);
    }
    @PostMapping("/users")
    public Users saveUser(@RequestBody Users user) {
        return userService.saveUser(user);
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}
