package rs.ac.uns.ftn.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.backend.model.User;
import rs.ac.uns.ftn.backend.service.UserService;

@RestController
@RequestMapping(value="api/users")
public class UserController {
	
	@Autowired  
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> getRecipes() {
		
		List<User> users = userService.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

}
