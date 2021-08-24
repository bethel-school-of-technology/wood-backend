package com.woodapp.services;

import com.woodapp.models.Member;
import com.woodapp.dao.MemberDao;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
public class MemberService {
    // extends JpaRepository<Member, Integer> or should I use CrudRepository instead?

    private final MemberDao memberDao;

    @Autowired
    public MemberService(@Qualifier("mySQL2") MemberDao memberDao){
        this.memberDao = memberDao;
    }

    public int addMember(Member member) {
        return memberDao.insertMember(member);
    }

    public List<Member> getAllMembers() {
        return memberDao.selectAllMembers();
    }

    public Optional<Member> getMemberById(UUID id){
        return memberDao.selectMemberById(id);
    }

    public int deleteMember(UUID id) {
        return memberDao.deleteMemberById(id);
    }

    public int updateMember(UUID id, Member newMember){
        return memberDao.updateMemberById(id, newMember);
    }
}
