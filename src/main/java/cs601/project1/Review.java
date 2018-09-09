package cs601.project1;

public class Review extends CustomerEngagement{

	private String reviewText;

	public Review() {
		super("");
		reviewText = "";
	}
	
	public Review(String asin, String reviewText) {
		super(asin);
		this.reviewText = reviewText;
	}
	
	public String getReviewText() {
		return this.reviewText;
	}
	
	public void setReviewText(String value) {
		this.reviewText = value;
	}
	
	public String toString() {
		return "asin: "+ asin + ", reviewText: " + reviewText;
	}
	
	
}
