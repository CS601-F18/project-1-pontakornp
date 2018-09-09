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
	 * @param fileType - expects either "review" or "qa"
	 */
	public ArrayList<Review> readReviewFile(String fileName) {
		
		Charset cs = Charset.forName("ISO-8859-1");
		Path path = Paths.get(fileName);
		
		// create gson object to keep json object
		ArrayList<Review> reviewList = new ArrayList<Review>();
		try(
			BufferedReader reader = Files.newBufferedReader(path, cs);
				){
			
			// initializes necessary variables: gson, line, and review to use while looping the json file 
			Gson gson = new Gson(); // create gson object to 
			String line = "";
			Review review = new Review();
			while((line = reader.readLine()) != null) {
				//parse json into Review object
				review = gson.fromJson(line, Review.class); // set values to Review object
				System.out.println(review.toString());
				reviewList.add(review);
				
			}
			return reviewList;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return reviewList;
	}
	
	public ArrayList<QA> readQAFile(String fileName) {
		
		Charset cs = Charset.forName("ISO-8859-1");
		Path path = Paths.get(fileName);
		
		// create gson object to keep json object
		ArrayList<QA> qaList = new ArrayList<QA>();
		try(
			BufferedReader reader = Files.newBufferedReader(path, cs);
				){
			
			// initializes necessary variables: gson, line, and review to use while looping the json file 
			Gson gson = new Gson(); // create gson object to 
			String line = "";
			QA qa = new QA();
			while((line = reader.readLine()) != null) {
				//parse json into Review object
				qa = gson.fromJson(line, QA.class); // set values to Review object
				System.out.println(qa.toString());
				qaList.add(qa);
				
			}
			return qaList;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return qaList;
	}
	
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
		
		CustomerEngagement ce;
		
		
		Charset cs = Charset.forName("ISO-8859-1");
		Path path = Paths.get(fileName);
		
		// create gson object to keep json object
		ArrayList<CustomerEngagement> list = new ArrayList<CustomerEngagement>();
		try(
			BufferedReader reader = Files.newBufferedReader(path, cs);
				){
			
			// initializes necessary variables: gson, line, and review to use while looping the json file 
			Gson gson = new Gson(); // create gson object to 
			String line = "";
			while((line = reader.readLine()) != null) {
				
				if(customerEngagementType == "review") {
					ce = gson.fromJson(line, Review.class); // parse json to Review object
				} else {
					ce = gson.fromJson(line, QA.class); // parse json to QA object
				}
				
				System.out.println(ce.toString());
				list.add(ce);
			}
			return list;
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return list;
	}
	
}
