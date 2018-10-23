package au.com.suncorp.cashman3001.service;

import org.springframework.stereotype.Service;

import au.com.suncorp.cashman3001.common.MoneyBundle;
import au.com.suncorp.cashman3001.persistence.ATM;
import au.com.suncorp.cashman3001.persistence.PersistenceException;

/**
 * Service pattern for providing reports on the status of the ATM
 * @author Luke
 *
 */
@Service
public class ReportingService {
	
	/**
	 * Returns the MoneyBundle with the current level of notes within the ATM
	 * @return
	 */
	public MoneyBundle getStatus() {
		return ATM.getBundle();
	}
}
