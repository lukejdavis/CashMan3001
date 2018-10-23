package au.com.suncorp.cashman3001.common;

/**
 * Simple message object for return communication through the REST interface
 * Content contains the string message.
 *  
 * @author Luke
 *
 */
public class Message {
	
	private String content="";
	
	public Message(String message) {
		this.content = message;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
