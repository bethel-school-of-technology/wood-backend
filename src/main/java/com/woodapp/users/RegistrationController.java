package com.woodapp.users;

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

@RequestMapping("/api")
@RestController
public class RegistrationController {
    
    @Autowired
    UserRepository dao;
    
    @GetMapping("/user")
	public List<Users> getAllUsers() {
		List<Users> foundAllUsers = dao.findAll();
		return foundAllUsers;
	}
    
    @GetMapping("/user/{id}")
	public ResponseEntity<Users> getOneUser(@PathVariable("id") Integer id) {
		Users foundUser = dao.findById(id).orElse(null);
		if (foundUser == null) {
			return ResponseEntity.notFound().header("User", "Nothing found with that id").build();
		}
		return ResponseEntity.ok(foundUser);
	}
    
    @PostMapping("/user")		
    public ResponseEntity<Users> addUser(@RequestBody Users users) {
		Users createdUser = dao.save(users);
		return ResponseEntity.ok(createdUser);
	}
    
    @PutMapping("/user/{id}")
	public ResponseEntity<Users> updateUser(@PathVariable("id") Integer id, @RequestBody Users users) throws Exception {
		Users updateUser = dao.findById(id).orElse(null);
		if (updateUser == null) {
			return ResponseEntity.notFound().header("User","Nothing found with that id").build();
		}
		else {
			updateUser.setFirstName(users.getFirstName());
			updateUser.setLastName(users.getLastName());
			updateUser.setEmail(users.getEmail());
			updateUser.setPassword(users.getPassword());
			updateUser.setGender(users.getGender());
			updateUser.setBirthday(users.getBirthday());
			updateUser.setPhoneNumber(users.getPhoneNumber());
			updateUser.setAddress(users.getAddress());
			dao.save(updateUser);
		}
		return ResponseEntity.ok(updateUser);
	}
    
    @DeleteMapping("/user/{id}")
	public ResponseEntity<Users> deleteUser(@PathVariable("id") Integer id) {
		Users foundUser = dao.findById(id).orElse(null);

		if (foundUser == null) {
			return ResponseEntity.notFound().header("User", "Nothing found with that username").build();
		} else {
			dao.delete(foundUser);
		}
		return ResponseEntity.ok().build();
	}
    
//    @Autowired
//	private MySQLUserDetailsService userService;
//	
//	@PostMapping("/user/register")
//	public void register(@RequestBody User newUser) {
//		userService.Save(newUser);
//	}
}
    
    
