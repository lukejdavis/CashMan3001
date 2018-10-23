package au.com.suncorp.cashman3001.common;

/**
 * Withdrawl Object for the Rest Interface
 * 
 * Bundle contains notes recieved from the withdrawal
 * Content contains error message on error.
 * 
 * @author Luke
 *
 */
public class Withdrawal {
	
	private MoneyBundle bundle;
	private String content;
	
	public Withdrawal(String message) {
		content=message;
		bundle = new MoneyBundle();
	}

	public Withdrawal(MoneyBundle bundle, String message) {
		this.bundle = bundle;
		this.content = message;
	}
	
	public MoneyBundle getBundle() {
		return bundle;
	}

	public void setBundle(MoneyBundle bundle) {
		this.bundle = bundle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
