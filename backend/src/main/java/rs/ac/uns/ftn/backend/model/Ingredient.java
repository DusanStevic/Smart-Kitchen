package rs.ac.uns.ftn.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import rs.ac.uns.ftn.backend.model.enumeration.IngredientType;
import rs.ac.uns.ftn.backend.model.enumeration.UnitType;

@Entity
@Table(name="ingredients")
public class Ingredient {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
    
    @Column(name = "ingredient_type", nullable = false)
    private IngredientType ingredientType;
    
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "image_url",nullable = true)
	private String imageUrl;
    
    @Column(name = "unit_type", nullable = false)
    private UnitType unitType;
    
    @Column(name = "calories_per_unit", nullable = false)
    private Double caloriesPerUnit;
    
    
	@OneToMany(mappedBy = "ingredient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<RecipeItem> recipeItems = new HashSet<RecipeItem>();

	public Ingredient() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public IngredientType getIngredientType() {
		return ingredientType;
	}

	public void setIngredientType(IngredientType ingredientType) {
		this.ingredientType = ingredientType;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public UnitType getUnitType() {
		return unitType;
	}

	public void setUnitType(UnitType unitType) {
		this.unitType = unitType;
	}

	public Double getCaloriesPerUnit() {
		return caloriesPerUnit;
	}

	public void setCaloriesPerUnit(Double caloriesPerUnit) {
		this.caloriesPerUnit = caloriesPerUnit;
	}

	public Set<RecipeItem> getRecipeItems() {
		return recipeItems;
	}

	public void setRecipeItems(Set<RecipeItem> recipeItems) {
		this.recipeItems = recipeItems;
	}
	
	
	
	
	
	


	










	
    

}
