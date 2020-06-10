package rs.ac.uns.ftn.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.backend.dto.InputDTO;
import rs.ac.uns.ftn.backend.model.Ingredient;
import rs.ac.uns.ftn.backend.model.Rating;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.repository.IngredientRepository;
import rs.ac.uns.ftn.backend.repository.RatingRepository;
import rs.ac.uns.ftn.backend.repository.RecipeRepository;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Service
public class DroolsService {
	@Autowired
	private RecipeRepository recipeRepository;
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@Autowired
	private RatingRepository ratingRepository;
	
	
	private static Logger log = LoggerFactory.getLogger(DroolsService.class);

	
	private final KieContainer kieContainer;
	
	@Autowired
	public DroolsService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
	
	
	public List<Ingredient> ingredients() {
		List<Ingredient> ingredients = ingredientRepository.findAll();
		return ingredients;
  
       
	}
	
	public List<Recipe> recipes() {
		List<Recipe> recipes = recipeRepository.findAll();
		List<Recipe> active = new ArrayList<>();
		for (Recipe recipe : recipes) {
			if (recipe.isActive()==true) {
				active.add(recipe);
			}
			
		}
		return active;
  
       
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
	
	public List<Recipe> deactivate() {
		KieSession kieSession = kieContainer.newKieSession();		
		List<Recipe> recipes = recipeRepository.findAll();
		for (Recipe recipe : recipes) {
			
			kieSession.insert(recipe);
		}
		kieSession.fireAllRules();
		kieSession.dispose();
		return recipes;
  
       
	}
	
	//ovo radi
	
	  public List<Rating> ratings() { 
		  KieSession kieSession = kieContainer.newKieSession(); 
		  List<Rating> ratings = ratingRepository.findAll(); 
		  DecimalFormat decimalFormat = new DecimalFormat("#.##"); 
		  decimalFormat.setRoundingMode(RoundingMode.CEILING);
		  for (Rating rating : ratings) { 
			  kieSession.insert(rating); 
		  }
		  kieSession.setGlobal("rounding", decimalFormat);
		  kieSession.getAgenda().getAgendaGroup("rating").setFocus();
		  kieSession.fireAllRules();
		  for (Rating rating : ratings) { 
			  kieSession.insert(rating); 
		  }
		  kieSession.getAgenda().getAgendaGroup("high").setFocus();
		  kieSession.fireAllRules();
		  
		  kieSession.dispose(); 
		  return ratings;
	  
	  
	  }
	 
	
	/*
	 * public List<Recipe> ratings() { KieSession kieSession =
	 * kieContainer.newKieSession(); List<Recipe> recipes =
	 * recipeRepository.findAll(); DecimalFormat decimalFormat = new
	 * DecimalFormat("#.##"); decimalFormat.setRoundingMode(RoundingMode.CEILING);
	 * for (Recipe recipe : recipes) { kieSession.insert(recipe); }
	 * 
	 * kieSession.setGlobal("rounding", decimalFormat); kieSession.fireAllRules();
	 * kieSession.dispose(); return recipes;
	 * 
	 * 
	 * }
	 */

}
