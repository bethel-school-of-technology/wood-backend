package com.woodapp.services;

import com.woodapp.repositories.UserRepository;
import com.woodapp.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        com.woodapp.models.User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities());
    }

    public UserDetails Save(User newUser) {
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        User savedUser = userRepository.save(newUser);
        return new org.springframework.security.core.userdetails.User(savedUser.getEmail(), savedUser.getPassword(), getAuthorities());
    }

    private List<SimpleGrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authList;
    }

    //    private final static String USER_NOT_FOUND_MSG = "User with that email %s not found";

//    public String signUpUser(User user) {
//        boolean userExists = userRepository.findByEmail(user.getEmail())
//                .isPresent();
//
//        if (userExists){
//            throw new IllegalStateException("This email already exists in system");
//        }
//
//        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
//
//        user.setPassword(encodedPassword);
//
//        return "";
//    }


}
