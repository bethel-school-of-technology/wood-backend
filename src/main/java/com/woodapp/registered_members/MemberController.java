package com.woodapp.registered_members;

import com.woodapp.registered_members.MemberRepository;
import com.woodapp.registered_members.Member;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/")
@RestController
public class MemberController {
    
	@Autowired
    MemberRepository dao;

   @GetMapping("/member")
   public List<Member> getAllMembers() {
	   List<Member> foundAllMembers = dao.findAll();
	   return foundAllMembers;
   }

   @GetMapping("/member/{id}")
   public ResponseEntity<Member> getMember(@PathVariable("id") Integer id) {
	   Member foundMember = dao.findById(id).orElse(null);
	   
	   if(foundMember == null) {
		   return ResponseEntity.notFound().header("Member", "Nothing found with that id").build();
	   		}
	   return ResponseEntity.ok(foundMember);
	   }
   
   @PostMapping("/member")
   public ResponseEntity<Member> addMember(@RequestBody Member member) {
       Member createdMember = dao.save(member);
       return ResponseEntity.ok(createdMember);
   }
   
   @PutMapping("/member/{id}")
	public ResponseEntity<Member> updateMember(@PathVariable("id") Integer id, @RequestBody Member member) throws Exception {
		Member updateMember = dao.findById(id).orElse(null);
		if (updateMember == null) {
			return ResponseEntity.notFound().header("Member","Nothing found with that id").build();
		}
		else {
			updateMember.setFirstName(member.getFirstName());
			updateMember.setLastName(member.getLastName());
			updateMember.setGender(member.getGender());
			updateMember.setAge(member.getAge());
			updateMember.setEmail(member.getEmail());
			updateMember.setPhoneNumber(member.getPhoneNumber());
			updateMember.setAddress(member.getAddress());
			dao.save(updateMember);
		}
		return ResponseEntity.ok(updateMember);
	}
   
   @DeleteMapping("/member/{id}")
   public ResponseEntity<Member> deleteMember(@PathVariable("id") Integer id) {
       Member foundMember = dao.findById(id).orElse(null);

       if(foundMember == null) {
           return ResponseEntity.notFound().header("Member","Nothing found with that id").build();
       }else {
           dao.delete(foundMember);
       }
       return ResponseEntity.ok().build();
   } 
   
   
}


   












