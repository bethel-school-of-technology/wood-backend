package com.woodapp.users;

import com.woodapp.authorization.AppUserRole;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name="USERS")

public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="app_user_id", updatable=false)
	private Integer id;

	@NotNull
	@Size(min=2, max=30)
	@Column(name="first_name", nullable=false)
	private String firstName;

	@NotNull
	@Size(min=2, max=30)
	@Column(name="last_name", nullable=false)
	private String lastName;

	@NotNull
	@Email
	@Column(name="email", nullable=false, unique = true)
	public String email;

	@NotNull
	@Size(min=6, max=20)
	@Column(name="password", nullable=false, unique=true)
	private String password;

	private String gender;

	@Temporal(TemporalType.DATE)
	private Date birthday;

	@NotNull
	@Column(name="phone_number", nullable=false)
	private Integer phoneNumber;

	@NotNull
	@Column(name="street_address", nullable=false)
    private String streetAddress;
	private String state;
	private Integer zipCode;
	private LocalDate signUpDate = LocalDate.now();
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;
	private Boolean locked;
	private Boolean enabled;

	public User(String firstName, String lastName, String email, String password, String gender, Date birthday,
				Integer phoneNumber, String streetAddress, String state, Integer zipCode, LocalDate signUpDate,
				AppUserRole appUserRole) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.state = state;
		this.zipCode = zipCode;
		this.signUpDate = signUpDate;
		this.appUserRole = appUserRole;
	}

	public User() {

	}

	public User(String firstName, String lastName, String email, String password, AppUserRole user) {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
		return Collections.singletonList(authority);
	}

	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return email;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return !locked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
}