package com.woodapp.controllers;

import java.util.List;
import com.woodapp.models.User;
import com.woodapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/user/")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	UserRepository dao;

    @GetMapping("/find/all")
	public List<User> getAllUsers() {
		List<User> foundAllUsers = dao.findAll();
		return foundAllUsers;
	}

    @GetMapping("/find/{id}")
	public ResponseEntity<User> getOneUser(@PathVariable("id") Integer id) {
		User foundUser = dao.findById(id).orElse(null);
		if (foundUser == null) {
			return ResponseEntity.notFound().header("User", "Nothing found with that id").build();
		}
		return ResponseEntity.ok(foundUser);
	}

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
		User createdUser = dao.save(user);
		return ResponseEntity.ok(createdUser);
	}

    @PutMapping("/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) throws Exception {
		User updateUser = dao.findById(id).orElse(null);
		if (updateUser == null) {
			return ResponseEntity.notFound().header("User","Nothing found with that id").build();
		}
		else {
			updateUser.setFirstName(user.getFirstName());
			updateUser.setLastName(user.getLastName());
			updateUser.setEmail(user.getEmail());
			updateUser.setPassword(user.getPassword());
			updateUser.setGender(user.getGender());
			updateUser.setBirthday(user.getBirthday());
			updateUser.setPhoneNumber(user.getPhoneNumber());
			updateUser.setStreetAddress(user.getStreetAddress());
			updateUser.setCity(user.getCity());
			updateUser.setState(user.getState());
			updateUser.setZipCode(user.getZipCode());
			updateUser.setSignUpDate(user.getSignUpDate());
			dao.save(updateUser);
		}
		return ResponseEntity.ok(updateUser);
	}

    @DeleteMapping("/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
		User foundUser = dao.findById(id).orElse(null);

		if (foundUser == null) {
			return ResponseEntity.notFound().header("User", "Nothing found with that username").build();
		} else {
			dao.delete(foundUser);
		}
		return ResponseEntity.ok().build();
	}

}

    
    
