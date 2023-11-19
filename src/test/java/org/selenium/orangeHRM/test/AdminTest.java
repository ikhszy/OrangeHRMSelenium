package org.selenium.orangeHRM.test;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
		admPage.selectDropdown("role", "admin");
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
		admPage.selectDropdown("role", "ESS");
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
		admPage.searchEmployeeName(empName.get(0));
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
			if(!admPage.getTableEmployeename(i).equalsIgnoreCase(empName.get(0))) {
				Assert.fail("table with wrong employee name at row: " + (i+1) + " " + admPage.getTableEmployeename(i));
			} else {
				Assert.assertTrue(true);
			}
		}
	}
	
	@Test(priority = 6, description = "Admin - Failed search by Employee Name")
	public void ADM006() {
		// clear and re-search
		driver.navigate().refresh();
		admPage.searchEmployeeName("return nothing");
		
		// press tab twice and click search
		for(int i = 0; i <= 1; i++) {
			admPage.keysAction(Keys.TAB);
		}
		
		admPage.searchClick();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert 
		if(admPage.searchResultTable() == 0) {
			Assert.fail("table is showing zero result");
		} else if(!admPage.empNameError().equalsIgnoreCase("invalid")) {
			Assert.fail("error text is wrong: " + admPage.empNameError());
		} else {
			Assert.assertTrue(true);
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
		admPage.selectDropdown("status", "enabled");
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
		admPage.selectDropdown("status", "disabled");
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
				if(!admPage.getTableUserStatus(i).equalsIgnoreCase("Disabled")) {
					Assert.fail("table with non Disabled result at row: " + (i+1) + " " + admPage.getTableUserStatus(i));
				} else {
					Assert.assertTrue(true);
				}
			}
		}
	}
	
	@Test(priority = 10, description = "Admin - Add new admin user")
	public void ADM010() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// enter add user page
		admPage.addNewUser();
		
		// set fake data
		vbl.setUsername(faker.name().username());
		vbl.setPassword(faker.internet().password());
		
		// fill the form
		adduserPage.selectDropdown("role", "admin");
		adduserPage.selectDropdown("status", "enabled");
		adduserPage.addEmployeeName(empName.get(0));
		adduserPage.addInput("username", vbl.getUsername());
		adduserPage.addInput("password", vbl.getPassword());
		adduserPage.addInput("else", vbl.getPassword());
		
		// save the form
		adduserPage.addSave();
		admPage.waitPage();
		
		// Assert by searching the username
		// Search by username
		admPage.searchUsernameType(vbl.getUsername());
		admPage.searchClick();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert 1
		if(!admPage.searchResultText().equals("(" + 1 + ") Record Found")) {
			Assert.fail("text result is wrong: " + admPage.searchResultText());
		} else if(admPage.searchResultTable() != 1) {
			Assert.fail("table is showing wrong number of result: " + admPage.searchResultTable());
		} else {
			Assert.assertTrue(true);
		}
		
		// Assert 2
		for(int i = 0; i < admPage.searchResultTable(); i++) {
			if(admPage.getTableUsername(i).equalsIgnoreCase(vbl.getUsername())) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("table doesn't contain the username");
			}
		}
		
		// Assert 3
		if(admPage.getTableUserRole(0).equalsIgnoreCase("admin")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("showing wrong role: " + admPage.getTableUserRole(0));
		}
		
		// logout
		profBadge.dropSelection("logout");
		
		// Assert 4
		loginPage.usernameInput(vbl.getUsername());;
		loginPage.passInput(vbl.getPassword());
		loginPage.loginClick();
		
		if(!dashPage.dashboardTitle.isDisplayed()) {
			Assert.fail("failed to login");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test(priority = 11, description = "Admin - Add new ESS user")
	public void ADM011() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// enter add user page
		admPage.addNewUser();
		
		// set fake data
		vbl.setUsername(faker.name().username());
		vbl.setPassword(faker.internet().password());
		
		// fill the form
		adduserPage.selectDropdown("role", "ess");
		adduserPage.selectDropdown("status", "enabled");
		adduserPage.addEmployeeName(empName.get(0));
		adduserPage.addInput("username", vbl.getUsername());
		adduserPage.addInput("password", vbl.getPassword());
		adduserPage.addInput("else", vbl.getPassword());
		
		// save the form
		adduserPage.addSave();
		admPage.waitPage();
		
		// Assert by searching the username
		// Search by username
		admPage.searchUsernameType(vbl.getUsername());
		admPage.searchClick();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert 1
		if(!admPage.searchResultText().equals("(" + 1 + ") Record Found")) {
			Assert.fail("text result is wrong: " + admPage.searchResultText());
		} else if(admPage.searchResultTable() != 1) {
			Assert.fail("table is showing wrong number of result: " + admPage.searchResultTable());
		} else {
			Assert.assertTrue(true);
		}
		
		// Assert 2
		for(int i = 0; i < admPage.searchResultTable(); i++) {
			if(admPage.getTableUsername(i).equalsIgnoreCase(vbl.getUsername())) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("table doesn't contain the username");
			}
		}
		
		// Assert 3
		if(admPage.getTableUserRole(0).equalsIgnoreCase("ess")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("showing wrong role: " + admPage.getTableUserRole(0));
		}
		
		// logout
		profBadge.dropSelection("logout");
		
		// Assert 4
		loginPage.usernameInput(vbl.getUsername());;
		loginPage.passInput(vbl.getPassword());
		loginPage.loginClick();
		
		if(!dashPage.dashboardTitle.isDisplayed()) {
			Assert.fail("failed to login");
		} else {
			Assert.assertTrue(true);
		}
	}
	
	@Test(priority = 12, description = "Admin - Add new disabled user")
	public void ADM012() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// enter add user page
		admPage.addNewUser();
		
		// set fake data
		vbl.setUsername(faker.name().username());
		vbl.setPassword(faker.internet().password());
		
		// fill the form
		adduserPage.selectDropdown("role", "ess");
		adduserPage.selectDropdown("status", "disabled");
		adduserPage.addEmployeeName(empName.get(0));
		adduserPage.addInput("username", vbl.getUsername());
		adduserPage.addInput("password", vbl.getPassword());
		adduserPage.addInput("else", vbl.getPassword());
		
		// save the form
		adduserPage.addSave();
		admPage.waitPage();
		
		// Assert by searching the username
		// Search by username
		admPage.searchUsernameType(vbl.getUsername());
		admPage.searchClick();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Assert 1
		if(!admPage.searchResultText().equals("(" + 1 + ") Record Found")) {
			Assert.fail("text result is wrong: " + admPage.searchResultText());
		} else if(admPage.searchResultTable() != 1) {
			Assert.fail("table is showing wrong number of result: " + admPage.searchResultTable());
		} else {
			Assert.assertTrue(true);
		}
		
		// Assert 2
		for(int i = 0; i < admPage.searchResultTable(); i++) {
			if(admPage.getTableUsername(i).equalsIgnoreCase(vbl.getUsername())) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("table doesn't contain the username");
			}
		}
		
		// Assert 3
		if(admPage.getTableUserStatus(0).equalsIgnoreCase("disabled")) {
			Assert.assertTrue(true);
		} else {
			Assert.fail("showing wrong status: " + admPage.getTableUserStatus(0));
		}
		
		// logout
		profBadge.dropSelection("logout");
		
		// Assert 4
		loginPage.usernameInput(vbl.getUsername());;
		loginPage.passInput(vbl.getPassword());
		loginPage.loginClick();
		
		if(!loginPage.errNotifText().equalsIgnoreCase("Invalid credentials")) {
			Assert.fail("text doesn't match: " + loginPage.errNotifText());
		} else if (loginPage.errNotifShown() == false) {
			Assert.fail("Error text is not shown");
		} else {
			Assert.assertTrue(true);
		}
	}
}
