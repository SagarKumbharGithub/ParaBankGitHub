package com.qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.pageObjects.HomePage;
import com.qa.pageObjects.LoginPage;
import com.qa.pageObjects.OpenNewAccount;
import com.qa.reports.AllureListener;
import com.qa.reports.ExtentReport;
import com.qa.reusableComponents.ReadExcel;
import com.qa.testComponents.BaseClass;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners({ AllureListener.class })
//@Listeners(com.qa.reports.ExtentListener.class)
@Epic("Account services")
@Feature("Account services_Open New Account")

public class OpenNewAccountSuite extends BaseClass {

	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\UseCaseData\\ParaBank_TestCases.xlsx";

	@Severity(SeverityLevel.CRITICAL)
	@Description("TC_ONA_001:Verify that whether the What type of Account would you like to open? Field contains CHECKING option ")
	@Story("Open New Account Functionality Validation")
	@Test(groups = {
			"Regression" }, enabled = true, priority = 1, description = "TC_ONA_001:Verify that whether the What type of Account would you like to open? Field contains CHECKING option ")
	public void TC_ONA_001_VerifyWhetherCheckingOptionPresentInAccountTypeDropdown() throws Exception {
		Map<String, String> input = ReadExcel.getExcelData(filePath, "Open New Account", "TC_ONA_001");

		ExtentReport.createTest(input.get("TestCaseID")+"_"+input.get("TestCaseDescription"), input.get("TesterName"), input.get("FeatureName"),input.get("browser"));
		
		initialize_driver(input.get("browser"));
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginIntoApplication(username,password);
		Assert.assertTrue(loginPage.verifyUserLoginStatus());
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnOpenNewAccountTab();
		OpenNewAccount openNewAccount = new OpenNewAccount(getDriver());
		boolean accountTypeStatus = openNewAccount.validateSpecifiedAccountTypeOptionAvailableInDrpdown(input.get("accountType"));
		Assert.assertTrue(accountTypeStatus, "Validated presence of Account Type dropdown value ");

	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("TC_ONA_002:Verify that whether the What type of Account would you like to open? Field contains SAVING option ")
	@Story("Open New Account Functionality Validation")
	@Test(groups = {
			"Regression" }, enabled = true, priority = 2, description = "TC_ONA_002:Verify that whether the What type of Account would you like to open? Field contains SAVING option ")
	public void TC_ONA_002_VerifyWhetherSavingOptionPresentInAccountTypeDropdown() throws Exception {
		Map<String, String> input = ReadExcel.getExcelData(filePath, "Open New Account", "TC_ONA_002");
		ExtentReport.createTest(input.get("TestCaseID")+"_"+input.get("TestCaseDescription"), input.get("TesterName"), input.get("FeatureName"),input.get("browser"));
		
		initialize_driver(input.get("browser"));
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginIntoApplication(username,password);
		Assert.assertTrue(loginPage.verifyUserLoginStatus());
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnOpenNewAccountTab();
		OpenNewAccount openNewAccount = new OpenNewAccount(getDriver());
		boolean accountTypeStatus = openNewAccount.validateSpecifiedAccountTypeOptionAvailableInDrpdown(input.get("accountType"));
		Assert.assertTrue(accountTypeStatus, "Validated presence of Account Type dropdown value ");

	}

	@Severity(SeverityLevel.CRITICAL)
	@Description("TC_ONA_003:Verify whether the user able to create new account with CHECKING as a account type")
	@Story("Open New Account Functionality Validation")
	@Test(groups = { "Regression" }, enabled = true, priority = 3,description = "TC_ONA_003:Verify whether the user able to create new account with CHECKING as a account type")
	public void TC_ONA_003_VerifyWhetherTheUserAbleToCreateNewAccountWithCheckingAccountType() throws Exception {
		Map<String, String> input = ReadExcel.getExcelData(filePath, "Open New Account", "TC_ONA_003");

		ExtentReport.createTest(input.get("TestCaseID")+"_"+input.get("TestCaseDescription"), input.get("TesterName"), input.get("FeatureName"),input.get("browser"));
		
		initialize_driver(input.get("browser"));
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginIntoApplication(username,password);
		Assert.assertTrue(loginPage.verifyUserLoginStatus());
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnOpenNewAccountTab();
		OpenNewAccount openNewAccount = new OpenNewAccount(getDriver());
		openNewAccount.selectAccountTypeFromDropdown(input.get("accountType"));
		openNewAccount.clickOnOpenNewAccountButton();
		boolean accountStatus = openNewAccount.validateNewAccountOpenedSuccessfullyOrNot(input.get("accountOpenedSuceessMsg"));
		Assert.assertTrue(accountStatus, "Validated whether the New Account Opened Sucessfully or not");

	}
	
	@Severity(SeverityLevel.CRITICAL)
	@Description("TC_ONA_004:Verify whether the user able to create new account with SAVING as a account type")
    @Story("Open New Account Functionality Validation")
	@Test(groups = { "Regression" }, enabled = true, priority = 4,description = "TC_ONA_004:Verify whether the user able to create new account with SAVING as a account type")
	public void TC_ONA_004_VerifyWhetherTheUserAbleToCreateNewAccountWithSavingAccountType() throws Exception {
		Map<String, String> input = ReadExcel.getExcelData(filePath, "Open New Account", "TC_ONA_004");

		ExtentReport.createTest(input.get("TestCaseID")+"_"+input.get("TestCaseDescription"), input.get("TesterName"), input.get("FeatureName"),input.get("browser"));
		
		initialize_driver(input.get("browser"));
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginIntoApplication(username,password);
		Assert.assertTrue(loginPage.verifyUserLoginStatus());
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnOpenNewAccountTab();
		OpenNewAccount openNewAccount = new OpenNewAccount(getDriver());
		openNewAccount.selectAccountTypeFromDropdown(input.get("accountType"));
		openNewAccount.clickOnOpenNewAccountButton();
		boolean accountStatus = openNewAccount.validateNewAccountOpenedSuccessfullyOrNot(input.get("accountOpenedSuceessMsg"));
		Assert.assertTrue(accountStatus, "Validated whether the New Account Opened Sucessfully or not");

	}
}
