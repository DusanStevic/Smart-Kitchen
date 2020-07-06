package rs.ac.uns.ftn.backend.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@DiscriminatorValue("user")
public class RegisteredUser extends User {
	private static final long serialVersionUID = 1L;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<FridgeItem> fridgeItems = new HashSet<FridgeItem>();
	
	

	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<PreparedRecipe> preparedRecipe = new HashSet<PreparedRecipe>();
	
	public List<Ingredient> getFridgeIngredients(){
		List<Ingredient> fridgeIngredients = new ArrayList<>();
		for (FridgeItem fridgeItem : fridgeItems) {
			fridgeIngredients.add(fridgeItem.getIngredient());
		}
		return fridgeIngredients;
	}

	public Set<FridgeItem> getFridgeItems() {
		return fridgeItems;
	}

	public void setFridgeItems(Set<FridgeItem> fridgeItems) {
		this.fridgeItems = fridgeItems;
	}

	public Set<PreparedRecipe> getPreparedRecipe() {
		return preparedRecipe;
	}

	public void setPreparedRecipe(Set<PreparedRecipe> preparedRecipe) {
		this.preparedRecipe = preparedRecipe;
	}
	public RegisteredUser() {
		// TODO Auto-generated constructor stub
	}

	public RegisteredUser(Set<FridgeItem> fridgeItems, Set<PreparedRecipe> preparedRecipe) {
		super();
		this.fridgeItems = fridgeItems;
		this.preparedRecipe = preparedRecipe;
	}
	
	
	
	
	

}
