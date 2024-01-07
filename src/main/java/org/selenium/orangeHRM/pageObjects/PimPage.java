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
	
	@FindBy(css = ".oxd-autocomplete-option")
	private List<WebElement> dropdownSelection;
	
	@FindBy(xpath = "//*[@type='button']")
	private WebElement resetButton;
	
	@FindBy(xpath = "//*[@type='submit']")
	private List<WebElement> mainButton;
	
	@FindBy(xpath = "//*[@class='oxd-table-cell oxd-padding-cell']")
	private List <WebElement> tableData;
	
	public String verifyPage() {
		String title = pimTitle.getText();
		return title;
	}
	
	public void suggestionSearch(String field, String keyword, int index) {
		if(field.equalsIgnoreCase("employee")) {
			hintSearch.get(0).sendKeys(keyword);
		} else {
			hintSearch.get(1).sendKeys(keyword);
		}
		
		try {
			Thread.sleep(3000);
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
	
	public String resultGetter(String func, String input) {
		String result = "";
		switch(func) {
			case "id":
				for(int i = 1; i < tableData.size(); i++) {
					if(!tableData.get(i).getText().equalsIgnoreCase(input)) {
						if(i + 9 >= tableData.size()) {
							result = "no result found";
						} else {
							i = i + 9;
						}
					} else {
						result = tableData.get(i).getText();
						break;
					}
				}
				break;
			
			case "firstname":
				for(int i = 2; i < tableData.size(); i++) {
					if(!tableData.get(i).getText().equalsIgnoreCase(input)) {
						if(i + 9 >= tableData.size()) {
							result = "no result found";
						} else {
							i = i + 9;
						}
					} else {
						result = tableData.get(i).getText();
						break;
					}
				}
				break;
			
			case "lastname":
				for(int i = 3; i < tableData.size(); i++) {
					if(!tableData.get(i).getText().equalsIgnoreCase(input)) {
						if(i + 9 >= tableData.size()) {
							result = "no result found";
						} else {
							i = i + 9;
						}
					} else {
						result = tableData.get(i).getText();
						break;
					}
				}
				break;
			
			case "jobtitle":
				for(int i = 4; i < tableData.size(); i++) {
					if(!tableData.get(i).getText().equalsIgnoreCase(input)) {
						if(i + 9 >= tableData.size()) {
							result = "no result found";
						} else {
							i = i + 9;
						}
					} else {
						result = tableData.get(i).getText();
						break;
					}
				}
				break;
			
			case "employmentstatus":
				for(int i = 5; i < tableData.size(); i++) {
					if(!tableData.get(i).getText().equalsIgnoreCase(input)) {
						if(i + 9 >= tableData.size()) {
							result = "no result found";
						} else {
							i = i + 9;
						}
					} else {
						result = tableData.get(i).getText();
						break;
					}
				}
				break;
			
			case "subunit":
				for(int i = 6; i < tableData.size(); i++) {
					if(!tableData.get(i).getText().equalsIgnoreCase(input)) {
						if(i + 9 >= tableData.size()) {
							result = "no result found";
						} else {
							i = i + 9;
						}
					} else {
						result = tableData.get(i).getText();
						break;
					}
				}
				break;
			
			case "supervisor":
				for(int i = 7; i < tableData.size(); i++) {
					if(!tableData.get(i).getText().equalsIgnoreCase(input)) {
						if(i + 9 >= tableData.size()) {
							result = "no result found";
						} else {
							i = i + 9;
						}
					} else {
						result = tableData.get(i).getText();
						break;
					}
				}
				break;
		}
		
		return result;
	}
}
