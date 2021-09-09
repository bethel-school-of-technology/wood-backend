package com.woodapp.models;

import com.woodapp.models.roles.UserRole;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false)
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
	@Size(max=20)
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
	private Boolean locked;
	private Boolean enabled;

	private Integer gym_id;
	//This is to map the USERS table and the GYM_INFO table using their ids**
//	@OneToOne
//	@JoinColumn(name = "gym_info_id", referencedColumnName = "gym_info_id")
//	private GymInfo gymInfo;
//
//	@ManyToMany(fetch = FetchType.LAZY)
//	@JoinTable(	name = "user_roles",
//			joinColumns = @JoinColumn(name = "user_id"),
//			inverseJoinColumns = @JoinColumn(name = "role_id"))
//	private Set<UserRole> roles = new HashSet<>();

	public User() {
		super();
	}

	public User(String firstName, String lastName, String email, String password, String gender, String birthday,
				Integer phoneNumber, String streetAddress, String city, String state, Integer zipCode, LocalDate signUpDate, String membershipType) {
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
		this.membershipType = membershipType;
	}


	public boolean isAccountNonExpired(){
		return true;
	}

	public boolean isAccountNonLocked(){
		return true;
	}

	public boolean isCredentialsNonExpired(){
		return true;
	}

	public boolean isEnabled(){
		return true;
	}
}