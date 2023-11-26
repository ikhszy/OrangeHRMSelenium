package org.selenium.orangeHRM.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.orangeHRM.utils.ReusedFeature;

public class PimPage extends ReusedFeature {

WebDriver driver;
	
	public PimPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "oxd-topbar-header-breadcrumb-module")
	private WebElement pimTitle;
	
	@FindBy(xpath = "//*[@placeholder='Type for hints...']")
	private List<WebElement> hintSearch;
	
	@FindBy(css = ".oxd-autocomplete-dropdown")
	private WebElement suggestionSearch;
	
	@FindBy(className = "oxd-input--active")
	private List<WebElement> textSearch;
	
	@FindBy(className = "oxd-select-text-input")
	private List<WebElement> dropdownSearch;
	
	@FindBy(className = "//*[@role='option']/span")
	private List<WebElement> dropdownSelection;
	
	@FindBy(xpath = "//*[@type='button']")
	private WebElement resetButton;
	
	@FindBy(xpath = "//*[@type='submit']")
	private List<WebElement> mainButton;
	
	public void suggestionSearch(String field, String keyword, int index) {
		if(field.equalsIgnoreCase("employee")) {
			hintSearch.get(0).sendKeys(keyword);
		} else {
			hintSearch.get(1).sendKeys(keyword);
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dropdownSelection.get(index).click();
	}
	
	public void searchEmployeeId(String empId) {
		textSearch.get(1).sendKeys(empId);
	}
	
	public void dropdownSelect(String dropName, String val) {
		if(dropName.equalsIgnoreCase("status")) {
			dropdownSearch.get(0).click();
		} else if(dropName.equalsIgnoreCase("include")) {
			dropdownSearch.get(1).click();
		} else if(dropName.equalsIgnoreCase("title")) {
			dropdownSearch.get(2).click();
		} else {
			dropdownSearch.get(3).click();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < dropdownSelection.size(); i++) {
			if(val.equalsIgnoreCase(dropdownSelection.get(i).getText())) {
				dropdownSelection.get(i).click();
			}
		}
	}
	
	public void searchReset() {
		resetButton.click();
	}
	
	public void searchSubmit() {
		mainButton.get(0).click();
	}
	
	public void addEmployee() {
		mainButton.get(1).click();
	}
}
