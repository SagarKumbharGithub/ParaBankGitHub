package com.qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.pageObjects.SignUpPage;
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

@Listeners({AllureListener.class})
//@Listeners(com.qa.reports.ExtentListener.class)

@Epic("User Registration")
@Feature("Sign Up User")
public class UserSignUpSuite extends BaseClass{


	@Severity(SeverityLevel.CRITICAL)
	@Description("TC_RF_001 :Verify that user is able to Register successfully in the ParaBank application after providing the valid Register Test Data")
	@Story("New User SignUp Functionality Validation")
	@Test(groups= {"Regression","SignUp"},enabled=true,priority=1,description = "TC_RF_001 :Verify that user is able to Register successfully in the ParaBank application after providing the valid Register Test Data")
	public void TC_RF_001_VerifyWhetherTheUserABleToRegisterInParaBankApplicationWithValidTestData() throws Exception
	{
		String filePath=System.getProperty("user.dir")+"\\src\\test\\resources\\UseCaseData\\ParaBank_TestCases.xlsx";
		Map<String, String> input=ReadExcel.getExcelData(filePath, "SignUp", "TC_RF_001");
		
		ExtentReport.createTest(input.get("TestCaseID")+"_"+input.get("TestCaseDescription"), input.get("TesterName"), input.get("FeatureName"),input.get("browser"));
		initialize_driver(input.get("browser"));
		SignUpPage signUp= new SignUpPage(getDriver());
		signUp.clickOnRegisterLink();
		signUp.enterFirstName(input.get("firstName"));
		signUp.enterLastName(input.get("lastName"));
		signUp.enterAddress(input.get("address"));
		signUp.enterCityName(input.get("city"));
		signUp.enterStateName(input.get("state"));
		signUp.enterZipCode(input.get("zipCode"));
		signUp.enterPhoneNumber(input.get("phone"));
		signUp.enterSSN(input.get("sSN"));
		signUp.enterUsername(input.get("username"));
		signUp.enterPassword(input.get("password"));
		signUp.enterConfirmPassword(input.get("confirmPassword"));
		signUp.clickOnRegisterButtonForSubmitUserSignUpForm();
	    Assert.assertTrue(signUp.verifyUserSuccessfullyCompletedSignUpProcess(input.get("accountSuccessfullyRegiseredMsg")));

	}
	
}
