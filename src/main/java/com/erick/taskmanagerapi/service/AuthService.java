package com.erick.taskmanagerapi.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;

import com.erick.taskmanagerapi.repository.UserRepository;
import com.erick.taskmanagerapi.dto.AuthResponse;
import com.erick.taskmanagerapi.dto.RegisterRequestDto;
import com.erick.taskmanagerapi.dto.LoginRequestDto;
import com.erick.taskmanagerapi.exception.BadRequestException;
import com.erick.taskmanagerapi.entity.User;
import com.erick.taskmanagerapi.enums.Role;
import com.erick.taskmanagerapi.entity.User;

import java.util.Locale;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AuthResponse register(RegisterRequestDto request) {
        String normalizedEmail = request.email().trim().toLowerCase(Locale.ROOT);

        boolean isExisting = userRepository.existsByEmail(normalizedEmail);

        if(isExisting) throw new BadRequestException("User with this email already exists");

        User newUser = new User();
        newUser.setEmail(normalizedEmail);
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(Role.USER);

        try {
            userRepository.saveAndFlush(newUser);
        } catch(DataIntegrityViolationException ex) {
            throw new BadRequestException("User with this email already exists");
        }

        return new AuthResponse("Registered successfully. You can log in using your credentials");
    }

    @Transactional(readOnly = true)
    public AuthResponse login(LoginRequestDto request) {
        String normalizedEmail = request.email().trim().toLowerCase(Locale.ROOT);

        User user = userRepository.findByEmail(normalizedEmail)
            .orElseThrow(() -> new BadRequestException("Email or password is invalid"));

        boolean passwordMatch = passwordEncoder.matches(request.password(), user.getPassword());

        if(!passwordMatch) throw new BadRequestException("Email or password is invalid");

        //To generate JWT later

        return new AuthResponse("Logged in successfully");
    }
}
