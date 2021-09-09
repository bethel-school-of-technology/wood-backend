//package com.woodapp.authorization;
//
//import com.woodapp.security.PasswordEncoder;
//import com.woodapp.users.roles.RoleRepository;
//import com.woodapp.util.JwtUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RequestMapping("/api/authenticate")
//public class AuthController {
//
//    @Autowired
//    AuthenticationManager authenticationManager;
//
//    @Autowired
//    RoleRepository roleRepository;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    JwtUtils jwtUtils;
//
//    @PostMapping("/user/login")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
//
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtUtils.generateJwtToken(authentication);
//
//        MyUserDetailsService userDetails = (MyUserDetailsService) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
//
//        return ResponseEntity.ok(new JwtResponse(jwt,
//                userDetails.getId(),
//                userDetails.getUsername(),
//                userDetails.getEmail(),
//                roles));
//    }
//
//@PostMapping("/register")
//public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//        return ResponseEntity
//        .badRequest()
//        .body(new MessageResponse("Error: Username is already taken!"));
//        }
//
//        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//        return ResponseEntity
//        .badRequest()
//        .body(new MessageResponse("Error: Email is already in use!"));
//        }
//
//        // Create new user's account
//        User user = new User(signUpRequest.getUsername(),
//        signUpRequest.getEmail(),
//        encoder.encode(signUpRequest.getPassword()));
//
//        Set<String> strRoles = signUpRequest.getRole();
//        Set<Role> roles = new HashSet<>();
//
//        if (strRoles == null) {
//        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//        } else {
//        strRoles.forEach(role -> {
//        switch (role) {
//        case "admin":
//        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(adminRole);
//
//        break;
//        case "mod":
//        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
//        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(modRole);
//
//        break;
//default:
//        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
//        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//        }
//        });
//        }
//
//        user.setRoles(roles);
//        userRepository.save(user);
//
//        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//        }
//        }
////}
