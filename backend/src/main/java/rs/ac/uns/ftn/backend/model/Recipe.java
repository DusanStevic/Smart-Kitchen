package rs.ac.uns.ftn.backend.model;

import java.util.HashSet;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

import rs.ac.uns.ftn.backend.model.enumeration.RecipeType;

@Entity
@Table(name="recipes")
public class Recipe {
	
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable = false)
    private String name;
    
    @Column(name="description")
    private String description;
    
    @Column(name="recipe_type", nullable = false)
    private RecipeType recipeType;
    
	@Column(name = "active")
	private boolean active;
	
	@Column(name = "grade")
	private double grade;


	
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "recipe")
    @JsonManagedReference("sastojci")
    private Set<Ingredient> ingredients = new HashSet<>();


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


	public RecipeType getRecipeType() {
		return recipeType;
	}


	public void setRecipeType(RecipeType recipeType) {
		this.recipeType = recipeType;
	}


	public Set<Ingredient> getIngredients() {
		return ingredients;
	}


	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
    
	public Recipe() {
		
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public double getGrade() {
		return grade;
	}


	public void setGrade(double grade) {
		this.grade = grade;
	}
	


    


	

}
