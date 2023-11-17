package org.selenium.orangeHRM.test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.selenium.orangeHRM.testComponents.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AdminTest extends BaseClass {
	
	ArrayList <String> empName = new ArrayList<String>();
	
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
	
	@Test(priority = 2, description = "Admin - failed search by username")
	public void ADM002() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// clear and re-search
		driver.navigate().refresh();
		admPage.searchUsernameType("gggggg!!!!!");
		admPage.searchClick();
		
		// get the toast
		admPage.waitForElementToAppear(By.className("oxd-toast-content-text"));
		String tMessage = driver.findElements(By.className("oxd-toast-content-text")).get(1).getText();
		
		// Assert
		if(!tMessage.equals("No Records Found")) {
			Assert.fail("Wrong toast message: " + tMessage);
		} else if(!admPage.searchResultText().equals("No Records Found")) {
			Assert.fail("text result is wrong: " + admPage.searchResultText());
		} else if(admPage.searchResultTable() != 0) {
			Assert.fail("table is showing wrong number of result: " + admPage.searchResultTable());
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test(priority = 3, description = "Admin - Success search by role (admin)")
	public void ADM003() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// clear and re-search
		driver.navigate().refresh();
		admPage.dropdownRole("Admin");
		admPage.searchClick();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert 1
		if(admPage.searchResultTable() == 0) {
			Assert.fail("table is showing wrong number of result: " + admPage.searchResultTable());
		} else {
			Assert.assertTrue(true);
		}
		
		// Assert 2
		for(int i = 0; i < admPage.searchResultTable(); i++) {
			if(!admPage.getTableUserRole(i).equalsIgnoreCase("Admin")) {
				Assert.fail("table with non Admin result at row: " + (i+1) + " " + admPage.getTableUserRole(i));
			} else {
				// add employee name to pojo class
				empName.add(admPage.getTableEmployeename(i));
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test(priority = 4, description = "Admin - Success search by role (ESS)")
	public void ADM004() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// clear and re-search
		driver.navigate().refresh();
		admPage.dropdownRole("ESS");
		admPage.searchClick();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert 1
		if(admPage.searchResultTable() == 0) {
			Assert.fail("table is showing wrong number of result: " + admPage.searchResultTable());
		} else {
			Assert.assertTrue(true);
		}
		
		// Assert 2
		for(int i = 0; i < admPage.searchResultTable(); i++) {
			if(!admPage.getTableUserRole(i).equalsIgnoreCase("ESS")) {
				Assert.fail("table with non ESS result at row: " + (i+1) + " " + admPage.getTableUserRole(i));
			} else {
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test(priority = 5, description = "Admin - Success search by Employee Name")
	public void ADM005() {
		// clear and re-search
		// search using index no 2 because this is a public website and data is moving
		// 3rd data is the least to change so far
		driver.navigate().refresh();
		admPage.searchEmployeeName(empName.get(2));
		admPage.searchClick();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert 1
		if(admPage.searchResultTable() == 0) {
			Assert.fail("table is showing wrong number of result: " + admPage.searchResultTable());
		} else {
			Assert.assertTrue(true);
		}
		
		// Assert 2
		for(int i = 0; i < admPage.searchResultTable(); i++) {
			if(!admPage.getTableEmployeename(i).equalsIgnoreCase(empName.get(2))) {
				Assert.fail("table with wrong employee name at row: " + (i+1) + " " + admPage.getTableEmployeename(i));
			} else {
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test(priority = 7, description = "Admin - Success search by user status (Enabled)")
	public void ADM007() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// clear and re-search
		driver.navigate().refresh();
		admPage.dropdownStatus("enabled");
		admPage.searchClick();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert 1
		if(admPage.searchResultTable() == 0) {
			Assert.fail("table is showing wrong number of result: " + admPage.searchResultTable());
		} else {
			Assert.assertTrue(true);
		}
		
		// Assert 2
		for(int i = 0; i < admPage.searchResultTable(); i++) {
			if(!admPage.getTableUserStatus(i).equalsIgnoreCase("Enabled")) {
				Assert.fail("table with non Enabled result at row: " + (i+1) + " " + admPage.getTableUserStatus(i));
			} else {
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test(priority = 8, description = "Admin - Success search by user status (Disabled)")
	public void ADM008() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// clear and re-search
		driver.navigate().refresh();
		admPage.dropdownStatus("enabled");
		admPage.searchClick();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert 1
		if(admPage.searchResultTable() == 0) {
			Assert.assertTrue(true);
		} else {
			// Assert 2
			for(int i = 0; i < admPage.searchResultTable(); i++) {
				if(!admPage.getTableUserStatus(i).equalsIgnoreCase("Enabled")) {
					Assert.fail("table with non Disabled result at row: " + (i+1) + " " + admPage.getTableUserStatus(i));
				} else {
					Assert.assertTrue(true);
				}
			}
		}
	}

}
