package com.woodapp.users;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Entity(name="user_data")
@Table(
		name="user_data",
		uniqueConstraints = {
				@UniqueConstraint(name = "user_email_unique", columnNames = "email")
		}
)
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
	public Integer id;

	@Size(min=2, max=30)
	@Column(
            name="first_name",
            nullable = false,
            columnDefinition = "TEXT"
    )
	public String firstName;

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

	@Size(min=6, max=20)
	@Column(
            name="password",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
	public String password;
	
	@Column(name="gender")
	private String gender;
	
	@Column(
            name="birthday",
            nullable = false,
            columnDefinition = "Integer"
    )
	private Integer birthday;

	@Column(
            name="phone_number",
            nullable = false,
            columnDefinition = "Integer"
    )
	private Integer phoneNumber;

	@Column(
            name="street_address",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String streetAddress;

	@Column(
			name="state",
			nullable = false,
			columnDefinition = "TEXT"
	)
	private String state;

	@Column(
			name="zip_code",
			nullable = false,
			columnDefinition = "Integer"
	)
	private Integer zipCode;


	public Users() {
	}

	public Users(String firstName, String lastName, String email, String password, String gender, Integer birthday,
				 Integer phoneNumber, String streetAddress, String state, Integer zipCode) {
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

    @Override
	public String toString() {
		return "Users [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", gender=" + gender + ", birthday=" + birthday + ", phoneNumber="
				+ phoneNumber + ", address=" + address + "]";
	}
    
    
}