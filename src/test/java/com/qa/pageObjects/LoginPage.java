package com.qa.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.reports.AllureListener;
import com.qa.reports.ExtentLogger;
import com.qa.reports.ExtentManager;
import com.qa.reports.ExtentReport;
import com.qa.reusableComponents.CommonMethods;

import io.qameta.allure.Step;

public class LoginPage {

	WebDriver driver;

	@FindBy(name = "username")
	WebElement userID;

	@FindBy(name = "password")
	WebElement pass;

	@FindBy(css = "[value='Log In']")
	WebElement loginButton;

	@FindBy(css = ".smallText")
	WebElement headerUserName;

	@FindBy(linkText = "Log Out")
	WebElement logOut;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@Step("Login to the application")
	public void loginIntoApplication(String userId, String password) {
		enterUserID(userId);
		enterUserPassword(password);
		clickOnLoginButoon();
	}

	@Step("Enter User ID")
	public void enterUserID(String userId) {
		CommonMethods.waitForElement(driver, "User ID", userID);
		CommonMethods.sendKeys_custom(userID, "User ID", userId);

	}

	@Step("Enter User Password")
	public void enterUserPassword(String password) {
		CommonMethods.waitForElement(driver, "Password", pass);
		CommonMethods.sendKeys_custom(pass, "Password", password);

	}

	@Step("Click on Login button")
	public void clickOnLoginButoon() {
		CommonMethods.waitForElement(driver, "Login Button", loginButton);
		CommonMethods.click_custom(loginButton, "Login Button");
		verifyUserLogin();
	
	}


    @Step("Verify user login status")
	public void verifyUserLogin() {
		
		try {
			CommonMethods.waitForElement(driver, "Log Out Button", logOut);
			if (logOut.isDisplayed()) {
			
				ExtentLogger.pass("User sucessfully logged into Para Bank Application");
				AllureListener.saveTextLog("User sucessfully logged into Para Bank Application");
			} else {
				ExtentLogger.fail("User is not able to login into Para Bank application");
				AllureListener.saveTextLog("User is not able to login into Para Bank application");
				Assert.fail("User is not able to login into Para Bank application");

			}

		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("User is not able to login into Para Bank application");
			AllureListener.saveTextLog("User is not able to login into Para Bank application");
			Assert.fail("User is not able to login into Para Bank application");
		}
		
	}
	
	
	/*
	@Step("Get Welcome text with Username")
	public String getHeaderUserNameWithWelcomeText() {
		try {
			headerUserName.getText();
			ExtentLogger.pass("Username captured from Homepage : " + headerUserName.getText());
			AllureListener.saveTextLog("Username captured from Homepage : " + headerUserName.getText());

		} catch (Exception e) {
			// TODO: handle exception
			ExtentLogger.fail("User not able to login into application :Not able to get Username from homepage");
			AllureListener
					.saveTextLog("User not able to login into application :Not able to get Username from homepage");
			Assert.fail("User not able to login into application :Not able to get Username from homepage");
		}
		return headerUserName.getText();
	}
*/
	
	
}
