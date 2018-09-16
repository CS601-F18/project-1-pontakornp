package cs601.project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
	
	public HashMap<String, ArrayList<CustomerEngagementFrequency>> getTermMap() {
		return this.termMap;
	}

	// print all review list and qa list that asin matches
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
	
	// search for input term from review or qa list and print text
	public boolean search(String term) {
		if(!termMap.containsKey(term)) {
			return false;
		}
		for(int i = 0; i < termMap.get(term).size(); i++) {
			System.out.println("Freq: " + termMap.get(term).get(i).getFreq());
			System.out.println(termMap.get(term).get(i));
		}
		return true;
	}

	// do a partial search for input term from review or qa list and print text
	public void partialSearch(String term) {
		System.out.println("");
	}

	// sort term map
	public void sortTermMap() {
		for(ArrayList<CustomerEngagementFrequency> cef: termMap.values()) {
			Collections.sort(cef);
		}
	}
	
	/**
	 * put terms and array list of customer engagement into map
	 * 
	 * @param ce
	 * @param customerEngagementType
	 */
	public void putIndex(CustomerEngagement ce, String customerEngagementType){
//		System.out.println(ce.toString());
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
	
	public String cleanASIN(String asin) {
		return asin.toLowerCase();
	}
}
