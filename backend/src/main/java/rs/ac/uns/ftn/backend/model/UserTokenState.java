package rs.ac.uns.ftn.backend.model;

import rs.ac.uns.ftn.backend.model.enumeration.Role;

/*Klasa kojom prezerviram neko stanje tj. klasa koja treba da se ponasa kao cookie. 
Klasa koja treba od stateless http protokola da napravi stateful protokol
Klasa koja omogucava da polepim u token sve sto mi treba 
U token(cookie) nalepim sve sto mi treba da se prenese*/
public class UserTokenState {
	
	private String accessToken;
	private Long expiresIn;
	private Role role;

	public UserTokenState() {
		this.accessToken = null;
		this.expiresIn = null;
		this.setRole(null);
	}

	public UserTokenState(String accessToken, long expiresIn, Role role) {
		this.accessToken = accessToken;
		this.expiresIn = expiresIn;
		this.setRole(role);
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public Long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}