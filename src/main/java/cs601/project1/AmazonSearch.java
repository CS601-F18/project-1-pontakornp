package cs601.project1;

import java.util.Scanner;

public class AmazonSearch {

	public static void main(String[] args) {
		//check arguments
		if(args.length != 4 || !args[0].equals("-reviews") || !args[2].equals("-qa")) {
			System.out.println("Incorrect input format, please try again.");
			return;
		} else if (args[1] == "") {
			System.out.println("Please input the correct name of Review file");
		} else if (args[3] == "") {
			System.out.println("Please input the correct name of Q&A file");
		}
		
		String reviewFileName = args[1];
		String qaFileName = args[3];
		
//		String reviewFileName = "testreview.json";
//		String qaFileName = "testqa.json";
		AmazonFileHandling fileHandling = new AmazonFileHandling();
//		ArrayList<CustomerEngagement> reviewlist = file.readFile(reviewFileName, "review");
//		ArrayList<CustomerEngagement> qaList = file.readFile(qaFileName, "qa");
		InvertedIndex reviewIndex = fileHandling.readFile(reviewFileName, "review");
		InvertedIndex qaIndex = fileHandling.readFile(qaFileName, "qa");
		
//		reviewIndex.find("120401325X");
//		qaIndex.find("1466736038");
		
		
		try(
			Scanner scanner = new Scanner(System.in);
		){
			String text = "";
			while(!text.equals("exit")) {
				text = scanner.nextLine();
				fileHandling.callUserInputFunction(text, reviewIndex, qaIndex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}