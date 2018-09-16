package cs601.project1;

public class QA extends CustomerEngagement {

	private String question;
	private String answer;
	
	public QA() {
		super("");
		question = "";
		answer = "";
	}
	
	public QA(String asin, String question, String answer) {
		super(asin);
		this.question = question;
		this.answer = answer;
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
//		return "asin: " + asin + ", question: " + question + ", answer: " + answer;
		return "question: " + question + "\nanswer: " + answer;
	}
}
