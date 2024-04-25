package com.qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.pageObjects.HomePage;
import com.qa.pageObjects.LoginPage;
import com.qa.reports.AllureListener;
import com.qa.reports.ExtentListener;
import com.qa.reports.ExtentLogger;
import com.qa.reports.ExtentReport;
import com.qa.reusableComponents.ReadExcel;
import com.qa.testComponents.BaseClass;
import com.qa.testComponents.TestRetryAnalyzer;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({AllureListener.class})
//@Listeners(com.qa.reports.ExtentListener.class)
//retryAnalyzer = TestRetryAnalyzer.class

@Epic("User Management")
@Feature("Login User")
public class LoginSuite extends BaseClass{



	@Severity(SeverityLevel.BLOCKER)
	@Description("TC_LF_001:Verify that user is able to login successfully in the ParaBank application after providing the valid username and password")
	@Story("Login User Functionality Validation")
	@Test(groups= {"Regression","Login"},enabled=true,priority=1,description = "TC_LF_001:Verify that user is able to login successfully in the ParaBank application after providing the valid username and password")
	public void TC_LF_001_VerifyUserLoginIntoApplicationWithValidCredentials() throws Exception
	{
		
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\UseCaseData\\ParaBank_TestCases.xlsx";
		Map<String, String> input=ReadExcel.getExcelData(filePath, "Login", "TC_LF_001");
	

		LoginPage loginPage= new LoginPage(getDriver());
		loginPage.loginIntoApplication(input.get("username"), input.get("password"));
	
		HomePage homePage=new HomePage(getDriver());
		// verify successful login  welcome message with combination of user firstName+lastName
		String actualUserName=homePage.getHeaderUserNameWithWelcomeText();
		String  expectedUserName="Welcome"+" "+input.get("firstName")+" "+input.get("lastName");
		
		Assert.assertEquals(actualUserName, expectedUserName);
	
	    
	}
	
}
