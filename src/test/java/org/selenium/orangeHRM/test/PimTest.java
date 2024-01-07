package org.selenium.orangeHRM.test;

import org.selenium.orangeHRM.testComponents.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PimTest extends BaseClass {
	
	@Test(priority = 1, description = "Search Employee name")
	public void PEL001() {
		// login first
		loginPage.usernameInput("Admin");;
		loginPage.passInput("admin123");
		loginPage.loginClick();
		
		// enter the PIM page
		dashPage.searchMenuAndClick("PIM");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// verify entering the right page
		if(pimPage.verifyPage().equals("PIM")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("Entering wrong page with title: " + pimPage.verifyPage());
		}
		
		// Search by employee name
		pimPage.suggestionSearch("employee", "jacq", 0);
		pimPage.searchSubmit();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// validate the result
		String ver = pimPage.resultGetter("firstname", "jacqueline");
		if(ver.equalsIgnoreCase("no result found")) {
			Assert.fail("no result found");
		} else if (ver.equalsIgnoreCase("jacqueline")) {
			Assert.assertTrue(true);
		} else {
			System.out.println(ver);
		}
	}
}
