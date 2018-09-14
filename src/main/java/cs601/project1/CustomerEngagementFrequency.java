package cs601.project1;

public class CustomerEngagementFrequency {
	
	private CustomerEngagement ce;
	private Integer freq;
	
	public CustomerEngagementFrequency(CustomerEngagement ce, Integer freq) {
		this.ce = ce;
		this.freq= freq;
	}
	public void setCustomerEngagement(CustomerEngagement ce) {
		this.ce = ce;
	}
	public CustomerEngagement getCE() {
		return this.ce;
	}
	
	public void setFreq(Integer freq) {
		this.freq = freq;
	}

	public Integer getFreq() {
		return this.freq;
	}
}
