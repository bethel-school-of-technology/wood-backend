package com.woodapp.services;

import com.woodapp.models.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public interface MemberService extends CrudRepository<Member, Integer> {
    
}
