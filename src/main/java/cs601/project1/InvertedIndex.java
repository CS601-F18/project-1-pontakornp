package cs601.project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class InvertedIndex {
	
	private HashMap<String, ArrayList<CustomerEngagement>> termMap; //  term map of term and list of customer engagements (review or qa);
	private HashMap<String, ArrayList<CustomerEngagement>> asinMap; // asin map
	
	public InvertedIndex() {
		this.termMap = new HashMap<String, ArrayList<CustomerEngagement>>();
		this.asinMap = new HashMap<String, ArrayList<CustomerEngagement>>();
	}
	public InvertedIndex(HashMap<String, ArrayList<CustomerEngagement>> map, HashMap<String, ArrayList<CustomerEngagement>> asinMap) {
		this.termMap = map;
		this.asinMap = asinMap;
	}
	
	public HashMap<String, ArrayList<CustomerEngagement>> getMap() {
		return this.termMap;
	}
	
	public HashMap<String, ArrayList<CustomerEngagement>> getASINMap() {
		return this.asinMap;
	}
	
	// print all review list and qa list that asin matches
	public void find(String asin) {
		ArrayList<CustomerEngagement> asinList;
		if(asinMap.containsKey(asin)) {
			asinList = asinMap.get(asin);
			for(CustomerEngagement ce: asinList) {
				System.out.println(ce.toString());
			}
		}
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
//	public void putReviewIndex(Review review){
//		String[] terms = cleanReviewText(review);
//		ArrayList<CustomerEngagement> list;
//		for(String term: terms) {
//			if(termMap.containsKey(term)) {
//				list = termMap.get(term);
//			} else {
//				list = new ArrayList<CustomerEngagement>();
//			}
//			list.add(review);
//			termMap.put(term, list);
//		}
//	}
	
	public void putReviewIndex(Review review){
		
		String[] terms = cleanReviewText(review);
		String asin = review.getASIN();
		ArrayList<CustomerEngagement> termList;
		ArrayList<CustomerEngagement> asinList;
		
		for(String term: terms) {
			if(termMap.containsKey(term)) {
				termList = termMap.get(term);
			} else {
				termList = new ArrayList<CustomerEngagement>();
			}
			termList.add(review);
			termMap.put(term, termList);
		}
		
		if(asinMap.containsKey(asin)) {
			asinList = asinMap.get(asin);
		} else {
			asinList = new ArrayList<CustomerEngagement>();
		}
		asinList.add(review);
		asinMap.put(asin, asinList);
	}
	
	/**
	 * put terms and array list of qa into map
	 * 
	 * @param qa - QA object
	 */
	public void putQAIndex(QA qa){
		
		String[] terms = cleanQAText(qa);
		String asin = qa.getASIN();
		ArrayList<CustomerEngagement> termList;
		ArrayList<CustomerEngagement> asinList;
		
		for(String term: terms) {
			if(termMap.containsKey(term)) {
				termList = termMap.get(term);
				
			} else {
				termList = new ArrayList<CustomerEngagement>();
			}
			termList.add(qa);
			termMap.put(term, termList);
		}
		
		if(asinMap.containsKey(asin)) {
			asinList = asinMap.get(asin);
		} else {
			asinList = new ArrayList<CustomerEngagement>();
		}
		asinList.add(qa);
		asinMap.put(asin, asinList);
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
		text = text.replaceAll("[^A-Za-z0-9 ]", ""); // remove all non-alphanumeric characters
		text = text.toLowerCase(); // convert to lower case
		String[] terms = text.split(" "); // separate words by white space
		return terms;
	}
	
}
