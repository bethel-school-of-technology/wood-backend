//package com.woodapp.auth;
//
//import java.util.ArrayList;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.woodapp.users.RegisteredUsersRepository;
//
//@Service
//public class MySQLUserDetailsService implements UserDetailsService {
//
//  @Autowired
//  private RegisteredUsersRepository registeredUsersRepository;
//
//  @Autowired
//  private PasswordEncoder passwordEncoder;
//
//  @Override
//  public UserDetails loadUserByUsername(String username) {
//    com.woodapp.users.RegisteredUsers users = registeredUsersRepository.findByEmail(email);
//    if (users == null) {
//      throw new UsernameNotFoundException(email);
//    }
//    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities());
//  }
//
//  public UserDetails Save(User newUser) {
//    (newUser).setPassword(passwordEncoder.encode(newUser.getPassword()));
//    User savedUser = registeredUsersRepository.save(newUser);
//    return new org.springframework.security.core.userdetails.User(savedUser.getEmail(), savedUser.getPassword(), getAuthorities());
//  }
//
//  private List<SimpleGrantedAuthority> getAuthorities() {
//    List<SimpleGrantedAuthority> authList = new ArrayList<>();
//    authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//    return authList;
//  }
//}
