package com.example.controller;

import com.example.controller.request.CreateUserDTO;
import com.example.model.ERole;
import com.example.model.RoleEntity;
import com.example.model.UserEntity;
import com.example.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PrincipalController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/hello-secure")
    public String helloSecure() {
        return "Hello, Secure World!";
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {

        Set<RoleEntity> roles = createUserDTO.getRoles().stream()
                .map(role -> RoleEntity.builder()
                        .name(ERole.valueOf(role))
                        .build())
                .collect(Collectors.toSet());

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(createUserDTO.getPassword())
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    @DeleteMapping("/delete-user")
    public String deleteUser(@RequestParam String id) {

        userRepository.deleteById(Long.parseLong(id));
        return "Delete User".concat(id);
    }
}
