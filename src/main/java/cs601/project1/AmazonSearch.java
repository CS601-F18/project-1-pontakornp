package cs601.project1;

import java.util.ArrayList;

public class AmazonSearch {

	public static void main(String[] args) {
		//check arguments
		
		String reviewFileName = "testreview.json";
		String qaFileName = "testqa.json";
		AmazonFileHandling file = new AmazonFileHandling();
		ArrayList<CustomerEngagement> reviewlist = file.readFile(reviewFileName, "review");
		ArrayList<CustomerEngagement> qaList = file.readFile(qaFileName, "qa");
		
		
	}

}