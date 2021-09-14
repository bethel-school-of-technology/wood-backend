package com.woodapp.models;


import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import javax.validation.constraints.Size;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users", uniqueConstraints = { @UniqueConstraint(columnNames = "email")})
public class User  {

	@Id
	@Column(name = "user_id", updatable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Column(nullable=false)
	private String firstName;

	@NotNull
	@Column(nullable=false)
	private String lastName;

	@NotNull
	@Column(nullable=false)
	private String username;

	@NotNull
	@Email
	@Column(nullable=false, unique = true)
	public String email;

	@NotNull
//	@Size(max=20)
	@Column(nullable=false, unique=true)
	private String password;

	private String gender;

//	(we'll need a placeholder like MM/DD/YY on the form)
	private String birthday;

	@Column(nullable = false)
	private Integer phoneNumber;

	@Column(nullable=false)
    private String streetAddress;

	private String city;
	private String state;
	private Integer zipCode;
	private LocalDate signUpDate = LocalDate.now();

	//will need to be a drop-down menu on the form
	private String membershipType;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "gym_id")
	private Gym gym;
	//This is to map the USERS table and the GYM table using their ids**

	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> roles = new ArrayList<>();


//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "user_id")
//	private Role roles;
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(	name = "user_roles",
//			joinColumns = @JoinColumn(name = "user_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<Role> roles = new HashSet<>();


//	@Override
//	public int hashCode() {
//		return getClass().hashCode();
//	}


	public User(String firstName, String lastName, String username, String email, String password, String gender, String birthday,
				Integer phoneNumber, String streetAddress, String city, String state, Integer zipCode, LocalDate signUpDate,
				String membershipType, Collection<Role> roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
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
		this.membershipType = membershipType;
		this.roles = roles;
	}

	@Bean(name="entityManagerFactory")
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		return sessionFactory;
	}

}