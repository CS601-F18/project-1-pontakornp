package cs601.project1;

import java.util.Scanner;

public class AmazonSearch {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
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
		
//		reviewFileName = "testreview.json";
//		qaFileName = "testqa.json";
//		reviewFileName = "reviews_Cell_Phones_and_Accessories_5.json";
//		qaFileName = "qa_Cell_Phones_and_Accessories.json";
		AmazonFileHandling fileHandling = new AmazonFileHandling();
//		ArrayList<CustomerEngagement> reviewlist = file.readFile(reviewFileName, "review");
//		ArrayList<CustomerEngagement> qaList = file.readFile(qaFileName, "qa");
		InvertedIndex reviewIndex = fileHandling.readFile(reviewFileName, "review");
		InvertedIndex qaIndex = fileHandling.readFile(qaFileName, "qa");
		
//		reviewIndex.find("120401325X");
//		qaIndex.find("1466736038");
		
		long end = System.currentTimeMillis();
		System.out.println("Time: " + Math.round((end-start)/1000) + " seconds" );
		try(
			Scanner scanner = new Scanner(System.in);
		){
			String text = "";
			while(!text.equals("exit")) {
				System.out.println("Type \"help\" to list out all commands");
				System.out.print("Please type your command: ");
				text = scanner.nextLine();
				System.out.println("");
				fileHandling.callUserInputFunction(text, reviewIndex, qaIndex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}