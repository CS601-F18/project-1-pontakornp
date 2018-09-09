package cs601.project1;

import java.io.BufferedReader;
import java.io.IOException;
//import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AmazonFileHandling {

	/**
	 * Reads from a file in the project directory given the file name, and return void
	 * 
	 * @param fileName - expects the name of the input file
	 * @param customerEngagmentType - expects either "review" or "qa"
	 */
	public ArrayList<CustomerEngagement> readFile(String fileName, String customerEngagementType) {
		
		if(customerEngagementType != "review" && customerEngagementType != "qa") {
			return null;
		}
		
		Charset cs = Charset.forName("ISO-8859-1");
		Path path = Paths.get(fileName);
		
		CustomerEngagement ce;
		
		InvertedIndex textFreqMap = new InvertedIndex();
		ArrayList<CustomerEngagement> list = new ArrayList<CustomerEngagement>();
		HashMap<String, ArrayList<CustomerEngagement>> map = textFreqMap.getMap();
		
//		String reviews = "";
//		String qa = "";
		
		Review review;
		QA qa;
		String[] words;
		try(
			BufferedReader reader = Files.newBufferedReader(path, cs);
				){
			// initializes necessary variables: gson, line, and review to use while looping the json file 
			Gson gson = new Gson();
			String line = "";
			while((line = reader.readLine()) != null) {
				
				if(customerEngagementType == "review") {
					review = gson.fromJson(line, Review.class); // parse json to Review object
					ce = review;
					words = textFreqMap.cleanText(review);
					
					for(String w: words) {
						if(map.containsKey(w)) {
							list = map.get(w);
						}
						list.add(ce);
						map.put(w, list);
					}
				} else {
					ce = gson.fromJson(line, QA.class); // parse json to QA object
				}
				
				System.out.println(ce.toString());
				list.add(ce);
				new ArrayList<CustomerEngagement>();
			}
			return list;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return list;
	}
	
}
