package rs.ac.uns.ftn.backend.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import org.kie.api.runtime.rule.QueryResultsRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.backend.exceptions.BadRequestException;
import rs.ac.uns.ftn.backend.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.backend.model.Ingredient;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.model.RegisteredUser;
import rs.ac.uns.ftn.backend.repository.RecipeRepository;
import rs.ac.uns.ftn.backend.repository.UserRepository;
import rs.ac.uns.ftn.backend.templates.BoundsFilterTemplateModel;
import rs.ac.uns.ftn.backend.templates.RecipeDifficultyTemplateModel;

@Service
public class RecipeService {
	
	private static Logger log = LoggerFactory.getLogger(SampleAppService.class);

	private final KieContainer kieContainer;

	
	@Autowired 
	public RecipeService(KieContainer kieContainer) {
	  log.info("Initialising a new example session."); 
	  this.kieContainer = kieContainer; 
	}
	
	@Autowired
	private DroolsService droolsService;
	
	@Autowired 
	RecipeRepository recipeRepository;
	
	@Autowired 
	UserRepository userRepository;
	@Autowired 
	private UserService userService;
	
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}
	
	public Recipe getRecipeById(Long id) throws ResourceNotFoundException {
		Recipe  recipe  = recipeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recipe with id '" + id + "' doesn't exist."));

		return recipe;
	}
	
   
    
	public void addRecipeGrade(Long id, int grade) throws ResourceNotFoundException,BadRequestException {
		Recipe  recipe  = recipeRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Recipe with id '" + id + "' doesn't exist."));
        if(!(1 <= grade && grade <= 5)){
            throw new BadRequestException("Recipe grade not in range from 1 to 5");
        }
		switch (grade) {
		  case 1:
		    recipe.getRating().setFrequency1(recipe.getRating().getFrequency1()+1);
		    break;
		  case 2:
		    recipe.getRating().setFrequency2(recipe.getRating().getFrequency2()+1);
		    break;
		  case 3:
		    recipe.getRating().setFrequency3(recipe.getRating().getFrequency3()+1);
		    break;
		  case 4:
		    recipe.getRating().setFrequency4(recipe.getRating().getFrequency4()+1);
		    break;
		  case 5:
			recipe.getRating().setFrequency5(recipe.getRating().getFrequency5()+1);
		    break;
		  default:
		    System.out.println("Recipe grade not in range from 1 to 5");
		}
		recipeRepository.save(recipe);
		
		
	}
	
	public List<Recipe> addRecipeDifficulty(RecipeDifficultyTemplateModel recipeDifficultyTemplateModel) {
		InputStream template = RecipeService.class.getResourceAsStream("/drools/spring/templates/template-recipe-difficulty.drt");
        
        List<RecipeDifficultyTemplateModel> data = new ArrayList<RecipeDifficultyTemplateModel>();
        data.add(recipeDifficultyTemplateModel);    
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        System.out.println(drl);
        KieSession kieSession = droolsService.createKieSessionFromDRL(drl);
        List<Recipe> recipes = recipeRepository.findAll();
        for (Recipe recipe : recipes) {
        	kieSession.insert(recipe);
		}
        kieSession.fireAllRules();
        
        
		return recipes;
	}
	
	public List<Recipe> filterPreparationTimeBounds(BoundsFilterTemplateModel boundsFilterTemplateModel) {
		List<Recipe> filteredRecipe = new ArrayList<Recipe>();
		InputStream template = RecipeService.class.getResourceAsStream("/drools/spring/templates/template-preparation-time-bounds-filter.drt");
        List<BoundsFilterTemplateModel> data = new ArrayList<BoundsFilterTemplateModel>();
        data.add(boundsFilterTemplateModel);    
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        System.out.println(drl);
        KieSession kieSession = droolsService.createKieSessionFromDRL(drl);
        List<Recipe> recipes = recipeRepository.findAll();
        for (Recipe recipe : recipes) {
        	kieSession.insert(recipe);
		}
        kieSession.setGlobal("filteredRecipe", filteredRecipe);
        kieSession.fireAllRules();
		return filteredRecipe;
	}
	
	public List<Recipe> filterNumberOfIngredientsBounds(BoundsFilterTemplateModel boundsFilterTemplateModel) {
		List<Recipe> filteredRecipe = new ArrayList<Recipe>();
		InputStream template = RecipeService.class.getResourceAsStream("/drools/spring/templates/template-number-of-ingredients-bounds-filter.drt");
        List<BoundsFilterTemplateModel> data = new ArrayList<BoundsFilterTemplateModel>();
        data.add(boundsFilterTemplateModel);    
        ObjectDataCompiler converter = new ObjectDataCompiler();
        String drl = converter.compile(data, template);
        System.out.println(drl);
        KieSession kieSession = droolsService.createKieSessionFromDRL(drl);
        List<Recipe> recipes = recipeRepository.findAll();
        for (Recipe recipe : recipes) {
        	kieSession.insert(recipe);
		}
        kieSession.setGlobal("filteredRecipe", filteredRecipe);
        kieSession.fireAllRules();
		return filteredRecipe;
	}
	
	public List<Recipe> getRecipeTotalCalories() {
		KieSession kieSession = kieContainer.newKieSession();
		List<Recipe> recipes = findAll();
		for (Recipe recipe : recipes) {
			kieSession.insert(recipe);
		}
		QueryResults results = kieSession.getQueryResults("Total calories in recipe");
		for(QueryResultsRow queryResult : results) {
			Recipe recipe = (Recipe) queryResult.get("$recipe");
			Double totalCalories = (Double) queryResult.get("$totalCalories");
			recipe.setTotalCalories(totalCalories);
			
		

		}
		kieSession.dispose();
		return recipes;
	}
	

	
	public List<Recipe> getRecipesWeightLoss() { 
		KieSession kieSession = kieContainer.newKieSession();
		  List<Recipe> recipes = recipeRepository.findAll(); 
		  for (Recipe recipe : recipes) {
			  kieSession.insert(recipe); 
		  }
		  kieSession.fireAllRules();
		  kieSession.dispose(); 
		  return recipes;
	  
	  
	 }
	// Returns currently logged user
	private RegisteredUser getLoggedUser() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String username = authentication.getName();
			return (RegisteredUser) userRepository.findByUsername(username);
		}
		return null;
	}
	
	
	  public Recipe getBestRecipe() throws ResourceNotFoundException {
	  
	  KieSession kieSession = kieContainer.newKieSession(); 
	  List<Recipe> recipes =
	  recipeRepository.findAll(); for (Recipe recipe : recipes) {
	  kieSession.insert(recipe); } 
	  RegisteredUser loggedUser = (RegisteredUser) userService.findById(1L);
	  System.out.println(loggedUser.toString());
	  
	  for (Ingredient ingredient : loggedUser.getFridgeIngredients()) {
		  System.out.println(ingredient.toString());
		
	}
	  kieSession.insert(loggedUser);
	  
	  Recipe bestRecipe = null; kieSession.setGlobal("bestRecipe", bestRecipe);
	  kieSession.getAgenda().getAgendaGroup("recommendation").setFocus();
	  kieSession.fireAllRules(); 
	  kieSession.dispose();
	  bestRecipe = (Recipe) kieSession.getGlobal("bestRecipe");
	  System.out.println(bestRecipe.toString());
	  return bestRecipe;
	  
	  
	  }
	 
	
	
	
	

	

}
