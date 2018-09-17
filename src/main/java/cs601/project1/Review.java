package cs601.project1;

/**
 * 
 * This class represents review which is the child of customer engagement class.
 * It contains review text as additional variable of its parent.
 *
 */
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
		return "reviewText: " + reviewText;
	}
}
