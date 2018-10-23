package au.com.suncorp.cashman3001.service;

import org.springframework.stereotype.Service;

import au.com.suncorp.cashman3001.common.MoneyBundle;
import au.com.suncorp.cashman3001.persistence.ATM;
import au.com.suncorp.cashman3001.persistence.PersistenceException;

/**
 * Service class for dealing with ATM modifications
 * Uses a dirty version of the Service pattern without the interfaces
 * @author Luke
 *
 */
@Service
public class ATMService {
	
	/**
	 * Ititializes the ATM
	 * If the ATM has been ititialized throws a runtime exception.
	 * @param bundle
	 */
	public void initialize(MoneyBundle bundle) {
		if (ATM.getInitialized()) {
			MoneyBundle atmBundle = ATM.getBundle();
			throw new ServiceException("ATM has already been initialized. Present status is:\n "+atmBundle.toString());
		}
		try {
			ATM.setBundle(bundle);
			ATM.setInitialized(true);
		} catch (PersistenceException e) {
			//Catch any issues with initialization
			MoneyBundle zeroBundle = new MoneyBundle();
			ATM.setBundle(zeroBundle);
			ATM.setInitialized(false);
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Deposits a moneybundle into the ATM
	 * @param bundle
	 */
	public void deposit(MoneyBundle bundle) {
		if (!ATM.getInitialized()) {
			throw new ServiceException("ATM has not been initialized.");
		}
		try {
			ATM.addMoneyBundle(bundle);
		} catch (PersistenceException e) {
			//Catch any issues with deposits
			throw new ServiceException(e);
		}
	}

	/**
	 * First attempts to change an amount value into a set of notes, which will automatically throw a serviceexception
	 * if an error is encountered. 
	 * Then removes the resulting notes from the ATM. 
	 * @param amount
	 * @return
	 */
	public MoneyBundle withdraw(Integer amount) {
		if (!ATM.getInitialized()) {
			throw new ServiceException("ATM has not been initialized.");
		}
		MoneyBundle bundle = new MoneyBundle();
		try {
			bundle.changeAmountToNotes(amount, ATM.getBundle());
			ATM.removeMoneyBundle(bundle);
		} catch (PersistenceException e) {
			//Catch any issues with withdrawals
			throw new ServiceException(e);
		}
		return bundle;
	}

}
