package com.woodapp.repositories;

import com.woodapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
	User findByUsername(String username);

	Boolean existsByUsername(String username);

}
