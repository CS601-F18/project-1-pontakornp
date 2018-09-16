package cs601.project1;

import java.util.HashMap;
import java.util.Objects;

public class Review extends CustomerEngagement{

	private String reviewText;
	
	private HashMap<String, Integer> reviewFreq;

	public Review() {
		super("");
		reviewText = "";
		reviewFreq = new HashMap<String, Integer>();
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
	
	public void setReview(String term, Integer freq) {
		this.reviewFreq.put(term, freq);
	}
	
	public void incrementTermFreq(String term) {
		if (reviewFreq.containsKey(term)){
			reviewFreq.put(term, reviewFreq.get(term) + 1);
		} else {
			reviewFreq.put(term, 1);
		}
	}
	
	public String toString() {
//		return "asin: "+ asin + ", reviewText: " + reviewText;
		return "reviewText: " + reviewText;
	}
	
	public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Review)) {
            return false;
        }
        Review review = (Review) o;
        return Objects.equals(reviewText, review.reviewText) &&
                Objects.equals(asin, review.asin);
    }

    public int hashCode() {
        return Objects.hash(asin, reviewText);
    }

}
