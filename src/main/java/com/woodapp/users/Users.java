package com.woodapp.users;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="user_data")
public class Users {
	
	
	
	public Users(Integer id, @NotNull @Size(min = 2, max = 30) String firstName,
			@NotNull @Size(min = 2, max = 30) String lastName, @Email String email,
			@NotNull @Size(min = 6, max = 20) String password, String gender, Integer birthday,
			@NotNull Integer phoneNumber, @NotNull String address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(
            name = "id",
            updatable = false
    )
	public Integer id;
	
	@NotNull
	@Size(min=2, max=30)
	@Column(
            name="first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
	public String firstName;
	
	@NotNull
	@Size(min=2, max=30)
	@Column(
            name="last_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
	public String lastName;
	
	@Email
	@Column(
            name="email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
	public String email;
	
	@NotNull
	@Size(min=6, max=20)
	@Column(
            name="password",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
	public String password;
	
	@Column(
            name="gender"
    )
	private String gender;
	
	@Column(
            name="birthday",
            nullable = false,
            columnDefinition = "Integer"
    )
	private Integer birthday;
	
	@NotNull
	@Column(
            name="age",
            nullable = false,
            columnDefinition = "Integer"
    )
	private Integer phoneNumber;
	
	@NotNull
	@Column(
            name="street_address",
            nullable = false,
            columnDefinition = "Integer"
    )
	//will probably need to add more data type variables for address
    private String address;

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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
	@Override
	public String toString() {
		return "Users [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", gender=" + gender + ", birthday=" + birthday + ", phoneNumber="
				+ phoneNumber + ", address=" + address + "]";
	}
    
    
}