package org.selenium.orangeHRM.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.selenium.orangeHRM.utils.ReusedFeature;

public class DashboardPage extends ReusedFeature {

WebDriver driver;
	
	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(className = "oxd-topbar-header-breadcrumb-module")
	private WebElement dashboardTitle;
	
	@FindBy(xpath = "//*[@title='Assign Leave']")
	private WebElement qlAssignLeave;
	
	@FindBy(xpath = "//*[@title='Leave List']")
	private WebElement qlLeaveList;
	
	@FindBy(xpath = "//*[@title='Timesheets']")
	private WebElement qlTimesheet;
	
	@FindBy(xpath = "//*[@title='Apply Leave']")
	private WebElement qlApplyLeave;
	
	@FindBy(xpath = "//*[@title='My Leave']")
	private WebElement qlMyLeave;
	
	@FindBy(xpath = "//*[@title='My Timesheet']")
	private WebElement qlMyTimesheet;
	
	@FindBy(xpath = "//*[@class='My oxd-icon-button oxd-icon-button--warn orangehrm-report-icon']")
	private WebElement maLeaveRequestApprove;
	
	@FindBy(xpath = "//*[@class='oxd-icon-button oxd-icon-button--success orangehrm-report-icon']")
	private WebElement maTimesheetsApprove;
	
	@FindBy(xpath = "//*[@class='oxd-icon-button oxd-icon-button--info orangehrm-report-icon']")
	private WebElement maCandidateInterview;
	
	@FindBy(className = "oxd-input")
	private WebElement sbSearch;
	
	@FindBy(className = "oxd-main-menu-item-wrapper")
	private List<WebElement> menus;
	
	public void searchMenuAndClick(String menu) {
		sbSearch.sendKeys(menu);
		menus.get(0).click();
	}
}
