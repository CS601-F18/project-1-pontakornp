package cs601.project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class InvertedIndex {
	
//	private HashMap<String, ArrayList<CustomerEngagement>> termMap; // term map of term and list of customer engagements (review or qa);
//	private HashMap<String, HashMap<CustomerEngagement, Integer>> termFreqMap; //
	
	private HashMap<String, ArrayList<CustomerEngagement>> asinMap; // asin map
	

	private HashMap<String, ArrayList<Integer>> termMap; 
	private ArrayList<CustomerEngagement> ceList;
//	private HashMap<Integer, ArrayList<String>> ceIndexMap;
//	private HashMap<String, ArrayList<CustomerEngagement>> termMap;

	public InvertedIndex() {
//		this.termMap = new HashMap<String, ArrayList<CustomerEngagement>>();
		this.asinMap = new HashMap<String, ArrayList<CustomerEngagement>>();
		
		
		this.termMap = new HashMap<String, ArrayList<Integer>>();
		this.ceList = new ArrayList<CustomerEngagement>();
//		this.ceIndexMap = new HashMap<Integer, ArrayList<String>>();
//		this.termMap = new HashMap<String, ArrayList<CustomerEngagement>>();
	}
	
//	public InvertedIndex(HashMap<String, ArrayList<CustomerEngagement>> map, HashMap<String, ArrayList<CustomerEngagement>> asinMap) {
////		this.termMap = map;
//		this.asinMap = asinMap;
//	}
	
//	public HashMap<String, ArrayList<CustomerEngagement>> getMap() {
//		return this.termMap;
//	}
	
	public HashMap<String, ArrayList<Integer>> getMap() {
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
		if(termMap.containsKey(term)) {
			for(int i = 0; i < termMap.get(term).size(); i++) {
				
//				termMap.get(term).get(i);
				System.out.println(termMap.get(term).get(i));
			}
		}
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
	
	// sort term map
	public void sortTermMap() {
		
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
//		ArrayList<CustomerEngagement> termList;
		ArrayList<CustomerEngagement> asinList;
		
		ceList.add(review); // add Review object to CustomerEngagement list
		int ceListIndex = ceList.size()-1;
		ArrayList<Integer> reviewIndexList = new ArrayList<Integer>();
		//test new
		for(String term: terms) {
			review.incrementTermFreq(term); // increment frequency of each term in Review object
			if(termMap.containsKey(term)) {
				reviewIndexList = termMap.get(term);
				if(reviewIndexList.get(reviewIndexList.size() - 1) != ceListIndex) {
					reviewIndexList.add(ceListIndex);
				}
			} else {
				reviewIndexList = new ArrayList<Integer>();
				reviewIndexList.add(ceListIndex);
				
			}
			termMap.put(term, reviewIndexList);
		}
		
		//test new InvertedIndex update
//		ceIndexMap.put(ceListIndex, termList);
		
		// test asinMap
//		if(asinMap.containsKey(asin)) {
//			asinList = asinMap.get(asin);
//			if(asinList.get(asinList.size()-1) == review) {
//				
//			}
//		} else {
//			asinList = new ArrayList<CustomerEngagement>();
//		}
		
		// asinMap
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
		
//		for(String term: terms) {
//			if(termMap.containsKey(term)) {
//				termList = termMap.get(term);
//			} else {
//				termList = new ArrayList<CustomerEngagement>();
//			}
//			termList.add(qa);
//			termMap.put(term, termList);
//		}
		
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
