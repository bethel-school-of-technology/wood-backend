package com.woodapp.gym_members;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woodapp.gym_members.GymMembers;

public interface GymMembersRepository extends JpaRepository<GymMembers, Integer> {
    
}
