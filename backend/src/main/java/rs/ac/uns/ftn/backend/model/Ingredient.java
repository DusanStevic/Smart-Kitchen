package rs.ac.uns.ftn.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import rs.ac.uns.ftn.backend.model.enumeration.IngredientType;

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
    
    @Column(name = "price", nullable = false)
    private Double price;
    
    @Column(name = "quantity", nullable = false)
    private Double quantity;
    
    @Column(name = "calories", nullable = false)
    private Double calories;
    
    @Column(name = "total_price")
    private Double total_price;
    
    @Column(name = "total_calories")
    private Double total_calories;
    
	
	

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JsonBackReference("sastojci")
	@JoinColumn(name = "recipe_id")
    private Recipe recipe;


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


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Double getQuantity() {
		return quantity;
	}


	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}


	public Double getCalories() {
		return calories;
	}


	public void setCalories(Double calories) {
		this.calories = calories;
	}


	public Double getTotal_price() {
		return total_price;
	}


	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}


	public Double getTotal_calories() {
		return total_calories;
	}


	public void setTotal_calories(Double total_calories) {
		this.total_calories = total_calories;
	}


	public Recipe getRecipe() {
		return recipe;
	}


	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}



	


	
	
	
	



	
	
	
	
    

}
