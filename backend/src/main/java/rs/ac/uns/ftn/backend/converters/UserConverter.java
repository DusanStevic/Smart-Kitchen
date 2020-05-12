package rs.ac.uns.ftn.backend.converters;

import rs.ac.uns.ftn.backend.dto.UserDTO;
import rs.ac.uns.ftn.backend.model.User;

public class UserConverter {
	//konvertuje Usera u UserDTO
	public static UserDTO UserToUserDTO(User user) {
		return new UserDTO(user);
	}
	
	
	

}
