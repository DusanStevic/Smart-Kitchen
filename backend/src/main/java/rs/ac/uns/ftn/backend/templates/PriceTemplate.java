package rs.ac.uns.ftn.backend.templates;

public class PriceTemplate {
	private double minPrice;
	private double maxPrice;
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	public double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public PriceTemplate(double minPrice, double maxPrice) {
		super();
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	

	
	

}
