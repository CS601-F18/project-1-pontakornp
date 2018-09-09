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
	
	// search for input term from qa list and print out
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
	 * put terms and array list into map
	 * 
	 * @param terms - array of terms
	 * @param ce - customer engagement object
	 */
	public void putTermsAndList(String[] terms, CustomerEngagement ce){
		ArrayList<CustomerEngagement> list;
		for(String term: terms) {
			if(map.containsKey(term)) {
				list = map.get(term);
			} else {
				list = new ArrayList<CustomerEngagement>();
			}
			list.add(ce);
			map.put(term, list);
		}
	}
	
	/**
	 * clean review text (separate words by white space, remove all non-alphanumeric characters, and convert the string to lower case)
	 * 
	 * @param review
	 * @return array of words
	 */
	public String[] cleanText(Review review) {
		String reviewText = review.getReviewText();
		reviewText = reviewText.replaceAll("[^A-Za-z0-9 ]", ""); // remove all non-alphanumeric characters
		reviewText = reviewText.toLowerCase(); // convert to lower case
		String[] terms = reviewText.split(" "); // separate words by white space
		return terms;
	}
	
	
	// clean qa text (separate words by white space, remove all non-alphanumeric characters, and convert the string to lower case)
	
	
	
}
