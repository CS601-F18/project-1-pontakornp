package cs601.project1;

public abstract class CustomerEngagement {
	
	protected String asin;
	
	public CustomerEngagement() {
		this.asin = "";
	}
	
	public CustomerEngagement(String asin) {
		this.asin = asin;
	}
	
	public String getASIN() {
		return this.asin;
	}
	
	public void setASIN(String asin) {
		this.asin = asin;
	}
	
	public String toString() {
		return "asin: " + asin;
	}
	
}
