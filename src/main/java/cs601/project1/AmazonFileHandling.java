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
	
}
