package com.woodapp.repositories;

import java.util.Optional;

import com.woodapp.models.ERole;
import com.woodapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
