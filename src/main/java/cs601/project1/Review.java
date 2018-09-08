package cs601.project1;

public class Review {
	
	private String asin;
	private String reviewText;

	public Review() {
		asin = "";
		reviewText = "";
	}
	
	public Review(String asin, String reviewText) {
		this.asin = asin;
		this.reviewText = reviewText;
	}
	
	public String getASIN() {
		return asin;
	}
	public void setASIN(String value) {
		asin = value;
	}
	
	public String getReviewText() {
		return reviewText;
	}
	
	public void setReviewText(String value) {
		reviewText = value;
	}
	
	public String toString() {
		return "asin: "+ asin + ", reviewText: " + reviewText;
	}
	
	
}
