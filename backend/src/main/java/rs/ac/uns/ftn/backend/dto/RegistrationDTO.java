package rs.ac.uns.ftn.backend.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class RegistrationDTO {
	
	@NotNull(message = "Username is mandatory")
	@Length(min=1, max = 45, message="Username is mandatory and must not be longer than {max} or shorter than {min}")
	private String username;
	
	@NotNull(message = "Password is mandatory")
	@Length(min=3, max = 80, message="Password is mandatory and must not be longer than {max} or shorter than {min}")
	private String password;
	
	@NotNull(message = "Email is mandatory")
	//@Length(min=1, max = 45, message="Username is mandatory and must not be longer than {max} or shorter than {min}")
	private String email;
	
	@NotNull(message = "First name is mandatory")
	@Length(min=1, max = 30, message="First name is mandatory and must not be longer than {max} or shorter than {min}")
	private String firstName;
	
	@NotNull(message = "Last name is mandatory")
	@Length(min=1, max = 80, message="Last name is mandatory and must not be longer than {max} or shorter than {min}")
	private String lastName;
	
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	

}
