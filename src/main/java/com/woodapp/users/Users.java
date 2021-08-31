package com.woodapp.users;

import com.woodapp.registration.AppUserRole;
import lombok.EqualsAndHashCode;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@EqualsAndHashCode
@Entity(name="Users")
@Table(name="users")

public class Users implements UserDetails{

	@Id
	@SequenceGenerator(name="user_sequence", sequenceName="user_sequence", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_sequence")

	@Column(name="id", updatable=false)
	private Integer id;

	@NotNull
	@Size(min=2, max=30)
	@Column(name="first_name", nullable=false, columnDefinition="TEXT")
	private String firstName;

	@NotNull
	@Size(min=2, max=30)
	@Column(name="last_name", nullable=false, columnDefinition="TEXT")
	private String lastName;

	@NotNull
	@Email
	@Column(name="email", nullable=false, columnDefinition="TEXT", unique = true)
	private String email;

	private String username;

	@NotNull
	@Size(min=6, max=20)
	@Column(name="password", nullable=false, unique=true)
	private String password;

	private String gender;
	private Integer birthday;

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

	public Users() {
	}

	public Users(String firstName, String lastName, String email, String username, String password, String gender, Integer birthday,
				 Integer phoneNumber, String streetAddress, String state, Integer zipCode, LocalDate signUpDate,
				 AppUserRole appUserRole, Boolean locked, Boolean enabled) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.streetAddress = streetAddress;
		this.state = state;
		this.zipCode = zipCode;
		this.signUpDate = signUpDate;
		this.appUserRole = appUserRole;
		this.locked = locked;
		this.enabled = enabled;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {this.id = id;}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
//	public String getUsername(){ return username;}
	public void setUsername(String username){this.username = username;}
//	public String getPassword() { return password;}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Integer getBirthday() {
        return birthday;
    }
    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }
    public Integer getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getZipCode() {
		return zipCode;
	}
	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	public LocalDate getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(LocalDate signUpDate) {
		this.signUpDate = signUpDate;
	}

	@Override
	public String toString() {
		return "Users{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", gender='" + gender + '\'' +
				", birthday=" + birthday +
				", phoneNumber=" + phoneNumber +
				", streetAddress='" + streetAddress + '\'' +
				", state='" + state + '\'' +
				", zipCode=" + zipCode +
				", signUpDate=" + signUpDate +
				'}';
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
	public String getUsername() { return username;}
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