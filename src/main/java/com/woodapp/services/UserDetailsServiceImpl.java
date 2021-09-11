package com.woodapp.services;

import com.woodapp.models.User;
import com.woodapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        com.woodapp.models.User user = userRepository.findByEmail(email);
                if (user == null) {
                    throw new UsernameNotFoundException("User Not Found with that email: " + email);
                }
        return UserDetailsImpl.build(user);
    }

}
