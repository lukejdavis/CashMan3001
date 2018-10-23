package au.com.suncorp.cashman3001.common;

/**
 * Deposit Object for the Rest Interface
 * 
 * Bundle contains notes deposited
 * Content contains either success or error message.
 * 
 * @author Luke
 *
 */
public class Deposit {

	private MoneyBundle bundle;
	private String content;
	
	public Deposit(MoneyBundle bundle, String message) {
		this.bundle = bundle;
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
