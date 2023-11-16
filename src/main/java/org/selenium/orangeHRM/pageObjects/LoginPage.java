package org.selenium.orangeHRM.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.orangeHRM.utils.ReusedFeature;

public class LoginPage extends ReusedFeature {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "username")
	private WebElement userInput;
	
	@FindBy(name = "password")
	private WebElement passInput;
	
	@FindBy(xpath = "//*[@type='submit']")
	private WebElement loginButton;
	
	@FindBy(className = "orangehrm-login-forgot-header")
	private WebElement forgotPasswordButton;
	
	@FindBy(className = "oxd-alert-content-text")
	private WebElement errorNotification;
	
	@FindBy(className = "oxd-input-group__message")
	private List<WebElement> errorText;

	public void usernameInput(String user) {
		userInput.sendKeys(user);
	}
	
	public void usernameClear() {
		userInput.clear();
	}
	
	public void passInput(String pass) {
		passInput.sendKeys(pass);
	}
	
	public void passClear() {
		passInput.clear();
	}
	
	public void loginClick() {
		loginButton.click();
	}
	
	public void forgotPassClick() {
		forgotPasswordButton.click();
	}
	
	public void forgotClick() {
		forgotPasswordButton.click();
	}
	
	public String errorText(int index) {
		String erText = errorText.get(index).getText();
		return erText;
	}
	
	public int errTextCount() {
		int errCount = errorText.size();
		return errCount;
	}
	
	public String errNotifText() {
		String notText = errorNotification.getText();
		return notText;
	}
	
	public boolean errNotifShown() {
		boolean err = errorNotification.isDisplayed();
		return err;
	}
}
