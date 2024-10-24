package com.adrian.champlonshipfootball.controller;

import com.adrian.champlonshipfootball.model.Users;
import com.adrian.champlonshipfootball.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<Users> getUsers() throws Exception {
        try {
            return userService.findAllUsers();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());

        }
    }

    @GetMapping("/users/{id}")
    public Users getUserById(@PathVariable Long id) throws Exception {
        try {
            return userService.findUserById(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PostMapping("/users")
    public Users saveUser(@RequestBody Users user) throws Exception {
        try {
            return userService.saveUser(user);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) throws Exception {
        try {
            return userService.update(id, user);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) throws Exception {
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<Users> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users currentUser = (Users) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);

    }

}
