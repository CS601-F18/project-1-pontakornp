package cs601.project1;

import java.util.ArrayList;

public class AmazonSearch {

	public static void main(String[] args) {
		//check arguments
		
		String fileName = "test.json";
		AmazonFileHandling file = new AmazonFileHandling();
		ArrayList<Review> reviewList = file.readFile(fileName);
		
		
	}

}