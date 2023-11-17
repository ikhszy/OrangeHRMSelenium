package org.selenium.orangeHRM.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.orangeHRM.utils.ReusedFeature;

public class AdminPage extends ReusedFeature {
	
	WebDriver driver;
	
	public AdminPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".oxd-input.oxd-input--active")
	private List <WebElement> searchUsername;
	
	@FindBy(xpath = "//*[@placeholder='Type for hints...']")
	private WebElement searchEmployeeName;
	
	@FindBy(css = ".oxd-autocomplete-dropdown")
	private WebElement searchEmpNameSuggestion;
	
	@FindBy(className = "oxd-select-text-input")
	private List <WebElement> selectDropdown;
	
	@FindBy(xpath="//*[role='option']")
	private List <WebElement> dropdownSelections;
	
	@FindBy(xpath = "//*[@type='submit']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//*[@type='button']")
	private WebElement resetButton;
	
	@FindBy(className = "oxd-table-card")
	private List <WebElement> tableResults;
	
	@FindBy(xpath = "//*[@class='oxd-table-cell oxd-padding-cell']")
	private List <WebElement> tableData; 
	
	public void searchUsernameType(String uname) {
		searchUsername.get(1).sendKeys(uname);
	}
	
	public void searchUsernameClear() {
		searchUsername.get(1).clear();
	}
	
	public void dropdownRole(String role) {
		selectDropdown.get(0).click();
		
		if(role.equalsIgnoreCase("admin")) {
			selectDropdown.get(0).sendKeys(Keys.ARROW_DOWN);
			selectDropdown.get(0).sendKeys(Keys.ENTER);
		} else {
			selectDropdown.get(0).sendKeys(Keys.ARROW_DOWN);
			selectDropdown.get(0).sendKeys(Keys.ARROW_DOWN);
			selectDropdown.get(0).sendKeys(Keys.ARROW_DOWN);
			selectDropdown.get(0).sendKeys(Keys.ENTER);
		}
	}
	
	public void dropdownStatus(String status) {
		selectDropdown.get(1).click();
		if(status.equalsIgnoreCase("enabled")) {
			selectDropdown.get(1).sendKeys(Keys.ARROW_DOWN);
			selectDropdown.get(1).sendKeys(Keys.ENTER);
		} else {
			selectDropdown.get(1).sendKeys(Keys.ARROW_DOWN);
			selectDropdown.get(1).sendKeys(Keys.ARROW_DOWN);
			selectDropdown.get(1).sendKeys(Keys.ENTER);
		}
	}
	
	public void searchEmployeeName(String emp) {
		searchEmployeeName.sendKeys(emp);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		searchEmpNameSuggestion.click();
	}
	
	public void searchClick() {
		searchButton.click();
	}
	
	public String searchResultText() {
		String text = driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[2]/div[2]/div/span")).getText();
		return text;
	}
	
	public int searchResultTable() {
		int table = tableResults.size();
		return table;
	}
	
	public String getTableUsername(int index) {
		String userName = "";
		if(index == 0) {
			userName = tableData.get(index + 1).getText();
		} else {
			userName = tableData.get(index * 6 + 1).getText();
		}
		return userName;
	}
	
	public String getTableUserRole(int index) {
		String userRole = "";
		if(index == 0) {
			userRole = tableData.get(index + 2).getText();
		} else {
			userRole = tableData.get(index * 6 + 2).getText();
		}
		return userRole;
	}
	
	public String getTableEmployeename(int index) {
		String userRole = "";
		if(index == 0) {
			userRole = tableData.get(index + 3).getText();
		} else {
			userRole = tableData.get(index * 6 + 3).getText();
		}
		return userRole;
	}
	
	public String getTableUserStatus(int index) {
		String userRole = "";
		if(index == 0) {
			userRole = tableData.get(index + 4).getText();
		} else {
			userRole = tableData.get(index * 6 + 4).getText();
		}
		return userRole;
	}

}
