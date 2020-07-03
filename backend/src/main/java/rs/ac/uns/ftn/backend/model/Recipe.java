package rs.ac.uns.ftn.backend.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import rs.ac.uns.ftn.backend.model.enumeration.RecipeDifficultyLevels;
import rs.ac.uns.ftn.backend.model.enumeration.RecipeType;

@Entity
@Table(name="recipes")
public class Recipe implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@Column(name = "image_url",nullable = true)
	private String imageUrl;
	
    @Column(name = "total_calories")
    private Double total_calories;
	

	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<RecipeItem> recipeItems = new HashSet<RecipeItem>();
	
	
	@OneToOne
	@JoinColumn(name = "rating_id")
	private Rating rating;
	
	@OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Direction> directions = new HashSet<Direction>();
	
    @Column(name="difficulty", nullable = true)
    private RecipeDifficultyLevels difficulty;


	public Recipe() {
		// TODO Auto-generated constructor stub
	}


	public Recipe(Long id, String name, String description, RecipeType recipeType, boolean active, String imageUrl,
			Double total_calories, Set<RecipeItem> recipeItems, Rating rating, Set<Direction> directions,
			RecipeDifficultyLevels difficulty) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.recipeType = recipeType;
		this.active = active;
		this.imageUrl = imageUrl;
		this.total_calories = total_calories;
		this.recipeItems = recipeItems;
		this.rating = rating;
		this.directions = directions;
		this.difficulty = difficulty;
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


	public RecipeType getRecipeType() {
		return recipeType;
	}


	public void setRecipeType(RecipeType recipeType) {
		this.recipeType = recipeType;
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


	public Double getTotal_calories() {
		return total_calories;
	}


	public void setTotal_calories(Double total_calories) {
		this.total_calories = total_calories;
	}


	public Set<RecipeItem> getRecipeItems() {
		return recipeItems;
	}


	public void setRecipeItems(Set<RecipeItem> recipeItems) {
		this.recipeItems = recipeItems;
	}


	public Rating getRating() {
		return rating;
	}


	public void setRating(Rating rating) {
		this.rating = rating;
	}


	public Set<Direction> getDirections() {
		return directions;
	}


	public void setDirections(Set<Direction> directions) {
		this.directions = directions;
	}


	public RecipeDifficultyLevels getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(RecipeDifficultyLevels difficulty) {
		this.difficulty = difficulty;
	}


	
	


    


	

}
