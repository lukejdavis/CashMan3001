package au.com.suncorp.cashman3001.service;

/**
 * Basic Service exception, for when an error occurs trying to execute a service.
 * @author Luke
 *
 */
public class ServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}
	
	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable e) {
		super(e);
	}
}
