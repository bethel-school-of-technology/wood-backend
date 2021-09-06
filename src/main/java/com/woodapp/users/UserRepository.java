package com.woodapp.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByEmail(String email);

//	org.springframework.security.core.userdetails.User save(org.springframework.security.core.userdetails.User newUser);

}
