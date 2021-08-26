package com.woodapp.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisteredUsersRepository extends JpaRepository<RegisteredUsers, Integer>{
	RegisteredUsers findByEmail(String email);

//	org.springframework.security.core.userdetails.User save(org.springframework.security.core.userdetails.User newUser);
}
