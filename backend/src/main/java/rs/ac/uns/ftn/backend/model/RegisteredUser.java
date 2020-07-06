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

}
