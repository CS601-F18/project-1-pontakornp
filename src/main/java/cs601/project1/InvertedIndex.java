package cs601.project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

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
				System.out.println("Freq: " + termMap.get(term).get(i).getFreq());
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
//		private HashMap<String, ArrayList<CustomerEngagementFrequency>> termMap; // for reference
		String[] terms = cleanReviewText(review);
		String asin = review.getASIN();
//		ArrayList<CustomerEngagement> termList;
		ArrayList<CustomerEngagement> asinList;
		
//		ArrayList<CustomerEngagementFrequency> ceFreqList;
//		CustomerEngagementFrequency cef = new CustomerEngagementFrequency(review, 0);
		
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
					ceFreqList.add(new CustomerEngagementFrequency(review, 1)); 
				}
			} else {
				ceFreqList = new ArrayList<CustomerEngagementFrequency>();
				uniqueTermMap.put(term, 1);
				ceFreqList.add(new CustomerEngagementFrequency(review, 1));
				termMap.put(term, ceFreqList);
			}
		}
		
		
		
		
		//comment
//		ceList.add(review); // add Review object to CustomerEngagement list
//		int ceListIndex = ceList.size()-1;
//		ArrayList<Integer> reviewIndexList = new ArrayList<Integer>();
		//test new
//		for(String term: terms) {
//			review.incrementTermFreq(term); // increment frequency of each term in Review object
//			if(termMap.containsKey(term)) {
//				reviewIndexList = termMap.get(term);
//				if(reviewIndexList.get(reviewIndexList.size() - 1) != ceListIndex) {
//					reviewIndexList.add(ceListIndex);
//				}
//			} else {
//				reviewIndexList = new ArrayList<Integer>();
//				reviewIndexList.add(ceListIndex);
//				
//			}
//			termMap.put(term, reviewIndexList);
//		}
		
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
