package rs.ac.uns.ftn.backend.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.backend.exceptions.BadRequestException;
import rs.ac.uns.ftn.backend.exceptions.ResourceNotFoundException;
import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.repository.RecipeRepository;
import rs.ac.uns.ftn.backend.templates.RecipeDifficultyTemplateModel;

@Service
public class RecipeService {
	@Autowired
	private DroolsService droolsService;
	
	@Autowired 
	RecipeRepository recipeRepository;
	
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
	
	

	

}
