package au.com.suncorp.cashman3001.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.suncorp.cashman3001.common.MoneyBundle;
import au.com.suncorp.cashman3001.service.ReportingService;

/**
 * ATM Report Rest Interface
 * Provides any reports required by the ATM
 * 
 * @author Luke
 *
 */

@RestController
public class ReportingController {

	@Autowired
	ReportingService reportingService;

	/**
	 * Interface into the current status of the ATM
	 * 
	 * Returns a MoneyBundle containing the number of notes presently in the ATM 
	 * @param amount
	 * @return
	 */
	@RequestMapping("/reports/status")
	public MoneyBundle withdrawal(@RequestParam(value="amount", defaultValue="0") Integer amount) {
		MoneyBundle bundle = reportingService.getStatus();
        return bundle;
    }

}
