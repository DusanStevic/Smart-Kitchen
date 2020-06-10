package rs.ac.uns.ftn.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.backend.model.Recipe;
import rs.ac.uns.ftn.backend.repository.RecipeRepository;

@Service
public class RecipeService {
	@Autowired RecipeRepository recipeRepository;
	
	public List<Recipe> findAll() {
		return recipeRepository.findAll();
	}
	

}
