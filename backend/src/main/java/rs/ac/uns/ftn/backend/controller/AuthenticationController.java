package rs.ac.uns.ftn.backend.controller;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;












import rs.ac.uns.ftn.backend.common.DeviceProvider;
import rs.ac.uns.ftn.backend.converters.UserConverter;
import rs.ac.uns.ftn.backend.dto.RegistrationDTO;
import rs.ac.uns.ftn.backend.dto.UserDTO;
import rs.ac.uns.ftn.backend.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.backend.exceptions.SavingException;
import rs.ac.uns.ftn.backend.model.Administrator;
import rs.ac.uns.ftn.backend.model.RegisteredUser;
import rs.ac.uns.ftn.backend.model.Role;
import rs.ac.uns.ftn.backend.model.User;
import rs.ac.uns.ftn.backend.model.UserTokenState;
import rs.ac.uns.ftn.backend.security.TokenUtils;
import rs.ac.uns.ftn.backend.security.auth.JwtAuthenticationRequest;
import rs.ac.uns.ftn.backend.service.CustomUserDetailsService;
import rs.ac.uns.ftn.backend.service.UserService;



//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

	@Autowired
	TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private DeviceProvider deviceProvider;
	
	@Autowired
    private UserService userService;

	@PostMapping(value = "/login")
	@CrossOrigin()
	public ResponseEntity<String> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest,
			HttpServletResponse response, Device device) throws AuthenticationException, IOException {
		System.out.println("ULETEO SAM U LOGOVANJE");
		if (device == null) {
			System.out.println("PUKLA DETEKCIJA");
		}
		if (device.isNormal()) {
			System.out.println("KACIS SE PREKO KOMPA");
		} else if (device.isTablet()) {
			System.out.println("KACIS SE PREKO TABLETA");
		} else if (device.isMobile()) {
			System.out.println("KACIS SE PREKO MOBILNOG");
		}
		
		
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(
						authenticationRequest.getUsername(),
						authenticationRequest.getPassword()));

		// Ubaci username + password u kontext
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token
		User user = (User) authentication.getPrincipal();
		
		@SuppressWarnings("unused")
		int expiresIn = tokenUtils.getExpiredIn(device);
		Role role = null;
		//zajedno sa tokenom salje se i uloga na front pa u zavisnosti od tipa korisnika
		//na frontu ce ce otvarati posebno strana za usera, admina i sys-admina
	
		if (user instanceof Administrator) {
			role = Role.ROLE_ADMIN;
		}

		else {
			role = Role.ROLE_REGISTERED_USER;
		}

		String jwt = tokenUtils.generateToken(user.getUsername(), role.toString(), device);
		// Vrati token kao odgovor na uspesno autentifikaciju
		return new ResponseEntity<String>(jwt, HttpStatus.OK);
	}

	@PostMapping(value = "/refresh")
	public ResponseEntity<?> refreshAuthenticationToken(HttpServletRequest request) {

		String token = tokenUtils.getToken(request);
		String username = this.tokenUtils.getUsernameFromToken(token);
	    User user = (User) this.userDetailsService.loadUserByUsername(username);
	    
	    Role userType = null;
		//zajedno sa tokenom salje se i uloga na front pa u zavisnosti od tipa korisnika
		//na frontu ce ce otvarati posebno strana za usera, admina i sys-admina
	
		if (user instanceof Administrator) {
			userType = Role.ROLE_ADMIN;
		}

		else {
			userType = Role.ROLE_REGISTERED_USER;
		}

		Device device = deviceProvider.getCurrentDevice(request);

		if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = tokenUtils.refreshToken(token, device);
			int expiresIn = tokenUtils.getExpiredIn(device);

			return ResponseEntity.ok(new UserTokenState(refreshedToken, expiresIn,userType));
		} else {
			UserTokenState userTokenState = new UserTokenState();
			return ResponseEntity.badRequest().body(userTokenState);
		}
	}

	@PostMapping(value = "/change-password")
	@PreAuthorize("hasAnyRole('ROLE_REGISTERED_USER', 'ROLE_ADMIN', 'ROLE_SYS_ADMIN')")
	public ResponseEntity<?> changePassword(@RequestBody PasswordChanger passwordChanger) {
		userDetailsService.changePassword(passwordChanger.oldPassword, passwordChanger.newPassword);
		
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		return ResponseEntity.accepted().body(result);
	}

	static class PasswordChanger {
		public String oldPassword;
		public String newPassword;
	}
	
	
	/*Prilikom slanja sa fronta mora se poslati default slika ako korisnik nece da uploduje neku
	 * simultano slanje json+multipart-data*/
	@PostMapping(value = "/registerUser",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> registerUser(@RequestBody RegistrationDTO registrationDTO) {
		RegisteredUser registeredUser = userService.registerUser(registrationDTO);
		return new ResponseEntity<>(UserConverter.UserToUserDTO(registeredUser), HttpStatus.OK);
	}
	

	/*Prilikom slanja sa fronta mora se poslati default slika ako korisnik nece da uploduje neku
	 * simultano slanje json+multipart-data*/
	@PostMapping(value = "/registerAdmin",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> registerAdmin(@RequestBody RegistrationDTO registrationDTO) {
		Administrator administrator = userService.registerAdmin(registrationDTO);
		return new ResponseEntity<>(UserConverter.UserToUserDTO(administrator), HttpStatus.OK);
	}
	
	
	
	
	//prilikom potvrdjivanja konfirmacionog registracionog mail-a account se aktivira
	@GetMapping(value = "/confirmRegistration/{encodedId}")
	public RedirectView confirmRegistration(@PathVariable("encodedId") String encodedId)
			throws UnsupportedEncodingException, ResourceNotFoundException, SavingException {
		
		byte[] bytes = Base64.getDecoder().decode(encodedId);
		String str = new String(bytes);
		Long decodedId = Long.valueOf(str);
		User user = userService.findById(decodedId);
		if (user == null) {
			return null;
		}
		user.setEnabled(true);
		userService.save(user);
		return new RedirectView("http://localhost:4200/auth/login");
		
			
	}

	
	
	
}