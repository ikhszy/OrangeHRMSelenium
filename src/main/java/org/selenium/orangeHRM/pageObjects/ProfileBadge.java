package org.selenium.orangeHRM.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.orangeHRM.utils.ReusedFeature;

public class ProfileBadge extends ReusedFeature {
	
	WebDriver driver;
	
	public ProfileBadge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "oxd-userdropdown-tab")
	private WebElement dropdown;
	
	@FindBy(className = "oxd-userdropdown-link")
	private List<WebElement> dropdownSelection;
	
	public void dropSelection(String menu) {
		dropdown.click();
		waitForElementToAppear(By.className("oxd-userdropdown-link"));
		
		if(menu.equalsIgnoreCase("About")) {
			dropdownSelection.get(0).click();
		} else if(menu.equalsIgnoreCase("Support")) {
			dropdownSelection.get(1).click();
		} else if(menu.equalsIgnoreCase("change password")) {
			dropdownSelection.get(2).click();
		} else {
			dropdownSelection.get(3).click();
		}
	}

}
