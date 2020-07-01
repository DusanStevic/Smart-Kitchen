package rs.ac.uns.ftn.backend.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PreDestroy;

import org.kie.api.KieBase;
import org.kie.api.KieBaseConfiguration;
import org.kie.api.KieServices;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	private KieSession kieSession;
	

	public DroolsService(KieContainer kieContainer) {
		log.info("Initialising a new example session.");
		this.kieContainer = kieContainer;
	}
	
    @PreDestroy
    private void release(){
        this.kieSession.dispose();
    }

    public KieContainer getKieContainer() {
        return kieContainer;
    }

    public KieSession getKieSession() {
        if(kieSession == null){
        	KieServices kieServices = KieServices.Factory.get();
    		KieBaseConfiguration kieBaseConfiguration = kieServices.newKieBaseConfiguration();
    		kieBaseConfiguration.setOption(EventProcessingOption.STREAM);
    		KieBase kieBase = getKieContainer().newKieBase(kieBaseConfiguration);
    		kieSession = kieBase.newKieSession();
        }
        return kieSession;
    }

    public void setKieSession(KieSession kieSession) {
        this.kieSession = kieSession;
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
		//KieSession kieSession = kieContainer.newKieSession();
		//KieSession kieSession = kieContainer.newKieSession();
		KieSession kieSession = getKieSession();
		List<Recipe> recipes = recipeRepository.findAll();
		for (Recipe recipe : recipes) {
			
			kieSession.insert(recipe);
		}
		kieSession.fireAllRules();
		kieSession.dispose();
		return recipes;
  
       
	}
	
/*	
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
	  
*/
	  

	 
	
	

	 

}
