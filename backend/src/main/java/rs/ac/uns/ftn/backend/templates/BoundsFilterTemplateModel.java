package rs.ac.uns.ftn.backend.templates;

public class BoundsFilterTemplateModel {
	private int lowerBound;
    private int upperBound;
    
    public BoundsFilterTemplateModel() {
		// TODO Auto-generated constructor stub
	}

	public BoundsFilterTemplateModel(int lowerBound, int upperBound) {
		super();
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
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
    
    

}
