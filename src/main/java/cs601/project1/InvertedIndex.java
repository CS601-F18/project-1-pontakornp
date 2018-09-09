package cs601.project1;

import java.util.ArrayList;
import java.util.HashMap;

public class InvertedIndex {
	
	// map of term and list of customer engagements (review or qa);
	private HashMap<String, ArrayList<CustomerEngagement>> map;
	
	public InvertedIndex() {
		this.map = new HashMap<String, ArrayList<CustomerEngagement>>();
	}
	public InvertedIndex(HashMap<String, ArrayList<CustomerEngagement>> map) {
		this.map = map;
	}
	
	public HashMap<String, ArrayList<CustomerEngagement>> getMap() {
		return this.map;
	}
	
	// print all review list and qa list that asin matches
	public void find(String asin) {
		System.out.println("");
	}
	
	// search for input term from review list and print out
	public void reviewSearch(String term) {
		System.out.println("");
	}
	
	// search for input term from question and answer lists and print out
	public void qaSearch(String term) {
		System.out.println("");
	}
	
	// do a partial search for input term from review list and print out
	public void reviewPartialSearch(String term) {
		System.out.println("");
	}
	
	// do a partial search for input term from qa list and print out
	public void qaPartialSearch(String term) {
		System.out.println("");
	}
	
	// sort map
	public void sortMap() {
		
	}
	
	/**
	 * put terms and array list of reviews into map
	 * 
	 * @param review - Review object
	 */
	public void putReviewIndex(Review review){
		String[] terms = cleanReviewText(review);
		ArrayList<CustomerEngagement> list;
		for(String term: terms) {
			if(map.containsKey(term)) {
				list = map.get(term);
			} else {
				list = new ArrayList<CustomerEngagement>();
			}
			list.add(review);
			map.put(term, list);
		}
	}
	
	/**
	 * put terms and array list of qa into map
	 * 
	 * @param qa - QA object
	 */
	public void putQAIndex(QA qa){
		String[] terms = cleanQAText(qa);
		ArrayList<CustomerEngagement> list;
		for(String term: terms) {
			if(map.containsKey(term)) {
				list = map.get(term);
			} else {
				list = new ArrayList<CustomerEngagement>();
			}
			list.add(qa);
			map.put(term, list);
		}
	}
	
	/**
	 * clean review text (separate words by white space, remove all non-alphanumeric characters, and convert the string to lower case)
	 * 
	 * @param review
	 * @return arrays of terms
	 */
	public String[] cleanReviewText(Review review) {
		String reviewText = review.getReviewText();
		return cleanText(reviewText);
	}
	
	/**
	 * clean review text (separate words by white space, remove all non-alphanumeric characters, and convert the string to lower case)
	 * 
	 * @param qa
	 * @return array of terms
	 */
	public String[] cleanQAText(QA qa) {
		String question = qa.getQueston();
		String answer = qa.getAnswer();
		return cleanText(question + answer);
	}
	
	/**
	 * helper method for cleanReviewText and cleanQAText
	 * @param text
	 * @return arrays of terms after cleaning
	 */
	public String[] cleanText(String text) {
		text = text.replace("[^A-Za-z0-9 ]", ""); // remove all non-alphanumeric characters
		text = text.toLowerCase(); // convert to lower case
		String[] terms = text.split(" "); // separate words by white space
		return terms;
	}
	
	
	// clean qa text (separate words by white space, remove all non-alphanumeric characters, and convert the string to lower case)
	
	
	
}
