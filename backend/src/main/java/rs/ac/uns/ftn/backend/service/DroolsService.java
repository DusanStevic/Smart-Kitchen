package rs.ac.uns.ftn.backend.service;

import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.backend.dto.InputDTO;
import rs.ac.uns.ftn.backend.model.Ingredient;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.repository.IngredientRepository;
import rs.ac.uns.ftn.backend.repository.RecipeRepository;

@Service
public class DroolsService {
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	
	
	private static Logger log = LoggerFactory.getLogger(DroolsService.class);

	
	private final KieContainer kieContainer;
	
	@Autowired
	public DroolsService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
	
	
	public List<Recipe> recommandRecipe(InputDTO inputDTO) {
		List<Recipe> recipes = recipeRepository.findAll();
		return recipes;
  
       
	}
	
	public List<Ingredient> calculateTotalPrice() {
		KieSession kieSession = kieContainer.newKieSession();		
		List<Ingredient> ingredients = ingredientRepository.findAll();
		for (Ingredient ingredient : ingredients) {
			
			kieSession.insert(ingredient);
		}
		kieSession.fireAllRules();
		kieSession.dispose();
		return ingredients;
  
       
	}

}
