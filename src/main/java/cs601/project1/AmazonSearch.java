package cs601.project1;

import java.util.Scanner;

public class AmazonSearch {

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
			while(!text.equals("exit")) {
				System.out.println("Type \"help\" to list out all commands.");
				System.out.println("Type \"exit\" to exit the program.");
				System.out.print("Please type your command: ");
				text = scanner.nextLine();
				System.out.println("Search Results: ");
				fileHandling.execute(text, reviewIndex, qaIndex);
				System.out.println("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}