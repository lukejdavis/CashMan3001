package au.com.suncorp.cashman3001.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.suncorp.cashman3001.common.Deposit;
import au.com.suncorp.cashman3001.common.Message;
import au.com.suncorp.cashman3001.common.MoneyBundle;
import au.com.suncorp.cashman3001.common.Withdrawal;
import au.com.suncorp.cashman3001.persistence.ATM;
import au.com.suncorp.cashman3001.service.ATMService;
import au.com.suncorp.cashman3001.service.ServiceException;

/**
 * ATM Rest Interface
 * Provides the interface to interact and make changes within the ATM
 * 
 * @author Luke
 *
 */
@RestController
public class ATMController {
	
	@Autowired
	ATMService atmService;
	
	/**
	 * Rest Interface to intitialize the ATM.
	 * Can only be run once and will return an error message if attempted a second time.
	 * 
	 * @param hundreds
	 * @param fifties
	 * @param twenties
	 * @param tens
	 * @param fives
	 * @return
	 */
	@RequestMapping("/ATM/initialize")
	public Message initialize(@RequestParam(value="hundreds", defaultValue="0") Integer hundreds,
			@RequestParam(value="fifties", defaultValue="0") Integer fifties,
			@RequestParam(value="twenties", defaultValue="0") Integer twenties,
			@RequestParam(value="tens", defaultValue="0") Integer tens,
			@RequestParam(value="fives", defaultValue="0") Integer fives) {
		try {
			MoneyBundle bundle = new MoneyBundle();
			bundle.setHundreds(hundreds);
			bundle.setFifties(fifties);
			bundle.setTwenties(twenties);
			bundle.setTens(tens);
			bundle.setFives(fives);
			atmService.initialize(bundle);
			return new Message("ATM Has been ititialized with:\n"+ATM.getBundle().toString());
		} catch (ServiceException e) {
			return new Message(e.getMessage());
		}
    }

	/**
	 * Deposit interface into the ATM
	 * Accepts a set of note amounts as parameters
	 * 
	 * @param hundreds
	 * @param fifties
	 * @param twenties
	 * @param tens
	 * @param fives
	 * @return
	 */
	@RequestMapping("/ATM/deposit")
	public Deposit deposit(@RequestParam(value="hundreds", defaultValue="0") Integer hundreds,
			@RequestParam(value="fifties", defaultValue="0") Integer fifties,
			@RequestParam(value="twenties", defaultValue="0") Integer twenties,
			@RequestParam(value="tens", defaultValue="0") Integer tens,
			@RequestParam(value="fives", defaultValue="0") Integer fives) {
		MoneyBundle bundle = new MoneyBundle();
		bundle.setHundreds(hundreds);
		bundle.setFifties(fifties);
		bundle.setTwenties(twenties);
		bundle.setTens(tens);
		bundle.setFives(fives);
		Deposit dep = new Deposit(bundle,"");
		try {
			atmService.deposit(bundle);
			dep.setContent("$"+bundle.getTotalValue()+" has been deposited.");
		} catch (ServiceException e) {
			dep.setContent(e.getMessage());
		}
		return dep;
    }

	/**
	 * Withdrawal Interface for the ATM
	 * Accepts the amount to be withdrawn and returns a MoneyBundel with the count of notes recieved.
	 * @param amount
	 * @return
	 */
	@RequestMapping("/ATM/withdraw")
	public Withdrawal withdrawal(@RequestParam(value="amount", defaultValue="0") Integer amount) {
		MoneyBundle bundle;
		Withdrawal withdrawal;
		try {
			bundle = atmService.withdraw(amount);
			withdrawal = new Withdrawal(bundle,"");
		} catch (ServiceException e) {
			withdrawal = new Withdrawal(e.getMessage());
		}
        return withdrawal;
    }
	
}
