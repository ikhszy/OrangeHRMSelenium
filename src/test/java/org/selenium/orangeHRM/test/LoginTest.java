package org.selenium.orangeHRM.test;

import org.selenium.orangeHRM.testComponents.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseClass {
	
	@Test(priority = 3, description = "successfully login")
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
	
	@Test(priority = 1, description = "username and password null")
	public void LGN002() {
		loginPage.loginClick();
		
		// Assert
		if(loginPage.errTextCount() == 2) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("error text only shown once");
		}
	}
	
	@Test(priority = 2, description = "password null")
	public void LGN003() {
		loginPage.usernameInput("Admin");
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

}
