package com.adrian.champlonshipfootball.service;

import com.adrian.champlonshipfootball.dtos.LoginUserDto;
import com.adrian.champlonshipfootball.dtos.RegisterUserDto;
import com.adrian.champlonshipfootball.model.Users;
import com.adrian.champlonshipfootball.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users signup(RegisterUserDto input) {
        Users user = new Users();
                user.setDni(input.getDni());
                user.setFirstname(input.getFirstname());
                user.setLastname(input.getLastname());
                user.setUsername(input.getUsername());
                user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public Users authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}
