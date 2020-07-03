package rs.ac.uns.ftn.backend.templates;

import rs.ac.uns.ftn.backend.model.enumeration.RecipeDifficultyLevels;

public class RecipeDifficultyTemplateModel {
	private int lowerBound;
    private int upperBound;
    private RecipeDifficultyLevels newDifficulty;
    
    public RecipeDifficultyTemplateModel() {
		// TODO Auto-generated constructor stub
	}
    
    public RecipeDifficultyTemplateModel(int lowerBound, int upperBound, String newDifficulty) {
		super();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.newDifficulty = RecipeDifficultyLevels.valueOf(newDifficulty);  
	}

	public RecipeDifficultyTemplateModel(int lowerBound, int upperBound, RecipeDifficultyLevels newDifficulty) {
		super();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.newDifficulty = newDifficulty;
	}

	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	public int getUpperBound() {
		return upperBound;
	}

	public void setUpperBound(int upperBound) {
		this.upperBound = upperBound;
	}

	public RecipeDifficultyLevels getNewDifficulty() {
		return newDifficulty;
	}

	public void setNewDifficulty(RecipeDifficultyLevels newDifficulty) {
		this.newDifficulty = newDifficulty;
	}
    
    

    
    


}
