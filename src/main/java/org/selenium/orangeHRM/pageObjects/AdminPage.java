package org.selenium.orangeHRM.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
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
	
	@FindBy(className = "oxd-autocomplete-text-input")
	private WebElement searchEmployeeName;
	
	@FindBy(className = "oxd-autocomplete-dropdown")
	private List<WebElement> searchEmpNameSuggestion;
	
	@FindBy(className = "oxd-select-text-input")
	private List <WebElement> selectDropdown;
	
	@FindBy(xpath = "//*[@type='submit']")
	private WebElement searchButton;
	
	@FindBy(xpath = "//*[@type='button']")
	private WebElement resetButton;
	
	@FindBy(className = "oxd-table-card")
	private List <WebElement> tableResults;
	
	public void searchUsernameType(String uname) {
		searchUsername.get(1).sendKeys(uname);
	}
	
	public void searchUsernameClear() {
		searchUsername.get(1).clear();
	}
	
	public void dropdownRole(String role) {
		Select sel = new Select(selectDropdown.get(0));
		
		if(role.equalsIgnoreCase("admin")) {
			sel.selectByVisibleText("Admin");
		} else {
			sel.selectByVisibleText("ESS");
		}
	}
	
	public void dropdownStatus(String status) {
		
		Select sel = new Select(selectDropdown.get(1));
		
		if(status.equalsIgnoreCase("enabled")) {
			sel.selectByVisibleText("Enabled");
		} else {
			sel.selectByVisibleText("Disabled");
		}
	}
	
	public void searchEmployeeName(String emp) {
		searchEmployeeName.sendKeys(emp);
		
		waitForElementToAppear(By.className("oxd-autocomplete-dropdown"));
		searchEmpNameSuggestion.get(0).click();
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

}
