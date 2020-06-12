package rs.ac.uns.ftn.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.backend.model.Rating;
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

}
