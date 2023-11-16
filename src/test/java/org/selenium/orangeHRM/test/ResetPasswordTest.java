package org.selenium.orangeHRM.test;

import org.selenium.orangeHRM.testComponents.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseClass {
	
	@Test(priority = 100, description = "Reset - Successful password reset")
	public void RST001() {
		// enter the page
		loginPage.forgotClick();
		
		resetPage.userType("someone");
		resetPage.clickSubmitButton();
		
		// Assert
		if(!resetPage.successBox.isDisplayed()) {
			Assert.fail("reset failed");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test(priority = 1, description = "Reset - Empty username")
	public void RST002() {
		// enter the page
		loginPage.forgotClick();
		
		resetPage.clickSubmitButton();
		
		// Assert
		String ertext = resetPage.errorText();
		if(!ertext.equalsIgnoreCase("Required")) {
			Assert.fail("text doesn't match: " + ertext);
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test(priority = 2, description = "Reset - Cancel")
	public void RST003() {
		resetPage.userType("someone");
		resetPage.clickCancelButton();
		
		// Assert
		if(!loginPage.title.isDisplayed()) {
			Assert.fail("user not redirected to login page");
		} else {
			Assert.assertTrue(true);
		}
	}

}
