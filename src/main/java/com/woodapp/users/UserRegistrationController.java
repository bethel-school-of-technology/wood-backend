package com.woodapp.users;

import java.util.List;

import com.woodapp.authorization.AppUserService;
import com.woodapp.authorization.AuthenticationResponse;
import com.woodapp.authorization.RegistrationRequest;
import com.woodapp.authorization.RegistrationService;
import com.woodapp.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api")
@RestController
@AllArgsConstructor
public class UserRegistrationController {

	UserRepository dao;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AppUserService appUserService;
	@Autowired
	private JwtUtil jwtTokenUtil;

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
    public ResponseEntity<User> addUser(@RequestBody User users) {
		User createdUser = dao.save(users);
		return ResponseEntity.ok(createdUser);
	}

    @PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User users) throws Exception {
		User updateUser = dao.findById(id).orElse(null);
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
			updateUser.setStreetAddress(users.getStreetAddress());
			updateUser.setState(users.getState());
			updateUser.setZipCode(users.getZipCode());
			updateUser.setSignUpDate(users.getSignUpDate());
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

	@PostMapping("/user/registration")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody RegistrationRequest request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
			);
		}   catch (BadCredentialsException e){
			throw new Exception("Incorrect email or password", e);
		}

		final UserDetails userDetails = appUserService.loadUserByUsername(request.getEmail());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
    
    
