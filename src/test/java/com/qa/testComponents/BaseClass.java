package com.qa.testComponents;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.qameta.allure.Step;

public class BaseClass {

	public String username="mike";
	public String password="mike@1234";
	
	WebDriver driver;
	public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();
	

	//@BeforeMethod(alwaysRun = true)
	public ThreadLocal<WebDriver> initialize_driver(String browser) throws Exception {

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: PropertiesOperations.getPropertyValueByKey("browser");
		String url = PropertiesOperations.getPropertyValueByKey("url");

		if (browserName.equalsIgnoreCase("chrome")) {
			setUpChrome();

		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			setUpFireFox();
		}

		else if (browserName.equalsIgnoreCase("edge")) {

			setUpMircrosoftEdge();

		}

		else if (browserName.equalsIgnoreCase("remote")) {

			setUpRemoteDriver(browser);

		}
		tdriver = openurl(url);
		return tdriver;

	}
	public static synchronized WebDriver getDriver() {
		return tdriver.get();
	}

	@Step("Navigate to url :[0]")
	public ThreadLocal<WebDriver> openurl(String url) throws MalformedURLException {
		// String host=PropertiesOperations.getPropertyValueByKey("hosturl");
		// driver = new RemoteWebDriver(new URL(host), capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().deleteAllCookies();
		driver.get(url);
		tdriver.set(driver);
		tdriver.get();
		return tdriver;
	}

	@AfterMethod(alwaysRun = true)
	@Step("Closing Browser")
	public void tearDown() {
		BaseClass.getDriver().quit();

	}

	@Step("Chrome Browser Opened ")
	public void setUpChrome() {

		driver = new ChromeDriver();
	}

	@Step("FireFox Browser Opened ")
	public void setUpFireFox() {

		driver = new FirefoxDriver();
	}

	@Step("Microsoft Edge Browser Opened ")
	public void setUpMircrosoftEdge() {
		driver = new EdgeDriver();
	}
	
	public void setUpRemoteDriver(String browserName)  {
		try {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName(browserName); // MicrosoftEdge
			driver = new RemoteWebDriver(new URL("http://localhost:4444/"), cap);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	
	
	/*
	@Step("Remote Driver Opned ")
	public void setUpRemoteDriver() throws MalformedURLException {
		DesiredCapabilities cap= new DesiredCapabilities();
		cap.setBrowserName("chrome");		//MicrosoftEdge
		driver=new RemoteWebDriver(new  URL("http://localhost:4444/"),cap);
	}
	*/
	}

}
