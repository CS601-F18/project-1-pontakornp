package cs601.project1;

import java.util.ArrayList;

public class AmazonSearch {

	public static void main(String[] args) {
		//check arguments
		
		String reviewFileName = "testreview.json";
		String qaFileName = "testqa.json";
		AmazonFileHandling file = new AmazonFileHandling();
		ArrayList<Review> reviewList = file.readReviewFile(reviewFileName);
		ArrayList<QA> qaList = file.readQAFile(qaFileName);
		
		
	}

}