package com.woodapp.dao;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.woodapp.models.Member;

@Repository("mySQL2")
public class MemberDataAccessService implements MemberDao {
    
    private static List<Member> DB = new ArrayList<>();

    @Override
    public int insertMember(Member member) {
        DB.add(new Member(member.getId(), member.getFirstName(), member.getLastName(), member.getSex(), member.getAge(), member.getEmail(), member.getPhoneNumber(),member.getAddress()));
        return 1;
    }

    @Override
    public List<Member> selectAllMembers() {
        return DB;
    }

    @Override
    public Optional<Member> selectMemberById(int id) {
        return DB.stream()
            .filter(member -> member.getId().equals(id))
            .findFirst();
    }

    @Override
    public int deleteMemberById(int id) {
        Optional<Member> memberMaybe = selectMemberById(id);
        if (memberMaybe.isEmpty()){
            return 0;
        }
        DB.remove(memberMaybe.get());
        return 1; 
    }

    @Override
    public int updateMemberById(int id, Member updateMember) {
        return selectMemberById(id)
            .map(Member -> {
                int indexOfMemberToUpdate = DB.indexOf(Member);
                if (indexOfMemberToUpdate >= 0) {
                    DB.set(indexOfMemberToUpdate, new Member(updateMember.getId(), updateMember.getFirstName(), updateMember.getLastName(), updateMember.getSex(), updateMember.getAge(), updateMember.getEmail(), updateMember.getPhoneNumber(),updateMember.getAddress() ));
                    return 1;
                }
                return 0;
            })
            .orElse (0);
    }
}
