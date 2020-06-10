package rs.ac.uns.ftn.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.service.RecipeService;

@RestController
@RequestMapping(value="api/recipes")
public class RecipeController {
	
	@Autowired  RecipeService recipeService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Recipe>> getRecipes() {
		
		List<Recipe> recipes = recipeService.findAll();
		
		/*
		 * //convert courses to DTOs List<CourseDTO> coursesDTO = new ArrayList<>(); for
		 * (Course s : courses) { coursesDTO.add(new CourseDTO(s)); }
		 */
		
		return new ResponseEntity<>(recipes, HttpStatus.OK);
	}

}
