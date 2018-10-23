package au.com.suncorp.cashman3001.persistence;

import au.com.suncorp.cashman3001.common.MoneyBundle;

/**
 * This is an ATM persistence emulator. In reality I would use the DAO/Domain objects pattern and something like hibernate
 * to actually store this information in the database.
 * 
 * This stores the information in memory and should be thread safe, will only allow one thread at a time to 
 * modify information in the ATM.
 * 
 * Requesting the current notes (bundle) returns a copy of the static variable rather than the actual object
 * as you do not wish other objects making changes to the underlying data accidently.
 * @author Luke
 *
 */
public class ATM {
	
	private static MoneyBundle bundle = new MoneyBundle();
	private static final Object lock = new Object();
	private static Boolean initialized = false;
	
	public static MoneyBundle getBundle() {
		return bundle.clone();
	}

	public static void setBundle(MoneyBundle bundle) {
		synchronized (lock) {
			ATM.bundle = bundle;
		}
	}

	public static Boolean getInitialized() {
		return initialized;
	}

	public static void setInitialized(Boolean initialized) {
		ATM.initialized = initialized;
	}
	
	/**
	 * Takes a money bundle and adds the notes contained into the ATM
	 * @param newBundle
	 */
	public static void addMoneyBundle(MoneyBundle newBundle) {
		synchronized (lock) {
			bundle.setHundreds(bundle.getHundreds()+newBundle.getHundreds());
			bundle.setFifties(bundle.getFifties()+newBundle.getFifties());
			bundle.setTwenties(bundle.getTwenties()+newBundle.getTwenties());
			bundle.setTens(bundle.getTens()+newBundle.getTens());
			bundle.setFives(bundle.getFives()+newBundle.getFives());
		}
	}
	
	
	/**
	 * Takes a money bundle and attempts to remove those notes from the ATM
	 * If there are insufficient notes an exception will be generated.
	 * @param newBundle
	 */
	public static void removeMoneyBundle(MoneyBundle newBundle) {
		if ((bundle.getHundreds()-newBundle.getHundreds())<0||
			(bundle.getFifties()-newBundle.getFifties())<0||
			(bundle.getTwenties()-newBundle.getTwenties())<0||
			(bundle.getTens()-newBundle.getTens())<0||
			(bundle.getFives()-newBundle.getFives())<0) {
			throw new PersistenceException("Can not withdraw more than in the ATM.");
		}
		synchronized (lock) {
			if (bundle.getHundreds()-newBundle.getHundreds()<0||
					bundle.getFifties()-newBundle.getFifties()<0||
					bundle.getTwenties()-newBundle.getTwenties()<0||
					bundle.getTens()-newBundle.getTens()<0||
					bundle.getFives()-newBundle.getFives()<0) {
				throw new PersistenceException("Insufficient notes in ATM for transaction.");
			}
			bundle.setHundreds(bundle.getHundreds()-newBundle.getHundreds());
			bundle.setFifties(bundle.getFifties()-newBundle.getFifties());
			bundle.setTwenties(bundle.getTwenties()-newBundle.getTwenties());
			bundle.setTens(bundle.getTens()-newBundle.getTens());
			bundle.setFives(bundle.getFives()-newBundle.getFives());
		}
	}
	
	
}
