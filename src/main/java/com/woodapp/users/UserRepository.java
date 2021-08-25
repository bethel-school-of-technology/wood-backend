package com.woodapp.users;

import org.springframework.data.jpa.repository.JpaRepository;
import com.woodapp.users.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);

//	org.springframework.security.core.userdetails.User save(org.springframework.security.core.userdetails.User newUser);
}
