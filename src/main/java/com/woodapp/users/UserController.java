package com.woodapp.users;

import com.woodapp.users.User;
import com.woodapp.users.UserRepository;
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
public class UserController {
    
    @Autowired
    UserRepository dao;
    
    @GetMapping("/user")
	public List<User> getAllUsers() {
		List<User> foundAllUsers = dao.findAll();
		return foundAllUsers;
	}
    
    @GetMapping("/user/{id}")
	public ResponseEntity<User> getOneUser(@PathVariable("id") Integer id) {
		User foundUser = dao.findById(id).orElse(null);
		if (foundUser == null) {
			return ResponseEntity.notFound().header("User", "Nothing found with that id").build();
		}
		return ResponseEntity.ok(foundUser);
	}
    
    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user) {
		User createdUser = dao.save(user);
		return ResponseEntity.ok(createdUser);
	}
    
    @PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) throws Exception {
		User updateUser = dao.findById(id).orElse(null);
		if (updateUser == null) {
			return ResponseEntity.notFound().header("User","Nothing found with that id").build();
		}
		else {
			updateUser.setFirstName(user.getFirstName());
			updateUser.setLastName(user.getLastName());
			updateUser.setUsername(user.getUsername());
			updateUser.setPassword(user.getPassword());
			dao.save(updateUser);
		}
		return ResponseEntity.ok(updateUser);
	}
    
    @DeleteMapping("/user/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
		User foundUser = dao.findById(id).orElse(null);

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
    
    
