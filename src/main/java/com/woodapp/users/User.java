package com.woodapp.users;

import javax.persistence.*;

@Entity
@Table(name="user_data")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id; 
	public String firstName;
	public String lastName;
	public String username;
	public String password;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}