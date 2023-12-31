package org.selenium.orangeHRM.testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.selenium.orangeHRM.pageObjects.AddUserPage;
import org.selenium.orangeHRM.pageObjects.AdminPage;
import org.selenium.orangeHRM.pageObjects.DashboardPage;
import org.selenium.orangeHRM.pageObjects.LoginPage;
import org.selenium.orangeHRM.pageObjects.PimPage;
import org.selenium.orangeHRM.pageObjects.ProfileBadge;
import org.selenium.orangeHRM.pageObjects.ResetPasswordPage;
import org.selenium.orangeHRM.utils.Variables;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	public LoginPage loginPage;
	public ResetPasswordPage resetPage;
	public DashboardPage dashPage;
	public AdminPage admPage;
	public AddUserPage adduserPage;
	public ProfileBadge profBadge;
	public PimPage pimPage;
	public Faker faker;
	public Variables vbl;
	
	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\org\\selenium\\orangeHRM\\resources\\config.properties");
		prop.load(fis);
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(chromeOptions);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// not sure how to prepare this haha
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "edge.exe");
			driver = new EdgeDriver();
		} 

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeClass
	public void lauchApplication() throws IOException {
		driver = initializeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		loginPage = new LoginPage(driver);
		resetPage = new ResetPasswordPage(driver);
		dashPage = new DashboardPage(driver);
		admPage = new AdminPage(driver);
		adduserPage = new AddUserPage(driver);
		profBadge = new ProfileBadge(driver);
		pimPage = new PimPage(driver);
		faker = new Faker();
		vbl = new Variables();
	}
	
	@AfterClass
	public void tearDown() {
		driver.close();
	}

}
