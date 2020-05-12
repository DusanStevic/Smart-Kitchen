package rs.ac.uns.ftn.backend.dto;

import java.util.Date;

public class UserUpdateDTO {
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String imageUrl;
	private boolean enabled;
	private Date lastPasswordResetDate;
	private boolean firstTime;

	public UserUpdateDTO() {
		super();
	}

	public UserUpdateDTO(Long id, String username, String firstName,
			String lastName, String email, String phoneNumber, String imageUrl,
			boolean enabled, Date lastPasswordResetDate, boolean firstTime) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.imageUrl = imageUrl;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
		this.firstTime = firstTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public boolean isFirstTime() {
		return firstTime;
	}

	public void setFirstTime(boolean firstTime) {
		this.firstTime = firstTime;
	}

}
