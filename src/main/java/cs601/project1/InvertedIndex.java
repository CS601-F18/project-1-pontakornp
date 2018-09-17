package cs601.project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * This class is an inverted index data structure used for 
 * searching ASIN and term from Amazon review and/or qa documents.
 * 
 * Contains two variables, asinMap and termMap, which are maps used for storing search keywords.
 * 
 * asinMap has asin as the key and list of customer engagement as the value.
 * termMap has term as the key and list of customer engagement frequency as the value.
 * 
 * Contains main methods as follow: 
 * - getters and setters for the class,
 * - find asin from asin map, 
 * - perform exact search and partial search for term from term map, 
 * - sort term map by frequency for exact search method,
 * - and put relevant objects into map.
 * 
 * Contains helper methods:
 * - clean text
 * - print text
 * 
 */
public class InvertedIndex {
	
	private HashMap<String, ArrayList<CustomerEngagement>> asinMap; // asin map
	private HashMap<String, ArrayList<CustomerEngagementFrequency>> termMap; // term map
	
	public InvertedIndex() {
		this.asinMap = new HashMap<String, ArrayList<CustomerEngagement>>();
		this.termMap = new HashMap<String, ArrayList<CustomerEngagementFrequency>>();
	}
	
	public InvertedIndex(HashMap<String, ArrayList<CustomerEngagement>> asinMap, HashMap<String, ArrayList<CustomerEngagementFrequency>> termMap) {
		this.asinMap = asinMap;
		this.termMap = termMap;
	}

	public HashMap<String, ArrayList<CustomerEngagement>> getASINMap() {
		return this.asinMap;
	}
	
	public void setASINMap(HashMap<String, ArrayList<CustomerEngagement>> asinMap) {
		this.asinMap = asinMap;
	}
	
	public HashMap<String, ArrayList<CustomerEngagementFrequency>> getTermMap() {
		return this.termMap;
	}

	/**
	 * 
	 * Searches and prints all review/ qa list matches to given asin.
	 * 
	 * @param asin - expects asin of a specific product
	 * @return true or false
	 */
	public boolean find(String asin) {
		if(!asinMap.containsKey(asin)) {
			return false;
		}
		ArrayList<CustomerEngagement> asinList = asinMap.get(asin);
		for(CustomerEngagement ce: asinList) {
			System.out.println(ce.toString());
		}
		return true;
	}
	
	/**
	 * 
	 * Searches and prints all review/ qa list exactly matches to given search term.
	 * 
	 * @param term - expects term for exact search
	 * @return true or false
	 */
	public boolean search(String term) {
		if(!termMap.containsKey(term)) {
			return false;
		}
		printText(term);
		return true;
	}
	
	/**
	 * 
	 * Searches and prints all review/ qa list that partially matches with given search term.
	 * 
	 * @param term - expects term for partial search
	 * @return true or false
	 */
	public boolean partialSearch(String term) {
		boolean isExist = false;
		for(Map.Entry<String, ArrayList<CustomerEngagementFrequency>> entry: termMap.entrySet()) {
			String key = entry.getKey();
			if(key.indexOf(term) != -1) {
				printText(key);
				isExist = true;
			}
		}
		return isExist;
	}
	
	
	/**
	 * 
	 * Sorts objects in the list of term map by frequency.
	 */
	public void sortTermMap() {
		for(ArrayList<CustomerEngagementFrequency> cef: termMap.values()) {
			Collections.sort(cef);
		}
	}
	
	/**
	 * 
	 * Puts terms and list of customer engagement frequency object into map.
	 * 
	 * @param ce - expects customer engagement object
	 * @param customerEngagementType - expects either of the 2 types of customer engagement, review or qa.
	 */
	public void putIndex(CustomerEngagement ce, String customerEngagementType){
		String[] terms;
		if(customerEngagementType == "review") {
			terms = cleanReviewText((Review)ce);
		} else {
			terms = cleanQAText((QA)ce);
		}
		String asin = cleanASIN(ce.getASIN());
		ArrayList<CustomerEngagement> asinList;

		HashMap<String, Integer> uniqueTermMap = new HashMap<String, Integer>(); // created map to keep unique term
		int index;
		for(String term: terms) {
			ArrayList<CustomerEngagementFrequency> ceFreqList;
			if(termMap.containsKey(term)) {
				ceFreqList = termMap.get(term); // get list that contains cef object matching with the term
				if(uniqueTermMap.containsKey(term)) { // if cef object exists in the cef list
					index = ceFreqList.size() - 1; // get index of the review object of cef list
					ceFreqList.get(index).incrementFreq(); // get cef object and increment the frequency
				} else { //if the cef object exists in the cef list
					uniqueTermMap.put(term, 1);
					ceFreqList.add(new CustomerEngagementFrequency(ce, 1)); 
				}
			} else {
				ceFreqList = new ArrayList<CustomerEngagementFrequency>();
				uniqueTermMap.put(term, 1);
				ceFreqList.add(new CustomerEngagementFrequency(ce, 1));
				termMap.put(term, ceFreqList);
			}
		}
		
		// asinMap
		if(asinMap.containsKey(asin)) {
			asinList = asinMap.get(asin);
		} else {
			asinList = new ArrayList<CustomerEngagement>();
		}
		asinList.add(ce);
		asinMap.put(asin, asinList);
	}
	
	/**
	 * 
	 * Cleans review text
	 * 
	 * @param review
	 * @return arrays of terms
	 */
	private String[] cleanReviewText(Review review) {
		String reviewText = review.getReviewText();
		return cleanText(reviewText);
	}
	
	/**
	 * 
	 * Cleans qa text
	 * 
	 * @param qa
	 * @return array of terms
	 */
	private String[] cleanQAText(QA qa) {
		String question = qa.getQueston();
		String answer = qa.getAnswer();
		return cleanText(question + answer);
	}
	
	/**
	 * 
	 * Helper method for clean review and qa text methods.
	 * 
	 * Cleans text by
	 * separate words by white space, 
	 * remove all non-alphanumeric characters, 
	 * and convert the string to lower case
	 * 
	 * @param text
	 * @return arrays of terms after cleaning
	 */
	private String[] cleanText(String text) {
		text = text.replaceAll("[^A-Za-z0-9]", " "); // remove all non-alphanumeric characters
		text = text.toLowerCase(); // convert to lower case
		String[] terms = text.split(" +"); // separate words by white space
		return terms;
	}
	
	/**
	 * 
	 * Cleans asin by convert it to lower case for case insensitive purpose.
	 * 
	 * @param asin - expects asin of a product
	 * @return asin after converted to lower case
	 */
	private String cleanASIN(String asin) {
		return asin.toLowerCase();
	}
	
	private void printText(String term) {
		for(int i = 0; i < termMap.get(term).size(); i++) {
			System.out.println(termMap.get(term).get(i));
		}
	}
}
