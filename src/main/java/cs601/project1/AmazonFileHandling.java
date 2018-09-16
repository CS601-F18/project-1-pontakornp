package cs601.project1;

import java.io.BufferedReader;
import java.io.IOException;
//import java.io.BufferedReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class AmazonFileHandling {

	/**
	 * Reads from a file in the project directory given the file name, and return void
	 * 
	 * @param fileName - expects the name of the input file
	 * @param customerEngagmentType - expects either "review" or "qa"
	 */
	public InvertedIndex readFile(String fileName, String customerEngagementType) {
		
		if(customerEngagementType != "review" && customerEngagementType != "qa") {
			return null;
		}
		
		Charset cs = Charset.forName("ISO-8859-1");
		Path path = Paths.get(fileName);
		
		InvertedIndex index = new InvertedIndex();
		Review review;
		QA qa;
		// initializes necessary variables: gson, line, and review to use while looping the json file 
		Gson gson = new Gson();
		String line = "";
		try(
			BufferedReader reader = Files.newBufferedReader(path, cs);
				){
			while((line = reader.readLine()) != null) {
				try {
					if(customerEngagementType == "review") {
						review = gson.fromJson(line, Review.class); // parse json to Review object
						index.putIndex(review, customerEngagementType);
					} else {
						qa = gson.fromJson(line, QA.class); // parse json to QA object
						index.putIndex(qa, customerEngagementType);
					}
				} catch(JsonSyntaxException jse) {
					// skip
				}
			}
			index.sortTermMap();
			return index;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return index;
	}
	
	public void execute(String line, InvertedIndex reviewIndex, InvertedIndex qaIndex) {
		if(line == "") {
			System.out.println("Please try again with the correct format");
			return;
		} else if(line.equals("help")) {
			System.out.println("Command List:\n"
					+ "\tfind <asin> - print all review and qa lists e.g. find 1234567890\n"
					+ "\treviewsearch <term> - print all review lists that contain term e.g. reviewsearch hello\n"
					+ "\tqasearch <term> - print all review lists that contain term e.g. qasearch hello\n"
					+ "\tpartialreviewsearch <term> - print all review lists that contain partially matched term e.g. partialreviewsearch hello\n"
					+ "\tpartialqasearch <term> - print all review lists that contain partially matched term e.g. partialqasearch hello\n"
			);
			return;
		} else if(line.equals("exit")) {
			System.out.println("Exit program");
		}
		
		String[] parts = line.split(" ");
		if(parts.length != 2) {
			System.out.println("Please try again with the correct format");
			return;
		}
		String command = parts[0].toLowerCase();
		String text = parts[1].toLowerCase();
		if(command.equals("find")) {
			if(!reviewIndex.find(text) && !qaIndex.find(text)) {
				System.out.println("ASIN not found. Please try to find other ASIN.");
			}
		} else if(command.equals("reviewsearch")) {
			if(!reviewIndex.search(text)) {
				System.out.println("Search term is not found. Please try other search term.");
			}
		} else if(command.equals("qasearch")) {
			if(!qaIndex.search(text)) {
				System.out.println("Search term is not found. Please try other search term.");
			}
		} else if(command.equals("reviewpartialsearch")) {
			
		} else if(command.equals("qapartialsearch")) {
			
		} else {
			System.out.println("Please try again with the correct format");
		}
	}
	
}
