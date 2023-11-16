package org.selenium.orangeHRM.test;

import org.selenium.orangeHRM.testComponents.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {
	
	@Test(priority = 100, description = "Login - Successful login")
	public void LGN001() {
		loginPage.passInput("admin123");
		loginPage.loginClick();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test(priority = 1, description = "username invalid")
	public void LGN002() {
		loginPage.usernameInput("invalid");
		loginPage.passInput("string");
		loginPage.loginClick();
		
		// Assert
		String errtext = loginPage.errNotifText();
		
		if(!errtext.equalsIgnoreCase("Invalid credentials")) {
			Assert.fail("text doesn't match: " + errtext);
		} else if (loginPage.errNotifShown() == false) {
			Assert.fail("Error text is not shown");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test(priority = 2, description = "password invalid")
	public void LGN003() {
		loginPage.usernameClear();
		loginPage.usernameInput("Admin");
		loginPage.passInput("string");
		loginPage.loginClick();
		
		// Assert
		String errtext = loginPage.errNotifText();
		
		if(!errtext.equalsIgnoreCase("Invalid credentials")) {
			Assert.fail("text doesn't match: " + errtext);
		} else if (loginPage.errNotifShown() == false) {
			Assert.fail("Error text is not shown");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test(priority = 3, description = "username null")
	public void LGN004() {
		loginPage.usernameClear();
		loginPage.loginClick();
		
		// Assert
		if(loginPage.errTextCount() == 0) {
			Assert.fail("No error text found");
		} else if(!loginPage.errorText(0).equalsIgnoreCase("Required")) {
			Assert.fail("Wrong text shown: " + loginPage.errorText(0));
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test(priority = 4, description = "password null")
	public void LGN005() {
		loginPage.usernameInput("Admin");
		loginPage.passClear();
		loginPage.loginClick();
		
		// Assert
		if(loginPage.errTextCount() == 0) {
			Assert.fail("No error text found");
		} else if(!loginPage.errorText(0).equalsIgnoreCase("Required")) {
			Assert.fail("Wrong text shown: " + loginPage.errorText(0));
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test(priority = 5, description = "username and password null")
	public void LGN006() {
		
		driver.navigate().refresh();
		
		loginPage.usernameClear();
		loginPage.passClear();
		loginPage.loginClick();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert
		if(loginPage.errTextCount() == 2) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("error text only shown once");
		}
	}
}
