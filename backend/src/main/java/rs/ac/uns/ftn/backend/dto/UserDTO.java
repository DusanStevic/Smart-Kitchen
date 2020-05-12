package rs.ac.uns.ftn.backend.dto;

import java.util.List;
import java.util.stream.Collectors;

import rs.ac.uns.ftn.backend.model.Authority;
import rs.ac.uns.ftn.backend.model.User;



public class UserDTO {
	private Long id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String imageUrl;
	private List<String> authorities;
	
	public UserDTO(User user){
		this.id = user.getId();
		this.username = user.getUsername();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.imageUrl = user.getImageUrl();
		//Java 1.8 way Primer sa predavanja Advanced java 
		//umesto da iteriramo kroz for petlju koristimo stream da namapiramo 
		//authorities objekta User na authorites objekta UserDTO
		//kraci zapis uz koriscenje funkcionalnog programiranja, streamova i lambda izraza
    	/*List<GrantedAuthority> grantedAuthorities = user.getUserAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority().getName()))
                .collect(Collectors.toList());*/
		this.authorities = user.getAuthorities().stream().map(authority -> ((Authority) authority).getRole().toString()).collect(Collectors.toList());
		
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

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	

}
