package com.woodapp.users;

import com.woodapp.authorization.AppUserRole;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name="USERS")

public class User implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", updatable=false)
	private Integer id;

	@NotNull
	@Column(nullable=false)
	private String firstName;

	@NotNull
	@Column(nullable=false)
	private String lastName;

	@NotNull
	@Email
	@Column(nullable=false, unique = true)
	public String email;

	@NotNull
//	@Size(min=5, max=20)
	@Column(nullable=false, unique=true)
	private String password;

	private String gender;

//	(we'll need a placeholder like MM/DD/YY)
	private String birthday;

	@Column()
	private Integer phoneNumber;

	@Column(nullable=false)
    private String streetAddress;
	private String city;
	private String state;
	private Integer zipCode;
	private LocalDate signUpDate = LocalDate.now();
	private String membershipType;
	private Boolean locked;
	private Boolean enabled;
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;

	public User(String firstName, String lastName, String email, String password, String gender, String birthday,
				Integer phoneNumber, String streetAddress, String city, String state, Integer zipCode, LocalDate signUpDate, String membershipType,
				AppUserRole appUserRole) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.signUpDate = signUpDate;
		this.appUserRole = appUserRole;
		this.membershipType = membershipType;
	}

	public User() {
	}

}