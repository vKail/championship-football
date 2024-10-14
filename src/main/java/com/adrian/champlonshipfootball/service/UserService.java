package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.model.Users;
import com.adrian.champlonshipfootball.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> findAllUsers() {
        return userRepository.findAll();
    }

    public Users saveUser(Users user) {
        return  userRepository.save(user);
    }
    public Users findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Users update(Long id, Users user) {
        Users userToUpdate = findUserById(id);
        userToUpdate.setDni(user.getDni());
        userToUpdate.setFirstname(user.getFirstname());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(user.getPassword());
        return userRepository.save(userToUpdate);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
