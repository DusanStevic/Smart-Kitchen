package rs.ac.uns.ftn.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.backend.model.Ingredient;
import rs.ac.uns.ftn.backend.service.IngredientService;

@RestController
@RequestMapping(value="api/ingredients")
public class IngredientController {
	@Autowired  IngredientService ingredientService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Ingredient>> getIngredients() {
		
		List<Ingredient> ingredients = ingredientService.findAll();
		
		/*
		 * //convert courses to DTOs List<CourseDTO> coursesDTO = new ArrayList<>(); for
		 * (Course s : courses) { coursesDTO.add(new CourseDTO(s)); }
		 */
		
		return new ResponseEntity<>(ingredients, HttpStatus.OK);
	}

}
