package au.com.suncorp.cashman3001.common;

import au.com.suncorp.cashman3001.service.ServiceException;

/**
 * Common Class to handle transferring bundles of money around
 * 
 * @author Luke
 *
 */
public class MoneyBundle {
	
	private Integer hundreds=0; 
	private Integer fifties=0; 
	private Integer twenties=0; 
	private Integer tens=0; 
	private Integer fives=0;
	
	//These variableswere added for expedience for breaking down withdrawal amounts into notes, with more thought I could probably come up with a more elegant solution
	private Integer amount=0;
	private MoneyBundle atmBundle;
	
	/**
	 * Clones the money bundle
	 * Added so the ATM persistance can give a copy of the current values in the ATM rather than directly working with the underlying details
	 */
	public MoneyBundle clone() {
		MoneyBundle bundle = new MoneyBundle();
		bundle.hundreds = this.hundreds;
		bundle.fifties = this.fifties;
		bundle.twenties = this.twenties;
		bundle.tens = this.tens;
		bundle.fives = this.fives;
		return bundle;
	}
	
	//Getters and Setters
	
	public Integer getTotalValue() {
		Integer value = hundreds*100+fifties*50+twenties*20+tens*10+fives*5;
		return value;
	}
	
	public Integer getHundreds() {
		return hundreds;
	}
	public void setHundreds(Integer hundreds) {
		this.hundreds = hundreds;
	}
	public Integer getFifties() {
		return fifties;
	}
	public void setFifties(Integer fifties) {
		this.fifties = fifties;
	}
	public Integer getTwenties() {
		return twenties;
	}
	public void setTwenties(Integer twenties) {
		this.twenties = twenties;
	}
	public Integer getTens() {
		return tens;
	}
	public void setTens(Integer tens) {
		this.tens = tens;
	}
	public Integer getFives() {
		return fives;
	}
	public void setFives(Integer fives) {
		this.fives = fives;
	}
	
	/**
	 * Creates a String representation of this object, formatted for HTML use.
	 */
	public String toString() {
		String result = "";
		result+="<br>"+hundreds+"x$100";
		result+="<br>"+fifties+"x$50";
		result+="<br>"+twenties+"x$20";
		result+="<br>"+tens+"x$10";
		result+="<br>"+fives+"x$5";
		return result;
	}
	
	/**
	 * Entry point into converting an amount into the notes required to represent this amount
	 * @param amount
	 * @param atmBundle
	 */
	public void changeAmountToNotes(Integer amount, MoneyBundle atmBundle) {
		if (this.getTotalValue()!=0) return;
		if (atmBundle.getTotalValue()<amount) {
			throw new ServiceException("Insufficient Funds in ATM");
		}
		this.amount = amount;
		this.atmBundle = atmBundle;
		setHighestNotes();
		exchange();
		if (this.amount>0) {
			throw new ServiceException("Unable to service request due to insufficient available notes");
		}
	}

	/**
	 * Because $20 is not a multiple of $50 there may be $10 remaining after determining the largest notes to use.
	 * If so this will swap a fifty out plus the $10 left over for three twenties
	 */
	private void exchange() {
		if (amount==10&&fifties>=1&&atmBundle.twenties>=3) {
			fifties-=1;
			atmBundle.fifties+=1;
			twenties+=3;
			atmBundle.twenties-=3;
			amount -= 10;
		}
	}
	
	/**
	 * Main algorithm for solving the notes to use problem
	 * Starts with the highest value notes and checks if there are any in the ATM. If so add one note of that denomination and subtract 
	 * the note value from the current amount.
	 * Repeat until no more notes can be swapped for an amount.
	 */
	private void setHighestNotes() {
		boolean continueProc = true;
		while (continueProc&&amount>0) {
			if (atmBundle.hundreds>0&&amount>=100) {
				hundreds+=1;
				atmBundle.hundreds-=1;
				amount -= 100;
			} else if (atmBundle.fifties>0&&amount>=50) {
				fifties+=1;
				atmBundle.fifties-=1;
				amount -= 50;
			} else if (atmBundle.twenties>0&&amount>=20) {
				twenties+=1;
				atmBundle.twenties-=1;
				amount -= 20;
			} else if (atmBundle.tens>0&&amount>=10) {
				tens+=1;
				atmBundle.tens-=1;
				amount -= 10;
			} else if (atmBundle.fives>0&&amount>=5) {
				fives+=1;
				atmBundle.fives-=1;
				amount -= 5;
			} else {
				continueProc=false;
			}
		}
	}
	
}
