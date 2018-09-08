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
	 */
	public ArrayList<Review> readFile(String fileName) {
		Charset cs = Charset.forName("ISO-8859-1");
		Path path = Paths.get(fileName);
		
		// create Gson object to keep Json object
		Gson gson = new Gson();
		ArrayList<Review> reviewList = new ArrayList<Review>();
		try(
			BufferedReader reader = Files.newBufferedReader(path, cs);
				){
			String line = "";
			Review review = new Review();
			while((line = reader.readLine()) != null) {
				//parse json into Review object
				review = gson.fromJson(line, Review.class);
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
	
}
