package com.woodapp.users;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Entity(name="Users")
@Table(name="users")

public class Users {

	@Id
	@SequenceGenerator(
			name = "user_sequence",
			sequenceName = "user_sequence",
			allocationSize = 1
	)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "user_sequence"
	)
	@Column(
            name = "id",
            updatable = false
    )
	private Integer id;

	@Size(min=2, max=30)
	@Column(
            name="first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
	private String firstName;

	@Size(min=2, max=30)
	@Column(
            name="last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
	private String lastName;
	
	@Email
	@Column(
            name="email",
            nullable = false,
            columnDefinition = "TEXT",
			unique = true
    )
	private String email;

	@Size(min=6, max=20)
	@Column(
            name="password",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
	private String password;

	private String gender;
	@Size(min=6, max=8)
	private Integer birthday;

	@Column(
            name="phone_number",
            nullable = false,
            columnDefinition = "Integer"
    )
	private Integer phoneNumber;

	@Column(
            name="street_address",
            nullable = false
    )
    private String streetAddress;
	private String state;
	private Integer zipCode;
	private LocalDate signUpDate;

	public Users() {
	}

	public Users(String firstName, String lastName, String email, String password, String gender, Integer birthday,
				 Integer phoneNumber, String streetAddress, String state, Integer zipCode, LocalDate signUpDate) {
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
	}

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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
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
}