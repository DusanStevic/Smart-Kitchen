package rs.ac.uns.ftn.backend.templates;

public class CaloriesTemplate {
	private double minCal;
	private double maxCa;
	public double getMinCal() {
		return minCal;
	}
	public void setMinCal(double minCal) {
		this.minCal = minCal;
	}
	public double getMaxCa() {
		return maxCa;
	}
	public void setMaxCa(double maxCa) {
		this.maxCa = maxCa;
	}
	public CaloriesTemplate(double minCal, double maxCa) {
		super();
		this.minCal = minCal;
		this.maxCa = maxCa;
	}
	
	

}
