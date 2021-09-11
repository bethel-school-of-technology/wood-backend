package com.woodapp.repositories;

import com.woodapp.models.Gym;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<Gym, Integer> {
}
