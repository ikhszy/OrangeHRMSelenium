package org.selenium.orangeHRM.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.orangeHRM.utils.ReusedFeature;

public class AddUserPage extends ReusedFeature {
	
	WebDriver driver;
	
	public AddUserPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "oxd-select-text-input")
	private List <WebElement> dropdowns;
	
	@FindBy(className ="oxd-select-option")
	private List <WebElement> dropdownSelections;
	
	@FindBy(xpath = "//*[@placeholder='Type for hints...']")
	private WebElement addEmployeeName;
	
	@FindBy(css = ".oxd-autocomplete-dropdown")
	private WebElement addEmpNameSuggestion;
	
	@FindBy(css =".oxd-input.oxd-input")
	private List <WebElement> addInput;
	
	@FindBy(xpath = "//*[@type='submit']")
	private WebElement addEmployeeSaveButton;
	
	@FindBy(xpath = "//*[@type='button']")
	private WebElement addEmployeeCancelButton;

	public void selectDropdown(String dropdown, String selection) {
		if(dropdown.equalsIgnoreCase("role")) {
			dropdowns.get(0).click();
		} else {
			dropdowns.get(1).click();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(selection.equalsIgnoreCase("admin") || selection.equalsIgnoreCase("enabled")) {
			dropdownSelections.get(1).click();
		} else {
			dropdownSelections.get(2).click();
		}
	}
	
	public void addEmployeeName(String emp) {
		addEmployeeName.sendKeys(emp);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addEmpNameSuggestion.click();
	}
	
	public void addInput(String box, String input) {
		if(box.equalsIgnoreCase("username")) {
			addInput.get(1).sendKeys(input);
		} else if(box.equalsIgnoreCase("password")) {
			addInput.get(2).sendKeys(input);
		} else {
			addInput.get(3).sendKeys(input);
		}
	}
	
	public void addSave() {
		addEmployeeSaveButton.click();
	}
	
	public void addCancel() {
		addEmployeeCancelButton.click();
	}
}
