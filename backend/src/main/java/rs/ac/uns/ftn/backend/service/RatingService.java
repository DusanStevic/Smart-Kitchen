package rs.ac.uns.ftn.backend.service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.backend.model.Rating;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.repository.RatingRepository;
import rs.ac.uns.ftn.backend.repository.RecipeRepository;

@Service
public class RatingService {
	private static Logger log = LoggerFactory.getLogger(SampleAppService.class);

	private final KieContainer kieContainer;

	
	@Autowired 
	public RatingService(KieContainer kieContainer) {
	  log.info("Initialising a new example session."); 
	  this.kieContainer = kieContainer; 
	}
	
	@Autowired
    private RatingRepository ratingRepository;
	
	@Autowired
    private RecipeRepository recipeRepository;
	
	public Rating addRating() {
		Rating rating = new Rating();
		ratingRepository.save(rating);
		return rating;
	}
	
	  public List<Recipe> ratings() { 
		  KieSession kieSession = kieContainer.newKieSession();
		  List<Recipe> recipes = recipeRepository.findAll(); 
		  DecimalFormat decimalFormat = new DecimalFormat("#.##"); 
		  decimalFormat.setRoundingMode(RoundingMode.CEILING);
		  for (Recipe recipe : recipes) {
			  kieSession.insert(recipe); 
		  }
	  
		  kieSession.setGlobal("rounding", decimalFormat); 
		  kieSession.fireAllRules();
		  kieSession.dispose(); 
		  return recipes;
	  
	  
	  }

}
