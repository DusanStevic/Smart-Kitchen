package rs.ac.uns.ftn.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.backend.model.Ingredient;
import rs.ac.uns.ftn.backend.repository.IngredientRepository;

@Service
public class IngredientService {
	
	@Autowired IngredientRepository ingredientRepository;
	
	public List<Ingredient> findAll() {
		return ingredientRepository.findAll();
	}

}
