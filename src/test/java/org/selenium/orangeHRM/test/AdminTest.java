package org.selenium.orangeHRM.test;

import org.selenium.orangeHRM.testComponents.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminTest extends BaseClass {
	
	@Test(priority = 1, description = "Admin - Success search by username")
	public void ADM001() {
		// login first
		loginPage.usernameInput("Admin");;
		loginPage.passInput("admin123");
		loginPage.loginClick();
		
		// enter the admin page
		dashPage.searchMenuAndClick("Admin");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Search by username
		admPage.searchUsernameType("Admin");
		admPage.searchClick();
		
		// Assert
		if(!admPage.searchResultText().equals("(" + 1+ ") Record Found")) {
			Assert.fail("text result is wrong: " + admPage.searchResultText());
		} else if(admPage.searchResultTable() != 1) {
			Assert.fail("table is showing wrong number of result: " + admPage.searchResultTable());
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test(priority = 2, description = "Admin - Success search by username")
	public void ADM002() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// clear and re-search
		admPage.searchUsernameClear();
		admPage.searchUsernameType("gggggg!!!!!");
		admPage.searchClick();
		
		// Assert
		if(!admPage.searchResultText().equals("No Records Found")) {
			Assert.fail("text result is wrong: " + admPage.searchResultText());
		} else if(admPage.searchResultTable() != 0) {
			Assert.fail("table is showing wrong number of result: " + admPage.searchResultTable());
		} else {
			Assert.assertTrue(true);
		}
		
	}

}
