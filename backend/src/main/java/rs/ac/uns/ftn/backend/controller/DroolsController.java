package rs.ac.uns.ftn.backend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.backend.model.Ingredient;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.service.DroolsService;

@RestController
@RequestMapping(value = "/api/drools")
public class DroolsController {
	
	private static Logger log = LoggerFactory.getLogger(SampleAppController.class);

	private final DroolsService droolsService;

	@Autowired
	public DroolsController(DroolsService droolsService) {
		this.droolsService = droolsService;
	}
	

    
    @GetMapping(value = "/calculate", produces = "application/json")
	public ResponseEntity<List<Ingredient>> calculate() {
		List<Ingredient> ingredients = droolsService.calculateTotalPrice();
		log.debug("Item request received for: " + ingredients);	
		return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
	}
    
  //all ingredients prebaci ga u poseban RecipeController
    @GetMapping(value = "/all-active-recipes", produces = "application/json")
	public ResponseEntity<List<Recipe>> active() {
		List<Recipe> recipes = droolsService.recipes();
		return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
	}
    
    @GetMapping(value = "/deactivate", produces = "application/json")
   	public ResponseEntity<List<Recipe>> deactivate() {
   		List<Recipe> recipes = droolsService.deactivate();
   		return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
   	}
    
/*	 
	 @GetMapping(value = "/ratings", produces = "application/json") 
	 public ResponseEntity<List<Rating>> rating() { 
		 List<Rating> ratings = droolsService.ratings(); 
		 return new ResponseEntity<List<Rating>>(ratings,HttpStatus.OK); 
	 }
	 
*/
	 
	

	 
    

}
