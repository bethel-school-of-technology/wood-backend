package com.woodapp.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.woodapp.models.Role;
import com.woodapp.models.User;
import com.woodapp.repositories.RoleRepository;
import com.woodapp.repositories.UserRepository;
import com.woodapp.services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/")
public class UserController {

	UserRepository userRepo;
	RoleRepository roleRepo;
	private final UserService userService;

    @GetMapping("/user/find/all")
	public ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok().body(userService.getUsers());
	}

    @GetMapping("/user/find/{id}")
	public ResponseEntity<User> getOneUser(@PathVariable("id") Integer id) {
		User foundUser = userRepo.findById(id).orElse(null);
		if (foundUser == null) {
			return ResponseEntity.notFound().header("User", "Nothing found with that id").build();
		}
		return ResponseEntity.ok(foundUser);
	}

//    @PostMapping("/add")
//    public ResponseEntity<User> addUser(@RequestBody User user) {
//		User createdUser = dao.save(user);
//		return ResponseEntity.ok(createdUser);
//	}

	@PostMapping("/user/add")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/add").toUriString());
		return ResponseEntity.created(uri).body(userService.saveUser(user));
		//A 201 response is more precise than 200 ok
	}

    @PutMapping("/user/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, @RequestBody User user) throws Exception {
		User updateUser = userRepo.findById(id).orElse(null);
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
			userRepo.save(updateUser);
		}
		return ResponseEntity.ok(updateUser);
	}

    @DeleteMapping("/user/delete/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") Integer id) {
		User foundUser = userRepo.findById(id).orElse(null);
		if (foundUser == null) {
			return ResponseEntity.notFound().header("User", "Nothing found with that username").build();
		} else {
			userRepo.delete(foundUser);
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("/role/find/all")
	public List<Role> getAllRoles() {
    	List<Role> foundAllRoles = roleRepo.findAll();
    	return foundAllRoles;
	}

	@PostMapping("/role/save")
	public ResponseEntity<Role> saveRole(@RequestBody Role role) {
		URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
		return ResponseEntity.created(uri).body(userService.saveRole(role));
	}

	@PostMapping("/role/added")
	public ResponseEntity<Role> addRoleToUser(@RequestBody RoleToUserForm form) {
		userService.addRoleToUser(form.getUsername(), form.getRoleName());
		return ResponseEntity.ok().build();
	}

	@PostMapping("/user/register")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void registerUser(@RequestParam("username") String username, @RequestParam("password") String password) {
		User foundUser = userRepo.findByUsername(username);
		if (foundUser == null) {
			User newUser = new User();
			newUser.setUsername(username);
			newUser.setPassword(password);
			userService.saveUser(newUser);
		}
	}

//	@PostMapping("/register")
//	@ResponseStatus(code = HttpStatus.CREATED)
//	public void ResponseEntity<User> register(@Valid @RequestBody User user) {
//    	userService.saveUser(user);
//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//	}

	@GetMapping("token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
			try {
				String refresh_token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				User user = userService.getUser(username);
				String access_token = JWT.create()
						.withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
						.sign(algorithm);
				Map<String, String> tokens = new HashMap<>();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			}catch (Exception exception){
				response.setHeader("error", exception.getMessage());
				response.setStatus(FORBIDDEN.value());
				Map<String, String> error = new HashMap<>();
				error.put("error_message", exception.getMessage());
				response.setContentType(APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		}else {
			throw new RuntimeException("Refresh token is missing");
		}
	}
}

@Data
class RoleToUserForm {
	private String username;
	private String roleName;
}

    
    
