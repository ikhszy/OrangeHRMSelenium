package org.selenium.orangeHRM.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.orangeHRM.utils.ReusedFeature;

public class ResetPasswordPage extends ReusedFeature {
	
	WebDriver driver;
	
	public ResetPasswordPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "username")
	private WebElement userInput;
	
	@FindBy(xpath = "//*[@type='submit']")
	private WebElement submitButton;
	
	@FindBy(xpath = "//*[@type='button']")
	private WebElement cancelButton;
	
	@FindBy(className = "oxd-input-group__message")
	private WebElement errText;
	
	@FindBy(className = "orangehrm-card-container")
	public WebElement successBox;
	
	public void userType(String user) {
		userInput.sendKeys(user);
	}
	
	public void userClear() {
		userInput.clear();
	}
	
	public void clickSubmitButton() {
		submitButton.click();
	}
	
	public void clickCancelButton() {
		cancelButton.click();
	}
	
	public String errorText() {
		String ertext = errText.getText();
		return ertext;
	}
}
