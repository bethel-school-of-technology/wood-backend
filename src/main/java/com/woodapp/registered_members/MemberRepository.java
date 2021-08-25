package com.woodapp.registered_members;

import org.springframework.data.jpa.repository.JpaRepository;
import com.woodapp.registered_members.Member;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    
}
