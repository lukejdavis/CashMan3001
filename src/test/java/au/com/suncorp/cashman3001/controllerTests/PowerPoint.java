package au.com.suncorp.cashman3001.controllerTests;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PowerPoint {

	public void test() {
		boolean isAdmin = true; 
		try { 
		  codeWhichMayFail(); 
		  isAdmin = isUserInRole("Administrator"); 
		} catch (Exception ex) {
		  System.out.println(ex.toString()); 
		}
		if (isAdmin) {}//do admin stuff
	}
	
	public void test2() {
		boolean isAdmin = false; 
		try { 
		  codeWhichMayFail(); 
		  isAdmin = isUserInRole("Administrator"); 
		}
		catch (Exception ex) {
		  System.out.println(ex.toString()); 
		}
		if (isAdmin) {}//do admin stuff
	}
	
	private void codeWhichMayFail() {
		
	}

	private boolean isUserInRole(String user) {
		return true;
	}

}
