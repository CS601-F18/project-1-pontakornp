package cs601.project1;

/**
 * 
 * This class represents customer engagement
 * which is the parent class for review and qa classes.
 * 
 * Contains getter and setter method for ASIN, product identifier.
 *
 */
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
