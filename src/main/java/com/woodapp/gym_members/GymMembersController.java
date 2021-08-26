package com.woodapp.gym_members;

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
public class GymMembersController {
    
	@Autowired
    GymMembersRepository dao;

   @GetMapping("/member")
   public List<GymMembers> getAllMembers() {
	   List<GymMembers> foundAllMembers = dao.findAll();
	   return foundAllMembers;
   }

   @GetMapping("/member/{id}")
   public ResponseEntity<GymMembers> getMember(@PathVariable("id") Integer id) {
	   GymMembers foundMember = dao.findById(id).orElse(null);
	   
	   if(foundMember == null) {
		   return ResponseEntity.notFound().header("Member", "Nothing found with that id").build();
	   		}
	   return ResponseEntity.ok(foundMember);
	   }
   
   @PostMapping("/member")
   public ResponseEntity<GymMembers> addMember(@RequestBody GymMembers gymMembers) {
       GymMembers createdMember = dao.save(gymMembers);
       return ResponseEntity.ok(createdMember);
   }
   
   @PutMapping("/member/{id}")
	public ResponseEntity<GymMembers> updateMember(@PathVariable("id") Integer id, @RequestBody GymMembers gymMembers) throws Exception {
		GymMembers updateMember = dao.findById(id).orElse(null);
		if (updateMember == null) {
			return ResponseEntity.notFound().header("Member","Nothing found with that id").build();
		}
		else {
			updateMember.setFirstName(gymMembers.getFirstName());
			updateMember.setLastName(gymMembers.getLastName());
			updateMember.setGender(gymMembers.getGender());
			updateMember.setAge(gymMembers.getAge());
			updateMember.setEmail(gymMembers.getEmail());
			updateMember.setPhoneNumber(gymMembers.getPhoneNumber());
			updateMember.setAddress(gymMembers.getAddress());
			dao.save(updateMember);
		}
		return ResponseEntity.ok(updateMember);
	}
   
   @DeleteMapping("/member/{id}")
   public ResponseEntity<GymMembers> deleteMember(@PathVariable("id") Integer id) {
       GymMembers foundMember = dao.findById(id).orElse(null);

       if(foundMember == null) {
           return ResponseEntity.notFound().header("Member","Nothing found with that id").build();
       }else {
           dao.delete(foundMember);
       }
       return ResponseEntity.ok().build();
   } 
   
   
}


   












