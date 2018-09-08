package cs601.project1;

public class QA {
	
	private String asin;
	private String question;
	private String answer;
	
	public QA() {
		asin = "";
		question = "";
		answer = "";
	}
	
	public QA(String asin, String question, String answer) {
		this.asin = asin;
		this.question = question;
		this.answer = answer;
	}
	
	public String getASIN() {
		return asin;
	}
	
	public void setASIN(String value) {
		asin = value;
	}
	
	public String getQueston() {
		return question;
	}
	
	public void setQuestion(String value) {
		question = value;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String value) {
		answer = value;
	}
	
	public String toString() {
		return "asin: " + asin + ", question: " + question + ", answer: " + answer;
	}
}
