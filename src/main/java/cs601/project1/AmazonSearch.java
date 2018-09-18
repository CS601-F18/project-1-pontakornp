package cs601.project1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author pontakornp
 * 
 * This class is the main method to run project1 for CS601 Fall 2018 class.
 * The main purpose of this project is for user to search for Amazon customer review and question/answer from json files.
 * Contains InvertedIndex which is the core data structure used to support searching for term and asin (product identifier).
 * 
 */
public class AmazonSearch {

	/**
	 * 
	 * Main logic to search for term or asin to return review/qa details.
	 * 
	 * Expected usage: java -cp project1.jar cs601.project1.AmazonSearch -reviews <review_file_name> -qa <qa_file_name>
	 * 
	 * Command List:
	 * 	find <asin> - print all review and qa lists e.g. find 1234567890"
	 * 	reviewsearch <term> - print all review lists that contain term e.g. reviewsearch hello"
	 * 	qasearch <term> - print all review lists that contain term e.g. qasearch hello"
	 * 	reviewpartialsearch <term> - print all review lists that contain partially matched term e.g. partialreviewsearch he"
	 * 	qapartialsearch <term> - print all review lists that contain partially matched term e.g. partialqasearch he"
	 * 
	 * @param args - expects the command -reviews <review_file_name> -qa <qa_file_name>
	 */
	public static void main(String[] args) {
		AmazonFileHandling fileHandling = new AmazonFileHandling();
		if(!fileHandling.isInputValid(args)) {
			return;
		}
		String reviewFileName = args[1];
		String qaFileName = args[3];
		InvertedIndex reviewIndex = fileHandling.readFile(reviewFileName, "review");
		InvertedIndex qaIndex = fileHandling.readFile(qaFileName, "qa");
		try(
			Scanner scanner = new Scanner(System.in);
		){
			String text = "";
			String defaultPrint = "Type \"help\" to list out all commands.\n"
					+ "Type \"exit\" to exit the program.\n"
					+ "Please type your command: ";
			while(!text.equals("exit")) {
				System.out.print(defaultPrint);
				text = scanner.nextLine();
				System.out.println("");
				fileHandling.execute(text, reviewIndex, qaIndex);
			}
		} catch (InputMismatchException e) {
			System.out.println("Please try again with the appropriate format.");
		}
	}
}