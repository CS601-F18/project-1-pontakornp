package cs601.project1;

import java.util.*;

public class SortTermFrequency implements Comparator<String>{
	HashMap<String, Integer> freqMap;
	
	public SortTermFrequency(HashMap<String, Integer> freqMap) {
		this.freqMap = freqMap;
	}
	
	public int compare(String a, String b) {
		if(freqMap.get(a) > freqMap.get(b)) {
			return -1;
		} else {
			return 1;
		}
	}
}