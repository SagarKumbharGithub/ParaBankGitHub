package com.qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.pageObjects.HomePage;
import com.qa.pageObjects.LoginPage;
import com.qa.pageObjects.TransferFundsPage;
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
@Feature("Account services_Transfer Funds")
public class TransferFundsSuite extends BaseClass {

	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\UseCaseData\\ParaBank_TestCases.xlsx";
    
	@Severity(SeverityLevel.BLOCKER)
	@Description("TC_TF_001:Verify whether the user land on Transfer Funds page")
	@Story("Transfer Funds Functionality Validation")
	@Test(groups = {
			"Regression" }, enabled = true, priority = 1, description = "TC_TF_001:Verify whether the user land on Transfer Funds page")
	public void TC_TF_001_VerifyWhetherUserIsLandOnTransferFundPage() throws Exception {
		Map<String, String> input = ReadExcel.getExcelData(filePath, "Transfer Funds", "TC_TF_001");

		ExtentReport.createTest(input.get("TestCaseID")+"_"+input.get("TestCaseDescription"), input.get("TesterName"), input.get("FeatureName"),input.get("browser"));
		
		initialize_driver(input.get("browser"));
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginIntoApplication(username,password);
		Assert.assertTrue(loginPage.verifyUserLoginStatus());
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnTransferFundsTab();
		TransferFundsPage transferFunds=new TransferFundsPage(getDriver());
		boolean textStatus=transferFunds.verifyUserLandingTransferFundsPage();
		Assert.assertTrue(textStatus,"Validated user land on Transfer funds Page");
		
	}

	@Severity(SeverityLevel.BLOCKER)
	@Description("TC_TF_002:Verify that user able to transfer the funds from one account to another account ")
	@Story("Transfer Funds Functionality Validation")
	@Test(groups = {
			"Regression" }, enabled = true, priority = 2, description = "TC_TF_002:Verify that user able to transfer the funds from one account to another account ")
	public void TC_TF_002_VerifyWhetherTheUserAbleToTransferFund() throws Throwable {
		Map<String, String> input = ReadExcel.getExcelData(filePath, "Transfer Funds", "TC_TF_002");

		ExtentReport.createTest(input.get("TestCaseID")+"_"+input.get("TestCaseDescription"), input.get("TesterName"), input.get("FeatureName"),input.get("browser"));
		
		initialize_driver(input.get("browser"));
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.loginIntoApplication(username,password);
		Assert.assertTrue(loginPage.verifyUserLoginStatus());
		HomePage homePage = new HomePage(getDriver());
		homePage.clickOnTransferFundsTab();
		TransferFundsPage transferFunds=new TransferFundsPage(getDriver());		
		transferFunds.enterTransferFundAmount(input.get("amount"));
		transferFunds.clickOnTransferButton();
		boolean fundTransferStatus=transferFunds.verifyUserSucessfullyTransferedFund(input.get("transferSuccessMsg"));
	    Assert.assertTrue(fundTransferStatus,"Fund Transfer status validated");
		
	}

}
