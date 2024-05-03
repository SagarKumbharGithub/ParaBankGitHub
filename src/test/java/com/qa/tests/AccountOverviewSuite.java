package com.qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.pageObjects.AccountOverviewPage;
import com.qa.pageObjects.HomePage;
import com.qa.pageObjects.LoginPage;
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
@Feature("Account services_Account Overview")
public class AccountOverviewSuite extends BaseClass {

	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\UseCaseData\\ParaBank_TestCases.xlsx";
    SoftAssert softAssert=new SoftAssert();
    
	@Severity(SeverityLevel.BLOCKER)
	@Description("TC_AOF_001:Verify that Account Overview page is displayed")
	@Story("Account Overview Functionality Validation")
	@Test(groups = {
			"Regression" }, enabled = true, priority = 1, description = "TC_AOF_001:Verify that Account Overview page is displayed")
	public void TC_AOF_001_VerifyWhetherAccountOverviewPageDisplayedOrNot() throws Exception {
		Map<String, String> input = ReadExcel.getExcelData(filePath, "Accounts Overview", "TC_AOF_001");

		
		ExtentReport.createTest(input.get("TestCaseID")+"_"+input.get("TestCaseDescription"), input.get("TesterName"), input.get("FeatureName"),input.get("browser"));
		
		initialize_driver(input.get("browser"));
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginIntoApplication(username,password);
		Assert.assertTrue(loginPage.verifyUserLoginStatus());
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnAccountOverviewTab();
		AccountOverviewPage accountOverview = new AccountOverviewPage(getDriver());		
		Assert.assertTrue(accountOverview.validateAccountOverviewTextDisplayedOnHeader(), " Validated user land on Account Overview page");

	}

	@Severity(SeverityLevel.BLOCKER)
	@Description("TC_AOF_002:Verify  after click on Account Number in Account Overview page, whether the user land Account details page  and same Account number is displayed in Account Details page")
	@Story("Account Overview Functionality Validation")
	@Test(groups = {
			"Regression" }, enabled = true, priority = 2, description = "TC_AOF_002:Verify  after click on Account Number in Account Overview page, whether the user land Account details page  and same Account number is displayed in Account Details page")
	public void TC_AOF_002_VerifyUserLandOnAccountDetailsPageAfterClickOnAccountNumberFromAccountOverviewPage() throws Exception {
		Map<String, String> input = ReadExcel.getExcelData(filePath, "Accounts Overview", "TC_AOF_002");

		
		ExtentReport.createTest(input.get("TestCaseID")+"_"+input.get("TestCaseDescription"), input.get("TesterName"), input.get("FeatureName"),input.get("browser"));
		
		initialize_driver(input.get("browser"));
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginIntoApplication(username,password);
		Assert.assertTrue(loginPage.verifyUserLoginStatus());
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnAccountOverviewTab();
		AccountOverviewPage accountOverview = new AccountOverviewPage(getDriver());
		int accountNumberAO=accountOverview.getAccountNumberFromAccountOverviewPage();
		accountOverview.clickOnAccountNumberFromAccountOverviewPage();		
		softAssert.assertTrue(accountOverview.validateAccountDetailsTextDisplyedOnHeader(),"Validated Account Details text is diplsyed on header");
		int accountNumberAD=accountOverview.getAccountNumberFromAccountDetailsPage();
		softAssert.assertEquals(accountNumberAD, accountNumberAO);
		softAssert.assertAll();
	}

}
