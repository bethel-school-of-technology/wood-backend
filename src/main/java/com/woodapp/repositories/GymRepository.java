package com.woodapp.repositories;

import com.woodapp.models.GymInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GymRepository extends JpaRepository<GymInfo, Integer> {
}
