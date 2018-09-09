package cs601.project1;

import java.io.BufferedReader;
import java.io.IOException;
//import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
		// create gson object to keep json object
		ArrayList<CustomerEngagement> list = new ArrayList<CustomerEngagement>();
		
		String reviews = "";
		String qa = "";
		try(
			BufferedReader reader = Files.newBufferedReader(path, cs);
				){
			// initializes necessary variables: gson, line, and review to use while looping the json file 
			Gson gson = new Gson();
			String line = "";
			while((line = reader.readLine()) != null) {
				
				if(customerEngagementType == "review") {
					ce = gson.fromJson(line, Review.class); // parse json to Review object
					
				} else {
					ce = gson.fromJson(line, QA.class); // parse json to QA object
				}
				
				System.out.println(ce.toString());
				list.add(ce);
				
				r
				
			}
			return list;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return list;
	}
	
}
