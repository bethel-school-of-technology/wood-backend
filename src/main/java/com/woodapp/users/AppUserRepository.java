package com.woodapp.users;

import com.woodapp.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<Users, Integer>{
	Optional<Users> findByEmail(String email);

//org.springframework.security.core.userdetails.User save(org.springframework.security.core.userdetails.User newUser);
}
