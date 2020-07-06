package rs.ac.uns.ftn.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.backend.exceptions.BadRequestException;
import rs.ac.uns.ftn.backend.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.model.RegisteredUser;
import rs.ac.uns.ftn.backend.service.RecipeService;
import rs.ac.uns.ftn.backend.service.UserService;
import rs.ac.uns.ftn.backend.templates.BoundsFilterTemplateModel;
import rs.ac.uns.ftn.backend.templates.RecipeDifficultyTemplateModel;

@RestController
@RequestMapping(value="api/recipes")
public class RecipeController {
	
	@Autowired  RecipeService recipeService;
	
	@Autowired 
	private UserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Recipe>> getRecipes() {
		
		List<Recipe> recipes = recipeService.findAll();
		
		/*
		 * //convert courses to DTOs List<CourseDTO> coursesDTO = new ArrayList<>(); for
		 * (Course s : courses) { coursesDTO.add(new CourseDTO(s)); }
		 */
		
		return new ResponseEntity<>(recipes, HttpStatus.OK);
	}
	
	// TO DO PREDJI NA DTO
	/* get an recipe by id, permitted for all */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Recipe> getRecipeById(@PathVariable(value = "id") Long recipeId) throws ResourceNotFoundException {
		Recipe recipe = recipeService.getRecipeById(recipeId);
		return new ResponseEntity<>(recipe, HttpStatus.OK);
	}
	
	

	

	

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> addRecipeGrade(@PathVariable(value = "id") Long recipeId,@RequestParam(value = "grade") int grade)
			throws ResourceNotFoundException,BadRequestException{
		recipeService.addRecipeGrade(recipeId, grade);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@PostMapping(value = "/addRecipeDifficulty")
	public ResponseEntity<List<Recipe>> addRecipeDifficulty(@RequestBody RecipeDifficultyTemplateModel recipeDifficultyTemplateModel) {
		List<Recipe> recipes = recipeService.addRecipeDifficulty(recipeDifficultyTemplateModel);
		return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
	}
	
	@PostMapping(value = "/filterPreparationTimeBounds")
	public ResponseEntity<List<Recipe>> filterPreparationTimeBounds(@RequestBody BoundsFilterTemplateModel boundsFilterTemplateModel) {
		List<Recipe> recipes = recipeService.filterPreparationTimeBounds(boundsFilterTemplateModel);
		return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
	}
	
	@PostMapping(value = "/filterNumberOfIngredientsBounds")
	public ResponseEntity<List<Recipe>> filterNumberOfIngredientsBounds(@RequestBody BoundsFilterTemplateModel boundsFilterTemplateModel) {
		List<Recipe> recipes = recipeService.filterNumberOfIngredientsBounds(boundsFilterTemplateModel);
		return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getRecipeTotalCalories")
	public ResponseEntity<List<Recipe>> getRecipeTotalCalories() {
		List<Recipe> recipes = recipeService.getRecipeTotalCalories();
		return new ResponseEntity<>(recipes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getRecipesWeightLoss")
	public ResponseEntity<List<Recipe>> getRecipesWeightLoss() {
		List<Recipe> recipes = recipeService.getRecipesWeightLoss();
		return new ResponseEntity<>(recipes, HttpStatus.OK);
	}
	
	
	  @GetMapping(value = "/getBestRecipe") public ResponseEntity<Recipe>
	  getBestRecipe() throws ResourceNotFoundException { Recipe recipe = recipeService.getBestRecipe(); return new
	  ResponseEntity<>(recipe, HttpStatus.OK); }
	  
	  @GetMapping(value = "/allIngredients") public ResponseEntity<Recipe> allIngredients() throws ResourceNotFoundException { 
		  Recipe recipe = recipeService.getBestRecipe(); 
		  return new ResponseEntity<>(recipe, HttpStatus.OK); }
	  @GetMapping(value = "/reports") public ResponseEntity<RegisteredUser> reports() throws ResourceNotFoundException {  
		  RegisteredUser loggedUser = (RegisteredUser) userService.findById(1L);
		  return new ResponseEntity<>(loggedUser,HttpStatus.OK); }
	

	


}
