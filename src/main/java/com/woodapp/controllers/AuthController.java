package com.woodapp.controllers;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woodapp.models.ERole;
import com.woodapp.models.Role;
import com.woodapp.models.User;
import com.woodapp.repositories.RoleRepository;
import com.woodapp.repositories.UserRepository;
import com.woodapp.utils.MessageResponse;
import com.woodapp.utils.RegisterRequest;
import com.woodapp.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    AuthenticationManager authenticationManager;

    UserRepository userRepository;

    RoleRepository roleRepository;

    PasswordEncoder encoder;

    UserService userService;

@PostMapping("/login")
public ResponseEntity<?> authenticateUser(@Valid @RequestBody org.springframework.security.core.userdetails.User user){

        Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
        userDetails.getId(),
        userDetails.getEmail(),
        roles));
        }


@PostMapping("/register")
public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
        return ResponseEntity
        .badRequest()
        .body(new MessageResponse("Error: Email is already in use!")); // will be using email as username
        }

        // Create new user's account
        User user = new User(
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                 encoder.encode(registerRequest.getPassword()),
                registerRequest.getBirthday(),
                registerRequest.getGender(),
                registerRequest.getPhoneNumber(),
                registerRequest.getStreetAddress(),
                registerRequest.getCity(),
                registerRequest.getState(),
                registerRequest.getZipCode(),
                registerRequest.getSignUpDate(),
                registerRequest.getMembershipType(),
                registerRequest.getRoles()
        );

        Collection<Role> strRoles = registerRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        } else {
        strRoles.forEach(role -> {
        switch (role) {
        case "admin":
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);

        break;
        case "manager":
        Role managerRole = roleRepository.findByName(ERole.ROLE_MANAGER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(managerRole);

        break;
default:
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);
        }
        });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!") {
        });
        }
}

