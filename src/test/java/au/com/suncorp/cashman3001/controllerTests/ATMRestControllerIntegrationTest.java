package au.com.suncorp.cashman3001.controllerTests;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring Runner Automated Testing Class
 * Conditions : This is a rest interface test therefore the application must be running prior to execution.
 * 				Assumes localhost:8080 is the web address
 * 
 * This is just an example test scenario testing cases outlined in the problem document.  
 * SpringRunner can be extended to run data tests, MVC tests, Unit Tests and so on.
 * 
 * @author Luke
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@ComponentScan("au.com.suncorp.cashman3001.service")
public class ATMRestControllerIntegrationTest {

	TestRestTemplate restTemplate = new TestRestTemplate();
	
	/**
	 * Ensure ATM has been intitialized prior to executing tests
	 */
	@Before
	public void setup() {
		try {
			String result;

			result = this.restTemplate.getForObject("http://localhost:8080/ATM/initialize?fifties=100&twenties=100",String.class);
			System.out.println(result);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Simple test to ensure deposits interface works 
	 */
	@Test
	public void testDeposits() {
		try {
			String result = this.restTemplate.getForObject("http://localhost:8080/ATM/deposit?fifties=1&twenties=1",String.class);
			JSONObject obj = new JSONObject(result);
			assert(obj.getString("content").contains("$70 has been deposited."));
			System.out.println("Deposit:"+result);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * More complicated test using scenarios in the problem document
	 */
	@Test
	public void testWithdrawals() {
		try {
			String result;
			JSONObject obj;

			//Get the current machine status and ensure there are enough notes in the ATM to execute this test.
			result = this.restTemplate.getForObject("http://localhost:8080/reports/status",String.class);
			obj = new JSONObject(result);
			Integer fifties=0;
			Integer twenties=0;
			if (obj.getInt("fifties")<100) {
				fifties = 100-obj.getInt("fifties");
			}
			if (obj.getInt("twenties")<100) {
				twenties = 100-obj.getInt("twenties");
			}
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/deposit?fifties="+fifties+"&twenties="+twenties,String.class);
			
			//Withdraw $30 - this should fail to withdraw
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=30",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==0);
			assert(obj.getString("content").equals("Unable to service request due to insufficient available notes"));
			System.out.println("Withdrawal:"+result);

			//Withdraw $20
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=20",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==20);
			System.out.println("Withdrawal:"+result);

			//Withdraw $40
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=40",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==40);
			System.out.println("Withdrawal:"+result);

			//Withdraw $50
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=50",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==50);
			System.out.println("Withdrawal:"+result);

			//Withdraw $70
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=70",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==70);
			System.out.println("Withdrawal:"+result);

			//Withdraw $80
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=80",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==80);
			System.out.println("Withdrawal:"+result);

			//Withdraw $100
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=100",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==100);
			System.out.println("Withdrawal:"+result);

			//Withdraw $150
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=150",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==150);
			System.out.println("Withdrawal:"+result);

			//Withdraw $60
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=60",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==60);
			System.out.println("Withdrawal:"+result);

			//Withdraw $110
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=110",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==110);
			System.out.println("Withdrawal:"+result);

			//Clear ATM of notes and add 3 fifties and 8 twenties to the ATM
			result = this.restTemplate.getForObject("http://localhost:8080/reports/status",String.class);
			obj = new JSONObject(result);
			fifties=obj.getInt("fifties");
			twenties=obj.getInt("twenties");
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount="+(fifties*50),String.class);
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount="+(twenties*20),String.class);
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/deposit?fifties=3&twenties=8",String.class);
			result = this.restTemplate.getForObject("http://localhost:8080/reports/status",String.class);
			System.out.println("Status:"+result);
			
			//Withdraw $200
			result = this.restTemplate.getForObject("http://localhost:8080/ATM/withdraw?amount=200",String.class);
			obj = new JSONObject(result);
			assert(obj.getJSONObject("bundle").getInt("totalValue")==200);
			System.out.println("Withdrawal:"+result);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
