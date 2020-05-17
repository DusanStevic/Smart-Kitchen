package rs.ac.uns.ftn.backend.converters;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import rs.ac.uns.ftn.backend.dto.RegistrationDTO;
import rs.ac.uns.ftn.backend.model.Administrator;
import rs.ac.uns.ftn.backend.model.Authority;
import rs.ac.uns.ftn.backend.model.RegisteredUser;
import rs.ac.uns.ftn.backend.model.enumeration.Role;



public class RegistrationConverter {
	
	static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	//konvertuje RegistrationDTO u RegisteredUser
	public static RegisteredUser RegistrationDTO2RegisteredUser(RegistrationDTO registrationDTO){
		RegisteredUser registeredUser = new RegisteredUser();
		registeredUser.setUsername(registrationDTO.getUsername());
		registeredUser.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
		//account ce biti aktiviran kada se potvrdi registracioni konfirmacioni mail
		registeredUser.setEnabled(false);
		//kada se user bude prvi put prijavljivao na sistem mora da promeni password
		registeredUser.setFirstTime(false);
		registeredUser.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		registeredUser.setFirstName(registrationDTO.getFirstName());
		registeredUser.setLastName(registrationDTO.getLastName());
		registeredUser.setEmail(registrationDTO.getEmail());
		//slika ce biti nalepljena na korisnicki profil iz FileUploadService
		List<Authority> authorities = new ArrayList<>();
		Authority a = new Authority();
		a.setRole(Role.ROLE_REGISTERED_USER);
		authorities.add(a);
		registeredUser.setAuthorities(authorities);
		return registeredUser;
	}
	//konvertuje RegistrationDTO u Administrator
	public static Administrator RegistrationDTO2Administrator(RegistrationDTO registrationDTO){
		Administrator administrator = new Administrator();
		administrator.setUsername(registrationDTO.getUsername());
		administrator.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
		//account ce biti aktiviran kada se potvrdi registracioni konfirmacioni mail
		administrator.setEnabled(false);
		//kada se administrator bude prvi put prijavljivao na sistem mora da promeni password
		administrator.setFirstTime(true);
		administrator.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
		administrator.setFirstName(registrationDTO.getFirstName());
		administrator.setLastName(registrationDTO.getLastName());
		administrator.setEmail(registrationDTO.getEmail());
		//slika ce biti nalepljena na korisnicki profil iz FileUploadService
		List<Authority> authorities = new ArrayList<>();
		Authority a = new Authority();
		a.setRole(Role.ROLE_ADMIN);
		authorities.add(a);
		administrator.setAuthorities(authorities);
		return administrator;
	}
}
