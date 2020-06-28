package rs.ac.uns.ftn.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.backend.model.Rating;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.service.RatingService;

@RestController
@RequestMapping(value="api/ratings")
public class RatingController {
	@Autowired  RatingService ratingService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Rating> addRating() {
		
		Rating rating = ratingService.addRating();
		
		/*
		 * //convert courses to DTOs List<CourseDTO> coursesDTO = new ArrayList<>(); for
		 * (Course s : courses) { coursesDTO.add(new CourseDTO(s)); }
		 */
		
		return new ResponseEntity<>(rating, HttpStatus.CREATED);
	}
	
	@GetMapping(produces = "application/json") 
	public ResponseEntity<List<Recipe>> rating() { 
		 List<Recipe> ratings = ratingService.ratings(); 
		 return new ResponseEntity<List<Recipe>>(ratings,HttpStatus.OK); 
	}

}
