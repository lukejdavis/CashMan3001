package au.com.suncorp.cashman3001.persistence;

/**
 * Basic Persistence exception, for when an error occurs trying to modify the ATM values.
 * @author Luke
 *
 */
public class PersistenceException extends RuntimeException{
	
	public PersistenceException() {
		super();
	}
	
	public PersistenceException(String message) {
		super(message);
	}

	public PersistenceException(Throwable e) {
		super(e);
	}
}
