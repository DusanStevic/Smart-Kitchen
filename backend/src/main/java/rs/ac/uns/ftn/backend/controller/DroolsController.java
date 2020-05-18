package rs.ac.uns.ftn.backend.controller;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rs.ac.uns.ftn.backend.dto.InputDTO;
import rs.ac.uns.ftn.backend.model.Ingredient;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.service.DroolsService;

@RestController
@RequestMapping(value = "/api/drools")
public class DroolsController {
    @Autowired
    private DroolsService droolsService;
    
    @Autowired
	private KieContainer kieContainer;
    
    @PostMapping(value = "/recipe")
	public ResponseEntity<List<Recipe>> recipe(@RequestBody InputDTO dto) {
		List<Recipe> recipes = droolsService.recommandRecipe(dto);
		return new ResponseEntity<List<Recipe>>(recipes, HttpStatus.OK);
	}
    //all ingredients prebaci ga u poseban RecipeController
    @GetMapping(value = "/all-ingredients", produces = "application/json")
	public ResponseEntity<List<Ingredient>> allIngredients() {
		List<Ingredient> ingredients = droolsService.calculateTotalPrice();
		return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
	}
    
    @GetMapping(value = "/calculate", produces = "application/json")
	public ResponseEntity<List<Ingredient>> calculate() {
    	KieSession kieSession = kieContainer.newKieSession();
		List<Ingredient> ingredients = droolsService.calculateTotalPrice();
		for (Ingredient ingredient : ingredients) {
			kieSession.insert(ingredient);
		}
	    
		kieSession.fireAllRules();
	    
		
		return new ResponseEntity<List<Ingredient>>(ingredients, HttpStatus.OK);
	}

}
